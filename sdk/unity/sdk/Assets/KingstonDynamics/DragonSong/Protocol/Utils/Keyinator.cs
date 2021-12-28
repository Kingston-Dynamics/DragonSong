using System;

namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public static class Keyinator
    {

        // ReSharper disable once InconsistentNaming
        public static string GenerateGUID()
        {
            return Guid.NewGuid().ToString();
        }
    }
}