
/**
 * Escreva a descrição da classe Discount aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Discount extends Hotel
{
   private double ocupacao; //% ocupação
   //ou
   //private int nQuartosOcupados;

   public Discount(){
       super();
       this.ocupacao = 0;
   }
   
   public Discount(String codigo, String nome, String localidade, double precoQuarto, int nquartos, double ocupacao){
      super(codigo, nome, localidade, precoQuarto, nquartos);
      this.ocupacao = ocupacao;
   }
   
   public double precoQuarto(){
       double p = super.getPrecoQuarto();
       return 0.5 * p + 0.4 * p * ocupacao;
   }
   
   public Discount clone(){
       return this;
   }
   
   
   public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Discount o = (Discount) obj;
        return o.getCodigo().equals(codigo) && o.getNome().equals(nome) && 
               o.getLocalidade().equals(localidade) && o.getPrecoQuarto() == precoQuarto &&
               o.getNquartos() == nquartos && o.getOcupacao() == ocupacao;
   }
   
   
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HotelDiscount '").append(nome).append("'\n");
        sb.append("(").append(codigo).append(") ").append(localidade);
        sb.append("Preço por quarto: ").append(precoQuarto).append("€");
        sb.append("Número de quartos: ").append(nquartos);
        sb.append("Ocupação. ").append(ocupacao).append("%");
        return sb.toString();
    }
   
   public int getNquartos(){
       return nquartos;
   }
   
   public double getOcupacao(){
       return ocupacao;
   }
   
   public void setNquartos (int nquartos){
       this.nquartos = nquartos;
   }
   
   public void setOcupacao (double ocupacao){
       this.ocupacao = ocupacao;
   }
   
   //Falta:
   //clone
   //equals
   //toString
   //get & set
   //construtor por cópia
}