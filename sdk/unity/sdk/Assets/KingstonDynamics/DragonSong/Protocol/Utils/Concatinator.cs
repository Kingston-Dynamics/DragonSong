namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public static class Concatinator
    {
        public static byte[] ConctatinateByteArrays(params byte[][] arrays)
        {
            var length = 0;

            foreach(byte[] array in arrays)
            {
                length += array.Length;
            }

            var combined = new byte[length];

            var position = 0;
            
            foreach (byte[] array in arrays)
            {
                array.CopyTo(combined, position);
                position += array.Length;
            }

            return combined;
        }
    }
}