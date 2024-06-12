
public class Consumer extends Thread{
	private Mailbox mailbox;
	private String identificador;
	
	public Consumer(Mailbox mailbox,String identificador){
		this.mailbox = mailbox;
		this.identificador = identificador;
	}
	public void run() {
        for (int i = 0; i < 3; i++) {
            mailbox.retrieveMessage(identificador);
        }
    }
}
