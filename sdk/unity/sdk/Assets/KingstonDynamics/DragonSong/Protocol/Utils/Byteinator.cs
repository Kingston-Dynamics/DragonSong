using System;
using System.Text;

namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public class Byteinator
    {
        public static byte[] IntToBytes(int value)
        {
            return ReverseBytes(BitConverter.GetBytes(value));
        }
        
        public static byte[] StringToBytes(string value)
        {
            return Encoding.UTF8.GetBytes(value);
        }

        public static byte[] StringToBytesPrefixed(string value)
        {
            return Prefixinator.Prefix(StringToBytes(value));
        }

        public static string BytesToString(byte[] bytes)
        {
            return Encoding.UTF8.GetString(bytes);
        }

        public static int BytesToInt(byte[] bytes)
        {
            return BitConverter.ToInt32(ReverseBytes(bytes), 0);
        }

        private static byte[] ReverseBytes(byte[] bytes)
        {

            // Reverse byte order if necessary
            if (BitConverter.IsLittleEndian)
            {
                Array.Reverse(bytes, 0, bytes.Length);
            }
            
            return bytes;
        }

        public static String BytesToHex(byte[] bytes)
        {
            return BitConverter.ToString(bytes).Replace("-", "");
        }
    }
}

