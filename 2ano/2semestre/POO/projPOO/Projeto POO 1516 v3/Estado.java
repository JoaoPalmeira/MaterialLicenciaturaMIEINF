
/**
 * Enumeration class Estado - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
//isto
public enum Estado
{
    VENDA, ALUGUER, TRESPASSE, OCUPADO;
    
    @Override
    public String toString() {
        switch(this) {
            case VENDA: 
                return "venda";
            case ALUGUER: 
                return "aluguer";
            case TRESPASSE:
                return "trespasse";
            case OCUPADO:
                return "ocupado";
            default: 
                System.out.println("Estado Inválido");
                break;
        }
        return "";
    }
    
}


