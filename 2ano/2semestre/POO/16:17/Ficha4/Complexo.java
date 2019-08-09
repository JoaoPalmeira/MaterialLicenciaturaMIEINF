
/**
 * Escreva a descrição da classe Complexo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Complexo
{
    //Variaveis de instância
  private double a,b;
  
    //Construtores
   public Complexo(){
      this.a=1;
      this.b=1;
   }   
    public Complexo(Complexo c){
    this.a=c.getA();
    this.b=c.getB();
   }
    public Complexo(double a,double b){
    this.a=a;
    this.b=b;
   }
        
  //Getters e Setters
   public double getA(){return this.a;} 
   public double getB(){return this.b;}

   public void setA(double a){this.a=a;} 
   public void setB(double b){this.b=b;}
   
   //Métodos de instância
    public Complexo conjugado(){
        return new Complexo(this.a,-this.b);
    }
    
    public Complexo soma(Complexo c){
        double c1 = c.getA()+this.a;
        double c2 = c.getB()+this.b;
        return new Complexo(c1,c2);
    }
    
    public Complexo produto(Complexo c){
        double c1 = (c.getA()*this.a)-(c.getB()*this.b);
        double c2 = (c.getA()*this.b)+(c.getB()*this.a);
        return new Complexo(c1,c2);
    }
    
    public Complexo reciproco(){
        double c1 = ((this.a)/((this.a*this.a)+(this.b*this.b)));
        double c2 = ((this.b)/((this.a*this.a)+(this.b*this.b)));
        return new Complexo(c1,c2);
    }
    
    public String toString(){
       return "Complexo("+this.a+"+"+this.b+"i)";
   }
   
   public boolean equals(Complexo p){
       if(this== p)
       return true;
       
       if((p==null)||(this.getClass()!=p.getClass()))
        return false;
       else{
           Complexo c= (Complexo) p;
           return(this.a==c.getA() && this.b==c.getB());
        }
    }
     
   public Complexo clone(){
        return new Complexo(this);
   }
    
    
    
    
    
    
    
    
    
}
