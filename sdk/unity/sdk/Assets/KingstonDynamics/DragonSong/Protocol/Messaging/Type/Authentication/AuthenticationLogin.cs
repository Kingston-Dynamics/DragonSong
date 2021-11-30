using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationLogin : Message
    {
        private static readonly MessageType type = MessageType.AUTHENTICATION_LOGIN;
        
        private readonly string PlayerId;
        private readonly string CharacterId;

        public AuthenticationLogin(string playerId, string characterId, string auditId) : base((int)type, auditId)
        {
            PlayerId = playerId;
            CharacterId = characterId;
        }

        public AuthenticationLogin(string playerId, string characterId) : this(playerId, characterId,Keyinator.GenerateGUID())
        {
            // Empty
        }

        public AuthenticationLogin(Readinator reader) : base(reader)
        {
            PlayerId = reader.ReadIntPrefixedString();
            CharacterId = reader.ReadIntPrefixedString();
        }

        public AuthenticationLogin(byte[] data) : base(new Readinator(data)) 
        {
            // Empty
        }

        public new byte[] Build()
        {
            byte[] b1 = Byteinator.StringToBytesPrefixed(PlayerId);
            byte[] b2 = Byteinator.StringToBytesPrefixed(CharacterId);

            return Concatinator.ConctatinateByteArrays(base.Build(), b1, b2);
        }
    }
}