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
import com.kdyncs.dragonsong.protocol.utils.Concatinator;
import com.kdyncs.dragonsong.protocol.utils.Splitinator;
import org.junit.Assert;
import org.junit.Test;

public class SplitinatorTest {
    
    @Test
    public void testSplitinator() {
        
        String v1 = "Down with the system";
        
        byte[] b1 = Byteinator.stringToBytes(v1);
        
        byte[][] d1 = Splitinator.splitByteArray(b1, 3);
        
        byte[] c2 = Concatinator.ConcatinateByteArrays(d1);
        
        String r1 = Byteinator.bytesToString(c2);
        
        Assert.assertEquals(v1, r1);
    }
}
