using KingstonDynamics.DragonSong.Protocol.Messaging.Data;

namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public static class Determinator
    {
        public static MessageType Determinate(byte[] data)
        {
            // Get Reader
            var reader = new Readinator(data);
            
            // Determine Value 
            return (MessageType) reader.ReadInt();
        }
    }
}