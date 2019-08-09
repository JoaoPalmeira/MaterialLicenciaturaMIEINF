
/**
 * Classe Imovel: definição das funções de tratamento e criação de Imóveis para venda.
 */

import java.lang.*;
import java.util.*;
import java.io.*;

public class Imovel implements Serializable
{
    /**
     * Variáveis de Instância
     */
   private String idImovel, rua;
   private double preco, precoMin;
   private int view;
   private Estado estado;
   public List<String> comentarios;
      //construtor
   public Imovel (String i, String r, double p, double pm, int v, Estado e) {
       idImovel = i;
       rua = r;
       preco = p;
       precoMin = pm;
       view = v;
       estado = e;
       comentarios = Collections.<String>emptyList();
   }    
   public Imovel () { 
       this ("","",0.0,0.0,0,Estado.VENDA);
       comentarios = Collections.<String>emptyList();
   }
   public Imovel (Imovel c) {
       this.idImovel = c.getId();
       this.rua = c.getRua();
       this.preco = c.getPreco();
       this.precoMin = c.getPrecoMin();
       this.view = c.getView();
       this.estado = c.retEstado();
       this.comentarios = Collections.<String>emptyList();
    }
   // Métodos de Instância
   public String getId() { return idImovel; }
   public String getRua() { return rua; }
   public double getPreco() { return preco; }
   public double getPrecoMin() { return precoMin; }
   public int getView() { return view; }
   public String getEstado() { return estado.toString(); }
   public Estado retEstado() { return estado; }
   public void incView() { this.view +=1; }
   public List<String> getComentarios() {return comentarios;}
   
   public void showImovel() {
       System.out.printf("\n\n____________________\n"); // 20 underscores
       System.out.println("Rua: " + this.getRua());
       System.out.println("Preço: " + this.getPreco() + "€");     
   }
   public Imovel clone() {
       return new Imovel(this);
    }
   public void setEstadoAux(Estado e) { this.estado = e; } 
   
   public String getHoje() { // alterar
       Scanner input = new Scanner(System.in);
       int d,m;
       String a;
       //Falta verificar se são válidos;
       System.out.print("Dia? ");
       d = input.nextInt();
       System.out.print("Mês? ");
       m = input.nextInt();
       a = d + "/" + m + "\n";
       return a;
   }
   public void consulta(String nome) {
       String a = "CONSULTA:  " + nome + " - ID: " + this.getId();
       this.comentarios.add(a);
       this.incView();
   }
  
   
}





    

