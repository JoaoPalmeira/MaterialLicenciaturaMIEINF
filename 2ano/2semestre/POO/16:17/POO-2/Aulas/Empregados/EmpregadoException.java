
/**
 * Write a description of class NãoExisteEmpregadoException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EmpregadoException extends Exception {

    private int cod;

    public EmpregadoException(String s, int i) {
        super(s);
        cod = i;
    }
    
    public int getCod() {return cod;}
}
