
/**
 * Write a description of class Veiculo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Veiculo
{
   private double kmp,kmt,consumoMedio;
   private int capacidade,conteudo;
   private String matricula;
   
   //Construtores
   public Veiculo(){
       this(0.0,0.0,0.0,0,0,"");
   }
   public Veiculo(double a, double b, double c, int d, int e, String f){
       this.kmp=a;
       this.kmt=b;
       this.consumoMedio=c;
       this.capacidade=d;
       this.conteudo=e;
       this.matricula=f;
   }
   public Veiculo(Veiculo v){
       this.kmp=v.getKmp();
       this.kmt=v.getKmt();
       this.consumoMedio=v.getConsumoMedio();
       this.capacidade=v.getCapacidade();
       this.conteudo=v.getConteudo();
       this.matricula=v.getMatricula();
   }
   
   //Getters & Setters
   public double getKmp(){
       return this.kmp;
   }
   public double getKmt(){
       return this.kmt;
   }
   public double getConsumoMedio(){
       return this.consumoMedio;
   }
   public int getCapacidade(){
       return this.capacidade;
   }
   public int getConteudo(){
       return this.conteudo;
   }
   public String getMatricula(){
       return this.matricula;
   }
   public void setKmp(double kmp){
       this.kmp=kmp;
   }
   public void setKmt(double kmt){
       this.kmt=kmt;
   }
   public void setConsumoMedio(double consumoMedio){
       this.consumoMedio=consumoMedio;
   }
   public void setCapacidade(int capacidade){
       this.capacidade=capacidade;
   }
   public void setConteudo(int conteudo){
       this.conteudo=conteudo;
   }
   public void setMatricula(String matricula){
       this.matricula=matricula;
   }
   
   public void abastecer(int litros){
       int total=conteudo+litros;
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
       double aut=(conteudo*100)/consumoMedio;
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
       else return false;
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
   
   //equals, toString & clone
   public boolean equals (Object o){
       if(this==o) return true;
       if((o==null)||(this.getClass()!=o.getClass())) {return false;}
       else{
           Veiculo n = (Veiculo) o;
           return(this.kmp==n.getKmp() 
                  && this.kmt==n.getKmt() 
                  && this.consumoMedio==n.getConsumoMedio()
                  && this.capacidade==n.getCapacidade()
                  && this.conteudo==n.getConteudo()
                  && this.matricula.equals(n.getMatricula()));
       }
   }
   public String toString(){
       StringBuilder sb = new StringBuilder();
       sb.append("Kms percorridos"+this.kmp+"\n");
       sb.append("Kms totais"+this.kmt+"\n");
       sb.append("Consumo médio do veículo"+this.consumoMedio+"\n");
       sb.append("Capacidade do veículo"+this.capacidade+"\n");
       sb.append("Conteudo"+this.conteudo+"\n");
       sb.append("Matricula: ").append(this.matricula).append("\n");
       return sb.toString();
   }
   public Veiculo clone(){
        return new Veiculo(this);
    }
}
