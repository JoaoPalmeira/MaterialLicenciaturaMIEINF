/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosd;

import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteHandler extends Thread
{
    
    private Socket cliente;
    private HashMap<Integer,Leilao> leiloes;
    private HashMap<String,User> utilizadores;
    private BufferedReader in;
    private PrintWriter out;
    private ReentrantLock lock;
    private int clientID = -1;
    
    
    public ClienteHandler ( Socket cliente , HashMap<Integer,Leilao> leiloes, HashMap<String,User> utilizadores,int i ) throws IOException {
        this.cliente = cliente;
        this.utilizadores = utilizadores;
        this.leiloes = leiloes;
        this.in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        this.out = new PrintWriter(cliente.getOutputStream(),true);
        this.clientID = i;
    }   
    
    public String regista ( String username , String password ) {
            boolean estado = utilizadores.containsKey(username) ;
            if (estado == true) {return "UTILIZADOR JA EXISTENTE" ;}
            else{
            User utilizador = new User(username,password);
            utilizadores.put(username,utilizador);
            utilizadores.get(username).setLigacao(true);
            return "UTILIZADOR REGISTADO";
            }
    }     
    
    
    public String login ( String username, String password ) {
         String pass_aux;
         boolean ligacao;
         boolean estado = utilizadores.containsKey(username) ;
         if (estado == true) {
             
         ligacao = utilizadores.get(username).getLigacao();
         if(ligacao == true) return "[UTILIZADOR JA CONECTADO]";
         pass_aux = utilizadores.get(username).getPassword();
         if ( pass_aux.equals(password) ) {utilizadores.get(username).setLigacao(true) ;
                                          return "[LOGIN FEITO COM SUCESSO]";
         }
         if ( !pass_aux.equals(password) )return "[PALAVRA PASSE INCORRETA]";
                                                                                         
         }            
            return "[NOME DE UTILIZADOR INEXISTENTE]";
        }
    
    public synchronized String comeca_leilao(String username,String item, String valor, String data_fim) throws ParseException{
        Date current_date = new Date();
        Date aux_date;
        String [] aux = data_fim.split("\\/");
                
        
        int res = 0, res_aux = 0, res_aux2 = 0, res_aux3 = 0;
        
        for ( int i = 0 ; i<item.length();i++) {
            if ( item.charAt(i)>= 'a' && item.charAt(i)<='z') res ++;
            
        }

        if (res<item.length()) return "[Item Inválido]";
        else{
            
            for ( int f = 0 ; f < valor.length() ; f++ ) {
                if ( valor.charAt(f)>='0' && valor.charAt(f)<='9') res_aux++;
            }
            
            if ( res_aux < valor.length()) return "[Valor Invalido]";
            else{
                if (data_fim.contains("/")){
                    
                
                    for ( int j = 0 ; j < 3 ; j++){
                        for ( int k = 0 ; k < aux[j].length() ; k++){
                               if (aux[j].charAt(k)>='0' && aux[j].charAt(j)<='9') res_aux2++;
                    }
                        if (aux[j].length()==res_aux2) res_aux3++;
                }
                    if ( res_aux3 <3 ) return "[Data Invalida]";
                    else{
                        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
                        aux_date = df1.parse(data_fim);
                        if ( aux_date.after(current_date)){
                
                        int id_leilao = leiloes.size();
                        id_leilao=id_leilao+1;
                        String lic= "*";
                        Leilao leilao = new Leilao(item,Float.parseFloat(valor),aux_date,lic,username);
                        leiloes.put(id_leilao,leilao);
                
                        return "[LEILAO ADICIONADO COM SUCESSO]";
                }
                }
        }
                else return "[Data Invalida]";
            }
                    
        }
        
        return "[LEILAO NAO FOI ADICIONADO, DADOS INVALIDOS]";
    }
    
    public synchronized String ver_leiloes_indiv (String username) {
        StringBuilder conjunto = new  StringBuilder();
        for(Map.Entry<Integer,Leilao> leilao : leiloes.entrySet()){
            if (leilao.getValue().getLicitacao().equals("+") || leilao.getValue().getLicitacao().equals("-")) {
             conjunto.append(" [ITEM:").append(leilao.getValue().getItem()).append(" | VALOR:").append(leilao.getValue().getValor()).append(" Licitacao mais alta:").append(leilao.getValue().getLicitacao()).append(" Utilizador da licitacao:").append(leilao.getValue().getUsername()).append(" | DATA FIM:").append(leilao.getValue().getData()).append("]\n");
            
            }
            }
       
        return conjunto.toString()+"licitacoes totais";
    }
    
    
    public synchronized String ver_leiloes_ativos(String u){
         StringBuilder conjunto = new StringBuilder();
            
        if ( leiloes != null ) {
            for(Map.Entry<Integer,Leilao> leilao : leiloes.entrySet()){
                if (leilao.getValue().getLicitacao().equals("*")) {
                if (leilao.getValue().getUsername().equals(u)) {  
                                                conjunto.append("[ID Leilao:").append(leilao.getKey()).append(" [ITEM:").append(leilao.getValue().getItem()).append(" | VALOR DA LICITACAO MAIS ALTA:").append(leilao.getValue().getValor()).append(" | DATA FIM:").append(leilao.getValue().getData()).append("| INDISPONIVEL(o leilao é seu)]\n") ;
                                            }
                                            
                else {
                                                conjunto.append("[ID Leilao:").append(leilao.getKey()).append(" [ITEM:").append(leilao.getValue().getItem()).append(" | VALOR DA LICITACAO MAIS ALTA:").append(leilao.getValue().getValor()).append(" | DATA FIM:").append(leilao.getValue().getData()).append("| DISPONIVEL]\n") ;
                                            }
                }
            }
                
        }   
        return conjunto.toString()+"leiloes ativos";
       //return "[NAO HA LEILOES ATIVOS]";
      
    }
    
    public synchronized String fazer_licitacao(String username, String v, String i){
 
        boolean estado;
        try{
        Float valor = Float.parseFloat(v);
        Integer id = Integer.parseInt(i);
                    for ( Map.Entry<Integer,Leilao> leilao : leiloes.entrySet()){
                        if ( leilao.getKey().equals(id)  ) {
                        String it=leilao.getValue().getItem();
                            if (leilao.getValue().getUsername().equals(username)) return "[NAO PODE LICITAR O SEU PROPRIO LEILAO]";
                            if(leilao.getValue().getLicitacao().equals("*")){
                                Servidor.atualizar(it);
                                estado = leilao.getValue().novo_valor(valor,username);
                                if ( estado == true) return "[ LICITACAO FEITA COM SUCESSO ]";
                            }
                        }
                    }
        return "[NAO FOI FEITA LICITACAO, LEILAO NAO EXISTE]";    
        }catch(NumberFormatException e) {
                return "[NAO FOI FEITA LICITACAO,VALORES INVALIDOS]";
    }
    }
    
            
    
    
    
    public synchronized String terminar(String i,String user){
        try{
        Integer id = Integer.parseInt(i);   
            if (Servidor.remover(id,user))
            return "Terminado com sucesso";
            else return "Nao terminado, leilao nao lhe pertence";
         }
        catch(NumberFormatException e) {
                return "[VALOR INVALIDO]";
}
    }
    
    
        public synchronized  String ganho (String username) {
        StringBuilder conjunto = new  StringBuilder();
        for(Map.Entry<Integer,Leilao> leilao : leiloes.entrySet()){
            if(leilao.getValue().getUsername().equals(username)){
            if (leilao.getValue().getLicitacao().equals("Ganho")) {
             conjunto.append(" [ITEM:").append(leilao.getValue().getItem()).append(" | VALOR:").append(leilao.getValue().getValor()).append(" ").append(leilao.getValue().getLicitacao()).append("]\n");
            
            }
            }
        }
        return conjunto.toString()+"leiloes ganhos";
    }
    
 public synchronized String vendido (String username) {
        StringBuilder conjunto = new  StringBuilder();
        for(Map.Entry<Integer,Leilao> leilao : leiloes.entrySet()){
            if(leilao.getValue().getUsername().equals(username)){
            if (leilao.getValue().getLicitacao().equals("Vendido")) {
             conjunto.append(" [ITEM:").append(leilao.getValue().getItem()).append(" | VALOR:").append(leilao.getValue().getValor()).append(" ").append(leilao.getValue().getLicitacao()).append("]\n");
            
            }
            }
        }
        return conjunto.toString()+"leiloes vendidos";
    }   
    
    
    
    
    
    public synchronized boolean hand (String s) throws ParseException{
        boolean hand = true;
        String [] aux = s.split("\\|");
        switch(aux[0]){
            case "REGISTO": out.println(regista(aux[1],aux[2])); break;
            case "LOGIN" :  out.println( login(aux[1],aux[2])); break;
            case "Começa": out.println(comeca_leilao(aux[1],aux[2],aux[3],aux[4]));break;
            case "Geral": out.println(ver_leiloes_ativos(aux[1]));break;
            case "Individual": out.println(ver_leiloes_indiv(aux[1]));break;
            case "Licitar":out.println(fazer_licitacao(aux[1],aux[2],aux[3]));break;
            case "Termina":out.println(terminar(aux[1],aux[2]));break;
            case "Ganho":out.println(ganho(aux[1]));break;
            case "Vendido":out.println(vendido(aux[1]));break;
            case "Sair": utilizadores.get(aux[1]).setLigacao(false); break;
            default: break;
            
        }
        return hand;
    }
    
    
    @Override
    public void run() {
        
        System.out.println("Novo cliente no servidor. ID: "+this.clientID);
        try {
            PrintWriter o = new PrintWriter(cliente.getOutputStream());
            BufferedReader b = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            while(hand(in.readLine())){
                //System.out.println (in.readLine());
                o.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
    
    
}