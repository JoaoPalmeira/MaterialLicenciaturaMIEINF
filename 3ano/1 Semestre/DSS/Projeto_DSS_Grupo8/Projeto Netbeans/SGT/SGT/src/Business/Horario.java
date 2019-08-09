package Business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joaopalmeira
 */
public class Horario {
    
    private int dia; // Dia da semana
    private int inicio; // Hora de inicio
    private int fim; // Hora de fim
    
    
    public Horario(int dia, int inicio, int fim) {
        this.dia = dia;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    
    public Horario(Horario a) {
        dia = a.getDia();
        inicio = a.getInicio();
        fim = a.getFim();
    }
    
    
    public int getDia() {
        return dia;
    }
    
    
    public int getInicio() {
        return inicio;
    }

    
    public int getFim() {
        return fim;
    }
    
  
    
    public boolean compara(Horario a) {
        boolean ret = false;
        
        if (dia == a.getDia()) {
            int aInicio = a.getInicio();
            int aFim = a.getFim();
            
            if ((aInicio >= inicio && aInicio < fim) ||
                    (aFim > inicio && aFim <= fim) ||
                    (aInicio <= inicio && aFim >= fim)) {
                ret = true;
            }
        }
        
        return ret;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || (this.getClass() != o.getClass())) {
            return false;
        }
        
        Horario a = (Horario)o;
        
        return inicio == a.getInicio() && fim == a.getFim() &&
                dia == a.getDia();
    }
    
    
    @Override
    public Horario clone() {
        return new Horario(this);
    }
}
