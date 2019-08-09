import java.util.*;
public class Revisao {
    private String titulo,timestamp;
    private long idartigo, numpalavras, nbytes;

    public Revisao() {
        this.titulo = null;
        this.timestamp = null;
        this.idartigo =0;
        this.numpalavras =0;
        this.nbytes=0;
    }
    
    
    public Revisao(String titulo, String timestamp, long idartigo, long numpalavras, long bytes, Contribuidor contribuidor) {
        this.titulo = titulo;
        this.timestamp = timestamp;
        this.idartigo = idartigo;
        this.numpalavras = numpalavras;
        this.nbytes= bytes;
    }
    
    public Revisao(Revisao r) {
    this.titulo = r.getTitulo();
    this.timestamp =r.getTimestamp();
    this.idartigo =r.getIdartigo() ;
    this.numpalavras =r.getNumpalavras();
    this.nbytes=r.getNbytes();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public long getIdartigo() {
        return idartigo;
    }

    public void setIdartigo(long idartigo) {
        this.idartigo = idartigo;
    }

    public long getNumpalavras() {
        return numpalavras;
    }

    public void setNumpalavras(long numpalavras) {
        this.numpalavras = numpalavras;
    }

    public long getNbytes() {
        return nbytes;
    }

    public void setNbytes(long nbytes) {
        this.nbytes = nbytes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.titulo);
        hash = 11 * hash + Objects.hashCode(this.timestamp);
        hash = 11 * hash + (int) (this.idartigo ^ (this.idartigo >>> 32));
        hash = 11 * hash + (int) (this.numpalavras ^ (this.numpalavras >>> 32));
        hash = 11 * hash + (int) (this.nbytes ^ (this.nbytes >>> 32));
        return hash;
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
        final Revisao other = (Revisao) obj;
        if (this.idartigo != other.idartigo) {
            return false;
        }
        if (this.numpalavras != other.numpalavras) {
            return false;
        }
        if (this.nbytes != other.nbytes) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Revisao{" + "titulo=" + titulo + ", timestamp=" + timestamp + ", idartigo=" + idartigo + ", numpalavras=" + numpalavras + ", nbytes=" + nbytes + '}';
    }
    


    
    public Revisao clone(){
      return new Revisao(this);
    }  

}
