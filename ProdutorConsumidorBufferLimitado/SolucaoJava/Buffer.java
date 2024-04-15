package ProdutorConsumidor;

import java.util.ArrayList;

public class Buffer {
	private int capacidade;
	private ArrayList<Integer> buffer = new ArrayList<Integer>();
	
	public Buffer(int capacidade){
		this.capacidade = capacidade;
	}
	
	public synchronized void produzir(String produtor, int dado){
		while(buffer.size() == capacidade){
			try {
				System.out.println("Buffer cheio. O produtor " + produtor + 
						" está esperado algum dado ser consumido");
				wait();
			} catch (InterruptedException e) {
				System.out.println("Cansei de esperar");
			}
		}
		buffer.add(dado);
		System.out.println("O produtor " + produtor + " produziu o dado: " + dado);
		notifyAll();
	}
	
	public synchronized void consumir(String consumidor){
		while(buffer.size() == 0){
			try {
				System.out.println("Buffer vazio. O Consumidor " + consumidor + 
						" está esperado algum dado ser produzido");
				wait();
			} catch (InterruptedException e) {
				System.out.println("Cansei de esperar");
			}
		}
		System.out.println("O Consumidor " + consumidor +
				" consumiu o dado: " + buffer.get(buffer.size()-1));
		buffer.remove(buffer.size()-1);
		notifyAll();
	}
}
