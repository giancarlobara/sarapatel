using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace SDC.Chat.WebApp.Migrations
{
    /// <inheritdoc />
    public partial class GroupNameUniqueIndex : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_Group_Name",
                table: "Group",
                column: "Name",
                unique: true,
                filter: "[Name] IS NOT NULL");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_Group_Name",
                table: "Group");
        }
    }
}
