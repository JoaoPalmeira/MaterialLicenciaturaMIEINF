
/**
 * Classe Imoobiliaria: início e controlo de todas as operações 
 */
import java.lang.*;
import java.util.*;
import java.io.*;

public class Imoobiliaria
{
    //private String clientFile = "Clientes.txt";
    public Vector<Utilizador> users = new Vector<Utilizador>();
    public ArrayList<Imovel> imoveisGeral = new ArrayList<Imovel>();
    public Imoobiliaria () { initApp(); }
    
    private void initApp() { MenuInicial(); }
    
    private void MenuInicial() {
        System.out.println("______________________");
        System.out.println(" --- Imoobiliaria --- ");
        System.out.println("______________________");
        System.out.println("'R'egistar   'E'ntrar"); 
        System.out.println("'L'istar Utilizadores");
        Scanner input = new Scanner(System.in);
        String log1;
                
        log1 = input.next();
        
        while (!log1.equals("R") && !log1.equals("E") && !log1.equals("L")) { 
            System.out.println("Char introduzido não correspondente.");
            log1 = input.next();
        }    
        //Linhas de organização
        System.out.printf("\n\n\n\n");
        if (log1.equals("R")) MenuRegUtil();
        else if (log1.equals("E")) MenuInitSessao();
        else if (log1. equals("L")) {
            showUsers();
            MenuInicial();
        }
    }
    private void MenuRegUtil() {
        String m,n,pw,mo,tipo;
        int d;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Pretende registar-se na plataforma Imoobiliaria. Para finalizar, são necessários os seguintes dados:");
        System.out.print("E-Mail: ");
        m = input.nextLine();
        System.out.print("Nome: "); 
        n = input.nextLine();
        System.out.print("Password: ");
        pw = input.nextLine();
        System.out.print("Morada: ");
        mo = input.nextLine();
        System.out.print("Data de Nascimento (DDMMAAA): ");
        d = input.nextInt();
        System.out.print("'V'endedor / 'C'omprador: ");
        tipo = input.next();
        
        while (!tipo.equals("V") && !tipo.equals("C")) {
            System.out.println("\nTipo de Utilizador não reconhecido. Tente novamente.");
            tipo = input.next();
        }
        Utilizador novo;
        if (tipo.equals("V")) {
            novo = new Vendedor(m,n,pw,mo,d);
        } else {
            novo = new Comprador(m,n,pw,mo,d);
        }
        try {
            registarUtilizador(novo);
        }
        catch (UtilizadorExistenteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        MenuInicial();
    }
        
    private void MenuInitSessao() {
        String mail;
        String pass;
        Scanner input2 = new Scanner(System.in);
        System.out.println("Escolheu iniciar sessão como utilizador registado.");
        System.out.print("E-mail: ");
        mail = input2.next();
        System.out.print("Password: ");
        pass = input2.next();
        
        try {
            iniciaSessao(mail,pass);
        } catch (SemAutorizacaoException e) {
            System.out.println("Erro: " + e);
        }
        
    }
    
    private void MenuVendedor(){
        // olá, nome de utilizador
        int n;
        Scanner num = new Scanner(System.in);
        System.out.printf("1. Consultar informação própria;\n2. Colocar Imóvel à venda;\n3. Alterar estado de Imóvel;\n4. Ver Imovéis mais consultados;\n5. Informações Gerais.");
        n = num.nextInt();
        MenuAdicionaImovel();
        //case
        
    }
    private void MenuComprador() {
       int n;
       Scanner num = new Scanner(System.in);
       System.out.println("1. Consultar informação própria;\n2. Pesquisar Imóveis por Preço;\n3. Informações Gerais.");
       n = num.nextInt();
       
    }
    private void subMenuGeral() { 
        // está nos dois menus.
        int n;
        Scanner num = new Scanner(System.in);
        System.out.println("1. Lista Geral de Imóveis\n2. Lista de Imóveis Habitáveis\n3. Mapeamento de Imóveis por Vendedor");
        n = num.nextInt();
        
        //case
    }
    
    public void registarUtilizador(Utilizador utilizador) throws UtilizadorExistenteException {
        
        for (int i = 0; i < users.size(); i++) {
            if (utilizador.getMail().equals(users.get(i).getMail())) {
                throw new UtilizadorExistenteException("Mail já existente!");
            }
        }
        users.add(utilizador);
        System.out.println("Utilizador registado com sucesso.");
    }
    
    private void showUsers() {
        System.out.println("Lista de Utilizadores Registados:");
        for (int i = 0; i<users.size(); i++) {
            users.get(i).showUtilizador();
            System.out.print("\n");
        }
        System.out.println("----------------");
    }
    
    
    
    public void iniciaSessao(String email, String password) throws SemAutorizacaoException{
        for (int i = 0; i<users.size(); i++) {
            if (email.equals(users.get(i).getMail())) {
                if (!password.equals(users.get(i).getPass())) {
                    throw new SemAutorizacaoException("Password Incorreta!");
                } else {
                    System.out.println("\nSucesso!");
                    if (users.get(i).isComprador() == 1) {
                     //   Comprador comp = users.get(i);
                        MenuComprador();
                    } else {
                       // Vendedor vend = users.get(i);
                        MenuVendedor();
                    }
                }
            }
            if (i == users.size()-1 && !email.equals(users.get(i).getMail())) {
                System.out.println("O e-mail introduzido não corresponde a um utilizador registado na plataforma.");
                MenuInicial();
            }
            
        }
    }
    
    public void MenuAdicionaImovel() {
        String id,r,est;
        Scanner input = new Scanner(System.in);
        double pre,pmin;
        int v = 0;
        System.out.println("ID do imóvel: ");
        id = input.next();
        System.out.println("Rua: ");
        r = input.nextLine();
        System.out.println("Estado: ");
        est = input.nextLine();
        System.out.println("Preço(€): ");
        pre = input.nextInt();
        System.out.println("Preço Mínimo exigido(€): ");
        pmin = input.nextInt();
        
        while (pmin > pre) {
            System.out.println("O preço mínimo exigido não pode ser superior ao preço do imóvel.\nIntroduza novamente um valor:");
            pmin = input.nextInt();
        }
        
        int arg;
        System.out.println("Tipo de Imóvel a adicionar\n1.Apart;\n2. Moradia;\n3. Loja;\n4. Terreno");
        arg = input.nextInt();
        
        switch(arg) {
            case 1:
                String tipoApa;
                double areaApa;
                int wcApa,porApa;
                Boolean garagem;
                
                System.out.println("Tipo de Apartamento: ");
                tipoApa = input.next();
                System.out.println("Área do apartamento: ");
                areaApa = input.nextDouble(); //verificar
                System.out.println("Nº de WC: ");
                wcApa = input.nextInt();
                System.out.println("Nº da Porta: ");
                porApa = input.nextInt();
                if(garagem = True){
                System.out.println("Tem garagem.");}
                else System.out.println("Não tem garagem.");
                garagem = input.nextBoolean();
                
                Apartamento novo = new Apartamento(id,r,pre,pmin,v,tipoApa,e,tipoApa,areaApa,wcApa,porApa,garagem);
               
                imoveisGeral.add(novo);
                System.out.println("Imóvel adicionado com sucesso!");
                break;
            case 2:
                String tipomor;
                double ai,ac,ae;
                int wc,pm;
                
                System.out.println("Tipo de Moradia: ");
                tipomor = input.next();
                System.out.println("Área de Implantação: ");
                ai = input.nextDouble(); //verificar
                System.out.println("Área Coberta: ");
                ac = input.nextDouble();
                System.out.println("Área Envolvente: ");
                ae = input.nextDouble();
                System.out.println("Nº de WC: ");
                wc = input.nextInt();
                System.out.println("Nº da Porta: ");
                pm = input.nextInt();
                
                Moradia novo = new Moradia(id,r,pre,pmin,v,tipomor,est,ai,ac,ae,wc,pm);
               
                imoveisGeral.add(novo);
                System.out.println("Imóvel adicionado com sucesso!");
                break;
            case 3:
                String neg;
                double areaL;
                int portaL;
                Boolean wcLoja;
                
                System.out.println("Tipo de Negócio: ");
                neg = input.next();
                System.out.println("Área da Loja: ");
                areaL = input.nextDouble(); //verificar
                System.out.println("Nº da Porta: ");
                portaL = input.nextInt();
                if(wcLoja = True){
                System.out.println("Tem WC.");}
                else System.out.println("Não tem WC.");
                wcLoja = input.nextBoolean();
                
                Loja novo = new Loja(id,r,pre,pmin,v,neg, areaL, portaL, wcLoja);
               
                imoveisGeral.add(novo);
                System.out.println("Imóvel adicionado com sucesso!");
                break;
            case 4:
                String tipoTerreno;
                int diametro;
                float kwh;
                Boolean esgoto;
                
                System.out.println("Tipo de Terreno: ");
                tipoTerreno = input.next();
                System.out.println("Diametro das canalizações: ");
                diametro = input.nextInt();
                System.out.println("Consumo energético:");
                kwh = imput.nextFloat();
                if(esgoto = True){
                System.out.println("Tem esgoto.");}
                else System.out.println("Não tem esgoto.");
                esgoto = input.nextBoolean();
                
                
                Terreno novo = new Terreno(id,r,pre,pmin,v,tipoTerreno,es,diametro,kwh,e);
               
                imoveisGeral.add(novo);
                System.out.println("Imóvel adicionado com sucesso!");
                break;
            default : //Dá um erro aqui!
        }
    }
    public List <Imovel > getImovel ( String classe , int preco ) {
        List<Imovel> x = new ArrayList<Imovel>();
        for (Imovel o : imoveisGeral){
            if (x.getClass().getSimpleName().equals(classe) && o.getPreco()<preco) x.add(o.clone());
        }
        return x;
    }
    public void setEstado ( String idImovel , String estado ) throws ImovelInexistenteException, SemAutorizacaoException ,EstadoInvalidoException {

        if (this.getClass().getSimpleName().equals("Comprador")) throw new SemAutorizacaoException("Permissão Negada");
        
        for (int i = 0; i < imoveisGeral.size(); i++) {
            if (imoveisGeral.get(i).getId().equals(idImovel)){
                switch (estado.toLowerCase()) {
                    case "venda":
                        Estado v = Estado.VENDA;
                        imoveisGeral.get(i).setEstadoAux(v);
                    case "aluguer":
                        Estado a = Estado.ALUGUER;
                        imoveisGeral.get(i).setEstadoAux(a);
                    case "trespasse":
                        Estado t = Estado.TRESPASSE;
                        imoveisGeral.get(i).setEstadoAux(t);
                    case "ocupado":
                        Estado o = Estado.OCUPADO;
                        imoveisGeral.get(i).setEstadoAux(o);
                    default: throw new EstadoInvalidoException("Estado inválido.");
                }
            }
            else if (i == imoveisGeral.size()-1) throw new ImovelInexistenteException ("O imóvel não existe.");
        }
         
    }
}

