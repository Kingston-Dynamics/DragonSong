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

package com.kdyncs.dragonsong.server.subsystem.admin.model.pools;

import com.kdyncs.dragonsong.server.subsystem.admin.model.connection.AdminConnection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AdminPool {
    
    public void registerConnection(AdminConnection connection) {
        throw new UnsupportedOperationException("Unimplemented");
    }
    
    public void deregisterConnection(String connectionID) {
        throw new UnsupportedOperationException("Unimplemented");
    }
    
    public ArrayList<AdminConnection> getConnections() {
        throw new UnsupportedOperationException("Unimplemented");
    }
    
    public AdminConnection getConnection(String connectionID) {
        throw new UnsupportedOperationException("Unimplemented");
    }
}
