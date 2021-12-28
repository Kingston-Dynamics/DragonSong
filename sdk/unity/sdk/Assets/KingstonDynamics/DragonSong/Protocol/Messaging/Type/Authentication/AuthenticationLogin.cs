using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationLogin : Message
    {
        private static readonly MessageType Type = MessageType.AUTHENTICATION_LOGIN;
        
        private readonly string _playerId;
        private readonly string _characterId;

        public AuthenticationLogin(string playerId, string characterId, string auditId) : base((int)Type, auditId)
        {
            _playerId = playerId;
            _characterId = characterId;
        }

        public AuthenticationLogin(string playerId, string characterId) : this(playerId, characterId,Keyinator.GenerateGUID())
        {
            // Empty
        }

        public AuthenticationLogin(Readinator reader) : base(reader)
        {
            _playerId = reader.ReadIntPrefixedString();
            _characterId = reader.ReadIntPrefixedString();
        }

        public AuthenticationLogin(byte[] data) : base(new Readinator(data)) 
        {
            // Empty
        }

        public new byte[] Build()
        {
            var b1 = Byteinator.StringToBytesPrefixed(_playerId);
            var b2 = Byteinator.StringToBytesPrefixed(_characterId);

            return Concatinator.ConctatinateByteArrays(base.Build(), b1, b2);
        }
    }
}