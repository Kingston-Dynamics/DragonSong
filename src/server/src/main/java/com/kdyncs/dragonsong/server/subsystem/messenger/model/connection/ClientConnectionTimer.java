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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ClientConnectionTimer implements Runnable {

    private final AuthenticationService authentication;

    private static final Logger LOG = LogManager.getLogger();
    private static final long DELAY = 10000;
    private ClientConnection user;
    private Thread timer;



    @Autowired
    public ClientConnectionTimer(AuthenticationService authentication) {

        // Add Services
        this.authentication = authentication;

        // Create Thread Instance
        timer = new Thread(this);

        // Mark this thread as a Daemon
        timer.setDaemon(true);
    }

    @Override
    public void run() {

        try {

            // This timer needs to
            Thread.sleep(DELAY);

            if (user.getApplicationKey() == null) {

            }

        } catch (InterruptedException ex) {

        }

//        if (user.getApplication() == null) {
//            disconnectMessage();
//            user.kill();
//        }
    }

    public void setUser(ClientConnection user) {
        this.user = user;
    }

    public void start() {
        if (timer.isAlive()) {
            LOG.warn("Timer Already Started");
            return;
        }

        timer.start();
    }

//    private void disconnectMessage() {
//        Transmission message = new TransmissionFactory().buildReturnTransmission(ResponseCode.AUTHENTICATION_TIME_EXCEEDED);
//        user.post(message);
//    }
}
