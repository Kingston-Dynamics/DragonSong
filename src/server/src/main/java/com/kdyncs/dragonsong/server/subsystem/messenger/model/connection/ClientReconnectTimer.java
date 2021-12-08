package com.kdyncs.dragonsong.server.subsystem.messenger.model.connection;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;

/*
  @author peter
 */

//@Component
//@Scope("prototype")
//public class ClientReconnectTimer implements Runnable{
//    
//    private static final Logger log = LogManager.getLogger();
//    
//    private final long connectionTimer = 10000;
//
//    private ClientConnection user;
//
//    public ClientReconnectTimer(ClientConnection user) {
//        this.user = user;
//    }
//
//    @Override
//    public void run() {
//
//        try {
//            Thread.sleep(connectionTimer);
//            user.kill();
//        } catch (InterruptedException ex) {
//            log.info("Reconnect Timer Interrupted; Client Reconnected");
//        }
//    }    
//}
