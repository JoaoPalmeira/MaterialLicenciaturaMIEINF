import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Playlist
{
    private List<Faixa> faixas;
    
    public Playlist()
    {
        this.faixas = new ArrayList<Faixa>();
    }
    
    public Playlist(ArrayList<Faixa> f)
    {
        //this.faixas = new ArrayList<Faixa>();
        setFaixas(f);
    }
    
    public Playlist(Playlist p)
    {
        setFaixas(p.getFaixas());
    }
    
    public List<Faixa> getFaixas()
    {
        List <Faixa> res = new ArrayList<Faixa>();
        for(Faixa f:this.faixas)
        res.add(f.clone());
        return res;
    }
    
    public List<Faixa> getFaixas2()
    {
        List <Faixa> res=new ArrayList<Faixa>();
        Iterator<Faixa> it = this.faixas.iterator();
        while (it.hasNext())
        {
            Faixa f = it.next();
            res.add(f.clone());
        }
        return res;
    }
    
    public List<Faixa> getFaixas3()
    {
        return this.faixas.stream()
                   .map (Faixa::clone)
                   .collect(Collectors.toList());
    }
    
    public void setFaixas(List<Faixa> f)
    {
        this.faixas.clear();
        this.faixas = f.stream()
                      .map(Faixa::clone)
                      .collect(Collectors.toList());
    }
    
    public boolean equals (Object o)
    {
        if (o==this) return true;
        if (o==null || o.getClass() != this.getClass()) return false;
        else 
        {
            Playlist pl = (Playlist) o;
            return pl.getFaixas().equals(this.faixas);
        }
    }
    
    public Playlist clone() 
    {
        return new Playlist(this);
    }
    
    public int numFaixas()
    {
        return faixas.size();
    }
    
    public void addFaixa(Faixa f)
    {
        this.faixas.add(f.clone());
    }
    
    public void removeFaixa(Faixa m)
    {
        this.faixas.remove(m.clone());
    }
    
    public void adicionar(List<Faixa> fs)
    {
        fs.forEach(f -> { this.faixas.add(f.clone());});
    }
    
    public void adicionar(List<Faixa> faixas)
    {
        
    }
    
    public int classificacaoSuperior(Faixa f)
    {
        return (int) faixas.stream()
                     .filter(m->m.getClassificacao() > f.getClassificacao())
                     .count();
    }
    
    public boolean duracaoSuperior(double d)
    {
        if (this.faixas.isEmpty()) return false;
        else 
        {
            int i;
            for(i=0;!this.faixas.isEmpty();i++)
            {
                if (this.faixas.get(i).getDuracao()>d) return true;
            }
            return false;
        }
    }
    
    public List<Faixa> getCopiaFaixa(int n)
    {
        return this.faixas.stream()
        .map(f->new Faixa(f.getNome(),f.getAutor(),f.getDuracao(),n))
        .collect(Collectors.toList());
    }
    
    public double diracaoTotalF()
    {
        return this.faixas.stream()
        .mapToDouble(Faixa :: getDuracao)
        .sum();
    }
}