package trabalhosd;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Pedro
 */
public class Servidor {

    public static HashMap<Integer,Leilao> leiloes   = new HashMap<Integer,Leilao> () ;
    public static HashMap<String,User> utilizadores = new HashMap<String,User> () ;
    
    
    public static void main ( String[] args ) throws IOException, ParseException {
       //HashMap<Integer,Leilao> leiloes = new HashMap<Integer,Leilao> () ;
       DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
       leiloes.put(1,new Leilao("l1",10,df1.parse("12/12/2017"),"*","d"));
       leiloes.put(2,new Leilao("l2",5,df1.parse("12/12/2017"),"*","p"));
       leiloes.put(3,new Leilao("l3",7,df1.parse("12/12/2017"),"*","d"));
       leiloes.put(4,new Leilao("l4",8,df1.parse("12/12/2017"),"*","s"));
       
       //leiloes.put(4,new Leilao(4,"l4",8,df1.parse("12/12/2017"),"f"));
       //HashMap<String,User> utilizadores = new HashMap<String,User> () ;
       utilizadores.put("d",new User("d","d"));
       utilizadores.put("p",new User("p","p"));
       utilizadores.put("s",new User("s","s"));
       ServerSocket ss = new ServerSocket(4321);
        int id=0;
        while (true) {
        Socket clientSocket = ss.accept();
        ClienteHandler thread = new ClienteHandler(clientSocket,leiloes,utilizadores, id++);
        thread.start();
    }
  }
    
    public static void adiciona(Leilao l){
        int id_leilao=leiloes.size()+1;
        leiloes.put(id_leilao, l);
    }
    
    public static void atualizar(String it){
        for(Map.Entry<Integer,Leilao> leilao1 :leiloes.entrySet()){
            if ((leilao1.getValue().getItem().equals(it))&&(!leilao1.getValue().getLicitacao().equals("*"))){
                if(leilao1.getValue().getLicitacao().equals("+")){
                    leilao1.getValue().setLicitacao("-");
                }
            }
        }
    }
    
    public static boolean remover(Integer id,String user){
        boolean estado=false;
        for(Map.Entry<Integer,Leilao> leilao1 :leiloes.entrySet()){
            if(leilao1.getKey().equals(id)){
            if(leilao1.getValue().getUsername().equals(user) && leilao1.getValue().getLicitacao().equals("*")){
                String it = leilao1.getValue().getItem();
                leilao1.getValue().setLicitacao("Vendido");
                for(Map.Entry<Integer,Leilao> leilao2 :leiloes.entrySet()){
                    if(leilao2.getValue().getItem().equals(it) && leilao2.getValue().getLicitacao().equals("-")){
                    leiloes.remove(leilao2);
                        }
                    if (leilao2.getValue().getItem().equals(it) && leilao2.getValue().getLicitacao().equals("+")){
                    leilao2.getValue().setLicitacao("Ganho");
                    } 
            }
                return true;
            }
            estado = false;
        }
        }    
        return estado;
    }
    
}
