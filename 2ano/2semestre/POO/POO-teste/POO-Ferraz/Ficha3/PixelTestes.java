
/**
 * Escreva a descrição da classe PixelTestes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class PixelTestes
{   public static void main(String[] args){
    System.out.println("Informação dos pixeis");
    Pixel p1 = new Pixel();
    System.out.println("Criação do pixel vazio -> "+p1);
    p1.desloca(1.0,1.0);
    System.out.println("Deslocamento do pixel -> "+p1);
    p1.mudarCor(4);
    System.out.println("Mudança de cor do pixel -> "+p1);
    Pixel p2 = new Pixel(2.0,3.0,1);
    System.out.println("Criação do pixel -> "+p2);
    p2.desloca(1.0,1.0);
    System.out.println("Deslocamento do pixel -> "+p2);
    p2.mudarCor(12);
    System.out.println("Mudança de cor do pixel -> "+p2);
   }
}