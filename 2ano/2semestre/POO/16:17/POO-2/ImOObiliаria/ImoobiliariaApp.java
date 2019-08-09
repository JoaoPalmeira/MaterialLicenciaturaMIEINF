import java.io.IOException;
import java.util.Scanner;
import java.util.GregorianCalendar;

public class ImoobiliariaApp
{   
    private ImoobiliariaApp(){}
    
    private static Imoobiliaria imo;
    
    private static Menu menuinit, menuvend, menucomp, menuaux;
    
    public static void main(String[] args) 
    {
        carregarMenus();
        imo=initApp();
        outerloop:
        do
        {
            menuinit.executar();
            switch (menuinit.getOpcao()) 
            {
                case 1: loginImo();
                        if(imo.getUtilizador()!=null) break outerloop;
                        else break;
                case 2: registarImo();
                        break;
            }
        } while(menuinit.getOpcao()!=0);
        if(imo.getUtilizador()!=null)
        {
            if(imo.getUtilizador().getClass()==Vendedor.class)
            {
                do
                {
                    menuvend.executar();
                    switch (menuvend.getOpcao()) 
                    {
                        case 1: addImo();
                                break;
                        case 2: consultasImo();
                                break;
                        case 3: alteraImo();
                                break;
                        case 4: topImo();
                                break;
                        case 5: mostraImo();
                                break;
                        case 6: mostraHab();
                                break;
                        case 7: mostraMapImo();
                                break;        
                    }
                } while (menuvend.getOpcao()!=0);
            }
            else if(imo.getUtilizador().getClass()==Comprador.class)
            {
                do
                {
                    menucomp.executar();
                    switch (menucomp.getOpcao()) 
                    {
                        case 1: mostraImo();
                                break;
                        case 2: mostraHab();
                                break;
                        case 3: mostraMapImo();
                                break;
                        case 4: marcaImo();
                                break;
                        case 5: favoritosImo();
                                break;
                    }
                } while (menucomp.getOpcao()!=0);
            }
        }
        imo.fechaSessao();
        try 
        {
            imo.gravaObj("estado.imo");
        }
        catch (IOException e) 
        {
            System.out.println("Não consegui gravar os dados!");
        }
        System.out.println("Até à proxima!");     
    }
    
    public static Imoobiliaria initApp()
    {
       try
       {
            imo = Imoobiliaria.leObj("estado.imo");
       }
       catch(IOException e)
       {
           imo = new Imoobiliaria();
           System.out.println("Não foi possível ler os dados!\nErro de leitura.");
       }
       catch(ClassNotFoundException e)
       {
           imo = new Imoobiliaria();
           System.out.println("Não foi possível ler os dados!\nFicheiro com formato desconhecido.");
       }
       catch(ClassCastException e)
       {
           imo = new Imoobiliaria();
           System.out.println("Não foi possível ler os dados!\nErro de formato.");
       }
       return imo;
    }
    
    private static void carregarMenus() 
    {
        String[] opsinit = {"Login.",
                            "Registo."};
        String[] opsvend = {"Registar Imóvel.",
                            "Lista das 10 últimas consultas aos imóveis à venda.",
                            "Alterar estado do Imóvel.",
                            "Os Imóveis mais consultados.",
                            "Consultar Imóveis.",
                            "Consultar Imóveis habitáveis.",
                            "Consultar Imovéis e respetivos vendedores."};
        String[] opscomp = {"Consultar Imóveis.",
                            "Consultar Imóveis habitáveis.",
                            "Consultar Imovéis e respetivos vendedores.",
                            "Marcar um Imóvel como favorito.",
                            "Consultar Imóveis favoritos."};
        String[] opsaux = {"Adicionar Vendedor",
                           "Adicionar Comprador"
                          };
        menuinit = new Menu(opsinit);
        menuvend = new Menu(opsvend);
        menucomp = new Menu(opscomp);
        menuaux = new Menu(opsaux);
    }
    
