using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationDisconnect : Message
    {
        private static readonly MessageType Type = MessageType.AUTHENTICATION_DISCONNECT;

        public AuthenticationDisconnect(string auditId) : base((int)Type, auditId)
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