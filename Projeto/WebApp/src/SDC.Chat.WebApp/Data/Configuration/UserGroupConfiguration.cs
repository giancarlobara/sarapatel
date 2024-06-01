using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Microsoft.EntityFrameworkCore;
using SDC.Chat.WebApp.Domain;

namespace SDC.Chat.WebApp.Data.Configuration
{
    public class UserGroupConfiguration : IEntityTypeConfiguration<UserGroup>
    {
        public void Configure(EntityTypeBuilder<UserGroup> builder)
        {
            builder.HasKey(x => new { x.UserId, x.GroupId });

            builder.ToTable("UserGroup");
        }
    }
}
