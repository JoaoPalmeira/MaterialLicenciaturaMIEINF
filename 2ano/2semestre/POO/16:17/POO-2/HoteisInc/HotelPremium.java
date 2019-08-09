/**
 * Hotel Premium, acresce uma taxa ao hotel
 * @author Rui Couto
 * @version 1.0
 */
public class HotelPremium extends Hotel implements CartaoPontos{

    /** Taxa de luxo */
    private double taxa;
    private int pontos;
    
    /**
     * Construtor vazio
     */
    public HotelPremium() {
        super();
        taxa = 1;
    }

    /**
     * Construtor por cópia
     * @param c 
     */
    public HotelPremium(HotelPremium c) {
        super(c);
        this.taxa = c.getTaxa();
    }

    /**
     * Construtor por parâmetro
     * @param codigo
     * @param nome
     * @param localidade
     * @param precoQuarto
     * @param taxa 
     */
    public HotelPremium(String codigo, String nome, String localidade, double precoQuarto, double taxa, int nquartos) {
        super(codigo, nome, localidade, precoQuarto , nquartos);
        this.taxa = taxa;
    }

    
    /**
     * Calcula o preço de uma noite no hotel
     * @return 
     */
    public double precoQuarto() {
        return super.getPrecoQuarto()*taxa;
    }
    
    /**
     * Obter a taxa
     * @return 
     */
    public double getTaxa() {
        return taxa;
    }

    /**
     * Definir a taxa
     * @param taxa 
     */
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    /**
     * Devolve uma cópia da instância
     * @return 
     */
    public HotelPremium clone() {
        return new HotelPremium(this);
    }

    /**
     * Compara a igualdade com outro objecti
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
        HotelPremium o = (HotelPremium) obj;
        return super.equals(o) && o.getTaxa() == taxa;
    }

    /**
     * Devolve uma representação textual do hotel
     * @return 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append("Taxa: ").append(taxa).append("%");
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