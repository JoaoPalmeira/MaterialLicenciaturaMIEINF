/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd_guiao2;

/**
 *
 * @author joaopalmeira
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
