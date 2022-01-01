using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Heartbeat
{
    public class HeartbeatPing : Message
    {
        private const MessageType Type = MessageType.HEARTBEAT_PING;

        public HeartbeatPing(string auditId) : base((int)Type, auditId)
        {
            // Empty
        }

        public HeartbeatPing() : this(Keyinator.GenerateGUID())
        {
            // Empty
        }

        public HeartbeatPing(Readinator reader) : base(reader)
        {
            // Empty
        }
    }
}