import java.io.Serializable;

public abstract class Imovel implements Serializable
{
    private String rua;
    private double precoPed;
    private double precoMin;
    private String estado;
    private String id;
    public Imovel () {
        this.rua = "n/a";
        this.precoPed = 0.0;
        this.precoMin = 0.0;
        this.estado = "n/a";
        this.id = "n/a";
    }
    public Imovel (String rua, double precoPed, double precoMin, String estado, String id) {
        this.rua = rua;
        this.precoPed = precoPed;
        this.precoMin = precoMin;
        this.estado = estado;
        this.id = id;
    }
    public Imovel (Imovel i) {
        this.rua = i.getRua ();
        this.precoPed = i.getPrecoPed ();
        this.precoMin = i.precoMin;
        this.estado = i.getEstado();
        this.id = i.getId();
    }
    public String getRua () {return rua;}
    public double getPrecoPed () {return precoPed;}
    public String getEstado () {return estado;}
    public String getId () {return id;}
    public void setRua (String rua) {this.rua = rua;}
    public void setPrecoPed (double precoP) {this.precoPed = precoP;}
    public void setEstado (String estado) {this.estado = estado;}
    public void setId (String id) {this.id = id;}
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Imovel o = (Imovel) obj;
        return ((o.getRua()==rua) && (o.getPrecoPed()==precoPed) && (o.precoMin==precoMin) && 
                (o.getEstado().equals(estado)) && (o.getId().equals(id)));
    }
    public abstract Imovel clone();
}
