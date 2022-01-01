using KingstonDynamics.DragonSong.Client.Example;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

namespace KingstonDynamics.DragonSong.Example.Interface
{
    public class ChatInput : MonoBehaviour
    {

        public InputField input;
        private bool _focused;
        
        private void Update()
        {
            KeyControl();
        }
        
        private void KeyControl()
        {

            // Manage Input Focus
            if (!_focused && input.isFocused)
            {
                _focused = !_focused;
            }
            
            // Hot key Chat Focus
            if (Input.GetKeyUp(KeyCode.T) && !input.isFocused)
            {
                FocusInput();
                return;
            }
            
            if (_focused)
            {
                if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.KeypadEnter))
                {
                    ProcessInput();
                    ClearInput();
                    FocusInput();
                    return;
                }
            }

            // Do this at end to avoid timing issues
            if (_focused && !input.isFocused)
            {
                _focused = !_focused;
            }
        }

        private void FocusInput()
        {
            EventSystem.current.SetSelectedGameObject(input.gameObject, null);
            input.OnPointerClick(new PointerEventData(EventSystem.current));
        }
        
        private void ClearInput()
        {
            input.text = "";
        }

        private void ProcessInput()
        {
            if (input.text.Length == 0)
            {
                return;
            }
            
            var command = new Command(input.text);
        }
    }
}