namespace SDC.Chat.WebApp.Domain
{
    public class Message
    {
        public Message()
        {
            Id = Guid.NewGuid();
        }

        public Guid Id { get; set; }

        public MessageType Type { get; set; }

        public string Content { get; set; }

        // FK e propriedade de navegacao
        public Guid GroupId { get; set; }

        public Group Group { get; set; }

        public Guid UserId { get; set; }

        public User User { get; set; }

        public DateTime Sent { get; set; }
    }
}