    private static void registarImo()
    {
        Scanner sc = new Scanner(System.in);
        String email;
        String nome;
        String password;
        String morada;
        GregorianCalendar dataNascimento = new GregorianCalendar(1900,1,1);
        String dataTmp;
        int diatmp;
        int mestmp;
        int anotmp;
        boolean naoinseriu=true;
        menuaux.executar();
        if(menuaux.getOpcao() == 1)
        {
            System.out.print("Nome: ");
            nome = sc.nextLine();
            System.out.print("Email: ");
            email = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Morada: ");
            morada = sc.nextLine();
            while(naoinseriu)
            {
                System.out.print("Data de Nascimento: (AAAA-MM-DD)");
                dataTmp=sc.nextLine();
                try
                {
                    anotmp=Integer.parseInt(dataTmp.substring(0,4));
                    mestmp=Integer.parseInt(dataTmp.substring(5,7));
                    diatmp=Integer.parseInt(dataTmp.substring(8,10));
                    dataNascimento = new GregorianCalendar(anotmp,mestmp,diatmp);
                    naoinseriu=false;
                }
                catch(Exception e)
                {
                    System.out.println("Data Invalida!");
                }
            }
            Vendedor vend = new Vendedor(email,nome,password,morada,dataNascimento);
            try
            {
                imo.registarUtilizador(vend);
            }
            catch (UtilizadorExistenteException u)
            {
                System.out.println(u.getMessage());
            }
        }
        else if(menuaux.getOpcao() == 2)
        {
            System.out.print("Nome: ");
            nome = sc.nextLine();
            System.out.print("Email: ");
            email = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();
            System.out.print("Morada: ");
            morada = sc.nextLine();
            while(naoinseriu)
            {
                System.out.print("Data de Nascimento: (AAAA-MM-DD)");
                dataTmp=sc.nextLine();
                try
                {
                    anotmp=Integer.parseInt(dataTmp.substring(0,4));
                    mestmp=Integer.parseInt(dataTmp.substring(5,7));
                    diatmp=Integer.parseInt(dataTmp.substring(8,10));
                    dataNascimento = new GregorianCalendar(anotmp,mestmp,diatmp);
                    naoinseriu=false;
                }
                catch(Exception e)
                {
                    System.out.println("Data Invalida!");
                }
            }
            Comprador comp = new Comprador(email,nome,password,morada,dataNascimento);
            try
            {
                imo.registarUtilizador(comp);
            }
            catch (UtilizadorExistenteException u)
            {
                System.out.println(u.getMessage());
            }
        }
        else
        {
            System.out.println("Inserção cancelada!");
        }
        sc.close();
    }
    
    private static void loginImo()
    {
        Scanner sc = new Scanner(System.in);
        String email;
        String password;
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        try
        {
            imo.iniciaSessao(email,password);
        }
        catch (SemAutorizacaoException u)
        {
            System.out.println(u.getMessage());
        }
    }
    
