import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class Consulta
{
    private String email;
    private String idImo;
    private List<Date> dataCons;
    private int nCons;
    public Consulta(){
        this.email="n/a";
        this.idImo="n/a";
        this.dataCons = new ArrayList<Date>();
        this.nCons=0;
    }
    public Consulta(String email,String idImo){
        this.email=email;
        this.idImo=idImo;
        this.dataCons = new ArrayList<Date>();
        this.dataCons.add(new Date());
        this.nCons=1;
    }
    public Consulta(Consulta c){
        this.email=c.getEmail();
        this.idImo=c.getIdImo();
        this.dataCons=c.getDataCons();
        this.nCons=c.getNCons();
    }
    public void setEmail(String email){this.email=email;}
    public void setIdImo(String id){this.idImo=id;}
    public void setDataCons(List<Date> datas){this.dataCons=datas;}
    public void setNCons(int n){this.nCons=n;}
    public String getEmail(){return email;}
    public String getIdImo(){return idImo;}
    public List<Date> getDataCons(){return dataCons;}
    public int getNCons(){return nCons;}
    public Consulta clone() {return new Consulta(this);}
    
    
    public void addConsulta(){
        if (this.nCons==0) {
            this.dataCons = new ArrayList<Date>();
        }
        this.nCons+=1;
        this.dataCons.add(new Date());
    }
}
