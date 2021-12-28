namespace KingstonDynamics.DragonSong.Client.Example
{
    public static class CommandParser
    {
        public static CommandType GetType(string command)
        {
            
            // Is Chat
            if (!command.Substring(0,1).Equals("/"))
            {
                return CommandType.CHAT;
            }

            // Is Whisper
            if (command.Substring(0,2).Equals("/w"))
            {
                return CommandType.WHISPER;
            }

            // Unknown
            return CommandType.UNKNOWN;
        }
    }
}