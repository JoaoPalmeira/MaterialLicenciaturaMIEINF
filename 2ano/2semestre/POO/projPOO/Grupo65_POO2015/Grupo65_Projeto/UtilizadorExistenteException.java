/**
 * Exceção para tratamento de erros.
 */
public class UtilizadorExistenteException extends Exception {
       public UtilizadorExistenteException(String m) {
           super(m); // Não funciona corretamente.
       }
}