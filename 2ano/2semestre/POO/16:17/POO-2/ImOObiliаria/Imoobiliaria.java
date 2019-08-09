import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.joining;
import java.lang.String;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

public class Imoobiliaria implements Serializable
{
   private String nome;
   private Map<String,Imovel> imoveis;
   private Map<String,Utilizador> compradores;
   private Map<String,Utilizador> vendedores;
   private Utilizador uti;
   private Map<String,Imovel> habitaveis;
   private List<Consulta> consultas;
   
   public Imoobiliaria()
   {
       this.nome = "";
       this.imoveis = new HashMap<String,Imovel>();
       this.compradores = new HashMap<String,Utilizador>();
       this.vendedores = new HashMap<String,Utilizador>();
       this.habitaveis = new HashMap<String,Imovel>();
       this.consultas = new ArrayList<Consulta>();
   }
   
   public Imoobiliaria(String nome)
   {
       this.nome = nome;
       this.imoveis = new HashMap<String,Imovel>();
       this.compradores = new HashMap<String,Utilizador>();
       this.vendedores = new HashMap<String,Utilizador>();
       this.habitaveis = new HashMap<String,Imovel>();
       this.consultas = new ArrayList<Consulta>();
   }
   
   public Imoobiliaria(String nome, Map<String,Imovel> imo, Map<String,Utilizador> compradores, Map<String,Utilizador> vendedores, Map<String,Imovel>habitaveis, List<Consulta> consultas)
   {
       this.nome = nome;
       this.imoveis = imo.values()
                         .stream()
                         .collect(toMap(Imovel::getId, Imovel::clone));
       this.compradores = compradores.values()
                                     .stream()
                                     .collect(toMap(Utilizador::getEmail, Utilizador::clone));
       this.vendedores = vendedores.values()
                                   .stream()
                                   .collect(toMap(Utilizador::getEmail, Utilizador::clone));
       this.habitaveis = habitaveis.values()
                                   .stream()
                                   .collect(toMap(Imovel::getId, Imovel::clone));
       setConsultas(consultas);
   }
   
   public Imoobiliaria(Imoobiliaria im2)
   {
       this(im2.getNome(),im2.getImoveis(),im2.getCompradores(),im2.getVendedores(),im2.getHabitaveis(),im2.getAllConsultas());
   }
   
   public String getNome()
   {
       return this.nome;
   }
   
   public Map<String,Imovel> getImoveis()
   {
       HashMap<String,Imovel> aux = new HashMap<String,Imovel>();
       for(Imovel i: this.imoveis.values())
           aux.put(i.getId(),i.clone());
       return aux;
   }
   
   public Map<String,Utilizador> getVendedores()
   {
       HashMap<String,Utilizador> aux = new HashMap<String,Utilizador>();
       for(Utilizador v: this.vendedores.values())
           aux.put(v.getNome(),v.clone());
       return aux;
   }
   
   public Map<String,Utilizador> getCompradores()
   {
       HashMap<String,Utilizador> aux = new HashMap<String,Utilizador>();
       for(Utilizador c: this.compradores.values())
           aux.put(c.getNome(),c.clone());
       return aux;
   }
   
   public Utilizador getUtilizador()
   {
       return this.uti;
   }
   
   public Map<String,Imovel> getHabitaveis()
   {
       HashMap<String,Imovel> aux = new HashMap<String,Imovel>();
       for(Imovel h: this.habitaveis.values())
           aux.put(h.getId(),h.clone());
       return aux;
   }
   
   public List<Consulta> getAllConsultas()
   {
       return this.consultas.stream()
                            .map(Consulta::clone)
                            .collect(Collectors.toList());
   }
   
   public void setConsultas(List<Consulta> c)
    {
        this.consultas.clear();
        this.consultas = c.stream()
                          .map(Consulta::clone)
                          .collect(Collectors.toList());
    }
   
   public boolean equals(Object obj)
   {
        if(this == obj) return true;
        if((obj == null) || (this.getClass() != obj.getClass()))
            return false;
        Imoobiliaria im = (Imoobiliaria) obj;
        return (this.nome.equals(im.getNome()) &&
                this.imoveis.equals(im.getImoveis()) &&
                this.compradores.equals(im.getCompradores()) &&
                this.vendedores.equals(im.getVendedores()) &&
                this.uti.equals(im.getUtilizador()) &&
                this.habitaveis.equals(im.getHabitaveis()));
   }
   
   public Imoobiliaria clone()
   {
        return new Imoobiliaria(this);
   }
   
