using System;

namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public class Encodeinator
    {
        public static byte[] Encodeinate(byte[] bytes)
        {
            return Byteinator.StringToBytes(Convert.ToBase64String(bytes));
        }

        public static byte[] Decodeinate(byte[] bytes)
        {
            return Convert.FromBase64String(Byteinator.BytesToString(bytes));
        }
    }
}