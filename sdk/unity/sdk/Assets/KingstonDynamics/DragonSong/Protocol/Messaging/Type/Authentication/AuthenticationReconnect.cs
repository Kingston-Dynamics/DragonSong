using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationReconnect : Message
    {
        private static readonly MessageType Type = MessageType.AUTHENTICATION_LOGIN;

        public AuthenticationReconnect(string auditId) : base((int)Type, auditId)
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