
/**
 * Escreva a descrição da classe ComplexoTestes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class ComplexoTestes
{
    public static void main(String[] args){
       Complexo c1= new Complexo();
       Complexo c2 = new Complexo(2,3);
       Complexo c3 = c1.soma(c2);
       Complexo c4 = new Complexo(2,3);
       Complexo c4x = new Complexo(3,4);
       Complexo c5= c4.produto(c4x);
       Complexo c6 = new Complexo(2,3);
       Complexo c7 = c6.reciproco();
       System.out.println("SOMA-> "+c1+"+"+c2+"="+c3);
       System.out.println("PRODUTO-> "+c4+"*"+c4x+"="+c5);
       System.out.println("RECIPROCO-> "+c6+"= "+c3);
}
}
