package NumeroCompartilhado;

public class Main {
	public static void main(String[] args) {
		GeneratedId id = new GeneratedId(1,"Thread 1");
		GeneratedId id2 = new GeneratedId(1, "Thread 2");
		new Thread(id).start();
		new Thread(id2).start();
	}
}