   public void registarUtilizador(Utilizador utilizador) throws UtilizadorExistenteException
   {
       if(this.compradores!=null)
       {
           if(this.compradores.containsKey(utilizador.getEmail()))
                throw new UtilizadorExistenteException("Utilizador já existe");
           if(this.vendedores.containsKey(utilizador.getEmail()))
                throw new UtilizadorExistenteException("Utilizador já existe");
           else
           {
               if(utilizador instanceof Vendedor) this.vendedores.put(utilizador.getEmail(),utilizador.clone());
               else this.compradores.put(utilizador.getEmail(),utilizador.clone());
           }
       }
       else
       {
           if(utilizador instanceof Vendedor) this.vendedores.put(utilizador.getEmail(),utilizador.clone());
           else this.compradores.put(utilizador.getEmail(),utilizador.clone());
       }
   }
   
   public void iniciaSessao(String email , String password) throws SemAutorizacaoException
   {
       if(this.vendedores.containsKey(email))
       {
           Utilizador u = this.vendedores.get(email);
           if(!u.getPassword().equals(password))
           {
               throw new SemAutorizacaoException("Password errada!");
           }
           else
           {
               uti = new Vendedor();
               uti = u.clone();
           }
       }
       else if(this.compradores.containsKey(email))
       {
           Utilizador u = this.compradores.get(email);
           if(!u.getPassword().equals(password))
           {
               throw new SemAutorizacaoException("Password errada!");
           }
           else
           {
               uti = new Comprador();
               uti = u.clone();
           }
       }
       else
       {
           throw new SemAutorizacaoException("Não está registado!");
       }
   }
   
   public void fechaSessao()
   {
       this.uti = null;
   }
   
   public void registaImovel (Imovel im) throws ImovelExisteException ,SemAutorizacaoException
   {
       if(this.imoveis!=null)
       {
           if(this.imoveis.containsKey(im.getId())) throw new ImovelExisteException("Imovel já existe!");
           else 
           {
               this.imoveis.put(im.getId(),im.clone());
               ((Vendedor) this.vendedores.get(uti.getEmail())).getAvenda().put(im.getId(),im.clone());
               if(im instanceof Habitavel) addHabitavel(im.clone());
           }
       }
       else
       {
           this.imoveis.put(im.getId(),im.clone());
           ((Vendedor) this.vendedores.get(uti.getEmail())).getAvenda().put(im.getId(),im.clone());
           if(im instanceof Habitavel) addHabitavel(im.clone());
       }
   }
   
   public void addConsultas(Imovel im, String email)
   {
       Consulta con = new Consulta(email,im.clone());
       this.consultas.add(con.clone());
   }
   
   public List<Consulta> getConsultas10() throws SemAutorizacaoException
   {
       if(!this.vendedores.containsKey(this.uti.getEmail())) throw new SemAutorizacaoException("Não esta registado");
       else
       {
           ArrayList lista = new ArrayList();
           ArrayList consultas1 = new ArrayList();
           consultas1 = (ArrayList)this.consultas;
           Collections.reverse(consultas1);
           if(consultas1.size()>10)
           {
               for(int i=0;i<10;i++)
               {
                   if(consultas1.get(i)!=null) lista.add(consultas1.get(i));
               }
           }
           else
           {
               lista=consultas1;
           }
           return lista;
       }
   }
   
   public Set<String> getTopImoveis(int n)
   {
        TreeSet<String> res = new TreeSet<String>();
        for(int i=0;i<n;i++)
        {
            
        }
        return res;
   }
   
   public void setEstado(String idImovel, String estado) throws ImovelInexistenteException, SemAutorizacaoException, EstadoInvalidoException
   {
       if(this.imoveis.containsKey(idImovel))
       {
           if(uti instanceof Comprador) throw new SemAutorizacaoException("Não esta autorizado!");
           if(!this.vendedores.containsKey(this.uti.getEmail())) throw new SemAutorizacaoException("Não esta registado");
           else
           {
               if(estado.equals("Vendido") || estado.equals("À Venda"))
               {
                   if(estado.equals("Vendido")) 
                   {
                       this.imoveis.get(idImovel).setEstado('V');
                       ((Vendedor) this.vendedores.get(uti.getEmail())).getVendidos().put(idImovel,this.imoveis.get(idImovel).clone());
                       ((Vendedor) this.vendedores.get(uti.getEmail())).getAvenda().remove(this.imoveis.get(idImovel));
                       this.habitaveis.get(idImovel).setEstado('V');
                   }
                   if(estado.equals("À Venda"))
                   {
                       this.imoveis.get(idImovel).setEstado('A');
                       ((Vendedor) this.vendedores.get(uti.getEmail())).getAvenda().put(idImovel,this.imoveis.get(idImovel).clone());
                       ((Vendedor) this.vendedores.get(uti.getEmail())).getVendidos().remove(this.imoveis.get(idImovel));
                       this.habitaveis.get(idImovel).setEstado('A');
                   }
               }
               else 
               {
                   throw new EstadoInvalidoException("Estado Invalido!");
               }
           }
       }
       else
       {
           throw new ImovelInexistenteException("Este imovel não está registado!");
       }
   }
   
