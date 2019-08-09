import java.util.Arrays;
/**
 * Write a description of class Stand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stand
{
    //variáveis de instância
    private String nomeStand;
    private Veiculo[] carros;
    private int nveiculos;   //número veículos no stand
  
    //capacidade do stand (em número de veículos)
    private int capacidade;
  
    //capacidade inicial do Stand: valor por omissão
    public static final int capacidade_inicial = 10;
    
    //Construtores
    public Stand(){
        this.nomeStand = new String();
        this.carros = new Veiculo[capacidade_inicial];
        this.nveiculos=0;
        this.capacidade=capacidade_inicial;
    }
    public Stand(String nome, int capacidade){
        this.nomeStand=nome;
        this.carros=new Veiculo[capacidade_inicial];
        this.capacidade=capacidade;
        this.nveiculos=0;
    }
    public Stand(Stand s){
        this.nomeStand  = s.getNomeStand();
        this.carros     = s.getCarros();
        this.capacidade = s.getCapacidade();
        this.nveiculos  = s.getNVeiculos();
    }
    public String getNomeStand(){
        return this.nomeStand;
    }
    public Veiculo[] getCarros(){
        int i;
        Veiculo[] aux= new Veiculo[this.capacidade];
        for(i=0;i<this.nveiculos;i++){
            aux[i]=(this.carros[i]).clone();
        }
        return aux;
    }
    public int getNVeiculos(){
        return this.nveiculos;
    }
    public int getCapacidade(){
        return this.capacidade;
    }
    public void setNomeStand(String nomeStand){
            this.nomeStand=nomeStand;
    }
    public void setCarros(Veiculo[] carros){
        this.carros=carros;
    }
    public void setNveiculos(int nveiculos){
        this.nveiculos=nveiculos;
    }
    public void setCapacidade(int capacidade){
        this.capacidade=capacidade;
    }
    
    
    //funçoes
    public void insereVeiculo(Veiculo v) {
        if(nveiculos==capacidade){}
        else{
            carros[nveiculos++]=v.clone();
        }
    }
    public boolean existeVeiculo(Veiculo v) {
        int i;
        boolean flag=false;
        for(i=0;i<this.nveiculos && carros[i]!=null;i++){
            if(carros[i].equals(v)) {flag=true;}
        }
        return flag;
    }
    public Veiculo veiculoComMaisKms() {
        int i;
        double r;
        double g=0;
        Veiculo v=new Veiculo();
    
        for(i=0;i<this.capacidade && carros[i]!=null ;i++){
            r=carros[i].getKmt();
            if(g<r){
                g=r;
                v=carros[i];
            }
        }
        return v;
    }
    public Veiculo veiculoMaisGastador() {
        int i;
        double r;
        double g=0;
        Veiculo v=new Veiculo();
        for(i=0;i<this.capacidade;i++){
            r=carros[i].getConsumoMedio();
            if(g<r){
            g=r;
            v=carros[i];
            }
        }
        return v;
    }
    public double totalKmsTodosVeiculos() {
        int i;
        double r=0;
        for(i=0;i<this.capacidade;i++){
            r+=carros[i].getKmt();
            
        }
        return r;
    }
    
    
    //Equals, toString && clone
    /**
     * equals
     * 
     */
    public boolean equals(Object o) {
        if (this==o)
            return true;
        if((o==null||(this.getClass()!=o.getClass())))
            return false;
        else{
            Stand  s = (Stand) o;
            return(this.nomeStand.equals(s.getNomeStand()) && Arrays.equals(this.carros,s.getCarros()) && this.nveiculos==s.getNVeiculos() && this.capacidade==s.getCapacidade());
        }
    }
    
    /**
     * toString
     */

    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("Nome: "+this.nomeStand+"\n");
        sb.append("Lista de carros: ");
        for( int i=0;i<this.nveiculos;i++)
          sb.append(this.carros[i].toString()+"\n");
      
        sb.append("Numero de veículos "+this.nveiculos+"\n");
        sb.append("Capacidade "+this.capacidade+"\n");  
        return sb.toString();
    }
    
    /**
     * clone
     */
    public Stand clone() {
        return new Stand(this);
    }
}
