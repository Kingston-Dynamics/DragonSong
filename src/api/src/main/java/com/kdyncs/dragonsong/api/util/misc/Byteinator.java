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

package com.kdyncs.dragonsong.api.util.misc;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class Byteinator {
    
    // Uninstantiable
    private Byteinator() {
        throw new AssertionError("Instantiating utility class.");
    }
    
    public static byte[] doubleToBytes(double value) {
        return ByteBuffer.allocate(8).putDouble(value).array();
    }
    
    public static double bytesToDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).asDoubleBuffer().get();
    }
    
    public static byte[] floatToBytes(float value) {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }
    
    public static float bytesToFloat(byte[] bytes) {
        return ByteBuffer.wrap(bytes).asFloatBuffer().get();
    }
    
    public static byte[] longToBytes(long value) {
        return ByteBuffer.allocate(8).putLong(value).array();
    }
    
    public static long bytesToLong(byte[] bytes) {
        return ByteBuffer.wrap(bytes).asLongBuffer().get();
    }
    
    public static byte[] intToBytes(int value) {
        return ByteBuffer.allocate(4).putInt(value).array();
    }
    
    public static int bytesToInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).asIntBuffer().get();
    }
    
    public static byte[] shortToBytes(short value) {
        return ByteBuffer.allocate(2).putShort(value).array();
    }
    
    public static short bytesToShort(byte[] bytes) {
        return ByteBuffer.wrap(bytes).asShortBuffer().get();
    }
    
    public static byte[] stringToBytes(String value) {
        return value.getBytes(StandardCharsets.UTF_8);
    }
    
    public static byte[] stringToBytes(String value, Charset charset) {
        return value.getBytes(charset);
    }
    
    public static String bytesToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
    
    public static String bytesToString(byte[] bytes, Charset charset) {
        return new String(bytes, charset);
    }
    
}
