using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

namespace KingstonDynamics.DragonSong.Example.Interface
{
    public class ChatDisplay : MonoBehaviour
    {
        // Chat Output Reference
        public Text display;
        
        // Data Inside Chat
        private List<string> _chatLogData;
        private string _chatLogText = ""; 
        
        // private readonly char _newline = '\n';
        
        private void Start()
        {
            _chatLogData = new List<string>();
        }

        private void Update()
        {
            UpdateDisplay();
        }

        private void UpdateDisplay()
        {
            display.text = _chatLogText; 
            
            
            // Old Code From Old Implementation
//            Debug.Log("Got Message");
//
//            Char newline = '\n';
//
//            //Create Message and Add it to List
//
//            InboundMessage data = new InboundMessage(message.Payload.Build());
//
//
//            string chatMessage = "[" + data.ChannelID.FieldData + "]" + " " + data.DisplayName.FieldData + ":" + data.Message.FieldData;
//            chatLogData.Add(chatMessage);
//
//            //Trim the List
//            if (chatLogData.Count > maxLines)
//            {
//                chatLogData.RemoveAt(0);
//            }
//
//            //Clear ChatLogText
//            chatLogText = "";
//
//            foreach (String temp in chatLogData)
//            {
//                chatLogText += newline + temp;
//            } 
//
//            text.text = chatLogText;
        }
    }
}