namespace SDC.Chat.WebApp.Configurations
{
    public class SqsConfiguration
    {
        public string Region { get; set; }
        public string QueueId { get; set; }
        public string QueueName { get; set; }
        public string AccessKey { get; set; }
        public string SecretKey { get; set; }
        public string Url { get; set; }
    }
}
