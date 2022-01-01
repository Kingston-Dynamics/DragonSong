using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Heartbeat
{
    public class HeartbeatPong : Message
    {
        private const MessageType Type = MessageType.HEARTBEAT_PING;

        public HeartbeatPong(string auditId) : base((int)Type, auditId)
        {
            // Empty
        }

        public HeartbeatPong() : this(Keyinator.GenerateGUID())
        {
            // Empty
        }

        public HeartbeatPong(Readinator reader) : base(reader)
        {
            // Empty
        }
    }
}