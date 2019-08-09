
/**
 * Classe Utilizador: definição dos dois tipos interventivos na compra e 
 * venda de Imóveis.
 */

import java.lang.*;
import java.util.*;
import java.io.*;
import java.text.*;

public abstract class Utilizador implements Serializable
{
    // Variáveis de Instância:
    private String mail, nome, password, morada;
    private int nasc; 
    
    // Construtores:
    public Utilizador(String m, String n, String p, String mo, int na) {
        mail = m;
        nome = n;
        password = p;
        morada = mo;
        nasc = na; // recebe uma data do tipo AAAAMMDD;
    }
    public Utilizador() { this("","","","",0); }
    public Utilizador(Utilizador act) {
        mail = act.getMail();
        nome = act.getNome();
        password = act.getPass();
        morada = act.getMorada();
        nasc = act.getNasc();
               
    }
    
    // Métodos de Instância
    public String getMail() { return mail; }
    public String getNome() { return nome; }
    public String getPass() { return password; }
    public String getMorada() { return morada; }
    public int getNasc() { return nasc; }
    
    // Métodos Set:
    private void setMail(String m) { 
        this.mail = m;
        System.out.println("Alteração efetuada com sucesso.");
    }
    private void setNome(String n) { 
        this.nome = n; 
        System.out.println("Alteração efetuada com sucesso.");
    }
    private void setPass(String p) { 
        this.password = p; 
        System.out.println("Alteração efetuada com sucesso.");
    }
    private void setMorada (String m) { 
        this.morada = m;
        System.out.println("Alteração efetuada com sucesso.");
    }
    private void setNasc (int n) {
        this.nasc = n;
        System.out.println("Alteração efetuada com sucesso.");
    }
    public void showUtilizador() {
        System.out.println("\n____________________");
        System.out.println(this.getNome() + " - " + this.getNasc() + "\nE-mail: " + this.getMail());
    }
    public String toString() {
        return this.getNome() + " " + this.getMail();
    }
    /** 
     * Transforma um inteiro numa data. O inteiro não é verificado.
     */
    public Date intToDate() {
        int val = this.getNasc();
        int ano = val / 10000;
        int mes = ((val % 1000) /100)-1;
        int dia = val %100;
        Date nascim = new GregorianCalendar(ano,mes,dia).getTime();
        return nascim;
    }
    
    public void aniversario() {
        Date temp = this.intToDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data de Nascimento " + formato.format(temp));
    }
    public void adicionaFavorito(Imovel im) {} // comprador
    public void registaPVenda(Imovel im) {}
   // public void getFavoritos() {}
}


    
// public class Vendedor extends Utilizador
    
