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

package com.kdyncs.dragonsong.database.schema.audit.dao;

import com.kdyncs.dragonsong.database.schema.audit.model.ChatLogModel;
import com.kdyncs.dragonsong.database.schema.audit.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatLogDAO {
    
    private final ChatLogRepository repository;
    
    @Autowired
    public ChatLogDAO(ChatLogRepository repository) {
        this.repository = repository;
    }
    
    public void save(ChatLogModel log) {
        repository.save(log);
    }
}
