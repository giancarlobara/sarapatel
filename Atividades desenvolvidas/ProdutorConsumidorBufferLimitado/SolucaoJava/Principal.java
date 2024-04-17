package ProdutorConsumidor;

public class Principal {
	public static void main(String[] args) {
		Buffer buffer = new Buffer(3);
		
		Produtor produtor1 = new Produtor("L", buffer, 1);
		Produtor produtor2 = new Produtor("M", buffer, 2);
		Produtor produtor3 = new Produtor("N", buffer, 3);
		Produtor produtor4 = new Produtor("O", buffer, 4);
		Produtor produtor5 = new Produtor("P", buffer, 5);
		Consumidor consumidor1 = new Consumidor("A", buffer);
		Consumidor consumidor2 = new Consumidor("B", buffer);
		Consumidor consumidor3 = new Consumidor("C", buffer);
		Consumidor consumidor4 = new Consumidor("D", buffer);
		Consumidor consumidor5 = new Consumidor("E", buffer);
		Consumidor consumidor6 = new Consumidor("F", buffer);
		
		produtor1.start();
		produtor2.start();
		produtor3.start();
		produtor4.start();
		produtor5.start();
		
		consumidor1.start();
		consumidor2.start();
		consumidor3.start();
		consumidor4.start();
		consumidor5.start();
		consumidor6.start();
	
		
	}
}
