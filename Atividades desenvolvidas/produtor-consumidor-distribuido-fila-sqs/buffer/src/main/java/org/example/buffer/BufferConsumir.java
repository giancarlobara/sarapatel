package org.example.buffer;

import com.amazonaws.services.sqs.model.Message;
import org.example.SQS;

import java.util.List;

public class BufferConsumir extends Thread{
    private Buffer buffer;
    public BufferConsumir(Buffer buffer) {
        this.buffer = buffer;
    }
    public void run() {
        SQS sqsConsumidor = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/consumidor.fifo","grupo-1");
        SQS sqsProdutorAux = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/aux-produtor.fifo", "grupo-1");

        while(true) {
            System.out.println("CONSUMIDOR: Verificando mensagens na fila...");
            List<Message> messageList = sqsConsumidor.recuperaMensagem();
            for (Message message : messageList) {
                System.out.println("Mensagem recebida:");
                System.out.println("  Body: " + message.getBody());
                buffer.consumir(message.getBody());
                sqsConsumidor.deletarMensagem(message);
                sqsProdutorAux.enviaMensagem("PRODUTOR AUXILIAR");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
