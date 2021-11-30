using KingstonDynamics.DragonSong.Protocol.Messaging.Data;

namespace KingstonDynamics.DragonSong.Protocol.Utils
{
    public class Determinator
    {
        public static MessageType Determinate(byte[] data)
        {
            // Get Reader
            Readinator reader = new Readinator(data);
            
            // Determine Value 
            return (MessageType) reader.ReadInt();
        }
    }
}