package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int vez;
    public static boolean[] threads = new boolean[3];
    public static void main(String[] args) {

        Peterson peterson = new Peterson();
        MinhaThread[] threads = new MinhaThread[3];

        for (int i = 0; i < 3; i++) {
            threads[i] = new MinhaThread(peterson, i);
            threads[i].start();
        }
    }
}