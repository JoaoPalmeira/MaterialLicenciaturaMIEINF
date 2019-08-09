/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socios;

import java.util.Objects;

/**
 *
 * @author jeter15
 */
public class Quota {
    
    private String mes;
    
    private double valor;

    
    public Quota(String mes, double valor) {
        this.mes = mes;
        this.valor = valor;
    }
    
    public Quota(){
        
        this.mes = "";
        this.valor=0;
        
    }
    
    public Quota(Quota q){
        
        this.mes = q.getMes();
        this.valor = q.getValor();
        
    }
    
    public String getMes() {
        return mes;
    }

    public double getValor() {
        return valor;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.mes);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quota other = (Quota) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.mes, other.mes)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
    
    
    
    
    
    
}
