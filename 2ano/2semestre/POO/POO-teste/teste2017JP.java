//TESTE 2017
//1
public class Faixa {
private String nome;
private String autor;
private double duracao;
private int classificacao;
private ArrayList <String > letra; // letra da m´usica
private int numeroVezesTocada; // n´umero de vezes que foi tocada
private LocalDateTime ultimaVez; // regista quando foi tocada pela ´ultima vez
...
}
//a
public Faixa(Faixa f){
	this.nome=f.getNome();
	this.autor=g.getAutor();
	this.duracao=f.getDuracao();
	this.classificacao=f.getClassificacao();
	this.letra=f.getLetra();
	this.numeroVezesTocada=f.getNumeroVezesTocada();
	this.ultimaVez=f.getUltimaVez();
}

//b
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

//c
public int compareTo(Faixa f){
	int numV = f.getNumeroVezesTocada();
	int res;	
	if(this.numeroVezesTocada==numV){
		res = 0;
	}
	else if (numeroVezesTocada>numV){
		res = 1;
	}
	else res = -1;
	return res;
}

//d - pesquisar isBefore


//2
public class PlayList {
private String nome;
private Map <String , List <Faixa >> musicas;
//a
public List<Faixa> getFaixas(String autor) throws AutorInexistenteException{
	List<Faixa> res = new ArrayList<>();
	if(musicas.containsKey(autor)){
		res.addAll(this.musicas.get(autor));
	}
	else {throw new AutorInexistenteException("AUTORZECO NUM EXISTE .|.");}
	return res;
}

//b
public double tempoTotal(String autor) throws AutorInexistenteException{
	List<Faixa> res = new ArrayList<>();
	double total;
	if(musicas.containsKey(autor)){
		res.addAll(this.musicas.get(autor));
		for(Faixa i : res){
			total += i.getDuracao();
		}
	}
	else{
		throw new AutorInexistenteException("O AUTORZECO NUM EXISTE PAH")
	}
	return total;
}

//3
//a
public List<Faixa> todasAsFaixas(){
	List<Faixa> res = new ArrayList<>();
	//verificamos se esta vazia a lista de musicas
	if(this.musicas.isEmpty()){}
	else{
		for(List<Faixa> faixas : this.musicas.getValues()){
			for(Faixa i : faixas){
				res.add(i.clone());
			}
		}
	}
	return res;
}

//b
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

//c
public class Faixa implements Playable, Serializable{
	(...)

	public void play(){
		System.audio.play(letra);
		
	}

	(...)
}

//4
public class MusicaComVideo extends Faixa implements Playable, Serializable{

	private String video; //a

	public MusicaComVideo(String nome, String autor, double duracao, int classificacao,
						  ArrayList<String> letra, int num, LocalDate data, String v){

		super(nome,autor,duracao,classificacao,letra,num,data);
		this.video = new ArrayList<>();                   // b
		this.video = v;
		
	}
	(...)

	public void play(){
		super.play();
		System.audio.play(video);
	}
	(...)
}

//5
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