    private static void addImo()
    {
        Scanner sc = new Scanner(System.in);
        String tipoim;
        String id;
        char estado = 'A';
        String rua;
        double preco;
        double precoMin;
        System.out.print("Tipo do Imóvel: (Moradia,Apartamento,Loja,Terreno) ");
        tipoim = sc.nextLine();
        while(!tipoim.equals("Moradia") && !tipoim.equals("Loja") && !tipoim.equals("Terreno") && !tipoim.equals("Apartamento"))
        {
            System.out.println("Tipo Invalido!");
            System.out.print("Tipo do Imóvel: (Moradia,Apartamento,Loja,Terreno) ");
            tipoim = sc.nextLine();
        }
        if(tipoim.equals("Moradia"))
        {
            String tipo;
            double area;
            double areaCoberta;
            double areaEnvolvente;
            int quartos;
            int wcs;
            int porta;
            System.out.print("ID: ");
            id = sc.nextLine();
            System.out.print("Rua: ");
            rua = sc.nextLine();
            System.out.print("Preço: ");
            preco = sc.nextDouble();
            System.out.print("Preço Minimo: ");
            precoMin = sc.nextDouble();
            System.out.print("Tipo da Moradia: (Isolada, Geminada, Banda, Gaveto) ");
            tipo = sc.nextLine();
            while(!tipo.equals("Isolada") && !tipo.equals("Geminada") && !tipo.equals("Banda") && !tipo.equals("Gaveto"))
            {
                System.out.println("Tipo Invalido!");
                System.out.print("Tipo da Moradia: (Isolada, Geminada, Banda, Gaveto) ");
                tipo = sc.nextLine();
            }
            System.out.print("Area: ");
            area = sc.nextDouble();
            System.out.print("Area Coberta: ");
            areaCoberta = sc.nextDouble();
            System.out.print("Area Envolvente: ");
            areaEnvolvente = sc.nextDouble();
            System.out.print("Numero de quartos: ");
            quartos = sc.nextInt();
            System.out.print("Numero de casas de banho: ");
            wcs = sc.nextInt();
            System.out.print("Numero da porta: ");
            porta = sc.nextInt();
            Moradia m = new Moradia(id,estado,rua, preco, precoMin, tipo, area, areaCoberta, areaEnvolvente, quartos, wcs, porta);
            try
            {
                imo.registaImovel(m);
            }
            catch (ImovelExisteException i)
            {
                System.out.println(i.getMessage());
            }
            catch (SemAutorizacaoException i)
            {
                System.out.println(i.getMessage());
            }
        }
        else if(tipoim.equals("Apartamento"))
        {
            String tipo;
            double area;
            int quartos;
            int wcs;
            int porta;
            int andar;
            char garagem;
            System.out.print("ID: ");
            id = sc.nextLine();
            System.out.print("Rua: ");
            rua = sc.nextLine();
            System.out.print("Preço: ");
            preco = sc.nextDouble();
            System.out.print("Preço Minimo: ");
            precoMin = sc.nextDouble();
            System.out.print("Tipo do Apartamento: (Simples, Duplex, Triplex) ");
            tipo = sc.nextLine();
            while(!tipo.equals("Simples") && !tipo.equals("Duplex") && !tipo.equals("Triplex"))
            {
                System.out.println("Tipo Invalido!");
                System.out.print("Tipo do Apartamento: (Simples, Duplex, Triplex) ");
                tipo = sc.nextLine();
            }
            System.out.print("Area: ");
            area = sc.nextDouble();
            System.out.print("Numero de quartos: ");
            quartos = sc.nextInt();
            System.out.print("Numero de casas de banho: ");
            wcs = sc.nextInt();
            System.out.print("Numero da porta: ");
            porta = sc.nextInt();
            System.out.print("Andar: ");
            andar = sc.nextInt();
            System.out.print("Tem garagem? (S/N)");
            garagem = sc.next().charAt(0);
            while(!(garagem=='s') && !(garagem=='S') && !(garagem=='n') && !(garagem=='N'))
            {
                System.out.println("Responda à pergunta!");
                System.out.print("Tem garagem? (S/N)");
                garagem = sc.next().charAt(0);
            }
            Apartamento a = new Apartamento(id, estado, rua, preco, precoMin, tipo, area, quartos, wcs, porta, andar, garagem);
            try
            {
                imo.registaImovel(a);
            }
            catch (ImovelExisteException i)
            {
                System.out.println(i.getMessage());
            }
            catch (SemAutorizacaoException i)
            {
                System.out.println(i.getMessage());
            }
        }
        else if(tipoim.equals("Loja"))
        {
            double area;
            char wc;
            String tipo;
            int porta;
            System.out.print("ID: ");
            id = sc.nextLine();
            System.out.print("Rua: ");
            rua = sc.nextLine();
            System.out.print("Preço: ");
            preco = sc.nextDouble();
            System.out.print("Preço Minimo: ");
            precoMin = sc.nextDouble();
            System.out.print("Area: ");
            area = sc.nextDouble();
            System.out.print("Tem casa de banho? (S/N)");
            wc = sc.next().charAt(0);
            while(!(wc=='s') && !(wc=='S') && !(wc=='n') && !(wc=='N'))
            {
                System.out.println("Responda à pergunta!");
                System.out.print("Tem casa de banho? (S/N)");
                wc = sc.next().charAt(0);
            }
            System.out.print("Tipo de Loja: ");
            tipo = sc.nextLine();
            System.out.print("Numero da porta: ");
            porta = sc.nextInt();
            Loja l = new Loja(id,estado, rua, preco, precoMin, area, wc, tipo, porta);
            try
            {
                imo.registaImovel(l);
            }
            catch (ImovelExisteException i)
            {
                System.out.println(i.getMessage());
            }
            catch (SemAutorizacaoException i)
            {
                System.out.println(i.getMessage());
            }
        }
        else 
        {
            double area;
            char tipo;
            double diametro=0.0;
            long kwh;
            char esgoto='N';
            System.out.print("ID: ");
            id = sc.nextLine();
            System.out.print("Rua: ");
            rua = sc.nextLine();
            System.out.print("Preço: ");
            preco = sc.nextDouble();
            System.out.print("Preço Minimo: ");
            precoMin = sc.nextDouble();
            System.out.print("Area: ");
            area = sc.nextDouble();
            System.out.print("É para Armazéns ou para Habitação? (H/A)");
            tipo = sc.next().charAt(0);
            while(!(tipo=='h') && !(tipo=='H') && !(tipo=='a') && !(tipo=='A'))
            {
                System.out.println("Responda à pergunta!");
                System.out.print("É para Armazéns ou para Habitação? (H/A)");
                tipo = sc.next().charAt(0);
            }
            System.out.print("kWh máximo suportados pela rede elétrica: ");
            kwh = sc.nextLong();
            System.out.print("Tem esgoto? (S/N)");
            esgoto = sc.next().charAt(0);
            while(!(esgoto=='s') && !(esgoto=='S') && !(esgoto=='n') && !(esgoto=='N'))
            {
                System.out.println("Responda a pergunta!");
                System.out.print("Tem esgoto? (S/N)");
                esgoto = sc.next().charAt(0);
            }
            if(esgoto=='s' || esgoto=='S')
            {
                System.out.print("Diametro da canalização: ");
                diametro = sc.nextDouble();
            }
            Terreno t = new Terreno(id,estado,rua,preco,precoMin,area,tipo,diametro,kwh,esgoto);
            try
            {
                imo.registaImovel(t);
            }
            catch (ImovelExisteException i)
            {
                System.out.println(i.getMessage());
            }
            catch (SemAutorizacaoException i)
            {
                System.out.println(i.getMessage());
            }
        }
    }
    
