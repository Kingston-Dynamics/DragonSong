using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationLogout : Message
    {
        private const MessageType Type = MessageType.AUTHENTICATION_LOGOUT;

        public AuthenticationLogout(string auditId) : base((int)Type, auditId)
        {
            // Empty
        }

        public AuthenticationLogout() : this(Keyinator.GenerateGUID())
        {
            // Empty
        }

        public AuthenticationLogout(Readinator reader) : base(reader)
        {
            // Empty
        }
    }
}