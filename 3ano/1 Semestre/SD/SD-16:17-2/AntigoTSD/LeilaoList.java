import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LeilaoList{

	static List<Leilao> lista;
	
	public LeilaoList() 
	{
		lista = new ArrayList<Leilao>();
	}
	public static synchronized void adiciona(Leilao l)
	{
		lista.add(l);
	}
	public static int tamanho()
	{
		return lista.size();
	}
	public static synchronized void removeLeilao(Leilao l)
	{
		lista.remove(l);
	}
	public static String imprimeLista()
	{
		String s = "";
		if (lista.size() == 0)
		{ s = s + "Vendedor não fez leiloação";}
		else {
			for (Leilao l : lista)
			{
				s = s + l.item.getDescricao();
			}
		}
		return s;
	}
}
