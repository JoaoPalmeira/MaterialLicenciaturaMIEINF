
/**
 * Esta classe simula uma mesa de apostas de um casino.
 * Para cada ponto, que neste caso é um Ponto2D, guarda-se a 
 * lista das pessoas que apostaram nesse ponto.
 * 
 * Pretende-se ter um mapeamento de:
 * Ponto2D -> Lista Apostadores
 * 
 * Internamente a estrutura de dados que foi escolhida para guardar 
 * esta informação é um TreeMap, neste caso, um TreeMap<Ponto2D, ArrayList<String>>.
 * 
 * Por questões de simplificação, para os apostadores apenas se guarda o 
 * seu nome (uma String).
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class TabuleiroApostas {

  private TreeMap<Ponto2D,ArrayList<String>> apostas;
  
  /**
   * Construtor por omissão.
   * Apenas cria o objecto TreeMap, mas deve parametrizar o Map com a capacidade
   * de comparar as chaves, que neste caso são Ponto2D.
   * Caso não fosse passado o comparador de Ponto2D não seria possível inserir mais do
   * que um Ponto2D.
   */
  public TabuleiroApostas() {
    this.apostas = new TreeMap<Ponto2D,ArrayList<String>>(new Ponto2DComparator());  
  }    
  
  /**
   * Construtor que aceita como parâmetro um Map com relações já preenchidas.
   */
  public TabuleiroApostas(Map<Ponto2D,ArrayList<String>> infoApostas) {
    this.apostas = new TreeMap<Ponto2D,ArrayList<String>>(new Ponto2DComparator());
    
    for(Ponto2D p: infoApostas.keySet()) {
      ArrayList<String> linha = infoApostas.get(p);
      ArrayList<String> novaLinha = new ArrayList<String> ();
      novaLinha.addAll(linha); //são Strings, logo não é preciso clone
      this.apostas.put(p.clone(),novaLinha);                                         
    }
  }
  
  /**
   * Construtor de cópia.
   */
  
  public TabuleiroApostas(TabuleiroApostas tab) {
    this.apostas = tab.getApostas();
  }
  
  
  public TreeMap<Ponto2D,ArrayList<String>> getApostas() {
    TreeMap<Ponto2D,ArrayList<String>> aux = 
           new TreeMap<Ponto2D,ArrayList<String>>(new Ponto2DComparator());
           
    for(Ponto2D p: this.apostas.keySet()) {
      ArrayList<String> linha = this.apostas.get(p);
      ArrayList<String> novaLinha = new ArrayList<String> ();
      novaLinha.addAll(linha); //são Strings, logo não é preciso clone
      aux.put(p.clone(),novaLinha);                                         
    }      
    return aux;
  }    
   
  
   /**
    * Método que insere mais um nome de apostador num determinado ponto.
    * Se o ponto fornecido não existir no TreeMap, cria uma nova relação no TreeMap.
    * Caso contrário acrescenta à lista de apostadores.
    */
  
   public void insereApostador(Ponto2D p, String apostador) {
     if (this.apostas.containsKey(p)) { //se o ponto já existe no MAP
       ArrayList<String> linha = this.apostas.get(p);
       if (!linha.contains(apostador)) { //se fosse um Set não teriamos de fazer este teste
         linha.add(apostador);
         this.apostas.put(p.clone(),linha);
       }
     else {
       ArrayList<String> aux = new ArrayList<String>();
       aux.add(apostador);
       this.apostas.put(p.clone(),aux);
     }   
   }
 } 
 
 
   /**
    * Determinar se o ponto existe.
    * 
    */

    public boolean existePonto(Ponto2D p) {
      return this.apostas.containsKey(p);   
    }
    
    
    /** 
     * Determinar quantos apostadores estão associados a um ponto.
     * Tem como pré-condição que o ponto exista (ver método existePonto(Ponto2D)).
     */
    
    public int qtApostadoresPorPonto(Ponto2D p) {
        return (this.apostas.get(p)).size();
    }    
    
    //outros métodos
    
    /**
     * toString, equals e clone
     * 
     */
}