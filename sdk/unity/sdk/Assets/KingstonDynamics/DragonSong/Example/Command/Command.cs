using System;
using UnityEngine;

namespace KingstonDynamics.DragonSong.Client.Example
{
    public class Command
    {
        private string Value { get; }
        private CommandType Type { get; }

        public Command(string s)
        {
            Value = s;
            Type = CommandParser.GetType(s);
        }
    }
}