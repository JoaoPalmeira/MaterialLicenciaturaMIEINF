
/**
 * Esta classe simula uma mesa de apostas de um casino.
 * Para cada ponto, que neste caso � um Ponto2D, guarda-se a 
 * lista das pessoas que apostaram nesse ponto.
 * 
 * Pretende-se ter um mapeamento de:
 * Ponto2D -> Lista Apostadores
 * 
 * Internamente a estrutura de dados que foi escolhida para guardar 
 * esta informa��o � um TreeMap, neste caso, um TreeMap<Ponto2D, ArrayList<String>>.
 * 
 * Por quest�es de simplifica��o, para os apostadores apenas se guarda o 
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
   * Construtor por omiss�o.
   * Apenas cria o objecto TreeMap, mas deve parametrizar o Map com a capacidade
   * de comparar as chaves, que neste caso s�o Ponto2D.
   * Caso n�o fosse passado o comparador de Ponto2D n�o seria poss�vel inserir mais do
   * que um Ponto2D.
   */
  public TabuleiroApostas() {
    this.apostas = new TreeMap<Ponto2D,ArrayList<String>>(new Ponto2DComparator());  
  }    
  
  /**
   * Construtor que aceita como par�metro um Map com rela��es j� preenchidas.
   */
  public TabuleiroApostas(Map<Ponto2D,ArrayList<String>> infoApostas) {
    this.apostas = new TreeMap<Ponto2D,ArrayList<String>>(new Ponto2DComparator());
    
    for(Ponto2D p: infoApostas.keySet()) {
      ArrayList<String> linha = infoApostas.get(p);
      ArrayList<String> novaLinha = new ArrayList<String> ();
      novaLinha.addAll(linha); //s�o Strings, logo n�o � preciso clone
      this.apostas.put(p.clone(),novaLinha);                                         
    }
  }
  
  /**
   * Construtor de c�pia.
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
      novaLinha.addAll(linha); //s�o Strings, logo n�o � preciso clone
      aux.put(p.clone(),novaLinha);                                         
    }      
    return aux;
  }    
   
  
   /**
    * M�todo que insere mais um nome de apostador num determinado ponto.
    * Se o ponto fornecido n�o existir no TreeMap, cria uma nova rela��o no TreeMap.
    * Caso contr�rio acrescenta � lista de apostadores.
    */
  
   public void insereApostador(Ponto2D p, String apostador) {
     if (this.apostas.containsKey(p)) { //se o ponto j� existe no MAP
       ArrayList<String> linha = this.apostas.get(p);
       if (!linha.contains(apostador)) { //se fosse um Set n�o teriamos de fazer este teste
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
     * Determinar quantos apostadores est�o associados a um ponto.
     * Tem como pr�-condi��o que o ponto exista (ver m�todo existePonto(Ponto2D)).
     */
    
    public int qtApostadoresPorPonto(Ponto2D p) {
        return (this.apostas.get(p)).size();
    }    
    
    //outros m�todos
    
    /**
     * toString, equals e clone
     * 
     */
}