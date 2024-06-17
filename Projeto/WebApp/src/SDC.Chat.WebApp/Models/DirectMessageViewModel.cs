namespace SDC.Chat.WebApp.Models
{
    public class DirectMessageViewModel
    {
        public Guid UserId { get; set; }

        public List<MessageViewModel> Messages { get; set; }
    }
}
