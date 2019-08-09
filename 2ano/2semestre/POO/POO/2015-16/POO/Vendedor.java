import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
public class Vendedor extends Utilizador
{
    private List<Imovel> imoveis;
    private List<Consulta> consultas;
    
    public Vendedor () {
        super();
        this.imoveis = new ArrayList<Imovel>();
        this.consultas = new ArrayList<Consulta>();
    }
    public Vendedor (String email,
                     String nome,
                     String password,
                     String morada,
                     Date dataNasc,
                     List<Imovel> imoveis,
                     List<Consulta> consultas) {
        super(email,nome,password,morada,dataNasc);
        if (imoveis==null) this.imoveis = new ArrayList<Imovel>();
        else this.imoveis = imoveis;
        if (consultas==null) this.consultas = new ArrayList<Consulta>();
        else this.consultas = consultas;
    }
    public Vendedor (Vendedor c) {
        super(c);
        this.imoveis = c.getImoveis();
        this.consultas = c.getConsultas();
    }
    public List<Imovel> getImoveis () {return imoveis;}
    public List<Consulta> getConsultas (){return consultas;}
    public void setImoveis (List<Imovel> imoveis) {this.imoveis = imoveis;}
    public void setConsultas (List<Consulta> consultas){this.consultas = consultas;}
    public Vendedor clone(){return new Vendedor(this);}
    
    
    public void addImovel(Imovel im) {
        this.imoveis.add(im.clone());
    }
    public void addConsulta(String idIm) {
        for (Consulta c : consultas) {
            if (c.getIdImo().equals(idIm)) {
                c.addConsulta();
                return;
            }
        }
        consultas.add(new Consulta(super.getEmail(),idIm));
    }
    public void setEstadoImovel (String id, String estado) {
        for (Imovel l: imoveis){
            if (l.getId().equals(id)) {
                l.setEstado(estado);
                return;
            }
        }
    }
}
