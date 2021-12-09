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

package com.kdyncs.dragonsong.protocol.test;

import com.kdyncs.dragonsong.protocol.utils.Encodeinator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncodeinatorTest {
    
    @Test
    public void hello() {
        
        String input = "Encode Me";
        
        byte[] encoded = Encodeinator.encodeinate(input.getBytes());
        byte[] decoded = Encodeinator.decodeinate(encoded);
        
        String output = new String(decoded);
        
        assertEquals(input, output);
        
    }
}
