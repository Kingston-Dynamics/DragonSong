using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Heartbeat
{
    public class HeartbeatPong : Message
    {
        private static readonly MessageType type = MessageType.HEARTBEAT_PING;

        public HeartbeatPong(string AuditId) : base((int)type, AuditId)
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