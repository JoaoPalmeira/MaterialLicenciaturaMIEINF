
/**
 * Classe Imoobiliaria: início e controlo de todas as operações 
 */
import java.lang.*;
import java.util.*;
import java.io.*;

public class Imoobiliaria implements Serializable
{
    //private String clientFile = "Clientes.txt";
    public ArrayList<Utilizador> users = new ArrayList<Utilizador>();
    public ArrayList<Imovel> imoveisGeral = new ArrayList<Imovel>();
    public String ref1; //mail
    public String ref2; //pass
    public Imoobiliaria () {}
    
    public void initApp() { 
        MenuInicial();
    }
    public void fechaSessao() {

    }
    private void MenuInicial() {
        System.out.println("______________________");
        System.out.println(" --- Imoobiliaria --- ");
        System.out.println("______________________");
        System.out.println("'R'egistar   'E'ntrar"); 
        System.out.println("'L'istar Utilizadores");
        System.out.println("'S'air da Aplicação");
        Scanner input = new Scanner(System.in);
        String log1;
                
        log1 = input.next();
        
        while (!log1.equals("R") && !log1.equals("E") && !log1.equals("L") && !log1.equals("S")) { 
            System.out.println("Char introduzido não correspondente.");
            log1 = input.next();
        }    
        //Linhas de organização - Falta mudar para switch
        System.out.printf("\n\n\n\n");
        if (log1.equals("R")) MenuRegUtil();
        else if (log1.equals("E")) MenuInitSessao();
        else if (log1. equals("L")) {
            showUsers();
            MenuInicial();
        }
        else if (log1.equals("S")) {
       
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
        System.out.print("Data de Nascimento (DDMMAAAA): ");
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
    private void espacamento() {
        System.out.print("______________________\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    private void MenuVendedor(){
        // olá, nome de utilizador
        int n;
        Scanner num = new Scanner(System.in);
        System.out.println("Bem vindo, " + ref1);
        System.out.printf("1. Consultar informação própria;\n2. Colocar Imóvel à venda;\n3. Alterar estado de Imóvel;\n4. Ver Imóveis mais consultados;\n5. Informações Gerais.\n6. Sair\n");
        n = num.nextInt();
        switch (n) {
            case 1:
                printUser(ref1,ref2);
                espacamento();
                MenuVendedor();
                break;
            case 2: 
                MenuAdicionaImovel();
                espacamento();
                MenuVendedor();
                break;
            case 3:
                MenuSetEstado();
                espacamento();
                MenuVendedor();
                break;                
            case 4:
            case 5:
                subMenuGeral();
                espacamento();
                MenuVendedor();
                break;
            case 6:
                // Libertar espaço e cenas
                espacamento();
                MenuInicial();
                break;
            default: System.out.println("Escolha não reconhecida.");
        }
        //case
        
    }
    private void MenuComprador() {
        int n;
        Scanner num = new Scanner(System.in);
        System.out.println("Bem vindo, " + ref1 + "!");
        System.out.println("1. Consultar informação própria;\n3. Consultar Imóveis Favoritos;\n4. Adicionar Imóvel aos Favoritos;\n5. Informações Gerais;\n6. Sair.");
        n = num.nextInt();
       
        switch (n) {
            case 1:
                printUser(ref1,ref2);
                espacamento();
                MenuComprador(); 
                break;
            case 2:
                MenuGetImovel();
                espacamento();
                MenuComprador();
                break;
            case 3:
                
            case 4: 
                MenuSetFavorito();
                espacamento();
                MenuComprador();
            case 5:
                subMenuGeral();
                espacamento();
                MenuComprador();
                break;
            case 6:
                espacamento();
                MenuInicial();
                break;
            
            default: System.out.println("Escolha não reconhecida.");
        }
       
    }
    private void subMenuGeral() { 
        // está nos dois menus.
        int n;
        Scanner num = new Scanner(System.in);
        System.out.println("_________\n1. Lista Geral de Imóveis;\n2. Lista de Imóveis Habitáveis;\n3. Mapeamento de Imóveis por Vendedor;\n4. Listar Imóveis por Preço e Categoria\n5.  Sair.");
        n = num.nextInt();
        
        switch (n) {
            case 1:
                MenuListaGeral();
                espacamento();
                break;
            case 2:
                MenuHabitaveis();
                espacamento();
                break;
            case 3:
                MenuMapeamento();
                espacamento();
                break;
            case 4:
                MenuGetImovel();
                espacamento();
                break;
            default: System.out.println("Escolha não reconhecida.");
        }
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
    private void showImoveis() {
        System.out.println("Lista de Imóveis:");
        for (int i = 0; i<imoveisGeral.size(); i++) {
            System.out.println(i+1);
            imoveisGeral.get(i).showImovel();
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
                    ref1=email;
                    ref2=password;
                    if (users.get(i).getClass().getSimpleName().equals("Comprador")) {
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
    
    public void printUser(String m, String p) {
        for (int i= 0; i<users.size(); i++) {
            if (m.equals(users.get(i).getMail()) && p.equals(users.get(i).getPass())) {
                users.get(i).showUtilizador();
            }
        }
    }
    public void MenuAdicionaImovel() {
        String id,r;
        Scanner input = new Scanner(System.in);
        double pre,pmin;
        int v = 0;
        int est;
        System.out.println("ID do imóvel: ");
        id = input.next();
        System.out.println("Rua: ");
        r = input.next();
        System.out.println("Estado(1. Venda 2. Aluguer 3. Trespasse 4. Ocupado): ");
        est = input.nextInt();
        
        Estado estad = Estado.VENDA;
        while (!(est<=4 && est>=1)){
            System.out.println("Escolha inválida. Tente novamente.");
            est = input.nextInt();
        }
            
        switch (est) {
        
            case 1:
                estad = Estado.VENDA;
                break;
            case 2:
                estad = Estado.ALUGUER;
                break;
            case 3:
                estad = Estado.TRESPASSE;
                break;
             case 4:
                estad = Estado.OCUPADO;
                break;
            }
        
                
        System.out.println("Preço(€): ");
        pre = input.nextInt();
        System.out.println("Preço Mínimo exigido(€): ");
        pmin = input.nextInt();
        
        while (pmin > pre) {
            System.out.println("O preço mínimo exigido não pode ser superior ao preço do imóvel.\nIntroduza novamente um valor:");
            pmin = input.nextInt();
        }
        
        int arg;
        System.out.println("Tipo de Imóvel a adicionar\n1. Apartamento;\n2. Loja;\n3. Moradia;\n4. Terreno");
        arg = input.nextInt();
        
        switch(arg) {
            case 1:
                String tipoApa;
                double areaApa;
                int wcApa,porApa;
                String tempGar;
                Boolean garagem;

                System.out.println("Tipo de Apartamento: ");
                tipoApa = input.next();
                System.out.println("Área do apartamento: ");
                areaApa = input.nextDouble(); //verificar
                System.out.println("Nº de WC: ");
                wcApa = input.nextInt();
                System.out.println("Nº da Porta: ");
                porApa = input.nextInt();
                System.out.print("Tem garagem? [S/N] ");
                tempGar = input.next();
                
                while(!(tempGar.equals("S") || tempGar.equals("N")) ){
                    System.out.println("Erro. Tente Novamente!");
                    tempGar = input.next();
                }
                if (tempGar.equals("S")) garagem = true;
                else garagem = false;
                
                Apartamento novoApa = new Apartamento(id,r,pre,pmin,v,estad,tipoApa,areaApa,wcApa,porApa,garagem);

                imoveisGeral.add(novoApa);
                System.out.println("Imóvel adicionado com sucesso!");
                break;
            case 2:
                String neg;
                double areaL;
                int portaL;
                String tempWc;
                Boolean wcLoja;

                System.out.println("Tipo de Negócio: ");
                neg = input.next();
                System.out.println("Área da Loja: ");
                areaL = input.nextDouble(); //verificar
                System.out.println("Nº da Porta: ");
                portaL = input.nextInt();
                System.out.println("Tem WC? [S/N] ");                             
                tempWc = input.next();
                
                while(!(tempWc.equals("S") || tempWc.equals("N")) ){
                    System.out.println("Erro. Tente Novamente!");
                    tempGar = input.next();
                }
                
                if (tempWc.equals("S")) wcLoja = true;
                else wcLoja = false;
                
                Loja novaLoja = new Loja(id,r,pre,pmin,v,estad,neg, areaL, portaL, wcLoja);

                imoveisGeral.add(novaLoja);
                System.out.println("Imóvel adicionado com sucesso!");
                break;
            case 3:
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
                
                Moradia novaMorad = new Moradia(id,r,pre,pmin,v,tipomor,estad,ai,ac,ae,wc,pm);
               
                imoveisGeral.add(novaMorad);
                System.out.println("Imóvel adicionado com sucesso!");
                break;
            case 4:
                String tipoTerreno;
                int diametro;
                float kwh;
                String tempEsg;
                Boolean esgoto;

                System.out.println("Tipo de Terreno: ");
                tipoTerreno = input.next();
                System.out.println("Diametro das canalizações: ");
                diametro = input.nextInt();
                System.out.println("Consumo energético:");
                kwh = input.nextFloat();
                System.out.println("Tem WC? [S/N] ");                             
                tempEsg = input.next();
                
                while(!(tempEsg.equals("S") || tempEsg.equals("N")) ){
                    System.out.println("Erro. Tente Novamente!");
                    tempEsg = input.next();
                }
                if (tempEsg.equals("S")) esgoto = true;
                else esgoto = false;

                Terreno novoTerr = new Terreno(id,r,pre,pmin,v,estad,tipoTerreno,diametro,kwh,esgoto);

                imoveisGeral.add(novoTerr);
                System.out.println("Imóvel adicionado com sucesso!");
                break;        

            default : System.out.println("Escolha não reconhecida.");
        }
    }
    
    public void MenuGetImovel() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Classe de Imóvel: ");
        String cl = input.next();
        System.out.print("Preço? ");
        int pr = input.nextInt();
        
        List<Imovel> g = getImovel(cl,pr);
        
        for(Imovel o: g) {
            o.showImovel();
        }
    }
    public List <Imovel> getImovel ( String classe , int preco ) {
        List<Imovel> x = new ArrayList<Imovel>();
        for (Imovel o : imoveisGeral){
            if (x.getClass().getSimpleName().equals(classe) && o.getPreco()<preco) {
                if (classe.equals("apartamento")) {
                    Apartamento apa = (Apartamento) o;
                    x.add(apa);
                } else if (classe.equals("moradia")) {
                    Moradia mor = (Moradia) o;
                    x.add(mor);
                } else if (classe.equals("terreno")) {
                    Terreno ter = (Terreno) o;
                    x.add(ter);
                } else if (classe.equals("loja")) {
                    Loja loj = (Loja) o;
                    x.add(loj);
                }
            }
        }
        return x;
    }
    
    public void MenuSetEstado() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("ID do Imóvel: ");
        String id = input.next();
        System.out.print("Estado: ");
        String es = input.next();
        
        try {
            setEstado(id,es);
        } catch (ImovelInexistenteException e) {
            System.out.println("Erro. " + e.getMessage());
        } catch (SemAutorizacaoException f) {
            System.out.println("Erro. " + f.getMessage());
        } catch (EstadoInvalidoException g) {
            System.out.println("Erro. " + g.getMessage());
        }
        
        
            
    }
    
    public void setEstado ( String idImovel , String estado ) throws ImovelInexistenteException, SemAutorizacaoException ,EstadoInvalidoException {

        if (this.getClass().getSimpleName().equals("Comprador")) throw new SemAutorizacaoException("Permissão Negada");
        // Adicionar imóvel a if (
        for (int i = 0; i < imoveisGeral.size(); i++) {
            if (imoveisGeral.get(i).getId().equals(idImovel)){//switch de strings dá erro
                if (estado.toLowerCase().equals("venda")) {
                    Estado v = Estado.VENDA;
                    imoveisGeral.get(i).setEstadoAux(v);
                }
                else 
                    if (estado.toLowerCase().equals("aluguer")) {
                        Estado a = Estado.ALUGUER;
                        imoveisGeral.get(i).setEstadoAux(a);
                    }
                else 
                    if (estado.toLowerCase().equals("trespasse")) {
                        Estado t = Estado.TRESPASSE;
                        imoveisGeral.get(i).setEstadoAux(t);
                    }
                else
                    if (estado.toLowerCase().equals("ocupado")) {
                        Estado o = Estado.OCUPADO;
                        imoveisGeral.get(i).setEstadoAux(o);
                    }
                else throw new EstadoInvalidoException("Estado inválido.");
                
            }
            else if (i == imoveisGeral.size()-1) throw new ImovelInexistenteException ("O imóvel não existe.");
        }
         
    }
    
    public void registaImovel(Imovel im) throws ImovelExisteException, SemAutorizacaoException {
        
        if (this.getClass().getSimpleName().equals("Comprador")) throw new SemAutorizacaoException("Permissão Negada");
        
        for(int i = 0; i < imoveisGeral.size(); i++) {   
            if (im.getId().equals(imoveisGeral.get(i).getId())) throw new ImovelExisteException("ID já existe.");
        }
        // Adicionar aos imóveis a vender do Vendedor
        for (int j = 0; j< users.size(); j++) {
            if (users.get(j).getMail().equals(ref1)) users.get(j).registaPVenda(im);
        }
        imoveisGeral.add(im);     
    }
    public void MenuSetFavorito() {
        Scanner input = new Scanner(System.in);
        String temp;
        System.out.println("ID do imóvel que procura: ");
        temp = input.next();
        
        try {
            setFavorito(temp);
        } catch (ImovelInexistenteException e) {
            System.out.println("Erro: Imóvel Inexistente");
        } catch (SemAutorizacaoException e) {
            System.out.println("Erro: Sem Autorização");
        }
    }
        
    public void setFavorito(String idImovel) throws ImovelInexistenteException, SemAutorizacaoException {
        
        if (this.getClass().getSimpleName().equals("Vendedor")) throw new SemAutorizacaoException("Permissão Negada");
        
        for( int i = 0; i<imoveisGeral.size(); i++ ) {
            if (idImovel.equals(imoveisGeral.get(i).getId())) {
                for(int j = 0; j<users.size(); j++) {
                    if (ref1.equals(users.get(j).getMail()) && ref2.equals(users.get(j).getPass())) {
                        users.get(j).adicionaFavorito(imoveisGeral.get(i).clone());
                        System.out.println("Imóvel adicionado com sucesso!");
                    }
                }
            }
            else if (i == imoveisGeral.size()-1) {
                throw new ImovelInexistenteException("Imóvel Inexistente");
            }
        }
        
    }
    
    public Set<String> getTopImoveis(int n) {
        
        Set<String> topIm = new HashSet<String>();
        for (Imovel o : imoveisGeral){
            if (o.getView()>=n) topIm.add(o.getId());
        }
        
        return topIm;
    }
    public void MenuHabitaveis(){
        Scanner input = new Scanner(System.in);
        int p;
        System.out.println("Até que preço quer procurar?");
        p = input.nextInt();
        
        List<Habitavel> x = getHabitaveis(p);
        
        
    }
    public List<Habitavel> getHabitaveis(int preco) {
        List<Habitavel> x = new ArrayList<Habitavel>();
        
        for(Imovel o: imoveisGeral) {
            if(o instanceof Habitavel && o.getPreco() <= preco) {
                Habitavel z = (Habitavel) o;
                x.add(z);
            }
        }
        
        return x;
    }
    public void MenuMapeamento () {
        Map<Imovel,Vendedor> x = getMapeamentoImoveis();
        
        Set<Map.Entry<Imovel,Vendedor>> hashSet = x.entrySet();
        for (Map.Entry o: hashSet){
           // System.out.println(o.getKey().getId() + " - " + o.getValue().getNome());
        }
    }
    public Map<Imovel,Vendedor> getMapeamentoImoveis() {
        Map<Imovel,Vendedor> x = new HashMap<Imovel,Vendedor>();
        
        for(Utilizador u: users){
            if(u.getClass().getSimpleName().equals("Vendedor")) {
                Vendedor ven = (Vendedor) u;
                List<Imovel> temp = ven.getPVenda();
                
                for(Imovel i: temp) {
                    x.put(i.clone(),ven.clone());
                }
            }
        }
        
        return x;
    }
    public void MenuListaGeral() {
        Scanner input = new Scanner(System.in);
        int s;
        showImoveis();
        
        System.out.println("Imóvel a consultar? (0-Sair)");
        s = input.nextInt();
        
        while (s > imoveisGeral.size()-1) {
            System.out.println("Escolha não reconhecida");
            s = input.nextInt();
        }
        
        s=s-1; //Ordenação do apontador
        
        imoveisGeral.get(s).showImovel();
        imoveisGeral.get(s).consulta(ref1);
    }
        
}

