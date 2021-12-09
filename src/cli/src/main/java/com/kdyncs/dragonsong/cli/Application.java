package com.kdyncs.dragonsong.cli;

import java.util.Scanner;

class Application {
    
    
    public static void main(String[] args) {
        Scanner command = new Scanner(System.in);
        CommandParser parser = new CommandParser();
        
        while (true) {
            
            // Print CLI Stuff
            // System.out.print("DragonSong > ");
            
            // Get Input from User
            String input = command.nextLine();
            
            // Forward input to a command processor
            parser.handleCommand(input);
            
            // Quitting application
            if (input.equals("/q")) {
                break;
            }
            
        }
        
        //System.out.println("Shutting Down");
        
        command.close();
    }
}
