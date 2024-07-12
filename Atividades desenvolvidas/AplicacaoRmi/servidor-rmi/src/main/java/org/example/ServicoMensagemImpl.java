package org.example;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class ServicoMensagemImpl extends UnicastRemoteObject implements ServicoMensagem{

    protected ServicoMensagemImpl() throws RemoteException {
    }

    @Override
    public String enviarMensagem(String mensagem) throws ServerNotActiveException {
        String cliente = getClientHost();
        System.out.println("Mensagem recebida do cliente: " + cliente);
        return "Mensagem recebida do cliente: " + mensagem;
    }
    @Override
    public String media(float n1, float n2,float n3) throws ServerNotActiveException {
        String cliente = getClientHost();
        System.out.println("Mensagem recebida do cliente: " + cliente);

        float media = (n1 + n2) / 2;
        String resposta;
        if(media >= 7) resposta = "Aprovado";
        else if(media > 3){
            float mm = (media+n3)/2;
            if(mm >= 5) resposta = "Aprovado";
            else resposta = "Reprovado";
        }else {
            resposta = "Reprovado";
        }
        return resposta;
    }
}
