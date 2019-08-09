
/**
 * Escreva a descrição da classe Habitavel aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Habitavel extends Imovel
{
    public Habitavel(){
        super();
    }
    
    /**
    * Construtor a partir das partes
    */
    public Habitavel(String cod, String tipoI, String ru, String est, double precoP, double precoM, int visualiz, String ma){
        super(cod, tipoI, ru, est, precoP, precoM, visualiz, ma);
    }
    
    /**
     * Construtor de cópia
     */
    public Habitavel(Habitavel h){
        super(h);
    }
    
    /**
     * Retorna uma cópia da instância
     */
    public Habitavel clone(){
        return new Habitavel(this);
    }
    
    /**
     * Compara a igualdade com outro objeto
     */
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Habitavel o = (Habitavel) obj;
        return super.equals(o);             
    }
    
    /**
     * Devolve sobre a forma de uma string toda a informação 
     * atual sobre as lojas habitáveis
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("---------------- LOJA HABITÁVEL ----------------\n");
        s.append(super.toString()).append("\n");
        return s.toString();
    }
}
