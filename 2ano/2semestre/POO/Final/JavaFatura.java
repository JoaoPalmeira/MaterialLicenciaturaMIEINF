import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.io.*;
import java.lang.Object;
import java.util.Iterator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * 
 */
public class JavaFatura implements Serializable
{
    // variáveis de instância
    private Map<Integer,Utilizador> utilizadores;
    private Map<Integer, List<Despesa>> faturas;
    private Map<Integer, List<Integer>> despesasAlterada;
    private Map<String,Double> concelhos;
    
    public JavaFatura(){
        this.utilizadores = new HashMap<>();
        this.faturas =  new HashMap<>();
        this.despesasAlterada = new HashMap<>();
        this.concelhos = new HashMap<>();
    }
    
    public JavaFatura(Map<Integer,Utilizador> untilizadores, Map<Integer,List<Despesa>> faturasS, Map<Integer, List<Integer>> desp,  Map<String,Double> concelhoS){
        this.utilizadores =  new HashMap<>();
        for(Utilizador u : untilizadores.values()){
            this.utilizadores.put(u.getNif(),u.clone());
        }
        this.faturas =  new HashMap<>();
        for(List<Despesa> despesas : faturasS.values()){
            for(Despesa d : despesas){
                this.faturas.put(d.getNifC(),despesas);
            }
        }
        this.despesasAlterada =  new HashMap<>();
        for(List<Integer> ids : desp.values()){
            for(Integer i : ids){
                this.despesasAlterada.put(i,ids);
            }
        }
    }
    
    
    public JavaFatura(JavaFatura jf){
        this.utilizadores = jf.getUtilizadores();
        this.faturas = jf.getFaturas();
        this.despesasAlterada = jf.getDespesasAlt();
    }
    
    // getters
    
    public Map<Integer,Utilizador> getUtilizadores(){
        Map<Integer,Utilizador> res = new HashMap<>();
        for(Utilizador u : this.utilizadores.values()){
            res.put(u.getNif(),u.clone());
        }
        return res;
    }
    
    public Map<Integer,List<Despesa>> getFaturas(){
        Map<Integer,List<Despesa>> res =  new HashMap<>();
        for(List<Despesa> despesas : this.faturas.values()){
            for(Despesa d : despesas){
                res.put(d.getNifC(),despesas);
            }
        }
        return res;
    }
    
    public Map<Integer, List<Integer>> getDespesasAlt(){
        Map<Integer, List<Integer>>  res = new HashMap<>();
        for(List<Integer> ids : this.despesasAlterada.values()){
            for(Integer i : ids){
                res.put(i,ids);
            }
        }
        return res;
    }
    
    // setters
    
    public void setUtilizadores(Map<Integer,Utilizador> untilizadores){
       this.utilizadores = new HashMap<>();
       for(Utilizador u: untilizadores.values())
           this.utilizadores.put(u.getNif(),u.clone());
    }

    
    // -------------------- funçoes pedidas ----------------- //
    
     /**
      * Inicia Sessao na aplicacao
      */
    
     public int iniciaSessao(int contribuinte, String pass) throws UtilizadorInexistenteException{
        
        int funcao = 0;
        
        if(contribuinte == 1234){
                    if(!pass.equals("admin")){
                        throw new UtilizadorInexistenteException("Password incorreta !!!");
                    }
                    else{
                        System.out.println("\nValidou a sua entrada como Administrador!");
                        funcao = 3;
                    }
                }
        else {
            if(!utilizadores.containsKey(contribuinte)) {
                throw new UtilizadorInexistenteException("Contribuinte inválido");
            }
            else{
                for (Utilizador u: utilizadores.values()) {
                    if (u.getNif() == contribuinte) {
                        if (!pass.equals(u.getPass())) {
                            throw new UtilizadorInexistenteException("Password incorreta!!!");
                        } else {
                            if (u.getClass().getSimpleName().equals("Contribuinte")) {                    
                                System.out.println("\nValidou a sua entrada como Contribuinte individual!");
                                funcao = 1;
                            } else {
                                System.out.println("\nValidou a sua entrada como Empresa!");
                                funcao = 2;
                            }
                        }
                    }
                }
            }
        }
        return funcao;
    }
    
