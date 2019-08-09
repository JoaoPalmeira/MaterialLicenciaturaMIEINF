

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste TesteCache.
 *
 * @author  (seu nome)
 * @version (um número de versão ou data)
 */
public class TesteCache
{
    /**
     * Construtor default para a classe de teste TesteCache
     */
    public TesteCache()
    {
    }

    /**
     * Define a .
     *
     * Chamado antes de cada método de caso de teste.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Chamado após cada método de teste de caso.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testeCache (){
        Cache c1 = new Cache ();
        Cache c2 = new Cache ();
        assertTrue(c1 == c1);
        assertEquals(c1,c2);
        assertEquals(c1.getLat(),c2.getLat(), 0);
        assertFalse(c1 == c2);
    }
    
    @Test
    public void teste2(){
        Cache cache1 = new Cache ();
        Cache cache2 = new Cache ();
        assertEquals(true, cache1.equals(cache2)); 
    }
}
