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

package com.kdyncs.dragonsong.server.proto;

import com.kdyncs.dragonsong.protocol.networking.NetworkManager;
import com.kdyncs.dragonsong.protocol.networking.NetworkReader;
import com.kdyncs.dragonsong.protocol.networking.NetworkWriter;
import com.kdyncs.dragonsong.server.subsystem.messenger.protocol.Command;
import com.kdyncs.dragonsong.server.subsystem.messenger.protocol.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
@Scope("prototype")
public class BaseConnection implements NetworkManager {

    // Spring Components
    private final Processor processor;

    // Store instance of Socket
    private Socket socket;

    // Input and Output
    private NetworkWriter writer;
    private NetworkReader reader;

    //
    private String externalID;

    // Connection Pool Reference
    private String connectionID;
    private String applicationKey;

    // Helpful Bits
    private String displayName;

    @Autowired
    public BaseConnection(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getConnectionID() {
        return connectionID;
    }

    public void setConnectionID(String connectionID) {
        this.connectionID = connectionID;
    }

    public String getExternalID() {
        return this.externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
    }

    public NetworkWriter getWriter() {
        return writer;
    }

    public void setWriter(NetworkWriter writer) {
        this.writer = writer;
    }

    public NetworkReader getReader() {
        return reader;
    }

    public void setReader(NetworkReader reader) {
        this.reader = reader;
    }

    public String getApplicationKey() {
        return applicationKey;
    }

    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    @Override
    public void handleInput(byte[] data) {
        processor.queueCommand(new Command(connectionID, data));
    }
}
