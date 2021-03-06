
/**
 * Classe que representa um conjunto de Ponto2D.
 * Internamente os diversos pontos s�o mantidos num HashSet<Ponto2D>
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.HashSet;
import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.util.stream.Collectors;

public class ConjuntoPontos_Hash {

  private Set<Ponto2D> pontos;
  private String nomeConj;
  
  public ConjuntoPontos_Hash() {
    this.pontos = new HashSet<Ponto2D>();
    this.nomeConj = "";
  }
  
  public ConjuntoPontos_Hash(String nome) {
    this.pontos = new HashSet<Ponto2D>();
    this.nomeConj = nome;
  }
  
  public ConjuntoPontos_Hash(Collection<Ponto2D> novosPontos) {
    this.pontos = new HashSet<Ponto2D>();
    this.nomeConj = "";
    
    // 1)
    //for (Ponto2D p: novosPontos)
    //  this.pontos.add(p.clone());
    
    // 2)
    this.pontos = novosPontos.stream()
                              .map(Ponto2D::clone)
                              .collect(Collectors.toSet());
    // 3)
    //this.pontos.addAll(novosPontos); --> deixo de ter encapsulamento garantido.  
  }
  
  
  public ConjuntoPontos_Hash(ConjuntoPontos_Hash umConj) {
    this.pontos = umConj.getPontos();
    this.nomeConj = umConj.getNomeConj();
  }  
  
  
  /**
   * Devolve o valor do nome do conjunto de pontos
   */
  public String getNomeConj() {
    return this.nomeConj;
  }

  /**
   * Devolve um conjunto de Ponto2D. Para respeitar o princ�pio do encapsulamento
   * os objectos contidos s�o clonados.
   */

  public HashSet<Ponto2D> getPontos() {
    // 1)
    //HashSet<Ponto2D> novo = new HashSet<Ponto2D>();
    //for(Ponto2D p: this.pontos)
    // novo.add(p.clone());
    //return novo;
    
    // 2)
    return (HashSet<Ponto2D>)this.pontos.stream()
                                        .map(Ponto2D::clone)
                                        .collect(Collectors.toSet());
  }
  
  //outros m�todos
  
  /**
   * Insere um novo Ponto2D no conjunto.
   * Esta classe guarda o ponto no HashSet.
   */
  
  public void inserePonto(Ponto2D p) {
    this.pontos.add(p.clone());
  }  
  
  /**
   * Devolve o n�mero de pontos que est�o guardados.
   * 
   */
  
   public int numPontos() {
     return this.pontos.size();
   }
   
   /**
    * Verifica se um determinado Ponto2D existe neste conjunto.
    * 
    */
  
    public boolean existePonto(Ponto2D p) {
      return this.pontos.contains(p);
    }  
    
    /**
     * Conta o n�mero de pontos at� encontrar um ponto com 
     * coordenada X igual ao par�metro.
     * 
     * M�todo com utiliza��o de Iterator, para percorrer o 
     * HashSet at� encontrar um ponto com X = valor passado como
     * par�metro.
     */
     
     public int numPontosAntesCoord(double coordX) {
         int numPontos = 0;
         boolean stop = false;
         Iterator<Ponto2D> i = this.pontos.iterator();
         while(i.hasNext() && !stop) {
            if ((i.next().getX()) != coordX)
              numPontos++;
            else
              stop = true;
         }
         
         return numPontos;
     }   
             
     /**
      * Altera a coordenada X de todos os pontos 
      * que est�o no conjunto.
      * 
      */
     
     public void alteraTodosPontos(double incremento) {
       // 1)
       //for(Ponto2D p: this.pontos) 
       //  p.incCoord(incremento,0); // incremento 0 para a coord Y
       
       // 2)
       this.pontos.forEach(p->p.incCoord(incremento,0));  
         
     }    
    
    
    
  // toString, equals e clone
  /**
   * Vers�o simplista, e n�o muito eficiente, do m�todo toString.
   * Deveria ser reescrito com base num StringBuffer.
   */
  
  public String toString() {
    return "ConjuntoPontos: "+ this.nomeConj + "Elementos: " + this.pontos.toString();
  }  
  
  
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    else {
      ConjuntoPontos_Hash conj = (ConjuntoPontos_Hash) o;
      return this.nomeConj.equals(conj.getNomeConj()) && 
             this.pontos.equals(conj.getPontos());
    }
  }  
  
  
  public ConjuntoPontos_Hash clone() {
    return new ConjuntoPontos_Hash(this);
  }
  
}
