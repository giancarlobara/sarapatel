using SDC.Chat.WebApp.Hubs;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using SDC.Chat.WebApp.Data;
using SDC.Chat.WebApp.Domain;
using SDC.Chat.WebApp.Services;
using SDC.Chat.WebApp.Configurations;

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

            builder.Services.Configure<IdentityOptions>(options =>
            {
                // Default Password settings.
                options.Password.RequireDigit = false;
                options.Password.RequireLowercase = false;
                options.Password.RequireNonAlphanumeric = false;
                options.Password.RequireUppercase = false;
                options.Password.RequiredLength = 6;
                options.Password.RequiredUniqueChars = 1;
            });

            // Add services to the container.
            builder.Services.AddControllersWithViews();

            builder.Services.AddSignalR(options => {
                //options.ClientTimeoutInterval = TimeSpan.FromSeconds(60);
                //options.KeepAliveInterval = TimeSpan.FromSeconds(60);
            });

            builder.Services.AddScoped<GroupService>();
            builder.Services.AddSingleton<SemaphoreDirectMessageService>();
            builder.Services.AddSingleton<SemaphoreGroupService>();

            builder.Services.Configure<SqsConfiguration>(builder.Configuration.GetSection("SqsConfiguration"));
            builder.Services.AddSingleton<QueueService>();

            var app = builder.Build();
            if (!app.Environment.IsDevelopment())
            {
                app.UseExceptionHandler("/Home/Error");
                app.UseHsts();
            }

            using (var scope = app.Services.CreateScope())
            {
                var services = scope.ServiceProvider;

                var context = services.GetRequiredService<ChatDbContext>();
                context.Database.Migrate();
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
