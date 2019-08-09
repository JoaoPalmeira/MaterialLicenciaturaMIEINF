import java.util.*;
import java.lang.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.locks.*;

class Leilao implements Comparable<Leilao> {
	public ReentrantLock lock;
	private int emEspera;
	private boolean disponivel;
	private boolean aberto;
	private Condition condicaoComprador;
	private Condition condicaoVendedor;
	private Integer identificacao;
	private Utilizador vendedor;
	private Utilizador comprador;
	private String descricao;
	private Float preco;
	private Set<Socket> sockets;

	public Leilao(Integer id,String descricao,Utilizador vendedor,Float preco){
		this.identificacao=id;
		this.descricao=descricao;
		this.vendedor=vendedor;
		this.comprador=vendedor;
		this.preco=preco;
		this.emEspera=0;
		this.disponivel=true;
		this.aberto=true;
		this.lock=new ReentrantLock();
		this.condicaoComprador=lock.newCondition();
		this.condicaoVendedor=lock.newCondition();
	}

	public String getDescricao(){
		return this.descricao;
	}

	public Utilizador getVendedor(){
		return this.vendedor;
	}

	public float getPreco(){
		return this.preco;
	} 

	public int getID(){
		return this.identificacao;
	}

	public Utilizador getComprador(){
		return this.comprador;
	}

	public int getIdentificacao(){
		return this.identificacao;
	}

	public void setID(int nr){
		this.identificacao=nr;
	}

	public boolean temPermissao(Utilizador vendedor){
		return this.vendedor.equals(vendedor);
	}
/*
   	public void stall() {
		System.out.println("\nPressione <ENTER> para continuar!");
		Scanner scin = new Scanner(System.in);
		scin.nextLine();
    }
*/
	public synchronized int novaLicitacao(Utilizador utilizador, float preco){
		if(preco<this.preco)
			return -1;
		else{
			this.comprador=utilizador;
			this.preco=preco;
			return 0;
		}
	}

	public void indicarFimLicitacao(){
		lock.lock();
			this.emEspera--;
			if(this.emEspera==0) condicaoVendedor.signalAll();
			this.disponivel=true;
			condicaoComprador.signalAll();
		lock.unlock();
	}

	public int esperarFimLicitacao(){
		lock.lock();
		if(this.aberto==false){
			lock.unlock();
			return 1;
		}
		else{
			try{
				this.emEspera++;
				while(this.disponivel==false){
					condicaoComprador.await();
				}
			}
			catch(Exception e){e.printStackTrace();}
			finally{
				this.disponivel=false;
				lock.unlock();
				return 0;
			}
		}
	}

	public void fimLeilao(){
		lock.lock();
		this.aberto=false;
		try{
			while(emEspera>0){
				condicaoVendedor.await();
			}
		}
		catch(Exception e){e.printStackTrace();}
		finally{
			lock.unlock();
		}
	}

	public int compareTo(Leilao l){
		return l.getIdentificacao()-this.identificacao;
	}
}

class Utilizador implements Comparable<Utilizador>{
	private PrintWriter pw;
	private String password;
	private String email;
	private int totalLeiloes;
	private int totalGanho;
	
	public Utilizador(String email,String password,PrintWriter pw){
		this.pw=pw;
		this.password=password;
		this.email=email;
		this.totalLeiloes=0;
		this.totalGanho=0;
	}

	public String getPassword(){
		return this.password;
	}

	public String getEmail(){
		return this.email;
	}

	public int compareTo(Utilizador u){
		return this.email.compareTo(u.getEmail());
	}

	public boolean equals(Object obj){
		if(this==obj) return true;
		if(this==null) return false;
		if(this.getClass()!=obj.getClass()) return false;
		Utilizador util=(Utilizador) obj;
		return this.email.equals(util.getEmail()) && this.password.equals(util.getPassword());
	}

	public int getTotalLeiloes(){
		return this.totalLeiloes;
	}

	public int getTotalGanho(){
		return this.totalGanho;
	}

	public void incrementartotalGanho(){
		this.totalGanho++;
	}

	public void incrementartotalLeiloes(){
		this.totalLeiloes++;
	}

	public void notificarUtilizador(String s){
		pw.println(s);
	}
}	

class BaseDados{
	private Map<String,Utilizador> utilizadores;
	private Map<Utilizador,Set<Leilao>> leiloesUtilizador;
	private List<Leilao> leiloes;
	private int totalLeiloes;

	public BaseDados(){
		this.utilizadores=new HashMap<>();
		this.leiloesUtilizador=new HashMap<>();
		this.leiloes=new ArrayList<>();
	}

