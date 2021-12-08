package com.kdyncs.dragonsong.api.service.clamav;

import com.kdyncs.dragonsong.api.service.clamav.constant.AVCommand;
import com.kdyncs.dragonsong.api.service.clamav.constant.AVResponse;
import com.kdyncs.dragonsong.api.service.clamav.exception.AbortedScanException;
import com.kdyncs.dragonsong.api.util.misc.Byteinator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

@Service
public class AntivirusService {
    
    public static final int BUFFER_SIZE = 2048;
    // Logging
    private static final Logger log = LogManager.getLogger();
    
    // Service Definition
    private String hostname;
    private int port;
    private int timeout;
    
    public boolean isAvailable() {
        
        //Open Sockets and Streams
        try (Socket socket = new Socket(hostname, port);
             InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()) {
            
            log.trace("PING");
            
            //Timeout after a while
            socket.setSoTimeout(timeout);
            
            //Write Ping Command
            output.write(AVCommand.PING.getValue());
            output.flush();
            
            //Get Data from Input Stream
            byte[] data = new byte[4]; //The response is always 4 Bytes, apparently.
            input.read(data, 0, 4);
            
            if (AVResponse.PONG.getValue().equals(new String(data))) {
                log.trace("PONG");
                return true;
            }
            
            return false;
            
        } catch (IOException ex) {
            log.error("Problem Opening Connection", ex);
        }
        
        return false;
    }
    
    public FileReport scan(MultipartFile file) throws AbortedScanException {
        
        //Note Start of Operation
        log.debug("STARTING SCAN OPERATION");
        Instant start = Instant.now();
        
        //Open some Input Output Streams
        try (Socket socket = new Socket(hostname, port);
             InputStream input = socket.getInputStream();
             OutputStream output = new BufferedOutputStream(socket.getOutputStream())) {
            
            //Extract Data from File
            byte[] fileData = file.getBytes();
            
            //Create a File Report
            FileReport report = new FileReport();
            report.setFileName(file.getOriginalFilename());
            report.setSize(fileData.length);
            
            //
            log.debug("Processing File: {} Bytes", fileData.length);
            log.debug("SENDING SCAN START COMMAND");
            
            //Create a Stream we can feed to the Antivirus service
            InputStream fileStream = new ByteArrayInputStream(fileData);
            
            //Timeout after a period
            socket.setSoTimeout(timeout);
            
            //Tell it we're going to scan something
            output.write(AVCommand.SCAN_START.getValue());
            output.flush();
            
            //Allocate a 2 Kilobyte Buffer
            byte[] fileBuffer = new byte[BUFFER_SIZE];
            
            //Read an initial chunk of file data into buffer
            int buffered = fileStream.read(fileBuffer);
            
            log.debug("SENDING FILE DATA");
            
            //As long as we have file data remaining we need to upload data
            while (buffered > 0) {
                
                //Tell ClamAV We're about to send some data and the size of it.
                output.write(Byteinator.intToBytes(buffered));
                
                //Write the Buffer to the Antivirus.
                output.write(fileBuffer, 0, buffered);
                
                if (input.available() > 0) {
                    log.debug("SCANNING INTERRUPTED");
                    byte[] response = read(input);
                    throw new AbortedScanException("SCAN ABORTED: " + Byteinator.bytesToString(response, StandardCharsets.US_ASCII));
                }
                
                //Buffer the next piece of file data if available
                buffered = fileStream.read(fileBuffer);
                
            }
            
            log.debug("FINISHED SENDING FILE DATA");
            
            //Don't need file data anymore
            fileStream.close();
            
            //Tell it we're done scanning
            output.write(AVCommand.SCAN_TERMINATE.getValue());
            output.flush();
            
            log.debug("CHECKING SCAN RESULTS");
            
            //Get Scan Results
            String response = Byteinator.bytesToString(read(input), StandardCharsets.US_ASCII);
            log.debug("RESPONSE FROM SCAN: {}", response);
            
            Duration elapsed = Duration.between(start, Instant.now());
            log.debug("COMPLETED SCAN OPERATION IN: {}", elapsed);
            
            report.setProcessingTime(elapsed);
            
            if (response.equals(AVResponse.CLEAN.getValue())) {
                report.setClean(true);
                return report;
            }
            
            report.setClean(false);
            return report;
            
        } catch (IOException ex) {
            log.error("IO Error: ", ex);
            throw new AbortedScanException("SCAN ABORTED: IO ERROR");
        }
    }
    
    private byte[] read(InputStream input) throws IOException {
        
        //A Stream to temporarily store data.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        //A 2 Kilobyte Buffer To Read Stream Output
        byte[] outputBuffer = new byte[BUFFER_SIZE];
        
        log.debug("READING RESULT DATA");
        
        //Perform an Initial Read
        int buffered = input.read(outputBuffer);
        output.write(outputBuffer, 0, buffered);
        
        //Pull any remaining data from the Input if there is any
        while (buffered > 0 && input.available() > 0) {
            
            log.debug("MORE DATA IN PIPE");
            
            buffered = input.read(outputBuffer);
            output.write(outputBuffer, 0, buffered);
        }
        
        log.debug("FINISHED READING RESULT DATA");
        
        //Return all read data
        return output.toByteArray();
    }
    
    @PostConstruct
    public void init() {
        log.info("Loading {}", this.getClass().getSimpleName());
        log.trace("Host:{} ", hostname);
        log.trace("Post: {}", port);
        log.trace("Timeout: {}", timeout);
    }
}
