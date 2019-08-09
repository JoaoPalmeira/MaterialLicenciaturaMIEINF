import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;
/**
 * Write a description of class encomendinha here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class encomendinha
{
    private String nome;
    private int nif, ne;
    private String morada;
    private LocalDate data;
    private List<LinhaEncomenda> linhas;
    
    public encomendinha(String a, int b, String c, int d, LocalDate e, List<LinhaEncomenda> f){
        this.nome=a;
        this.nif=b;
        this.ne = d;
        this.morada=c;
        this.data=data;
        setEncomendas(f);
    }
    public encomendinha(){
        this.nome="";
        this.nif=0;
        this.ne=0;
        this.morada="";
        this.data=LocalDate.now();
        this.linhas = new ArrayList<>();
    }
    public encomendinha(encomendinha enc){
        this.nome=enc.getNome();
        this.nif=enc.getNif();
        this.ne=enc.getNe();
        this.morada=enc.getMorada();
        this.data=enc.getData();
        this.linhas=enc.getLinhas();
    }
    public String getNome(){
        return this.nome;
    }
    public int getNif(){
        return this.nif;
    }
    public int getNe(){
        return this.ne;
    }
    public String getMorada(){
        return this.morada;
    }
    public LocalDate getData(){
        return this.data;
    }
    public List<LinhaEncomenda> getLinhas(){
        List<LinhaEncomenda> res = new ArrayList<>();
        for(LinhaEncomenda l : this.linhas){
            res.add(l.clone());
        }
        return res;
    }
    public void setEncomendas(List<LinhaEncomenda> le){
        this.linhas = new ArrayList<>();
        for(LinhaEncomenda l : le){
            this.linhas.add(l.clone());
        }
    }
    
    //funções
    public double calculaValorTotal(){
        double total=0;
        for(LinhaEncomenda l : this.linhas){
            total+=l.calculaValorLinhaEnc();
        }
        return total;
    }
    
    public double calculaValorDesconto(){
        double totDesc = 0;
        for(LinhaEncomenda l : this.linhas){
            totDesc += l.calculaValorDesconto();
        }
        return totDesc;
    }
    
    public int numeroTotalProdutos() {
        int aReceber = 0;
        for(LinhaEncomenda l : this.linhas) {
            aReceber += l.getQuantidade();
        }
        return aReceber;
    }
    
    public boolean existeProdutoEncomenda(String refProduto){
        boolean existe = false;
        for(int i=0; existe != true && i<this.linhas.size(); i++){
            if(this.linhas.get(i).getReferencia().equals(refProduto)){
                existe=true;
            }
        }
        return existe;
    }
    
    public void adicionaLinha(LinhaEncomenda linha){
        this.linhas.add(linha.clone());
    }
    
    public void removeProduto(String codProd){
      for(Iterator<LinhaEncomenda> it = this.linhas.iterator() ; it.hasNext();){
          LinhaEncomenda le = it.next();
          if(le.getReferencia().equals(codProd)){
              it.remove();
          }
      }
    }
    
    
    //equals, toString, clone
    public Encomenda clone() {
        return new Encomenda();
    }
    public boolean equals(Object o) {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass()) return false;
        encomendinha e = (encomendinha)o;
        return nome.equals(e.getNome()) && nif == e.getNif() &&
               morada.equals(e.getMorada()) && ne == e.getNe() && 
               data.equals(e.getData()) && this.linhas.equals(e.getLinhas()); 
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda : [");
        sb.append("Número Encomenda: ").append(this.ne);
        sb.append("NomeCliente: ").append(this.nome);
        sb.append("NIF: ").append(this.nif);
        sb.append("Morada: ").append(this.morada);
        sb.append("Data Enc: ").append(this.data);
        sb.append(this.linhas.toString());
        return sb.toString();
    }
}
