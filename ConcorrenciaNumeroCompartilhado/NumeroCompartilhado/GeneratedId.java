package NumeroCompartilhado;
public class GeneratedId implements Runnable {
	
	private String name;
	private int id;
	
	GeneratedId(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getGeneratedId(){
		return ++ this.id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public void run() {
		int cont = 1;
		while (cont < 10) {
			System.out.println(getName()+" - "+getGeneratedId());
			cont++;
		}
	}
}
