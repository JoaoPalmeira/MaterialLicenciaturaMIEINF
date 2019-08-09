
public class Veiculo
{
    // Variáveis de Instância
    private String matricula;
    private double kmsTotal;
    private double kmsParcial;
    private int capacidade;
    private int conteudo;
    private double consumoMedio;

    // Construtores
    public Veiculo()
    {
        this.matricula = "aa-00-00";
        this.kmsTotal = 0;
        this.kmsParcial = 0;
        this.capacidade = 0;
        this.conteudo = 0;
        this.consumoMedio = 0;
    }
    
    public Veiculo(String matricula, double kmsTotal, double kmsParcial, int conteudo, int capacidade, double consumoMedio){
        this.matricula = matricula;
        this.kmsTotal = kmsTotal;
        this.kmsParcial = kmsParcial;
        this.capacidade = capacidade;
        this.conteudo = conteudo;
        this.consumoMedio = consumoMedio;
    }
    
    public Veiculo(Veiculo veiculo){
        this.matricula = veiculo.getMatricula();
        this.kmsTotal = veiculo.getKmsTotal();
        this.kmsParcial = veiculo.getKmsParcial();
        this.capacidade = veiculo.getCapacidade();
        this.conteudo = veiculo.getConteudo();
        this.consumoMedio = veiculo.getConsumoMedio();
    }
    
    // Métodos de Instância
    public String getMatricula(){
        return this.matricula;
    }
    
    public double getKmsTotal(){
        return this.kmsTotal;
    }
    
    public double getKmsParcial(){
        return this.kmsParcial;
    }
    
    public int getCapacidade(){
        return this.capacidade;
    }
    
    public int getConteudo(){
        return this.conteudo;
    }
    
    public double getConsumoMedio(){
        return this.consumoMedio;
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public void setKmsTotal(double kmsTotal){
        this.kmsTotal = kmsTotal;
    }
    
    public void setKmsParcial(double kmsParcial){
        this.kmsParcial = kmsParcial;
    }
    
    public void setCapacidade(int capacidade){
        this.capacidade = capacidade;
    }
    
    public void setConteudo(int conteudo){
        this.conteudo = conteudo;
    }
    
    public void setConsumoMedio(double consumoMedio){
        this.consumoMedio = consumoMedio;
    }
    
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Veiculo n = (Veiculo) o;
        return n.getMatricula().equals(this.matricula) && n.getKmsTotal() == kmsTotal && n.getKmsParcial() == kmsParcial && n.getCapacidade() == capacidade
            && n.getConteudo() == conteudo && n.getConsumoMedio() == consumoMedio;
    }
    
    public String toString (){
        return matricula+" "+kmsTotal+" "+kmsParcial+" "+capacidade+" "+conteudo+" "+consumoMedio;
    }
    
    public Veiculo clone(){
        return new Veiculo(this); 
    }
    
    // Exercícios
    public void abastecer(int litros){
        if (litros + this.getConteudo() > this.getCapacidade()) 
            this.setConteudo(this.getCapacidade());
        else this.setConteudo(litros + this.getConteudo());
    }
    
    public void resetKms(){
        this.setKmsParcial (0);
        this.setConsumoMedio (0);
    }
    
    public double autonomia(){
        return (this.getConteudo() * 100 / this.getConsumoMedio());
    }
    
    public void registarViagem(int kms, double consumo){
        double c = this.getConsumoMedio() * this.getKmsParcial() / 100 + consumo;
        this.setKmsParcial (this.getKmsParcial() + kms);
        this.setConsumoMedio (c / this.getKmsParcial() * 100);
        this.setConteudo (this.getConteudo() - (int) consumo);
        this.setKmsTotal (this.getKmsTotal() + kms);
    }
    
    public boolean naReserva(){
        return (this.getConteudo() < 10);
    }
    
    public double totalCombustivel (double custoLitro){
        return this.getKmsTotal() * this.getConsumoMedio() / 100 * custoLitro;
    }
    
    public double custoMedioKm (double custoLitro){
        return this.getConsumoMedio() / 100 * custoLitro;
    }
}
