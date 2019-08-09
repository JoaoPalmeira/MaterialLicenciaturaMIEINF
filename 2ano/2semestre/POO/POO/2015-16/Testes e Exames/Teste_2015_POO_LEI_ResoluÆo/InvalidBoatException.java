import java.util.*;
import java.io.*;

public class InvalidBoatException extends Exception implements Serializable{
    public InvalidBoatException(){
        super();
    }
    
    public InvalidBoatException(String s){
        super(s);
    }
}