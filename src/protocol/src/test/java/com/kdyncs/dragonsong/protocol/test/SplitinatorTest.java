package com.kdyncs.dragonsong.protocol.test;

import com.kingstondynamics.dragonsong.protocol.utils.Byteinator;
import com.kingstondynamics.dragonsong.protocol.utils.Concatinator;
import com.kingstondynamics.dragonsong.protocol.utils.Splitinator;
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
