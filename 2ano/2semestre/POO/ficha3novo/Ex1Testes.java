
/**
 * Escreva a descrição da classe Ex1Testes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex1Testes
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    public static void main(String[] args){
        Ex1 c1 = new Ex1();
        Ex1 c2 = new Ex1(2,3);
        Ex1 c3 = c1.soma(c2);
        
        System.out.println(c3.toString());
    }
}
