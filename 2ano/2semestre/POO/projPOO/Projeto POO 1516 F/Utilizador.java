
/**
 * Classe Utilizador: definição dos dois tipos interventivos na compra e 
 * venda de Imóveis.
 */

import java.lang.*;
import java.util.*;
import java.io.*;

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
        nasc = na; // recebe uma data do tipo DDMMAAAA;
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
    
    // Falta tratamento de erros!
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
        System.out.print(this.getNome() + " - " + this.getNasc() + "\nE-mail: " + this.getMail());
    }     
    public void adicionaFavorito(Imovel im) {} // comprador
    public void registaPVenda(Imovel im) {}
   // public void getFavoritos() {}
}


    
// public class Vendedor extends Utilizador
    
