namespace SDC.Chat.WebApp.Services
{
    public abstract class SemaphoreService
    {
        public SemaphoreSlim Semaphore = new SemaphoreSlim(1, 1);
    }
}
