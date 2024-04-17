package org.example;

import static org.example.Main.CA;
import static org.example.Main.CB;

public class ThreadB extends Thread{
    public void processamentoB(){
        for (int i =0;i<5;i++){
            CB = true;
            while (CA){}
            System.out.println("ENTRANDO: região critica B...");
            System.out.println("SAINDO: região critica B...");
            CB = false;
            System.out.println("EXCUTANDO:região NÃO critica B...");
        }
    }
    public void run() {
        this.processamentoB();
    }
}
