package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface ServicoMensagem extends Remote {
    String enviarMensagem(String mensagem) throws RemoteException, ServerNotActiveException;
    String media(float n1, float n2,float n3) throws RemoteException, ServerNotActiveException;
}
