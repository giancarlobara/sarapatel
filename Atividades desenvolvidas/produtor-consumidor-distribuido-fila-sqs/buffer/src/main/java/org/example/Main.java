package org.example;

import com.amazonaws.services.sqs.model.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SQS sqsProdutor = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/produtor.fifo", "grupo-1");
        SQS sqsProdutorAux = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/aux-produtor.fifo", "grupo-1");
        SQS sqsConsumidor = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/consumidor.fifo", "grupo-1");
        SQS sqsConsumidorAux = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/aux-consumidor.fifo", "grupo-1");
        while (true){
            List<Message> messages = sqsConsumidorAux.recuperaMensagem();
            for (Message msg : messages){
                sqsConsumidorAux.deletarMensagem(msg);
            }
            List<Message> messagesCon = sqsConsumidor.recuperaMensagem();
            for (Message msg : messagesCon){
                sqsConsumidor.deletarMensagem(msg);
            }
            List<Message> messagesProAux= sqsProdutorAux.recuperaMensagem();
            for (Message msg : messagesProAux){
                sqsProdutorAux.deletarMensagem(msg);
            }
            List<Message> messagesPro = sqsProdutor.recuperaMensagem();
            for (Message msg : messagesPro){
                sqsProdutor.deletarMensagem(msg);
            }
        }

    }
}