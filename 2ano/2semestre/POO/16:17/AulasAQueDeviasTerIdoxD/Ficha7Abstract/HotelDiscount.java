
/**
 * Escreva a descrição da classe HotelStandard aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class HotelDiscount extends Hotel
{
    private boolean epocaAlta;
    private int taxaDesconto;
    
    public HotelDiscount() {
        super();
        this.epocaAlta = false;
        this.taxaDesconto = 10;
    }

    public HotelDiscount(HotelDiscount h) {
        super(h); //funciona, pois há compatibilidade de tipos entre a subclasse e a superclasse. Para ver o HotelStandard mesmo quando antes é visto como Hotel ao nível do HoteisInc é preciso fazer um cast
        this.epocaAlta = h.getEpocaAlta();
        this.taxaDesconto = h.getTaxaDesconto();
    }
    
    public HotelDiscount(String codigo, String nome, String localidade, double precoQuarto, int numQuartos, int estrelas, boolean epocaAlta, int taxaDesconto) {
        super(codigo,nome,localidade,precoQuarto,numQuartos,estrelas);
        this.epocaAlta = epocaAlta;
        this.taxaDesconto = taxaDesconto;
    }
    
    public boolean getEpocaAlta(){
        return this.epocaAlta;
    }
    
    public void setEpocaAlta(boolean epocaAlta){
        this.epocaAlta = epocaAlta;
    }
    
    public int getTaxaDesconto(){
        return this.taxaDesconto;
    }
    
    public void setTaxaDesconto(int taxaDesconto){
        this.taxaDesconto = taxaDesconto;
    }
    
    public double getPrecoQuarto(){
        if(this.epocaAlta) return super.getPrecoQuarto() + 20 - this.taxaDesconto;
        else return super.getPrecoQuarto() - this.taxaDesconto;
        
        /* ou
         * return super.getPrecoQuarto() + (this.epocaAlta ? 20 : 0);
         */
    }
    
    
    public double precoNoite(){
        return getPrecoQuarto()+(this.epocaAlta ? 20 : 0)-this.taxaDesconto;
    }
    
    public Hotel clone() {
        return new HotelDiscount(this);
    }
    
    
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        HotelDiscount o = (HotelDiscount) obj;
        return o.getEpocaAlta() == this.epocaAlta && o.getTaxaDesconto() == this.taxaDesconto; // && o.super().equals(super());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Hotel '").append(nome).append("'\n");
        //sb.append("Hotel '").append(nome).append("'\n");
        return sb.toString();
    }
}
