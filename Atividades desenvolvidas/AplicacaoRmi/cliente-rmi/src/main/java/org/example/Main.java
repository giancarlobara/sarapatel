package org.example;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Registry registry = null;
        try {

            //  Busca objeto pelo endereço e porta e método
            registry = LocateRegistry.getRegistry("localhost", 1099);
            ServicoMensagem server = (ServicoMensagem) registry
                   .lookup("ServicoMensagem");

            //  Busca objeto por nome do método
            // server = (ServicoMensagem) Naming.lookup("ServicoMensagem");
            System.out.println(server.enviarMensagem("Testendo Conexão"));

            while (true){
                System.out.println("Digite as notas 1, 2 e 3 respectivamente:\n");
                Scanner scanner = new Scanner(System.in);
                float nota1 = Float.parseFloat(scanner.nextLine());
                float nota2 = Float.parseFloat(scanner.nextLine());
                float nota3 = Float.parseFloat(scanner.nextLine());
                System.out.println("Resultado:" + server.media(nota1,nota2,nota3));
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }
}