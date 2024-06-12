
public class Teste {
	public static void main(String[] args) {
		Mailbox mailbox = new Mailbox();
	    Producer producer1 = new Producer(mailbox, "1", "Primeira produção");
	    Producer producer2 = new Producer(mailbox, "2","Segunda produção");
	    Consumer consumer1 = new Consumer(mailbox, "1");
	    Consumer consumer2 = new Consumer(mailbox, "2");
	 
	    producer1.start();
	    producer2.start();
	    consumer1.start();
	    consumer2.start();
	}
}
