using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.Serialization;
using UnityEngine.UI;
using Toggle = UnityEngine.UI.Toggle;

namespace KingstonDynamics.DragonSong.Example.Interface
{
    public class LoginManager : MonoBehaviour
    {

        // Must be assigned in Editor
        public InputField apiKeyInput;
        public InputField displayNameInput;

        public Toggle autoConnect;
        public InputField serverAddressInput;
        public InputField serverPortInput;

        private void Start()
        {

            // Set Default Value
            autoConnect.isOn = UserPrefs.GetBool("AUTO_CONNECT");

        }

        public void Login()
        {

            // Save Inputs
            SaveLogin();
            SaveConfig();

            // Transition
            SceneManager.LoadScene("Chat");
        }

        private void SaveLogin()
        {
            // Save login information to player preferences
            // This will be used for authentication later

            // Set Login Variables
            PlayerPrefs.SetString("API_KEY", apiKeyInput.text);
            PlayerPrefs.SetString("DISPLAY_NAME", displayNameInput.text);
        }

        private void SaveConfig()
        {
            // Save Config information to player preferences
            // This will be used for configuration later. 

            // Set Config Variables
            UserPrefs.SetBool("AUTO_CONNECT", autoConnect.isOn);
            PlayerPrefs.SetString("SERVER_ADDRESS", serverAddressInput.text);
            PlayerPrefs.SetInt("SERVER_PORT", int.Parse(serverPortInput.text));
        }
    }
}
