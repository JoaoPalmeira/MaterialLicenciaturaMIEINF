import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
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
   private Map <String, Lugar> lugares;
   
   public Parque(){
       lugares =  new HashMap<>();
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
   }p  
   
   public void registaLugar(Lugar lugar){
       String k = lugar.getMatricula();
       lugares.put(k, lugar.clone());
   }
}
