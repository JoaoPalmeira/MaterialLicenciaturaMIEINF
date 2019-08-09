import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

public class Imoobiliaria implements Serializable{
    /*Imovel |-| Email Vendedor*/
    private Map<Imovel,String> imoveis;
    /*Email Utilizador  |-|  Utilizador*/
    private Map<String, Utilizador> utilizadores;
    
    /***/
    private Utilizador user;
    /*0->sessão fechada 1->vendedor 2->comprador*/
    private int sessao;
    /***/
    public Imoobiliaria () {
        this.imoveis = new TreeMap<Imovel,String>(new ComparatorImovelId());
        this.utilizadores = new TreeMap<String,Utilizador>();
    }
    public Imoobiliaria (Map<Imovel,String> imoveis, Map<String, Utilizador> utilizadores) {
        this.imoveis=imoveis;
        this.utilizadores=utilizadores;
    }
    public Imoobiliaria (Imoobiliaria c) {
        this.imoveis = c.getImoveis();
        this.utilizadores = c.getUtilizadores();
    }
    public Map<Imovel,String> getImoveis() {return imoveis;}
    public Map<String, Utilizador> getUtilizadores() {return utilizadores;}
    public void setImoveis(Map<Imovel,String> imoveis) {this.imoveis = imoveis;}
    public void setUtilizadores(Map<String, Utilizador> utilizadores) {this.utilizadores=utilizadores;}
  
    
    
