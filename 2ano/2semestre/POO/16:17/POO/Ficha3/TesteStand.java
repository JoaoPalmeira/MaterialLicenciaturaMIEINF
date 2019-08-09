
/**
 * Classe de teste das classes Stand e Veículo.
 * 
 * Neste exercício faça:
 * 
 * 1a) forneça o código necessário (nas classes Stand e TesteStand) para que 
 *    código funcione correctamente
 * 1b) compile, execute e coloque mais testes no método main
 * 
 * 2) Crie, no método main, um sistema de menus, por forma a que seja
 *    o utilizador a inserir os dados que pretende testar.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TesteStand {

  public static void main(String[] args) {
    Veiculo v1, v2, v3, v4, v5,v6;
    Veiculo d1, d2, d3, d4, d5;
    Stand veiculos_utilitarios;
    Stand veiculos_desportivos;
    
    //chamar os construtores (completar!)
    v1 = new Veiculo(123.5,500.0,4.5,15,30,"01-01-AA");
    v2 = new Veiculo(223.5,600.0,6.5,20,40,"05-05-ZZ");
    v3 = new Veiculo(423.5,700.0,2.5,30,50,"05-CC-05");
    v4 = new Veiculo(523.5,800.0,5.5,12,45,"05-AB-89");
    v5 = new Veiculo(723.5,900.0,8.5,25,60,"05-LL-99");
    v6 = new Veiculo(723.5,901.0,8.5,25,60,"06-LL-99");   


    d1 = new Veiculo(50,100.0,2.5,30,20,"55-GT-33");
    d2 = new Veiculo(100.5,1700.0,11.0,30,50,"25-GG-33");
    d3 = new Veiculo(423.5,10700.0,8.9,30,50,"35-FT-33");
    d4 = new Veiculo(200,12700.0,12.3,30,50,"25-GM-33");
    d5 = new Veiculo(567,13700.0,3.6,30,50,"15-GL-33");
   
    veiculos_utilitarios = new Stand("Garagem Veiculos Baratos", 10);
    
    veiculos_utilitarios.insereVeiculo(v1);
    veiculos_utilitarios.insereVeiculo(v2);
    veiculos_utilitarios.insereVeiculo(v3);
    veiculos_utilitarios.insereVeiculo(v4);
    veiculos_utilitarios.insereVeiculo(v5);
    veiculos_utilitarios.insereVeiculo(v6);
    
    veiculos_desportivos = new Stand("Garagem Auto Luxo", 10);
    
    veiculos_desportivos.insereVeiculo(d1);
    veiculos_desportivos.insereVeiculo(d2);
    veiculos_desportivos.insereVeiculo(d3);
    veiculos_desportivos.insereVeiculo(d4);
    veiculos_desportivos.insereVeiculo(d5);

    
    System.out.println("Informações do Stand " + veiculos_utilitarios.getNomeStand());
    System.out.println("--------------------------");
    System.out.println("Número de veículos: " + veiculos_utilitarios.getNVeiculos());
    System.out.println("Veículos: ");
    System.out.println(veiculos_utilitarios.toString());
  
    System.out.println("Informações do Stand " + veiculos_desportivos.getNomeStand());
    System.out.println("--------------------------");
    System.out.println("Número de veículos: " + veiculos_desportivos.getNVeiculos());
    System.out.println("Veículos: ");
    System.out.println(veiculos_desportivos.toString());

    
    //o v1 está no stand?
    
    System.out.println("v1 está no stand? " + veiculos_utilitarios.existeVeiculo(v1));
    
    System.out.print("Veículo com mais kms: ");
    Veiculo vx = veiculos_utilitarios.veiculoComMaisKms();
    Veiculo ux=veiculos_desportivos.veiculoComMaisKms();
    System.out.println(vx.toString());
    System.out.println(ux.toString());
    
    //....
    // mais operações
      
    
    
  }    
    
    
}
