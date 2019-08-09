import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Testes.
 *
 * @author  (your name)
 * @version 1.0 (2016)
 */
public class Testes
{
    /**
     * Default constructor for test class Testes
     */
    public Testes()
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
     * Testes da classe Veiculo
     */
    @Test
    public void testVeiculo() {
        Veiculo v1 = new Veiculo("aa-00-bb", 135000, 0, 5, 50, 10, 1);
        
        //Acesso a variveis static
        Veiculo.Gasolina95 = 1.3;
        Veiculo.Gasolina98 = 1.5;
        Veiculo.Gasoleo = 0.9;
        
        //Teste a totalCombustivel
        assertEquals(10125.0,v1.totalCombustivel(),0);
        v1.setKmsTotal(1);
        assertEquals(0.075,v1.totalCombustivel(),0.1);
        
        //Teste a custoMedioKm
        assertEquals(0.075,v1.custoMedioKm(),0.1);
        v1.setTipoCombustivel(2);
        assertEquals(0.045,v1.custoMedioKm(),1);
        
        //Teste a clone e equals
        Veiculo o2 = v1.clone();
        assertTrue(o2.equals(v1));
        o2.setKmsTotal(1000);
        assertFalse(o2.equals(v1));
        assertFalse(o2 == v1);
        assertFalse(o2.equals(null));
        assertFalse(o2.equals(new java.util.ArrayList()));
        
        //Test toString
        assertTrue(testToString(o2.toString(),o2.getMatricula(), o2.getKmsTotal(), o2.getKmsParcial(), o2.getConsumoMedio(), o2.getCapacidade(), o2.getConteudo(), o2.getTipoCombustivel() ));
    }
    
    /**
     * Testes da classe Ecran
     */
    @Test
    public void testEcran() {
        //teste a metodo setDimensoes
        Ecran.SetDimensoes(10,5);
        
        Ecran e = new Ecran(2);  
        
        //Teste a metodos mudaCor e obterCor
        e.mudaCor(9,4,2);
        int c = e.obterCor(9,4);
        assertEquals(2,c);
        c = e.obterCor(2,2);
        assertEquals(2,c);
        
        //Teste a clone e equals
        Ecran o2 = e.clone();
        assertTrue(o2.equals(e));
        o2.mudaCor(1,1,0);
        assertFalse(o2.equals(e));
        assertFalse(o2 == e);
        assertFalse(o2.equals(null));
        assertFalse(o2.equals(new java.util.ArrayList()));
        
        //Test toString
        assertTrue(testToString(o2.toString(),""));
    }
    
    /**
     * Testes da classe LLString
     */
    
    @Test
    public void testLLString() {
        LLStrings l = new LLStrings();
        
        //Teste a adicionar, tamanho e vazia
        assertEquals(0,l.tamanho());
        assertTrue(l.vazia());
        l.adicionar("Teste");
        l.adicionar("Teste2");
        l.adicionar("Teste3");
        assertEquals(3,l.tamanho());
        assertFalse(l.vazia());
        
        //teste a inserir e get
        l.inserir(2,"TesteN");
        System.out.println("0");
        String v1 = l.get(2);
        String v2 = l.get(3);
        assertEquals(v1,"TesteN");
        assertEquals(v2,"Teste3");
        
        //teste a esvaziar
        l.esvaziar();
        assertEquals(0,l.tamanho());
        assertTrue(l.vazia());
    }
    
    public boolean testToString(String s, Object...args) {
        for(Object o: args) {
            String ts = String.valueOf(o);
            if(!s.contains(ts)) {
                return false;
            }
        }
        if(!s.contains(",") && !s.contains(" ") && !s.contains(";") && !s.contains(":") && !s.contains(">")) {
            return false;
        }
        return true;
    }
    
    
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
