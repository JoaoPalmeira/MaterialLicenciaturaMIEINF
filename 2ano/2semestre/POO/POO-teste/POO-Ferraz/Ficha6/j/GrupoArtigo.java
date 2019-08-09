import java.util.*;

public class GrupoArtigo {
    private Set<Long> g_artigo;
    private Map< Long, Revisao> g_revisao;
    private Map< Long, Contribuidor> g_contribuidor;
    private long nartigos;

    public GrupoArtigo(){
      this.g_artigo=new TreeSet();
      this.g_revisao= new HashMap();
      this.g_contribuidor=new HashMap();
      this.nartigos=0;
  }
  
  public GrupoArtigo(Set<Long> ga, Map<Long, Revisao> gr, Map<Long, Contribuidor> gc, long artigos){
      this.g_artigo=new TreeSet();
      for(Long g : ga){
          this.g_artigo.add(g);
      }
      this.g_revisao=new HashMap();
      for(Long p: gr.keySet()){
          this.g_revisao.put(p,gr.get(p));
       }
      this.g_contribuidor=new HashMap();
      for(Long p: gc.keySet()){
          this.g_contribuidor.put(p,gc.get(p));
       }
      this.nartigos=artigos;
  }
  
  public GrupoArtigo(GrupoArtigo ga){
      this.g_artigo= ga.getg_artigo();
      this.g_revisao= ga.getg_revisao();
      this.g_contribuidor= ga.getg_contribuidor();
      this.nartigos= ga.getNartigos();
  }

    public Set<Long> getg_artigo() {
        Set<Long> aux = new TreeSet();
        for(Long g : this.g_artigo){
          aux.add(g);
      }
        return aux;
    }
    
  public Map<Long,Revisao> getg_revisao(){
      Map<Long,Revisao> copia = new HashMap();
      for(Map.Entry<Long,Revisao> j : this.g_revisao.entrySet()){
                    copia.put(j.getKey(),j.getValue().clone());
                }
      return copia;
  }
  
    public Map<Long,Contribuidor> getg_contribuidor(){
      Map<Long,Contribuidor> copia = new HashMap();
      for(Map.Entry<Long,Contribuidor> j : this.g_contribuidor.entrySet()){
                    copia.put(j.getKey(),j.getValue().clone());
                }
      return copia;
  }


    public long getNartigos() {
        return this.nartigos;
    }

    public void setg_revisao(Map<Long,Revisao> grupo) {
        this.g_revisao.clear();
        for(Map.Entry<Long,Revisao> j : grupo.entrySet()){
                    g_revisao.put(j.getKey(),j.getValue().clone());
        }
    }
    
    public void setg_contribuidor(Map<Long,Contribuidor> grupo) {
        this.g_contribuidor.clear();
        for(Map.Entry<Long,Contribuidor> j : grupo.entrySet()){
                    g_contribuidor.put(j.getKey(),j.getValue().clone());
        }
    }

    public void setNartigos(long nartigos) {
        this.nartigos = nartigos;
    }
    
    public void addg_titulo(Long id){
        this.g_artigo.add(id);
    }
    
    public void addg_revisao(Long id,Revisao r){
        
        this.g_revisao.put(id, r.clone());
    }
    
    public void addg_contribuidor(Long id_revisao,Contribuidor c){
        
        this.g_contribuidor.put(id_revisao, c.clone());
    }
    
    public void addNumero(Long i){
        this.nartigos = (this.nartigos) + i;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.g_revisao);
        hash = 37 * hash + Objects.hashCode(this.g_contribuidor);
        hash = 37 * hash + (int) (this.nartigos ^ (this.nartigos >>> 32));
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
        final GrupoArtigo other = (GrupoArtigo) obj;
        if (this.nartigos != other.nartigos) {
            return false;
        }
        if (!Objects.equals(this.g_revisao, other.g_revisao)) {
            return false;
        }
        if (!Objects.equals(this.g_contribuidor, other.g_contribuidor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoArtigo{" + "g_revisao=" + g_revisao + ", g_contribuidor=" + g_contribuidor + ", nartigos=" + nartigos + '}';
    }

   
  
  public GrupoArtigo clone(){
      return new GrupoArtigo(this);
  }
    
}
