
/**
 * Escreva a descrição da classe Circulo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Circulo
{
    private double x;
    private double y;
    private double raio;
    
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getRaio(){
        return this.raio;
    }
    public void setX (int nx){
        this.x=nx;
    }
    public void setY (int ny){
        this.y=ny;
    }
    public void setRaio (int nr){
        this.raio=nr;
    }
    
    public void alteraCentro(double dx, double dy){
        this.x=dx;
        this.y=dy;
    }
    
    public double calculaArea(){
        return (3.14*(this.raio)*(this.raio));
    }
    
    public double calculaPerimetro(){
        return (2*3.14*(this.raio));
    }
    
    public boolean equals(Object o){
        if(o==this){
            return true;
        }
        if (o ==null || o.getClass() != this.getClass()){
            return false;
        }
        Circulo c = (Circulo) o;
        return (c.getX() == this.x && c.getY() == this.y && c.getRaio() == this.raio);
    }
    public String toString(){
        return "X: " + x + ", Y: " + y + ", R: " + raio;
    }
}
