
/**
 * Escreva a descrição da classe Veiculo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veiculo
{
   private double kmp,kmt,consumoMedio;
   private int capacidade,conteudo;
   private String matricula;
   
   
   
   //Construtores usuais
   public Veiculo(){
       this(0.0,0.0,0.0,0,0,"");
       }
       
   public Veiculo(double a, double b, double c, int d, int e, String f){
       this.kmp=a;
       this.kmt=b;
       this.consumoMedio=c;
       this.conteudo=d;
       this.capacidade=e;
       this.matricula=f;
    }
    public Veiculo(Veiculo v){
       this.kmp=v.getKmp();
       this.kmt=v.getKmt();
       this.consumoMedio=v.getConsumoMedio();
       this.conteudo=v.getConteudo();
       this.capacidade=v.getCapacidade();
       this.matricula=v.getMatricula();
    }    
       
    
    //Métodos de instância
    
    public double getKmp(){return this.kmp;}
    public double getKmt(){return this.kmt;}
    public double getConsumoMedio(){return this.consumoMedio;}
    public int getConteudo(){return this.conteudo;}
    public int getCapacidade(){return this.capacidade;}
    public String getMatricula(){return this.matricula;}

    public void setKmp(double kmp){this.kmp=kmp;}
    public void setKmt(double kmt){this.kmt=kmt;}
    public void setConsumoMedio(double consumoMedio){this.consumoMedio=consumoMedio;}
    public void setConteudo(int conteudo){this.conteudo=conteudo;}
    public void setCapacidade(int capacidade){this.capacidade=capacidade;}
    public void setMatricula(String matricula){this.matricula=matricula;}
    
        
    public void abastecer(int litros){
        int total= conteudo+litros;
        if(total>capacidade){
            conteudo=capacidade;
        }
        else{conteudo=total;}
    }
    
    public void resetKms(){
        this.kmp=0;
        this.consumoMedio=0;
    }
    
    public double autonomia(){
        double aut= (conteudo*100)/consumoMedio;
        return aut;
    }
    
    public void registarViagem(int kms, int consumo){
        this.kmt=this.kmt+kms;
        this.conteudo=this.conteudo-consumo;
        this.consumoMedio=(100*consumo)/kms;
    }
    
    public boolean naReserva(){
         if(this.conteudo<10){
            return true;
        }
        else{ return false;}
    }
    
    public double totalCombustivel(double custoLitro){
        double c = (this.consumoMedio*this.kmt)/100;
        double custo = c*custoLitro;
        return custo;
    }
    
    public double custoMedioKm(double custoLitro){
        double ckm=this.consumoMedio/100;
        double custo=ckm*custoLitro;
        return custo;
    }
        
 public boolean equals(Veiculo v){
       if(this== v)
       return true;
       
       if((v==null)||(this.getClass()!=v.getClass()))
        return false;
       else{
           Veiculo n= (Veiculo) v;
           return(this.kmp==v.getKmp() && this.kmt==v.getKmt() && this.consumoMedio==v.getConsumoMedio() && this.conteudo==v.getConteudo() && this.capacidade==v.getCapacidade() && this.matricula.equals(v.getMatricula())); 
        }
    }
     
	

   public Veiculo clone(){
        return new Veiculo(this);
   }
	

    public String toString(){
        return new String("Veiculo-> "+matricula+", "+capacidade+", "+conteudo+", "+consumoMedio+", "+kmt+", "+kmp);
    }
}
    
    
    
    
    
    
    