
public class Complexo
{
   private double a;
   private double b;
   
   public Complexo(double a, double b)
   {
       this.a = a;
       this.b = b;
   }
   
   public Complexo()
   {
       this.a=0.0;
       b=0.0;//porque não há ambiguidade
   }
   
   public Complexo (Complexo c)
   {
       this.a = c.getA();
       this.b = c.getB();
   }
   
   public double getA()
   {
       return this.a;
   }
   
   public double getB()
   {
       return b;
   }
   
   public void setA(double a)
   {
       this.a = a;
   }
   
   public void setB(double b)
   {
       this.b = b;
   }
   
   public boolean equals (Object o)
   {
       if (this == o) return true;
       if ((o==null)||(o.getClass()!=this.getClass())) return false;
       else { Complexo c = (Complexo) o;
                 return (c.getA()==this.a && c.getB()==this.b);
              }
   }
   
   public Complexo clone ()
   {
       return new Complexo (this );
   }
   
   public String toString()
   {
       StringBuilder s = new StringBuilder();
       s.append ("Complexo");
       s.append("x: "+ this.a);
       s.append("y: "+this.b);
       return s.toString();
   }
   
   public Complexo conjugado ()
   {
       return new Complexo (this.a, this.b*-1);
   }
   
   public Complexo soma (Complexo c)
   {
       return new Complexo (c.getA()+this.a,c.getB()+this.b);
   }
   
   public Complexo produto (Complexo c)
   {
       return new Complexo(c.getA()*this.a-c.getB()*this.b,c.getA()*this.b+c.getB()*this.a);
   }
   
   public Complexo reciproco()
   {
       return new Complexo((this.a/(this.a*this.a+this.b*this.b)),(this.b/(this.a*this.a+this.b*this.b)*-1));
   }
}
