import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Veículo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veiculo
{
   private String matricula;
   private double kmsTotal;
   private double kmsParcial;
   private double consumoMedio;
   private int capacidade;
   private int conteudo;
   
   public Veiculo(){
       matricula = "00-AA-00";
       kmsTotal = 0.0;
       kmsParcial = 0.0;
       consumoMedio = 0.0;
       capacidade = 0;
       conteudo = 0;
   }
   
   public Veiculo (String mat, double kmsT, double kmsP, double cM, int cap, int cont) {
       matricula = mat;
       kmsTotal = kmsT;
       kmsParcial = kmsP;
       consumoMedio = cM;
       capacidade = cap;
       conteudo = cont;
   }
   
   public Veiculo (Veiculo v2) {
       matricula = v2.getMatricula();
       kmsTotal = v2.getKmsTotal();
       consumoMedio = v2.getConsumoMedio();
       capacidade = v2.getCapacidade();
       conteudo = v2.getConteudo();
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
   
   public void setMatricula (String matricula) {
       this.matricula = matricula;
   }
   
   public void setKmsTotal (double kmsTotal) {
       if (kmsTotal >= 0) this.kmsTotal = kmsTotal;
   }
   
   public void setKmsParcial (double kmsParcial) {
       if (kmsParcial >= 0) this.kmsParcial = kmsParcial;
   }
   
   public void setConsumoMedio (double consumoMedio) {
       if (consumoMedio >= 0) this.consumoMedio = consumoMedio;
   }
   
   public void setCapacidade (int capacidade) {
       if (capacidade >= 0) this.capacidade = capacidade;
   }
   
   public void setConteudo (int conteudo) {
       if (conteudo >= 0) { 
          if (conteudo >= this.capacidade) this.conteudo = this.capacidade;
          else this.conteudo = conteudo;
       }
   }
   
   public void abastecer (int litros){
       if (litros + conteudo >= capacidade) conteudo = capacidade;
       else conteudo += litros;
   }
   
   public void resetKms(){
       kmsParcial = 0;
       consumoMedio = 0;
   }
   
   public double autonomia(){
       double auton = conteudo / (consumoMedio/100);
       return auton;
   }
   
   public void registarViagem (int kms, double consumo) {
       if (consumo >= conteudo) conteudo = 0;
       else conteudo -= consumo;
       consumoMedio = ((consumoMedio/100) * kmsParcial + consumo) / (kmsParcial + kms);
       kmsTotal += kms;
       kmsParcial += kms;
   }
   
   public boolean naReserva(){
       if (conteudo < 10) return true;
       else return false;
   }
   
   public double totalCombustivel (double custoLitro) {
       double totalC = (consumoMedio/100) * kmsTotal * custoLitro;
       return totalC;
   }
   
   public double custoMedioKm (double custoLitro) {
       double custoK = (consumoMedio/100) * custoLitro;
       return custoK;
   }
}

