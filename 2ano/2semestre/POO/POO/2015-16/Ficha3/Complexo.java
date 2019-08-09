import java.util.Scanner;
import java.util.Arrays;
/**
 * Escreva a descrição da classe Complexo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Complexo
{
    // variáveis de instância
    private double a;
    private double b;
    
    // construtores
    public Complexo(){
        a = 0.0;
        b = 0.0;
    }
    
    public Complexo (double na, double nb) {
        a = na;
        b = nb;
    }
    
    public Complexo(Complexo c2) {
        a = c2.getA();
        b = c2.getB();
    }
    
    public double getA () {
        return a;
    }
    
    public double getB () {
        return b;
    }
    
    public void setA (double a) {
        this.a = a;
    }
    
    public void setB (double b) {
        this.b = b;
    }
    
    public Complexo conjugado (Complexo complexo) {
        Complexo c2 = new Complexo ();
        c2.setA(complexo.getA());
        c2.setB(-complexo.getB());
        return c2;
    }
    
    public Complexo soma (Complexo complexo) {
        double c = complexo.getA();
        double d = complexo.getB();
        Complexo c2 = new Complexo(this.a + c, this.b + d);
        return c2;
    }
    
    public Complexo produto (Complexo complexo) {
        double c = complexo.getA();
        double d = complexo.getB();
        Complexo c2 = new Complexo(this.a * c - this.b * d, this.b * c + this.a * d);
        return c2;
    }
    
    public Complexo reciproco (Complexo complexo) {
        double c = complexo.getA();
        double d = complexo.getB();
        Complexo c2 = new Complexo(this.a / ((this.a*this.a) + (this.b*this.b)), -(this.a / ((this.a*this.a) + (this.b*this.b))));
        return c2;
    }
}