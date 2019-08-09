import java.util.Objects;

public class Contribuidor {
    private long idcontribuidor;
    private String nomecontribuidor;
    
    public Contribuidor(){
        this.idcontribuidor=0;
        this.nomecontribuidor=null;
    }
    public Contribuidor(long idcontribuidor, String nomecontribuidor,int contribuicoes ){
        this.idcontribuidor=idcontribuidor;
        this.nomecontribuidor=nomecontribuidor;
    }
    public Contribuidor(Contribuidor c){
        this.idcontribuidor=c.getIdcontribuidor();
        this.nomecontribuidor=c.getNomecontribuidor();
    }

    public long getIdcontribuidor() {
        return idcontribuidor;
    }

    public void setIdcontribuidor(long idcontribuidor) {
        this.idcontribuidor = idcontribuidor;
    }

    public String getNomecontribuidor() {
        return nomecontribuidor;
    }

    public void setNomecontribuidor(String nomecontribuidor) {
        this.nomecontribuidor = nomecontribuidor;
    }
    
    @Override

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contribuidor other = (Contribuidor) obj;
        if (this.idcontribuidor != other.idcontribuidor) {
            return false;
        }
        if (!Objects.equals(this.nomecontribuidor, other.nomecontribuidor)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Contribuidor{" + "idcontribuidor=" + idcontribuidor + ", nomecontribuidor=" + nomecontribuidor + '}';
    }
    
    public Contribuidor clone(){
        return new Contribuidor(this);
    }

   
}