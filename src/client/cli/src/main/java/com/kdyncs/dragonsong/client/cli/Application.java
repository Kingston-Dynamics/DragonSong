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

package com.kdyncs.dragonsong.client.cli;

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
