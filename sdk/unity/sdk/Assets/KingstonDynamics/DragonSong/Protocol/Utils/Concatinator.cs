namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public class Concatinator
    {
        public static byte[] ConctatinateByteArrays(params byte[][] arrays)
        {
            int length = 0;

            foreach(byte[] array in arrays)
            {
                length += array.Length;
            }

            byte[] combined = new byte[length];

            int position = 0;
            foreach (byte[] array in arrays)
            {
                array.CopyTo(combined, position);
                position += array.Length;
            }

            return combined;
        }
    }
}