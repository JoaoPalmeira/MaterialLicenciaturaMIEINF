
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Teste
{
    @Test
    public void TestCache()
    {
        Cache c1 = new Cache("Cache1","Esta ok",20,34,3);
        assertEquals(3,c1.getDificuldade());
        assertEquals(20,c1.getLat(),0);
        Cache c2 = c1.clone();
        c2.setLat(-4.5);
        assertFalse(c2.equals(c1));
    }
    
    public void TestGeoCah()
    {
        
    }
}
