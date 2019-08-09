/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosd;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class Leilao
{
    //private int id ;
    private String username;
    private String item;
    private float valor;
    private Date data_fim;
    private String licitacao;
    private ReentrantLock lock;
    
    public Leilao ( String item , float valor, Date data_fim,String licitacao,String user ) {
        this.item = item;
        this.valor = valor;
        this.data_fim = data_fim;
        this.licitacao=licitacao;
        this.username=user;
        lock = new ReentrantLock();
    }

    public String getLicitacao() {
        return licitacao;
    }
    
    public String getUsername() {
        return username;
    }

    public Date getData_fim() {
        return data_fim;
    }
    
    public String getItem () {
        return item ;
    }
    
    public void setItem( String item ) {
        this.item = item  ;
    }
    
    public float getValor() {
        return valor ; 
    }
    
    public void setValor ( float valor ) {
        this.valor = valor ;
    }
    
    public Date getData (){
        return data_fim ;
    }
    
    public void setData ( Date data ) {
        this.data_fim = data_fim;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public void setLicitacao(String licitacao) {
        this.licitacao = licitacao;
    }
    
    public boolean novo_valor( float v, String username ) {
        lock.lock();
        boolean estado=false;
        try {
            if ( this.valor < v ) {
                this.valor=v;
                Leilao l = new Leilao(this.item,v,this.data_fim,"+",username);
                Servidor.adiciona(l);
                estado=true;
            }
        } finally {
            lock.unlock();
        }
        return estado;
    }
}
