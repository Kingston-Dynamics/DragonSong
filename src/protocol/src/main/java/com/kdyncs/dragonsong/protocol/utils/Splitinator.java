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

package com.kdyncs.dragonsong.protocol.utils;

import java.util.Arrays;

public class Splitinator {
    
    /**
     * Private Constructor for Utility Class
     */
    private Splitinator() {
        throw new AssertionError();
    }
    
    public static byte[][] splitByteArray(byte[] data, int splits) {
        
        // This is Illegal
        if (splits < 2) {
            throw new IllegalArgumentException();
        }
        
        // This is also Illegal
        if (data.length < splits) {
            throw new IllegalArgumentException();
        }
        
        // Prepare Our Data
        byte[][] splitData = new byte[splits][];
        
        // Determine Chunk Sizes
        int size = (int) Math.ceil(data.length / (float) splits);
        
        // Split Data
        for (int i = 0; i < splits; i++) {
            
            // Find start of Range
            int start = i * size;
            
            // Find End of Range inclusive to the Data Size
            int end = Math.min(data.length, i * size + size);
            
            // Split off that Data
            splitData[i] = Arrays.copyOfRange(data, start, end);
        }
        
        return splitData;
    }
}
