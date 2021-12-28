using UnityEngine;

namespace KingstonDynamics.DragonSong.Client.Example
{
    public class Heartbeat : MonoBehaviour
    {
        private KingstonDynamics.DragonSong.Example.Client.Client _client;

        public float delay = 1.0f;
        public float repeat = 2.0f;
        
        private void Start()
        {
            _client = KingstonDynamics.DragonSong.Example.Client.Client.Instance;
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