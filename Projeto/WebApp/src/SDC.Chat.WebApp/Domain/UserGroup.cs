namespace SDC.Chat.WebApp.Domain
{
    public class UserGroup
    {
        public Guid GroupId { get; set; }

        public Group Group { get; set; } = null!;

        public Guid UserId { get; set; }

        public User User { get; set; } = null!;

        public DateTime Joined { get; set; }

        public DateTime? Left { get; set; }
    }
}
