package org.example;

public class Main {
    public static char vez;
    public static void main(String[] args) {
        vez = 'A';
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA();
        threadB.start();
        threadA.start();
    }
}