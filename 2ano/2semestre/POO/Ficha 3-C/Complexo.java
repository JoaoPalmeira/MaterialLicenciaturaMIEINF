
/**
 * Write a description of class Complexo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Complexo
{
    // variáveis de instância
    private double a;
    private double b;
    
    //construtores
    public Complexo() {
        this.a = 0;
        this.b = 0;
    }
    
    public Complexo(double na, double nb) {
        this.a = na;
        this.b = nb;
    }
    
    public Complexo(Complexo c2) {
        this.a = c2.getA();
        this.b = c2.getB();
    }
    
    //métodos
    public double getA() {
        return a;
    }
    
    public double getB() {
        return b;
    }
    
    public void setA(double na) {
        this.a = na;
    }
    
    public void setB(double nb) {
        this.b = nb;
    }
    
    public boolean equals(Complexo c2) {
        return (this.a == c2.getA() && this.b == c2.getB());
    }
    
    public String toString() {
        return new String(this.a + " + " + this.b + "i");
    }
    
    public Complexo clone() {
        return new Complexo(this);
    }
    
    public Complexo conjugado(Complexo complexo) {
        Complexo c2 = new Complexo();
        c2.setA(complexo.getA());
        c2.setB(-complexo.getB());
        return c2;
    }
    
    public Complexo soma(Complexo complexo) {
        Complexo c2 = new Complexo();
        c2.setA(this.a + complexo.getA());
        c2.setB(this.b + complexo.getB());
        return c2;
    }
    
    public Complexo produto(Complexo complexo) {
        double a = this.a*complexo.getA() - this.b*complexo.getB();
        double b = this.b*complexo.getA() + this.a*complexo.getB();
        return new Complexo(a,b);
    }
    
    public Complexo reciproco(Complexo complexo){
        double den = this.a*this.a + this.b*this.b;
        double a = this.a / den;
        double b = -this.b / den;
        return new Complexo(a,b);
    }
        
}