    /**
     * Adicionar um utilizador
     */
    
    public void adicionaU(Utilizador u) throws UtilizadorRepetidoException {
        if(utilizadores.containsKey(u.getNif())){
           throw new UtilizadorRepetidoException("\nNIF repetido !!!");  
        }
        utilizadores.put(u.getNif(), u.clone());
    }
    
    /**
     * Adicionar uma AtividadeEconomica de uma Empresa
     */
    
    public void associarActEmp(ActividadeEconomica act, int contribuinte) throws ActividadeEconomicaRepetidaException {
        for(Utilizador u : utilizadores.values()){
            if(contribuinte == u.getNif()){
                if (u.getClass().getSimpleName().equals("Empresa")){
                    Empresa e = (Empresa) u;
                    if (e.adicionaActividade(act)==true){
                         throw new ActividadeEconomicaRepetidaException("\nActividade Economica repetida !!!"); 
                    }
                    else{
                        e.adicionaActividade(act);
                    }
                }
            }
        }
    }
    
    /**
     * Adicionar uma AtividadeEconomica de contribuinte individual
     */
    
    public void associarActCont(ActividadeEconomica act, int contribuinte) throws ActividadeEconomicaRepetidaException {
        for(Utilizador u : utilizadores.values()){
            if(contribuinte == u.getNif()){
                if (u.getClass().getSimpleName().equals("Contribuinte")){
                    Contribuinte c = (Contribuinte) u;
                    if (c.adicionaActividade(act)==true){
                         throw new ActividadeEconomicaRepetidaException("\nActividade Economica repetida !!!"); 
                    }
                    else{
                        c.adicionaActividade(act);
                    }
                }
            }
        }
    }

    /**
     * Cria fatura associada a um contribuinte individual
    */
    
    public void criaFaturaAss(Despesa d) throws UtilizadorInexistenteException, ActividadeEconomicaInexistenteException{
       int nifE = d.getNifE();
       int nifC = d.getNifC();
       int count = 0 ;
       List<Despesa> despesasCont = new ArrayList<>();
       
       for(Utilizador u : utilizadores.values()){
           if(nifE == u.getNif()){
                if (u.getClass().getSimpleName().equals("Empresa")){
                     Empresa e = (Empresa) u;
                     d.setDesignacao(e.getDesignacao());
                     if (!e.getActivEcono().containsKey(d.getNaturezaDespesa().getCodigo())){
                         throw new ActividadeEconomicaInexistenteException("Actividade Economica nao existe na empresa...");
                     }
                }
           }      
       }
       
      if(this.utilizadores.containsKey(nifC)){
           if(this.faturas.isEmpty()==false){
               if(this.faturas.containsKey(nifC)){
                  despesasCont.addAll(this.faturas.get(nifC));
                  count = despesasCont.size();
                  d.setId(count+1);
                  despesasCont.add(d.clone());
                  this.faturas.put(nifC,despesasCont);
               }
               else{
                    despesasCont.add(d.clone());
                    this.faturas.put(nifC,despesasCont);
               }
           }
           else{
              despesasCont.add(d);
              this.faturas.put(nifC,despesasCont);
           }
      }
      else{
          throw new UtilizadorInexistenteException("Contribuinte Individual inexistente...");
      }
    }
        
    
    /**
     * Valida as faturas automaticamente
     */
   
