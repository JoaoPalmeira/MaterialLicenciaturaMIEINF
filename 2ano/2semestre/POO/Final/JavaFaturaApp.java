import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.io.Serializable;

/**
 * Write a description of class JavaFaturaApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JavaFaturaApp
{
    private JavaFatura javaFatura;
    private Menu menuPrincipal, menuContribuinte, menuEmpresa, menuAdmin;
    private int funcao;                                             // 1-Contribuinte, 2-Empresa, 3-Administrador
    private int contribuinteSessao;                                 // contribuinte com sessao iniciada
    
    /**
     * O método main cria a aplicação e invoca o método run()
     */
    public static void main(String[] args) {
        new JavaFaturaApp().run();
    }
    
    /**
     * Construtor.
     * 
     * Cria os menus e a camada de negócio.
     */
    private JavaFaturaApp() {
        // Criar o menu 
        String[] opcoes = {"Registar Utilizador",
                           "Iniciar Sessão",
                           "Listagem de faturas de uma determinada empresa",
                           "Total faturado por uma empresa num periodo",
                           };
        this.menuPrincipal = new Menu(opcoes);
        
        String[] opcoesContribuinte = {"Verificar faturas",
                                       "Corrigir classificaçao de ActividadeEconomica",
                                       "Associar Actividades Economicas"
                                      };
        this.menuContribuinte = new Menu(opcoesContribuinte);
        
        String[] opcoesEmpresa = {"Criar Faturas associadas a um contribuinte",
                                  "Faturas por contribuinte de um intervalo de datas",
                                  "Faturas por contribuinte ordenadas por valor",
                                  "Associar Actividades Economicas e respectivas deduçoes"
                                 };
        this.menuEmpresa = new Menu(opcoesEmpresa);
        
        String[] opcoesAdmin = {"Os dez contribuintes com mais gastos",
                                "Relação de X empresas mais fatura",
                                "Adiciona cidade com incentivo fiscal"
                               };
        this.menuAdmin = new Menu(opcoesAdmin);         
        
        try {
            javaFatura = carregaEstado("Estado");
        } catch (FileNotFoundException e) {
            System.out.println("Erro1. " + e.getMessage());
            javaFatura = new JavaFatura();
        } catch (IOException e) {
            System.out.println("Erro2. " + e.getMessage());
            javaFatura = new JavaFatura();
        } catch (ClassNotFoundException e) {
            System.out.println("Erro3. " + e.getMessage());
            javaFatura = new JavaFatura();
        }
    }
    
    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    
    private void run() {
        System.out.println("\nBem-Vindo ao JavaFatura !!!");   
        do {
            menuPrincipal.executa();
            switch (menuPrincipal.getOpcao()) {
                case 1: registarUtilizador();
                        break;
                case 2: iniciarSessao();
                        if (funcao==1){
                         do{validaAutomaticamente();
                            menuContribuinte.executa();
                           switch (menuContribuinte.getOpcao()) {
                             case 1: consultaFaturas();
                                     break;
                             case 2: associaActEconoFact();
                                     break;
                             case 3: associa_ActividadeEconomica_Cont();
                                     break;
                           }
                         }while (menuContribuinte.getOpcao()!=0);
                        }
                        else if(funcao==2){
                         do{menuEmpresa.executa();
                           switch (menuEmpresa.getOpcao()) {
                             case 1: criaFatura();
                                     break;
                             case 2: faturaEntreDatas();
                                     break;
                             case 3: consutlaFactValor();
                                     break;
                             case 4: associa_ActividadeEconomica_Emp();
                                     break;
                           }
                         }while (menuEmpresa.getOpcao()!=0);                        
                        }
                        else if(funcao==3){
                            do{menuAdmin.executa();
                               switch (menuAdmin.getOpcao()){
                                   case 1: top10Contribuinte();
                                           break;
                                   case 2: topXEmpresas();
                                           break;
                                   case 3: adicionaCidade();
                                           break;
                               }
                         }while (menuAdmin.getOpcao()!=0);
                        }
                        break;
                case 3: consultaFaturasEmpresa(); 
                        break;
                case 4: totalFaturado();
                        break;
            }
        }while (menuPrincipal.getOpcao()!=0); // A opção 0 é usada para sair do menu.
        try {
            guardaEstado(javaFatura,"Estado");
            escreveEmFicheiroTxt("texto.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Erro1. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro2. " + e.getMessage());
        }
        System.out.println("Até breve e obrigado por usar o JavaFatura !!!");     
    }
    
    // ----------------------------------------------------- Requisitos Pedidos ----------------------------------------------------- //
    
    /** 
     * Associa uma actividade Economica a uma Empresa 
     */
    
    private void associa_ActividadeEconomica_Emp(){
        ActividadeEconomica ae = new ActividadeEconomica();
        Scanner sc = new Scanner(System.in);
        int num1=-1;
        double valorDeducao;
        String descricao, tipoServico, tipoVeiculo, tipoTransporte, tipoHabitacao;
                           
        System.out.print("\nActvidadadeEconomica em que participa:\n" + "1-Restauracao\n" + "2-Educacao\n" + "3-Servicos\n" + 
                             "4-Saude\n"+ "5-Reparacao de Veiculos\n"+ "6-Transportes\n" + 
                             "7-Habitacao\n" + "0-Para sair\n"+"Opcao:");
        num1 = lerInteiro("Opcao: ");
        
            switch(num1){
                case 1: {
                    System.out.print("Descricao: ");
                    descricao = sc.nextLine();
                    System.out.print("Fator de dedução: ");
                    valorDeducao= sc.nextDouble();
                    ae = new Restauracao(true,num1,valorDeducao,descricao);
                    break;
                }    
                case 2: {
                    System.out.print("Descricao: ");
                    descricao= sc.nextLine();
                    System.out.print("Fator de dedução: ");
                    valorDeducao= sc.nextDouble();
                    ae = new Educacao(true,num1,valorDeducao,descricao);
                    break;
                }   
                case 3:{
                    System.out.print("Descricao: ");
                    descricao = sc.nextLine();
                    System.out.print("Tipo de Servico: ");
                    tipoServico = sc.nextLine();
                    System.out.print("Fator de dedução: ");
                    valorDeducao= sc.nextDouble();
                    ae = new Servicos(true,num1,valorDeducao,descricao,tipoServico);
                    break;
                }
                case 4:{
                    System.out.print("Descricao: ");
                    descricao = sc.nextLine();
                    System.out.print("Fator de dedução: ");
                    valorDeducao= sc.nextDouble();
                    ae = new Saude(true,num1,valorDeducao,descricao);
                    break;
                }
                case 5:{
                    System.out.print("Descricao: ");
                    descricao = sc.nextLine();
                    System.out.print("Tipo de Veiculo: ");
                    tipoVeiculo = sc.nextLine();
                    System.out.print("Fator de dedução: ");
                    valorDeducao= sc.nextDouble();
                    ae = new ReparacaoVeiculos(true,num1,valorDeducao,descricao,tipoVeiculo);
                    break;
                }
                case 6:{
                    System.out.print("Descricao: ");
                    descricao = sc.nextLine();
                    System.out.print("Tipo de Transporte: ");
                    tipoTransporte = sc.nextLine();
                    System.out.print("Fator de dedução: ");
                    valorDeducao= sc.nextDouble();
                    ae = new Transportes(true,num1,valorDeducao,descricao,tipoTransporte);
                    break;
                }
                case 7:{
                    System.out.print("Descricao: ");
                    descricao = sc.nextLine();
                    System.out.print("Tipo Habitacao: ");
                    tipoHabitacao = sc.nextLine();
                    System.out.print("Fator de dedução: ");
                    valorDeducao= sc.nextDouble();
                    ae = new Servicos(true,num1,valorDeducao,descricao,tipoHabitacao);
                    break;
                }
                case 0: break;             
            }

        if(num1!=0){
            try{
                javaFatura.associarActEmp(ae,contribuinteSessao);
                System.out.println("\nAssociou a Atividade Economica...");
            }
            catch (ActividadeEconomicaRepetidaException e){
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
  
    /** 
     * Associa uma actividade Economica a um contribuinte individual 
     */
   
    private void associa_ActividadeEconomica_Cont(){
        ActividadeEconomica ae = new ActividadeEconomica();
        Scanner sc = new Scanner(System.in);
        
        int num1=-1;
        
        System.out.print("\nActvidadadeEconomica em que participa:\n" + "1-Restauracao\n" + "2-Educacao\n" + "3-Servicos\n" + 
                             "4-Saude\n"+ "5-Reparacao de Veiculos\n"+ "6-Transportes\n" + 
                             "7-Habitacao\n" + "0-Para sair\n"+"Opcao:");
        num1 = lerInteiro("Opcao: ");
        ae = new ActividadeEconomica(true,num1);
        
        if(num1!=0){
            try{
                javaFatura.associarActCont(ae,contribuinteSessao);
                System.out.println("\nAssociou a Actividade Economica...");
            }
            catch (ActividadeEconomicaRepetidaException e){
                System.out.print(e.getMessage());
            }
        }
        sc.close();
    }
      
    /** 
     * Inicia sessao na aplicacao 
     */
    
    private void iniciarSessao() {
        int contribuinte;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite os seus dados\n");
        System.out.print("Contribuinte: ");
        contribuinte = sc.nextInt();
        System.out.print("Password: ");
        password = sc.next();
        
        try {
            funcao = javaFatura.iniciaSessao(contribuinte,password);
            contribuinteSessao = contribuinte;
        } catch (UtilizadorInexistenteException e) {
            System.out.println("\nErro nos seus dados: " + e.getMessage());
            funcao = 0;
            contribuinteSessao = 000000000;
        }
        sc.close();
    }
    
    /**
     * Regista um utilizador na aplicacão 
     */
    
    private void registarUtilizador(){
        Utilizador ut = new Utilizador();
        Scanner sc = new Scanner(System.in);
        int nif=0;
        String email, nome, morada, pass, designacao, localizacao;
        int num=-1;
        int numAgr,coef,nifA,factDeducao;
        
        List<Integer> nFa;
        Map<Integer,ActividadeEconomica> codAct;
        Map<Integer,ActividadeEconomica> activEcono;
        
        do{
           System.out.println("Como se deseja registar?"); 
           System.out.println("1-Contribuinte Individual");
           System.out.println("2-Empresa");
           System.out.println("0-Sair");
           System.out.print("Opcao: ");
           num = lerInteiro("Opcao: ");
           
           if(num!=0){
                
                   do{
                       System.out.print("O NIF deve conter 9 digitos, caso contrario, nao irá continuar !!\n");  
                       System.out.print("NIF: ");
                       nif = lerInteiro("NIF: ");
                   }while(verificaNif(nif));
               
                   System.out.print("Nome: ");
                   nome = sc.nextLine();
                   System.out.print("E-mail: ");
                   email = sc.nextLine();
                   System.out.print("Morada: ");
                   morada = sc.nextLine();
                   System.out.print("Password: ");
                   pass = sc.nextLine();
   
                   switch(num){
                       case 1:{
                           System.out.print("Coeficiente fiscal: ");
                           coef = sc.nextInt();
                           System.out.print("Numero do agregado familiar: ");
                           numAgr = sc.nextInt();
                           nFa =  new ArrayList<>();
                           
                           nFa.add(nif);

                           for(int i = 1; i<numAgr; i++){
                               do{
                                  System.out.print("Adicione os contribuintes do agregrado, nao se esqueça que so tem 9 numeros !!!\n");  
                                  System.out.print("NIF: ");
                                  nifA = lerInteiro("NIF: ");
                               } while(verificaNif(nif));
                               nFa.add(nifA);
                           }
                           
                           codAct = new HashMap<>();
                           ut = new Contribuinte(nif,email,nome,morada,pass,numAgr,coef,nFa,codAct);
                           break;
                        }
                        case 2:{
                            System.out.print("Designação: ");
                            designacao = sc.nextLine();
                            
                            do{
                                System.out.print("Neste parametro só são permitidas duas opções (Litoral/Interior).\n");
                                System.out.print("Localização: ");
                                localizacao = sc.nextLine();
                            }while(!verificaLocalizacao(localizacao));
                            
                            System.out.print("Fator de dedução: ");
                            factDeducao= sc.nextInt();
                            activEcono = new HashMap<>();
                            
                            ut = new Empresa(nif,email,nome,morada,pass,designacao,localizacao,factDeducao,activEcono);
                            break;
                        }
                        case 0:break;
                    }   
                   
                } 
        }while(num!=0 && num!=1 && num!=2);
        
        if(num!=0){
            try {
                javaFatura.adicionaU(ut);
                System.out.println("\nUtilizador registado com sucesso...");
            } 
        
            catch (UtilizadorRepetidoException e) {
                System.out.println(e.getMessage());
                ut = new Utilizador();
            }
        }
        sc.close();
    }
    
    /**
     * Cria uma fatura associada a um contribuinte
     */
    
    private void criaFatura(){
        Despesa d = new Despesa();
        ActividadeEconomica ae = new ActividadeEconomica();
        Scanner sc = new Scanner(System.in);
        
        int dia, mes, ano;
        int nifC;
        String descricao;
        int num;
        double valor;
        
        do{
           System.out.print("Introduza o NIF do cliente. (9 -> numeros) !!\n");  
           System.out.print("NIF: ");
           nifC = lerInteiro("NIF: ");
        }while(verificaNif(nifC));
        
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>=2018));
        
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
        
        LocalDate data = LocalDate.of(ano,mes,dia);
        
        System.out.print("Introduza uma breve descricao da despesa: ");
        descricao = sc.nextLine();
        
        System.out.print("Qual é a natureza da despesa:\n" + "1-Restauracao\n" + "2-Educacao\n" + "3-Servicos\n" + 
                             "4-Saude\n"+ "5-Reparacao de Veiculos\n"+ "6-Transportes\n" + 
                             "7-Habitacao\n" + "0-Para sair\n"+"Opcao:");
        num = lerInteiro("Opcao: ");
        
        ae = new ActividadeEconomica(false,num);
        
        System.out.print("Qual o valor da despesa: ");
        valor = sc.nextDouble();
        
        d = new Despesa(1,false,contribuinteSessao," ",data,nifC,descricao,ae,valor);
        
        try{
            javaFatura.criaFaturaAss(d);
            System.out.println("\nFatura criada com sucesso...");
        }
        catch (UtilizadorInexistenteException e){
            System.out.println("\nErro: " + e.getMessage());
        }
        catch (ActividadeEconomicaInexistenteException e){
            System.out.println("\nErro: " + e.getMessage());
        }
        sc.close();
    }
    
    /**
     * Valida as faturas automaticamente dum contribuinte 
     */
    
    private void validaAutomaticamente(){
        int nif = contribuinteSessao;
        
        try{
            javaFatura.validaFact(nif); 
        }
        catch (UtilizadorSemFaturaException e){
            System.out.println(e.getMessage());
        }
        catch (FaturaInexistenteException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Verifica faturas asscoiadas a um contribuinte bem como ao seu agregrado familiar 
     */
    
    private void consultaFaturas(){
       int nif = contribuinteSessao;
        
       try{
            System.out.println(javaFatura.consultaFat(nif).toString());
            System.out.println("\nDedução fiscal acumulada: " + javaFatura.consultaDeducoes(nif));
        }
       catch (UtilizadorSemFaturaException e){
            System.out.println(e.getMessage());
       }
    }
    
    /** 
     * Associa uma actividade Economica as despesas invalidas 
     */
    
    private void associaActEconoFact(){
        int nif = contribuinteSessao;
        Scanner sc = new Scanner(System.in);
        List<Integer> ids = new ArrayList<>();
        List<Despesa> despInv = new ArrayList<>();
        Map<Integer,ActividadeEconomica> codActE =  new HashMap<>();
        Map<Integer,ActividadeEconomica> codActC =  new HashMap<>();
        
        try{
            despInv = javaFatura.faturasInvalidas(nif);
            System.out.println("Tem as seguintes faturas ainda por validar: \n");
            System.out.println(despInv.toString());
            System.out.println("Prentende corrigir as actividades economicas: ");
            System.out.println("1-Sim");
            System.out.println("0-Nao");
            System.out.print("Opcao: ");
            int num = sc.nextInt();
            switch(num){
                case 1: {
                    System.out.print("Insira ID da despesa: ");
                    int id = sc.nextInt();
                    codActE = javaFatura.codActiEmp(id,despInv);
                    System.out.println("\nCodigo das ActividadesEconomicas da Empresa emitente da despesa, so pode selecionar um: ");
                    for(Map.Entry<Integer,ActividadeEconomica> entry : codActE.entrySet()) {
                        Integer codE = entry.getKey();
                        ActividadeEconomica aeE = entry.getValue();
                        System.out.println("Codigo: " +codE+ "-> ActividadeEconomica: " +aeE);    
                    }
                    codActC = javaFatura.codActiCont(nif);
                    System.out.println("\nCodigo das ActividadesEconomicas que voce pode deduzir: ");
                    for(Map.Entry<Integer,ActividadeEconomica> entry : codActC.entrySet()) {
                        Integer codC = entry.getKey(); //!!!!
                        ActividadeEconomica aeC = entry.getValue(); 
                        System.out.println("Codigo: " +codC+ "-> ActividadeEconomica: " +aeC);    
                    }
                    System.out.print("\nIntroduza o codigo: ");
                    int cod = sc.nextInt();
                    if(codActE.containsKey(cod)){
                        if(codActC.containsKey(cod)){
                            ActividadeEconomica ae = new ActividadeEconomica();
                            ae = codActE.get(cod);
                            int flag = 0;
                            flag = javaFatura.corrigeFact(id,ae,nif);                 
                            if(flag==1){
                                javaFatura.adicionaId(nif,id);
                                System.out.println("\nCorrigiu com sucesso a Actividade Economica da Despesa com o ID "+id+ " ...");
                            }
                            else{
                                 System.out.println("\nCodigo errado...");
                            }
                        }
                        else{
                            System.out.println("\nVoce nao deduz para essa ActividadeEconomica...");
                        }
                    }
                    else{
                        System.out.println("\nIntroduza um codigo que pertença a empresa...");
                    }
                }
                case 0: {
                    break;
                }
            }
        }
        catch(UtilizadorSemFaturaException e){
            System.out.println(e.getMessage());
        }
        catch(FaturaInexistenteException e){
            System.out.println("\nErro: " +e.getMessage());
        }
        catch(ActividadeEconomicaInexistenteException e){
            System.out.println("\nErro: " +e.getMessage());
        }
        sc.close();
    }
    
    /**
     * Faz uma consulta das faturas associadas a uma Empresa por data de emisao ou por valor 
     */
    
    private void consultaFaturasEmpresa(){
        int opcao = -1;
        int nif;
                
        do{
            System.out.println("Visualizar por:");
            System.out.println("1-Data de emissao");
            System.out.println("2-Valor");
            System.out.println("0-Sair");
            System.out.print("Opcao: ");
            opcao = lerInteiro("Opcao: ");
        }while(opcao != 1 && opcao != 2 && opcao !=0);
        
        if(opcao != 0){
            
            do{
               System.out.print("O NIF deve conter 9 digitos, caso contrario, nao irá continuar !!\n");  
               System.out.print("NIF: ");
               nif = lerInteiro("NIF: ");
            }while(verificaNif(nif));
             
            try{
                
                if(opcao==1){
                    System.out.println("Faturas ordenas por Data de Emissao: \n" + javaFatura.ordenaDataFact(nif).toString());
                }
                
                if(opcao==2){
                    System.out.println("Faturas ordenas por valor: \n" + javaFatura.ordenaValorFact(nif).toString());
                }
                
            }
            catch (UtilizadorInexistenteException e){
                System.out.println("\nErro: " +e.getMessage());
            }
            catch(FaturaInexistenteException e){
                System.out.println("\nErro: " +e.getMessage());
            }
        }    
    }
    
    /** 
     * Consulta as faturas dum contribuinte entre duas datas 
     */
    
    private void faturaEntreDatas(){
        Scanner scin = new Scanner(System.in);
        int nifC;
        int ano, mes, dia;
        int nifE = contribuinteSessao;
        
        do{
           System.out.print("Introduza o NIF do cliente. (9 -> numeros) !!\n");  
           System.out.print("NIF: ");
           nifC = lerInteiro("NIF: ");
        }while(verificaNif(nifC));
 
        System.out.print("Insira a primeira data: \n");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>=2018));
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
        
        System.out.print("Insira a segunda data: \n");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>=2018));
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
            
            try{
                System.out.println("Faturas do cliente com o NIF " +nifC+ ": \n" + javaFatura.consuFactData(nifE,nifC,data1,data2).toString());
            }
            catch(UtilizadorInexistenteException e){
                System.out.println("\nErro: " +e.getMessage());
            }
            catch(FaturaInexistenteException e){
                System.out.println("\nErro: " +e.getMessage());
            }
            catch(UtilizadorSemFaturaException e){
                System.out.println("\nErro: " +e.getMessage());
            }
        }
        else {
            System.out.println("\nErro a introduzir as datas...");
        }
        scin.close();
    }
    
    /** 
     *  Consultar as faturas por contribuinte ordenadas por valor
     */
    
    private void consutlaFactValor(){
        int nifC;
        int nifE = contribuinteSessao;
        
        do{
           System.out.print("Introduza o NIF do cliente. (9 -> numeros) !!\n");  
           System.out.print("NIF: ");
           nifC = lerInteiro("NIF: ");
        }while(verificaNif(nifC));
        
        try{
            System.out.println("\nFaturas do cliente com o NIF " +nifC+ ": \n\n" + javaFatura.consuFactValor(nifE,nifC).toString());
        }
        catch(UtilizadorInexistenteException e){
            System.out.println("\nErro: " +e.getMessage());
        }
        catch(FaturaInexistenteException e){
            System.out.println("\nErro: " +e.getMessage());
        }
        catch(UtilizadorSemFaturaException e){
            System.out.println("\nErro: " +e.getMessage());
        }
    }
    
    /** Total faturado por uma empresa num peoriodo 
     *
     */
    
    private void totalFaturado(){
        Scanner scin = new Scanner(System.in);
        int ano, mes, dia;
        int nifE;
        
        do{
           System.out.print("Introduza o NIF da Empresa. (9 -> numeros) !!\n");  
           System.out.print("NIF: ");
           nifE = lerInteiro("NIF: ");
        }while(verificaNif(nifE));
 
        System.out.print("Insira a primeira data: \n");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>=2018));
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
        
        System.out.print("Insira a segunda data: \n");
        do{
            System.out.print("Ano: ");
            ano = lerInteiro("Ano: ");
        } while(!(ano>=2018));
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
            try{
                System.out.println("\nTotal faturado da empresa -> " +nifE+ " <- entre as datas " +data1+ "<->" +data2+ ": " + javaFatura.totalFact(nifE,data1,data2) + " €");
            }
            catch(UtilizadorInexistenteException e){
                System.out.println("\nErro: " +e.getMessage());
            }
            catch(UtilizadorSemFaturaException e){
                System.out.println("\nErro: " +e.getMessage());
            }
        }
        else {
            System.out.println("\nErro a introduzir as datas...");
        }
        scin.close();
    }
    
    
    ////////////////////////////      Funçoes do administrador      //////////////////////////////////
     
    /** 
     * 10 contribuinte que mais gastam 
     */
    
    private void top10Contribuinte(){
            try{
                System.out.println("Os 10 contribuinte que mais gastam na aplicacao:\n");
                Map<Contribuinte,Double> resp = new HashMap<>();
                resp.putAll(javaFatura.topCliente());
                int i = 10;
                for (Map.Entry<Contribuinte,Double> entry : resp.entrySet()) {
                    Contribuinte contribuinte = entry.getKey();
                    Double gasto = entry.getValue();
                    if(i>0){
                        System.out.println("Contribuinte:  " +contribuinte.getNif() + "\nValor Gasto: " + gasto +"\n");
                        i--;
                    }
                }
            }
            catch(UtilizadorInexistenteException e){
                System.out.println("\nErro: " +e.getMessage());
            }
            catch(FaturaInexistenteException e){
                System.out.println("\nErro: " +e.getMessage());
            }
    }
    
    /** 
     * X empresas que mais faturas emitem no sistema e o montante dessas deduçoes fiscais
     */
    
    private void topXEmpresas(){
        Scanner sc = new Scanner(System.in);
        int num = 0;
        
        System.out.print("Introduza quantas empresas prentende consultar: ");
        num = sc.nextInt();
        
        try{
            Map<Empresa,Double> resp = new HashMap<>();
            resp.putAll(javaFatura.topEmpresas());   
            int i = 1;
            for (Map.Entry<Empresa,Double> entry : resp.entrySet()) {
                Empresa empresa = entry.getKey();
                Double deducao = entry.getValue();
                if(num>0){
                    System.out.println("\nEmpresa "+ i++ + ": \n" + "Nif: "+ empresa.getNif() + "\nDeducao: " + deducao +"\n");
                    num--;
                }
            }
        }
        catch(UtilizadorInexistenteException e){
            System.out.println("\nErro: " +e.getMessage());
        }
        catch(FaturaInexistenteException e){
            System.out.println("\nErro: " +e.getMessage());
        }
        sc.close();
    }
    
    /**
     * Adiciona cidades com incentivo fiscal
     */
    
    private void adicionaCidade(){
        Scanner sc = new Scanner(System.in);
        String cidade;
        double perc = 0.0;
        
        System.out.print("Introduza a cidade: ");
        cidade = sc.nextLine();
        System.out.print("Introduza a percentagem: ");
        perc = sc.nextDouble();
        
        try{
            javaFatura.adicionaConcelho(cidade,perc);
            System.out.println("\nAdicionada cidade...");
        }
        catch(CidadeExistenteException e){
            System.out.println("\nErro: " +e.getMessage());
        }
    }
    
    /**
     * Método que guarda o estado de uma instância num ficheiro de texto.
     */
    
    public void escreveEmFicheiroTxt(String nomeFicheiro) throws IOException {
       PrintWriter fich = new PrintWriter(nomeFicheiro);
       fich.println("------- JavaFatura --------");
       fich.println(this.toString()); // ou fich.println(this);
       fich.flush();
       fich.close();
     }

    /**
     * Método que guarda em ficheiro de objectos o objecto que recebe a mensagem.
     */
    
    public void guardaEstado(JavaFatura javaFatura, String nomeFicheiro) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(javaFatura); //guarda-se todo o objecto de uma só vez
        oos.flush();
        oos.close();
    }
    
     /**
     * Método que recupera uma instância de JavaFatura de um ficheiro de objectos.
     * Este método tem de ser um método de classe que devolva uma instância já construída de
     * JavaFatura.
     * 
     * @param nome do ficheiro onde está guardado um objecto do tipo JavaFatura
     * @return objecto JavaFatura inicializado
     */
   
    public static JavaFatura carregaEstado(String nomeFicheiro) throws FileNotFoundException,IOException,ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(fis);
        JavaFatura j = (JavaFatura) ois.readObject();
        ois.close();
        return j;
    }
    
    /////////////////////////    funcoes auxiliares    ///////////////////////////////
    
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
    
    private boolean verificaNif(int nif){
        return String.valueOf(nif).length() != 9 ;
    }
    
    private boolean verificaLocalizacao(String localizacao){
        return ((localizacao.equals("Litoral")) || (localizacao.equals("litoral")) || (localizacao.equals("Interior")) || (localizacao.equals("interior")));
    }
}
