using Amazon;
using Amazon.SQS;
using Amazon.SQS.Model;
using Microsoft.Extensions.Options;
using SDC.Chat.WebApp.Configurations;
using SDC.Chat.WebApp.DTOs;
using System.Text.Json;

namespace SDC.Chat.WebApp.Services
{
    public class QueueService
    {
        private readonly AmazonSQSClient _amazonSQSClient;
        private readonly string _emailQueueUrl;

        public QueueService(IOptions<SqsConfiguration> options)
        {
            _emailQueueUrl = options.Value.Url;

            var config = new AmazonSQSConfig()
            {
                RegionEndpoint = RegionEndpoint.GetBySystemName(options.Value.Region),
                ServiceURL = options.Value.Url
            };

            _amazonSQSClient = new AmazonSQSClient(options.Value.AccessKey, options.Value.SecretKey, config);
        }

        public async Task SendAsync( ConfirmEmailDTO confirmEmailDTO )
        {
            var message = JsonSerializer.Serialize(confirmEmailDTO);

            var sendMessageRequest = new SendMessageRequest()
            {
                MessageBody = message,
                QueueUrl = _emailQueueUrl,
                MessageGroupId = "email"
            };

            await _amazonSQSClient.SendMessageAsync(sendMessageRequest);
        }
    }
}
