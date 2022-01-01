using System.Net.Sockets;

namespace KingstonDynamics.DragonSong.Protocol.Networking
{
    public interface INetworkManager
    {
        
        // Input From Reader
        void HandleInput(byte[] data);

        // Connection Error From Socket
        void ConnectionError();
        
        // Get Copy of Socket
        TcpClient Socket { get; }
    }
}