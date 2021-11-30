package com.kingstondynamics.dragonsong.api.service.clamav;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ScanReport {
    
    private boolean clean;
    private Duration reportRuntime;
    private ArrayList<FileReport> filesScanned;
    
    public ScanReport() {
        filesScanned = new ArrayList<>();
    }
    
    public boolean isClean() {
        return clean;
    }
    
    public void setClean(boolean clean) {
        this.clean = clean;
    }
    
    public Duration getReportRuntime() {
        return reportRuntime;
    }
    
    public void setReportRuntime(Duration reportRuntime) {
        this.reportRuntime = reportRuntime;
    }
    
    public List<FileReport> getFilesScanned() {
        return filesScanned;
    }
    
    public void addScannedFile(FileReport file) {
        filesScanned.add(file);
    }
}
