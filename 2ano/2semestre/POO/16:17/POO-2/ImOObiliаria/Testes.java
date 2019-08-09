import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import static java.util.stream.Collectors.toMap;
import java.util.GregorianCalendar;

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
        
        String id1 = "1000";
        char estado1 = 'V';
        String rua1 = "Tapada";
        double preco1 = 50000;
        double precoMin1 = 45000;
        double area1 = 100;
        char tipo1 = 'A';
        double diametro1 = 5.4;
        long kwh1 = 20;
        char esgoto1 = 'S';
        
        Terreno t = new Terreno(id1,estado1,rua1,preco1,precoMin1,area1,tipo1,diametro1,kwh1,esgoto1);
        
        String id2 = "2000";
        char estado2 = 'V';
        String rua2 = "Liberdade";
        double preco2 = 100000;
        double precoMin2 = 90000;
        String tipo2 = "isolada";
        double area2 = 300000;
        double areaCoberta2 = 1000;
        double areaEnvolvente2 = 299000;
        int quartos2 = 4;
        int wcs2 = 4;
        int porta2 = 64;
        
        Moradia m = new Moradia(id2,estado2,rua2,preco2,precoMin2,tipo2,area2,areaCoberta2,areaEnvolvente2,quartos2,wcs2,porta2);
        
        String id3 = "3000";
        char estado3 = 'A';
        String rua3 = "25 de Abril";
        double preco3 = 80000;
        double precoMin3 = 70000;
        String tipo3 = "Duplex";
        double area3 = 300;
        int quartos3 = 4;
        int wcs3 = 3;
        int porta3 = 10;
        int andar3 = 5;
        char garagem3 = 'S';
        
        Apartamento a = new Apartamento(id3,estado3,rua3,preco3,precoMin3,tipo3,area3,quartos3,wcs3,porta3,andar3,garagem3);
        
        String id4 = "4000";
        char estado4 = 'A';
        String rua4 = "Costa";
        double preco4 = 10000;
        double precoMin4 = 9000;
        double area4 = 15;
        char wc4 = 'S';
        String tipo4 = "Florista";
        int porta4 = 90;
        
        Loja l = new Loja(id4,estado4,rua4,preco4,precoMin4,area4,wc4,tipo4,porta4);
        
        String email1 = "joaquimsobral@gmail.com";
        String nome1 = "Joaquim Sobral";
        String password1 = "Joaquina";
        String morada1 = "Lameiras";
        GregorianCalendar dataNascimento1 = new GregorianCalendar(1972,4,18);
        
        try 
        {
            v = new Vendedor(email1,nome1,password1,morada1,dataNascimento1);
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
        
        try {
            imo.registaImovel(t);
        } catch (Exception e) {
            fail();
        }
            
        int s = imo.getImovel("Terreno", Integer.MAX_VALUE).size();
        assertTrue(s>0);
        //Set<String> ids = imo.getTopImoveis(0);
        //assertTrue(ids.contains(t.getId()));
        //assertTrue(imo.getMapeamentoImoveis().keySet().contains(t));
        //assertTrue(imo.getConsultas().size()>0);
        
        imo.fechaSessao();
        
        String email2 = "sabinorui@gmail.com";
        String nome2 = "Sabino Rui";
        String password2 = "CurralDeMoinas";
        String morada2 = "Ronfe";
        GregorianCalendar dataNascimento2 = new GregorianCalendar(1955,01,31);
        
        Comprador c = new Comprador(email2, nome2, password2, morada2, dataNascimento2);
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