    public static Imoobiliaria initApp (){
        Imoobiliaria imo1 = new Imoobiliaria();
        
        Vendedor v1 = new Vendedor("alexandreteixeira@imoobiliaria.um","Alexandre Teixeira","123456","Rua do DI, DI - UMinho",new Date(1995,06,21),null,null);
        try {imo1.registarUtilizador(v1);} catch (UtilizadorExistenteException e) {System.out.println("Utilizador Existente");}
        try {imo1.iniciaSessao("alexandreteixeira@imoobiliaria.um","123456");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        Apartamento apart1 = new Apartamento("Rua de cima",50000,42000,"venda","001","Triplex",100,5,3,1,3,true);
        Loja loj1 = new Loja ("Rua de baixo",20000,17000,"reservado","002",100,true,"Mercearia",2);
        try {imo1.registaImovel(apart1);} catch (ImovelExisteException e) {System.out.println("Imovel Existente");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        try {imo1.registaImovel(loj1);} catch (ImovelExisteException e) {System.out.println("Imovel Existente");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        imo1.fechaSessao();

        Vendedor v2 = new Vendedor("brunosousa@imoobiliaria.um","Bruno Sousa","123456","Rua do DI, DI - UMinho",new Date(1996,06,24),null,null);
        try {imo1.registarUtilizador(v2);} catch (UtilizadorExistenteException e) {System.out.println("Utilizador Existente");}
        try {imo1.iniciaSessao("brunosousa@imoobiliaria.um","123456");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        Moradia mora1 = new Moradia("Rua de meio",70000,60000,"venda","003","germinada",200,400,200,7,8,17);
        Terreno terr1 = new Terreno ("Rua de campo",5000,4900,"reservado","004",50,"Armazem",5,30,true);
        try {imo1.registaImovel(mora1);} catch (ImovelExisteException e) {System.out.println("Imovel Existente");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        try {imo1.registaImovel(terr1);} catch (ImovelExisteException e) {System.out.println("Imovel Existente");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        try {imo1.setEstado("004","vendido");} catch (ImovelInexistenteException e) {System.out.println("Imovel Inexistente");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");} catch (EstadoInvalidoException e){System.out.println("Estado Invalido");}
        imo1.fechaSessao();
        
        Comprador c1 = new Comprador("rafasilva@imoobiliaria.um","Rafael Silva","123456","Rua do DI, DI - UMinho",new Date(1996,01,01),null);
        try {imo1.registarUtilizador(c1);} catch (UtilizadorExistenteException e) {System.out.println("Utilizador Existente");}
        try {imo1.iniciaSessao("rafasilva@imoobiliaria.um","123456");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        try {imo1.setFavorito("004");} catch (ImovelInexistenteException e) {System.out.println("Imovel Inexistente");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        try {imo1.setFavorito("001");} catch (ImovelInexistenteException e) {System.out.println("Imovel Inexistente");} catch (SemAutorizacaoException e) {System.out.println("Sem Autorizacao");}
        imo1.fechaSessao();
        
        return imo1;
    }
    public void iniciaSessao ( String email , String password ) throws SemAutorizacaoException {
        for (Utilizador l : utilizadores.values())
            if (l.getEmail().equals(email) && l.getPassword().equals(password)){
                this.user=l;
                if (l.getClass().getSimpleName().equals("Vendedor")) this.sessao=1;
                else this.sessao=2;
                return;
            }
        throw new SemAutorizacaoException();
    }
    public void registarUtilizador (Utilizador utilizador) throws UtilizadorExistenteException {
        for (Utilizador l : utilizadores.values())
            if (l.getEmail().equals(utilizador.getEmail()) && l.getPassword().equals(utilizador.getPassword())) throw new UtilizadorExistenteException();
        this.utilizadores.put(utilizador.getEmail(),utilizador);
    }
    public void fechaSessao () {
        this.sessao=0;
        this.user=null;
    }
    public void registaImovel (Imovel im) throws ImovelExisteException, SemAutorizacaoException {
        if (this.sessao==2 || this.sessao==0) throw new SemAutorizacaoException();
        Iterator<Imovel> it = this.imoveis.keySet().iterator();
        while (it.hasNext()) {
            Imovel aux=it.next();
            if (im.equals(aux)) throw new ImovelExisteException();
        }
        
        this.imoveis.put(im,this.user.getEmail());
        
        Vendedor vAux = (Vendedor) this.user;
        vAux.addImovel(im);
    }
    public List<Consulta> getConsultas() throws SemAutorizacaoException {
        int i,x;
        List<Consulta> aux1 = new ArrayList<Consulta>();
        List<Consulta> aux2 = new ArrayList<Consulta>();
        if (this.sessao==2 || this.sessao==0) throw new SemAutorizacaoException();
        else{
            Vendedor y = (Vendedor) user;
            aux1 = y.getConsultas();
        }
        for(i=0,x=aux1.size()-1;i<10 && x>=0;i++,x--){
            aux2.add(aux1.get(x));
        }
        return aux2;
    }
    public void setEstado (String idImovel, String estado) throws ImovelInexistenteException, SemAutorizacaoException, EstadoInvalidoException {
        if (this.sessao==0 || this.sessao==2) throw new SemAutorizacaoException();
        if (!estado.equals("venda") && !estado.equals("reservado") && !estado.equals("vendido")) throw new EstadoInvalidoException();
        for (Imovel l: imoveis.keySet()){
            if (l.getId().equals(idImovel)) {
                l.setEstado(estado);
                Utilizador ut = utilizadores.get(imoveis.get(l));
                Vendedor v = (Vendedor) ut;
                v.setEstadoImovel(idImovel,estado);
                return;
            }
        }
        throw new ImovelInexistenteException();
    }
    public Set<String> getTopImoveis (int n){
        if (this.sessao==1) {
            Vendedor vAux = (Vendedor) this.user;
            List<Consulta> aux = vAux.getConsultas();
            Set<String> ret = new HashSet<String>();
            for(Consulta c: aux) {
                if (c.getNCons()>n) ret.add(c.getIdImo());
            }
            return ret;
        }
        return null;
    }
    public List<Imovel> getImovel (String classe, int preco){
        List<Imovel> ret = new ArrayList<Imovel>();
        Iterator<String> itEmail = this.imoveis.values().iterator();
        for (Imovel im : imoveis.keySet()) {
            String email = itEmail.next();
            if ((im.getClass().getSimpleName().equals(classe)) && im.getPrecoPed()<preco) {
                ret.add(im.clone());
                for (Utilizador v : utilizadores.values()) {
                    if (email.equals(v.getEmail())) {
                        Vendedor y = (Vendedor) v;
                        y.addConsulta(im.getId());
                        v=y;
                        break;
                    }
                }
            }
        }
        return ret;
    }
    public List<Habitavel> getHabitaveis (int preco) {
        List<Habitavel> ret = new ArrayList<Habitavel>();
        for (Imovel im : this.imoveis.keySet())
            if ((im instanceof Apartamento || im instanceof Moradia) && im.getPrecoPed()<preco){
                Habitavel hb = (Habitavel) im;
                ret.add(hb);
            }
        return ret;
    }
    public Map<Imovel,Vendedor> getMapeamentoImoveis (){
        Map<Imovel,Vendedor> ret = new TreeMap<Imovel,Vendedor>(new ComparatorImovelId());
        for(Utilizador ut : utilizadores.values()){
            if (ut instanceof Vendedor){
                Vendedor v = (Vendedor) ut;
                List<Imovel> lImo = v.getImoveis();
                for(Imovel i : lImo) ret.put(i.clone(),v.clone());
            }
        }
        return ret;
    }
    public void setFavorito (String idImovel) throws ImovelInexistenteException, SemAutorizacaoException{
        if (this.sessao==0 || this.sessao==1) throw new SemAutorizacaoException();
        Imovel imAux = null;
        for (Imovel i : this.imoveis.keySet()){
            if (i.getId().equals(idImovel)) {
                imAux=i.clone();
                break;
            }
        }
        if (imAux == null) throw new ImovelInexistenteException();
        
        Comprador cAux = (Comprador) this.user;
        cAux.addFavorito(imAux.clone());
    }
    public TreeSet<Imovel> getFavoritos() throws SemAutorizacaoException {
        if (this.sessao==0 || this.sessao==1) throw new SemAutorizacaoException();
        TreeSet<Imovel> ret = new TreeSet<Imovel>(new ComparatorPreco());
        Comprador c = (Comprador) this.user;
        List<Imovel> aux = c.getImoveisFavoritos();
        for (Imovel im : aux)
            ret.add(im.clone());
        return ret;
    }
    public void saveState (String nomeFicheiro) throws IOException{
        FileOutputStream file = new FileOutputStream(nomeFicheiro+".imoobiliaria");
        ObjectOutputStream oss = new ObjectOutputStream(file);
        oss.writeObject(this);
        oss.flush();
        oss.close();
    }
    public static Imoobiliaria loadState(String nomeFicheiro) throws IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream(nomeFicheiro+".imoobiliaria");
        ObjectInputStream ois = new ObjectInputStream(file);
        Imoobiliaria imo = new Imoobiliaria();
        imo = (Imoobiliaria) ois.readObject();
        ois.close();
        return imo;
    }
}