import java.util.concurrent.Semaphore;


public class Processador extends Thread{
	private int id;
    private Semaphore semaforo;
    
    public Processador(int id, Semaphore semaforo){
    	this.id = id;
    	this.semaforo = semaforo;
    }
    
    private void processar(){
    	try{
    		System.out.println("Thread #" + id + " processando");
    		Thread.sleep((long) (Math.random() *10000));
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    private void regiaoCritica(){
    	System.out.println("A Thread #" + id + " está entrando em região crítica");
        processar();
        System.out.println("A Thread #" + id + " está saindo da região crítica");
    }
    
    private void regiaoNaoCritica(){
    	System.out.println("A Thread #" + id + " está na região não crítica");
        processar();   
    }
    
    public void run() {
    	regiaoNaoCritica();
        try {
            semaforo.acquire();
            regiaoCritica();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }
    

}
