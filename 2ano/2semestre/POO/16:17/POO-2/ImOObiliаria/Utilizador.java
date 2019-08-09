import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Calendar;
public abstract class Utilizador implements Serializable
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private GregorianCalendar dataNascimento;
    
    public Utilizador()
    {
        this.email = "";
        this.nome = "";
        this.password = "";
        this.morada = "";
        this.dataNascimento = new GregorianCalendar(1900,1,1);
    }
    
    public Utilizador(String email, String nome, String password, 
                    String morada, GregorianCalendar dataNascimento)
    {
        this.email=email;
        this.nome=nome;
        this.password=password;
        this.morada=morada;
        this.dataNascimento=dataNascimento;
    }
    
    public Utilizador(Utilizador u2)
    {
        this(u2.getEmail(),u2.getNome(),u2.getPassword(),u2.getMorada(),u2.getDataNascimento());
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public String getNome()
    {
        return this.nome;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    public String getMorada()
    {
        return this.morada;
    }
    
    public GregorianCalendar getDataNascimento()
    {
        return dataNascimento;
    }
    
    public void setEmail(String email)
    {
        this.email=email;
    }
    
    public void setNome(String nome)
    {
        this.nome=nome;
    }
    
    public void setPassword(String password)
    {
        this.password=password;
    }
    
    public void setMorada(String morada)
    {
        this.morada=morada;
    }
    
    public void setDataNascimento(GregorianCalendar dataNascimento)
    {
        this.dataNascimento=dataNascimento;
    }
    
    public abstract Utilizador clone();

    public boolean equals(Object o)
    {
       if (this == o) return true;
       if ((o==null)||(o.getClass()!=this.getClass())) return false;
       else
       {
           Utilizador u = (Utilizador) o;
           return (this.email.equals(u.getEmail()) &&
                   this.nome.equals(u.getNome()) &&
                   this.password.equals(u.getPassword()) &&
                   this.morada.equals(u.getMorada()) &&
                   this.dataNascimento.equals(u.getDataNascimento()));
       }
    }
    
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("\nUtilizador\n");
        s.append("Email: ");
        s.append(this.email+"\n");
        s.append("Nome: ");
        s.append(this.nome+"\n");
        s.append("Password: ");
        s.append(this.password+"\n");
        s.append("Morada: ");
        s.append(this.morada+"\n");
        s.append("Data de Nasciemnto: ");
        s.append(this.dataNascimento.get(Calendar.YEAR)+"/"+(this.dataNascimento.get(Calendar.MONTH)+1)+"/"+this.dataNascimento.get(Calendar.DAY_OF_MONTH)+"\n");
        return s.toString();
    }
}