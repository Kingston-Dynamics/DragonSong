package com.kdyncs.dragonsong.server.subsystem.messenger.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
@Scope("Prototype")
public class AuthenticationTimer implements Runnable{

    private static final int DELAY = 10;

    // Task Thread
    private Thread task;

    @Autowired
    public AuthenticationTimer() {
        // Do Nothing

    }

    @PostConstruct
    public void init() {

        //
        this.task = new Thread(this);

        // Mark thread as Daaemon
        this.task.setDaemon(true);


    }

    public void start() {

    }

    @Override
    public void run() {

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(DELAY));
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

    }
}
