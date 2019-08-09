/*********************************************************************************/
/** DISCLAIMER: Este código foi criado e alterado durante as aulas práticas      */
/** de POO. Representa uma solução em construção, com base na matéria leccionada */
/** até ao momento da sua elaboração, e resulta da discussão e experimentação    */
/** durante as aulas. Como tal, não deverá ser visto como uma solução canónica,  */
/** ou mesmo acabada. É disponibilizado para auxiliar o processo de estudo.      */
/** Os alunos são encorajados a testar adequadamente o código fornecido e a      */
/** procurar soluções alternativas, à medida que forem adquirindo mais           */
/** conhecimentos de POO.                                                        */
/*********************************************************************************/

/**
 * Exercício da Gestão de Encomendas da Ficha 6
 *
 * @author MaterialPOO
 * @version 20180403
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class GestaoEncomendas {
  private Map<Integer,Encomenda> encomendas;
  
  public GestaoEncomendas() {    
    this.encomendas = new HashMap<>();
  }
  
  public GestaoEncomendas(Map<Integer,Encomenda> encs) {
    //this.encomendas = encs.values().stream().collect(Collectors.toMap((e) -> e.getNEnc(),(e) -> e.clone(),(e1,e2) -> e1,HashMap::new));
    this.encomendas = encs.values().stream().collect(Collectors.toMap((e) -> e.getNEnc(),(e) -> e.clone()));
  }
  
  public GestaoEncomendas(GestaoEncomendas ge) {
    this.encomendas = ge.getEncomendas();    
  }
  
  public Map<Integer,Encomenda> getEncomendas() {
    return this.encomendas.values().stream().collect(Collectors.toMap((e) -> e.getNEnc(),(e) -> e.clone())); 
  }
  
  
       
// a) public Set<Integer> todosCodigosEnc()
// se fizessemos return this.encomendas.keySet(), seria possível apagar chaves do map
  public Set<Integer> todosCodigosEnc() {
    return new TreeSet<Integer>(this.encomendas.keySet());    
  }

// b) public void addEncomenda(Encomenda enc)
  public void addEncomenda(Encomenda enc) {
    this.encomendas.put(enc.getNEnc(), enc.clone());    
  }
  
// c) public Encomenda getEncomenda(Integer codEnc)

  public Encomenda getEncomenda(Integer codEnc) {
    return (this.encomendas.get(codEnc)).clone();
  }

// d) public void removeEncomenda(Integer codEnc)

  public void removeEncomenda(Integer codEnc) {
    this.encomendas.remove(codEnc);    
  }
// e) public Integer encomendaComMaisProdutos()
// 
// Nota: criado um método em Encomenda que determina a quantidade de produtos encomendados
  public Integer encomendaComMaisProdutos() {
    TreeSet<Encomenda> aux = 
     new TreeSet<>((e1,e2) -> (e1.numProdutos()) - (e2.numProdutos()));
    // uso de forEach com 
    // this.encomendas.values().forEach(e -> aux.add(e));
    // ou
    // aux.addAll(this.encomendas.values())
    // porque, apesar de partilhar referências, a variável aux não é depois utilizada
    // (a partilha é só efectuada neste método)
    for (Encomenda e: this.encomendas.values())
      aux.add(e);
    return (aux.last()).getNEnc();
  }

// f) public Set<Integer> encomendasComProduto(String codProd)
//
// Nota: criado um método, da classe Encomenda, que determina um produto consta da encomenda
  public Set<Integer> encomendasComProduto(String codProd) {
     return this.encomendas.values().stream().filter(e -> e.existeNaEncomenda(codProd)).map(Encomenda::getNEnc).collect(Collectors.toCollection(TreeSet::new));       
  }

// g) public Set<Integer> encomendasAposData(DateTime d)

   public Set<Integer> encomendasAposData(LocalDate d) {
     return this.encomendas.values().stream().filter(e -> e.getData().isAfter(d)).map(Encomenda::getNEnc).collect(Collectors.toSet());  
   }
// h) public Set<Encomenda> encomendasValorDecrescente()
   public Set<Encomenda> encomendasValorDecrescente() { 
     TreeSet<Encomenda> aux = new TreeSet<Encomenda>((e1,e2) -> (int)(e2.calculaValorTotal() - e1.calculaValorTotal()));
     //Nota: valor de e2 - valor de e1, para garantir ordem decrescente
     
     for(Encomenda e: this.encomendas.values())
        aux.add(e.clone());
     
     return aux;        
   }
// i) public Map<String,List<Integer>> encomendasDeProduto()
// devolve um map de código de produto -> lista de códigos de encomenda
    
   public Map<String,List<Integer>> encomendasDeProduto() {
     Map<String,List<Integer>> aux = new HashMap<>();
     
     for (Encomenda e: this.encomendas.values()) {
        List<String> lprods = e.getListaProdutos();
        for (String codProd: lprods) {
           if (!aux.containsKey(codProd))
             aux.put(codProd, new ArrayList<Integer>());
           aux.get(codProd).add(e.getNEnc());
        }   
     }
     return aux;
   }
   
   
   public String toString() {
     StringBuffer sb = new StringBuffer();
     for (Encomenda e: this.encomendas.values())
       sb.append(e.toString() + "\n");
     return sb.toString(); 
   }
   
   
   public boolean equals(Object o) {
      if (this == o) 
        return true;
      if ((o == null) || (this.getClass() != o.getClass()))
        return false;
      GestaoEncomendas ge = (GestaoEncomendas) o;
      return this.encomendas.equals(ge.getEncomendas());
       
   }    

   public GestaoEncomendas clone() {
     return new GestaoEncomendas(this); 
   }
}
