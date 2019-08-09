
/**
 * Classe Imovel: definição das funções de tratamento e criação de Imóveis para venda.
 */

import java.lang.String;
import java.lang.Boolean;
import java.util.Scanner;


public class Imovel
{
    /**
     * Variáveis de Instância
     */
   public String idImovel, rua;
   public double preco, precoMin;
   public int view;
   public Estado estado;
      //construtor
   public Imovel (String i, String r, double p, double pm, int v, Estado e) {
       idImovel = i;
       rua = r;
       preco = p;
       precoMin = pm;
       view = v;
       estado = e;
   }    
   public Imovel () { this ("","",0.0,0.0,0,Estado.VENDA); }
   public Imovel (Imovel c) {
       this.idImovel = c.getId();
       this.rua = c.getRua();
       this.preco = c.getPreco();
       this.precoMin = c.getPrecoMin();
       this.view = c.getView();
       this.estado = c.getEstado();
    }
   // Métodos de Instância
   public String getId() { return idImovel; }
   public String getRua() { return rua; }
   public double getPreco() { return preco; }
   public double getPrecoMin() { return precoMin; }
   public int getView() { return view; }
   public String getEstado() { return estado.toString(); }
   public void incView() { this.view +=1; }
   
   public void showImovel() {
       System.out.printf("\n\n____________________\n"); // 20 underscores
       System.out.println("Rua: " + this.getRua());
       System.out.println("Preço: " + this.getPreco() + "€");
       //System.out.printf("____________________\n");       
       
   }
   public void setEstadoAux(Estado e) { this.estado = e; } 
   public Imovel clone () {
       return new Imovel(this);
   }
   
}





    

