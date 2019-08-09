
/**
 * Escreva a descrição da classe Complexo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Complexo
{
    //variaveis de instancia
    private double a;
    private double b;
    
    //construtores
    public Complexo() {
        a = 0;
        b = 0;
    }
    
    public Complexo(double na, double nb) {
        a = na;
        b = nb;
    }
    
    public Complexo(Complexo c2) {
        a = c2.getA();
        b = c2.getB();
    }
    
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
    
    public Complexo conjugado() {
        Complexo c2 = new Complexo();
        c2.setA(this.a);
        c2.setB(-this.b);
        return c2;
    }
    
    public Complexo soma(Complexo complexo) {
        double c = complexo.getA();
        double d = complexo.getB();
        Complexo c2 = new Complexo(this.a + c, this.b + d);
        return c2;
    }
    
    public Complexo produto(Complexo complexo) {
        double c = complexo.getA();
        double d = complexo.getB();
        double na = a*c - b*d;
        double nb = b*c + a*d;
        return new Complexo(na, nb);
    }
    
    public Complexo reciproco() {
        double den = this.a*this.a + this.b*this.b;
        double a = this.a / den;
        double b = -this.b / den;
        return new Complexo(a,b);
    }
    
    public Complexo clone(){
        return new Complexo(this);
    }
    
    public boolean equals(Object o){
        if(o==this) {
            return true;
        }
        if(o==null || o.getClass() != this.getClass()) {
            return false;
        }
        Complexo n = (Complexo) o;
        return n.getA() == a && n.getB() == b;
    }
    
    public String toString() {
        String sign = "+";
        if(b<0) {
            sign = " ";
        }
        return a+sign+b+"i";
    }
}
