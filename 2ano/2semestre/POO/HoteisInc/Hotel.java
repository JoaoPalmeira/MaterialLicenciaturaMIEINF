/**
 * Classe base Hotel
 * @author Rui Couto
 * @version 1.0
 */
public abstract class Hotel {
    /** O código do hotel */
    private String codigo;
    /** O nome do hotel */
    private String nome;
    /** Localidade do hotel */
    private String localidade;
    /** Preço base por quarto */
    private double precoQuarto;
    /** Numero de quartos */
    private int numeroQuartos;
    
    /**
     * Cria uma instância de hotel
     */
    public Hotel() {
        this.codigo = "n/a";
        this.nome = "n/a";
        this.localidade = "n/a";
        this.precoQuarto = 0;
        this.numeroQuartos = 0;
    }

    /**
     * Construtor por cópia.
     * @param c 
     */
    public Hotel(Hotel c) {
        this.codigo = c.getCodigo();
        this.nome = c.getNome();
        this.localidade = c.getLocalidade();
        this.precoQuarto = c.getPrecoQuarto();
        this.numeroQuartos = c.getNumeroQuartos();
    }

    /**
     * Construtor por parametro
     * @param codigo
     * @param nome
     * @param localidade
     * @param precoQuarto 
     */
    public Hotel(String codigo, String nome, String localidade, double precoQuarto, int numQuartos) {
        this.codigo = codigo;
        this.nome = nome;
        this.localidade = localidade;
        this.precoQuarto = precoQuarto;
        this.numeroQuartos = numQuartos;
    }
    
    
    /**
     * Calcula o preço de uma noite no hotel
     * @return 
     */
    public abstract double precoQuarto();
    
    /**
     * Obter o código do hotel
     * @return 
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Definir o código do hotel
     * @param codigo 
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obter o nome do hotel
     * @return 
     */
    public String getNome() {
        return nome;
    }

    /**
     * Definir o nome do hotel
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroQuartos(int nquartos)
    {
      this.numeroQuartos = nquartos;
    }
    
    /**
     * Obter a localidade do hotel
     * @return 
     */
    public String getLocalidade() {
        return localidade;
    }
    
    /**
     * Definir a localidade do hotel
     * @param localidade 
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    /**
     * Obter o preço base de um quarto
     * @return 
     */
    public double getPrecoQuarto() {
        return precoQuarto;
    }

    /**
     * Definir o preço do quarto
     * @param precoQuarto 
     */
    public void setPrecoQuarto(double precoQuarto) {
        this.precoQuarto = precoQuarto;
    }

    public int getNumeroQuartos()
    { return this.numeroQuartos; }
    /**
     * Devolve uma cópia desta instância
     * @return 
     */
    public abstract Hotel clone();

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
        Hotel o = (Hotel) obj;
        return o.getCodigo().equals(codigo) && o.getNome().equals(nome) && 
                o.getLocalidade().equals(localidade) && o.getPrecoQuarto() == precoQuarto;
    }

    /**
     * Devolve uma representação no formato textual
     * @return 
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hotel '").append(nome).append("'\n");
        sb.append("(").append(codigo).append(") ").append(localidade);
        sb.append("Preço por quarto: ").append(precoQuarto).append("€");
        return sb.toString();
    }

    /**
     * Código de hash
     * @return 
     */
    public int hashCode() {
        return codigo.hashCode();
    }
}