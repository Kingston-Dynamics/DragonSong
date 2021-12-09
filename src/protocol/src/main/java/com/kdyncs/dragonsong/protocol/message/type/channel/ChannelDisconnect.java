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

package com.kdyncs.dragonsong.protocol.message.type.channel;

//import io.dragonsong.protocol.message.data.TransmissionType;
//import io.dragonsong.protocol.message.data.fields.StringField;
//import io.dragonsong.protocol.message.Header;
//import io.dragonsong.protocol.message.Payload;
//import io.dragonsong.protocol.message.transmission.Transmission;
//import io.dragonsong.protocol.utils.Keyinator;
//
//public class ChannelDisconnect extends Transmission {
//
//    private final static TransmissionType type = TransmissionType.CHANNEL_CONNECT;
//
//    private StringField channelId;
//
//    public ChannelDisconnect(String id, String auditId) {
//
//        // Generate Payload
//        Payload payload = new Payload();
//        channelId = new StringField(id);
//        payload.addFields(channelId);
//
//        // Generate Header
//        Header header = new Header(type, auditId, payload.length());
//
//        // Populate Data
//        this.setHeader(header);
//        this.setPayload(payload);
//    }
//
//    public ChannelDisconnect(String id) {
//        this(id, Keyinator.generateGUID());
//    }
//
//}
