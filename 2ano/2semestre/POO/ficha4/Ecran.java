
public class Ecran
{
    private Pixel[][] pixeis;
    private static int N;
    private static int M;
    
    public static void SetDimensoes (int n, int m)
    {
        Ecran.N = n;
        Ecran.M = m;
    }
    
    public Ecran (Ecran e)
    {
        pixeis = new Pixel [N][M];
        for (int i=0;i<N;i++)
        for (int j=0;j<M;j++)
        pixeis [i][j] = e.getPixel(i,j);
    }
    
    public Pixel getPixel(int i,int j)
    {
        return pixeis[i][j].clone();
    }
    
    public Ecran ()
    {
        this(0);
    }
    
    public Ecran (int cor)
    {
        this.N=15;
        this.M=15;
        pixeis = new Pixel [N][M];
        for (int i=0;i<N;i++)
        for (int j=0;j<M;j++)
        pixeis [i][j] = new Pixel(i,j,cor);
    }
    
    public Ecran clone()
    {
        return new Ecran (this );
    }
    
    public int getN()
    {
        return this.N;
    }
    
    public int getM()
    {
        return this.M;
    }
    
    public void mudaCor(int x, int y, int cor)
    {
        if(!(x>this.N && y>this.M)&&(cor>=0 && cor<=15))  this.pixeis[x][y].setCor(cor);
    }
    
    public int obterCor(int x, int y)
    {
        if(!(x>this.N && y>this.M))  return this.pixeis[x][y].getCor();
        else return (-1);
    }
    
    public boolean equals (Object o)
    {
      if (this == o) return true;
       if ((o==null)||(o.getClass()!=this.getClass())) return false;
      else 
       {
           Ecran e = (Ecran) o;
           if (this.N==e.getN() && this.M==e.getM())
           {
              boolean resultado=true;
              for (int i=0;i<this.N;i++)
               for (int j=0;j<this.M;j++)
               {
                  Pixel p=e.getPixel(i,j);
                  if(!pixeis[i][j].equals(p)) 
                  {
                      resultado = false;
                      j=this.M;
                      i=this.N;
                    }
              }
              return resultado;
           }
           else return false;
       }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("Ecran");
        s.append("N: "+this.N);
        s.append("M: "+this.M);
        for (int i=0;i<this.N;i++)
        for (int j=0;j<this.M;j++)
        s.append("Pixel: "+this.pixeis[i][j].toString());
        return s.toString();
    }
}