    public void validaFact(int cF) throws UtilizadorSemFaturaException, FaturaInexistenteException{
        List<Despesa> despesasCont = new ArrayList<>();
        List<Integer> idDespesas = new ArrayList<>();
        Map<Integer,ActividadeEconomica> actC = new HashMap<>();
        Map<Integer,ActividadeEconomica> actE = new HashMap<>();
        int count=0;
        int flag = 1;
        int id = 0;
        
        if(this.faturas.containsKey(cF)){
                despesasCont.addAll(this.faturas.get(cF));
                for(Despesa d : despesasCont){
                    id = 0;
                    id = d.getId();
                    for(Utilizador u : this.utilizadores.values()){
                        if(d.getNifE() == u.getNif()){
                            if (u.getClass().getSimpleName().equals("Empresa")){
                                Empresa e = (Empresa) u;
                                actE = e.getActivEcono();
                                count = e.getActivEcono().size();  
                            }
                        }
                        if(d.getNifC() == u.getNif()){
                            if (u.getClass().getSimpleName().equals("Contribuinte")){
                                Contribuinte c = (Contribuinte) u;
                                actC = c.getCodAct();
                            }
                        }
                    }
                    
                    for(Integer i : actC.keySet()){
                        if(!actE.containsKey(i)){
                            flag = 0;
                        }
                    }
                    
                    if((count==1) && (flag==1)){
                       d.setValidade(true);
                    }
                    else{
                       d.setValidade(false);
                    }
                    
                    if(this.despesasAlterada.containsKey(cF)){
                        idDespesas.addAll(this.despesasAlterada.get(cF));
                        if(idDespesas.contains(id)){
                             d.setValidade(true);
                        }
                    }
                    else{
                        throw new FaturaInexistenteException(" ");
                    }
                }   
        }
        else {
            throw new UtilizadorSemFaturaException("Utilizador sem faturas...");
        }
    }
    
    /**
     * Adicionar um id de despesa
     */
    
    public void adicionaId(int nif, int id){
       List<Integer> despesasCont = new ArrayList<>();
       
       if(this.despesasAlterada.isEmpty()==false){
               if(this.despesasAlterada.containsKey(nif)){
                  despesasCont.addAll(this.despesasAlterada.get(nif));
                  if(!despesasCont.contains(id)){
                      despesasCont.add(id);
                      this.despesasAlterada.put(nif,despesasCont);
                  }
               }
               else {
                    despesasCont.add(id);
                    this.despesasAlterada.put(nif,despesasCont);
               }
       }
       else{
           despesasCont.add(id);
           this.despesasAlterada.put(nif,despesasCont);
       }
    }

    
    /**
     * Consulta as faturas de uma dado contribuinte 
     */
    
    public List<Despesa> consultaFat(int nifC) throws UtilizadorSemFaturaException{
         List<Despesa> despesasNifC = new ArrayList<>();
        
         if(this.faturas.containsKey(nifC)){
            despesasNifC.addAll(this.faturas.get(nifC));  
         }
         else{
             throw new UtilizadorSemFaturaException(" ");
         }
         
         return despesasNifC;
    }
    
    /**
     * Consulta as deduçoes do contribuinte e do seu agregrado
     */
    
    public Double consultaDeducoes(int nifC)throws UtilizadorSemFaturaException{
        List<Despesa> despesasAux1;
        List<Despesa> despesasAux2;
        List<Integer> numAgr = new ArrayList<>();
        double total = 0.0;
        int num = 0;
        
        if(this.faturas.containsKey(nifC)){  
            for(Utilizador u : this.utilizadores.values()){
                if(u.getNif() == nifC){
                    if(u.getClass().getSimpleName().equals("Contribuinte")){
                        Contribuinte c = (Contribuinte) u;
                        if(c.getNfa().size() > 1){
                            num = c.getNAgrFam();
                            numAgr.addAll(c.getNfa());
                        }
                    }
                }
            }
            
            if(!numAgr.isEmpty()){
                for(Integer i : numAgr){
                    if(this.faturas.containsKey(i)){
                        despesasAux1 = new ArrayList<>();
                        despesasAux1.addAll(this.faturas.get(i));
                        despesasAux2 = new ArrayList<>();
                        for(Despesa d : despesasAux1){
                            if(d.getValidade() == true){
                                despesasAux2.add(d.clone());
                            }
                        }
                        
                        total +=calcula(despesasAux2);
                    }
                }
            }         
            
            total = total/num;  
        }
        else{
            throw new UtilizadorSemFaturaException("Utilizador sem faturas...");
        }
         
        return total;
    }
    
