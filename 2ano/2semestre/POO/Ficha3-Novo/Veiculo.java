
/**
 * Escreva a descrição da classe Veiculo aqui.
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
    
    public Veiculo() {
        new Veiculo("00-AA-00",0,0,0,0,0);
    }
    
    public Veiculo(String matricula, double kmsTotal, double kmsParcial, double consumoMedio, int capacidade, int conteudo) {
        this.matricula = matricula;
        this.kmsTotal = kmsTotal;
        this.kmsParcial = kmsParcial;
        this.consumoMedio = consumoMedio;
        this.capacidade = capacidade;
        this.conteudo = conteudo;
    }
    
    public Veiculo(Veiculo v1) {
        this.matricula = v1.getMatricula();
        this.kmsTotal = v1.getKmsTotal();
        this.kmsParcial = v1.getKmsParcial();
        this.consumoMedio = v1.getConsumoMedio();
        this.capacidade = v1.getCapacidade();
        this.conteudo = v1.getConteudo();
    }
    
    //methods
    //getters & setters
    public String getMatricula() {
        return this.matricula;
    }
    
    public double getKmsTotal() {
        return this.kmsTotal;
    }
    public double getKmsParcial() {
        return this.kmsParcial;
    }
    public double getConsumoMedio() {
        return this.consumoMedio;
    }
    public int getCapacidade() {
        return this.capacidade;
    }
    public int getConteudo() {
        return this.conteudo;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setKmsTotal(double kmsTotal) {
        if(kmsTotal>=0) this.kmsTotal = kmsTotal;
    }
    public void setKmsParcial(double kmsParcial) {
        if(kmsParcial>=0) this.kmsParcial = kmsParcial;
    }
    public void setConsumoMedio(double consumoMedio) {
        if(consumoMedio>=0) this.consumoMedio = consumoMedio;
    }
    public void setCapacidade(int capacidade) {
        if(capacidade>=0) this.capacidade = capacidade;
    }
    public void setConteudo(int conteudo) {
        if(conteudo > 0) {
            if(conteudo >= this.capacidade) this.conteudo = this.capacidade;
            else this.conteudo = conteudo;
        }
    }
    
    //exercicios
    public void abastecer(int litros) {
        if ((litros + this.conteudo) >= this.capacidade) this.conteudo = this.capacidade;
        else this.conteudo += litros;
    }
    
    public void resetKms() {
        this.kmsParcial = 0;
        this.consumoMedio = 0;
    }
    
    public double autonomia() {
        return (this.conteudo / this.consumoMedio * 100);
    }
    
    public void registarViagem(int kms, double consumo) {
        if (this.kmsParcial == 0) this.consumoMedio = consumo * 100 / kms;
        else this.consumoMedio = (this.consumoMedio*this.kmsParcial + 100*consumo) / (kms + this.kmsParcial);
        this.kmsParcial += kms;
        this.kmsTotal += kms;
        if (this.conteudo <= consumo) this.conteudo = 0;
        else this.conteudo -= consumo;
    }
    
    public boolean naReserva() {
        return (this.conteudo < 10);
    }
    
    public double totalCombustivel(double custoLitro) {
        return (this.kmsTotal * this.consumoMedio * custoLitro / 100);
    }
    
    public double custoMedioKm(double custoLitro) {
        return (this.consumoMedio * custoLitro / 100);
    }
    
    public Veiculo clone(){
        return new Veiculo(this);
    }
    
    public boolean equals(Object o){
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Veiculo n = (Veiculo) o;
        return n.getMatricula().equals(matricula) && n.getKmsTotal() == kmsTotal && n.getKmsParcial() == kmsParcial && n.getConsumoMedio() == consumoMedio && n.getCapacidade() == capacidade && n.getConteudo() == conteudo;
    }
    
    public String toString() {
        String sign = ",";
        return matricula+sign+kmsTotal+sign+kmsParcial+sign+consumoMedio+sign+capacidade+sign+conteudo;
    }
}
