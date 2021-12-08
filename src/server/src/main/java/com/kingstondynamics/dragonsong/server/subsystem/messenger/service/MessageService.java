package com.kingstondynamics.dragonsong.server.subsystem.messenger.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {


//  public void channelChat(Transmission packet, ClientConnection user) {
//
//      ////
//      // VALIDATE PROTOCOL STATE
//      ////
//      if (user.getApplication() == null || user.getPrivateID() == null) {
//          returnMessage(packet, user, ResponseCode.NOT_AUTHENTICATED);
//          return;
//      }
//
//      ////
//      // MESSAGE DATA
//      ////
//      ChannelChat payload = (ChannelChat) packet.getPayload();
//      String channelID = payload.getChannelName().getFieldData();
//      String chatMessage = payload.getChatMessage().getFieldData();
//      String privateID = user.getPrivateID();
//      String displayName = user.getDisplayName();
//
//      ////
//      // VALIDATE CHANNEL
//      ////
//      Channel target = user.getApplication().getChannels().findChannel(channelID);
//
//      if (target == null) {
//          returnMessage(packet, user, ResponseCode.NOT_SUBSCRIBED_TO_CHANNEL);
//          return;
//      }
//      if (!target.isRegistered(user.getPrivateID())) {
//          returnMessage(packet, user, ResponseCode.NOT_SUBSCRIBED_TO_CHANNEL);
//          return;
//      }
//
//
//      ////
//      // PROCESS DATA
//      ////
//      ArrayList<String> subscribers = target.getSubscribers();
//
//      for (String subscriber : subscribers) {
//          ClientConnection connection = connectionPool.getConnection(subscriber);
//
//          if (connection != null) {
//              sendInboundMessage(displayName, privateID, channelID, chatMessage, connection);
//          }
//      }
//
//      ////
//      // RETURN MESSAGE
//      ////
//      returnMessage(packet, user, ResponseCode.POST_SUCCESSFUL);
//
//  }

//  /**
//  * SEND RETURN CODE TO USER
//  *
//  * @param packet
//  * @param user
//  * @param code
//  */
// public void returnMessage(Transmission packet, ClientConnection user, ResponseCode code) {
//
//     //Re-Propagate the client provided Audit ID
//     //String auditID = packet.getAuditID();
//     Transmission message = new TransmissionFactory().buildReturnTransmission(code);
//     user.post(message);
// }

}
