
public class Veiculo
{
    private String matricula;
    private double kmsTotal;
    private double kmsParcial;
    private double consumoMedio;
    private int capacidade;
    private int conteudo;
    private int tipoCombustivel; //0-Gasolina 95, 1-Gasolina 98, 2- Gasoleo
    public static double Gasolina95; //=1.3;
    public static double Gasolina98; //=1.7;
    public static double Gasoleo; //=1.1;
    static 
    {
        Gasolina95=1.3;
        Gasolina98=1.7;
        Gasoleo = 1.1;
    }
    
    public Veiculo() 
    {
        new Veiculo("00-AA-00",0,0,0,0,0,0);
    }
    
    public Veiculo(String matricula, double kmsTotal, double kmsParcial, double consumoMedio, int capacidade, int conteudo, int tipoCombustivel) 
    {
        this.matricula = matricula;
        this.kmsTotal = kmsTotal;
        this.kmsParcial = kmsParcial;
        this.consumoMedio = consumoMedio;
        this.capacidade = capacidade;
        this.conteudo = conteudo;
        this.tipoCombustivel = tipoCombustivel;
    }
    
    public Veiculo(Veiculo v2) 
    {
        this.matricula = v2.getMatricula();
        this.kmsTotal = v2.getKmsTotal();
        this.kmsParcial = v2.getKmsParcial();
        this.consumoMedio = v2.getConsumoMedio();
        this.capacidade = v2.getCapacidade();
        this.conteudo = v2.getConteudo();
        this.tipoCombustivel = v2.tipoCombustivel;
    }

    public String getMatricula() 
    {
        return this.matricula;
    }
    
    public double getKmsTotal() 
    {
        return this.kmsTotal;
    }
    
    public double getKmsParcial() 
    {
        return this.kmsParcial;
    }
    
    public double getConsumoMedio() 
    {
        return this.consumoMedio;
    }
    
    public int getCapacidade() 
    {
        return this.capacidade;
    }
    
    public int getConteudo() 
    {
        return this.conteudo;
    }

    public int getTipoCombustivel()
    {
        return this.tipoCombustivel;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public void setKmsTotal(double kmsTotal) {
        if(kmsTotal>=0) 
        this.kmsTotal = kmsTotal;
    }
    
    public void setKmsParcial(double kmsParcial) {
        if (kmsParcial>=0) 
        this.kmsParcial = kmsParcial;
    }
    
    public void setConsumoMedio(double consumoMedio) {
        if (consumoMedio>=0) 
        this.consumoMedio = consumoMedio;
    }
    
    public void setCapacidade(int capacidade) {
        if (capacidade>=0) 
        this.capacidade = capacidade;
    }
    
    public void setConteudo(int conteudo) {
        if(conteudo > 0) {
            if(conteudo <= this.capacidade) this.conteudo = conteudo;
            else this.conteudo = this.capacidade;
        }
    }
    
    public void setTipoCombustivel (int tipoCombustivel)
    {
        if (tipoCombustivel==0||tipoCombustivel==1||tipoCombustivel==2);
        this.tipoCombustivel = tipoCombustivel;
    }
    
    public boolean equals (Object o)
   {
       if (this == o) return true;
       if ((o==null)||(o.getClass()!=this.getClass())) return false;
       else { Veiculo v = (Veiculo) o;
                 return (v.getMatricula()==this.matricula && v.getKmsTotal()==this.kmsTotal && v.getKmsParcial()==this.kmsParcial && v.getConsumoMedio()==this.consumoMedio && v.getCapacidade()==this.capacidade && v.getConteudo()==this.conteudo && v.getTipoCombustivel()==this.tipoCombustivel);
                }
   }
   
   public Veiculo clone ()
   {
       return new Veiculo (this );
   }
   
   public String toString()
   {
       StringBuilder s = new StringBuilder();
       s.append("Veiculo");
       s.append("Matricula: "+this.matricula);
       s.append("KmsTotal: "+this.kmsTotal);
       s.append("KmsParcial: "+this.kmsParcial);
       s.append("Consumo Medio: "+this.consumoMedio);
       s.append("Capacidade: "+this.capacidade);
       s.append("Conteudo: "+this.conteudo);
       s.append("TipoCombustivel: "+this.tipoCombustivel);
       return s.toString();
   }
    
   public void abastecer(int litros) 
    {
        if ((litros + this.conteudo) <= this.capacidade) this.conteudo += litros;
        else this.conteudo = this.capacidade;
    }
   
    public void resetKms() 
    {
        this.kmsParcial = 0;
        this.consumoMedio = 0;
    }
    
    public double autonomia()
    {
        return (this.conteudo / this.consumoMedio * 100);
    }
    
    public void registarViagem(int kms, double consumo)
    {
         if (this.kmsParcial == 0) this.consumoMedio = consumo * 100 / kms;
         else this.consumoMedio = (this.consumoMedio*this.kmsParcial + 100*consumo) / (kms + this.kmsParcial);
         this.kmsParcial += kms;
         this.kmsTotal += kms;
         if (this.conteudo >= consumo) this.conteudo -= consumo;
         else this.conteudo = 0;
    }
    
    public boolean naReserva() {
        return (this.conteudo < 10);
    }
    
    public double totalCombustivel(double custoLitro)
    {
        return (this.kmsTotal * this.consumoMedio * custoLitro / 100);
    }
    
    public double custoMedioLtr()
    {
        if (this.tipoCombustivel==0) return Gasolina95;
        if (this.tipoCombustivel==1) return Gasolina98;
        else return Gasoleo;
    }
    
    public void SetCustoMedioLtr(double custo)
    {
        if (this.tipoCombustivel==0) Gasolina95=custo;
        if (this.tipoCombustivel==1) Gasolina98=custo;
        else Gasoleo = custo;
    }
    
    public double totalCombustivel()
    {
        double i = (this.consumoMedio*this.kmsTotal)/100;
        return i*custoMedioLtr();
    }
    
    public double custoMedioKm()
    {
        return ((this.consumoMedio)/100 * custoMedioLtr());
    }
}