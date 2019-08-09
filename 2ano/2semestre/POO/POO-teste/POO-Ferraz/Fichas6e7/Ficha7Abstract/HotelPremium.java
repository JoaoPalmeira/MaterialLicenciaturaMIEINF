
/**
 * Escreva a descrição da classe HotelStandard aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class HotelPremium extends Hotel
{
    private boolean epocaAlta;
    private int taxaLuxo;
    private int pontos;
    
    public HotelPremium() {
        super();
        this.epocaAlta = false;
        this.taxaLuxo = 10;
        this.pontos = 0;
    }

    public HotelPremium(HotelPremium h) {
        super(h); //funciona, pois há compatibilidade de tipos entre a subclasse e a superclasse. Para ver o HotelStandard mesmo quando antes é visto como Hotel ao nível do HoteisInc é preciso fazer um cast
        this.epocaAlta = h.getEpocaAlta();
        this.taxaLuxo = h.getTaxaLuxo();
        this.pontos = h.getPontos();
    }
    
    public HotelPremium(String codigo, String nome, String localidade, double precoQuarto, int numQuartos, int estrelas, boolean epocaAlta, int taxaLuxo, int pontos) {
        super(codigo,nome,localidade,precoQuarto,numQuartos,estrelas);
        this.epocaAlta = epocaAlta;
        this.taxaLuxo = taxaLuxo;
        this.pontos = pontos;
    }
    
    public boolean getEpocaAlta(){
        return this.epocaAlta;
    }
    
    public void setEpocaAlta(boolean epocaAlta){
        this.epocaAlta = epocaAlta;
    }
    
    public int getTaxaLuxo(){
        return this.taxaLuxo;
    }
    
    public void setTaxaLuxo(int taxaLuxo){
        this.taxaLuxo = taxaLuxo;
    }
    
    public double getPrecoQuarto(){
        if(this.epocaAlta) return super.getPrecoQuarto() + 20 + this.taxaLuxo;
        else return super.getPrecoQuarto() + this.taxaLuxo;
        
        /* ou
         * return super.getPrecoQuarto() + (this.epocaAlta ? 20 : 0);
         */
    }
    
    
    public double precoNoite(){
        return getPrecoQuarto()+(this.epocaAlta ? 20 : 0)+this.taxaLuxo;
    }
    
    public Hotel clone() {
        return new HotelPremium(this);
    }
    
        public int getPontos(){
        return this.pontos;
    }
    
    public void setPontos(int pontos){
        this.pontos = pontos;
    }
    
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        HotelPremium o = (HotelPremium) obj;
        return o.getEpocaAlta() == this.epocaAlta && o.getTaxaLuxo() == this.taxaLuxo && o.getPontos()==this.pontos; // && o.super().equals(super());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Hotel '").append(nome).append("'\n");
        //sb.append("Hotel '").append(nome).append("'\n");
        return sb.toString();
    }
}
