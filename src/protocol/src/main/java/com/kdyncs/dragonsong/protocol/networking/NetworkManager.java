package com.kdyncs.dragonsong.protocol.networking;

import java.net.Socket;

/**
 * @author peter
 */
public interface NetworkManager {
    
    void handleInput(byte[] data);
    
    Socket getSocket();
    
}