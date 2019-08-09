
/**
 * Escreva a descrição da classe Ex7 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.GregorianCalendar;
public class Ex7
{
    public static void main (String[] args){
       long fact=1,x=1000000;
       GregorianCalendar antes = new GregorianCalendar();
       System.out.println("Antes do ciclo:");
       System.out.println(antes.get(antes.DAY_OF_MONTH)+"/"+(antes.get(antes.MONTH)+1)+"/"+antes.get(antes.YEAR)+" "+antes.get(antes.HOUR_OF_DAY)+":"+antes.get(antes.MINUTE)+":"+antes.get(antes.SECOND)+":"+antes.get(antes.MILLISECOND));
       while(x!=0) {
           fact*=x;
           x--;
       }
       System.out.println("Depois do ciclo:");
       GregorianCalendar depois = new GregorianCalendar();
       System.out.println(depois.get(depois.DAY_OF_MONTH)+"/"+(depois.get(depois.MONTH)+1)+"/"+depois.get(depois.YEAR)+" "+depois.get(depois.HOUR_OF_DAY)+":"+depois.get(depois.MINUTE)+":"+depois.get(depois.SECOND)+":"+depois.get(depois.MILLISECOND));
       long dif = depois.getTimeInMillis()-antes.getTimeInMillis();
       System.out.println("Demorou "+dif+" milisegundos!"+"\n");
    }
}