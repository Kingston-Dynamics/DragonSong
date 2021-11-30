package com.kingstondynamics.dragonsong.server.subsystem.messenger.model.connection;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author peter
// */
//@Component
//@Scope("prototype")
//public class ClientConnectionTimer implements Runnable {
//
//    private static final Logger LOG = LogManager.getLogger();
//    
//    private final long connectionTimer = 10000;
//
//    private ClientConnection user;
//
//    private Thread timer;
//
//    @Override
//    public void run() {
//
//        try {
//            Thread.sleep(connectionTimer);
//        } catch (InterruptedException ex) {
//
//        }
//
//        if (user.getApplication() == null) {
////            disconnectMessage();
//            user.kill();
//        }
//    }
//
//    public void setUser(ClientConnection user) {
//        this.user = user;
//    }
//
//    public void start() {
//        if (timer.isAlive()) {
//            LOG.warn("Timer Already Started");
//            return;
//        }
//
//        timer.start();
//    }
//
////    private void disconnectMessage() {
////        Transmission message = new TransmissionFactory().buildReturnTransmission(ResponseCode.AUTHENTICATION_TIME_EXCEEDED);
////        user.post(message);
////    }
//    
//    public ClientConnectionTimer() {
//        timer = new Thread(this);
//    }
//}
