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
