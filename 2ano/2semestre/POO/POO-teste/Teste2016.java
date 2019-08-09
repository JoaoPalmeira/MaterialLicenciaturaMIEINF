// Teste 2016

// Parte 1

public class ListaEleitoral {
private String partidoPolitico;
private Set<Candidato> eleitos;
private List<Candidato> porEleger;
// ...
}

// a)

public ListaEleitoral(String partido, Collection<Candidato> candidatos){
	this.partidoPolitico = partido;
	this.eleitos = new TreeSet<>();
	this.porEleger = new ArryList<>();
	for(Candidato c : candidatos){
		this.porEleger.add(c.clone());
	}
}

// b)

public Candidato aEleger(){
	return this.porEleger.get(0).clone();
}

// c)

public void elege(){
	if(!this.porEleger.isEmpty()){
		this.eleitos.add(this.porEleger.get(0).clone());
		this.porEleger.remove(0);
	}
}

// d)

public void elege(int n){
	if(this.porEleger.size() >= n){
		int i;
		for(i=0; i<n ; i++){
			this.eleitos.add(aEleger());
			this.porEleger.remove(0); 
		}
	}
}

// e)

// iteradores

public Collection<Candidato> candidatos(){
	Collection<Candidato> res = new Collection<>();
	
	for(Iterator<Set<Candidato>> it1 = this.eleitos.iterator() ; it1.hasNext()){
		Candidato c = it1.next();
		res.add(it1);
	}
	
	for(Iterator<List<Candidato>> it2 = this.porEleger.iterator() ; it2.hasNext()){
		Candidato c = it2.next();
		res.add(it2);
	}

	return res;
}

// sem iteradores

public Collection<Candidato> candidatos(){
	Collection<Candidato> res = new Collection<>();
	
	for(Candidato c1 : this.eleitos){
		res.add(c1.clone());
	}
	
	for(Candidato c2 : this.porEleger){
		res.add(c2.clone());
	}
	
	return res;
}

// Parte 2

public class Candidato { ...
	public String getNome () {...} 
	public int getIdade () {...} ...
}


// a)

public TreeSet<Candidato> eleitos(){
	TreeSet<Candidato> res = new TreeSet<Candidato>();

	for(Candidato c : this.eleitos){
		res.add(c.clone());
	}
	return res;

}

// b) // Na classe Candidado !!!!!!!!!!!!!!!!

public int compareTo(Candidato c){
	return this.nome.compareTo(c.getNome());
}

// c)

public TreeSet<Candidato> eleitos(){
	TreeSet<Candidato> res = new TreeSet<>(new Comparator());

	for(Candidato c : this.eleitos){
		res.add(c.clone());
	}
	return res;
}


public class Comparator implements Comparator<Candidato>, Serializable{

	public compare(Candidato c1, Candidato c2){
		if(c1.getIdade() == c2.getIdade()){
			return (c1.getNome().compareTo(c2.getNome()));
		}
		else{
			return (c1.getIdade().compareTo(c2.getIdade()))
		}
	}
}


// Parte 3

// NAO SEIIIIIIIIIIIII

// Parte 4

// 4

public class ParqueComRecusados implements IParque{

	private Map<String, Set<String>> recusas;

	public ParqueComRecusados(){
		this.recusas = new HashMap<>();
	}

	public ParqueComRecusados(ParqueComRecusados pcr){
		this.recusas = pcr.getRecusas();
	}

	public void entra(String cartao, String matricula) throws SemPermissaoException{
		try{
			super.entra(cartao,matricula);
		}
		catch(SemPermissaoException e){
			if(recusas.containsKey(matricula)){
				this.recusas.get(matricula).add(cartao);
			}
			else{
				Set<String> aux = new TreeSet<>();
				aux.add(cartao);
				this.recusas.put(matricula,aux);
			}	
		}
	}

	public Map<String, Set<String>> getRecusas(){
		Map<String, Set<String>> res = new HashMap<>();

		for(Map.Entry<String,Set<String>> re : this.recusas.entrySet()){
			String key = re.getKey();
			Set<String> value = re.getValue();
			res.put(key,value);
		}
	}
	return res;
}

// 5

public double qtsKmsTotal(){
	double res = 0;

	for(Empregado e : this.empregados.values()){
		if(e.getClass().getSimpleName().equals("Motorista")){
			Motorista m = (Motorista) e;
			res += m.getNKms();
		}
	}
	return res;
}


// 6 

// Os iteradores internos sao mais eficientes, pois sao mais eficazes a executar 
// uma funçao pedida, praticos de utilizar, pela quantidade de linhas de codigo
// a escrever. 






















