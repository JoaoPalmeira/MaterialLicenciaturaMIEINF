import java.lang.String;
import java.lang.Boolean;
import java.util.ArrayList;
import static java.lang.System.out;
import java.util.*;
import java.io.*;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.joining;
/**
 * Escreva a descrição da classe Imobiliaria aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Imoobiliaria implements Serializable
{
   // variáveis de instância
   private List<Utilizador> utilizadores;
   private List<Imovel> imoveis;
   private boolean veSessao;
   private String tipoSessao;
   private Utilizador utilizador = new Utilizador();
   private Vendedor vendedor = new Vendedor();
   private Comprador comprador = new Comprador(); 
   private List<Vendedor> lVendedor = new ArrayList<Vendedor>();
   
   public Imoobiliaria(){
       utilizadores = new ArrayList<Utilizador>();
       imoveis = new ArrayList<Imovel>();
       veSessao = false;
       tipoSessao = "";
   }
   
   public Imoobiliaria (List<Utilizador> util, List<Imovel> im, boolean veSe, String tipoSe){
       utilizadores = new ArrayList<Utilizador>();
       for(Utilizador u : util){
           utilizadores.add(u.clone());
       }
       imoveis = new ArrayList<Imovel>();
       for(Imovel i : im) {
             imoveis.add(i);
       }
       veSessao = veSe;
       tipoSessao = tipoSe;
   }
   
   public Imoobiliaria (List<Utilizador> utiliza, List<Imovel> imov){
       utilizadores = new ArrayList<Utilizador>();
       for(Utilizador u : utiliza) utilizadores.add(u.clone());
       imoveis = new ArrayList<Imovel>();
       for(Imovel i : imov) imoveis.add(i.clone());
   }
   
   public Imoobiliaria (Imoobiliaria imobiliaria){
       utilizadores = new ArrayList<Utilizador>();
       for(Utilizador u : imobiliaria.comoArrayListU())
           utilizadores.add(u.clone());
       imoveis = new ArrayList<Imovel>();
       for(Imovel i : imobiliaria.comoArrayListI())
           imoveis.add(i.clone());
       veSessao = imobiliaria.getVeSessao();
       tipoSessao = imobiliaria.getTipoSessao();
   }
   
   public List<Utilizador> getUtilizadores(){
       return utilizadores;
   }
   
   public List<Imovel> getImoveis(){
       return imoveis;
   }
   
   public boolean getVeSessao(){
       return veSessao;
   }
   
   public String getTipoSessao(){
       return tipoSessao;
   }
   
   public void setUtilizadores(List<Utilizador> u){
       utilizadores = u;
   }
   
   public void setImoveis(List<Imovel> i){
       imoveis = i;
   }
   
   public void setVeSessao(boolean v){
       veSessao = v;
   }
   
   public void setTipoSessao(String t){
       tipoSessao = t;
   }
   
   /**
    * Devolve um ArrayList com todos os utilizadores copiados
    */
   public ArrayList<Utilizador> comoArrayListU(){
        ArrayList<Utilizador> res = new ArrayList<Utilizador>();
        for(Utilizador u : utilizadores) res.add(u.clone());
        return res;
   }
   
   /**
    * Devolve um ArrayList com todos os imóveis copiados
    */
   public ArrayList<Imovel> comoArrayListI(){
        ArrayList<Imovel> res = new ArrayList<Imovel>();
        for(Imovel i : imoveis) res.add(i.clone());
        return res;
   }
   
    /*public static Imoobiliaria initApp(){
    }
    */
    
   /**
    * Registar um utilizador, quer vendedor, quer comprador
    */
   public void registarUtilizador (Utilizador utilizador) throws UtilizadorExistenteException{
        if (utilizadores.contains(utilizador)) throw new UtilizadorExistenteException("Utilizador já existe");
        else {
            utilizadores.add(utilizador);
            out.println("Inscrito com sucesso");
        }
   }
   
   /**
    * Validar o acesso à aplicação utilizando as credenciais (email e password) - inciar sessão
    */
   public void iniciaSessao (String email, String password) throws SemAutorizacaoException{
       for(Utilizador u : utilizadores){
            if(u.getEmail().equals(email)){
                if(u.getPassword().equals(password)){
                    veSessao = true;
                    out.println("Bem vindo à ImOObiliária, "+u.getNome());
                    tipoSessao = u.getTipoUtilizador();
                }
                else {
                    veSessao = false; 
                    throw new SemAutorizacaoException("Password errada");
                }
            }
            else {
                veSessao = false; 
                throw new SemAutorizacaoException("Email errado ou inexistente");
            }
       }
   }
   
   /**
    * Terminar sessão
    */
   public void fechaSessao(){
       if (veSessao == true){
          veSessao = false;
          out.println("Até à próxima");
       }
   }
   
   /**
    * Colocar um imóvel à venda (tem de ser vendedor autenticado)
    */
   public void registaImovel (Imovel im) throws ImovelExisteException, SemAutorizacaoException{
        
        if (veSessao == true){
          if(tipoSessao.equals("Vendedor")){
            if (vendedor.getParaVenda().contains(im)) throw new ImovelExisteException("Imóvel já está à venda");
            else vendedor.getParaVenda().add(im);
          }
          else throw new SemAutorizacaoException("Não é vendedor");
        }
        else throw new SemAutorizacaoException("Não tem sessão iniciada");
   }
    
   /**
    * Alterar o estado de um imóvel, de acordo com as acções feitas sobre ele
    * (tem de ser vendedor autenticado)
    */
   public void setEstado (String idImovel, String estado) throws ImovelInexistenteException, SemAutorizacaoException, EstadoInvalidoException{
        
        int existe = 0;
        if (veSessao == true){
          if(tipoSessao.equals("Vendedor")){
           for(Imovel i : imoveis){
             if (i.getCodigo().equals(idImovel)){
                if (i.getEstado().equals(estado)) throw new EstadoInvalidoException("Estados iguais");
                else if (estado.equals("Em Venda")) i.setEstado("Em Venda");
                     else if (estado.equals("Vendido")) i.setEstado("Vendido");
                          else throw new EstadoInvalidoException("Estado inexistente");
                existe = 1;
             }
           }
           if (existe == 0) throw new ImovelInexistenteException("Imóvel não existe");
          }
          else throw new SemAutorizacaoException("Não é vendedor");
        }
        else throw new SemAutorizacaoException("Sem sessão inciciada");
   }
   
   /**
    * Obter um conjunto com os códigos dos imóveis do vendedor
    * com mais de n consultas (tem de ser vendedor autenticado)
    */
   public Set<String> getTopImoveis(int n){
       Set<String> topImoveis = new HashSet<String>();
       if (getVeSessao() == true){
           if (tipoSessao.equals("Vendedor")){
               for(Imovel i: imoveis)
                   if (i.getVisualizacoes() > n)topImoveis.add(i.getCodigo());
          }
        }
       return topImoveis;
   }
   
   /**
    * Consultar a lista de todos os imóveis de um dado tipo e até um certo preço
    */
   public List<Imovel> getImovel(String classe, int preco){
       
       List<Imovel> lImoveis = new ArrayList<Imovel>();
       for (Imovel i : imoveis){
           if (i.getTipoImovel().equals(classe) && i.getPrecoPedido() <= (double)preco) lImoveis.add(i.clone()); 
       }
       return lImoveis;
   }
   
   /**
    * Consultar a lista de todos os imóveis habitáveis e até um certo preço
    */
   public List <Habitavel> getHabitaveis (int preco){
       List<Habitavel> habitaveis = new ArrayList<Habitavel>();
       for (Imovel i : imoveis){
           if (i.getTipoImovel().equals("Moradia") || i.getTipoImovel().equals("Apartamento") || i.getTipoImovel().equals("LojaHabitavel")){
               if (i.getPrecoPedido() <= (double)preco) habitaveis.add((Habitavel)i.clone());
           }
       }
       return habitaveis;
   }
   
   /**
    * Obter um mapeamento entre todos os imóveis e respectivos vendedores
    */
   public Map<Imovel, Vendedor> getMapeamentoImoveis(){
       Map<Imovel, Vendedor> mImoveis = new HashMap<Imovel, Vendedor>();
       
       for(Imovel i : imoveis){
          for(Vendedor v : lVendedor){
              if(v.getEmail().equals(i.getMail())){
                    mImoveis.put(i.clone(),v.clone());
              }
          }
       }
       return mImoveis;
   }
   
   /**
    * Marcar um imóvel como favorito (tem de ser comprador autenticado)
    */
   public void setFavorito (String idImovel) throws ImovelInexistenteException, SemAutorizacaoException{
        
        int existe = 0;
        if (veSessao == true){
         if (tipoSessao.equals("Comprador")){
          for(Imovel i : imoveis){
             if (i.getCodigo().equals(idImovel)){
                 comprador.getFavoritos().add(i.clone());
                 existe = 1;
             }
          }
          if (existe == 0) throw new ImovelInexistenteException("Imóvel não existe");
         }
         else throw new SemAutorizacaoException("Não é comprador");
        }
        else throw new SemAutorizacaoException("Sem sessão iniciada");
   }
    
   /**
    * Consultar os imóveis favoritos por ordem crescente de preço,
    * de acordo com a comparação do comparador ComparatorSalario (tem de ser comprador autenticado)
    */
   public TreeSet<Imovel> getFavoritos() throws SemAutorizacaoException {
        
        TreeSet<Imovel> favOrdenados = new TreeSet<Imovel>(new ComparatorPreco());  
        if (getVeSessao() == true){
          if (tipoSessao.equals("Comprador")){
           for(Imovel i: comprador.getFavoritos())
              favOrdenados.add(i.clone());
           return favOrdenados;
          }
          else throw new SemAutorizacaoException("Não é comprador");
        }
        else throw new SemAutorizacaoException("Sem sessão inciciada");
   }  
   
   public String toString() {
        StringBuilder sb = new StringBuilder("--- IMOOBILIÁRIA ---\n");
        for(Utilizador u : this.utilizadores)
            sb.append(u.toString() + "\n");
        for(Imovel i : this.imoveis)
            sb.append(i.toString() + "\n");
        if (veSessao == true) sb.append("Com sessão iniciada\n");
        else sb.append("Sem sessão iniciada\n");
        sb.append("Lista de todos os imóveis: " + imoveis + "\n");
        return sb.toString();
   }

   public boolean equals(Object obj) {
        if(this == obj) return true; 
        if((obj == null) || (this.getClass() != obj.getClass())) 
            return false;
        Imoobiliaria o = (Imoobiliaria) obj;
        return o.getUtilizadores() == utilizadores && o.getImoveis() == imoveis && o.getVeSessao() == veSessao && 
               o.getTipoSessao().equals(tipoSessao);
   }  

   public Imoobiliaria clone() {
        return new Imoobiliaria(this);
   }
   
   public void gravaObj(String fich) throws IOException { 
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich)); 
        oos.writeObject(this); 
        
        oos.flush(); 
        oos.close(); 
    } 

    public static Imoobiliaria leObj(String fich) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
      
        Imoobiliaria te= (Imoobiliaria) ois.readObject();
        
        ois.close();
        return te;
    }

    public void log(String f, boolean ap) throws IOException {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n----------- LOG - LOG - LOG - LOG - LOG ----------------\n");
        fw.write(this.toString());
        fw.write("\n----------- LOG - LOG - LOG - LOG - LOG ----------------\n");
        fw.flush();
        fw.close();
    }
}

