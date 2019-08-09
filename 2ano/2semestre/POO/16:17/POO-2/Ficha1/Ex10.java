/**
 * Escreva a descrição da classe Ex10 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.GregorianCalendar;
import java.util.Calendar;
public class Ex10
{
  public static String somaDatas(int d1, int h1, int m1, int s1, int d2, int h2, int m2, int s2)
  {
    GregorianCalendar data = new GregorianCalendar(2015,02,d1,h1,m1,s1);
    data.add(Calendar.DAY_OF_MONTH, d2);
    data.add(Calendar.HOUR_OF_DAY, h2);
    data.add(Calendar.MINUTE,m2);
    data.add(Calendar.SECOND,s2);
    String r=data.get(Calendar.DAY_OF_MONTH)+"D"+data.get(Calendar.HOUR_OF_DAY)+"H"+ data.get(Calendar.MINUTE)+"M"+data.get(Calendar.SECOND)+"S";  
    return r;
  }
}
