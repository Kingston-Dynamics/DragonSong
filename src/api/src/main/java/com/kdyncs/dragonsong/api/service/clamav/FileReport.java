package com.kdyncs.dragonsong.api.service.clamav;

import java.time.Duration;

public class FileReport {
    
    private String fileName;
    private int size;
    private boolean clean;
    private Duration processingTime;
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isClean() {
        return clean;
    }
    
    public void setClean(boolean clean) {
        this.clean = clean;
    }
    
    public Duration getProcessingTime() {
        return processingTime;
    }
    
    public void setProcessingTime(Duration processingTime) {
        this.processingTime = processingTime;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
}
