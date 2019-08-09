import java.util.GregorianCalendar;
import java.io.Serializable;
import java.util.Calendar;
public class Consulta implements Serializable
{
    private GregorianCalendar data;
    private String email;
    private Imovel imovel;
    
    public Consulta()
    {
        data = new GregorianCalendar();
        email = "";
        //imovel = new Imovel();
    }
    
    public Consulta(String email, Imovel imovel)
    {
        this.data = new GregorianCalendar();
        this.email = email;
        this.imovel = imovel;
    }
    
    public Consulta(GregorianCalendar data, String email, Imovel imovel)
    {
        this.data = data;
        this.email = email;
        this.imovel = imovel;
    }
    
    public Consulta(Consulta c2)
    {
        this(c2.getData(),c2.getEmail(),c2.getImovel());
    }
    
    public GregorianCalendar getData()
    {
        return data;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public Imovel getImovel()
    {
        return this.imovel.clone();
    }
    
    public Consulta clone()
    {
        return new Consulta(this);
    }
    
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        if(o == null || o.getClass() != this.getClass())
        {
            return false;
        }
        Consulta c = (Consulta) o;
        return (this.data.equals(c.getData()) && this.email.equals(c.getEmail()) && this.imovel.equals(c.getImovel()));
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("\nData: ");
        s.append(this.data.get(Calendar.YEAR)+"/"+(this.data.get(Calendar.MONTH)+1)+"/"+this.data.get(Calendar.DAY_OF_MONTH)+" "+this.data.get(Calendar.HOUR)+":"+this.data.get(Calendar.MINUTE)+"h\n");
        s.append("Email: ");
        s.append(this.email+"\n");
        s.append("Imovel: ");
        s.append(this.imovel+"\n");
        return s.toString();
    }
    

}
