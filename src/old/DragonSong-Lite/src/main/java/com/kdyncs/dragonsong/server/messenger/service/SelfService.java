package com.kdyncs.dragonsong.server.messenger.service;

import com.kdyncs.dragonsong.server.messenger.protocol.Command;
import org.springframework.stereotype.Service;

@Service
public class SelfService {
    
    public void transmit(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void getUptime(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void whatami(Command command) {
        throw new UnsupportedOperationException();
    }
    
    public void whoami(Command command) {
        throw new UnsupportedOperationException();
    }
}
