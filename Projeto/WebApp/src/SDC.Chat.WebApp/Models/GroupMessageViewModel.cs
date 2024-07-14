namespace SDC.Chat.WebApp.Models
{
    public class GroupMessageViewModel
    {
        public Guid GroupId { get; set; }

        public List<MessageViewModel> Messages { get; set; }
    }
}
