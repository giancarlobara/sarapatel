using Microsoft.AspNetCore.SignalR;

namespace SDC.Chat.WebApp.Hubs
{
    public class ChatHub : Hub
    {
        public async Task SendMessage(string user, string message)
        {
            await Clients.All.SendAsync("ReceiveMessage", Context.User.Identity.Name, message);
        }

        public async Task SendGroupMessage(string user, string message)
        {
            var sala = "sala";
            await Groups.AddToGroupAsync(Context.ConnectionId, sala);

            await Clients.Group(sala).SendAsync("ReceiveMessage", user, message);
        }
    }
}
