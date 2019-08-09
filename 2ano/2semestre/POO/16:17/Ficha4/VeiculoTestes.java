
/**
 * Escreva a descrição da classe VeiculoTestes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class VeiculoTestes
{
    public static void main(String args[]){
      Veiculo v1;
      
      v1 = new Veiculo(200.7,100.6,5.2,50,6,"00-00-AA");
      
      System.out.println("conteúdo antes de abastecer: " + v1.getConteudo());
      
      String s = "";
      
      if(v1.naReserva() == true) s = "sim";
      else s = "não";
      
      System.out.println("está na reserva? " + s);
      
      
      v1.abastecer(20);
      
      System.out.println("conteúdo depois de abastecer: " + v1.getConteudo());
      
      
      
  
       
   }
}
