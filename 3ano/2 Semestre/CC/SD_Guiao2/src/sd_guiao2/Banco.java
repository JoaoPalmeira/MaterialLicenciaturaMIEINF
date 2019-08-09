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
public class Banco {
    private double[] contas;
	
	Banco(int numContas){
		contas = new double[numContas];
		for(int i=0; i<numContas; i++){
			contas[i]=0;
		}	
	}
	
	public double consultar(int nr_conta){
		return this.contas[nr_conta];
	}
	
	public synchronized void levantar(int nr_conta, double valor){
		this.contas[nr_conta] = this.contas[nr_conta] - valor;
	}
	
	
	public synchronized void depositar(int nr_conta, double valor){
		this.contas[nr_conta] = this.contas[nr_conta] + valor;
	}
}