    public double calcula(List<Despesa> despesas){
        double dedu = 0;
        int nifE = 0;
        int nifC = 0;
        Utilizador u1 = new Utilizador();
        Utilizador u2 = new Utilizador();
        ActividadeEconomica ae;
        int cod = 0 ;
        Map<Integer,ActividadeEconomica> act;

        for(Despesa d : despesas){
            dedu += d.getValorDespesa();
            nifE = d.getNifE();
            nifC = d.getNifC();
            u1 = this.utilizadores.get(nifC);
            u2 = this.utilizadores.get(nifE);
            cod = d.getNaturezaDespesa().getCodigo();
            
            if(u1.getClass().getSimpleName().equals("Contribuinte")){
                Contribuinte c = (Contribuinte) u1;
                dedu *= c.getCoef();
            }
            
            if(u2.getClass().getSimpleName().equals("Empresa")){
                Empresa e = (Empresa) u2;
                act = new HashMap<>();
                ae = new ActividadeEconomica();
                ae = act.get(d.getNaturezaDespesa().getCodigo());
                dedu *= e.getFactDeducao();
                if(ae != null) {
                    if(ae.getClass().getSimpleName().equals("Restauracao")){
                        Restauracao r = (Restauracao) ae;
                        dedu *= r.getValorDeducao();
                    }
                    else if(ae.getClass().getSimpleName().equals("Educacao")){
                        Educacao ed = (Educacao) ae;
                        dedu *= ed.getValorDeducao();
                    }
                    else if(ae.getClass().getSimpleName().equals("Servicos")){
                        Servicos sr = (Servicos) ae;
                        dedu *= sr.getValorDeducao();
                    }
                    else if(ae.getClass().getSimpleName().equals("Saude")){
                        Saude s = (Saude) ae;
                        dedu *= s.getValorDeducao();
                    }
                    else if(ae.getClass().getSimpleName().equals("ReparacaoVeiculos")){
                        ReparacaoVeiculos rv = (ReparacaoVeiculos) ae;
                        dedu *= rv.getValorDeducao();
                    }
                    else if(ae.getClass().getSimpleName().equals("Transportes")){
                        Transportes t = (Transportes) ae;
                        dedu *= t.getValorDeducao();
                    }
                    else if(ae.getClass().getSimpleName().equals("Habitacao")){
                        Habitacao h = (Habitacao) ae;
                        dedu *= h.getValorDeducao();
                    }
                }
            }
            
            dedu = reducaoImposto(dedu,nifE,nifC,d);
        }
        
        return dedu;
    }
    
    //----------/////// Requisitos Avançados ///////-------------//
    
    /**
     * Reduz os impostos para uma familia numerosa e para uma empresa do interior (Requisito avançado)
     */
    
    public double reducaoImposto(double total, int nifE, int nifC, Despesa d){
        double perc = 0.0;
        int aux = 0;
        
        // Familia Numerosa
        
        for(Utilizador u : this.utilizadores.values()){
            if(nifC == u.getNif()){
                if(u.getClass().getSimpleName().equals("Contribuinte")){
                    Contribuinte c = (Contribuinte) u;
                    aux = c.getNfa().size();
                    if((c.getNfa().size() > 5) && (aux > 1)){
                        total +=5;
                        aux--;
                    }
                }
            }
        }
        
        // Empresa com incentivo fiscal
        
        for(Utilizador u : this.utilizadores.values()){
            if(nifE == u.getNif()){
                if(u.getClass().getSimpleName().equals("Empresa")){
                    Empresa e = (Empresa) u;
                    if(e.getLocalizacao().equals("interior") || e.getLocalizacao().equals("Interior")){
                        if(this.concelhos.containsKey(u.getMorada())){
                           perc = this.concelhos.get(u.getMorada());
                           total *= (1+(perc)/100);
                        }
                    }
                }
            }
        }
        
        return total;
    }
    
    /**
     * Adiciona concelhos interiores
     */
    
    public void adicionaConcelho(String cidade, double perc) throws CidadeExistenteException{
        if(this.concelhos.isEmpty()){
            concelhos.put(cidade,perc);
        }
        else if(concelhos.containsKey(cidade)){
            throw new CidadeExistenteException("Cidade ja registada...");     
        }
        else{
            concelhos.put(cidade,perc);
        }
    }
    
    /**
     * Da as faturas validas de um determinado contribuinte e do seu agregrado
     */
    
