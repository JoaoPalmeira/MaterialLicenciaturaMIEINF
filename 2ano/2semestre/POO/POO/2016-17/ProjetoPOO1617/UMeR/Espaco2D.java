import java.io.*;
/**
 * Escreva a descrição da classe Espaco2D aqui.
 * 
 * @sofia carvalho 
 * @version 1
 */

public class Espaco2D implements Serializable
{
   // variáveis de instância
   private double x; // cooordenada x
   private double y; // cooordenada y
   
   // Construtores
    /**
     * Construtor para objetos da classe Espaco2D
     */
   public Espaco2D()
   {
        x = 0.0;
        y = 0.0;
   }
    
   //construtor por parâmetros
   public Espaco2D(double x, double y){
        this.x=x;
        this.y=y;
   }
    
   //construtor por cópia
   public Espaco2D(Espaco2D p){
        this.x=p.getX();
        this.x=p.getY();
   }
   
   //Métodos de instância
   //getters
   public double getX(){
        return x;
   }
    
   public double getY(){
        return y;
   }
    
   //setters
   public void setX(double x1){
        x = x1;
   }
    
   public void setY(double y1){
        y = y1;
   }
    
   public Espaco2D clone(){
       return new Espaco2D(this);
   }
   
   public boolean equals(Object o){
       if(o==this){
           return true;
        }
       if(o==null || o.getClass()!=this.getClass()){
            return false;
       }
       Espaco2D u =(Espaco2D) o;
       return u.getX()==(this.x) && u.getY()==(this.y);
   }
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Coordenada X: ").append(x).append("\n");
        sb.append("Coordenada Y: ").append(y).append("\n");
        return sb.toString();
   } 
}
   