    private static void mostraImo()
    {
        Scanner sc = new Scanner(System.in);
        String tipoim;
        int preco;
        System.out.print("Tipo do Imóvel: (Moradia,Apartamento,Loja,Terreno) ");
        tipoim = sc.nextLine();
        while(!tipoim.equals("Moradia") && !tipoim.equals("Loja") && !tipoim.equals("Terreno") && !tipoim.equals("Apartamento"))
        {
            System.out.println("Tipo Invalido!");
            System.out.print("Tipo do Imóvel: (Moradia,Apartamento,Loja,Terreno) ");
            tipoim = sc.nextLine();
        }
        System.out.print("Preço Limite: " );
        preco = sc.nextInt();
        System.out.println(imo.getImovel(tipoim,preco).toString());
    }
    
    private static void mostraHab()
    {
        Scanner sc = new Scanner(System.in);
        int preco;
        System.out.print("Preço Limite: ");
        preco = sc.nextInt();
        System.out.println(imo.getHabitaveis(preco).toString());
    }
    
    private static void alteraImo()
    {
        Scanner sc = new Scanner(System.in);
        String idImovel;
        String estado;
        System.out.print("Diga o Id do Imovel: ");
        idImovel = sc.nextLine();
        System.out.print("Para que estado pretende alterar? (À Venda/Vendido): ");
        estado = sc.nextLine();
        try
        {
            imo.setEstado(idImovel,estado);
        }
        catch (ImovelInexistenteException i)
        {
            System.out.println(i.getMessage());
        }
        catch (SemAutorizacaoException i)
        {
            System.out.println(i.getMessage());
        }
        catch (EstadoInvalidoException i)
        {
            System.out.println(i.getMessage());
        }
    }
    
    private static void marcaImo()
    {
        Scanner sc = new Scanner(System.in);
        String idImovel;
        System.out.print("Diga o Id do Imovel: ");
        idImovel = sc.nextLine();
        try
        {
            imo.setFavorito(idImovel);
        }
        catch (ImovelInexistenteException i)
        {
            System.out.println(i.getMessage());
        }
        catch (SemAutorizacaoException i)
        {
            System.out.println(i.getMessage());
        }
    }
    
    private static void favoritosImo()
    {
        try
        {
            System.out.println(imo.getFavoritos().toString());
        }
        catch (SemAutorizacaoException i)
        {
            System.out.println(i.getMessage());
        }
    }
    
    private static void mostraMapImo()
    {
        System.out.println(imo.getMapeamentoImoveis().toString());
    }
    
    private static void consultasImo()
    {
        try
        {
            System.out.println(imo.getConsultas10().toString());
        }
        catch (SemAutorizacaoException i)
        {
            System.out.println(i.getMessage());
        }
    }
    
    private static void topImo()
    {
        Scanner sc = new Scanner(System.in);
        int top;
        boolean invalido=true;
        while(invalido)
        {
            System.out.print("Quantos Imovéis quer ver? ");
            top = sc.nextInt();
            if(top>0) invalido=false;
            else System.out.print("Numero invalido!");
        }
    }
}