namespace KingstonDynamics.DragonSong.Client.Example
{
    public static class CommandParser
    {
        public static CommandType getType(string command)
        {
            
            // Is Chat
            if (!command.Substring(0,1).Equals("/"))
            {
                return CommandType.Chat;
            }

            // Is Whisper
            if (command.Substring(0,2).Equals("/w"))
            {
                return CommandType.Whisper;
            }

            // Unknown
            return CommandType.Unknown;
        }
    }
}