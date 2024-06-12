
public class Producer extends Thread{
	private Mailbox mailbox;
	private String identificador;
	private String mensagem;
	
	public Producer(Mailbox mailbox,String identificador,String mensagem){
		this.mailbox = mailbox;
		this.identificador = identificador;
		this.mensagem = mensagem;
	}
	public void run() {
        for (int i = 0; i < 3; i++) {
            mailbox.storeMessage(mensagem,identificador);
        }
    }
}
