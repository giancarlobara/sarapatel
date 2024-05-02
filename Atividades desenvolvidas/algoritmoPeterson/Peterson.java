package org.example;

import static org.example.Main.threads;
import static org.example.Main.vez;

public class Peterson {
    public Peterson(){
        for (int i = 0; i < 3; i++) {
            threads[i] = false;
        }
    }
    public void entraRegiaoCritica(int id){
        int other1 = (id + 1) % 3;
        int other2 = (id + 2) % 3;
        threads[id] = true;
        vez = id;
        while (vez == id && (threads[other1] || threads[other2])){}
    }
    public void saiRegiaoCritica(int id) {
        System.out.println("Thread " + id + " SAIU da seção crítica.");
        threads[id] = false;
    }
}
