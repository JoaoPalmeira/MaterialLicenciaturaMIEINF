
/**
 * Write a description of class Pixel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pixel
{
    // instance variables
    double x;
    double y;
    int cor;
    
    // constructors
    public Pixel() {
        new Pixel(0,0,0);
    }
    
    public Pixel(double x, double y, int cor) {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }
    
    public Pixel(Pixel p2) {
        this.x = p2.getX();
        this.y = p2.getY();
        this.cor = p2.getCor();
    }
    
    // getters & setters
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public int getCor() {
        return this.cor;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void mudarCor(int cor) {
        if (cor >= 0 && cor <= 15) this.cor = cor;
    }
    
    // other methods
    public void desloca(double x, double y) {
        this.x += x;
        this.y += y;
    }
    
    public String nomeCor() {
        if(this.cor == 0) return "Preto";
        if(this.cor == 1) return "Azul marinho";
        if(this.cor == 2) return "Verde escuro";
        if(this.cor == 3) return "Azul petróleo";
        if(this.cor == 4) return "Castanho";
        if(this.cor == 5) return "Púrpura";
        if(this.cor == 6) return "Verde oliva";
        if(this.cor == 7) return "Cinza claro";
        if(this.cor == 8) return "Cinza escuro";
        if(this.cor == 9) return "Azul";
        if(this.cor == 10) return "Verde";
        if(this.cor == 11) return "Azul turquesa";
        if(this.cor == 12) return "Vermelho";
        if(this.cor == 13) return "Fúcsia";
        if(this.cor == 14) return "Amarelo";
        if(this.cor == 15) return "Branco";
        return "";
    }
}
