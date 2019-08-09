
public class Pixel
{
   private double x;
   private double y;
   private int c;
   
   public Pixel (double x, double y, int c)
   {
       this.x = x;
       this.y = y;
       this.c = c;
   }
   
   public Pixel()
   {
       this.x = 0.0;
       this.y = 0.0;
       this.c = 0;
   }
   
   public Pixel (Pixel p)
   {
       this.x = p.getX();
       this.y = p.getY();
       this.c = p.getCor();
   }
   
   public double getX()
   {
       return this.x;
   }
   
   public double getY()
   {
       return this.y;
   }
   
   public int getCor()
   {
       return this.c;
   }
   
   public void setX(double x)
   {
       this.x = x;
   }
   
   public void setY(double y)
   {
       this.y = y;
   }
   
   public void setCor (int cor)
   {
       this.c = cor;
   }
   
   public boolean equals (Object o)
   {
       if (this == o) return true;
       if ((o==null)||(o.getClass()!=this.getClass())) return false;
       else { Pixel p = (Pixel) o;
                 return (p.getX()==this.x && p.getY()==this.y && p.getCor()==this.c);
                }
   }
   
   public Pixel clone ()
   {
       return new Pixel (this );
   }
   
   public String toString()
   {
       StringBuilder s = new StringBuilder();
       s.append("Pixel");
       s.append("x: "+this.x);
       s.append("y: "+this.y);
       s.append("cor: "+this.c);
       return s.toString();
   }
   
   public void desloca(double x, double y)
   {
      this.x += x;
      this.y +=y;
   }
   
  public void mudarCor(int cor)
  {
      if (cor>=0&&cor<=15) this.c = cor;
   }
   
   public String nomeCor()
   {
     String cor="cor";
     int COR = this.c;
     if (COR==0) cor="Preto";
     if (COR==1) cor="Azul marinho";
     if (COR==2) cor="Verde Escuro";
     if (COR==3) cor="Azul petróleo";
     if (COR==4) cor="Castanha";
     if (COR==5) cor="Púrpura";
     if (COR==6) cor="Verde oliva";
     if (COR==7) cor="Cinza clara";
     if (COR==8) cor="Cinza escura";
     if (COR==9) cor="Azul";
     if (COR==10) cor="Verde";
     if (COR==11) cor="Azul turquesa";
     if (COR==12) cor="Vermelho";
     if (COR==13) cor="Fúcsia";
     if (COR==14) cor="Amarelo";
     if (COR==15) cor="Branco";
     return cor;
     }
}
