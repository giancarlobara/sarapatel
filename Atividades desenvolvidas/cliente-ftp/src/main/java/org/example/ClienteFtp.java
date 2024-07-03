package org.example;

import java.io.*;
import java.net.Socket;

public class ClienteFtp {
    private Socket cliente;
    private String endereco = "192.168.100.69";
    private int porta = 21;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String resposta;
    private String pastaArquivosParaEnviar = "src/main/resources/arquivos-para-enviar";
    private String pastaArquivosRecebidos = "src/main/resources/arquivos-recebidos";
    public ClienteFtp() {
        try {
            cliente = new Socket(endereco,porta);
            reader = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            mensagemServidor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void login(String usuario, String senha) throws IOException {
        sendCommand("USER " + usuario);
        mensagemServidor();

        // Envia o comando PASS para enviar a senha
        sendCommand( "PASS " + senha);
        mensagemServidor();
    }
    public void enviaArquivo(String filename) throws IOException {
        Socket dataSocket = new Socket(endereco, this.getPort());
        OutputStream outputStream = dataSocket.getOutputStream();
        sendCommand("STOR " + filename);
        String resp  = mensagemServidor();
        int codigo = pegaCodigo(resp);
        if(codigo >= 500 && codigo < 600){
            return;
        }
        File file = new File(pastaArquivosParaEnviar + '/' + filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        bufferedInputStream.close();
        outputStream.close();
        mensagemServidor();
        dataSocket.close();
    }
    public void listaArquivos() throws IOException {
        Socket dataSocket = new Socket(endereco, this.getPort());
        BufferedReader dataReader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
        sendCommand("LIST");
        String msg = mensagemServidor();
        int codigo = pegaCodigo(msg);
        if(codigo >= 500 && codigo < 600){
            return;
        }
        System.out.println("ARQUIVOS:");
        String line;
        while ((line = dataReader.readLine()) != null) {
            System.out.println(line);
        }
        mensagemServidor();
        dataReader.close();
        dataSocket.close();
    }
    public void listaComandos() throws IOException {
        sendCommand("HELP");
        mensagemServidor();
        System.out.println("COMANDOS DISPONIVEIS:");
        String line;
        while ((line = reader.readLine()) != null) {
            if(line.startsWith("214")){ return;}
            System.out.println(line);
        }
    }
    public void exibeDiretorioServidor() throws IOException {
        sendCommand("PWD");
        mensagemServidor();
    }
    public void mudaDiretorioServidor(String dir) throws IOException {
        sendCommand("CWD "+ dir);
        mensagemServidor();
    }
    public void recebeArquivo(String filename) throws IOException {
        Socket dataSocket = new Socket(endereco, this.getPort());
        InputStream dataInputStream = dataSocket.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(pastaArquivosRecebidos+ '/' + filename);
        sendCommand("RETR " + filename);
        String msg = mensagemServidor();
        int codigo = pegaCodigo(msg);
        if(codigo >= 500 && codigo < 600){
            return;
        }
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = dataInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }
        mensagemServidor();
        fileOutputStream.close();
        dataInputStream.close();
        dataSocket.close();


    }
    public void fechaConexao() throws IOException {
        sendCommand("QUIT");
        mensagemServidor();
        System.out.println("Fechando conexao");
        cliente.close();
    }
    private void sendCommand(String command) throws IOException {
        writer.write(command + "\r\n");
        writer.flush();
    }
    private int getPort() throws IOException {
        sendCommand("PASV");
        String response = reader.readLine();
        System.out.println("MENSAGEM DO SERVIDO: "+ response);
        // Extrai o número da porta do modo passivo (PASV)
        int startIndex = response.indexOf("(");
        int endIndex = response.indexOf(")");
        String portStr = response.substring(startIndex + 1, endIndex);
        String[] parts = portStr.split(",");
        int port = Integer.parseInt(parts[4]) * 256 + Integer.parseInt(parts[5]);
        return port;
    }
    private String mensagemServidor() throws IOException {
        resposta = reader.readLine();
        System.out.println("MENSAGEM DO SERVIDO: " + resposta);
        return resposta;
    }
    private Integer pegaCodigo(String resposta) throws IOException {
        Integer codigo = Integer.parseInt(resposta.substring(0,3));
        return codigo;
    }
}
