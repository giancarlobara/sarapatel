package org.example;

import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SQS sqsProdutor = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/produtor.fifo", "grupo-1");
        SQS sqsProdutorAux = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/aux-produtor.fifo", "grupo-1");
        String name = "PRODUTOR 1";

        while (true) {
            System.out.println("Verificando mensagens na fila...");
            List<Message> messages = sqsProdutorAux.recuperaMensagem(1);
            for (Message message : messages) {
                System.out.println("Mensagem recebida do PRODUTOR AUXILIAR:");
                System.out.println("  Body: " + message.getBody());
                sqsProdutorAux.deletarMensagem(message);
                sqsProdutor.enviaMensagem(name);
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}