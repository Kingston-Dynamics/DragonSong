//
// This class provides a high level implementation of the DragonSong Services
//

using System;
using System.Collections;
using KingstonDynamics.DragonSong.Client.Configuration;
using KingstonDynamics.DragonSong.Protocol.Messaging;
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
            HeartbeatPing message = new HeartbeatPing();
            write(message);
        }
        
        public void SendAuthHandshake()
        {
            throw new NotImplementedException();
        }

        public void JoinChannel(string ChannelID)
        {
            throw new NotImplementedException();
        }

        public void PublishMessage(string ChannelID, string message)
        {
            throw new NotImplementedException();
        }

        public void SendPrivateMessage(string RecipientID, string message)
        {
            throw new NotImplementedException();
        }

        private void write(Message message)
        {
            _network.Write(message);
        }
        
        //
        // HANDLE RECEIVED PACKETS
        //
        public void Process()
        {
            // _packets.Dequeue();
        }
    }
}