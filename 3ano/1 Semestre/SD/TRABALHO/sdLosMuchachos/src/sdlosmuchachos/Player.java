package sdlosmuchachos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BruPal
 */
public class Player {
    
    private String username;
    private String password;
    private boolean ligacao;
 
    
    public Player(String username, String password)
    {
        this.username = username ; 
        this.ligacao = false;
        this.password = password;   
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



