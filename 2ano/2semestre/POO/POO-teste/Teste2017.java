//Parte 1

// 1

// a)

public Faixa(Faixa f){
	this.nome = f.getNome();
	this.autor = f.getAutor();
	this.duracao = f.getDuracao();
	this.classificacao = f.getClassificacao();
	this.letra = f.getLetra();
	this.numeroVezesTocada = f.getNumeroVezesTocada();
	this.ultimaVez = f.getUltimaVez();
}

// b)

public boolean equals(Object o){
	if(this == o){
		return true;
	}

	if(o == null || this.getClass() != o.getClass()){
		return false;
	}

	Faixa f = (Faixa) o;

	return( this.nome.equals(f.getNome()) &&
			this.autor.equals(f.getAutor()) &&
			this.duracao == f.getDuracao() &&
			this.classificacao == f.getClassificacao() &&
			this.letra.equals(f.getLetra()) &&
			this.numeroVezesTocada == f.getNumeroVezesTocada() &&
			this.ultimaVez == f.getUltimaVez());
}

// c)

public int ordena(Faixa f){
	int n = f.getNumeroVezesTocada();
	int res;
	if(this.numeroVezesTocada == n){
		res=0;
	}
	else{
		if(n < this.numeroVezesTocada){
			res=1;
		}
		else{
			res=-1;
		}
	}
	return res;
}

// d)

public class ComparatorData implements Comparator<Faixa>{

	int compare(Faixa f1, Faixa f2){
		
		return f1.getUltimaVez().compareTo(f2.getUltimaVez());
		
		/*if(f1.getUltimaVez().isBefore(f2.getUltimaVez())){ // Da das duas formas...
			return 1;
		}
		else{
			return -1;
		}*/
	}
}

// 2

// a)

// iteradores externos

public List<Faixa> getFaixas(String autor) throws AutorInexistenteException{
	List<Faixa> res = new ArrayList<>();

	if(this.musicas.containsKey(autor)){
		res.addAll(this.musicas.get(autor));
	}
	else{
		throw new AutorInexistenteException("Autor inexistente...");
	}
	return res;
}

// iteradores internos

public List<Faixa> getFaixas(String autor) throws AutorInexistenteException{

	if(this.musicas.containsKey(autor)){
		return this.musicas.get(autor).stream().map(Faixa :: clone).collect(collectors.toList());
	}
	else{
		throw new AutorInexistenteException("Autor inexistente...");
	}
}



// b)

// iteradores externos

public double tempoTotal(String autor) throws AutorInexistenteException{
	List<Faixa> faixas = new ArrayList<>();
	double total = 0;

	if(this.musicas.containsKey(autor)){
		faixas.addAll(this.musicas.get(autor));
		for(Faixa f : faixas){
			total += f.getDuracao();
		}
	}
	else{
		throw new AutorInexistenteException("Autor inexistente...");
	}
	return total;
}

// iteradores internos

public double tempoTotal(String autor) throws AutorInexistenteException{

	if(this.musicas.containsKey(autor)){		
		return this.musicas.get(autor).stream.mapToDouble(Faixa :: getDuracao).sum();
	}
	else{
		throw new AutorInexistenteException("Autor inexistente...");
	}
}



// Parte 2

// 3

// a)


public List<Faixa> todasAsFaixas(){
	List<Faixa> res = new ArrayList<>();

	if(!this.musicas.isEmpty()){
		for(List<Faixa> faixas : this.musicas.values()){
			for(Faixa f : faixas){
				res.add(f.clone());
			}
		}
	}
	return faixas;
}

// b)

// iterador externo;

public Map<Integer,List<Faixa>> faixasPorClass(){
	Map<Integer,List<Faixa>> res = new HashMap<>();
	List<Faixa> faixas = new ArrayList<>();
	List<Faixa> aux = new ArrayList<>();

	if(!this.musicas.isEmpty()){
		for(List<Faixa> fx : this.musicas.values()){
			for(Faixa f : fx){
				faixas.add(f.clone());  // todas as faixas da colecao musicas
			}
		}

		for(Faixa x : faixas){
			aux = new ArrayList<>();
			
			if(res.isEmpty()){
				aux.add(x.clone());
				res.put(x.getClassificacao(),aux);
			}			
			else{
				if(res.containsKey(x.getClassificacao())){
					aux.addAll(res.get(x.getClassificacao));
					aux.add(x.clone());
					res.put(x.getClassificacao(),aux);		
				}
				else{
					aux.add(x.clone());
					res.put(x.getClassificacao(),aux);
				}				
			}
		}
	}
	return res;
}

// iterador internos

public Map<Integer,List<Faixa>> faixasPorClass(){
	List<Faixa> faixas = new ArrayList<>();
	Map<Integer,List<Faixa>> res = new HashMap<>();

	for(List<Faixa> fx : this.musicas.values()){
		for(Faixa f : fx){
			faixas.add(f.clone());
		}
	}

	return res.stream().collect(collectors.groupingBy(Faixa :: getClassificacao));
}



// c)

public class Faixa implements Playable, Serializable{
	.
	.
	.

	public void play(){
		System.audio.play(letra);
		
	}

	.
	.
	.
}

// 4

// a) b) c)
public class MusicaComVideo extends Faixa implements Playable, Serializable{

	private String video; // a


	public MusicaComVideo(String nome, String autor, double duracao, int classificacao,
						  ArrayList<String> letra, int num, LocalDate data, String v){

		super(nome,autor,duracao,classificacao,letra,num,data);
		this.video = new ArrayList<>();                   // b
		this.video = v;
		
	}

	.
	.
	.

	public void play(){
		super.play();
		System.audio.play(video);
	}

	.
	.
	.
}

// Parte 3

// a) b) c)

public class AgenciaViagens{

	// a)

	private String nomeAgencia; // Lista de hoteis para que agencia trabalha

	private Map<Hotel,List<Integer>>  clientesHotel; // lista de nifs de clientes por codido de hotel

	// b)

	public void escreveFicheiroTxt(String nomeFicheiro, int tipo) throws IOException{
		PrintWritter fich = new PrintWritter(nomeFicheiro);
		
		try{
			if(this.clientesHotel.containsKey(tipo)){
				fich.println("----AgenciaViagens----");
				for(Hotel h : this.hoteis){
					if(h.getCodigo() == tipo){
						fich.println(h.toString());
						fich.flush();
						fich.close();
					}
				}
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	// c)

	public static AgenciaViagens carregaEstado(String nomeFicheiro) throws FileNotFoundException,
																		   IOException,
																		   ClassNotFoundException{	
		try{																   	
			FileInputStream fis = new FileInputStream(nomeFicheiro);
			ObjectInputStream ois = new ObjectInputStream(fis);
			AgenciaViagens av = (AgenciaViagens) ois.readObject();
			ois.close();
			return av;
		}
		catch(FileNotFoundException e| IOException e| ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
	}

	// d)

	/* 
			Teriamos que adicionar implementes Serializable na duas classes
	 */															   
}



























