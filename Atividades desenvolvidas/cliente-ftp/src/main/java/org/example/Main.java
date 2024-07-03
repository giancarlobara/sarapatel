package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        ClienteFtp cliente = new ClienteFtp();
        cliente.login("sarapatel","sarapatel");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String leitura;
        while (true){
            System.out.println("Escolha:\n 1.Enviar um arquivo\n 2.Receber um arquivo\n 3.Listar arquivos\n 4.Listar comandos disponiveis\n 5.Exibir diretorio dos arquivos no servidor\n 6.Mudar diretorio no servidor\n0.Sair");
            leitura = br.readLine();
            int i = Integer.parseInt(leitura);
            try {
                switch (i){
                    case 0: cliente.fechaConexao(); return;
                    case 1: cliente.enviaArquivo(br.readLine()); break;
                    case 2: cliente.recebeArquivo(br.readLine()); break;
                    case 3: cliente.listaArquivos(); break;
                    case 4: cliente.listaComandos(); break;
                    case 5: cliente.exibeDiretorioServidor(); break;
                    case 6: cliente.mudaDiretorioServidor(br.readLine());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
