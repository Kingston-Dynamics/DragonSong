//
// This class presents a configurable host class for the user
// All data needed in order to properly connect to the DragonSong
// Server should be stored within this class. 
//

namespace KingstonDynamics.DragonSong.Client
{
    public class DragonConfig
    {
        public string Hostname { get; }
        public int Port { get; }
        public bool AutoConnect { get; }
        public string PlayerId { get; }
        public string CharacterId { get; }

        public DragonConfig(string hostname, int port, bool autoConnect, string playerId, string characterId)
        {
            Hostname = hostname;
            Port = port;
            AutoConnect = autoConnect;
            PlayerId = playerId;
            CharacterId = characterId;
        }
    }
}
