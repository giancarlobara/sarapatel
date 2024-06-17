using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Microsoft.EntityFrameworkCore;
using SDC.Chat.WebApp.Domain;

namespace SDC.Chat.WebApp.Data.Configuration
{
    public class GroupConfiguration : IEntityTypeConfiguration<Group>
    {
        public void Configure(EntityTypeBuilder<Group> builder)
        {
            builder.HasKey(x => x.Id);

            builder.HasMany(x => x.UserGroup)
                .WithOne(x => x.Group)
                .HasForeignKey(x => x.GroupId);

            builder.HasMany(x => x.Messages)
                .WithOne(x => x.Group)
                .HasForeignKey(x => x.GroupId);

            builder.Property(x => x.Name)
                .HasMaxLength(255);

            builder.ToTable("Group");
        }
    }
}
