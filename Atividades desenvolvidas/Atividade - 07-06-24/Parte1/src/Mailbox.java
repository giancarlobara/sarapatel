
public class Mailbox {
	
	private String message = null;
	
	public synchronized void storeMessage(String valor, String identificador) {
		System.out.println("Produtor "+ identificador +" irá tentar produzir");
        while (message != null) {
            try {
                System.out.println("Produtor "+ identificador +" esperando mensagem ficar vazia");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        message = valor;
        System.out.println("Mensagem adicionada pelo produtor " + identificador + ": " + valor);
        notifyAll();
	}
	
	public synchronized void retrieveMessage(String identificador) {
		System.out.println("Consumidor "+ identificador +" irá tentar consumir");
        while (message == null) {
            try {
                System.out.println("Consumidor "+ identificador +" esperando mensagem ficar cheia");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("Mensagem a ser removida pelo consumidor " + identificador +": " +  message);
        message = null;
        System.out.println("Mensagem Vazia");
        notifyAll();
	}
}
