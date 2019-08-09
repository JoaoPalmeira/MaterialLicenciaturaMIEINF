

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;

/**
 * The test class Testes.
 *
 * É necessário completar os teste, colocando os parâmetros nos construtores.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Testes
{
    private Imoobiliaria imo;
    private Vendedor v;
    private Terreno t;

    /**
     * Teste principal
     */
    @Test
    public void mainTest() {
        imo = new Imoobiliaria();
        try {
            imo.iniciaSessao("",null);
			fail();
        } catch(SemAutorizacaoException e) {
            
        } catch(Exception e) {
            fail();
        }
        
        try {
            v = new Vendedor("bruno@imobiliaria.pt","Bruno","bruno","Rua",null,null,null);  // Preencher parâmetros do construtor
            imo.registarUtilizador(v);
        } catch(Exception e) {
            fail();
        }
        
        String email = v.getEmail();
        String password = v.getPassword();
        
        try {
            imo.iniciaSessao(email, password);
        } catch(Exception e) {
            fail();
        }
        
        t = new Terreno("Rua",50000,25000,"venda","001",100,"habitacao",0.05,12,true);  // Preencher parâmetros do construtor
        try {
            imo.registaImovel(t);
        } catch (Exception e) {
            fail();
        }
            
        int s = imo.getImovel("Terreno", Integer.MAX_VALUE).size();
        assertTrue(s>0);
        Set<String> ids = imo.getTopImoveis(0);
        assertTrue(ids.contains(t.getId()));
        //---------------------------------------
        assertTrue(imo.getMapeamentoImoveis().keySet().contains(t));
        //---------------------------------------
        try {
            assertTrue(imo.getConsultas().size()>0);
        } catch(Exception e) {
            fail();
        }
        
        imo.fechaSessao();
        Comprador c = new Comprador("alex@imobiliaria.pt","Alexandre","alex","Rua",null,null);  // Preencher parâmetros do construtor
        try {
            imo.registarUtilizador(c);
        } catch(Exception e) {
            fail();
        }
        email = c.getEmail();
        password = c.getPassword();
        try {
            imo.iniciaSessao(email, password);
            imo.setFavorito(t.getId());
            assertTrue(imo.getFavoritos().contains(t));
        } catch(Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    
}
