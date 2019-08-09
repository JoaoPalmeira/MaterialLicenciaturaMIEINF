public class Terreno extends Imovel
{
    /*• área disponivel para construção*/
    private double areaConst;
    /*• apropriado para que tipo de construção(habitação ou constrção de armazéns)*/
    private String tipoConst;
    /*• Diâmetro das canalizações*/
    private double diametroCan;
    /*• kWh máximos suportados pela rede elétrica*/
    private double kWhSup;
    /*• se existe, ou não, acesso á rede de esgotos*/
    private boolean accRedeEsgotos;
    
    public Terreno () {
        super();
        this.areaConst = 0.0;
        this.tipoConst = "n/a";
        this.diametroCan = 0.0;
        this.kWhSup = 0.0;
        this.accRedeEsgotos = false;
    }
    public Terreno (String rua,
                    double precoPed,
                    double precoMin,
                    String estado,
                    String id,
                    double areaConst,
                    String tipoConst,
                    double diametroCan,
                    double kWhSup,
                    boolean accRedeEsgotos) {
        super(rua,precoPed,precoMin,estado,id);
        this.areaConst = areaConst;
        this.tipoConst = tipoConst;
        this.diametroCan = diametroCan;
        this.kWhSup = kWhSup;
        this.accRedeEsgotos = accRedeEsgotos;
    }
    public Terreno (Terreno c) {
        super(c);
        this.areaConst = c.getAreaConst();
        this.tipoConst = c.getTipoConst();
        this.diametroCan = c.getDiametroCan();
        this.kWhSup = c.getKWhSup();
        this.accRedeEsgotos = c.getAccRedeEsgotos();
    }
    public double getAreaConst () {return areaConst;}
    public String getTipoConst () {return tipoConst;}
    public double getDiametroCan () {return diametroCan;}
    public double getKWhSup () {return kWhSup;}
    public boolean getAccRedeEsgotos () {return accRedeEsgotos;}
    public void SetAreaConst (double area) {this.areaConst = area;}
    public void setTipoConst (String tipo) {this.tipoConst = tipo;}
    public void setDiametroCan (double diametroCan) {this.diametroCan = diametroCan;}
    public void setKWhSup (double kWhSup) {this.kWhSup = kWhSup;}
    public void setAccRedeEsgotos (boolean accRedeEsgotos) {this.accRedeEsgotos = accRedeEsgotos;}
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Terreno o = (Terreno) obj;
        return super.equals(o) && (o.getAreaConst()==areaConst && o.getTipoConst().equals(tipoConst) && o.getDiametroCan()==diametroCan && 
                                   o.getKWhSup()==kWhSup &&o.getAccRedeEsgotos()==accRedeEsgotos);
    }
    public Terreno clone(){return new Terreno(this);}
}
