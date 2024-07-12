package org.example;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {
    public static void main(String[] args) {
        try {
            ServicoMensagem server = new ServicoMensagemImpl();
//            Naming.rebind("//localhost/media",server);
//            System.err.println("Server ready");
//            ServicoMensagem stub = (ServicoMensagem) UnicastRemoteObject
//                    .exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ServicoMensagem", server);
            System.out.println("Servidor rodando!");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}