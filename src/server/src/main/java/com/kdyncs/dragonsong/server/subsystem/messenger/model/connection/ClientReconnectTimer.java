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

import java.util.concurrent.ScheduledFuture;

/*
  @author peter
 */

@Component
@Scope("prototype")
public class ClientReconnectTimer implements Runnable{

    // Logging
    private static final Logger log = LogManager.getLogger();

    // Scheduling
    private final TaskScheduler scheduler;
    private ScheduledFuture<?> future;

    // Data
    private ClientConnection user;

    public ClientReconnectTimer(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(10000);

            // TODO:

        } catch (InterruptedException ex) {
            log.info("Reconnect Timer Interrupted; Client Reconnected");
        }
    }
}
