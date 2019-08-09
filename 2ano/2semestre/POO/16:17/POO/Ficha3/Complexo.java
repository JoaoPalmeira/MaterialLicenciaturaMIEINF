
/**
 * Escreva a descrição da classe Ex1 aqui.
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

    public void conjugadoSelf(){
        this.b=-this.b;   
    }

    public Complexo soma(Complexo c){
    double na= this.a+c.getA();
    double nb = this.b+c.getB();
    return new Complexo(na,nb);
   }
   
   public Complexo produto(Complexo c){
      double a,b;
      a=((this.a*c.getA())-(this.b*c.getB()));
      b=((this.b*c.getB())+(this.b*c.getB()));
      return new Complexo(a,b);
    }
    
   public Complexo reciproco(){
       double a,b;
       a=((this.a)/((this.a*this.a)+(this.b*this.b)));
       b=((this.b)/((this.a*this.a)+(this.b*this.b)));
       return new Complexo(a,b);
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






