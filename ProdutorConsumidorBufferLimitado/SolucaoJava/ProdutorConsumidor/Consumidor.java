package ProdutorConsumidor;

import java.util.ArrayList;

public class Consumidor extends Thread {
	
	private String nome;
	private Buffer buffer;

	public Consumidor(String nome, Buffer buffer) {
		this.nome = nome;
		this.buffer = buffer;
	}

	public void run() {
		buffer.consumir(nome);
	}

}
