import java.util.List;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
/**
 * Write a description of class TesteListaPaises here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TesteListaPaises
{
    public static void main(String[] args){
       ListaPaises p= new ListaPaises();
       ListaPaises pa= new ListaPaises();
       
       ArrayList<FichaPais> paa= new ArrayList<FichaPais>();
       ArrayList<String> paaa= new ArrayList<String>();
       p.adicionar("Portugal","Europa",1000000); 
       p.adicionar("Espanha","Europa",4700000);
       p.adicionar("França","Europa",6600000);
       p.adicionar("Holanda","Europa",1700000);
       p.adicionar("Brasil","America do Sul",20000000);
       pa.adicionar("Portugal","Europa",1000000); 
       pa.adicionar("Espanha","Europa",4700000);
       pa.adicionar("França","Europa",6600000);
       pa.adicionar("Holanda","Europa",1700000);
       pa.adicionar("Brasil","America do Sul",20000000);
       paa.add(new FichaPais("Japao","Asia",127000000));
       paa.add(new FichaPais("Brasil","America do Sul",21000000));
       pa.actualiza(paa);
       paaa.add("Portugal");
       paaa.add("França");
       paaa.add("Espanha");
       pa.remove(paaa);
       int n= p.numPaises();
       int e= p.numPaises("Europa");
       int f= p.numPaisesF("Europa");
       FichaPais g = p.getFicha("Portugal");
       //FichaPais h= p.getFichaF("Portugal");
       List<String> nomespaises = new ArrayList<String>();
       nomespaises=p.nomesPaises(1000000);
       List<String> nomespaisesf = new ArrayList<String>();
       nomespaisesf=p.nomesPaisesF(1000000);
       List<String> nomescontinentes = new ArrayList<String>();
       nomescontinentes=p.nomesContinentes(1000000);
       List<String> nomescontinentesf = new ArrayList<String>();
       nomescontinentesf=p.nomesContinentesF(1000000);
       double soma;
       soma=p.somatorio("Europa");
       double somaf;
       somaf=p.somatorio("Europa");
       
      System.out.println("Nº total de Países: "+n);
      System.out.println("-----Iteradores externos--------");
      System.out.println("Nº Países europeus: "+e);
      System.out.println("Ficha->: "+g);
      System.out.println("Nomes: "+nomespaises);
      System.out.println("Continentes: "+nomescontinentes);
      System.out.println("Soma população: "+soma);
      System.out.println("População atualizada: "+pa.getPaises());
      System.out.println("Países ñ removidos: "+pa.getPaises());
      System.out.println("--------------------------------");
      System.out.println("-----Iteradores internos--------");
      System.out.println("Nº Países europeus: "+f);
      //System.out.println("Ficha->: "+h);
      System.out.println("Nomes: "+nomespaisesf);
      System.out.println("Continentes: "+nomescontinentesf);
      System.out.println("Soma população: "+somaf);
    }
}
