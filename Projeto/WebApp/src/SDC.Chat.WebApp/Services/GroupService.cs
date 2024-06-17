using SDC.Chat.WebApp.Data;
using SDC.Chat.WebApp.Domain;
using Microsoft.EntityFrameworkCore;

namespace SDC.Chat.WebApp.Services
{
    public class GroupService
    {
        private readonly ChatDbContext _chatDbContext;

        public GroupService(ChatDbContext chatDbContext)
        {
            _chatDbContext = chatDbContext;
        }

        public async Task<Group?> GetPrivateGroupWithMessagesAsync(Guid userId, Guid loggedUserId)
        {
            return _chatDbContext.Group
                .Include(x => x.Messages)
                    .ThenInclude(x => x.User)
                .FirstOrDefault(x => x.IsPrivate &&
                                     x.UserGroup.Any(y => y.UserId == userId) &&
                                     x.UserGroup.Any(y => y.UserId == loggedUserId));
        }
    }
}
