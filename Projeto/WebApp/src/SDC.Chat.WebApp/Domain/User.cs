using Microsoft.AspNetCore.Identity;

namespace SDC.Chat.WebApp.Domain;

// Add profile data for application users by adding properties to the User class
public class User : IdentityUser<Guid>
{
    public IEnumerable<Message> Messages { get; set;} = Enumerable.Empty<Message>();

    public IEnumerable<UserGroup> UserGroup { get; set; } = Enumerable.Empty<UserGroup>();
}

