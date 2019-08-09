import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;
import java.lang.Object;
import java.util.Iterator;

/**
 * Escreva a descrição da classe UMeR aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class UMeR implements Serializable
{
    // variáveis de instância
    private Map<String, Utilizador> utilizadores;
    private Map<String, Viatura> viaturas;
    private Map<String, Empresa> empresas;
    private int total; //número total de viagens efetuadas pela empresa

    public UMeR(){
        // inicializa variáveis de instância
        this.utilizadores = new HashMap<>();
        this.viaturas = new HashMap<>();
        this.empresas = new HashMap<>();
        this.total = 0;
    }
    
    public UMeR(UMeR u){
        this.utilizadores = u.getUtilizadores();
        this.viaturas = u.getViaturas();
        this.empresas = u.getEmpresas();
        this.total = u.getTotal();
    }
    
    public UMeR(Map<String,Utilizador> unsUtilizadores, Map<String,Viatura> umasViaturas, Map<String,Empresa> umasEmpresas, int total){
        this.utilizadores = new HashMap<>();
        for(Utilizador u: unsUtilizadores.values()){
            this.utilizadores.put(u.getEmail(),u.clone());
        }
        this.viaturas = new HashMap<>();
        for(Viatura v: umasViaturas.values()){
            this.viaturas.put(v.getMatricula(),v.clone());
        }
        this.empresas = new HashMap<>();
        for(Empresa e: umasEmpresas.values()){
            this.empresas.put(e.getNome(),e.clone());
        }
        this.total = total;
    }
    
    public Map<String,Utilizador> getUtilizadores(){
       Map<String,Utilizador> res = new HashMap<>();
       for(Utilizador u: this.utilizadores.values())
           res.put(u.getEmail(),u.clone());
       return res;
    }
    
    public Map<String,Viatura> getViaturas(){
       Map<String,Viatura> res = new HashMap<>();
       for(Viatura v: this.viaturas.values())
           res.put(v.getMatricula(),v.clone());
       return res;
    }
    
    public Map<String,Empresa> getEmpresas(){
       Map<String,Empresa> res = new HashMap<>();
       for(Empresa e: this.empresas.values())
           res.put(e.getNome(),e.clone());
       return res;
    }
    
    public int getTotal(){
        return this.total;
    }
    
    public void setUtilizadores(Map<String,Utilizador> unsUtilizadores){
       this.utilizadores = new HashMap<>();
       for(Utilizador u: unsUtilizadores.values())
           this.utilizadores.put(u.getEmail(),u.clone());
    }
    
    public void setViaturas(Map<String,Viatura> umasViaturas){
       this.viaturas = new HashMap<>();
       for(Viatura v: umasViaturas.values())
           this.viaturas.put(v.getMatricula(),v.clone());
    }
    
    public void setEmpresas(Map<String,Empresa> umasEmpresas){
       this.empresas = new HashMap<>();
       for(Empresa e: umasEmpresas.values())
           this.empresas.put(e.getNome(),e.clone());
    }
    
    public void setTotal(int total){
        this.total = total;
    }
    
    /**
     * Verificar se um utilizador existe, dado o seu email
     */
    public boolean existeUtilizador(String email){
        return utilizadores.containsKey(email);
    }
    
    /**
     * Encontrar um utilizador, dado o seu email
     */
    public Utilizador getUtilizador(String email) throws UtilizadorInexistenteException {
        Utilizador u;
        if(!utilizadores.containsKey(email)) {
            throw new UtilizadorInexistenteException("Email inválido");
        }
        u = utilizadores.get(email).clone();
        return u;
    }
    
    /**
     * Adicionar uma viatura
     */
    public void adicionaV(Viatura v) throws ViaturaRepetidaException{
        if(viaturas.containsKey(v.getMatricula())){
           throw new ViaturaRepetidaException("Matrícula Repetida");          
        }
        viaturas.put(v.getMatricula(), v.clone());
    }
    
    /**
     * Adicionar um utilizador
     */
    public void adiciona(Utilizador u) throws UtilizadorRepetidoException {
        if(utilizadores.containsKey(u.getEmail())){
           throw new UtilizadorRepetidoException("Email repetido");          
        }
        utilizadores.put(u.getEmail(), u.clone());
    }
    
    /**
     * Criar uma viatura
     */
    public void criarViatura(double velMedKm, double precoBaseKm, double fiabilidade, Espaco2D localizacao, String matricula) throws ViaturaRepetidaException {
        if (viaturas.containsKey(matricula)) throw new ViaturaRepetidaException("Viatura já inserida");
        else {
            Map<Integer, Viagem> viagens = new HashMap<>();
            new Viatura(viagens, velMedKm, precoBaseKm, fiabilidade, localizacao, matricula);
        }
    }  
    
    //tempo que o motorista demora até chegar ao cliente
    public double tempoChegarCliente(Viatura v, Cliente c){
        return (v.calculardistancia(v.getLocalizacao(), c.getLocalizacao())) / v.getVelMedKm();
    }
    
    /**
     * Imprimir viaturas de um dado tipo
     */
    public void imprimeViaturas(int tipo){
        if(tipo==1){
          for(Viatura v: this.viaturas.values()){
            if(v.getClass().getSimpleName().equals("Carrinha")){
              Carrinha c = (Carrinha) v;
              System.out.print("---------------------\nVelocidade: " + c.getVelMedKm() + "\nPreço Base: " + c.getPrecoBaseKm() + "\nFiabilidade: " + c.getFiabilidade() +
                                "\nLocalização: " + c.getLocalizacao() + "Matrícula: " + c.getMatricula() + "\nCapacidade: " + c.getCapacidade() + "\n");
            }
          }
        }
        if(tipo==2){
          for(Viatura v: this.viaturas.values()){
            if(v.getClass().getSimpleName().equals("CarroLigeiro")){
              CarroLigeiro cl = (CarroLigeiro) v;
              System.out.print("---------------------\nVelocidade: " + cl.getVelMedKm() + "\nPreço Base: " + cl.getPrecoBaseKm() + "\nFiabilidade: " + cl.getFiabilidade() +
                                "\nLocalização: " + cl.getLocalizacao() + "Matrícula: " + cl.getMatricula() + "\nCapacidade: " + cl.getCapacidade() + "\n"); 
            }
          }
        }
        if(tipo==3){
          for(Viatura v: this.viaturas.values()){
            if(v.getClass().getSimpleName().equals("Moto")){
              Moto m = (Moto) v;
              System.out.print("---------------------\nVelocidade: " + m.getVelMedKm() + "\nPreço Base: " + m.getPrecoBaseKm() + "\nFiabilidade: " + m.getFiabilidade() +
                                "\nLocalização: " + m.getLocalizacao() + "Matrícula: " + m.getMatricula() + "\nCapacidade: " + m.getCapacidade() + "\n");
            }
          }
        }
    }
    
    /**
     * Imprimir nomes das empresa
     */
    public void imprimeEmpresas(){
        System.out.println("Empresas de táxis disponíveis:");
        for(Empresa e: this.empresas.values()){
            System.out.println(e.getNome());
        }
    }
    
    /**
     * Solicitar uma viagem para o ponto R(x,y), escolhendo uma Carrinha
     */
    public void solicitarViagemC(Espaco2D e, String matricula, String email) throws ViaturaNaoExistenteException,MotoristaNaoDisponivelException{
      if(this.viaturas.containsKey(matricula)){
       for(Utilizador ut: utilizadores.values()){
         if(ut.getClass().getSimpleName().equals("Motorista")){
          Motorista mt = (Motorista) ut;
          if(mt.getDisponivel()==true){
            for(Utilizador u: utilizadores.values()){
                if(email.equals(u.getEmail())){
                    if(u.getClass().getSimpleName().equals("Cliente")){
                       Cliente c = (Cliente) u;
                       for(Viatura v: viaturas.values()){
                           if(v.getMatricula().equals(matricula)){
                               if(v.getClass().getSimpleName().equals("Carrinha")){
                                   Carrinha cr = (Carrinha) v; 
                                   double custo = cr.calculardistancia(e,c.getLocalizacao()) * cr.getPrecoBaseKm();
                                   double distancia = cr.calculardistancia(e, c.getLocalizacao());
                                   double duracao = distancia / cr.getVelMedKm();
                                   LocalDate d = LocalDate.now();
                                   total++;
                                   if (cr.getViagensEmEspera().size()>0) {
                                       cr.adicionarViagemEspera(new Viagem(custo, duracao, distancia, total, e, d));
                                    }
                                    else {
                                        c.adicionaViagem(new Viagem(custo, duracao, distancia, total, e, d));
                                        mt.adicionaViagem(new Viagem(custo, duracao, distancia, total, e, d));
                                        cr.adicionaViagemViat(new Viagem(custo, duracao, distancia, total, e, d));
                                        c.setLocalizacao(e);
                                    } 
                                }
                            }
                        }
                    }
                }
            }
          }else throw new MotoristaNaoDisponivelException("O motorista desta viatura não esta a trabalhar");
         }
       }
      }
      else throw new ViaturaNaoExistenteException("Viatura não existe");
    }
    
    /**
     * Solicitar uma viagem para o ponto R(x,y), escolhendo um CarroLigeiro
     */
    public void solicitarViagemCL(Espaco2D e, String matricula, String email)throws ViaturaNaoExistenteException,MotoristaNaoDisponivelException{
     if(this.viaturas.containsKey(matricula)){
      for(Utilizador ut: utilizadores.values()){
        if(ut.getClass().getSimpleName().equals("Motorista")){
         Motorista mt = (Motorista) ut;
          if(mt.getDisponivel()==true){            
            for(Utilizador u: utilizadores.values()){
                if(email.equals(u.getEmail())){
                    if(u.getClass().getSimpleName().equals("Cliente")){
                        Cliente c = (Cliente) u;
                        for(Viatura v: viaturas.values()){
                            if(v.getMatricula().equals(matricula)){
                                if(v.getClass().getSimpleName().equals("CarroLigeiro")){
                                    CarroLigeiro cl = (CarroLigeiro) v;                       
                                    double custo = cl.calculardistancia(e,c.getLocalizacao()) * cl.getPrecoBaseKm();
                                    double distancia = cl.calculardistancia(e, c.getLocalizacao());
                                    double duracao = distancia / cl.getVelMedKm();
                                    LocalDate d = LocalDate.now();
                                    total++;
                                    if (cl.getViagensEmEspera().size()>0) {
                                        cl.adicionarViagemEspera(new Viagem(custo, duracao, distancia, total, e, d));
                                    }
                                    else {
                                        c.adicionaViagem(new Viagem(custo, duracao, distancia, total, e, d));
                                        mt.adicionaViagem(new Viagem(custo, duracao, distancia, total, e, d));
                                        cl.adicionaViagemViat(new Viagem(custo, duracao, distancia, total, e, d));
                                        c.setLocalizacao(e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
         }
         else throw new MotoristaNaoDisponivelException("O motorista desta viatura não esta a trabalhar");
        }
       }
      }        
     else throw new ViaturaNaoExistenteException("Viatura não existe");
    }
    
    /**
     * Solicitar uma viagem para o ponto R(x,y), escolhendo uma Moto
     */
    public void solicitarViagemM(Espaco2D e, String matricula, String email)throws ViaturaNaoExistenteException,MotoristaNaoDisponivelException{
      if(this.viaturas.containsKey(matricula)){
       for(Utilizador ut: utilizadores.values()){
         if(ut.getClass().getSimpleName().equals("Motorista")){
          Motorista mt = (Motorista) ut;
          if(mt.getDisponivel()==true){                      
            for(Utilizador u: utilizadores.values()){
                if(email.equals(u.getEmail())){
                    if(u.getClass().getSimpleName().equals("Cliente")){
                        Cliente c = (Cliente) u;
                        for(Viatura v: viaturas.values()){
                            if(v.getMatricula().equals(matricula)){
                                if(v.getClass().getSimpleName().equals("Moto")){
                                    Moto m = (Moto) v; 
                                    double custo = m.calculardistancia(e,c.getLocalizacao()) * m.getPrecoBaseKm();
                                    double distancia = m.calculardistancia(e, c.getLocalizacao());
                                    double duracao = distancia / m.getVelMedKm();
                                    LocalDate d = LocalDate.now();
                                    total++;
                                    c.adicionaViagem(new Viagem(custo, duracao, distancia, total, e, d));
                                    mt.adicionaViagem(new Viagem(custo, duracao, distancia, total, e, d));
                                    m.adicionaViagemViat(new Viagem(custo, duracao, distancia, total, e, d));
                                    c.setLocalizacao(e);
                                }
                            }
                        }
                    }
                }
            }
          }
          else throw new MotoristaNaoDisponivelException("O motorista desta viatura não esta a trabalhar");
        }
       }
      }  
      else throw new ViaturaNaoExistenteException("Viatura não existe");
    }
        
    /**
    * Solicitar uma viagem de ponto P(x,y) para o ponto R(x,y), solicitando a viatura mais próxima
    */
    public String solicitarViagem(Espaco2D e, String email)throws ViaturaNaoExistenteException,MotoristaNaoDisponivelException { 
       String matrix = new String();
       for(Utilizador u: utilizadores.values()){
            if(email.equals(u.getEmail())){
                  if(u.getClass().getSimpleName().equals("Cliente")){
                      Cliente c = (Cliente) u;
                      Map<Double, Viatura> distanciaViaturaCliente = new HashMap<>();
                      for(Viatura v : viaturas.values()){
                          double distanciaCliente = v.calculardistancia(v.getLocalizacao(), c.getLocalizacao());
                          distanciaViaturaCliente.put(distanciaCliente,v.clone());
                      }
                      Map<Double, Viatura> res = new TreeMap<Double, Viatura>(distanciaViaturaCliente);
                      Map.Entry<Double,Viatura> entry=res.entrySet().iterator().next();
                      double key = entry.getKey();
                      Viatura value = entry.getValue();
                      if (value.getClass().getSimpleName().equals("Carrinha")){
                          solicitarViagemC(e, value.getMatricula(), email);
                      }
                      else if (value.getClass().getSimpleName().equals("CarroLigeiro")){
                          solicitarViagemCL(e, value.getMatricula(), email);
                      } 
                      else if (value.getClass().getSimpleName().equals("Moto")){
                          solicitarViagemM(e, value.getMatricula(), email);
                      }
                      matrix = value.getMatricula();
                  }
            }   
       }
       return matrix;
    }
    
    /**
     * Alterar disponibilidade de um motorista
     */
    public void alteraDisponibilidade(String email, boolean trabalhar){
        for(Utilizador u: this.utilizadores.values()){
            if(email.equals(u.getEmail())){
                if(u.getClass().getSimpleName().equals("Motorista")){
                    Motorista mt = (Motorista) u;
                    mt.setDisponivel(trabalhar);
                }
            }
        }
    }
    
    /**
     * Adicionar uma nova viatura a uma empresa
     */
    public void add_viaturaE(Viatura v, String nomeEmpresa)throws EmpresaInexistenteException{
      if(this.empresas.containsKey(nomeEmpresa)){
         throw new EmpresaInexistenteException("Esta empresa não existe");
      }
      else{
        for(Empresa e: empresas.values()){
          if(e.getNome().equals(nomeEmpresa))  e.adicionaViatura(v);
        }
      }
    }
    
    /**
     * Associar uma nova viatura a um motorista
     */
    public void associarNovaViatura(Viatura v, String email)throws ViaturaRepetidaException{
      if(this.viaturas.containsKey(v.getMatricula())){
         throw new ViaturaRepetidaException("Já existe uma viatura com esta matrícula");
      }
      else{
        for(Utilizador u: utilizadores.values()){
          if(email.equals(u.getEmail())){
               if (u.getClass().getSimpleName().equals("Motorista")){
                    Motorista m = (Motorista) u;
                    m.setMatricula(v.getMatricula());
                    this.viaturas.put(v.getMatricula(),v.clone());
                }
            }
        }
      }
    }
    
    /**
     * Associar uma viatura já existente a um motorista
     */
    public void associarViatura(String matricula, String email)throws ViaturaNaoExistenteException{
      if(this.viaturas.containsKey(matricula)){
         throw new ViaturaNaoExistenteException("Não existe viatura com esta matrícula");
      }
      else{
        for(Utilizador u: utilizadores.values()){
          if(email.equals(u.getEmail())){
               if (u.getClass().getSimpleName().equals("Motorista")){
                    Motorista m = (Motorista) u;
                    m.setMatricula(matricula);
                }
            }
        }
      }
    }
    
    /**
     * Associar uma viatura a um motorista de uma empresa
     */
    public void associarViaturaE(String matricula, String email, String nomeEmpresa) throws MotoristaNaoEDaEmpresaException, ViaturaNaoEDaEmpresaException, EmpresaInexistenteException {
        if(this.empresas.containsKey(nomeEmpresa)){
         throw new EmpresaInexistenteException("Esta empresa não existe");
        }
        else{
          for(Empresa e: this.empresas.values() ) 
            if (e.getViaturas().containsKey(matricula)){
             if (e.getMotoristas().containsKey(email)) {
                e.associaViatEmp(matricula,email);
             }
             else throw new MotoristaNaoEDaEmpresaException("O motorista não pertence à empresa");
           }
           else throw new ViaturaNaoEDaEmpresaException("Esta viatura não pertence à empresa");
        }
    }
    
    /**
     * Classificar o motorista, após a viagem numa viatura
     */
    public void classificar(String email, String matricula, int nota)throws ViaturaNaoExistenteException{
       if(this.viaturas.containsKey(matricula)){
         for(Utilizador u: utilizadores.values()){
           if (u.getClass().getSimpleName().equals("Motorista")){
               Motorista m = (Motorista) u;
               if (m.getMatricula().equals(matricula)){
                      for(Utilizador ut: utilizadores.values()){
                          if (ut.getClass().getSimpleName().equals("Cliente")){
                              Cliente c = (Cliente) ut;
                              if(u.getEmail().equals(email)) c.darNota(m,nota);
                          }
                      }
               }
           }     
         }
       } else throw new ViaturaNaoExistenteException("Última viagem não pode ser classficada");
    }
    
    /**
     * Ter acesso, no perfil de cliente/motorista, à listagem das viagens efectuadas (entre datas).
     */
    public List<Viagem> viagensEfetuadas(String email, LocalDate d1, LocalDate d2)throws UtilizadorInexistenteException{
      if(this.utilizadores.containsKey(email)){
        List<Viagem> res = new ArrayList<>();
        for (Utilizador u : utilizadores.values()){
               if (u.getEmail().equals(email)){
            
                   for (Viagem v : u.getViagens().values()){
               
                       if ((v.getData()).isAfter(d1) && (v.getData()).isBefore(d2)) {
                           res.add(v.clone());
                        }
                
                    }
                }
            }
        return res;
      }
      else throw new UtilizadorInexistenteException("Este utilizador não existe");
    }
       
    /**
     * Indicar o total facturado por uma viatura num determinado período
     */ 
    public double totalFaturadoV(String matricula, LocalDate d1, LocalDate d2)throws ViaturaNaoExistenteException{
      if(this.viaturas.containsKey(matricula)){
        double total = 0.0;
        boolean encontrado = false;
        Iterator<Map.Entry<String,Viatura>> it = viaturas.entrySet().iterator();
        while (!encontrado && it.hasNext()) {
            Map.Entry<String, Viatura> v = it.next();
            if (v.getValue().getMatricula().equals(matricula)) {
                for (Viagem vi : v.getValue().getViagens().values()){
                
                if (vi.getData().isAfter(d1) && vi.getData().isBefore(d2)) {
                    total += vi.getCusto();
                }
       
               }      
            }
        }
        return total;
      }
      else throw new ViaturaNaoExistenteException("Esta viatura não existe");
    }
    
    /**
     * Indicar o total facturado por uma empresa de táxis num determinado período
     */
    public double totalFaturadoE(String nome, LocalDate d1, LocalDate d2)throws EmpresaInexistenteException{
        
        if (empresas.containsKey(nome)){
          double total = 0.0;
          for(Empresa e: this.empresas.values()){
           if(e.getNome().equals(nome)){  
            for (Viatura v : e.getViaturas().values()){
               for(Viagem vi: v.getViagens().values()){
                if (vi.getData().isAfter(d1) && vi.getData().isBefore(d2)) {
                    total += vi.getCusto();
                }                 
               }
            }
           }
          }
          return total;
        }
        else throw new EmpresaInexistenteException("Esta Empresa não existe");
    
    }
    
    /**
     * determinar a listagens dos 10 clientes que mais gastam 
     */
    public List<Cliente> top10ClientesMaisGastam(){
        List<Cliente> res = new ArrayList<>();
        Map<Double, Cliente> custoViagensCliente = new HashMap<>();
        List<Cliente> r = new ArrayList<>();
        
        for (Utilizador u : utilizadores.values()){
           if(u.getClass().getSimpleName().equals("Cliente")){
              Cliente c = (Cliente) u;
              double custo = 0.0;
              for(Viagem v : c.getViagens().values()){
                  custo += v.getCusto();
              }
              custoViagensCliente.put(custo,c.clone());
           }
        }
        
        Map<Double, Cliente> aux = new TreeMap<Double, Cliente>(custoViagensCliente);
        
        for (Cliente a: aux.values()) {
                  r.add(a.clone());
        }
        
        res = r.subList(Math.max(r.size() - 10, 0), r.size());
        return res;
    }
     
    public double tempoReal(Viagem v, Viatura x){
        if (x.getViagens().containsValue(v)){
            
            if ( (x.getFiabilidade() * v.getDuracao() - v.getDuracao()) > 0.25 * v.getDuracao()) return x.getVelMedKm ()* v.getDistancia();
            else return v.getDuracao();
    
        }
        else return 0.0;
    }
    
    /**
     * função que calcula o desvio entre os valores previstos para as viagens e o valor final faturado
     */
    public Map<Double,Motorista> desvios(List<Motorista> lm){
        Map<Double, Motorista> res = new TreeMap<>();
        double mediaDesvio = 0.0;
        int C=0; 
        for (Motorista m : lm){
           
           for (Viagem v : m.getViagens().values()){
               double desvio = v.getCusto() + (v.getDuracao()/v.getDistancia()*0.2) - v.getCusto();
               mediaDesvio = (mediaDesvio*C + desvio)/(C+1);
               C++;
           }
           
           res.put(mediaDesvio, m.clone());
        }
        return res;
    }
    
    /**
     * determinar a listagem dos 5 motoristas que apresentam mais desvios entre o valores previstos para as viagens e o valor final faturado
     */
    public List<Motorista> top5MotoristasDesvios(){
        List<Motorista> res = new ArrayList<>();
        List<Motorista> totalM = new ArrayList<>();
        for (Utilizador u : utilizadores.values()){
          if(u.getClass().getSimpleName().equals("Motorista")){
             Motorista m = (Motorista) u;
             totalM.add(m.clone());
          }
        }
        Map<Double, Motorista> d = new TreeMap<>();
        d = desvios(totalM);
        List<Motorista> r = new ArrayList<>();
        for (Motorista m : d.values()) {
            r.add(m.clone());
        }
        res = r.subList(Math.max(r.size() - 5, 0), r.size());
        return res;
    }

    /**
     * Calcular número total de utilizadores
     */
    public int quantos() {
        return utilizadores.size();
    }
    
    /**
     * Calcular número total de viaturas
     */
    public int quantas() {
        return viaturas.size();
    }
    
    /**
     * Calcular número total de clientes ou de motoristas, consoante o tipo fornecido
     */
    public int quantosPorTipo(String tipo) {
        int c = 0;
        for(Utilizador u: utilizadores.values()) {
            String cn = u.getClass().getSimpleName();
            if(cn.equals(tipo)) {
                c++;
            }
        }
        return c;
    }
    
    public int iniciaSessao(String email, String password) throws UtilizadorInexistenteException{
        int perfil = 0;
        if(!utilizadores.containsKey(email)) {
            throw new UtilizadorInexistenteException("Email inválido");
        }
        else{
          for (Utilizador u: utilizadores.values()) {
            if (email.equals(u.getEmail())) {
                if (!password.equals(u.getPassword())) {
                    throw new UtilizadorInexistenteException("Password incorreta!!!");
                } else {
                    if (u.getClass().getSimpleName().equals("Motorista")) {                    
                        System.out.println("\nValidou a sua entrada como Motorista!");
                        perfil = 2;
                    } else {
                        System.out.println("\nValidou a sua entrada como Cliente!");
                        perfil = 1;
                    }
                }
            }
          }
        }
        return perfil;
    }
    
    public UMeR clone(){
       return new UMeR(this);
    }
   
    public boolean equals(Object o) {
       if(o == this) {
            return true;
       }
       if(o == null || o.getClass() != this.getClass()) {
            return false;
       }
       UMeR u = (UMeR) o;
       return u.getUtilizadores().equals(this.utilizadores) && u.getViaturas().equals(this.viaturas) && u.getEmpresas().equals(this.empresas) && u.getTotal() == this.total;
    }
   
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utilizadores: ");
        for(Utilizador u: this.utilizadores.values())
            sb.append(u.toString()).append("\n");
        sb.append("Viaturas: ");
        for(Viatura v: this.viaturas.values())
            sb.append(v.toString()).append("\n");
        sb.append("Empresas: ");
        for(Empresa e: this.empresas.values())
            sb.append(e.toString()).append("\n");
        sb.append("Total de Viagens: " + total + "\n");
        return sb.toString();
    }    
}
