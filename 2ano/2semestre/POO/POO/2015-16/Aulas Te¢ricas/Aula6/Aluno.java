
/**
 * Classe que representa um aluno.
 * Com base no exemplo utilizado na aula de 29/02/2016 de POO.
 * 
 * @author José C. Campos 
 * @version 20160229
 */

public class Aluno {

    private static final double NOTA_PARA_PASSAR = 10.0;
    
    // Variáveis de instãncia

    private String nome;
    private int numero;
    private double nota = 0.0; // Deverá ser assegurado um valor entre 0 e 20
    
    // Construtores
    
    /** 
     * Construtor vazio.
     * Neste caso, declarado como privado para não ser possível construir alunos 
     * sem indicar nome, número e nota. 
     */
    private Aluno() {
        this("", 0, 0.0);
    }
    
    /** 
     * Construir um aluno.
     * 
     * @param nome o nome do aluno
     * @param numero o numero do aluno
     * @param nota a nota do aluno
     */
    public Aluno(String nome, int numero, double nota) {
        this.nome = nome;
        this.numero = numero;
        this.setNota(nota);
    }
    
    /** 
     * Construtor de cópia.
     * 
     * @param a o aluno a ser copiado
     */
    public Aluno(Aluno a) {
        this(a.getNome(), a.getNumero(), a.getNota());
        // ou... (mas versa acima reutiliza melhor o código)
        // this.nome = a.getNome();
        // this.numero = a.getNumero();
        // this.nota = a.getNota();
    }
    
    // Métodos instância
    
    /**
     * @return o nome do aluno
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * @return o número do aluno
     */
    public int getNumero() {
        return numero;
    }
    
    /**
     * @return a nota do aluno
     */
    public double getNota() {
        return nota;
    }
    
    /**
     * Mudar a nota do aluno.
     * 
     * @param nota a nova nota (ATENÇÃO: se não for entre 0.0 e 20.0, não é feita a alteração!)
     */
    public void setNota(double nota) {
        if (nota>=0.0 && nota <= 20.0)
            this.nota = nota;
    }
    
    /**
     * Saber se o aluno passa.
     * 
     * @return true se o aluno tem para passar 
     */
    public boolean passa() {
        return this.nota >= Aluno.NOTA_PARA_PASSAR;
    }
    
    /**
     * Subir a nota
     */
    public void sobeNota(int n) {
        this.nota += n;
        if (this.nota>20) this.nota = 20;
        if (this.nota<0) this.nota = 0;
    }
    /**
     * @return uma cópia do aluno
     */
    public Aluno clone() {
        return new Aluno(this);
    }
    
    /**
     * Método de comparação.
     * 
     * @param o o objecto a comparar
     * @return true se forem iguals; false noutro caso
     */
    public boolean equals(Object o) {
        if(this==o)
            return true;
        if(o==null || this.getClass()!=o.getClass())
            return false;
        Aluno a = (Aluno) o;
        return this.nome.equals(a.getNome()) && // Strings comparam-se com equals!
               this.numero==a.getNumero() && 
               this.nota==a.getNota();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("Aluno(Num: ");
        sb.append(this.numero);
        sb.append(", Nome: ");
        sb.append(this.nome);
        sb.append(", Nota: ");
        sb.append(this.nota);
        sb.append(")");
        return sb.toString();
    }
    
    public int CompareTo(Aluno a) {
        if (this.equals(a)) return 0;
        if (numero>a.getNumero()) return 1;
        return -1;
    }
}
