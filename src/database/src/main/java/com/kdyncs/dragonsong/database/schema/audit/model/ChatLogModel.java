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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author peter
 */

@Entity(name = "ChatLogModel")
@Table(name = "chatlog", schema = "ds_audit")
public class ChatLogModel implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 7272592350952536722L;
    
    @Id
    private UUID id;
    
    @Column
    private String content;
    
    @Column
    private OffsetDateTime createtimestamp;
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public OffsetDateTime getCreatetimestamp() {
        return createtimestamp;
    }
    
    public void setCreatetimestamp(OffsetDateTime createtimestamp) {
        this.createtimestamp = createtimestamp;
    }
}
