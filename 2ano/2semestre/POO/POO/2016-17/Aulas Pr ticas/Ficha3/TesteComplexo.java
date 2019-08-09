
/**
 * Escreva a descrição da classe TesteComplexo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class TesteComplexo
{
   public static void main(String args[]){
       /**Complexo c1 = new Complexo(1,1);
       Complexo c2 = c1.conjugado();
       Complexo c3 = c2.conjugado();
       double x = c3.getA();
       System.out.println(...);*/
       
       Complexo c1, c2, c3, c4, c5;
       
       c1 = new Complexo(3,4);
       c2 = new Complexo(c1);
       c3 = new Complexo();
       c4 = c1.reciproco();
       c5 = c1.conjugado();
       
       double r = c1.getR();
       //System.out.println("Parte real de c1 = " + r);
       //System.out.println("c2= "+c2);
       //System.out.println("reciproco de c1= "+c4);
       System.out.println("c1= "+c1);
       System.out.println("conjugado de c1= "+c5);
       
   }
}
