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

import com.kdyncs.dragonsong.client.common.DragonConfig;
import com.kdyncs.dragonsong.client.common.DragonSong;
import com.kdyncs.dragonsong.protocol.utils.Keyinator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommandParser {
    
    private static final int PORT = 27888;
    private static final Logger log = LoggerFactory.getLogger(CommandParser.class);
    
    private DragonSong client;
    
    public void handleCommand(String command) {
        
        String[] commands = splitCommands(command);
        
        switch (commands[0]) {
            case "/a":
                authenticate(commands);
                break;
            case "/c":
                connect(commands);
                break;
            case "/j":
                join(commands);
                break;
            case "/w":
                whisper(commands);
                break;
            case "/t":
                talk(commands);
                break;
            case "/q":
            case "/d":
                disconnect(commands);
                break;
            case "/p":
                ping(commands);
                break;
            case "/clear":
                ConsoleUtil.clear();
                break;
            default:
                break;
        }
    }
    
    private String[] splitCommands(String command) {
        return command.split(" ");
    }
    
    public void authenticate(String[] commands) {
        client.sendAuthHandshake();
    }
    
    // "/c -h 127.0.0.1 -p 27888"
    public void connect(String[] commands) {
        
        String host = "127.0.0.1";
        
        String privateID = Keyinator.generateGUID();
        log.info("Private ID is {}", privateID);
        
        DragonConfig config = new DragonConfig(host, PORT, "ac77bcf1-4b07-4477-8b90-c515a7f2e752", "DragonSong-DEV", "NULL", privateID, "Steve");
        client = new DragonSong(config);
        client.connect();
    }
    
    // "/d"
    public void disconnect(String[] commands) {
        client.disconnect();
    }
    
    // "/p"
    public void ping(String[] commands) {
        client.sendPing();
    }
    
    // "/j"
    public void join(String[] commands) {
        client.joinChannel("test");
    }
    
    // "/t"
    public void talk(String[] commands) {
        client.publishMessage("test", "Good Day Steve");
    }
    
    // "/w {GUID}"
    public void whisper(String[] commands) {
        client.directMessage(commands[1], "Direct Message Test");
    }
}
