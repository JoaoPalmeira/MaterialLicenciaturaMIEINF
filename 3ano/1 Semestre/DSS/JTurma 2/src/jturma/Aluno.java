package jturma;


/**
 * Write a description of class Aluno here.
 * 
 * @author José Creissac Campos
 * @version 1.1 (act. 09/2016)
 */

public class Aluno 
{
    // variáveis de instância

    private String nome;
    private String numero;
    private int notaT, notaP;

    // Construtores

    public Aluno() {
       this("", "", 0, 0);
    }

    public Aluno(String numero,String nome,int notaT,int notaP) {
        this.nome = nome;
        this.numero = numero;
        this.notaT = notaT;
        this.notaP = notaP;
    }


    // M�todos de inst�ncia

    public String getNome() {
        return this.nome;
    }

    public String getNumero() {
        return this.numero;
    }

    public int getMedia() {
        return (this.notaT+this.notaP)/2;
    }

    public int getNotaT() {
        return this.notaT;
    }


    public int getNotaP() {
        return this.notaP;
    }

    public void setNotaT(int nota) {
        this.notaT = nota;
    }

    public void setNotaP(int nota) {
        this.notaP = nota;
    }

    public boolean passa() {
        return this.getMedia()>=10;
    }
    
    public boolean equals(Object o) {
        boolean b=false;

        if (o!= null && o instanceof Aluno) {
            Aluno a = (Aluno)o;
            b = this.numero.equals(a.getNumero()) &&
                this.nome.equals(a.getNome()) &&
                this.notaT == a.getNotaT() &&
                this.notaP == a.getNotaP();
        }
        return b;
    }
    
    public Object clone() {
        return new Aluno(this.numero, this.nome, 
                                      this.notaT, this.notaP);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("Aluno(");
        sb.append(this.numero);
        sb.append(",");
        sb.append(this.nome);
        sb.append(",");
        sb.append(this.notaT);
        sb.append(",");
        sb.append(this.notaP);
        sb.append(")");
        return sb.toString();
    }
}
