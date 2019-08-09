
/**
 * Write a description of class Ponto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import static java.lang.Math.abs; 

public class Ponto2D { 
 
  // Variáveis de Instância 
   private double x, y; 
 
   // Construtores usuais 
   public Ponto2D(double cx, double cy) { x = cx; y = cy; } 
   public Ponto2D(){ this(0.0, 0.0); }  // usa o outro construtor 
   public Ponto2D(Ponto2D p) { x = p.getX(); y = p.getY(); } 
 
   
   // Métodos de Instância 
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

    /** soma as coordenadas do ponto parâmetro ao ponto receptor */ 
   public void somaPonto(Ponto2D p) { 
      x += p.getX(); y += p.getY(); 
   } 
   /** soma os valores parâmetro e devolve um novo ponto */ 
   public Ponto2D somaPonto(double dx, double dy) { 
     return new Ponto2D(x += dx, y+= dy); 
   } 
   /* determina se um ponto é simétrico (dista do eixo dos XX o 
       mesmo que do eixo dos YY */ 
   public boolean simetrico() { 
     return abs(x) == abs(y); 
   } 
 
   /** verifica se ambas as coordenadas são positivas */ 
   public boolean coordPos() { 
     return x > 0 && y > 0; 
   }  
 
   // Métodos complementares usuais 
 
   /* verifica se os 2 pontos são iguais */ 
   public boolean igual(Ponto2D p) { 
      if (p != null) return (x == p.getX() && y == p.getY()); 
      else return false; 
   } 
 
   // outra versão de igual(Ponto2D p) 
   public boolean igual1(Ponto2D p) { 
     return (p == null) ? false : x == p.getX() && y == p.getY(); 
   } 
   
   /** 
    * implementação do método equals
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

   /** Converte para uma representação textual  */ 
   public String toString() { 
     return new String("Pt2D = " + x + ", " + y); 
   } 
 
   /** Cria uma cópia do ponto receptor (receptor = this) */ 
   public Ponto2D clone() { 
      return new Ponto2D(this);  
   } 
   
   /**
    * Implementação do método hashcode.
    * Este método é necessário sempre que for necessário criar estruturas baseadas em hash.
    * Dessa forma cada uma das instâncias de Ponto2D será capaz de calcular o seu valor de hash.
    * 
    * Note-se que sempre que sempre que a comparação, com equals, de dois objectos dê true, então 
    * os seus valores de hashcode devem ser o mesmo.
    * 
    * Isto é, se  (o1.equals(o2)) == true, então o1.hashcode() == o2.hashcode()
    * 
    * 
    */
    public int hashCode() {
      return (int)(this.x*7 + this.y*11);
    }   
   
    
   /**
    * método que implementa a ordem natural.
    */ 
   
   public int compareTo(Ponto2D p) {
    if (this.x < p.getX()) return 1;
    if( this.x > p.getX() ) return -1;
    if( this.y < p.getY() ) return 1;
    if( this.y > p.getY() ) return -1; else return 0;
  } 
    
  
  
  
  
  
  
  
}
