using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using SDC.Chat.WebApp.Domain;
using SDC.Chat.WebApp.Models;
using SDC.Chat.WebApp.Services;
using System.Diagnostics;
using System.Security.Claims;

namespace SDC.Chat.WebApp.Controllers
{
    [Authorize]
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly UserManager<User> _userManager;

        public HomeController(ILogger<HomeController> logger, UserManager<User> userManager)
        {
            _logger = logger;
            _userManager = userManager;
        }

        public async Task<IActionResult> Index()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Group(HomeModel model)
        {
            ModelState.Clear();

            if (string.IsNullOrEmpty(model.Room))
            {
                ModelState.AddModelError(nameof(model.Room), "Sala inválida");
                return View("Index", model);
            }

            return RedirectToAction("GroupMessage", "Chat", new { room = model.Room });
        }

        [HttpPost]
        public async Task<IActionResult> DirectMessage(HomeModel model)
        {
            ModelState.Clear();

            if (string.IsNullOrEmpty(model.UserEmail))
            {
                ModelState.AddModelError(nameof(model.UserEmail), "Email inválido");
                return View("Index", model);
            }

            var loggedUserEmail = HttpContext.User.Claims.FirstOrDefault(x => x.Type == ClaimTypes.Name)?.Value;

            if (loggedUserEmail.ToLower() == model.UserEmail.ToLower())
            {
                ModelState.AddModelError(nameof(model.UserEmail), "Email inválido");
                return View("Index", model);
            }

            var user = await _userManager.FindByEmailAsync(model.UserEmail);

            if (user != null)
            {
                return RedirectToAction("DirectMessage", "Chat", new { userEmail = model.UserEmail } );
            }

            ModelState.AddModelError(nameof(model.UserEmail), "Usuário não encontrado");
            
            return View("Index", model);
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
