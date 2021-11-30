using System;

namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public class Readinator
    {
        private int Position;
        private readonly byte[] Data;

        public Readinator(byte[] Data)
        {
            this.Data = Data;
            Position = 0;
        }

        private void Advance()
        {
            Position++;
        }

        private void Advance(int distance)
        {
            Position += distance;
        }

        public void Reset()
        {
            Position = 0;
        }

        public byte ReadByte()
        {
            byte temp = Data[Position];
            Advance();
            return temp;
        }
        
        public byte[] ReadBytes(int length)
        {
            byte[] temp = new byte[length];
            Array.Copy(Data, Position, temp, 0, length);

            Advance(length);

            return temp;
        }
        
        public short ReadShort()
        {
            byte b1 = ReadByte();
            byte b2 = ReadByte();

            return (short)((b1 << 8) | (b2 & 0xFF));
        }
        
        public int ReadInt()
        {
            byte b1 = ReadByte();
            byte b2 = ReadByte();
            byte b3 = ReadByte();
            byte b4 = ReadByte();

            return b1 << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8 | (b4 & 0xFF);
        }
        
        public string ReadString(int length) 
        {
            byte[] temp = new byte[length];
            Array.Copy(Data, Position, temp, 0, length);

            Advance(length);

            return Byteinator.BytesToString(temp);
        }

        public string ReadIntPrefixedString()
        {
            // Length prefixes the String as an integer
            int length = ReadInt();

            // Get String by Length
            return ReadString(length);
        }
        
        public string readAuditId()
        {
            return ReadString(36);
        }
    }
}