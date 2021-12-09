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

package com.kdyncs.dragonsong.database.schema.software.model;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "ApplicationModel")
@Table(name = "applications", schema = "ds_software")
public class ApplicationModel {

    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Integer id;
    
    @Column (name = "application_name")
    private String name;
    
    @Column(name = "is_deployed")
    private boolean deployed;

    @GeneratedValue
    @Column (name = "application_key")
    private UUID key;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String softwarename) {
        this.name = softwarename;
    }
    
    public boolean isDeployed() {
        return deployed;
    }
    
    public void setDeployed(boolean deployed) {
        this.deployed = deployed;
    }
    
    public UUID getKey() {
        return key;
    }
    
    public void setKey(UUID apikey) {
        this.key = apikey;
    }
}
