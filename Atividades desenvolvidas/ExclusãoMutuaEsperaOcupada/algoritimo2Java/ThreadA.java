package org.example;


import static org.example.Main.CA;
import static org.example.Main.CB;

public class ThreadA extends Thread{
    public void processamentoA(){
        for (int i =0 ; i<5;i++){
            while (CB){}
            CA = true;
            System.out.println("ENTRANDO: região critica A...");
            System.out.println("SAINDO: região critica A...");
            CA = false;
            System.out.println("EXCUTANDO:região NÃO critica A...");
        }
    }
    public void run() {
        this.processamentoA();
    }

}