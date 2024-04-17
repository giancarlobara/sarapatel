package org.example;

import static org.example.Main.vez;

public class ThreadB extends Thread{
    public void processamentoB(){
        for(int i = 0;i<5;i++){
            while (vez == 'B'){}
            System.out.println("ENTRANDO: região critica B...");
            System.out.println("SAINDO: região critica B...");
            vez='B';
            System.out.println("EXCUTANDO:região NÃO critica B...");
        }
    }
    public void run() {
        this.processamentoB();
    }
}
