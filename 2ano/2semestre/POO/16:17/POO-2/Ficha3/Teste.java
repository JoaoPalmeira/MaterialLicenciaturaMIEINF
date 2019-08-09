
import java.lang.reflect.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.GregorianCalendar;

/**
 * The test class Teste.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Teste
{
    /**
     * Default constructor for test class Teste
     */
    public Teste()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Testes da classe Complexo
     */
    @Test
    public void testarComplexo() {
        Complexo c1 = new Complexo(10.3,20.4);
        Complexo c2 = new Complexo(15.2,15.1);
        //Teste a método soma
        Complexo c3 = c1.soma(c2);
        assertEquals(25.5, c3.getA(),0);
        assertEquals(35.5, c3.getB(),0);
        
        //Teste a método produto
        Complexo c4 = c1.produto(c2);
        assertEquals(-151.48, c4.getA(),1);
        assertEquals(465.61, c4.getB(),0);
        
        //Teste a método conjugado
        Complexo c5 = c1.conjugado();
        assertEquals(10.3,c5.getA(),0);
        assertEquals(-20.4,c5.getB(),0);
        
        //Teste a método reciproco
        Complexo c6 = c1.reciproco();
        assertEquals(0.0197,c6.getA(),1);
        assertEquals(0.039,c6.getB(),1);
        
        //Teste a método clone e equals
        Complexo cc = c1.clone();
        assertTrue(cc.equals(c1));
        cc.setA(1);
        assertFalse(cc.equals(c1));
        Complexo fc = new Complexo(0,0);
        fc.setA(cc.getA());
        fc.setB(cc.getB());
        assertTrue(cc.equals(fc));
        assertFalse(cc == fc);
        Complexo n = null;
        assertFalse(cc.equals(n));
        assertFalse(cc.equals(new java.util.ArrayList()));
        
        //Test toString
        assertTrue(cc.toString().contains(String.valueOf(cc.getA())));
        assertTrue(cc.toString().contains(String.valueOf(cc.getB())));
    }
    
    /**
     * Testes da classe Pixel
     */
    @Test
    public void testarPixel() {
        Pixel p1 = new Pixel(10.5,10.5,0);
        //teste a método deslocar
        p1.desloca(9.5,-10.5);
        assertEquals(20,p1.getX(),0);
        assertEquals(0,p1.getY(),0);
        
        //teste a método mudarCor
        p1.mudarCor(20);
        assertEquals(0,p1.getCor());
        p1.mudarCor(2);
        assertEquals(2,p1.getCor());
        
        //teste a método nomeCor
        assertEquals("Verde Escuro",p1.nomeCor());
        
        //Teste a método clone e equals
        Pixel p2 = p1.clone();
        assertTrue(p1.equals(p2));
        p1.setX(-10);
        assertFalse(p1.equals(p2));
        assertFalse(p1 == p2);
        assertFalse(p1.equals(null));
        assertFalse(p1.equals(new java.util.ArrayList()));
        
        //Test toString
        assertTrue(p1.toString().contains(String.valueOf(p1.getCor())));
    }
    
    /**
     * Testes da classe Veiculo
     */
    @Test
    public void testarVeiculo() {
        Veiculo v1 = new Veiculo("00-aa-00", 0,0,0,20,0);
        
        //teste a método abastecer
        v1.abastecer(5);
        assertEquals(5,v1.getConteudo());
        v1.abastecer(1000);
        assertEquals(v1.getConteudo(),v1.getCapacidade());
        
        //teste a método registarViagem
        v1.registarViagem(200,6);
        assertEquals(200,v1.getKmsTotal(),0);
        assertEquals(14,v1.getConteudo());        
       
        //teste a método autonomia
        assertEquals(466.67,v1.autonomia(),1);
        
        //teste a método naReserva
        v1.setConteudo(1);
        assertTrue(v1.naReserva());
        v1.setConteudo(10);
        assertFalse(v1.naReserva());
        
        //teste a método totalCombustivel
        assertEquals(9,v1.totalCombustivel(1.5),0);
        
        /*//teste a método custoMedioKm
        assertEquals(50,v1.custoMedioKm(1.5),0);*/
        
        //teste a método resetKms
        v1.resetKms();
        assertEquals(0,v1.getKmsParcial(),0);
        assertEquals(0,v1.getConsumoMedio(),0);
        
        //Teste a método clone e equals
        Veiculo o2 = v1.clone();
        assertTrue(o2.equals(v1));
        o2.setMatricula("---");
        assertFalse(o2.equals(v1));
        assertFalse(o2 == v1);
        assertFalse(o2.equals(null));
        assertFalse(o2.equals(new java.util.ArrayList()));
        
        //Test toString
        assertTrue(o2.toString().contains(String.valueOf(o2.getMatricula())));
    }
    
    /**
     * Testes da classe CartaoCliente
     */
    /*
    @Test
    public void testarCartaoCliente() {
        CartaoCliente c1 = new CartaoCliente(0, 0, "a12345","Nome1", 20);
        
        //Testar efectuarCompra
        c1.efectuarCompra(10);
        assertEquals(2,c1.getPontos());
        c1.efectuarCompra(5);
        assertEquals(3,c1.getPontos());
        for(int i=0;i<10;i++) {
            c1.efectuarCompra(10);
        }
        assertEquals(33,c1.getPontos());
        
        //Testar descontar
        c1.descontar(1);
        assertEquals(23,c1.getPontos());
        c1.descontar(2);
        assertEquals(3,c1.getPontos());
        c1.descontar(2);
        assertEquals(3,c1.getPontos());
        
        //Testar setNome
        c1.setNome("NovoNome");
        assertEquals("NovoNome",c1.getNome());
        
        //Testar setValorBonus
        c1.setValorBonus(4);
        c1.efectuarCompra(10);
        assertEquals(15,c1.getPontos());
        assertEquals(4,c1.getValorBonus());
        
        //Testar descarregarPontos
        CartaoCliente c2 = new CartaoCliente(0, 20, "a12345","Nome1", 20);
        c1.descarregarPontos(c2);
        assertEquals(35,c1.getPontos());
        assertEquals(0, c2.getPontos());
        
        //Teste a método clone e equals
        CartaoCliente o2 = c1.clone();
        assertTrue(o2.equals(c1));
        o2.setNome("---");
        assertFalse(o2.equals(c1));
        assertFalse(o2 == c1);
        assertFalse(o2.equals(null));
        assertFalse(o2.equals(new java.util.ArrayList()));
        
        //Test toString
        assertTrue(o2.toString().contains(String.valueOf(o2.getNome())));
    }
    */
   
    /**
     * Testes da classe Produto
     */
    /*
    @Test
    public void testarProduto() {
        Produto p = new Produto("123abc","Produto1",100,10,10,15);
        //Testar modificaStock
        p.modificaStock(-50);
        assertEquals(50,p.getStock());
        p.modificaStock(-60);
        assertEquals(50,p.getStock());        
        
        //Testar alteraCodigo
        p.alteraCodigo("a");
        assertEquals("123abc",p.getCodigo());
        p.alteraCodigo("abc123abc");
        assertEquals("abc123abc",p.getCodigo());
        
        //Testar setPrecoVenda
        p.setPrecoVenda(15);
        assertEquals(15,p.getPrecoVenda(),0);
        p.setPrecoVenda(11);
        assertEquals(11,p.getPrecoVenda(),0);
        
        //Testar defineMargemLucro
        p.defineMargemLucro(50);
        assertEquals(15,p.getPrecoVenda(),0);
        p.defineMargemLucro(100);
        assertEquals(20,p.getPrecoVenda(),0);
        
        //Testar efectuaCompra
        p.efectuaCompra(2500);
        assertEquals(50,p.getStock());
        p.efectuaCompra(100);
        assertEquals(45,p.getStock());
        
        //Testar lucroTotal
        assertEquals(900, p.lucroTotal(),0);
        
        //Testar precoTotal
        assertEquals(400,p.precoTotal(20),0);
        
        //Testar abaixoValor
        assertFalse(p.abaixoValor());
        p.modificaStock(-45);
        assertTrue(p.abaixoValor());
        
        //Teste a método clone e equals
        Produto o2 = p.clone();
        assertTrue(o2.equals(p));
        p.setStock(10);
        assertFalse(o2.equals(p));
        assertFalse(o2 == p);
        assertFalse(o2.equals(null));
        assertFalse(o2.equals(new java.util.ArrayList()));
        
        //Test toString
        assertTrue(o2.toString().contains(String.valueOf(p.getStock())));
    }
    */
    
    /**
     * Testes da classe Cronometro
     */
    /*
    @Test
    public void testarCronometro() {
        CronometroDS c = new CronometroDS();
        //Testar  primeiraParagem
        try {Thread.sleep(1000); } catch(Exception e) {}
        c.parar();
        assertEquals("1.0",c.primeiraParagem().split(":")[1].split("'")[0]);
        
        //Testar segundaParagem
        try {Thread.sleep(500); } catch(Exception e) {}
        c.parar();
        double v = Double.parseDouble(c.segundaParagem().split("'")[1]);
        assertEquals(500,Math.abs(v),10);
        
        //Testar tempoSplit
        v = Double.parseDouble(c.tempoSplit().split("'")[1]);
        assertEquals(500,Math.abs(v),10);           
    }
    */
    
    /**
     * Testes da classe ContaPrazo
     */
    /*
    @Test
    public void testarContaPrazo() {
        GregorianCalendar op = new GregorianCalendar(2016,1,1,10,0,0);
        GregorianCalendar lim = new GregorianCalendar(2017,2,1,10,0,0);
        GregorianCalendar now = new GregorianCalendar();
        ContaPrazo c = new ContaPrazo("a123","Nome",op,100000,now,5);

        //Testar diasPassados
        long ms = (now.getTimeInMillis()-op.getTimeInMillis())/1000/60/60/24;
        assertEquals(ms,c.diasPassados());
        
        //Testar alterarTitular
        c.alterarTitular("Titular");
        assertEquals("Titular",c.getTitular());
        
        //Testar alterarTaxa
        c.alterarTaxa(0.3);
        assertEquals(0.3, c.getTaxa(),0);
        
        //Testar verificarDiaJuros
        assertTrue(c.verificarDiaJuros());
                
        //Testar actualizarJuros
        c.actualizarJuros(lim);
        assertEquals(130000,c.getCapital(),0);
        
        //Testar fecharConta
        assertEquals(169000,c.fecharConta(),0);
        
        //Teste a método clone e equals
        ContaPrazo o2 = c.clone();
        assertTrue(o2.equals(c));
        o2.setCodigo("---");
        assertFalse(o2.equals(c));
        assertFalse(o2 == c);
        assertFalse(o2.equals(null));
        assertFalse(o2.equals(new java.util.ArrayList()));
        
        //Test toString
        assertTrue(o2.toString().contains(String.valueOf(o2.getCodigo())));
    }
    */
   
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
