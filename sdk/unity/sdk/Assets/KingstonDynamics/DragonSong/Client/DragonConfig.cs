//
// This class presents a configurable host class for the user
// All data needed in order to properly connect to the DragonSong
// Server should be stored within this class. 
//

namespace KingstonDynamics.DragonSong.Client.Configuration
{
    public class DragonConfig
    {
        public string Hostname => _hostname;
        public int Port => _port;
        public bool AutoConnect => _autoConnect;
        public string PlayerId => _playerId;
        public string CharacterId => _characterId;

        //Direct Connection Details
        private readonly string _hostname;
        private readonly int _port;
        
        //Connection Options
        private readonly bool _autoConnect;

        //User Connection Details
        private readonly string _playerId;
        private readonly string _characterId;

        public DragonConfig(string hostname, int port, bool autoConnect, string playerId, string characterId)
        {
            _hostname = hostname;
            _port = port;
            _autoConnect = autoConnect;
            _playerId = playerId;
            _characterId = characterId;
        }
    }
}
