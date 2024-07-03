package org.example.buffer;
import org.example.SQS;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(3);
        SQS sqsProdutorAux = new SQS("https://sqs.sa-east-1.amazonaws.com/058264270021/aux-produtor.fifo", "grupo-1");
        for (int i = 0; i < 3; i++) {
            sqsProdutorAux.enviaMensagem("PRODUTOR");
        }
        BufferProduzir produzir = new BufferProduzir(buffer);
        produzir.start();
        BufferConsumir consumir = new BufferConsumir(buffer);
        consumir.start();
    }
}
