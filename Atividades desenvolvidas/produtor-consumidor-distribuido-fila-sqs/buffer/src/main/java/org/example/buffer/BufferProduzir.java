package org.example.buffer;

import com.amazonaws.services.sqs.model.Message;
import org.example.SQS;

import java.util.List;

public class BufferProduzir extends Thread{
    private Buffer buffer;
    public BufferProduzir(Buffer buffer){
        this.buffer = buffer;
    }
    public void run(){
        SQS sqsProdutor = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/produtor.fifo","grupo-1");
        SQS sqsConsumidorAux = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/aux-consumidor.fifo", "grupo-1");
        while(true){
            System.out.println("PRODUTOR: Verificando mensagens na fila...");
            List<Message> messages = sqsProdutor.recuperaMensagem();

            for (Message message : messages) {
                System.out.println("Mensagem recebida:");
                System.out.println("  Body: " + message.getBody());
                    buffer.produzir(message.getBody(), 1);
                    sqsProdutor.deletarMensagem(message);
                    sqsConsumidorAux.enviaMensagem("CONSUMIDOR AUXILIAR");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
