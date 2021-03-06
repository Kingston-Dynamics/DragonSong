using UnityEngine;

namespace KingstonDynamics.DragonSong.Example.Client
{
    public class Heartbeat : MonoBehaviour
    {
        private Client _client;

        public float delay = 1.0f;
        public float repeat = 2.0f;
        
        private void Start()
        {
            _client = Client.Instance;
            InvokeRepeating(nameof(Ping), delay, repeat);
        }

        private void OnDestroy()
        {
            CancelInvoke();
        }

        private void Ping()
        {
            _client.Ping();
        }
    }
}