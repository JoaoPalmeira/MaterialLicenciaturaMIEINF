
/**
 * Escreva a descrição da classe Pixel aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Pixel
{
    //Variáveis de instância
   private double x,y;
   private int cor;
   
   //Construtores usais
   public Pixel(){
    this(0.0,0.0,0);   
    }
     
   public Pixel(Pixel p){
       this.x=p.getX();
       this.y=p.getY();
       this.cor=p.getCor();
    }
    
   public Pixel( double cx, double cy, int cz){
       this.x=cx;
       this.y=cy;
       this.cor=cz;
    }
 
    //Métodos de instância
    public double getX(){return x;}
    public double getY(){return y;}
    public int getCor(){return cor;}
   
    public void setX(double x){this.x=x;}
    public void setY(double y){this.y=y;}
    public void setCor(int cor){this.cor=cor;}
    
    
      
    
    
        
    public void desloca(double x, double y){
        this.x=this.x+x;
        this.y=this.y+y;
    }
    
    public void mudarCor(int cor){
        if(cor>=0 && cor<=15){
            this.cor= cor;
        }        
    }

	 public String nomeCor(){
     int c=this.cor;
     String[] cores={"Preto","Azul marinho","Verde escuro","Azul petróleo","Castanho","Púrpura",
         "Verde oliva", "Cinza claro", "Cinza escuro", "Azul", "Verde", "Azul turquesa","Vermelho", "Fúcsia","Amarelo","Branco"};
    return cores[c];
    }
   
 	public String toString(){
   	 return new String("Pixel = "+x+", "+y+", "+cor);   
   	}

	 public boolean equals(Pixel p){
       if(this== p)
       return true;
       
       if((p==null)||(this.getClass()!=p.getClass()))
        return false;
       else{
           Pixel c= (Pixel) p;
           return(this.x==c.getX() && this.y==c.getY() && this.cor==c.getCor());
        }
    }
     
   public Pixel clone(){
        return new Pixel(this);
   }



   
}
    
    
    

    