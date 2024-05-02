import java.util.concurrent.Semaphore;


public class Principal {
	
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		Processador[] processos = new Processador[8];
		for (int i = 0; i < 8; i++) {
	        processos[i] = new Processador(i, semaphore);
	        processos[i].start();
	    }
		
	}

}
