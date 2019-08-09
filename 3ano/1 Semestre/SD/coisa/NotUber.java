import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class Cliente {
	public String username;
	public boolean pedidopendente;

	public Cliente(String username) {
		this.username = username;
		this.pedidopendente=false;
	}
}

class Condutor extends Cliente {
	public String modelo;
	public String matricula;

	public Condutor (String username,String modelo, String matricula) {
		super(username);
		this.modelo = modelo;
		this.matricula = matricula;
	}
}

class Pedido {
	public int xstart;
	public int ystart;
	public int xend;
	public int yend;
	public Cliente cli;
	public ReentrantLock lock;
	private Condition driverReady;
	private boolean driver;
	private Condition precoReady;
	private double custo;

	public Pedido(int xstart, int ystart, int xend, int yend, Cliente cli) {
		this.xstart = xstart;
		this.ystart = ystart;
		this.xend = xend;
		this.yend = yend;
		this.cli = cli;
		this.lock = new ReentrantLock();
		this.driverReady = lock.newCondition();
		this.driver=false;
		this.precoReady = lock.newCondition();
		this.custo=-1.f;
	}

	public void setDriverReady() {
		lock.lock();
		this.driver=true;
		driverReady.signalAll();
		lock.unlock();
	}

	public void isDriverReady() {
		lock.lock();
		try {
			while(this.driver==false) driverReady.await();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}

	public void setPreco(Double preco) {
		lock.lock();
		this.custo = preco;
		this.precoReady.signalAll();
		lock.unlock();
	}

	public Double getPreco() {
		lock.lock();
		try {
			while(this.custo<0.f) precoReady.await();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			Double res = this.custo;
			lock.unlock();
			return res;
		}
	}

}

class Avail {
	public int x;
	public int y;
	public Condutor cond;
	private Pedido current;
	private ReentrantLock lock;
	private Condition c;

	public Avail(int x, int y,Condutor cond) {
		this.x = x;
		this.y = y;
		this.cond = cond;
		this.lock = new ReentrantLock();
		this.c = lock.newCondition();
	}

	public Pedido getPedido() {
		return this.current;
	}

	public void setPedido(Pedido ped) {
		lock.lock();
		this.current=ped;
		c.signalAll();
		lock.unlock();
	}

