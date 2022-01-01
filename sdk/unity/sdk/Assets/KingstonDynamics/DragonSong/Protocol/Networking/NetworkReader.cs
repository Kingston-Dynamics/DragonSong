using System.Net.Sockets;
using System.Threading;
using KingstonDynamics.DragonSong.Protocol.Utils;
using UnityEngine;

namespace KingstonDynamics.DragonSong.Protocol.Networking
{
    public class NetworkReader
    {

        // Manager
        private readonly INetworkManager _manager;
        
        // Threading
        private readonly Thread _thread;
        private bool _running;
        
        // Network Stream
        private TcpClient _client;
        private readonly NetworkStream _stream;

        // Constructor
        public NetworkReader(INetworkManager manager)
        {
            // Pull Data
            _manager = manager;
            _client = _manager.Socket;
            _stream = _client.GetStream();
            
            // Create Thread
            _thread = new Thread(Run) {IsBackground = true};
        }

        public void Start()
        {
            // Can't start Thread Twice'
            if (_thread.IsAlive)
            {
                return;
            }

            _running = true;
            _thread.Start();
        }

        public void Stop()
        {
            if (!_thread.IsAlive)
            {
                return;
            }

            _running = false;
            _thread.Abort(); // TODO: Using this is apparently bed practice
        }

        // Thread Logic
        private void Run()
        {
            // TODO Catch IO Exception
            while (_running)
            {
                // Length Prefix
                var length = ReadLength();
            
                // Byte Data
                var data = ReadData(length);

                if (data.Length != 0)
                {
                    // Send off to Manager
                    _manager.HandleInput(data);
                }
            }
        }

        private int ReadLength()
        {
            Debug.Log("Reading Length");
            
            // Frame Siz
            var read = 0;
            var buffer = new byte[4];
            
            // Read Fully
            while (read < 4)
            {
                read += _stream.Read(buffer, 0, buffer.Length);
                Debug.Log("Read: " + read);

                // if (read == 0) ;
                // {
                //     Debug.Log("Connection Error");
                //     _manager.connectionError();
                // }
            }
            
            return Byteinator.BytesToInt(buffer);
        }

        private byte[] ReadData(int length)
        {
            Debug.Log("Reading Data of Length:" + length);
            
            // Frame Data
            var read = 0;
            var buffer = new byte[length];
            
            // Read Fully
            while (read < length)
            {
                read += _stream.Read(buffer, 0, buffer.Length);
                Debug.Log("Read: " + read);
                
                // if (read == 0)
                // {
                //     Debug.Log("Connection Error");
                //     _manager.connectionError();
                // }
            }
            
            return buffer;
        }
    }
}