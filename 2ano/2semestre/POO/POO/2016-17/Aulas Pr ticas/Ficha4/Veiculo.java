
/**
 * Escreva a descrição da classe Veiculo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veiculo
{
    private double kmsTotal;
    private double kmsParcial;
    private String matricula;
    private int capacidade;
    private int conteudo;
    private double consumoMedio;
    
    public Veiculo(){
       matricula="00-00-AA";
       kmsTotal=0.0;
       kmsParcial=0.0;
       consumoMedio=0.0;
       capacidade=50;
       conteudo=0; 
    }
    
    public Veiculo(Veiculo v){
       matricula=v.matricula;
       kmsTotal=v.kmsTotal;
       kmsParcial=v.kmsParcial;
       consumoMedio=v.consumoMedio;
       capacidade=v.capacidade;
       conteudo=v.conteudo;
    }
    
    public Veiculo(String matricula, double kmsTotal, double
    kmsParcial, double consumoMedio, int capacidade, int conteudo){
       this.matricula=matricula;
       this.kmsTotal=kmsTotal;
       this.kmsParcial=kmsParcial;
       this.consumoMedio=consumoMedio;
       this.capacidade=capacidade;
       this.conteudo=conteudo;
    }
    
    public String toString(){
        return new String(matricula + " " + kmsTotal + " " + kmsParcial + " " +
        consumoMedio + " " + capacidade + " " + conteudo);
    }
    
    public Veiculo clone(){
        return new Veiculo(this);
    }
    
    public boolean equals (Veiculo v){
        if(!(matricula.equals(v.matricula))) return false;
        if(kmsTotal!=v.kmsTotal) return false;
        if(kmsParcial!=v.kmsParcial) return false;
        if(consumoMedio!=v.consumoMedio) return false;
        if(capacidade!=v.capacidade) return false;
        if(conteudo!=v.conteudo) return false;
        return true;
    }
    
    public void setMatricula (String m){
        matricula = m;
    }
    
    public void setKmsTotal (double kmsT){
        kmsTotal = kmsT;
    }
    
    public void setKmsParcial (double kmsP){
        kmsParcial = kmsP;
    }
    
    public void setConsumoMedio (double cM){
        consumoMedio = cM;
    }
    
    public void setCapacidade (int cap){
        capacidade = cap;
    }
    
    public void setConteudo (int cont){
        conteudo = cont;
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
    
    public void abastecer(int litros){
        if(conteudo + litros <= capacidade) conteudo = conteudo + litros;
    }
    
    public void resetKms(){
        setKmsParcial(0.0);
        setConsumoMedio(0.0);
    }
   
    public double autonomia(){
        double autonomia;
        if(conteudo == 0) autonomia = 0.0;
        else{
            autonomia = (conteudo*100)/consumoMedio;
        }
        return autonomia;
    }
    
    public void registarViagem(int kms, double consumo){
       if(conteudo - (consumo/kms) >= 0) conteudo = conteudo - ((int)consumo/kms);
       else{
           conteudo = 0;
       }
       consumoMedio = ((consumoMedio/100) * kmsParcial + (consumo/kms)) / (kmsParcial + kms);
       kmsTotal = kmsTotal + kms;
       kmsParcial = kmsParcial + kms;
    }
    
    public boolean naReserva(){
       if(conteudo<10.0) return true;
       else return false;
    }
    
    public double totalCombustivel(double custoLitro){
       return ((consumoMedio/100) * kmsTotal * custoLitro);
    }
    
    public double custoMedioKm(double custoLitro){
       return ((consumoMedio/100)*custoLitro);
    }
}
