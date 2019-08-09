import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;

/**
 * Escreva a descrição da classe UMeRApp aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class UMeRApp
{
    private UMeR umer;
    private Menu menuPrincipal, menuCliente, menuMotorista;
    private int perfil, idViatura; //1-cliente 2-motorista
    private String emailSessao; //email do utilizador com sessão iniciada atualmente
    private String matriculaViagem; //matricula da viatura da última viagem efetuada para se poder classificar o respetivo motorista
    
    /**
     * O método main cria a aplicação e invoca o método run()
     */
    public static void main(String[] args) {
        new UMeRApp().run();
    }
    
    /**
     * Construtor.
     * 
     * Cria os menus e a camada de negócio.
     */
    private UMeRApp() {
        // Criar o menu 
        String[] opcoes = {"Registar Utilizador",
                           "Iniciar Sessão",
                           "Total facturado por uma viatura num determinado período",
                           "Total facturado por uma empresa de táxis num determinado período",
                           "Listagem dos 10 clientes que mais gastam",
                           "Listagem dos 5 motoristas que apresentam mais desvios",
                           "Lista de utilizadores"};
        this.menuPrincipal = new Menu(opcoes);
        
        String[] opcoesCliente = {"Listagem das viagens efetuadas",
                                  "Solicitar uma viagem, escolhendo uma viatura",
                                  "Solicitar uma viagem",
                                  "Classificar motorista"};
        this.menuCliente = new Menu(opcoesCliente);
        
        String[] opcoesMotorista = {"Listagem das viagens efetuadas",
                                    "Associar viatura",
                                    "Alterar disponibilidade"};
        this.menuMotorista = new Menu(opcoesMotorista);        
        
        // Criar a lógica de negócio
        try {
            umer = carregaEstado("Estado");
        } catch (FileNotFoundException e) {
            System.out.println("Erro1. " + e.getMessage());
            umer = new UMeR();
        } catch (IOException e) {
            System.out.println("Erro2. " + e.getMessage());
            umer = new UMeR();
        } catch (ClassNotFoundException e) {
            System.out.println("Erro3. " + e.getMessage());
            umer = new UMeR();
        }
    }
    
    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    private void run() {
        do {
            menuPrincipal.executa();
            switch (menuPrincipal.getOpcao()) {
                case 1: registarUtilizador();
                        break;
                case 2: iniciarSessao();
                        if (perfil==1){
                         do{menuCliente.executa();
                           switch (menuCliente.getOpcao()) {
                             case 1: list_viagens();
                                    break;
                             case 2: solicita_viagem();
                                    break;
                             case 3: solicita_viagem1();
                                    break;
                             case 4: menu_classifica();
                                    break;
                           }
                         }while (menuCliente.getOpcao()!=0);
                        }
                        else if(perfil==2){
                         do{menuMotorista.executa();
                           switch (menuMotorista.getOpcao()) {
                             case 1: list_viagens();
                                    break;
                             case 2: associar_viatura();
                                    break;
                             case 3: altera_disp();
                                    break;
                           }
                         }while (menuMotorista.getOpcao()!=0);                        
                        }
                        break;
                case 3: totalFatV();
                        break;
                case 4: totalFatE();
                        break;
                case 5: top10Clientes();
                        break;
                case 6: top5Motoristas();
                        break;
                case 7: listaU();
                        break;
            }
        } while (menuPrincipal.getOpcao()!=0); // A opção 0 é usada para sair do menu.
        try {
            guardaEstado(umer,"Estado");
        } catch (FileNotFoundException e) {
            System.out.println("Erro1. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro2. " + e.getMessage());
        }
        System.out.println("Até breve!...");     
    }
    
    private void solicita_viagem() {
        Scanner scin = new Scanner(System.in);
        int opcao;
        System.out.print("Escolheu efetuar uma viagem!\nO nosso serviço dispõe de 3 tipos de viagens que das quais apenas poderá escolher uma digitando o número corresponde à opção desejada:\n");
        do{
          System.out.print("1-Solicitar uma carrinha\n");
          System.out.print("2-Solicitar um carro ligeiro\n");
          System.out.print("3-Solicitar uma mota\n");
          System.out.print("Opcao: ");
          opcao=scin.nextInt();
        } while(!(opcao>0 && opcao<4));
        System.out.print("Indique a longitude final da viagem:\n");
        int longitude = scin.nextInt();
        System.out.print("Indique a latitude final da viagem:\n");
        int latitude = scin.nextInt();
        Espaco2D espaco2D = new Espaco2D(longitude, latitude);
        if (opcao==1) {
            System.out.print("Escolha uma matrícula das seguintes carrinhas:\n");
            umer.imprimeViaturas(1);
            String matrix = scin.next();
            try{
                umer.solicitarViagemC(espaco2D, matrix, emailSessao);
                matriculaViagem = matrix;
                System.out.print("Reserva efetuada com sucesso.\nObrigado pela sua preferência!\nBoa viagem :)\n");
            } catch (ViaturaNaoExistenteException vnee){
                System.out.println("Erro: " + vnee.getMessage());
            } catch (MotoristaNaoDisponivelException mnde){
                System.out.println("Erro: " + mnde.getMessage());
            }
        }
           
        if (opcao==2) {
            System.out.print("Escolha uma matrícula das seguintes carrinhas:\n");
            umer.imprimeViaturas(2);
            String matrix = scin.next();
            try{
                umer.solicitarViagemCL(espaco2D, matrix, emailSessao);
                matriculaViagem = matrix;
                System.out.print("Reserva efetuada com sucesso.\nObrigado pela sua preferência!\nBoa viagem :)\n");
            } catch (ViaturaNaoExistenteException vnee){
                System.out.println("Erro: " + vnee.getMessage());
            } catch (MotoristaNaoDisponivelException mnde){
                System.out.println("Erro: " + mnde.getMessage());
            }
        }

        if (opcao==3) {
            System.out.print("Escolha uma matrícula das seguintes carrinhas:\n");
            umer.imprimeViaturas(3);
            String matrix = scin.next();
            try {
                umer.solicitarViagemM(espaco2D, matrix, emailSessao);
                matriculaViagem = matrix;
                System.out.print("Reserva efetuada com sucesso.\nObrigado pela sua preferência!\nBoa viagem :)\n");
            } catch (ViaturaNaoExistenteException vnne){
                System.out.println("Erro: " + vnne.getMessage());
            } catch (MotoristaNaoDisponivelException mnde){
                System.out.println("Erro: " + mnde.getMessage());
            }
        }
    }
    
    private void solicita_viagem1(){
        Scanner scin = new Scanner(System.in);
        int opcao=-1;
        System.out.print("Escolheu efetuar uma viagem\n");
        System.out.print("Iremos localizar a viatura que melhor o satifaça\n");
        System.out.print("Indique a longitude final da viagem:\n");
        int longitude = scin.nextInt();
        System.out.print("Indique a latitude final da viagem:\n");
        int latitude = scin.nextInt();
        Espaco2D espaco2D = new Espaco2D(longitude, latitude);
        try {
            matriculaViagem = umer.solicitarViagem(espaco2D,emailSessao);
            System.out.print("Reserva efetuada com sucesso.\nO nosso motorista já foi alertado\n");
            System.out.print("Obrigado pela sua preferência!\nBoa viagem :)\n");
        } catch (ViaturaNaoExistenteException vnne){
            System.out.println("Erro: " + vnne.getMessage());
        } catch (MotoristaNaoDisponivelException mnde){
            System.out.println("Erro: " + mnde.getMessage());
        }
    }  
    
    private void menu_classifica(){
        Scanner scin = new Scanner(System.in);
        int opcao;
        System.out.print("Muito obrigado por usufruir dos nossos serviços\nEsperamos que a viagem tenha corrido bem e com toda a comodidade que lhe é caraterística\n");
        System.out.print("Na votação, indique um número entre o 1(Muito mau) e o 5(Excelente)\n");
        System.out.print("1-Muito mau\n2-Mau\n3-Bom\n4-Muito Bom\n5-Excelente\nOpcao: ");
        opcao=scin.nextInt();
        try{
            umer.classificar(emailSessao, matriculaViagem, opcao);
            System.out.print("Muito obrigado pela sua classificação! Só assim é que podemos melhorar para tornar o nosso serviço cada vez melhor");
        }
        catch (ViaturaNaoExistenteException vnee){
            System.out.println("Erro: " + vnee.getMessage());
        }
    }
    
    public void totalFatV(){
        Scanner scin = new Scanner(System.in);
        int ano, mes, dia;
        String matrix = new String();
        System.out.print("Escolheu consultar o total faturado por uma viatura!\n");
        System.out.print("Para isso, insira a primeira data: ");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>1850 && ano<=2017));
        do{
            System.out.print("De 1 a 12, para relembrar\n");        
            System.out.print("Mês: ");
            mes = lerInteiro("Mês: ");
        } while(!(mes>=1 && mes<=12));
        int dias_ano=Year.of(ano).length();
        do{
            System.out.print("Há no máximo 31 dias por mês\n");    
            System.out.print("Dia: ");
            dia = lerInteiro("Dia: ");
        } while (!(dia>=1 && dia<=28 && (mes==2) && (dias_ano==365)) && !(dia>=1 && dia<=29 && (mes==2) && (dias_ano==366)) && !((dia>=1 && dia<=31)&&((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12))) && !((dia>=1 && dia<=30)&&((mes==4) || (mes==6) || (mes==9) || (mes==11))));
        LocalDate data1 = LocalDate.of(ano,mes,dia);
        System.out.print("Insira a segunda data: ");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>1850 && ano<=2017));
        do{
            System.out.print("De 1 a 12, para relembrar\n");        
            System.out.print("Mês: ");
            mes = lerInteiro("Mês: ");
        } while(!(mes>=1 && mes<=12));
        dias_ano=Year.of(ano).length();
        do{
            System.out.print("Há no máximo 31 dias por mês\n");    
            System.out.print("Dia: ");
            dia = lerInteiro("Dia: ");
        } while (!(dia>=1 && dia<=28 && (mes==2) && (dias_ano==365)) && !(dia>=1 && dia<=29 && (mes==2) && (dias_ano==366)) && !((dia>=1 && dia<=31)&&((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12))) && !((dia>=1 && dia<=30)&&((mes==4) || (mes==6) || (mes==9) || (mes==11))));
        LocalDate data2 = LocalDate.of(ano,mes,dia);
        if (data1.isBefore(data2)) {
            System.out.print("Qual o tipo de viatura que quer consultar?\n1-Carrinha\n2-CarroLigeiro\n3-Moto\n");
            int opcao = scin.nextInt();
            do{
               switch (opcao){
                   case 1: System.out.print("Escolha uma matrícula das seguintes carrinhas:\n");
                           umer.imprimeViaturas(1);
                           matrix = scin.next();
                           break;
                   case 2: System.out.print("Escolha uma matrícula dos seguintes carros ligeiros:\n");
                           umer.imprimeViaturas(2);
                           matrix = scin.next();
                           break;
                   case 3: System.out.print("Escolha uma matrícula das seguintes motos:\n");
                           umer.imprimeViaturas(3);
                           matrix = scin.next();
                           break;
               }
            } while(opcao!=1 && opcao!=2 && opcao!=3);
            try{
                   System.out.print("O total faturado por uma viatura, entre "+data1+" e "+data2+" é: "+umer.totalFaturadoV(matrix, data1, data2));    
            } catch (ViaturaNaoExistenteException vnee){
                   System.out.println("Erro: " + vnee.getMessage());
            }
        }
    }
    
    public void totalFatE(){
        Scanner scin = new Scanner(System.in);
        int ano, mes, dia;
        String empresa = new String();
        System.out.print("Escolheu consultar o total faturado por uma empresa!\n");
        System.out.print("Para isso, insira a primeira data: ");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>1850 && ano<=2017));
        do{
            System.out.print("De 1 a 12, para relembrar\n");        
            System.out.print("Mês: ");
            mes = lerInteiro("Mês: ");
        } while(!(mes>=1 && mes<=12));
        int dias_ano=Year.of(ano).length();
        do{
            System.out.print("Há no máximo 31 dias por mês\n");    
            System.out.print("Dia: ");
            dia = lerInteiro("Dia: ");
        } while (!(dia>=1 && dia<=28 && (mes==2) && (dias_ano==365)) && !(dia>=1 && dia<=29 && (mes==2) && (dias_ano==366)) && !((dia>=1 && dia<=31)&&((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12))) && !((dia>=1 && dia<=30)&&((mes==4) || (mes==6) || (mes==9) || (mes==11))));
        LocalDate data1 = LocalDate.of(ano,mes,dia);
        System.out.print("Insira a segunda data: ");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>1850 && ano<=2017));
        do{
            System.out.print("De 1 a 12, para relembrar\n");        
            System.out.print("Mês: ");
            mes = lerInteiro("Mês: ");
        } while(!(mes>=1 && mes<=12));
        dias_ano=Year.of(ano).length();
        do{
            System.out.print("Há no máximo 31 dias por mês\n");    
            System.out.print("Dia: ");
            dia = lerInteiro("Dia: ");
        } while (!(dia>=1 && dia<=28 && (mes==2) && (dias_ano==365)) && !(dia>=1 && dia<=29 && (mes==2) && (dias_ano==366)) && !((dia>=1 && dia<=31)&&((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12))) && !((dia>=1 && dia<=30)&&((mes==4) || (mes==6) || (mes==9) || (mes==11))));
        LocalDate data2 = LocalDate.of(ano,mes,dia);
        if (data1.isBefore(data2)) {
            System.out.print("Escolha uma das seguintes empresas:\n");
            umer.imprimeEmpresas();
            empresa = scin.next();
        }
        try{
            System.out.print("O total faturado por uma viatura, entre "+data1+" e "+data2+" é: "+umer.totalFaturadoE(empresa, data1, data2));    
        } catch (EmpresaInexistenteException eie){
            System.out.println("Erro: " + eie.getMessage());
        }
    }
    
    public void top10Clientes(){
        System.out.print("Escolheu consultar os 10 clientes que mais gastam!\nEles são: "+umer.top10ClientesMaisGastam().toString());
    }
    
    public void top5Motoristas(){
        System.out.print("Escolheu consultar os 5 motoristas que apresentam mais desvios entre o valores previstos para as viagens e o valor final faturado!\nEles são: "+umer.top5MotoristasDesvios().toString());
    }
    
    public void list_viagens(){
        Scanner scin = new Scanner(System.in);
        int opcao;
        int ano, mes, dia;
        System.out.print("Escolheu consultar a lista de viagens efetuadas entre duas datas\n");
        System.out.print("Para isso, insira a primeira data: ");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>1850 && ano<=2017));
        do{
            System.out.print("De 1 a 12, para relembrar\n");        
            System.out.print("Mês: ");
            mes = lerInteiro("Mês: ");
        } while(!(mes>=1 && mes<=12));
        int dias_ano=Year.of(ano).length();
        do{
            System.out.print("Há no máximo 31 dias por mês\n");    
            System.out.print("Dia: ");
            dia = lerInteiro("Dia: ");
        } while (!(dia>=1 && dia<=28 && (mes==2) && (dias_ano==365)) && !(dia>=1 && dia<=29 && (mes==2) && (dias_ano==366)) && !((dia>=1 && dia<=31)&&((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12))) && !((dia>=1 && dia<=30)&&((mes==4) || (mes==6) || (mes==9) || (mes==11))));
        LocalDate data1 = LocalDate.of(ano,mes,dia);
        System.out.print("Insira a segunda data: ");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>1850 && ano<=2017));
        do{
            System.out.print("De 1 a 12, para relembrar\n");        
            System.out.print("Mês: ");
            mes = lerInteiro("Mês: ");
        } while(!(mes>=1 && mes<=12));
        dias_ano=Year.of(ano).length();
        do{
            System.out.print("Há no máximo 31 dias por mês\n");    
            System.out.print("Dia: ");
            dia = lerInteiro("Dia: ");
        } while (!(dia>=1 && dia<=28 && (mes==2) && (dias_ano==365)) && !(dia>=1 && dia<=29 && (mes==2) && (dias_ano==366)) && !((dia>=1 && dia<=31)&&((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12))) && !((dia>=1 && dia<=30)&&((mes==4) || (mes==6) || (mes==9) || (mes==11))));
        LocalDate data2 = LocalDate.of(ano,mes,dia);
        if(data1.isBefore(data2)){
          try{
               System.out.print("Esta é a lista das suas viagens efetuadas entre " + data1 + " e " + data2 + ":\n" + umer.viagensEfetuadas(emailSessao,data1,data2).toString());
          } catch (UtilizadorInexistenteException uie){
               System.out.println("Erro: " + uie.getMessage());
          }
        }
    }
    
    public void associar_viatura(){
        Scanner scin = new Scanner(System.in);
        System.out.print("Insira os dados da sua nova viatura\n");
        Viatura v = new Viatura();
        Map<Integer,Viagem> viagens;
        int num=0,num1=0;
        do{
               String matrix;
               System.out.print("Digite a sua matrícula para identificarmos a sua viatura: ");
               matrix=scin.next();
               System.out.print("Que tipo de viatura é?\n" + "1-Mota\n2-Carro\n3-Carrinha\nOpcao:");
               num1=scin.nextInt();
               double velMed=20.0;
               double precoBase=1.0;
               double fiabil=1.0;
               int ocupa=0;
               viagens = new HashMap<>();
               Espaco2D espaco=new Espaco2D(0.0,0.0);
               switch(num1){
                      case 1:{ 
                                System.out.print("Quantos lugares tem?\nLugares\n");
                                int lugares=scin.nextInt();
                                 v = new Moto(viagens, velMed, precoBase, fiabil, espaco, matrix, lugares, ocupa);
                                 break;
                             }
                             case 2:{
                                 System.out.print("Quantos lugares tem?\nLugares\n");
                                 int lugares=scin.nextInt();
                                 List<Viagem> viagensEmEspera = new ArrayList<>();
                                 v = new CarroLigeiro(viagens, velMed, precoBase, fiabil, espaco, matrix, lugares, ocupa, viagensEmEspera);
                                 break;
                             }
                             case 3:{
                                 System.out.print("Quantos lugares tem?\nLugares\n");
                                 int lugares=scin.nextInt();
                                 List<Viagem> viagensEmEspera = new ArrayList<>();
                                 v = new Carrinha(viagens, velMed, precoBase, fiabil, espaco, matrix, lugares, ocupa, viagensEmEspera);
                                 break;
                             }
                             case 0: break;
               }
        } while(num1!=1 && num1!=3 && num1!=2 && num1!=0); 
        try{
           umer.associarNovaViatura(v, emailSessao);
           System.out.println("Associou com sucesso a sua nova viatura");
        }catch (ViaturaRepetidaException vre){
            System.out.println("Erro :" + vre.getMessage());
        }
    }
    
    public void altera_disp(){
        Scanner scin = new Scanner(System.in);
        System.out.print("Está disponível para trabalhar?\n1-Sim\n2-Não\n");
        boolean trabalha = true;
        int opcao = scin.nextInt();
        do{
            switch (opcao){
                   case 1: System.out.print("Pode efetuar viagens\n");
                           trabalha = true;
                           break;
                   case 2: System.out.print("Não pode efetuar viagens\n");
                           trabalha = false;
                           break;
            }
        } while(opcao!=1 && opcao!=2);
        umer.alteraDisponibilidade(emailSessao,trabalha);
        System.out.print("Obrigado pelos seus serviços\n");
    }
    
    private void registarUtilizador() {
        Utilizador ut=new Utilizador();
        Viatura v = new Viatura();
        Scanner scin = new Scanner(System.in);
        
        String email, nome, password, morada;
        int ano, mes, dia,num=0,num1=0;
        int dias_ano;
        LocalDate dataNascimento;
        Map<Integer,Viagem> viagens;
        int grau_cump=0,classif=0;
        double nkms1=0.0;
        boolean disponibilidade=true;
        do{
            System.out.print("Não esquecer que so aceitamos email tipo ...@hotmail.com ou ....@gmail.com ou ....@iol.pt\nCaso nao seja um email destes 3 nao irá continuar!!\n");  
            System.out.print("E-mail: ");
            email = scin.nextLine();
        } while(!email.contains("@hotmail.com") && !email.contains("@gmail.com") && !email.contains("@iol.pt"));
        System.out.print("Nome: ");
        nome = scin.nextLine();
        System.out.print("Password: ");
        password = scin.nextLine();
        System.out.print("Morada: ");
        morada = scin.nextLine();
        System.out.print("Data de Nascimento: ");
        do{
            System.out.print("Seja verdadeiro!!!...Nao invente uma idade que nós não aceitamos\n");    
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>1850 && ano<=2017));
        do{
            System.out.print("De 1 a 12,para relembrar\n");        
            System.out.print("Mês: ");
            mes = lerInteiro("Mês: ");
        } while(!(mes>=1 && mes<=12));
        dias_ano=Year.of(ano).length();
        do{
            System.out.print("Há no máximo 31 dias por mês\n");    
            System.out.print("Dia: ");
            dia = lerInteiro("Dia: ");
        } while (!(dia>=1 && dia<=28 && (mes==2) && (dias_ano==365)) && !(dia>=1 && dia<=29 && (mes==2) && (dias_ano==366)) && !((dia>=1 && dia<=31)&&((mes==1) || (mes==3) || (mes==5) || (mes==7) || (mes==8) || (mes==10) || (mes==12))) && !((dia>=1 && dia<=30)&&((mes==4) || (mes==6) || (mes==9) || (mes==11))));
        dataNascimento = LocalDate.of(ano,mes,dia);
        viagens = new HashMap<>();
        Espaco2D espaco=new Espaco2D(0.0,0.0);
        do{
            System.out.print("Deseja ser nosso cliente ou motorista?\n" + "1-Cliente\n" + "2-Motorista\n0-Sair\n"+"Opcao:");
            num=scin.nextInt();
            switch(num){
                case 1:{
                     ut = new Cliente(email, nome, password, morada, dataNascimento, viagens,espaco);break;
                }
                case 2: {
                     do{
                         String matrix;
                         System.out.print("Digite a sua matrícula para identificarmos a sua viatura: ");
                         matrix=scin.next();
                         System.out.print("Que tipo de viatura é?\n" + "1-Mota\n2-Carro\n3-Carrinha\nOpcao:");
                         num1=scin.nextInt();
                         double velMed=20.0;
                         double precoBase=1.0;
                         double fiabil=1.0;
                         int ocupa=0;
                         switch(num1){
                             case 1:{ 
                                 System.out.print("Quantos lugares tem?\nLugares\n");
                                 int lugares=scin.nextInt();
                                 ut = new Motorista(email, nome, password, morada, dataNascimento, viagens,grau_cump,classif,nkms1,disponibilidade,matrix);
                                 v = new Moto(viagens, velMed, precoBase, fiabil, espaco, matrix, lugares, ocupa);
                                 break;
                             }
                             case 2:{
                                 System.out.print("Quantos lugares tem?\nLugares\n");
                                 int lugares=scin.nextInt();
                                 ut = new Motorista(email, nome, password, morada, dataNascimento, viagens,grau_cump,classif,nkms1,disponibilidade,matrix);
                                 List<Viagem> viagensEmEspera = new ArrayList<>();
                                 v = new CarroLigeiro(viagens, velMed, precoBase, fiabil, espaco, matrix, lugares, ocupa, viagensEmEspera);
                                 break;
                             }
                             case 3:{
                                 System.out.print("Quantos lugares tem?\nLugares\n");
                                 int lugares=scin.nextInt();
                                 ut = new Motorista(email, nome, password, morada, dataNascimento, viagens,grau_cump,classif,nkms1,disponibilidade,matrix);
                                 List<Viagem> viagensEmEspera = new ArrayList<>();
                                 v = new Carrinha(viagens, velMed, precoBase, fiabil, espaco, matrix, lugares, ocupa, viagensEmEspera);
                                 break;
                             }
                             case 0: break;
                            }
                        }
            while(num1!=1 && num1!=3 && num1!=2 && num1!=0); 
          }
              case 0:break;
         }
        }
        while(num!=1 && num!=2 && num!=0);
        
        if(num1!=0 || num!=0){
            try {
                umer.adiciona(ut);
                umer.adicionaV(v);
                System.out.print("Utilizador registado com sucesso");
            } 
        
            catch (UtilizadorRepetidoException e) {
                System.out.println(e.getMessage());
                ut = new Utilizador();
            }
            
            catch (ViaturaRepetidaException e){
                System.out.println(e.getMessage());
                v = new Viatura();
            }
        }

        scin.close();
    }
    
    private void iniciarSessao() {
        String mail;
        String password;
        Scanner ler = new Scanner(System.in);
        System.out.print("Digite os seus dados\n");
        System.out.print("E-mail: ");
        mail = ler.next();
        System.out.print("Password: ");
        password = ler.next();
        
        try {
            perfil = umer.iniciaSessao(mail,password);
            emailSessao = mail;
        } catch (UtilizadorInexistenteException e) {
            System.out.println("Erro nos seus dados: " + e.getMessage());
            perfil = 0;
            emailSessao = new String();
        }
        
    }
    
    public void listaU(){
        System.out.println("Lista de Utilizadores: ");
        System.out.println(umer.getUtilizadores().toString());
    }
    
    /**
     * Método que guarda o estado de uma instância num ficheiro de texto.
     * 
     * @param nome do ficheiro
     */
    
    public void escreveEmFicheiroTxt(String nomeFicheiro) throws IOException {
       PrintWriter fich = new PrintWriter(nomeFicheiro);
       fich.println("------- UMeR --------");
       fich.println(this.toString()); // ou fich.println(this);
       fich.flush();
       fich.close();
     }

    /**
     * Método que guarda em ficheiro de objectos o objecto que recebe a mensagem.
     */
    
    public void guardaEstado(UMeR umer, String nomeFicheiro) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(umer); //guarda-se todo o objecto de uma só vez
        oos.flush();
        oos.close();
    }
    
    /**
     * Método que recupera uma instância de UMeR de um ficheiro de objectos.
     * Este método tem de ser um método de classe que devolva uma instância já construída de
     * UMeR.
     * 
     * @param nome do ficheiro onde está guardado um objecto do tipo UMeR
     * @return objecto UMeR inicializado
     */
    
    public static UMeR carregaEstado(String nomeFicheiro) throws FileNotFoundException,IOException,ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        UMeR u = (UMeR) ois.readObject();
        ois.close();
        return u;
    }
    
    public static int lerInteiro(String mensagem){  
        Scanner scan = new Scanner(System.in);  
        String numero = scan.nextLine();  
        int num = 0;  
        boolean conversao = true;  
        while(conversao){  
            try{  
                num = Integer.parseInt(numero);  
                conversao = false;  
            }catch(Exception e){  
                System.out.println("Só é válido numeros inteiros!");  
                System.out.println(mensagem);
                numero = scan.nextLine();
            }  
        }
        return num;
    } 
}

