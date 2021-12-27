/*
 * Copyright (C) 2021 Kingston Dynamics Inc.
 *
 * This file is part of DragonSong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kdyncs.dragonsong.client.common;

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
public class AutoHeartBeat implements Runnable{

    // Logging
    private static final Logger LOG = LogManager.getLogger();

    //
    private final TaskScheduler scheduler;
    private final DragonSong connection;

    //
    private ScheduledFuture<?> future;

    @Autowired
    public AutoHeartBeat(TaskScheduler scheduler, DragonSong connection) {
        this.scheduler = scheduler;
        this.connection = connection;
    }

    @PostConstruct
    public void init() {
        future = scheduler.scheduleWithFixedDelay(this, Instant.now().plus(Duration.of(3, ChronoUnit.SECONDS)), Duration.of(5, ChronoUnit.SECONDS));
    }

    public void start() {
        // future = scheduler.scheduleWithFixedDelay(this, Instant.now().plus(Duration.of(3, ChronoUnit.SECONDS)), Duration.of(5, ChronoUnit.SECONDS));
    }

    public void stop() {
        future.cancel(true);
    }

    @Override
    public void run() {
        LOG.info("SENDING HEARTBEAT!");

        // Fire off Ping
        connection.sendPing();
    }
}
