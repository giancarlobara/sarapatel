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
            return await _chatDbContext.Group
                .Include(x => x.Messages)
                    .ThenInclude(x => x.User)
                .FirstOrDefaultAsync(x => x.IsPrivate &&
                                     x.UserGroup.Any(y => y.UserId == userId) &&
                                     x.UserGroup.Any(y => y.UserId == loggedUserId));
        }

        public async Task<Group?> GetByNameAsync(string groupName)
        {
            return await _chatDbContext.Group
                .Include(x => x.Messages)
                    .ThenInclude(x => x.User)
                .Include(x => x.UserGroup)
                .FirstOrDefaultAsync(x => x.Name != null && x.Name.ToLower() == groupName);
        }

        public async Task CreateAsync(Group group)
        {
            await _chatDbContext.Group.AddAsync(group);
            await _chatDbContext.SaveChangesAsync();
        }

        public async Task AddUserToGroup(Group group, Guid loggedUserId)
        {
            group.UserGroup.Add(new UserGroup()
            {
                Joined = DateTime.UtcNow,
                UserId = loggedUserId,
            });

            await _chatDbContext.SaveChangesAsync();
        }

        public async Task<Group?> GetGroupWithMessagesByIdAsync(Guid groupId)
        {
            return await _chatDbContext.Group
                .Include(x => x.Messages)
                .FirstOrDefaultAsync(x => x.Id == groupId);
        }
    }
}
