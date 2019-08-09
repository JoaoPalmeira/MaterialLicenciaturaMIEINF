import java.util.Scanner;
import java.util.Arrays;

public class Veiculo
{
   // varáveis de instância
   private String matricula;
   private double kmsTotal;
   private double kmsParcial;
   private double consumoMedio;
   private int capacidade;
   private int conteudo;
   private int tipoCombustivel;
   
   public static double Gasolina95;
   public static double Gasolina98;
   public static double Gasoleo;
   
   // construtores
   public Veiculo() {
       matricula = "00-AA-00";
       kmsTotal = 0.0;
       kmsParcial = 0.0;
       consumoMedio = 0.0;
       capacidade = 0;
       conteudo = 0;
       tipoCombustivel = 0;
   }
   
   public Veiculo(String mat, double kmsT, double kmsP, double consumoMed, int capac, int co, int tComb) {
       matricula = mat;
       kmsTotal = kmsT;
       kmsParcial = kmsP;
       consumoMedio = consumoMed;
       capacidade = capac;
       conteudo = co;
       tipoCombustivel = tComb;
   }
   
   public Veiculo(Veiculo v) {
       matricula = v.getMatricula();
       kmsTotal = v.getKmsTotal();
       kmsParcial = v.getKmsParcial();
       consumoMedio = v.getConsumoMedio();
       capacidade = v.getCapacidade();
       conteudo = v.getConteudo();
       tipoCombustivel = v.getTipoCombustivel();
   }
   
   public String getMatricula(){
       return matricula;
   }
   
   public double getKmsTotal(){
       return kmsTotal;
   }
   
   public double getKmsParcial(){
       return kmsParcial;
   }
   
   public double getConsumoMedio(){
       return consumoMedio;
   }
   
   public int getCapacidade(){
       return capacidade;
   }
   
   public int getConteudo(){
       return conteudo;
   }
   
   public int getTipoCombustivel(){
       return tipoCombustivel;
   }
   
   public void setMatricula(String matricula) {
        this.matricula = matricula;
   }
   
   public void setKmsTotal(double kmsTotal) {
       if (kmsTotal>=0) this.kmsTotal = kmsTotal;
   }
   
   public void setKmsParcial(double kmsParcial) {
       if (kmsParcial>=0) this.kmsParcial = kmsParcial;
   }
   
   public void setConsumoMedio(double consumoMedio) {
       if (consumoMedio>=0) this.consumoMedio = consumoMedio;
   }
   
   public void setCapacidade(int capacidade) {
       if (capacidade>=0) this.capacidade = capacidade;
   }
   
   public void setConteudo(int conteudo) {
       if (conteudo>0) {
           if (conteudo>= this.capacidade) this.conteudo = this.capacidade;
           else this.conteudo = conteudo;
       }
   }
   
   public void setTipoCombustivel(int tipoCombustivel) {
       this.tipoCombustivel = tipoCombustivel;
   }
   
   public static double custoMedioLtr(int tipoComb){
       double v = -1;
       switch(tipoComb) {
           case 0 : v = Veiculo.Gasolina95;
                    break;
           case 1 : v = Veiculo.Gasolina98;
                    break;
           case 2 : v = Veiculo.Gasoleo;
       }
       return v;
   }
   
   public static void SetCustoMedioLtr(int tipoComb, double custo){
       switch(tipoComb) {
           case 0 : Veiculo.Gasolina95 = custo;
                    break;
           case 1 : Veiculo.Gasolina98 = custo;
                    break;
           case 2 : Veiculo.Gasoleo = custo;
       }
   } 
   
   public double totalCombustivel(){
       return kmsTotal / 100*getConsumoMedio() * Veiculo.custoMedioLtr(tipoCombustivel);
    }
    
   public double custoMedioKm(){
       return (getConsumoMedio()/100) * Veiculo.custoMedioLtr(tipoCombustivel);
   }
    
   public Veiculo clone(){
       return new Veiculo (this);
    }
    
   public boolean equals (Object o){
        if(o == this){
            return true;
        }
        if ((o == null) || o.getClass() != this.getClass()){
            return false;
        }
        Veiculo n = (Veiculo) o;
        return n.getMatricula().equals(matricula) && n.getKmsTotal() == kmsTotal && n.getKmsParcial() == kmsParcial && n.getConsumoMedio() == consumoMedio && n.getCapacidade() == capacidade && n.getConteudo() == conteudo && n.getTipoCombustivel() == tipoCombustivel;
   }

   public String toString(){
    return matricula + "," + kmsTotal + "," + kmsParcial + "," + consumoMedio + "," + capacidade + "," + conteudo + "," + tipoCombustivel + ".";  
   }
}