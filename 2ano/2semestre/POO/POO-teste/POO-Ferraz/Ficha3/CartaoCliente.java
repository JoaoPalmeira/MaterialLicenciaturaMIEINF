
/**
 * Write a description of class CartaoCliente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CartaoCliente
{
	//Variaveis de instância
   private int pontos,valor,valorBonus;
   private String nome,codigo;
	
	//Construtores usuais
	public CartaoCliente(int p, int v, int vb, String n,String c){
		this.pontos=p;
		this.valor=v;
		this.valorBonus=vb;
		this.nome=n;
		this.codigo=c;
	}
	
	public CartaoCliente(){
		this(0,0,0,"","");
	}
	
	public CartaoCliente(CartaoCliente c){
		this.pontos=c.getPontos();
		this.valor=c.getValor();
		this.valorBonus=c.getValorBonus();
		this.nome=c.getNome();
		this.codigo=c.getCodigo();
	   }

 	//Getters e Setters
	public int getPontos(){return this.pontos;}	
	public int getValor(){return this.valor;}
	public int getValorBonus(){return this.valorBonus;}
	public String getNome(){return this.nome;}
	public String getCodigo(){return this.codigo;}

	public void setPontos(int pontos){this.pontos=pontos;}	
	public void setValor(int valor){this.valor=valor;}
	public void setValorBonus(int valorBonus){this.valorBonus=valorBonus;}
	public void setNome(String nome){this.nome=nome;}
	public void setCodigo(String codigo){this.codigo=codigo;}
	  
	//Métodos de instância
	public void descontar(int menu){
		if(menu==1 && pontos>9){	
			this.pontos=this.pontos-10;
		}
		if(menu==2 && pontos>19){
			this.pontos=this.pontos-20;		
		}
	}
	
	public void descaregarPontos(CartaoCliente cartao){
		this.pontos=this.pontos+cartao.getPontos();
	}

	public void efetuarCompra(int valor){
		if(valor<5){
			this.pontos=this.pontos+1;
		}
		if(valor>=5){
			this.pontos=this.pontos+2;
		}
		if(this.pontos>=20){
			this.pontos=this.pontos+10;
		}
	}


	public String toString(){
		return new String("Cartão Cliente com o nome-> "+nome" );
	}

}