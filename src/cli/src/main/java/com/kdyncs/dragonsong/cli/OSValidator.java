package com.kdyncs.dragonsong.cli;

public class OSValidator {
    
    // Cache OS Name
    private static final String OS = System.getProperty("os.name").toLowerCase();
    
    private OSValidator() {
        throw new UnsupportedOperationException();
    }
    
    public static boolean isWindows() {
        return (OS.contains("win"));
    }
    
    public static boolean isMac() {
        return (OS.contains("mac"));
    }
    
    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
    }
    
    public static boolean isSolaris() {
        return (OS.contains("sunos"));
    }
}
