package org.example;

import static org.example.Main.vez;

public class ThreadA extends Thread{
    public void processamentoA(){
        for(int i = 0;i<5;i++){
            while (vez == 'A'){}
            System.out.println("ENTRANDO: região critica A...");
            System.out.println("SAINDO: região critica A...");
            vez='A';
            System.out.println("EXCUTANDO:região NÃO critica A...");

        }
    }
    public void run() {
        this.processamentoA();
    }
}
