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

import com.kdyncs.dragonsong.protocol.utils.Byteinator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("MagicNumber")
public class ByteinatorTest {
    
    @Test
    public void TestDouble() {
        double initial = 999.99;
        byte[] encoded = Byteinator.doubleToBytes(initial);
        double decoded = Byteinator.bytesToDouble(encoded);
        assertEquals(initial, decoded, 0.0);
    }
    
    @Test
    public void TestFloat() {
        float initial = 999.99f;
        byte[] encoded = Byteinator.floatToBytes(initial);
        float decoded = Byteinator.bytesToFloat(encoded);
        assertEquals(initial, decoded, 0.0);
    }
    
    @Test
    public void TestLong() {
        long initial = 999;
        byte[] encoded = Byteinator.longToBytes(initial);
        long decoded = Byteinator.bytesToLong(encoded);
        assertEquals(initial, decoded);
    }
    
    @Test
    public void TestInt() {
        int initial = 800;
        byte[] encoded = Byteinator.intToBytes(initial);
        int decoded = Byteinator.bytesToInt(encoded);
        assertEquals(initial, decoded);
        
    }
    
    @Test
    public void TestShort() {
        short initial = 500;
        byte[] encoded = Byteinator.shortToBytes(initial);
        short decoded = Byteinator.bytesToShort(encoded);
        assertEquals(initial, decoded);
    }
    
    @Test
    public void TestString() {
        String initial = "Testing String Please Ignore";
        byte[] encoded = Byteinator.stringToBytes(initial);
        String decoded = Byteinator.bytesToString(encoded);
        assertEquals(initial, decoded);
    }
    
}
