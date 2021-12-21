package com.kdyncs.dragonsong.server.subsystem.messenger.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledFuture;

@Component
@Scope("prototype")
public class HeartBeatMonitor implements Runnable{

    // Logging
    private static final Logger LOG = LogManager.getLogger();

    //
    private final TaskScheduler scheduler;
    private ScheduledFuture<?> future;

    @Autowired
    public HeartBeatMonitor(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void stop() {
        future.cancel(true);
    }

    @PostConstruct
    public void init() {
        future = scheduler.scheduleWithFixedDelay(this, Instant.now().plus(Duration.of(5, ChronoUnit.SECONDS)), Duration.of(5, ChronoUnit.SECONDS));
    }

    @Override
    public void run() {
        LOG.info("CHECKING HEARTBEAT!");
    }
}
