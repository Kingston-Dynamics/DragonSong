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

package com.kdyncs.dragonsong.database.schema.audit.model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity(name = "ConnectionLogModel")
@Table(name = "connection_log", schema = "ds_audit")
public class ConnectionLogModel {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column
    private String address;

    @Column
    private OffsetDateTime createTimestamp;
    
    @SuppressWarnings("unused")
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public OffsetDateTime getCreateTimestamp() {
        return createTimestamp;
    }
    
    public void setCreateTimestamp(OffsetDateTime created) {
        this.createTimestamp = created;
    }
}
