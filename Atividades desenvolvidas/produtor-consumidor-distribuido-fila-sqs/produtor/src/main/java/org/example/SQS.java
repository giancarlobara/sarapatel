package org.example;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.List;
import java.util.Random;

public class SQS {
    private String accessKey = "";
    private String secretKey = "";
    private String queueUrl;
    private String messageGroupId;
    private AmazonSQS sqs;
    public SQS(String queueUrl,String messageGroupId){
        this.queueUrl = queueUrl;
        this.messageGroupId = messageGroupId;
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        final AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion("sa-east-1").build();
        this.sqs = sqs;
    }
    public void enviaMensagem( String messageBody) {
        Random r = new Random();
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(this.queueUrl)
                .withMessageBody(messageBody)
                .withMessageDeduplicationId(String.valueOf(r.nextDouble()))
                .withMessageGroupId(this.messageGroupId);
        this.sqs.sendMessage(sendMsgRequest);
        System.out.println("Mensagem enviada para a fila do Produtor");
    }
    public List<Message> recuperaMensagem(int quant) {
        ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest()
                .withQueueUrl(queueUrl)
                .withMaxNumberOfMessages(quant)
                .withWaitTimeSeconds(5);
        return this.sqs.receiveMessage(receiveRequest).getMessages();
    }
    public void deletarMensagem(Message message) {
        String receiptHandle = message.getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(queueUrl, receiptHandle));
    }
}
