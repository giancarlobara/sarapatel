using Microsoft.AspNetCore.SignalR;
using Microsoft.EntityFrameworkCore;
using SDC.Chat.WebApp.Data;
using SDC.Chat.WebApp.Domain;
using SDC.Chat.WebApp.Services;
using System.Security.Claims;

namespace SDC.Chat.WebApp.Hubs
{
    public class ChatHub : Hub
    {
        private readonly ChatDbContext _chatDbContext;
        private readonly GroupService _groupService;

        public ChatHub(ChatDbContext chatDbContext, GroupService groupService)
        {
            _chatDbContext = chatDbContext;
            _groupService = groupService;
        }

        public async Task SendDirectMessage(string message, Guid userId)
        {
            var loggedUserId = Guid.Parse(Context.User.Claims.FirstOrDefault(x => x.Type == ClaimTypes.NameIdentifier)?.Value);
            var group = await _groupService.GetPrivateGroupWithMessagesAsync(userId, loggedUserId);

            var dateUtcNow = DateTime.UtcNow;

            if (group == null)
            {
                group = new Group()
                {
                    IsPrivate = true
                };

                group.UserGroup = new List<UserGroup>()
                {
                    new UserGroup()
                    {
                        UserId = loggedUserId,
                        GroupId = group.Id,
                        Joined = dateUtcNow
                    },
                    new UserGroup()
                    {
                        UserId = userId,
                        GroupId = group.Id,
                        Joined = dateUtcNow
                    }
                };

                group.Messages = new List<Message>();

                _chatDbContext.Add(group);
            }

            var messageEntity = new Message()
            {
                Type = MessageType.Text,
                Content = message,
                UserId = loggedUserId,
                Sent = dateUtcNow,
                GroupId = group.Id
            };

            _chatDbContext.Add(messageEntity);

            await _chatDbContext.SaveChangesAsync();

            await Clients.All.SendAsync("ReceiveMessage", Context.User.Identity.Name, message, dateUtcNow.ToLocalTime().ToString());
        }

        public async Task SendGroupMessage(string user, string message)
        {
            var sala = "sala";
            await Groups.AddToGroupAsync(Context.ConnectionId, sala);

            await Clients.Group(sala).SendAsync("ReceiveMessage", user, message);
        }
    }
}
