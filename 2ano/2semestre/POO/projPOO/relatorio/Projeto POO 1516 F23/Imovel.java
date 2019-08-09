
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
      //É o construtor que contém todas as variáveis sobre o Imóvel tais como o tipo o idImovel, a rua, o preço, o preço mínimo, a view e o estado do imóvel.
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
   //Retornam as variáveis definidas anteriormente.
   public String getId() { return idImovel; }
   public String getRua() { return rua; }
   public double getPreco() { return preco; }
   public double getPrecoMin() { return precoMin; }
   public int getView() { return view; }
   public String getEstado() { return estado.toString(); }
   public Estado retEstado() { return estado; }
   public void incView() { this.view +=1; }
   public List<String> getComentarios() {return comentarios;}
   
   //Função que permite seleccionar um imóvel e fornece ao utilizador as características do imóvel.
   public void showImovel() {
       System.out.println("____________________"); // 20 underscores
       System.out.println("Rua: " + this.getRua());
       System.out.println("Preço: " + this.getPreco() + "€");     
   }
   public Imovel clone() {
       return new Imovel(this);
    }
   
   //Função auxiliar para a função setEstado.
   public void setEstadoAux(Estado e) { this.estado = e; } 
   
   //Função que fornece as datas para as consultas.
   public String getHoje() { 
       Calendar calendar = new GregorianCalendar();
       Date temp = new Date();
       calendar.setTime(temp);
       return "data: " + calendar.get(Calendar.DATE);
   }
   
   //Função que gera uma string de consulta.
   public void consulta(String nome) {
       String a = "CONSULTA:  " + nome + " - ID: " + this.getId() +"\nDia " + getHoje();
       this.comentarios.add(a);
       this.incView();
   }
   public String toString() {
       return this.getId() + " - " + this.retEstado();
    }
   
}





    

