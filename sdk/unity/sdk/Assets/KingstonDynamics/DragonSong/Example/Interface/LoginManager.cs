using System;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using UnityEngine.UIElements;
using Toggle = UnityEngine.UI.Toggle;

public class LoginManager : MonoBehaviour
{

    // Must be assigned in Editoe
    public InputField PlayerIDInput;
    public InputField CharacterIDInput;

    public Toggle AutoConnect;
    public InputField ServerAddressInput;
    public InputField ServerPortInput;

    private void Start()
    {

        // Set Default Value
        AutoConnect.isOn = UserPrefs.GetBool("AUTO_CONNECT");

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
        UserPrefs.SetString("PLAYER_ID", PlayerIDInput.text);
        UserPrefs.SetString("CHARACTER_ID", CharacterIDInput.text);
    }

    private void SaveConfig()
    {
        // Save Config information to player preferences
        // This will be used for configuration later. 

        // Set Config Variables
        UserPrefs.SetBool("AUTO_CONNECT", AutoConnect.isOn);
        UserPrefs.SetString("SERVER_ADDRESS", ServerAddressInput.text);
        UserPrefs.SetInt("SERVER_PORT", Int32.Parse(ServerPortInput.text));
    }
}
