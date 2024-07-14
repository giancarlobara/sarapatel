using Microsoft.AspNetCore.SignalR;
using Microsoft.EntityFrameworkCore;
using SDC.Chat.WebApp.Data;
using SDC.Chat.WebApp.Domain;
using SDC.Chat.WebApp.Services;
using System.Security.Claims;
using System.Text.RegularExpressions;

namespace SDC.Chat.WebApp.Hubs
{
    public class ChatHub : Hub
    {
        private readonly ChatDbContext _chatDbContext;
        private readonly GroupService _groupService;
        private readonly SemaphoreDirectMessageService _semaphoreDirectMessageService;
        private readonly SemaphoreGroupService _semaphoreGroupService;

        public ChatHub(ChatDbContext chatDbContext, 
            GroupService groupService,
            SemaphoreDirectMessageService semaphoreDirectMessageService,
            SemaphoreGroupService semaphoreGroupService)
        {
            _chatDbContext = chatDbContext;
            _groupService = groupService;
            _semaphoreDirectMessageService = semaphoreDirectMessageService;
            _semaphoreGroupService = semaphoreGroupService;
        }

        public override async Task OnConnectedAsync()
        {
            var httpContext = Context.GetHttpContext();
            var groupId = httpContext.Request.Query["groupId"];

            await Groups.AddToGroupAsync(Context.ConnectionId, groupId);
        }

        public async Task SendDirectMessage(string message, Guid userId)
        {
            try
            {
                await _semaphoreDirectMessageService.Semaphore.WaitAsync();

                var loggedUserId = Guid.Parse(Context.User.Claims.FirstOrDefault(x => x.Type == ClaimTypes.NameIdentifier)?.Value);
                var group = await _groupService.GetPrivateGroupWithMessagesAsync(userId, loggedUserId);

                var dateUtcNow = DateTime.UtcNow;

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

                await Groups.AddToGroupAsync(Context.ConnectionId, group.Id.ToString());

                var data = $"{messageEntity.Sent.ToLocalTime().ToShortDateString()} {messageEntity.Sent.ToLocalTime().ToString("HH:mm")}";

                await Clients.Group(group.Id.ToString()).SendAsync("ReceiveMessage", Context.User.Identity.Name, message, data);
            }

            catch
            {
                throw;
            }

            finally
            {
                _semaphoreDirectMessageService.Semaphore.Release();
            }
        }

        public async Task SendGroupMessage(string message, Guid groupId)
        {
            try
            {
                await _semaphoreGroupService.Semaphore.WaitAsync();

                var loggedUserId = Guid.Parse(Context.User.Claims.FirstOrDefault(x => x.Type == ClaimTypes.NameIdentifier)?.Value);
                var group = await _groupService.GetGroupWithMessagesByIdAsync(groupId);

                if (group == null)
                {
                    return;
                }

                var dateUtcNow = DateTime.UtcNow;

                var messageEntity = new Message()
                {
                    Type = MessageType.Text,
                    Content = message,
                    UserId = loggedUserId,
                    Sent = DateTime.UtcNow,
                    GroupId = group.Id
                };

                _chatDbContext.Add(messageEntity);

                await _chatDbContext.SaveChangesAsync();

                await Groups.AddToGroupAsync(Context.ConnectionId, groupId.ToString());

                var data = $"{messageEntity.Sent.ToLocalTime().ToShortDateString()} {messageEntity.Sent.ToLocalTime().ToString("HH:mm")}";

                await Clients.Group(groupId.ToString()).SendAsync("ReceiveMessage", Context.User.Identity.Name, message, data);
            }

            catch
            {
                throw;
            }

            finally
            {
                _semaphoreGroupService.Semaphore.Release();
            }
        }
    }
}
