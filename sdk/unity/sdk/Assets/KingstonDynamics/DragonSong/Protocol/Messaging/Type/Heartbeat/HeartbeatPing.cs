using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Heartbeat
{
    public class HeartbeatPing : Message
    {
        public static readonly MessageType type = MessageType.HEARTBEAT_PING;

        public HeartbeatPing(string AuditId) : base((int)type, AuditId)
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