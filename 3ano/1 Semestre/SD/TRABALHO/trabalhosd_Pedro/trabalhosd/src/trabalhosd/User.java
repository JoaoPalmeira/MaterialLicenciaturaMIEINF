/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosd;

import java.util.HashMap;


public class User
{
    private String username;
    private String password;
    private boolean ligacao;
    private HashMap<String,Leilao> leiloes;
    
    public User(String utilizador, String password)
    {
        this.username = username ; 
        this.ligacao = false;
        this.password = password;
        
        leiloes = new HashMap<>();
    }

    public String getPassword(){
        return this.password;
    }
    
    public boolean getLigacao() {
        return this.ligacao;
    }
    
    public void setLigacao(boolean ligacao){
        this.ligacao = ligacao;
    }

    }

