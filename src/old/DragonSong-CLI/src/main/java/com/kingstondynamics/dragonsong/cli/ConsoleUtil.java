package com.kingstondynamics.dragonsong.cli;

public class ConsoleUtil {
    
    private ConsoleUtil() {
        throw new UnsupportedOperationException();
    }
    
    public static void clear() {
        
        if (OSValidator.isUnix()) {
            clearUnix();
            return;
        }
        
        if (OSValidator.isWindows()) {
            clearWindows();
        }
        
        //System.out.println("Unsupported OS");
        
    }
    
    private static void clearUnix() {
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
    }
    
    private static void clearWindows() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