	public synchronized int adicionarLeilao(Leilao l,Utilizador utilizador){
		this.leiloes.add(l);
		this.totalLeiloes++;
		int tamanho=leiloes.size();
		l.setID(this.totalLeiloes);
		if(leiloesUtilizador.containsKey(utilizador)){
			Set<Leilao> aux=leiloesUtilizador.get(utilizador);
			aux.add(l);
		}
		else{
			Set<Leilao> novo=new TreeSet<>();
			novo.add(l);
			leiloesUtilizador.put(utilizador,novo);
		}
		return totalLeiloes;
	}

	public synchronized boolean registaUtilizador(String email,String password,PrintWriter pw){
		Utilizador utilizador=new Utilizador(email,password,pw);
		if(utilizadores.containsKey(email)) return false;
		else{
			utilizadores.put(email,utilizador);
		}
		return true;
	}

	public Utilizador existeUtilizador(String email,String password){
		if((utilizadores.containsKey(email))==false) return null;
		else{
			Utilizador cli=utilizadores.get(email);
			if(cli.getPassword().equals(password)) return cli;
			else return null;
		}
	}

	public int totalUtilizadores(){
		return utilizadores.size();
	}

	public int getTotalLeiloes(){
		return this.totalLeiloes;
	}

	public Set<Leilao> getListagem(Utilizador utilizador){
		Set<Leilao> res=new TreeSet<>();
		if(leiloesUtilizador.containsKey(utilizador)){
			Set<Leilao> leiloes=leiloesUtilizador.get(utilizador);
			for(Leilao l:leiloes){
				res.add(l);
			}
		}
		else return null;
		return res;
	} 

	public List<Leilao> getLeiloes(){
		List<Leilao> res=new ArrayList<>();
		for(Leilao l:this.leiloes)
			res.add(l);
		return res;
	}

	public Leilao existeLeilao(int nrleilao){
		for(Leilao l:leiloes){
			if(l.getID()==nrleilao) return l;
		}
		return null;
	}

	public int licitacao(Utilizador utilizador,Float preco,Leilao l){
			if(leiloesUtilizador.containsKey(utilizador)){
				Set<Leilao> aux=leiloesUtilizador.get(utilizador);
				aux.add(l);
			}
			else{
				//adicionar novo utilizador
				Set<Leilao> novo=new TreeSet<>();
				novo.add(l);
				leiloesUtilizador.put(utilizador,novo);
			}
			int flag= l.novaLicitacao(utilizador,preco);
			return flag;
	}

	public synchronized boolean removerLeilao(Utilizador utilizador,Leilao l){
		boolean b=false;
		if(l.temPermissao(utilizador)){
			leiloes.remove(l);
			b=true;
		}
		return b;
	}

	public synchronized Set<Utilizador> getListaClientes(Leilao l){
		Set<Utilizador> licitadores=new TreeSet<>();
		for(Map.Entry<Utilizador,Set<Leilao>> entry:leiloesUtilizador.entrySet()){
			if(entry.getValue().contains(l)){
				licitadores.add(entry.getKey());
				entry.getValue().remove(l);
				leiloes.remove(l);
			}
		}
		return licitadores;
	}
}

public class eBai{

	public static void main(String []args) throws IOException{
		BaseDados bd=new BaseDados();
		ServerSocket ss= new ServerSocket(1234);
		int i=0;
		Socket s=null;
		while(true){
			ServerThread st=new ServerThread(ss.accept(),bd);
			st.start();
		}
	}	
}

class ServerThread extends Thread{
	private Socket s;
	private BaseDados b;

	public ServerThread(Socket s,BaseDados b){
		this.s=s;
		this.b=b;
	}

	public void run(){
		try{
			InputStream is=s.getInputStream();
			OutputStream os=s.getOutputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			PrintWriter pw=new PrintWriter(os,true);
			pw.println("Bem vindo ao eBai");
			pw.println("\n'!help' para ver comandos disponiveis\n");
			String str=null;
			while((str=br.readLine())!=null){
				//verificar comando introduzido
				StringTokenizer stemp = new StringTokenizer(str);
				ArrayList<String> tokens = new ArrayList<String>();
				String inCommand = stemp.nextToken();
				while(stemp.hasMoreTokens())
						tokens.add(stemp.nextToken());			
				boolean flag=false;
				switch (inCommand){
					case "!help":
						pw.println("registar [email] [password]");
						pw.println("login [email] [password]");
						break;
					case "registar":
						if(tokens.size()==2){
								flag=b.registaUtilizador(tokens.get(0),tokens.get(1),pw);
								if (flag==false)pw.println("Cliente já existente"); 
								else{
									pw.println("Novo utilizador registado com sucesso!");
								}
						}
						else pw.println("comando inválido");
						break;
					case "login":
						Utilizador utilizador=b.existeUtilizador(tokens.get(0),tokens.get(1));
						if(utilizador!=null){
							pw.println("Cliente "+ utilizador.getEmail() +" logado com sucesso!");
							this.loggedCliente(utilizador,br,pw);
						}
						else{
							pw.println("Utilizador não registado ...");
						}
						break;
					default:
						pw.println("Comando Inválido");

				}
			}
		}
		catch(Exception e){System.out.println("Exception a fechar conexão com o cliente...");};
	}

