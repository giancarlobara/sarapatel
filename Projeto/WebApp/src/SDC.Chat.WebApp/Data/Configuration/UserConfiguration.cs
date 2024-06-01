using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Microsoft.EntityFrameworkCore;
using SDC.Chat.WebApp.Domain;

namespace SDC.Chat.WebApp.Data.Configuration
{
    public class UserConfiguration : IEntityTypeConfiguration<User>
    {
        public void Configure(EntityTypeBuilder<User> builder)
        {
            builder.HasMany(x => x.UserGroup)
                .WithOne(x => x.User)
                .HasForeignKey(x => x.UserId);

            builder.HasMany(x => x.Messages)
                .WithOne(x => x.User)
                .HasForeignKey(x => x.UserId);

            builder.ToTable("User");
        }
    }
}
