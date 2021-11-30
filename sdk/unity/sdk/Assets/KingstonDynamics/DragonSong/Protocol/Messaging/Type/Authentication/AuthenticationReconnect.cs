using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationReconnect : Message
    {
        private static readonly MessageType type = MessageType.AUTHENTICATION_LOGIN;

        public AuthenticationReconnect(string AuditId) : base((int)type, AuditId)
        {
            // Empty
        }

        public AuthenticationReconnect() : this(Keyinator.GenerateGUID())
        {
            // Empty
        }

        public AuthenticationReconnect(Readinator reader) : base(reader)
        {
            // Empty
        }
    }
}