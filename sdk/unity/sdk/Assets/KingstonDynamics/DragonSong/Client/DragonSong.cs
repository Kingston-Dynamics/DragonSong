//
// This class provides a high level implementation of the DragonSong Services
//

using System;
using System.Collections;
using KingstonDynamics.DragonSong.Protocol.Messaging;
using KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication;
using KingstonDynamics.DragonSong.Protocol.Messaging.Type.Heartbeat;

namespace KingstonDynamics.DragonSong.Client
{
    public class DragonSong
    {
        //Data Connection
        private readonly DragonNet _network;
        
        //Packet Queue
        private readonly Queue _packets;

        public DragonSong(DragonConfig config)
        {
            _network = new DragonNet(config);
            _packets = new Queue();

            if (config.AutoConnect)
            {
                _network.Connect();
            }
        }

        public void Connect()
        {
            _network.Connect();
        }

        public void Disconnect()
        {
            _network.Disconnect();
        }

        public void SendPing()
        {
            var message = new HeartbeatPing();
            Write(message);
        }
        
        public void SendAuthHandshake()
        {
            var message = new AuthenticationLogin();
            
            throw new NotImplementedException();
        }

        public void JoinChannel(string channelID)
        {
            throw new NotImplementedException();
        }

        public void PublishMessage(string channelID, string message)
        {
            throw new NotImplementedException();
        }

        public void SendPrivateMessage(string recipientID, string message)
        {
            throw new NotImplementedException();
        }

        private void Write(Message message)
        {
            _network.Write(message);
        }
        
        //
        // HANDLE RECEIVED PACKETS
        //
        public void Process()
        {
            if (_packets.Count != 0)
            {
                _packets.Dequeue();
            }
        }
    }
}