    public Map<Integer,List<Despesa>> factValiDedu(List<Integer> nFis) throws UtilizadorInexistenteException, UtilizadorSemFaturaException{
        Map<Integer,List<Despesa>> res = new HashMap<>();
        Map<Integer,List<Despesa>> factVali = new HashMap<>();
        List<Despesa> dedu;
        List<Despesa> aux;
        
        for(Integer i : nFis){
            if(this.utilizadores.containsKey(i)){
                if(this.faturas.containsKey(i)){
                    aux = new ArrayList<>();
                    dedu = new ArrayList<>();
                    aux.addAll(this.faturas.get(i));
                    for(Despesa d : aux){
                        if(d.getValidade() == true){
                            dedu.add(d.clone());
                        }
                    }
                    factVali.put(i,dedu);
                }
                else{
                    throw new UtilizadorSemFaturaException(" ");
                }
            }
            else{
                throw new UtilizadorInexistenteException("Utilizador nao registado na aplicacao, é favor registar...");
            }
        }
        
        res = factDedu(factVali);
        
        return res;
    }
    
    /**
     * Da as faturas deduziveis de um determinado contribuinte e do seu agregrado
     */
    
    public Map<Integer,List<Despesa>> factDedu(Map<Integer,List<Despesa>> factVali){
        Map<Integer,ActividadeEconomica> codAct;
        List<Despesa> aux;
        List<Despesa> dedu;
        Map<Integer,List<Despesa>> res = new HashMap<>();
        
        for(Utilizador u : this.utilizadores.values()){
            if(u.getClass().getSimpleName().equals("Contribuinte")){
                Contribuinte c = (Contribuinte) u;
                for(Integer i : factVali.keySet()){
                    if(i == u.getNif()){
                        codAct = new HashMap<>();
                        aux = new ArrayList<>();
                        dedu = new ArrayList<>();
                        aux.addAll(factVali.get(i));
                        codAct = c.getCodAct();
                        for(Despesa d : aux){
                            if(codAct.containsKey(d.getNaturezaDespesa().getCodigo())){
                                dedu.add(d.clone());
                            }
                        }
                        res.put(i,dedu);
                    }
                }
            }
        }
        
        return res;
    }
    
    /**
     * Faturas nao validas de um contribuinte
     */
    
    public List<Despesa> faturasInvalidas(int nifC) throws UtilizadorSemFaturaException, FaturaInexistenteException{
        List<Despesa> aux = new ArrayList<>();
        List<Despesa> despesasInva = new ArrayList<>();
    
        if(this.faturas.containsKey(nifC)){
            aux.addAll(this.faturas.get(nifC));
            for(Despesa d : aux){
                if(d.getValidade()==false){
                    despesasInva.add(d.clone());
                }
            }
        }
        else{
            throw new UtilizadorSemFaturaException(" ");
        }
        
        if(despesasInva.isEmpty()){
            throw new FaturaInexistenteException("Utilizador sem faturas invalidas...");
        }
        
        return despesasInva;
    }
    
    /**
     * Codigos de actividades de uma empresa de uma despesa
     */
    
    public Map<Integer,ActividadeEconomica> codActiEmp(int id, List<Despesa> despInv) throws FaturaInexistenteException{
        int flag = 1;
        int nifE = 0;
        Map<Integer,ActividadeEconomica> codAct =  new HashMap<>();
        
        for(Despesa d : despInv){
            if(d.getId() == id){
                flag = 1;
                nifE = d.getNifE();
                for(Utilizador u : this.utilizadores.values()){
                    if(nifE == u.getNif()){
                        if(u.getClass().getSimpleName().equals("Empresa")){
                            Empresa e = (Empresa) u;
                            codAct = e.getActivEcono();
                        }
                    }
                }
                break;
            }
            else{
                flag=0;
            }
        } 
        
        if(flag == 0){
            throw new FaturaInexistenteException("Introduza o ID correto...");
        }
        
        return codAct;
    }
    
    /**
     * Codigos de actividades de um contribuinte 
     */
    
    public Map<Integer,ActividadeEconomica> codActiCont(int nif) throws ActividadeEconomicaInexistenteException{
        int flag = 1;
        Map<Integer,ActividadeEconomica> codAct =  new HashMap<>();
        
        for(Utilizador u : this.utilizadores.values()){
            if(u.getNif() == nif){
                if(u.getClass().getSimpleName().equals("Contribuinte")){
                    Contribuinte c = (Contribuinte) u;
                    codAct = c.getCodAct();
                }
            }
        }
        
        if(codAct.isEmpty()){
            throw new ActividadeEconomicaInexistenteException("Nao tem ActividadesEconomicas para deduzir...");
        }
        
        return codAct;
    }
    
