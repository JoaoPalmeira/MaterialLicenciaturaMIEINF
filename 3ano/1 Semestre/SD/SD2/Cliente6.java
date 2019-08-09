
/**
 * Escreva a descrição da classe Cliente6 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente6 implements Runnable {
	
	private Banco3 banco;

	public Cliente6(Banco3 b){
		this.banco=b; 
	}
	
	@Override
	public void run() {
		System.out.println("Cliente 2: Consulta iniciada");
		this.banco.consultar(1);
		System.out.println("Cliente 2: Consulta realizada" + this.banco.consultar(1));
		
	} 

}