	public void waitForPedido() {
		lock.lock();
		try {
			while(current==null) c.await();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
}

class Database {
	private HashMap<String,ArrayList<Cliente>> clientes;
	private ArrayList<Avail> queuedrivers;
	ReentrantLock lock;
	Condition condition;

	public Database() {
		this.clientes = new HashMap<String,ArrayList<Cliente>>();
		this.queuedrivers = new ArrayList<Avail>();
		this.lock = new ReentrantLock();
		this.condition = this.lock.newCondition();
	}

	public synchronized boolean registaCliente (String username,String password) {
		Cliente cli = new Cliente(username);
		ArrayList<Cliente> tmp = this.clientes.get(password);
		if (tmp==null) {
			tmp = new ArrayList<Cliente>();
			clientes.put(password,tmp);
		}
		else {
			for (Cliente c : tmp) {
				if (c.username.equals(username)) return false;
			}
		}
		tmp.add(cli);
		return true;
	}

	public synchronized boolean registaCondutor (String username,String password,String modelo,String matricula) {
		Condutor cnd = new Condutor(username,modelo,matricula);
		ArrayList<Cliente> tmp = this.clientes.get(password);
		if (tmp==null) {
			tmp = new ArrayList<Cliente>();
			clientes.put(password,tmp);
		}
		else {
			for (Cliente c : tmp) {
				if (c.username.equals(username)) return false;
			}
		}
		tmp.add(cnd);
		return true;
	}

	public Cliente login (String username,String password) {
		ArrayList<Cliente> res = this.clientes.get(password);
		for (Cliente c : res) {
			if (c.username.equals(username)) return c;
		}
		return null;
	}

	public Avail getClosestDriver(Pedido ped) {
		try {
			lock.lock();
			while (queuedrivers.size()==0) condition.await();
			int dist = Math.abs(queuedrivers.get(0).x-ped.xstart)+Math.abs(queuedrivers.get(0).x-ped.ystart);
			int index = 0;
			for(int i=0;i<queuedrivers.size();i++) {
				Avail a = queuedrivers.get(i);
				int disttemp = Math.abs(a.x-ped.xstart)+Math.abs(a.y-ped.ystart);
				if(dist>disttemp) {
					dist=disttemp;
					index = i;
				}
			}
			Avail res = queuedrivers.remove(index);
			lock.unlock();
			res.setPedido(ped);
			return res;
		}
		catch (Exception e) {
			e.printStackTrace();
			lock.unlock();
			return null;
		}
	}

	public void setDisponivel(Avail avai) {
		lock.lock();
		this.queuedrivers.add(avai);
		this.condition.signalAll();
		lock.unlock();
	}

}

class Uber {

	public static void main (String[] args) {
		Socket cs;
		Database db = new Database();
		try {
			ServerSocket ss = new ServerSocket(6063);
			while ((cs=ss.accept())!=null) {
				new Dealer(cs,db).start();
			}
		}
		catch (Exception e) {
			System.out.println("ServerSocket over!");
		}
	}

}

class Dealer extends Thread {
	private Socket sock;
	private Database data;

	public Dealer (Socket cs, Database db) {
		this.sock = cs;
		this.data = db;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(sock.getInputStream());
			BufferedReader input = new BufferedReader(isr);
			PrintWriter output = new PrintWriter(sock.getOutputStream(),true);
			output.println("Bem-Vindo ao serviço de taxi NotUber! Escreva 'help' para ver lista de comandos disponíveis.");
			String inString;
			while ((inString=input.readLine())!=null) {
				StringTokenizer stemp = new StringTokenizer(inString);
				ArrayList<String> tokens = new ArrayList<String>();
				if (!stemp.hasMoreTokens()) {
					output.println("No input!!");
					continue;
				}
				String inCommand = stemp.nextToken();
				while(stemp.hasMoreTokens()) {
					tokens.add(stemp.nextToken());
				}
				switch(inCommand) {
					case "help":
						output.println("registar [username] [password]");
						output.println("registar [username] [password] [\"modelo\"] [matricula]");
						output.println("login [username] [password]");
						break;
					case "registar":
						if (tokens.size()<2 || tokens.size()>4) output.println("Comando nao reconhecido, tente outra vez.");
						else if (tokens.size()==4) {
							if (this.data.registaCondutor(tokens.get(0),tokens.get(1),tokens.get(2),tokens.get(3))) output.println("Registado Condutor com sucesso!");
							else output.println("Nome de utilizador já existente.");
						}
						else if (tokens.size()==2){
							if (this.data.registaCliente(tokens.get(0),tokens.get(1))) output.println("Registado Cliente com sucesso!");
							else output.println("Nome de utilizador já existente.");
						}
						else {
							output.println("Input errado.");
						}
						break;
					case "login":
						if (tokens.size()<2) {
							output.println("Comando nao reconhecido, tente outra vez.");
							break;
						}
						//verificar se foi logged como condutor ou cliente e chamar metodo adequado
						Cliente cli = data.login(tokens.get(0),tokens.get(1));
						if (cli instanceof Condutor) {
							output.println("Bem-vindo Condutor "+cli.username+"!");
							if (!loggedCondutor((Condutor)cli,input,output)) {
								isr.close();
								input.close();
								output.close();
								return;
							}
						}
						else if (cli instanceof Cliente) {
							output.println("Bem-vindo Cliente "+cli.username+"!");
							if (!loggedCliente(cli,input,output)) {
								isr.close();
								input.close();
								output.close();
								return;
							}
						}
						else {
							output.println("Ocurreu um erro. Contacte um administrador.");
						}
						break;
					default :
						output.println("Comando nao reconhecido, tente outra vez.");
				}
			}
			isr.close();
			input.close();
			output.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("IO problem, closing connection with client...");
		}
	}

	//metodos criados apenas para melhorar a visibilidade do codigo
	//nova thread para condutor a escuta de clientes apos estar como disp? ou fica a espera de ser escolhido?
	private boolean loggedCondutor(Condutor cond, BufferedReader input, PrintWriter output) {
		try {
			String inString;
			while ((inString=input.readLine())!=null) {
				StringTokenizer stemp = new StringTokenizer(inString);
				ArrayList<String> tokens = new ArrayList<String>();
				if (!stemp.hasMoreTokens()) {
					output.println("No input!!");
					continue;
				}
				String inCommand = stemp.nextToken();
				while(stemp.hasMoreTokens()) {
					tokens.add(stemp.nextToken());
				}
				switch(inCommand) {
					case "help":
						output.println("disponivel [xx] [yy]");
						output.println("pedido [xpartida] [ypartida] [xdestino] [ydestino]");
						output.println("logout");
						output.println("exit");
					break;
					case "disponivel":
						Avail av = new Avail(Integer.parseInt(tokens.get(0)),Integer.parseInt(tokens.get(1)),cond);
						this.data.setDisponivel(av);
						av.waitForPedido();
						Pedido ped = av.getPedido();
						output.println("Press [Enter] when at start position...");
						input.readLine();
						//sleep((Math.abs(av.x-ped.xstart)+Math.abs(av.y-ped.ystart)*20)*1000);
						ped.setDriverReady();
						output.println("Type price of trip and press [Enter] when at destination...");
						Double preco = Double.parseDouble(input.readLine());
						ped.setPreco(preco);
						output.println("Registado com sucesso! Obrigado pela sua disponibilidade!");
					break;
					case "pedido":
						if (tokens.size()!=4) {
							output.println("Comando não reconhecido...");
							break;
						}
						if (cond.pedidopendente) output.println("Já tem um pedido pendente!");
						else {
							cond.pedidopendente=true;
							output.println("A enviar pedido... Por favor aguarde por uma resposta...");
							Pedido ped2 = new Pedido (Integer.parseInt(tokens.get(0)),Integer.parseInt(tokens.get(1)),Integer.parseInt(tokens.get(2)),Integer.parseInt(tokens.get(3)),cond);
							Avail driver = this.data.getClosestDriver(ped2);
							output.println("Condutor encontrado!");
							output.println("'"+driver.cond.username+"'");
							output.println("Modelo: "+driver.cond.modelo);
							output.println("Tempo de chegada: "+(Math.abs(driver.x-ped2.xstart)+Math.abs(driver.y-ped2.ystart)*20)+" segundos.");
							output.println("Aguarde pelo condutor...");
							ped2.isDriverReady();
							output.println("Condutor está no local de partida!");
							output.println("Aguarde pelo custo da viagem...");
							double preco2 = ped2.getPreco();
							output.println("Chegou ao seu destino. Custo da viagem: "+preco2+"€. Obrigado pela sua preferência!");
							cond.pedidopendente=false;
						}
					break;
					case "logout":
						output.println("Logged out!");
						return true;
					default :
						output.println("Comando nao reconhecido, tente outra vez.");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("IO problem, closing connection with client...");
			return false;
		}
		return false;
	}

	private boolean loggedCliente(Cliente cli, BufferedReader input, PrintWriter output) {
		try {
			String inString;
			while ((inString=input.readLine())!=null) {
				StringTokenizer stemp = new StringTokenizer(inString);
				ArrayList<String> tokens = new ArrayList<String>();
				if (!stemp.hasMoreTokens()) {
					output.println("No input!!");
					continue;
				}
				String inCommand = stemp.nextToken();
				while(stemp.hasMoreTokens()) {
					tokens.add(stemp.nextToken());
				}
				switch(inCommand) {
					case "help":
						output.println("pedido [xpartida] [ypartida] [xdestino] [ydestino]");
						output.println("logout");
						output.println("exit");
					break;
					case "pedido":
						if (tokens.size()!=4) {
							output.println("Comando não reconhecido...");
							break;
						}
						if (cli.pedidopendente) output.println("Já tem um pedido pendente!");
						else {
							cli.pedidopendente=true;
							output.println("A enviar pedido... Por favor aguarde por uma resposta...");
							Pedido ped = new Pedido (Integer.parseInt(tokens.get(0)),Integer.parseInt(tokens.get(1)),Integer.parseInt(tokens.get(2)),Integer.parseInt(tokens.get(3)),cli);
							Avail driver = this.data.getClosestDriver(ped);
							output.println("Condutor encontrado!");
							output.println("'"+driver.cond.username+"'");
							output.println("Modelo: "+driver.cond.modelo);
							output.println("Tempo de chegada: "+(Math.abs(driver.x-ped.xstart)+Math.abs(driver.y-ped.ystart)*20)+" segundos.");
							output.println("Aguarde pelo condutor...");
							ped.isDriverReady();
							output.println("Condutor está no local de partida!");
							output.println("Aguarde pelo custo da viagem...");
							double preco = ped.getPreco();
							output.println("Chegou ao seu destino. Custo da viagem: "+preco+"€. Obrigado pela sua preferência!");
							cli.pedidopendente=false;
						}
					break;
					case "logout":
						output.println("Logged out!");
						return true;
					default :
						output.println("Comando nao reconhecido, tente outra vez.");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("IO problem, closing connection with client...");
			return false;
		}
		return false;
	}

}
