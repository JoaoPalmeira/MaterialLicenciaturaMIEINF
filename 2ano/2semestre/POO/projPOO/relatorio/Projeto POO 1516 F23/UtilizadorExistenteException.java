 //Classe que cria a a exceção UtilizadorExistenteException.
 public class UtilizadorExistenteException extends Exception {
        public UtilizadorExistenteException(String m) {
            super(m); // Não funciona corretamente.
        }
    }