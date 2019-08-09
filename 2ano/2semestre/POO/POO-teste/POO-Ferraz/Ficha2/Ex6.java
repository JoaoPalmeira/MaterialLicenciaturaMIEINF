
/**
 * Escreva a descrição da classe Ex6 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Ex6
{
    private static String[] lerpal(){
        Scanner in= new Scanner(System.in);
        String[] palavras= new String[100];
        int i;
        int s=0;
        Stirng a;
        for(i=0;s!=99;i++){
            System.out.println("Introduza uma palavra(fim para sair)");
            a= in.next();
            if(a=="fim"){
                s=99;
            }else{
                palavras[i]=a;
            }
        }
        return palavras;
    }
    
    private static 
