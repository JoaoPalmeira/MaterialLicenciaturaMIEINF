
/**
 * Escreva a descrição da classe HotelStandard aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class HotelStandard extends Hotel implements CartaoPontos
{
    private boolean epocaAlta;
    private int pontos;
    
    public HotelStandard() {
        super();
        this.epocaAlta = false;
        this.pontos = 0;
    }

    public HotelStandard(HotelStandard h) {
        super(h); //funciona, pois há compatibilidade de tipos entre a subclasse e a superclasse. Para ver o HotelStandard mesmo quando antes é visto como Hotel ao nível do HoteisInc é preciso fazer um cast
        this.epocaAlta = h.getEpocaAlta();
        this.pontos = h.getPontos();
    }
    
    public HotelStandard(String codigo, String nome, String localidade, double precoQuarto, int numQuartos, int estrelas, boolean epocaAlta, int pontos) {
        super(codigo,nome,localidade,precoQuarto,numQuartos,estrelas);
        this.epocaAlta = epocaAlta;
        this.pontos = pontos;
    }
    
    public boolean getEpocaAlta(){
        return this.epocaAlta;
    }
    
    public void setEpocaAlta(boolean epocaAlta){
        this.epocaAlta = epocaAlta;
    }
    
    public double getPrecoQuarto(){
        if(this.epocaAlta) return super.getPrecoQuarto() + 20;
        else return super.getPrecoQuarto();
        
        /* ou
         * return super.getPrecoQuarto() + (this.epocaAlta ? 20 : 0);
         */
    }
    
    
    public double precoNoite(){
        return getPrecoQuarto()+(this.epocaAlta ? 20 : 0);
    }
    
    public Hotel clone() {
        return new HotelStandard(this);
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
        HotelStandard o = (HotelStandard) obj;
        return o.getEpocaAlta() == this.epocaAlta && o.getPontos() == this.pontos; // && o.super().equals(super());
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //sb.append("Hotel '").append(nome).append("'\n");
        //sb.append("Hotel '").append(nome).append("'\n");
        return sb.toString();
    }
}
