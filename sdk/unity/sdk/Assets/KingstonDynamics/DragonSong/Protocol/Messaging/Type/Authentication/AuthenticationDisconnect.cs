using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationDisconnect : Message
    {
        private static readonly MessageType type = MessageType.AUTHENTICATION_DISCONNECT;

        public AuthenticationDisconnect(string AuditId) : base((int)type, AuditId)
        {
            // Empty
        }

        public AuthenticationDisconnect() : this(Keyinator.GenerateGUID())
        {
            // Empty
        }

        public AuthenticationDisconnect(Readinator reader) : base(reader)
        {
            // Empty
        }
    }
}