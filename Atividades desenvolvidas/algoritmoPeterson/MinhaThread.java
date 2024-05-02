package org.example;

public class MinhaThread extends Thread{
    private Peterson peterson;
    private int id;
    private int tempoEspera;

    public MinhaThread(Peterson peterson, int id) {
        this.peterson = peterson;
        this.id = id;
        this.tempoEspera = tempoEspera;
    }

    public void run() {
        for(int i = 0;i<5;i++) {
            System.out.println("Thread " + id + " VAI entrar na seção crítica.");
            peterson.entraRegiaoCritica(id);
            System.out.println("Thread " + id + " ESTA na seção crítica.");
            // Seção crítica

            peterson.saiRegiaoCritica(id);

        }
    }
}