    /**
     * Corrige fatura de uma contribuinte
     */
    
    public int corrigeFact(int id, ActividadeEconomica ae, int nif) throws UtilizadorSemFaturaException {
        List<Despesa> despCont = new ArrayList<>();
        int res=0;
        if(this.faturas.containsKey(nif)){
            despCont.addAll(this.faturas.get(nif));
            for(Utilizador u : this.utilizadores.values()){
                if(u.getNif() == nif){
                    if(u.getClass().getSimpleName().equals("Contribuinte")){
                        Contribuinte c = (Contribuinte) u;
                        for(Despesa d : despCont){
                            if(id == d.getId() && ae.getCodigo()==d.getNaturezaDespesa().getCodigo()){
                                d.setActividadeEconomica(ae);
                                d.setValidade(true);
                                res=1;
                            }
                        }
                    }
                }
            }
        }
        else{
            throw new UtilizadorSemFaturaException(" ");
        }
        return res;
    }
    
    
    /**
     * Consulta as faturas de uma empresa por ordem de data
     */
    
    public List<Despesa> ordenaDataFact(int nif) throws UtilizadorInexistenteException, FaturaInexistenteException{
        List<Despesa> res = new ArrayList<>();
        
        if(this.utilizadores.containsKey(nif)){
            
            for(List<Despesa> desp : this.faturas.values()){
                for(Despesa d : desp){
                    if(d.getNifE() == nif){
                        res.add(d.clone());
                    }
                }
            }
            
            Collections.sort(res, new ComparatorData());
        }
        else{
            throw new UtilizadorInexistenteException("Empresa nao existe...");
        }
        
        if(res.isEmpty()){
            throw new FaturaInexistenteException("Empresa nao tem faturas em seu nome...");
        }
      
        return res;
    }
    
    
    /**
     * Consulta as faturas de uma empresa por ordem de valor
     */
    
    public List<Despesa> ordenaValorFact(int nif) throws UtilizadorInexistenteException, FaturaInexistenteException{
        List<Despesa> res = new ArrayList<>();
        
        if(this.utilizadores.containsKey(nif)){
            
            for(List<Despesa> desp : this.faturas.values()){
                for(Despesa d : desp){
                    if(d.getNifE() == nif){
                        res.add(d.clone());
                    }
                }
            }
            
            Collections.sort(res, new ComparatorValor());
        }
        else{
            throw new UtilizadorInexistenteException("Empresa nao existe...");
        }
        
        if(res.isEmpty()){
            throw new FaturaInexistenteException("Empresa nao tem faturas em seu nome...");
        }
      
        return res;
    }
    
    /**
     * Consulta as faturas de um contribuinte entre duas datas
     */
    
    public List<Despesa> consuFactData(int nifE, int nifC, LocalDate d1, LocalDate d2) throws UtilizadorInexistenteException, FaturaInexistenteException, UtilizadorSemFaturaException{
        List<Despesa> res = new ArrayList<>();
        List<Despesa> aux = new ArrayList<>();
        
        if(this.utilizadores.containsKey(nifC)){
            if(this.faturas.containsKey(nifC)){
                aux.addAll(this.faturas.get(nifC));
                for(Despesa d : aux){
                    if(d.getNifE() == nifE){
                        if((d1.isBefore(d.getDataDespesa()) && d2.isAfter(d.getDataDespesa())) || d1.equals(d.getDataDespesa()) || d2.equals(d.getDataDespesa())){
                            res.add(d.clone());
                        }
                    }
                }
            }
            else{
                throw new FaturaInexistenteException("O contribuinte nao tem faturas em seu nome...");
            }
        }
        else{
            throw new UtilizadorInexistenteException("O utilizador que pretende consultar nao existe...");
        }
        
        if(res.isEmpty()){
            throw new UtilizadorSemFaturaException("A empresa nao tem faturas em nome desse contribuinte (nesse periodo)...");
        }
        
        Collections.sort(res, new ComparatorData());
        return res;
    }
    
