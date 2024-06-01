namespace SDC.Chat.WebApp.Domain
{
    public class Group
    {
        public Guid Id { get; set; }

        public IEnumerable<UserGroup> UserGroup { get; set; } = Enumerable.Empty<UserGroup>();

        public IEnumerable<Message> Messages { get; set; } = Enumerable.Empty<Message>();

        public bool IsPrivate { get; set; }
    }
}
