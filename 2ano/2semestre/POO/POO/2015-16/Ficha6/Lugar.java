
/**
 * Escreva a descrição da classe Lugar aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Lugar
{
     private String matricula;
     private String nome;
     private int minutos;
     private boolean permanente;
     
     public Lugar(){
        matricula = "00-AA-00";
        nome = "Lugar";
        minutos = 0;
        permanente = true;
     }
     
     public Lugar (String mat, String no, int min, boolean perman){
        matricula = mat;
        nome = no;
        minutos = min;
        permanente = perman;
     }
     
     public Lugar (Lugar l1){
         matricula = l1.getMatricula;
     }
}
