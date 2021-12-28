using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging
{
    public abstract class Message
    {
        private int TypeCode { get; }
        private string AuditId { get; }

        protected Message(int TypeCode, string AuditId)
        {
            this.TypeCode = TypeCode;
            this.AuditId = AuditId;
        }

        protected Message(Readinator reader)
        {
            TypeCode = reader.ReadInt();
            AuditId = reader.ReadAuditId();
        }

        public byte[] Build()
        {
            byte[] b1 = Byteinator.IntToBytes(TypeCode);
            byte[] b2 = Byteinator.StringToBytes(AuditId);

            return Concatinator.ConctatinateByteArrays(b1, b2);
        }
    }
}