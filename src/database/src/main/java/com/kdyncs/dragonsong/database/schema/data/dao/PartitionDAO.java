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

package com.kdyncs.dragonsong.database.schema.data.dao;

import com.kdyncs.dragonsong.common.util.SafeList;
import com.kdyncs.dragonsong.database.schema.data.repository.PartitionRepository;
import com.kdyncs.dragonsong.database.schema.data.model.PartitionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartitionDAO {
    
    private final PartitionRepository repository;
    
    @Autowired
    public PartitionDAO(PartitionRepository repository) {
        this.repository = repository;
    }
    
    public List<PartitionModel> getAllActivePartitions() {
        return SafeList.get(repository.findAllActiveApplications());
    }
}
