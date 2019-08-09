import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;
import java.util.LinkedList;
/**
 * Escreva a descrição da classe Parque aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Parque
{
   private Map <String,Lugar> lugares;
   
   public Parque(){
       lugares =  new HashMap<String,Lugar>();
   }
   
   public Parque (Map<String, Lugar> lugares){
       setLugares(lugares);
   }
    
   public Parque (Parque parque){
       this.lugares = parque.getLugares();
   }
   
   public Set<String> getMatriculas(){
       return this.lugares.keySet();
   }
   
   public void registaLugar (Lugar lugar){
       String k = lugar.getMatricula();
       lugares.put(k, lugar.clone());
   }
   
   public void removeLugar (String matricula){
       lugares.remove(matricula);
   }
   
   //IMPLEMENTAÇÃO INEFICIENTE
   public Lugar consultarIneficiente (String matricula){
       Lugar l = null;
       for(Map.Entry<String,Lugar> p : lugares.entrySet()){
           if(p.getKey().equals(matricula)){
               l = p.getValue().clone();
            }
            return l;
       }
   }
   
   public Lugar consultar (String matricula){
       if(lugares.cointainsKey(matricula)){
           return lugares.get(matricula).clone();
       }
       return null;
   }
   
   public Map<String, Lugar> getLugares(){
       Map<String, Lugar> r = new HashMap<String, Lugar>();
       
       for(Map.Entry<String, Lugar> p : lugares.entrySet()){
           String k = p.getKey();
           lugar v = p.getValue().clone();
           r.put(k ,v);
       }
       return r;
   }
   
   public Set<String> getMatricula2(){
       Set<String> r = new HashSet<String>();
       
       for(String m : lugares.keySet()){
          r.add(m); 
       }
       
       return r;
   }
   
   //PORQUE AS CHAVES SÃO STRINGS
   public Set<String> getMatricula(){
       return lugares.keySet();
   }
   
   public List<Lugar> obterCopiaLugares(){
       List<Lugar> l = new ArrayList<Lugar>();
       
       for(Lugar a : lugares.values()){
           l.add(a.clone());
       }
       
       return l;
   }
   
   /**
    * Não era pedido no enunciado.
    */
   public List<String> getMatriculasLista(){
       return this.lugares.keySet().stream()
                          .collect(Collectors, toList());
   }
   
   public int totalMinutos(){
       int t=0;
       for(Lugar l : lugares.values()){
           t += l.getMinutos();
       }
       return t;
   }
   
}
