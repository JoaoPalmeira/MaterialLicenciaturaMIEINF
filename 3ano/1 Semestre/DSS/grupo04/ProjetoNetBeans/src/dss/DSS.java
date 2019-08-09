/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dss;

/**
 *
 * @author sofia
 */
public class DSS {

   private final Apartamento a;
   private final String[] opcoes = {"Pagamentos","Despesas"};
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        (new DSS()).run();
    }

    public DSS() {
        this.a = new Apartamento();
    }

    // Arraque da app
    private void run() {
        int op;
            
        do {
            op = this.doMenu();
            switch (op) {
                case 1: consultarPagamentosEfetuados(); 
                        break;
                case 2: consultarDespesas();
                        break;
            }
        } while (op!=0);
        System.out.println("Volte sempre!");
    }
    
    //Métodos auxiliares
    
    /**Lista de Pagamentos Efetuados */
    
    a.consultarPagamentosEfetuados();
}
