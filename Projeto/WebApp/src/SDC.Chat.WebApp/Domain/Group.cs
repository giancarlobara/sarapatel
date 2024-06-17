namespace SDC.Chat.WebApp.Domain
{
    public class Group
    {
        public Group()
        {
            Id = Guid.NewGuid();    
        }

        public Guid Id { get; set; }

        public ICollection<UserGroup> UserGroup { get; set; }

        public ICollection<Message> Messages { get; set; }

        public bool IsPrivate { get; set; }

        public string? Name { get; set; }
    }
}
