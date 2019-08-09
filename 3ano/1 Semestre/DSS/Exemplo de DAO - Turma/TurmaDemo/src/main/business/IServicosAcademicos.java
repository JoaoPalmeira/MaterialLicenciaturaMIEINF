package main.business;

import java.util.Random;

/**
 * Serviço externo que busca os dados a uma base de dados da universidade
 * @author ruicouto
 */
public class IServicosAcademicos {
    private RegistoCurricular _unnamed_RegistoCurricular_;

    /**
     * Método que daria o número associado a um dado nome
     * @param nome
     * @return 
     */
    public static int getNumero(String nome) {
            Random r = new Random();
            //...
            return 50000+r.nextInt(50000);
    }

    /**
     * Método que daria a informação de um aluno, dado o seu número
     * @param numero
     * @return 
     */
    public static Aluno getAluno(int numero) {
        Random r = new Random();
            Aluno a = new Aluno("aluno",numero , "email");
            //...
            return a;
    }
}