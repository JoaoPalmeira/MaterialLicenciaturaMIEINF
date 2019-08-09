
/**
 * Write a description of class Ponto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import static java.lang.Math.abs; 

public class Ponto2D { 
 
  // Vari�veis de Inst�ncia 
   private double x, y; 
 
   // Construtores usuais 
   public Ponto2D(double cx, double cy) { x = cx; y = cy; } 
   public Ponto2D(){ this(0.0, 0.0); }  // usa o outro construtor 
   public Ponto2D(Ponto2D p) { x = p.getX(); y = p.getY(); } 
 
   
   // M�todos de Inst�ncia 
   public double getX() { return this.x; } 
   public double getY() { return this.y; } 
 
   /** incremento das coordenadas */ 
   public void incCoord(double dx, double dy) { 
      x += dx; y += dy; 
   } 
   /** decremento das coordenadas */ 
   public void decCoord(double dx, double dy) { 
      x -= dx; y -= dy; 
   } 

    /** soma as coordenadas do ponto par�metro ao ponto receptor */ 
   public void somaPonto(Ponto2D p) { 
      x += p.getX(); y += p.getY(); 
   } 
   /** soma os valores par�metro e devolve um novo ponto */ 
   public Ponto2D somaPonto(double dx, double dy) { 
     return new Ponto2D(x += dx, y+= dy); 
   } 
   /* determina se um ponto � sim�trico (dista do eixo dos XX o 
       mesmo que do eixo dos YY */ 
   public boolean simetrico() { 
     return abs(x) == abs(y); 
   } 
 
   /** verifica se ambas as coordenadas s�o positivas */ 
   public boolean coordPos() { 
     return x > 0 && y > 0; 
   }  
 
   // M�todos complementares usuais 
 
   /* verifica se os 2 pontos s�o iguais */ 
   public boolean igual(Ponto2D p) { 
      if (p != null) return (x == p.getX() && y == p.getY()); 
      else return false; 
   } 
 
   // outra vers�o de igual(Ponto2D p) 
   public boolean igual1(Ponto2D p) { 
     return (p == null) ? false : x == p.getX() && y == p.getY(); 
   } 
   
   /** 
    * implementa��o do m�todo equals
    */
   public boolean equals(Object umPonto) {
     if (this == umPonto) return true;
     if (umPonto == null || this.getClass() != umPonto.getClass()) 
       return false;
     else {
       Ponto2D p = (Ponto2D) umPonto;
       return (this.x == p.getX() && this.y == p.getY());
     }
   }

   /** Converte para uma representa��o textual  */ 
   public String toString() { 
     return new String("Pt2D = " + x + ", " + y); 
   } 
 
   /** Cria uma c�pia do ponto receptor (receptor = this) */ 
   public Ponto2D clone() { 
      return new Ponto2D(this);  
   } 
   
   /**
    * Implementa��o do m�todo hashcode.
    * Este m�todo � necess�rio sempre que for necess�rio criar estruturas baseadas em hash.
    * Dessa forma cada uma das inst�ncias de Ponto2D ser� capaz de calcular o seu valor de hash.
    * 
    * Note-se que sempre que sempre que a compara��o, com equals, de dois objectos d� true, ent�o 
    * os seus valores de hashcode devem ser o mesmo.
    * 
    * Isto �, se  (o1.equals(o2)) == true, ent�o o1.hashcode() == o2.hashcode()
    * 
    * 
    */
    public int hashCode() {
      return (int)(this.x*7 + this.y*11);
    }   
   
    
   /**
    * m�todo que implementa a ordem natural.
    */ 
   
   public int compareTo(Ponto2D p) {
    if (this.x < p.getX()) return 1;
    if( this.x > p.getX() ) return -1;
    if( this.y < p.getY() ) return 1;
    if( this.y > p.getY() ) return -1; else return 0;
  } 
    
  
  
  
  
  
  
  
}
