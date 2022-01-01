using KingstonDynamics.DragonSong.Client;
using UnityEngine;
using UnityEngine.Assertions;

//
// This class is a simple test class to test IO using keys. 
//

namespace KingstonDynamics.DragonSong.Example.Client
{

    public class Client : Singleton<Client>
    {
 
        // High Level Client
        private DragonSong.Client.DragonSong _client;
        
        // Unity Exposed Bits
        public string hostname;
        public int port;
        public string playerId;
        public string characterId;
        public bool autoConnect;
        
        private void Start()
        {
            // Pull values from Player Preferences
            hostname = PlayerPrefs.GetString("SERVER_ADDRESS", "127.0.0.1");
            port = PlayerPrefs.GetInt("SERVER_PORT", 27888);
            playerId = PlayerPrefs.GetString("PLAYER_ID", playerId);
            characterId = PlayerPrefs.GetString("CHARACTER_ID", characterId);
            autoConnect = UserPrefs.GetBool("AUTO_CONNECT", autoConnect);
            
            // Sanity Checks
            Assert.IsFalse(string.IsNullOrWhiteSpace(hostname));
            Assert.IsFalse(string.IsNullOrWhiteSpace(playerId));
            Assert.IsFalse(string.IsNullOrWhiteSpace(characterId));
            Assert.IsFalse(port == 0);

            //Create New Config
	        var config = new DragonConfig(hostname, port, autoConnect, playerId, characterId);

            //Create DragonSong Instance
            _client = new DragonSong.Client.DragonSong(config);
        }
        
        private void OnDestroy()
        {
            _client?.Disconnect();
        }
        
        private void Update()
        { 
            _client?.Process();
        }
        
        public void SendMessage(string channel, string message)
        {
            _client?.PublishMessage(channel, message);
        }

        public void SendPrivateMessage(string recipient, string message)
        {
            _client?.SendPrivateMessage(recipient, message);
        }

        public void JoinChannel(string channel)
        {
            _client?.JoinChannel(channel);
        }
        
        public void Ping()
        {
            Debug.Log("Pinging Server");
            _client?.SendPing();
        }
    }
}
