using Microsoft.AspNetCore.Identity;

namespace SDC.Chat.WebApp.Domain;

// Add profile data for application users by adding properties to the User class
public class User : IdentityUser<Guid>
{
    public ICollection<Message> Messages { get; set; }

    public ICollection<UserGroup> UserGroup { get; set; }
}

