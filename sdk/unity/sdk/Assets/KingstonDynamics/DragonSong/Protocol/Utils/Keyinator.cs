using System;
using System.Security.Cryptography;

namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public class Keyinator
    {

        public static string GenerateGUID()
        {
            return Guid.NewGuid().ToString();
        }
    }
}