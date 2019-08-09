/**
 * Hotel Standard, com preço variável dependendo da época
 * @author Rui
 */
public class HotelStandard extends Hotel implements CartaoPontos{

    /** Indica se hotel se encontra em época alta */
    private int pontos;
    private boolean epocaAlta;
    
    /**
     * Construtor vazio
     */
    public HotelStandard() {
        super();
        epocaAlta = false;
    }

    /**
     * Construtor por cópia
     * @param c 
     */
    public HotelStandard(HotelStandard c) {
        super(c);
        this.epocaAlta = c.getEpocaAlta();
    }
    
    /**
     * Construtor por parâmetro
     * @param codigo
     * @param nome
     * @param localidade
     * @param precoQuarto
     * @param epocaAlta 
     */
    public HotelStandard(String codigo, String nome, String localidade
                        , double precoQuarto, boolean epocaAlta, int nquartos) {
        super(codigo, nome, localidade, precoQuarto, nquartos);
        this.epocaAlta = epocaAlta;
    }
    
    /**
     * Calcula o preço de uma noite no hotel
     * @return 
     */
    public double precoQuarto() {
        return super.getPrecoQuarto() + (epocaAlta?20:0);
    }

    /**
     * Indica se hotel se encontra em época alta
     * @return 
     */
    public boolean getEpocaAlta() {
        return epocaAlta;
    }

    /**
     * Define época alta
     * @param epocaAlta 
     */
    public void setEpocaAlta(boolean epocaAlta) {
        this.epocaAlta = epocaAlta;
    }
    
    /**
     * Retorna uma cópia da instância
     * @return 
     */
    public HotelStandard clone() {
        return new HotelStandard(this);
    }

    /**
     * Compara a igualdade com outro objecto
     * @param obj
     * @return 
     */
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        HotelStandard o = (HotelStandard) obj;
        return super.equals(o) && o.getEpocaAlta() == epocaAlta;
    }

    /**
     * Retorna representação textual
     * @return 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Preço final: ").append(precoQuarto()).append("€");        
        return sb.toString();
    }
    
    public void setPontosPorEuro(int pontos)
    {
        this.pontos = pontos;
    }
    
    public int getPontosPorEuro()
    {
        return this.pontos;
    }
}