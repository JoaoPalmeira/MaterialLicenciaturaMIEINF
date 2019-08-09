import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;
import java.util.LinkedList;
/**
 * Escreva a descrição da classe Nodes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Nodes
{ 
   private String conteudo;
   private Nodes proximo;

   public Nodes(){
       conteudo = "";
       proximo = null;
   }
    
   public Nodes (String cont){
       conteudo = cont;
   }
   
   public Nodes (String cont, Nodes prxm){
       conteudo = cont;
       proximo = prxm;
   }
   
   public Nodes(Nodes n2){
       conteudo = n2.getConteudo();
       proximo = n2.getProximo();
   }
   
   public String getConteudo(){
       return conteudo;
   }
   
   public Nodes getProximo(){
       return proximo;
   }
   
   public void setConteudo (String cont) {
       this.conteudo = cont;
   }
   
   public void setProximo (Nodes prxm) {
      this.proximo = prxm;
   }
   
   public Nodes clone(){
      return new Nodes (conteudo, proximo);
   }

   public boolean equals(Object o){
      if(o == this){
            return true;
      }
      if ((o == null) || o.getClass() != this.getClass()){
            return false;
      }
      else{
         Nodes n = (Nodes) o;
         return (n.getConteudo().equals(conteudo) && n.getProximo() == proximo);
      }
   }    
    
   public String toString(){
      StringBuilder s = new StringBuilder ();
      s.append ("Conteudo: " + conteudo + " ");
      s.append ("Proximo: " + proximo + " ");
      return s.toString();
   }
}