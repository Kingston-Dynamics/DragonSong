package com.kdyncs.dragonsong.server.subsystem;

public interface ConnectionListener extends Runnable {
    
    @Override
    void run();
    
    void start();
    
    void stop();
    
}
