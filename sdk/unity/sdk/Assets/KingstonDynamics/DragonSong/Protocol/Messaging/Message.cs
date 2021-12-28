using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Messaging
{
    public abstract class Message
    {
        private int TypeCode { get; }
        private string AuditId { get; }

        protected Message(int typeCode, string auditId)
        {
            this.TypeCode = typeCode;
            this.AuditId = auditId;
        }

        protected Message(Readinator reader)
        {
            TypeCode = reader.ReadInt();
            AuditId = reader.ReadAuditId();
        }

        public byte[] Build()
        {
            var b1 = Byteinator.IntToBytes(TypeCode);
            var b2 = Byteinator.StringToBytes(AuditId);

            return Concatinator.ConctatinateByteArrays(b1, b2);
        }
    }
}