   public boolean tipoImovel(String s, Imovel i)
   {
       if(s.equals("Terreno") && i.getClass().equals(Terreno.class)) return true;
       else if(s.equals("Loja") && i.getClass().equals(Loja.class)) return true;
       else if(s.equals("Moradia") && i.getClass().equals(Moradia.class)) return true;
       else if(s.equals("Apartamento") && i.getClass().equals(Moradia.class)) return true;
       else return false;
   }
   
   public List<Imovel> getImovel(String classe, int preco)
   {
       ArrayList lista = new ArrayList();
       for(Map.Entry<String,Imovel> im : imoveis.entrySet())
       {
           if(this.tipoImovel(classe,im.getValue()))
           {
               if(im.getValue().getPreco()<=preco)
               {
                   lista.add(im.getValue().clone());
                   if(uti instanceof Comprador) 
                   {
                       addConsultas(im.getValue().clone(),uti.getEmail());
                       this.consultas.add(new Consulta(uti.getEmail(),im.getValue().clone()));
                   }
               }
           }
       }
       return lista;
   }
   
   public void addHabitavel(Imovel i)
   {
       this.habitaveis.put(i.getId(),i.clone());
   }
   
   public List <Habitavel> getHabitaveis(int preco)
   {
       ArrayList lista = new ArrayList<>();
       for(Map.Entry<String,Imovel> h : habitaveis.entrySet())
       {
           lista.add(h.getValue().clone());
           if(uti instanceof Comprador) addConsultas(h.getValue().clone(),uti.getEmail());
           this.consultas.add(new Consulta(uti.getEmail(),h.getValue().clone()));
       }
       return lista;
   }
   
   public Map<Imovel,Vendedor> getMapeamentoImoveis()
   {
       HashMap<Imovel,Vendedor> res = new HashMap<Imovel,Vendedor>();
       for(Map.Entry<String,Utilizador> v : vendedores.entrySet())
       {
           Vendedor v1 = new Vendedor();
           v1=(Vendedor)v.getValue();
           for(Map.Entry<String,Imovel> im : v1.getAvenda().entrySet())
           {
               res.put(im.getValue().clone(),v1.clone());
               if(uti instanceof Comprador)
               {
                   addConsultas(im.getValue().clone(),uti.getEmail());
                   this.consultas.add(new Consulta(uti.getEmail(),im.getValue().clone()));
               }
           }
       }
       return res;
   }
   
   public void setFavorito(String idImovel) throws ImovelInexistenteException, SemAutorizacaoException
   {
       if(this.imoveis.containsKey(idImovel))
       {
           if(uti.getClass().equals(Vendedor.class)) {throw new SemAutorizacaoException("Não esta autorizado!");}
           if(!this.compradores.containsKey(this.uti.getEmail())) {throw new SemAutorizacaoException("Não esta registado");}
           else
           {
               ((Comprador) this.compradores.get(uti.getEmail())).getFavoritos().put(idImovel,this.imoveis.get(idImovel).clone());
           }
       }
       else throw new ImovelInexistenteException("Este imovel não está registado!");
   }
   
   public List<Imovel> getFavoritos() throws SemAutorizacaoException
   {
       if(uti.getClass().equals(Vendedor.class)) throw new SemAutorizacaoException("Não esta autorizado!");
       if(!this.compradores.containsKey(this.uti.getEmail())) throw new SemAutorizacaoException("Não esta registado");
       else
       {
           ArrayList<Imovel> res = new ArrayList<Imovel>();
           Map<String,Imovel> aux = new HashMap<String,Imovel>();
           Comprador c = new Comprador();
           c = (Comprador)this.compradores.get(uti.getEmail());
           aux=c.getFavoritos();
           for(Map.Entry<String,Imovel> im : aux.entrySet())
           {
               res.add(im.getValue().clone());
               addConsultas(im.getValue().clone(),uti.getEmail());
               this.consultas.add(new Consulta(uti.getEmail(),im.getValue().clone()));
           }
           return res;
       }
   }
   
   public void gravaObj(String fich) throws IOException 
   {
       ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
       oos.writeObject(this);    
       oos.flush();
       oos.close();
   }
   
   public static Imoobiliaria leObj(String fich) throws IOException, ClassNotFoundException
   {
       ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));    
       Imoobiliaria im = (Imoobiliaria) ois.readObject();
       ois.close();
       return im;
   }
}
