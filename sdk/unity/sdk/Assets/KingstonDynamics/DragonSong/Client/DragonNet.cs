using System.Net.Sockets;
using KingstonDynamics.DragonSong.Protocol.Messaging;
using KingstonDynamics.DragonSong.Protocol.Messaging.Type.Authentication;
using KingstonDynamics.DragonSong.Protocol.Networking;
using KingstonDynamics.DragonSong.Protocol.Utils;
using Debug = UnityEngine.Debug;

//
// This class provides a low level pipe into the DragonSong Server
//

namespace KingstonDynamics.DragonSong.Client
{
    public class DragonNet : INetworkManager
    {

        //Our Data Connections
        public TcpClient Socket { get; private set; }

        // Reader and Writer
        private NetworkReader _reader;
        private NetworkWriter _writer;

        //Client Configuration
        private readonly DragonConfig _config;
        
        public DragonNet(DragonConfig config)
        {
            _config = config;
        }
        
        //
        // OPEN SERVER CONNECTION AND STREAMS
        //
        public void Connect()
        {
            Debug.Log("Attempting to Open Connection");

            if (Socket != null)
            {
                Debug.Log("Connection Already Established");
                return;
            }

            try
            {
                //Connect to Server
                Debug.Log("Opening Socket");
                Socket = new TcpClient(_config.Hostname, _config.Port) {Client = {Blocking = true}};
            }
            catch (SocketException ex)
            {
                Debug.Log("DRAGONSONG SERVER APPEARS TO BE DOWN");
                Debug.Log(ex.ToString());

                Socket = null;

                return;
            }
            
            _reader = new NetworkReader(this);
            _reader.Start();
            
            _writer = new NetworkWriter(this);
            _writer.Start();
            
            // Auto Login
            // Authenticate();
            
        }

        //
        // CLOSE SERVER CONNECTION AND STREAMS
        //
        public void Disconnect()
        {
            Debug.Log("CLOSING CONNECTION");

            // Check if Socket exists
            if (Socket == null)
            {
                Debug.Log("Connection Already Closed");
                return;
            }
            
            // Notify Server of Disconnect
            //AuthenticationDisconnect message = new AuthenticationDisconnect();
            //write(message);
            
            //Close Socket
            if (Socket != null)
            {
                //Close Socket and Stream
                Socket.Close();
                Socket = null;
            }
        }

        public void Login()
        {
            var message = new AuthenticationLogin(_config.PlayerId, _config.CharacterId, Keyinator.GenerateGUID());
            Write(message);
        }

        public void Write(Message message)
        {
            // Must have Socket to Write
            if (Socket == null)
            {
                return;
            }

            var b = message.Build();
            
            Debug.Log("Sending Message of Length: " + b.Length);
            _writer.Write(message.Build());
        }
        
        public void HandleInput(byte[] data)
        {   
            Debug.Log("Received Input");
            Debug.Log("Input Length: " + data.Length);
            
            var type = Determinator.Determinate(data);
            
            Debug.Log(type.ToString());
            
            //throw new System.NotImplementedException();
        }

        public void ConnectionError()
        {
            Disconnect();
        }
    }
}

