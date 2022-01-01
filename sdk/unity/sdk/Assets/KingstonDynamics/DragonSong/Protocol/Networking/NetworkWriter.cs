using System.Collections.Concurrent;
using System.Net.Sockets;
using System.Threading;
using KingstonDynamics.DragonSong.Protocol.Utils;

namespace KingstonDynamics.DragonSong.Protocol.Networking
{
    public class NetworkWriter
    {
        // Manager
        private readonly INetworkManager _manager;
        
        // Threading
        private readonly Thread _thread;
        private bool _running;
        
        // Network Stream
        private TcpClient _client;
        private readonly NetworkStream _stream;
        
        // Write Queue
        private readonly BlockingCollection<byte[]> _queue;
        
        public NetworkWriter(INetworkManager manager)
        {
            // Pull Data
            _manager = manager;
            _client = _manager.Socket;
            _stream = _client.GetStream();
            
            // Create Thread
            _thread = new Thread(Run) {IsBackground = true};

            // Create Queue
            _queue = new BlockingCollection<byte[]>();
            
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
        
        private void Run()
        {
            // TODO: Catch IO Exception

            while (_running)
            {
                var data = _queue.Take();
                var length = Byteinator.IntToBytes(data.Length);

                if (_stream.CanWrite)
                {
                    // Write Length
                    _stream.Write(length, 0, length.Length);
                    _stream.Flush();
                
                    // Write Data
                    _stream.Write(data, 0, data.Length);
                    _stream.Flush();
                }
            }
        }

        public void Write(byte[] data)
        {
            _queue.Add(data);
        }
    }
}