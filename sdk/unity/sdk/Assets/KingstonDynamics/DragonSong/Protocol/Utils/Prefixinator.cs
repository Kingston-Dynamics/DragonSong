namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public class Prefixinator
    {

        public static byte[] Prefix(byte[] data)
        {
            return Concatinator.ConctatinateByteArrays(Byteinator.IntToBytes(data.Length), data);
        }
    }
}