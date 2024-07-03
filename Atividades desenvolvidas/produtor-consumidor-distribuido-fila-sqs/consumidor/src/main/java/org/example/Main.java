package org.example;

import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SQS sqsConsumidor = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/consumidor.fifo", "grupo-1");
        SQS sqsConsumidorAux = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/aux-consumidor.fifo", "grupo-1");
        String nome = "CONSUMIDOR 1";


        while (true) {
            System.out.println("Verificando mensagens na fila...");
            List<Message> messages = sqsConsumidorAux.recuperaMensagem(1);
            for (Message message : messages) {
                System.out.println("Mensagem recebida do CONSUMIDOR AUXILIAR:");
                System.out.println("  Body: " + message.getBody());
                sqsConsumidorAux.deletarMensagem(message);
                sqsConsumidor.enviaMensagem(nome);
            }
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}