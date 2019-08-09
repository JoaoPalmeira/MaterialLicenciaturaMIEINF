
/**
 * Exemplo de aplicação com menu em modo texto.
 * Fica como exercício completar a classe.
 * 
 * @author José Creissac Campos
 * @version 2.1
 */

import java.io.IOException;
import java.util.Scanner;

public class EmpregadosApp {
   
    // Construtor privado (não queremos instâncias!...)
    private EmpregadosApp() {}
    // A EmpresaPOO tem toda a 'lógica de negócio'.
    private static EmpresaPOO tab;
    // Menus da aplicação
    private static Menu menumain, menuad;

    // Método principal
    public static void main(String[] args) {
        carregarMenus();
        carregarDados();
        do {
            menumain.executa();
            switch (menumain.getOpcao()) {
                case 1: inserirEmp();
                        break;
                case 2: consultarEmp();
                        break;
                case 3: totalSalarios();
                        break;
                case 4: totalGestores();
                        break;
                case 5: totalPorTipo();
                        break;
                case 6: totalKms();
            }
        } while (menumain.getOpcao()!=0);
        try {
            tab.gravaObj("estado.tabemp");
            tab.log("log.txt", true);
        }
        catch (IOException e) {
            System.out.println("Não consegui gravar os dados!");
        }
        System.out.println("Até breve!...");     
    }
    
    // Métodos auxiliares
    
    private static void carregarMenus() {
        String[] ops = {"Inserir Empregado",
                        "Consultar Empregado",
                        "Calcular total de salários",
                        "Calcular total de Gestores",
                        "Calcular total de empregados por tipo",
                        "Calcular total de Kms percorridos"};
        String [] opsad = {"Adicionar Gestor",
                           "Adicionar Motorista",
                           "Adicionar Empregado Normal"
                          };

        menumain = new Menu(ops);
        menuad = new Menu(opsad);
    }
    
    private static void carregarDados() {
        try {
            tab = EmpresaPOO.leObj("estado.tabemp");
        }
        catch (IOException e) {
            tab = new EmpresaPOO();
            System.out.println("Não consegui ler os dados!\nErro de leitura.");
        } 
        catch (ClassNotFoundException e) {
            tab = new EmpresaPOO();
            System.out.println("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
        }
        catch (ClassCastException e) {
            tab = new EmpresaPOO();
            System.out.println("Não consegui ler os dados!\nErro de formato.");        
        }
    }
    
    private static void inserirEmp() {
        Empregado emp;
        Scanner scin = new Scanner(System.in);
            
        menuad.executa();
        if (menuad.getOpcao() != 0) {
            String cod, nome;
            int dias;
            double aux;
            
            System.out.print("Código: ");
            cod = scin.nextLine();
            System.out.print("Nome: ");
            nome = scin.nextLine();
            System.out.print("Dias de trabalho: ");
            dias = scin.nextInt();
            
            switch (menuad.getOpcao()) {
                case 1: System.out.print("Prémio: ");
                        aux = scin.nextDouble();
                        emp = new Gestor(cod, nome, dias, aux);
                        break;
                case 2: System.out.print("Kms: ");
                        aux = scin.nextDouble();
                        emp = new Motorista(cod, nome, dias, aux);
                        break;
                default: emp = new Normal(cod, nome, dias);
                        break;
            }
            tab.addEmpregado(emp);
        } else {
            System.out.println("Inserção cancelada!");
        }
        scin.close();
    }
    
    private static void consultarEmp() {
        String cod;
        Empregado emp;
        Scanner scin = new Scanner(System.in);
        
        System.out.print("Código: ");
        cod = scin.nextLine();
        try {
            emp = tab.getEmpregado(cod);
            System.out.println(emp.toString());
        }
        catch (EmpregadoException e){
            if (e.getCod()==1)
                System.out.println(e.getMessage());
            else
                System.out.println("Erro desconhecido!");
        }
        finally {
            scin.close();
        }
    }
    
    private static void totalSalarios() {
        System.out.println("Total de salários: "+tab.totalSalarios());
    }
    
    private static void totalGestores() {
        System.out.println("Opção ainda não implementada.");
    }
    
    private static void totalPorTipo() {
        System.out.println("Opção ainda não implementada.");
    }
    
    private static void totalKms() {
        System.out.println("Opção ainda não implementada.");
    }
}


