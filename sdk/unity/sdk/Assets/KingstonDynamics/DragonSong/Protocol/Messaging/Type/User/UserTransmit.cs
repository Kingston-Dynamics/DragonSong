using KingstonDynamics.DragonSong.Protocol.Messaging.Data;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging.Type.User
{
    public class UserTransmit : Message
    {
        public static readonly MessageType type = MessageType.USER_TRANSMIT;
        
        public readonly string identifier;
        public readonly string message;

        public UserTransmit(string id, string message, string auditId) :  base((int)type, auditId)
        {
            identifier = id;
            this.message = message;
        }

        public UserTransmit(Readinator reader) : base(reader)
        {
            identifier = reader.ReadIntPrefixedString();
            message = reader.ReadIntPrefixedString();
        }

        public UserTransmit(byte[] data) : this(new Readinator(data))
        {
            
        }

        public new byte[] Build()
        {
            var b1 = Byteinator.StringToBytesPrefixed(identifier);
            var b2 = Byteinator.StringToBytesPrefixed(message);
            return Concatinator.ConctatinateByteArrays(base.Build(), b1, b2);
        }
    }
}