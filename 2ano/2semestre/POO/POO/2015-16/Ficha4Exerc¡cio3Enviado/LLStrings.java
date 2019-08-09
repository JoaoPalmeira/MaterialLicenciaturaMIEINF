import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;
import java.util.LinkedList;
/**
 * Escreva a descrição da classe LLStrings aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class LLStrings
{
   private Nodes primeiro;
   private Nodes ultimo;
   private int numNodes;
    
   public LLStrings(){
       primeiro = null;
       ultimo = null;
       numNodes = 0;
   }
    
   public LLStrings(LLStrings l2){
       primeiro = l2.getPrimeiro();
       ultimo = l2.getUltimo();
       numNodes = l2.getNumNodes();
   }
   
   public Nodes getPrimeiro(){
       return primeiro;
   }
   
   public Nodes getUltimo(){
       return ultimo;
   }
   
   public int getNumNodes(){
       return numNodes;
   }
   
   public void setPrimeiro (Nodes n) {
       this.primeiro = primeiro;
   }
   
   public void setUltimo (Nodes n) {
      this.ultimo = ultimo;
   }
   
   public void setNumNodes (Nodes n) {
      this.numNodes = numNodes;
   }
   
   public int tamanho (){
      return numNodes; 
   }
   
   public boolean vazia (){
      if (primeiro == null) return true;
      else return false;
   }
   
   private Nodes getNodes (int pos){
       Nodes atual = primeiro;
       for (int i=0; i<pos; i++) atual = atual.getProximo();
       return atual;
   }
   
   public void adicionar(String s){
       if (primeiro == null){
           Nodes n = new Nodes (s,primeiro);
           primeiro = n;
           if (numNodes == 0) ultimo = primeiro;
           numNodes ++;
       }
       else{
           Nodes n = new Nodes (s);
           ultimo.setProximo(n);
           ultimo = n;
           numNodes ++;
       }
   }
   
   public void inserir (int i, String s){
       Nodes n = new Nodes (s,null);
       if (primeiro == null){
           primeiro = n;
           numNodes ++;
       }
       if (i<=0){
           primeiro = n;
           numNodes ++;
       }
       if (i>=numNodes){
           adicionar(s);
           numNodes ++;
       }
       else{
           Nodes anterior = getNodes(i-1);
           Nodes novo = new Nodes (s,anterior.getProximo());
           anterior.setProximo(novo);
           numNodes ++;
       }
   }
   
   public String get (int i){
       if (i>=0 && i<=numNodes) return getNodes(i).getConteudo();
       else return null;
   }
   
   public void esvaziar(){
      primeiro = null; 
      ultimo = null;
      numNodes = 0;
   }
   
   public LLStrings clone(){
      return new LLStrings (this);
    }

   public boolean equals(Object o){
      if(o == this){
            return true;
      }
      if ((o == null) || o.getClass() != this.getClass()){
            return false;
      }
      else{
          Nodes atual = primeiro;
          while (atual!=null){
              if (atual.getConteudo().equals(o))return true;
              atual = atual.getProximo();
          }
          return false;
      }
   }    
    
   public String toString(){
      if (primeiro == null) return "Primeiro: null";
      StringBuilder s = new StringBuilder ();
      Nodes atual = primeiro;
      s.append("Primeiro: " + atual.getConteudo() + ", ");
      int i;
      for (i = 0; i < numNodes - 1; i++){
          atual = atual.getProximo();
          s.append("Nodo: " + atual.getConteudo() + ", ");
      }
      s.append("Ultimo: " + atual.getConteudo());
      s.append(".");
      return s.toString();
   }
}