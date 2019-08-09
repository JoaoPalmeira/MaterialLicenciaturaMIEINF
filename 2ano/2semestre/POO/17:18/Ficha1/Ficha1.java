
/**
 * Exercicios II da ficha 1
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.Scanner;

public class Ficha1{
    
    /**
     * Método que dado um valor em graus Celsius calcula
     * o correspondente em graus Farenheit
     * 
     * @param grausC valor em graus Celsius
     */
    public double celsiusParaFarenheit(double grausC){
        return grausC * 9/5 + 32;
    }
    
    public int maximoNumeros(int a, int b){
        if(a>=b) return a;
        else return b;
    }
    
    public String criaDescricaoConta(String nome, double saldo){
        Scanner nomes = new Scanner(nome);
        String vai = nomes.next();
        
    }
}
