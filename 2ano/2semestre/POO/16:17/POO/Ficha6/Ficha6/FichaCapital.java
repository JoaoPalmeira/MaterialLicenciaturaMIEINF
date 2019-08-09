
/**
 * Write a description of class FichaCapital here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FichaCapital
{
  private String nomec;
  private int populacao, nveiculos;
  private double salariomedio, custovidamedio;
  
  public FichaCapital(){
      this.nomec=new String();
      this.populacao=0;
      this.nveiculos=0;
      this.salariomedio=0.0;
      this.custovidamedio=0.0;
  }
  
  public FichaCapital(String nomec, int populacao, int nveiculos, double salariomedio, double custovidamedio){
      this.nomec=nomec;
      this.populacao=populacao;
      this.nveiculos=nveiculos;
      this.salariomedio=salariomedio;
      this.custovidamedio=custovidamedio;
  }
  
  public FichaCapital(FichaCapital f){
      this.nomec=getNomec();
      this.populacao=getPopulacao();
      this.nveiculos=getNveiculos();
      this.salariomedio=getSalariomedio();
      this.custovidamedio=getCustovidamedio();
    } 
    
   public String getNomec(){
       return this.nomec;
   }
   
   public int getPopulacao(){
       return this.populacao;
    }
    
    public int getNveiculos(){
        return this.nveiculos;
    }
    
    public double getSalariomedio(){
        return this.salariomedio;
    }
    
    public double getCustovidamedio(){
        return this.custovidamedio;
    }
    
    public void setNomec(String nomec){
           this.nomec=nomec;
    }
    
    public void setPopulacao(int populacao){
        this.populacao=populacao;
    }
    
    public void setNveiculos(int nveiculos){
        this.nveiculos=nveiculos;
    }
    
    public void setSalariomedio(double salariomedio){
        this.salariomedio=salariomedio;
    }
    
    public void setCustovidamedio(double custovidamedio){
        this.custovidamedio=custovidamedio;
    }
    
    public FichaCapital clone(){
        return new FichaCapital(this);
    }
    
       public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if((o==null)|| (this.getClass()!= o.getClass())){
        return false;
        }
        
        FichaCapital f= (FichaCapital) o;
        return (this.nomec.equals(f.getNomec()) && this.populacao==f.getPopulacao() && this.nveiculos==f.getNveiculos()
                                && this.salariomedio==f.getSalariomedio() && this.custovidamedio==f.getCustovidamedio());
    }
    
    public String toString(){
        StringBuffer sb= new StringBuffer();
        sb.append("Nome Capital ="+this.getNomec());
        sb.append("Populacao ="+this.getPopulacao());
        sb.append("Nº veiculos ="+this.getNveiculos());
        sb.append("Salario Medio ="+this.getSalariomedio());
        sb.append("Custo Vida Medio ="+this.getCustovidamedio());
        return sb.toString();
    }
}
    
    
   
