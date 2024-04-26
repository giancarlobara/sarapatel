package org.example;

public class Main {
    public static boolean CA;
    public static boolean CB;
    public static void main(String[] args) {
        CA = false;
        CB = false;
        ThreadB threadA = new ThreadB();
        ThreadA threadB = new ThreadA();
        threadA.start();
        threadB.start();
    }
}