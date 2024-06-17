namespace SDC.Chat.WebApp.Models
{
    public class MessageViewModel
    {
        public string Username { get; set; }

        public string Date { get; set; }

        public bool IsFromLoggedUser { get; set; }

        public string Content { get; set; }
    }
}
