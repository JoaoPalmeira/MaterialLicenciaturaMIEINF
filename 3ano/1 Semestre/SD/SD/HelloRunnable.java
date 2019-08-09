
/**
 * Escreva a descrição da classe HelloRunnable aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class HelloRunnable implements Runnable {
     public void run() {
         System.out.println("Hello from a thread!");
     }
     public static void main(String args[]) {
         (new Thread(new HelloRunnable())).start();
     }
}
