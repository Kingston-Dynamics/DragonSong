using System;

namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public class Readinator
    {
        private int _position;
        private readonly byte[] _data;

        public Readinator(byte[] data)
        {
            _data = data;
            _position = 0;
        }

        private void Advance()
        {
            _position++;
        }

        private void Advance(int distance)
        {
            _position += distance;
        }

        public void Reset()
        {
            _position = 0;
        }

        public byte ReadByte()
        {
            var temp = _data[_position];
            Advance();
            return temp;
        }
        
        public byte[] ReadBytes(int length)
        {
            var temp = new byte[length];
            Array.Copy(_data, _position, temp, 0, length);

            Advance(length);

            return temp;
        }
        
        public short ReadShort()
        {
            var b1 = ReadByte();
            var b2 = ReadByte();

            return (short)((b1 << 8) | (b2 & 0xFF));
        }
        
        public int ReadInt()
        {
            var b1 = ReadByte();
            var b2 = ReadByte();
            var b3 = ReadByte();
            var b4 = ReadByte();

            return b1 << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8 | (b4 & 0xFF);
        }
        
        public string ReadString(int length) 
        {
            var temp = new byte[length];
            Array.Copy(_data, _position, temp, 0, length);

            Advance(length);

            return Byteinator.BytesToString(temp);
        }

        public string ReadIntPrefixedString()
        {
            // Length prefixes the String as an integer
            var length = ReadInt();

            // Get String by Length
            return ReadString(length);
        }
        
        public string ReadAuditId()
        {
            return ReadString(36);
        }
    }
}