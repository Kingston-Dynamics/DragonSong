using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationLogout : Message
    {
        private static readonly MessageType type = MessageType.AUTHENTICATION_LOGOUT;

        public AuthenticationLogout(string AuditId) : base((int)type, AuditId)
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