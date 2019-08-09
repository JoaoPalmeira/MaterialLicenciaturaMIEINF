public class Loja extends Imovel
{
    /*• a área total*/
    private double areaT;
    /*• se possui, ou não, WCs*/
    private boolean wc;
    /*• tipo de negócio viável*/
    private String tipoNegocioViavel;
    /*• o número da porta*/
    private int nPorta;
    
    public Loja () {
        super();
        this.areaT = 0.0;
        this.wc = false;
        this.tipoNegocioViavel = "n/a";
        this.nPorta = 0;
    }
    public Loja (String rua,
                 double precoPed,
                 double precoMin,
                 String estado,
                 String id,
                 double area,
                 boolean wc, 
                 String tipoNegocioViavel, 
                 int nPorta) {
        super(rua,precoPed,precoMin,estado,id);
        this.areaT = area;
        this.wc = wc;
        this.tipoNegocioViavel = tipoNegocioViavel;
        this.nPorta = nPorta;
    }
    public Loja (Loja c) {
        super(c);
        this.areaT = c.getAreaT();
        this.wc = c.getWc();
        this.tipoNegocioViavel = c.getTipoNegocioViavel();
        this.nPorta = c.getNPorta();
    }
    public double getAreaT () {return areaT;}
    public boolean getWc () {return wc;}
    public String getTipoNegocioViavel () {return tipoNegocioViavel;}
    public int getNPorta () {return nPorta;}
    public void setAreaT (double area) {this.areaT = area;}
    public void setWc (boolean wc) {this.wc = wc;}
    public void setTipoNegocioViavel (String tipoNegocioViavel) {this.tipoNegocioViavel = tipoNegocioViavel;}
    public void setNPorta (int nPorta) {this.nPorta = nPorta;}
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Loja o = (Loja) obj;
        return super.equals(o) && (o.getAreaT()==areaT && o.getWc()==wc &&
                                   o.getTipoNegocioViavel().equals(tipoNegocioViavel) && o.getNPorta()==nPorta);
    }
    public Loja clone(){return new Loja(this);}
}
