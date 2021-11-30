package com.kingstondynamics.dragonsong.protocol.message.type.channel;

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
