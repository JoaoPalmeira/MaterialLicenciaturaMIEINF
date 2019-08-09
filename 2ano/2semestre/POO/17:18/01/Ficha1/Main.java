import java.lang.String;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args){
        int trigger;
        Scanner in = new Scanner(System.in);
        System.out.println("Vai comceçar a festa....");
        do {
            System.out.println("1 - Celsius para Farenheit.");
            System.out.println("2 - Maior de dois numeros.");
            System.out.println("3 - Ler Nome/Saldo e imprime ambos.");
            System.out.println("4 - Converte de euros para libras a uma taxa.");
            System.out.println("5 - Dados dois numeros da a media dos dois, e ordena por ordem decrescente.");
            
            System.out.print("Insira numero da pegunta(O para sair): ");
            trigger = in.nextInt();
            
            if(trigger==1){
                Ex1 ex1 = new Ex1();
                System.out.println("Insira temperatura em graus: ");
                double temC = in.nextInt();
                double tempF = ex1.celsiusParaFarenheit(temC);
                System.out.println("Temperatura: " +temC+ " para Farenheit: "+tempF); 
            }
            
            else if(trigger==2){
                Ex2 ex2 = new Ex2();
                System.out.print("Insere um numero:");
                int n1 = in.nextInt();
                System.out.print("Insere um numero:");
                int n2 = in.nextInt();
                int maior = ex2.maximoNumeros(n1,n2);
                System.out.println("O maior numero de "+n1+ " e "+n2+ " é o: " +maior);
            }
            
            else if(trigger==3){
                Ex3 ex3 = new Ex3();
                System.out.print("Insere Nome:");
                String nome = in.next();
                System.out.print("Insere Saldo: ");
                double saldo = in.nextDouble();
                System.out.println(ex3.criaDescricaoConta(nome,saldo));
            }
            
            else if(trigger==4){
                Ex4 ex4 = new Ex4();
                System.out.print("Introduz valor em euros: ");
                double euroS = in.nextDouble();
                System.out.print("Introduz taxa de conversao: ");
                double taxa = in.nextDouble();
                double res = ex4.eurosParaLibras(euroS,taxa);
                System.out.println("O valor em euro para libras é: " +res);   
            }
            
            else if(trigger==5){
                Ex5 ex5 = new Ex5();
                System.out.print("Insere um numero:");
                int n1 = in.nextInt();
                System.out.print("Insere um numero:");
                int n2 = in.nextInt();
                double media = ex5.media(n1,n2);
                if(n1>=n2) {
                    System.out.println("Ordem decrescente: " +n1+ "," +n2+ " e a sua media: " +media);
                }
                else {
                    System.out.println("Ordem decrescente: " +n2+ "," +n1+ " e a sua media: " +media);
                }
            }
            else if (trigger!=0){
                System.out.println("Numero errado, volta a selecionar");
            }
        }while(trigger!=0);
        
        in.close();
        
        System.out.println("Obrigado e volte sempre !!!");
    }
}
