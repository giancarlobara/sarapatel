using SDC.Chat.WebApp.Hubs;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using SDC.Chat.WebApp.Data;
using SDC.Chat.WebApp.Domain;
using SDC.Chat.WebApp.Services;

namespace SDC.Chat.WebApp
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);
            var connectionString = builder.Configuration.GetConnectionString("ChatDbContextConnection") ?? throw new InvalidOperationException("Connection string 'ChatDbContextConnection' not found.");

            builder.Services.AddDbContext<ChatDbContext>(options => options.UseSqlServer(connectionString));

            builder.Services.AddDefaultIdentity<User>(options => options.SignIn.RequireConfirmedAccount = false).AddEntityFrameworkStores<ChatDbContext>();

            // Add services to the container.
            builder.Services.AddControllersWithViews();

            builder.Services.AddSignalR(options => {
                //options.ClientTimeoutInterval = TimeSpan.FromSeconds(60);
                //options.KeepAliveInterval = TimeSpan.FromSeconds(60);
            });

            builder.Services.AddScoped<GroupService>();

            var app = builder.Build();
            if (!app.Environment.IsDevelopment())
            {
                app.UseExceptionHandler("/Home/Error");
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthorization();

            app.MapControllerRoute(
                name: "default",
                pattern: "{controller=Home}/{action=Index}/{id?}");

            app.MapHub<ChatHub>("/chatHub");

            app.MapRazorPages();

            app.Run();
        }
    }
}
