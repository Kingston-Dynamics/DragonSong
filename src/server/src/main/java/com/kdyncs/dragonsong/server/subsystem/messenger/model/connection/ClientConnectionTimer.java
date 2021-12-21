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

import com.kdyncs.dragonsong.server.subsystem.messenger.service.AuthenticationService;
import org.apache.commons.logging.Log;
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

@Component
@Scope("prototype")
public class ClientConnectionTimer implements Runnable {

    private final AuthenticationService authentication;
    private final TaskScheduler scheduler;

    private static final Logger LOG = LogManager.getLogger();
    private static final long DELAY = 10;
    private ClientConnection user;

    @Autowired
    public ClientConnectionTimer(AuthenticationService authentication, TaskScheduler scheduler) {

        // Add Services
        this.authentication = authentication;
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() {
        scheduler.schedule(this, Instant.now().plus(Duration.of(DELAY, ChronoUnit.SECONDS)));
    }

    @Override
    public void run() {

        /*
         * Sanity check user.
         *
         * The possibility exists someone might create this task without setting a target user. Ideally this wouldn't
         * happen but we want to rule out that possibility.
         */
        if (user == null) {
            LOG.error("User wasn't set on Task");
            return;
        }

        // TODO: Double check that user is still connected

        if (!authentication.isAuthenticated(user)) {

            LOG.info("User {} failed authentication timeout", user.getConnectionID());
            // TODO: Send Disconnect Message
            // TODO: Disconnect Client
        } else {
            LOG.info("User {} passed authentication timeout", user.getConnectionID());
        }
    }

    public void setUser(ClientConnection user) {
        this.user = user;
    }
}
