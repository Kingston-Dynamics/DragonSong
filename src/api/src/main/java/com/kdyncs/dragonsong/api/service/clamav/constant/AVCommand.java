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

package com.kdyncs.dragonsong.api.service.clamav.constant;

import com.kdyncs.dragonsong.api.util.misc.Byteinator;

import java.nio.charset.StandardCharsets;

public enum AVCommand {
    
    PING("zPING\0"),
    SCAN_START("zINSTREAM\0"),
    SCAN_TERMINATE(new String(new byte[]{0, 0, 0, 0})); //Why?
    
    final byte[] value;
    
    AVCommand(String data) {
        value = Byteinator.stringToBytes(data, StandardCharsets.US_ASCII);
    }
    
    public byte[] getValue() {
        return this.value;
    }
}