    /**
     * Consulta das faturas de um contribuinte por valor ordendas decrescente
     */
    
    public List<Despesa> consuFactValor(int nifE, int nifC) throws UtilizadorInexistenteException, FaturaInexistenteException, UtilizadorSemFaturaException{
        List<Despesa> res = new ArrayList<>();
        List<Despesa> aux = new ArrayList<>();
        
        if(this.utilizadores.containsKey(nifC)){
            if(this.faturas.containsKey(nifC)){
                aux.addAll(this.faturas.get(nifC));
                for(Despesa d : aux){
                    if(d.getNifE() == nifE){
                        res.add(d.clone());
                    }
                }
            }
            else{
                throw new FaturaInexistenteException("O contribuinte nao tem faturas em seu nome...");
            }
        }
        else{
            throw new UtilizadorInexistenteException("O utilzador que pretende consultar nao existe...");
        }
        
        if(res.isEmpty()){
            throw new UtilizadorSemFaturaException("A empresa nao tem faturas em nome desse contribuinte...");
        }
        
        Collections.sort(res, new ComparatorValor());
        Collections.reverse(res);
        return res;
    } 
    
    /**
     * Total faturado por uma empresa num periodo
     */
    
    public double totalFact(int nifE, LocalDate d1, LocalDate d2) throws UtilizadorInexistenteException, UtilizadorSemFaturaException{
        double total = 0;
        
        if(this.utilizadores.containsKey(nifE)){
            for(List<Despesa> aux : this.faturas.values()){
                for(Despesa d : aux){
                    if(nifE == d.getNifE()){
                        total += d.getValorDespesa();
                    }
                }
            }
        }
        else{
            throw new UtilizadorInexistenteException("Empresa nao existe no sistema...");
        }
        
        if(total == 0){
            throw new UtilizadorSemFaturaException("Empresa nao tem faturas em seu nome...");
        }
        
        return total;
    }
    
    /**
     * 10 contribuintes que mais gastam na aplicacao
     */
    
    public Map<Contribuinte,Double> topCliente() throws UtilizadorInexistenteException, FaturaInexistenteException{
        Map<Contribuinte,Double> totalContribuinte = new HashMap<>();
        Map sortedMap = new TreeMap();
        Map<Contribuinte,Double> res = new HashMap<>();
        List<Despesa> aux;
        double soma = 0;
        
        if(!this.utilizadores.isEmpty()){
            for(Utilizador u : this.utilizadores.values()){
                if(u.getClass().getSimpleName().equals("Contribuinte")){
                    Contribuinte c = (Contribuinte) u;
                    if(!this.faturas.isEmpty()){
                        if(this.faturas.containsKey(u.getNif())){
                            aux = new ArrayList<>();
                            soma = 0;
                            aux.addAll(this.faturas.get(u.getNif()));
                            for(Despesa d : aux){
                                soma += d.getValorDespesa();
                            }
                            totalContribuinte.put(c.clone(),soma);
                            sortedMap = sortByValue(totalContribuinte);
                            res.putAll(sortedMap);
                        }
                    }
                    else{
                        throw new FaturaInexistenteException("A aplicacao nao tem registos de faturas...");
                    }
                }
            }
        }
        else{
            throw new UtilizadorInexistenteException("A aplicacao nao tem utilizadores...");
        }
        return res;
    }
    
    /**
     * X empresas com mais faturas na aplicacao e o montante das deduçoes
     */
    
