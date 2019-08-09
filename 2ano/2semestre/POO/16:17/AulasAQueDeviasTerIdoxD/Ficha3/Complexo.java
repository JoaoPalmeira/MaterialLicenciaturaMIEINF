import java.lang.Math;
/**
 * Escreva a descrição da classe Complexo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Complexo
{
    private double r;
    private double i;
    
    public Complexo(){
        this.r = 0;
        this.i = 0;
    }
    /**ou
    public Complexo(){
        this(0,0); 
    }*/
    
    public Complexo(Complexo c){
        this.r = c.getR();
        this.i = c.getI();
    }
    
    public Complexo(double r, double i){
        this.r = r;
        this.i = i;
    }
    
    public void setR (double v){
        this.r = v;
    }
    
    public double getR(){
        return this.r;
    }
    
    public void setI (double v){
        this.i = v;
    }
    
    public double getI(){
        return this.i;
    }
     
    public Complexo conjugado(){
        return new Complexo(this.r, -this.i);
    }
    
    public Complexo soma (Complexo complexo){
        double nr = this.r + complexo.getR();
        double ni = this.i + complexo.getI();
        return new Complexo(nr,ni);
    }
    
    public Complexo produto (Complexo complexo){
        double nr = (this.r*complexo.getR()-this.i*complexo.getI());
        double ni = (this.i*complexo.getR()+this.r*complexo.getI());        
        return new Complexo(nr, ni);
    }
    
    public Complexo reciproco(){
        double nr = this.r/(Math.pow(this.r,2) + Math.pow(this.i,2));
        double ni = this.i/(Math.pow(this.r,2) + Math.pow(this.i,2));
        return new Complexo(nr, ni);
    }
    
    public Complexo clone(){
        return new Complexo(this);
    }
    
    public boolean equals(Object o){
        if(this == o){return true;}
        if(o == null || o.getClass() != this.getClass()){return false;}
        Complexo c = (Complexo) o;
        return c.getR() == r && c.getI() == i;
    }
    
    public String toString(){
        return "Complexo: " + r + ", " + i;
    }
}
