package com.kdyncs.dragonsong.protocol.networking;

import java.net.Socket;

/**
 * @author peter
 */
public interface NetworkManager {

    void handleError(NetworkError error);
    void handleInput(byte[] data);

    String getName();
    Socket getSocket();
    
}
