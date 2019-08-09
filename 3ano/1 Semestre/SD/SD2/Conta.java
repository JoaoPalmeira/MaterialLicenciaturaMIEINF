
/**
 * Escreva a descrição da classe Conta aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Conta {

	private double valor;
	
	Conta(){
		this.valor=0;
	}
	
	public double consultar(){
		return this.valor;
	}
	
	public void depositar(double valor){
		this.valor=this.valor+valor;
	}
	
	public void levantar(double valor){
		this.valor=this.valor-valor;
	}
	
}
