using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication
{
    public class AuthenticationLogin : Message
    {
        private const MessageType Type = MessageType.AUTHENTICATION_LOGIN;

        private readonly string _applicationKey;
        private readonly string _uniqueID;
        private readonly string _displayName;

        public AuthenticationLogin(string applicationKey, string uniqueID, string displayName, string auditId) : base((int)Type, auditId)
        {
            _applicationKey = applicationKey;
            _uniqueID = uniqueID;
            _displayName = displayName;
        }

        public AuthenticationLogin(string applicationKey, string uniqueID, string displayName) : this(applicationKey, uniqueID, displayName, Keyinator.GenerateGUID())
        {
            // Empty
        }

        public AuthenticationLogin(Readinator reader) : base(reader)
        {
            _applicationKey = reader.ReadIntPrefixedString();
            _uniqueID = reader.ReadIntPrefixedString();
            _displayName = reader.ReadIntPrefixedString();
        }

        public AuthenticationLogin(byte[] data) : base(new Readinator(data)) 
        {
            // Empty
        }

        public new byte[] Build()
        {
            var b1 = Byteinator.StringToBytesPrefixed(_applicationKey);
            var b2 = Byteinator.StringToBytesPrefixed(_uniqueID);
            var b3 = Byteinator.StringToBytesPrefixed(_displayName);

            return Concatinator.ConctatinateByteArrays(base.Build(), b1, b2, b3);
        }
    }
}