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

package com.kdyncs.dragonsong.server.subsystem.messenger.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Client Reconnect Timer
 *
 * If a client disconnects in a manner which is unclean, which is to say if they didn't properly disconnect such as if
 * an application crashed or their heartbeat stops, we will afford them a grace period. During this grace period messages
 * they would have received will be cached to be sent later. If they reconnect during this grace period they will resume
 * as normal and all missed messages will be transmitted back. If they do not reconnect within the grace period they
 * will be fully disconnect and any pending messages will be dropped.
 */
@Component
@Scope("prototype")
public class ClientReconnectTimer implements Runnable{

    // Logging
    private static final Logger log = LogManager.getLogger();

    // Scheduling
    private final TaskScheduler scheduler;

    // Reconnect Timeout
    private static final long DELAY = 10;

    // Data
    private ClientConnection user;

    public ClientReconnectTimer(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() {
        scheduler.schedule(this, Instant.now().plus(Duration.of(DELAY, ChronoUnit.SECONDS)));
    }

    @Override
    public void run() {
        log.info("CHECKING CONNECTION STATUS OF USER: {}", user.getConnectionID());

        // TODO: Determine if user has reconnected or not

        // TODO: Cleanup user if they haven't reconnected
    }

    public void setUser(ClientConnection user) {
        this.user = user;
    }
}