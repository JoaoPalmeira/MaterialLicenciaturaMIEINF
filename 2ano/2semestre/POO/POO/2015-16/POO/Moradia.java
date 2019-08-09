public class Moradia extends Imovel implements Habitavel
{
    /*isolada/geminada/banda/gaveto*/
    private String tipo;
    /*área de implantação*/
    private double areaIm;
    /*área total de cobertura*/
    private double areaT;
    /*área do terreno envolvente*/
    private double areaTerrEnv;
    /*número de quartos e de WCs*/
    private int nQuartos, nWc;
    /*número da porta*/
    private int nPorta;

    public Moradia() {
        super();
        this.tipo = "n/a";
        this.areaIm = 0.0;
        this.areaT = 0.0;
        this.areaTerrEnv = 0.0;
        this.nQuartos = 0;
        this.nWc = 0;
        this.nPorta = 0;
    }
    public Moradia(String rua,
                   double precoPed,
                   double precoMin,
                   String estado,
                   String id,
                   String tipo,
                   double areaIm,
                   double areaT,
                   double areaTerrEnv,
                   int nQuartos,
                   int nWc,
                   int nPorta) {
        super(rua,precoPed,precoMin,estado,id);
        this.tipo = tipo;
        this.areaIm = areaIm;
        this.areaT = areaT;
        this.areaTerrEnv = areaTerrEnv;
        this.nQuartos = nQuartos;
        this.nWc = nWc;
        this.nPorta = nPorta;
    }
    public Moradia(Moradia c) {
        super(c);
        this.tipo = c.getTipo();
        this.areaIm = c.getAreaIm();
        this.areaT = c.getAreaT();
        this.areaTerrEnv = c.getAreaTerrEnv();
        this.nQuartos = c.getNQuartos();
        this.nWc = c.getNWc();
        this.nPorta = c.getNPorta();
    }
    public String getTipo () {return tipo;}
    public double getAreaIm () {return areaIm;}
    public double getAreaT () {return areaT;}
    public double getAreaTerrEnv () {return areaTerrEnv;}
    public int getNQuartos () {return nQuartos;}
    public int getNWc () {return nWc;}
    public int getNPorta () {return nPorta;}
    public void setTipo (String tipo) {this.tipo = tipo;}
    public void setAreaIm (double area) {this.areaIm = area;}
    public void setAreaT (double area) {this.areaT = area;}
    public void setAreaTerrEnv (double area) {this.areaTerrEnv = area;}
    public void setNQuartos (int nQuartos) {this.nQuartos = nQuartos;}
    public void setNWc (int nWc) {this.nWc = nWc;}
    public void setNPorta (int nPorta) {this.nPorta = nPorta;}
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Moradia o = (Moradia) obj;
        return super.equals(o) && (o.getTipo().equals(tipo) && o.getAreaIm()==areaIm && o.getAreaT()==areaT && o.getAreaTerrEnv()==areaTerrEnv && 
                                   o.getNQuartos()==nQuartos &&o.getNWc()==nWc && o.getNPorta()==nPorta);
    }
    public Moradia clone(){return new Moradia(this);}
}
