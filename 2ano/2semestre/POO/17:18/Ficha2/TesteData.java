import java.time.LocalDate;
/**
 * Escreva a descrição da classe TesteData aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class TesteData
{
    public static void main(String[] args){
        LocalDate[] datas = new LocalDate[10];
        
        datas[0] = LocalDate.of(2018,2,18);
        datas[1] = LocalDate.of(2018,1,18);
        datas[2] = LocalDate.of(2017,12,24);
        datas[3] = LocalDate.of(2016,5,20);
        datas[4] = LocalDate.of(2018,2,1);
        
        System.out.println("Tamanho : " + datas.length);
        
        
        /*CodDatas cd = new CodDatas (20);
        
        Scanner sc = new Scanner(System.in);
        int ano = sc.nextInt();
        int mes = sc.nextInt();
        int dia = sc.nextInt();*/
    }
}
