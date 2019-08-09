
/**
 * Escreva a descrição da classe Encomenda aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Encomenda
{
    private String nome;
    private int NIF;
    private String morada;
    private int numE;
    private LocalDate data;
    private LinhaE[] le;
    private int qt; //quantidade do array
    
    public Encomenda(){
        this.nome="";
        this.NIF=0;
        this.morada="";
        this.numE=0;
        this.data=null;
        this.le = new LinhaE[];
        this.qt = 0;
    }
    
      
}
