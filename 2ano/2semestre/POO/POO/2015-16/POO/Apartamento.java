public class Apartamento extends Imovel implements Habitavel
{
    /*• o tipo (Simples, Duplex, Triplex)*/
    private String tipo;
    /*• a área total*/
    private double areaT;
    /* • o número de quartos e WCs*/
    private int nQuartos, nWc;
    /* • o número da porta e o andar*/
    private int nPorta, andar;
    /* • se possui, ou não, garagem*/
    private boolean garagem;
    
    public Apartamento () {
        super();
        this.tipo = "n/a";
        this.areaT = 0.0;
        this.nQuartos = 0;
        this.nWc = 0;
        this.nPorta = 0;
        this.andar = 0;
        this.garagem = false;
    }
    public Apartamento (String rua,
                        double precoPed,
                        double precoMin,
                        String estado,
                        String id,
                        String tipo,
                        double areaT, 
                        int nQuatos, 
                        int nWc, 
                        int nPorta, 
                        int andar, 
                        boolean garagem) {
        super(rua,precoPed,precoMin,estado,id);
        this.tipo = tipo;
        this.areaT = areaT;
        this.nQuartos = nQuartos;
        this.nWc = nWc;
        this.nPorta = nPorta;
        this.andar = andar;
        this.garagem = garagem;
    }
    public Apartamento (Apartamento a) {
        super(a);
        this.tipo = a.getTipo();
        this.areaT = a.getAreaT();
        this.nQuartos = a.getNQuartos();
        this.nWc = a.getNWc();
        this.nPorta = a.getNPorta();
        this.andar = a.getAndar();
        this.garagem = a.getGaragem();
    }
    public String getTipo () {return tipo;}
    public double getAreaT () {return areaT;}
    public int getNQuartos () {return nQuartos;}
    public int getNWc () {return nWc;}
    public int getNPorta () {return nPorta;}
    public int getAndar () {return andar;}
    public boolean getGaragem () {return garagem;}
    public void setTipo (String tipo) {this.tipo = tipo;}
    public void setAreaT (double areaT) {this.areaT = areaT;}
    public void setNQuartos (int nQuartos) {this.nQuartos = nQuartos;}
    public void setNWc (int nWc) {this.nWc = nWc;}
    public void setNPorta (int nPorta) {this.nPorta = nPorta;}
    public void setAndar (int andar) {this.andar = andar;}
    public void setGaragem (boolean garagem) {this.garagem = garagem;}
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Apartamento o = (Apartamento) obj;
        return super.equals(o) && (o.getTipo().equals(tipo) && o.getAreaT()==areaT && o.getNQuartos()==nQuartos &&
                                   o.getNWc()==nWc && o.getNPorta()==nPorta && o.getAndar()==andar && o.getGaragem()==garagem);
    }
    public Apartamento clone(){return new Apartamento(this);}
}
