using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using SDC.Chat.WebApp.Data;
using SDC.Chat.WebApp.Domain;
using SDC.Chat.WebApp.Models;
using SDC.Chat.WebApp.Services;
using System.Security.Claims;

namespace SDC.Chat.WebApp.Controllers
{
    [Authorize]
    public class ChatController : Controller
    {
        private readonly ILogger<ChatController> _logger;
        private readonly UserManager<User> _userManager;
        private readonly GroupService _groupService;

        public ChatController(ILogger<ChatController> logger, 
            UserManager<User> userManager,
            GroupService groupService)
        {
            _logger = logger;
            _userManager = userManager;
            _groupService = groupService;
        }

        public async Task<IActionResult> DirectMessage(string userEmail)
        {
            var user = await _userManager.FindByEmailAsync(userEmail);

            if(user == null)
            {
                
            }

            var messages = new List<MessageViewModel>();

            var loggedUserId = Guid.Parse(HttpContext.User.Claims.FirstOrDefault(x => x.Type == ClaimTypes.NameIdentifier)?.Value);
            var group = await _groupService.GetPrivateGroupWithMessagesAsync(user.Id, loggedUserId);

            var dateTimeNow = DateTime.UtcNow;

            if (group == null)
            {
                group = new Group()
                {
                    IsPrivate = true,
                    UserGroup = new List<UserGroup>()
                    {
                        new UserGroup()
                        {
                            Joined = dateTimeNow,
                            UserId = loggedUserId,
                        },
                        new UserGroup()
                        {
                            Joined = dateTimeNow,
                            UserId = user.Id
                        }
                    }
                };

                await _groupService.CreateAsync(group);
            }

            if (group != null && group.Messages != null)
            {
                messages = group.Messages.OrderBy(x => x.Sent).Select(x => new MessageViewModel()
                {
                    Date = $"{x.Sent.AddHours(-3).ToShortDateString()} {x.Sent.AddHours(-3).ToString("HH:mm")}",
                    IsFromLoggedUser = x.UserId == loggedUserId,
                    Username = x.User.UserName,
                    Content = x.Content
                }).ToList();
            }

            return View(new DirectMessageViewModel()
            {
                UserId = user.Id,
                Messages = messages,
                GroupId = group.Id
            });
        }

        public async Task<IActionResult> GroupMessage(string room)
        {
            if (string.IsNullOrWhiteSpace(room))
            {

            }

            var group = await _groupService.GetByNameAsync(room);

            var loggedUserId = Guid.Parse(HttpContext.User.Claims.FirstOrDefault(x => x.Type == ClaimTypes.NameIdentifier)?.Value);

            if (group == null)
            {
                group = new Group()
                {
                    IsPrivate = false,
                    Name = room.ToLower(),
                    UserGroup = new List<UserGroup>()
                    {
                        new UserGroup()
                        {
                            Joined = DateTime.UtcNow,
                            UserId = loggedUserId,
                        }
                    }
                };

                await _groupService.CreateAsync(group);
            }

            if(!group.UserGroup.Any(x => x.UserId == loggedUserId))
            {
                await _groupService.AddUserToGroup(group, loggedUserId);
            }

            var messages = new List<MessageViewModel>();

            if (group != null && group.Messages != null)
            {
                messages = group.Messages.OrderBy(x => x.Sent).Select(x => new MessageViewModel()
                {
                    Date = $"{x.Sent.AddHours(-3).ToShortDateString()} {x.Sent.AddHours(-3).ToString("HH:mm")}",
                    IsFromLoggedUser = x.UserId == loggedUserId,
                    Username = x.User.UserName,
                    Content = x.Content
                }).ToList();
            }

            return View(new GroupMessageViewModel()
            {
                GroupId = group.Id,
                Messages = messages
            });
        }
    }
}
