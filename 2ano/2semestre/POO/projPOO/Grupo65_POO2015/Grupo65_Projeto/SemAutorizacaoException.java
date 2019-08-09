/**
 * Exceção para tratamento de erros.
 */
public class SemAutorizacaoException extends Exception {
        public SemAutorizacaoException(String m) {
            super(m);
        }
}
