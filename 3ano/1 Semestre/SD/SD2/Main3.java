
/**
 * Escreva a descrição da classe Main3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Main3 {

	public static void main3(String[] args) {
		Banco2 b = new Banco2(10);
		
		Thread t1=new Thread(new Cliente3(b));
		Thread t2=new Thread(new Cliente4(b));
		
		b.depositar(0, 1000);
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("Valor Conta 0 é: " + b.consultar(0));
		System.out.println("Valor Conta 1 é: " + b.consultar(1));
	}

}
