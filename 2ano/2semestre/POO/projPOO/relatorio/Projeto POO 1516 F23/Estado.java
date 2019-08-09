
public enum Estado
{
    VENDA, ALUGUER, TRESPASSE, OCUPADO;
    //lasse que cria uma interface que verifica o estado do imóvel.
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