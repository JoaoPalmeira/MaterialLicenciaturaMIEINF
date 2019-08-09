import java.util.Arrays;
import java.util.Scanner;
/**
 * Escreva a descrição da classe Ecran aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ecran
{
  private Pixel[][] pixels;

  public static int N;
  public static int M;

  public static void SetDimensions (int n, int m){
    Ecran.N = n;
    Ecran.M = m;
  }

  public Ecran (int cor){
    pixels = new Pixel [N][M];
    for (int i = 0; i < N; i++){
        for (int j = 0; j < M; j++){
            pixels [i][j] = new *pixel (i, j, cor);
        }
    }
  }
  
  public Ecran (){
      this(0);
  }
  
  public void mudaCor (int x, int y, int cor){
      pixeis[x][y].mudarCor(cor);
  }

  public Ecran clone (){
      return new Ecran(this);
  }
  
  public boolean equals (Object o){
      if (this == 0){
          return true;
      }
      if (o == null || o.getClass() != this.getClass()){
          return false;
      }
      Ecran e = (Ecran) o;
      for (int i = 0; i < N; i++){
          for (int j = 0; j < M; j++){
              if(pixels [
      
  

}
