/*
 * Copyright (C) 2021 Kingston Dynamics Inc.
 *
 * This file is part of DragonSong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kdyncs.dragonsong.api.service.clamav;

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