	public void loggedCliente(Utilizador utilizador,BufferedReader br,PrintWriter pw) throws IOException{
		Leilao leilao=null;
		String str=null;
		try{
			while((str=br.readLine())!=null){
				StringTokenizer stemp = new StringTokenizer(str);
				ArrayList<String> tokens = new ArrayList<String>();

				String inCommand = stemp.nextToken();
				while(stemp.hasMoreTokens())
					tokens.add(stemp.nextToken());
				switch(inCommand){
					case "!help":
						pw.println("leilao [preço] [descrição]");
						pw.println("listagem");
						pw.println("licitar [nr leilão] [preço]");
						pw.println("terminar [nr leilão]");
						pw.println("sair");
						break;
					case "leilao":
						int i=0;
						String descricao=new String();
						//verificar preço
						try{
							float preco=Float.parseFloat(tokens.get(0));
						}
						catch(NumberFormatException e){
							pw.println("Preço inválido...");
							break;
						}
						i=1;
						while(i<tokens.size()){
							descricao=descricao+tokens.get(i)+" ";
							i++;
						}
						leilao=new Leilao(0, descricao,utilizador,Float.parseFloat(tokens.get(0)));
						int nrleilao=b.adicionarLeilao(leilao,utilizador);
						pw.println("Leilao número "+ nrleilao+" adicionado com sucesso!");
						break;
					case "listagem":
						List<Leilao> listagemTotal=b.getLeiloes();
						for(Leilao l: listagemTotal){
							if(l.getVendedor().equals(utilizador)){
								pw.print("+ ");
								pw.println(l.getID() +" "+l.getPreco()+" "+l.getDescricao());
							}
							else{
								if(l.getComprador().equals(utilizador)){
									pw.print("* ");
									pw.println(l.getID() +" "+l.getPreco()+" "+l.getDescricao());
								}
								else{
									pw.print("- ");
									pw.println(l.getID() +" "+l.getPreco()+" "+l.getDescricao());
								}
							}
						}
						break;
					case "licitar":
						int queued=0;
						leilao=null;
						int flag=1;
						float preco=0;
						int nr=Integer.parseInt(tokens.get(0));
						try{
							preco=Float.parseFloat(tokens.get(1));
						}
						catch(NumberFormatException e){
							pw.println("Preço inválido...");
							break;
						}
						//verifica se leilao existe
						if((leilao=b.existeLeilao(nr))!=null){
							//aguarda fim de licitação do leilao leilao
							queued=leilao.esperarFimLicitacao();
							//verifica se vendedor ainda nao encerrou o leilao
							if(queued==0){
								flag=b.licitacao(utilizador,preco,leilao);
								//indica fim da sua licitacao
								leilao.indicarFimLicitacao();
								utilizador.incrementartotalLeiloes();
								if(flag==-1) pw.println("Preço inferior a preço base ...");
									else pw.println("Nova licitação no item "+nr+" efetuada com sucesso!");
							}
							//caso de o leilao estar encerrado
							else pw.println("Licitação encerrada...");
						}
						//caso o leilao nao exista
						else{
							pw.println("Licitação inexistente...");
						}

						break;
					case "terminar":
						//verificar se utilizador é vendedor
						Integer nrLeilao=0;
						try{
							nrLeilao=Integer.parseInt(tokens.get(0));
						}
						catch(NumberFormatException e){
							pw.println("Número de leilão inválido");
							break;
						}
						boolean terminado=false;
						Leilao l=b.existeLeilao(nrLeilao);
						if(l.temPermissao(utilizador)){
							if(l!=null){
								l.fimLeilao();
								terminado=b.removerLeilao(utilizador,l);
								if(terminado==true){
									Set<Utilizador> licitadores;
									licitadores=b.getListaClientes(l);
									Utilizador vencedor=l.getComprador();
									vencedor.incrementartotalGanho();
									for(Utilizador util:licitadores){
										util.notificarUtilizador("Leilão "+l.getID()+" terminado. O licitador "+vencedor.getEmail() +" venceu ao licitar " + l.getPreco() +" €.");
									}
								}
							}
							else pw.println("Número de leilão inválido...");
						}
						else pw.println("Não tem permissões para encerrar o leilão...");
						break;
					case "sair":
						pw.println("Obrigado por utilizar os serviços eBai. Participou em " + utilizador.getTotalLeiloes()+" e venceu "+utilizador.getTotalGanho()+".");
						break;
					default: pw.println("Comando inválido!");
				}
			}
		}
		catch(Exception e){System.out.println("Exception a fechar conexão com o cliente...");};
	}
}