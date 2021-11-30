namespace KingstonDynamics.DragonSong.Client.Example
{
    public class ChatCommand
    {
        // private static readonly CommandType _type = CommandType.Chat;
        
        private string Recipient { get; }
        private string Message { get; }

        public ChatCommand(string recipient, string message)
        {
            Recipient = recipient;
            Message = message;
        }
    }
}