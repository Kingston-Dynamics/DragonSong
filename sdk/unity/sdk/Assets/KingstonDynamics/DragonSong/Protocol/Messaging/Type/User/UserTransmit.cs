using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.User
{
    public class UserTransmit : Message
    {
        private const MessageType Type = MessageType.USER_TRANSMIT;

        private readonly string Identifier;
        private readonly string Message;

        public UserTransmit(string id, string message, string auditId) :  base((int)Type, auditId)
        {
            Identifier = id;
            Message = message;
        }

        public UserTransmit(Readinator reader) : base(reader)
        {
            Identifier = reader.ReadIntPrefixedString();
            Message = reader.ReadIntPrefixedString();
        }

        public UserTransmit(byte[] data) : this(new Readinator(data))
        {
            
        }

        public new byte[] Build()
        {
            var b1 = Byteinator.StringToBytesPrefixed(Identifier);
            var b2 = Byteinator.StringToBytesPrefixed(Message);
            return Concatinator.ConctatinateByteArrays(base.Build(), b1, b2);
        }
    }
}