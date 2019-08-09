
/**
 * Escreva a descrição da classe Ex1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex1
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private double a, b;

    /**
     * COnstrutor para objetos da classe Ex1
     */
    public Ex1()
    {
        a=1;
        b=1;
    }

    public Ex1(double a, double b){
        this.a=a;
        this.b=b;
    }
    
    public Ex1(Ex1 c){
        
    }
    
    //metodos de instancia
    public Ex1 conjugado(){
        return new Ex1(this.a, this.b);
    }
    
    public void conjugadoSelf(){
        this.b = -this.b;
    }
    
    public Ex1 soma(Ex1 c){
        double na = this.a + c.a;
        double nb = this.b + c.b;
        
        return new Ex1(na, nb);
    }
    
    public double getA(){
        return a;
    }
    
    public double getB(){
        return b;
    }
    
    public String toString(){
        return "Complexo(" + this.a + ", " + this.b + "i)"; 
    }
}
