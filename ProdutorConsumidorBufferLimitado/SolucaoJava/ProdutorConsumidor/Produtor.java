package ProdutorConsumidor;

import java.util.ArrayList;

public class Produtor extends Thread{
	private String nome;
	private int dado;
	private Buffer buffer;
	
	public Produtor(String nome, Buffer buffer, int dado) {
		this.nome = nome;
		this.buffer = buffer;
		this.dado = dado;
	}

	public void run() {
		buffer.produzir(nome, dado);
	}

}
