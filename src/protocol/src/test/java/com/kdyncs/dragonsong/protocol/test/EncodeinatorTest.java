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
