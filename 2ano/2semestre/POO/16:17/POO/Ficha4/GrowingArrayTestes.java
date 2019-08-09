
/**
 * Write a description of class GrowingAraayTestes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrowingArrayTestes
{   public static void main(String[] args) {
    GrowingArray e;
    e =new GrowingArray(5);
    
    Veiculo v1, v2, v3, v4, v5,v6;
    
    v1 = new Veiculo(123.5,500.0,4.5,15,30,"01-01-AA");
    v2 = new Veiculo(223.5,600.0,6.5,20,40,"05-05-ZZ");
    v3 = new Veiculo(423.5,700.0,2.5,30,50,"05-CC-05");
    v4 = new Veiculo(523.5,800.0,5.5,12,45,"05-AB-89");
    v5 = new Veiculo(723.5,900.0,8.5,25,60,"05-LL-99");
    v6 = new Veiculo(723.5,901.0,8.5,25,60,"06-LL-99");  
    e.add(v1);
   
    
}
}