    public Map<Empresa,Double> topEmpresas() throws UtilizadorInexistenteException, FaturaInexistenteException{
        Map<Empresa,Double> res =  new HashMap<>();
        Map<Empresa,Double> resp =  new HashMap<>();
        Map<Empresa,List<Despesa>> despesasEmp = new HashMap<>();
        Map<Empresa,Integer> quantFat = new HashMap<>();
        ValueComparator1 bvc = new ValueComparator1(despesasEmp);
        Map<Empresa,List<Despesa>> sorted_map = new TreeMap<Empresa,List<Despesa>>(bvc);
        
        if(!this.utilizadores.isEmpty()){
            if(!this.faturas.isEmpty()){  
               for(Utilizador u : this.utilizadores.values()){
                    List<Despesa> aux = new ArrayList<>();
                    if(u.getClass().getSimpleName().equals("Empresa")){
                        Empresa e = (Empresa) u;
                        for(List<Despesa> despesas : this.faturas.values()){
                            for(Despesa d : despesas){
                                if(u.getNif() == d.getNifE()){
                                    aux.add(d.clone());
                                }
                            }
                        }
                        despesasEmp.put(e.clone(),aux);
                    }
               }
               
               sorted_map.putAll(despesasEmp);
               
               for(Map.Entry<Empresa,List<Despesa>> entry : sorted_map.entrySet()){
                   Empresa empresa = entry.getKey();
                   List<Despesa> despesas = new ArrayList<>();
                   Map<Integer,ActividadeEconomica> codAct = new HashMap<>();
                   codAct.putAll(empresa.getActivEcono());
                   despesas.addAll(entry.getValue());
                   double dedu = 0;
                   int cod = 0;
                   dedu += empresa.getFactDeducao();
                   
                   if(!despesas.isEmpty()){
                       for(Despesa d: despesas){
                           dedu = dedu * d.getValorDespesa();
                           cod = d.getNaturezaDespesa().getCodigo();
                           for(ActividadeEconomica ae : codAct.values()){
                                if(ae.getClass().getSimpleName().equals("Restauracao") && cod==1){
                                   Restauracao r = (Restauracao) ae;
                                   dedu *= r.getValorDeducao();
                                }
                                if(ae.getClass().getSimpleName().equals("Educacao") && cod==2){
                                   Educacao ed = (Educacao) ae;
                                   dedu *= ed.getValorDeducao();
                                }
                                if(ae.getClass().getSimpleName().equals("Servicos") && cod==3){
                                    Servicos se = (Servicos) ae;
                                    dedu *= se.getValorDeducao();
                                }   
                                if(ae.getClass().getSimpleName().equals("Saude") && cod==4){
                                    Saude s = (Saude) ae;
                                    dedu *= s.getValorDeducao();
                                }
                                if(ae.getClass().getSimpleName().equals("ReparacaoVeiculos") && cod==5){
                                    ReparacaoVeiculos rv = (ReparacaoVeiculos) ae;
                                    dedu *= rv.getValorDeducao();
                                }
                                if(ae.getClass().getSimpleName().equals("Transportes") && cod==6){
                                    Transportes t = (Transportes) ae;
                                    dedu *= t.getValorDeducao();
                                }
                                if(ae.getClass().getSimpleName().equals("Habitacao") && cod==7){
                                    Habitacao h = (Habitacao) ae;
                                    dedu *= h.getValorDeducao();
                                }   
                           }
                       }
                       res.put(empresa.clone(),dedu);
                   }
               }
               
               return res;
            }
            else{
                throw new FaturaInexistenteException("A aplicacao nao tem registos de faturas...");
            }
        }
        else{
            throw new UtilizadorInexistenteException("A aplicacao nao tem utilizadores...");
        }
    }
    
    /**
     * Funcao auxiliar para ordenar um map
     */
     
    private static Map sortByValue(Map unsortedMap) {
        Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }
    
    /**
     * Verifica se um nif existe
    */
    
    public boolean verificaNifExiste(int nif) {
        if(utilizadores.containsKey(nif)){
           return true;          
        }
        else{
            return false;
        }
    }
    
    // equals
    
    public boolean equals(Object o) {
       if(o == this) {
            return true;
       }
       if(o == null || o.getClass() != this.getClass()) {
            return false;
       }
       JavaFatura jf = (JavaFatura) o;
       return (jf.getUtilizadores().equals(this.utilizadores) && 
               jf.getFaturas().equals(this.faturas));
    }
    
    // toString
    
     public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Utilizador u: this.utilizadores.values()){
            sb.append("Utilizadores: \n");
            sb.append(u.toString()).append("\n");
        }   
        for(List<Despesa> despesas : this.faturas.values()){
            for(Despesa d : despesas){
                sb.append("Faturas: \n");
                sb.append(d.toString()).append("\n");
            }
        }
        return sb.toString();
    }  
    
    // clone
 
    public JavaFatura clone(){
        return new JavaFatura(this);
    }
}
