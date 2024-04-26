package org.example;

public class Main {
    public static boolean CA;
    public static boolean CB;
    public static void main(String[] args) {
        CA = false;
        CB = false;
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA();
        threadB.start();
        threadA.start();
    }
}