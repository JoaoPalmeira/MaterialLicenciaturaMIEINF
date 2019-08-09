import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;
/**
 * The test class Testes.
 *
 * @author  (your name)
 * @version (a version number or a date)
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
     * Testes da classe Playlist
     */
    
    @Test
    public void testePlaylist() {
        Playlist p = new Playlist();
        //Teste a numFaixas
        assertEquals(0,p.numFaixas());
        
        //Teste a addFaixa e numFaixas
        p.addFaixa(new Faixa("Faixa 1", "Autor 1", 2.1, 3 ));
        assertEquals(1,p.numFaixas());
        
        //Teste a removeFaixa e numFaixas
        p.removeFaixa(new Faixa("Faixa 1", "Autor 1", 2.1, 3 ));
        assertEquals(0,p.numFaixas());
        
        //Teste a getFaixas
        p.addFaixa(new Faixa("Faixa 1", "Autor 1", 3.2, 3 ));
        Faixa f = p.getFaixas().get(0);
        f.setClassificacao(-1);
        assertEquals(2,p.getFaixas().get(0).getClassificacao(),3);
        
        //Teste a adicionar
        List<Faixa> l = new ArrayList<>();       
        l.add(new Faixa("Faixa 2", "Autor 1", 2.3, 3));
        l.add(new Faixa("Faixa 3", "Autor 1", 2.5, 1));
        List<Faixa> l2 = new ArrayList<>();
        l2.add(new Faixa("Faixa 4", "Autor 2", 2.1, 2));
        l2.add(new Faixa("Faixa 5", "Autor 3", 1.5, 5));
        p.adicionar(l);
        p.adicionarF(l2);
        assertTrue(p.getFaixas().contains(new Faixa("Faixa 3", "Autor 1", 2.5, 1)));
        assertTrue(p.getFaixas().contains(new Faixa("Faixa 5", "Autor 3", 1.5, 5)));
        assertEquals(5,p.numFaixas());
        
        //Teste a classificacaoSuperior
        assertEquals(3,p.classificacaoSuperior(new Faixa("","",0,2)));
        assertEquals(1,p.classificacaoSuperiorF(new Faixa("","",0,3)));
        
        //Teste a duracaoSuperior
        assertTrue(p.duracaoSuperior(1.5));
        assertFalse(p.duracaoSuperior(4));
        
        //Teste a getCopiaFaixas
        assertTrue(p.getCopiaFaixas(0).contains(new Faixa("Faixa 4", "Autor 2", 2.1, 0)));
        assertTrue(p.getCopiaFaixasF(-1).contains(new Faixa("Faixa 4", "Autor 2", 2.1, -1)));
        
        //Teste a duracaoTotal
        assertEquals(11.6,p.duracaoTotal(),0);
        assertEquals(11.6,p.duracaoTotalF(),0);
        
        //Teste a removeFaixas
        p.removeFaixas("Autor 2");
        assertEquals(4,p.numFaixas());
        p.removeFaixasF("Autor 3");
        assertEquals(3, p.numFaixas());
        
        //Teste a clone e equals
        Playlist o2 = p.clone();
        assertTrue(o2.equals(p));
        o2.removeFaixa(new Faixa("Faixa 2", "Autor 1", 2.3, 3));
        assertFalse(o2.equals(p));
        assertFalse(o2 == p);
        assertFalse(o2.equals(null));
        assertFalse(o2.equals(new java.util.ArrayList()));
                
        //Teste a toString
        String r = testToString(o2.toString(),"Faixa 1","Autor 1", "3", "Faixa 3","Autor 1", "1");
        if(!r.isEmpty()) {
            fail("ToString failed. Missing '"+r+"'");
        }
    }
    /**/
    
    /**
     * Testes da classe ListaPaises
     */
    /*
    @Test
    public void testListaPaises() {
        //Teste à estrutuda das classes
        try {
            testStructure(FichaPais.class);
        } catch(Exception e) {
            fail(e.getMessage());
        }
        
        try {
            testStructure(ListaPaises.class);
        } catch(Exception e) {
            fail(e.getMessage());
        }
        
        //Teste a adicionar
        ListaPaises l = new ListaPaises();
        l.adicionar("Portugal","Europa",10.46);
        l.adicionar("Espanha","Europa",66.03);
        l.adicionar("Marrocos","Africa",33.01);
        l.adicionar("Japao","Asia",127.3);
        l.adicionar("Brasil","America",200.4);
        
        //Teste a numPaises
        assertEquals(5,l.numPaises());
        assertEquals(1,l.numPaises("America"));
        assertEquals(2,l.numPaisesF("Europa"));     

        //Teste a getFicha
        assertEquals(new FichaPais("Portugal","Europa",10.46), l.getFicha("Portugal"));
        assertEquals(new FichaPais("Japao","Asia",127.3), l.getFichaF("Japao"));
        
        //Teste a nomesPaises
        assertEquals(3,l.nomesPaises(50.0).size());
        assertEquals(2,l.nomesPaisesF(70.0).size());
        
        //Teste a nomesContinentes
        assertEquals(4,l.nomesContinentes(0.0).size());
        assertEquals(4,l.nomesContinentesF(0.0).size());
        
        //Teste a somatorio
        assertEquals(76.49,l.somatorio("Europa"),0.1);
        assertEquals(76.49,l.somatorioF("Europa"),0.1);
        
        //Teste a actualiza
        ArrayList<FichaPais> al = new ArrayList<>();
        al.add(new FichaPais("Japao","Asia",127.5));
        ArrayList<FichaPais> al2 = new ArrayList<>();
        al2.add(new FichaPais("Mexico","America",122.33));
        
        l.actualiza(al);
        assertEquals(127.5,l.getFicha("Japao").getPopulacao(),0);
        assertEquals(5,l.numPaises());
        l.actualizaF(al2);
        assertEquals(6,l.numPaises());
        
        //Teste a remove
        ArrayList<String> n = new ArrayList<>();
        n.add("Japao");
        n.add("Espanha");
        ArrayList<String> n2 = new ArrayList<>();
        n2.add("Japao");
        n2.add("Mexico");
        
        l.remove(n);
        assertEquals(4,l.numPaises());
        l.removeF(n2);
        assertEquals(3,l.numPaises());
        
        //Teste a clone e equals
        ListaPaises o = l.clone();
        assertTrue(o.equals(l));
        n.add("Marrocos");
        o.remove(n);
        assertFalse(o.equals(l));
        assertFalse(o == l);
        assertFalse(o.equals(null));
        assertFalse(o.equals(new java.util.ArrayList()));
        
        //Teste a toString
        String r = testToString(o.toString(),"Portugal", "Europa", "Brasil","10.46");
        if(!r.isEmpty()) {
            fail("ToString failed. Missing '"+r+"'");
        }
    }
    /**/
    
    /**
     * Testes da classe Stack
     */
    /*
    @Test
    public void testStack() {
        //Teste à estrutuda da classe
        try {
            testStructure(Stack.class);
        } catch(Exception e) {
            fail(e.getMessage());
        }
        
        //Tese a push
        Stack s = new Stack();
        s.push("A");
        s.push("B");
        s.push("C");
        s.push("D");
        s.push("E");
        
        //Tese a length e top
        assertEquals(5,s.length());
        assertEquals("E",s.top());

        //Tese a pop, empty, size
        s.pop();
        assertEquals("D",s.top());
        assertFalse(s.empty());
        s.pop();
        assertEquals(3,s.length());
        
        //Teste a clone e equals
        Stack o = s.clone();
        assertTrue(o.equals(s));
        o.pop();
        assertFalse(o.equals(s));
        assertFalse(o == s);
        assertFalse(o.equals(null));
        assertFalse(o.equals(new java.util.ArrayList()));
        
        //Teste a toString
        String r = testToString(o.toString(),"A","B");
        if(!r.isEmpty()) {
            fail("ToString failed. Missing '"+r+"'");
        }
    }
    /**/
    
    /**
     * Testes da classe MailList
     */
    /*
    @Test
    public void testMailList() {
        //Teste à estrutuda da classe Mail
        try {
            testStructure(Mail.class);
        } catch(Exception e) {
            fail(e.getMessage());
        }
        
        //Teste à estrutuda da classe MailList
        try {
            testStructure(MailList.class);
        } catch(Exception e) {
            fail(e.getMessage());
        }
        
        GregorianCalendar now = new GregorianCalendar();
        int y = now.get(Calendar.YEAR);
        int m = now.get(Calendar.MONTH);
        int d = now.get(Calendar.DAY_OF_MONTH);
        
        //Teste a adicionarEmail
        MailList l = new MailList();
        l.adicionarEmail(new Mail("r1",now,new GregorianCalendar(y,m-1,d), "assunto1 word test","texto"));
        l.adicionarEmail(new Mail("r1",now,new GregorianCalendar(y,m-1,d), "assunto2 test word","texto"));
        l.adicionarEmail(new Mail("r2",now,new GregorianCalendar(y,m-2,d), "assunto3 spam","texto"));
        l.adicionarEmail(new Mail("r2",now,new GregorianCalendar(y,m-1,d), "assunto4","texto"));
        l.adicionarEmail(new Mail("r3",now,new GregorianCalendar(y,m-2,d), "assunto5 spam","texto"));
        l.adicionarEmail(new Mail("r3",now,now, "assunto6","texto"));
        l.adicionarEmail(new Mail("r4",now,new GregorianCalendar(y,m+1,d), "assunto7","texto"));
        l.adicionarEmail(new Mail("r4",now,new GregorianCalendar(y,m+2,d), "assunto8 spam","texto"));
        l.adicionarEmail(new Mail("",now,new GregorianCalendar(y,m-1,d), "assunto9 word","texto"));
        l.adicionarEmail(new Mail("",new GregorianCalendar(y,m+2,d),new GregorianCalendar(y,m+2,d), "assunto10 test","texto"));
        
        //Teste a totalEmails
        assertEquals(10,l.totalEmails());
        
        //Teste a from
        assertEquals(2,l.from("r1"));
        assertEquals(2,l.fromF("r2"));
        
        //Teste a comAssunto
        List<Integer> t = l.comAssunto("word");
        assertEquals(3, t.size());
        assertEquals(0,t.get(0),0);  
        assertEquals(1,t.get(1),0);
        assertEquals(8,t.get(2),0);
               
        t = l.comAssuntoF("test");
        assertEquals(3, t.size());
        assertEquals(0,t.get(0),0);  
        assertEquals(1,t.get(1),0);
        assertEquals(9,t.get(2),0);
        
        //Teste a comAssuntoL
        List<Mail> tl = l.comAssuntoL("word");
        assertEquals(3, t.size());
        tl.get(0).setAssunto("-");
        assertTrue(tl.stream().anyMatch(e -> e.getAssunto().equals("-")));
        assertFalse(l.getEmails().stream().anyMatch(e -> e.getAssunto().equals("-")));
        
        tl = l.comAssuntoLF("test");
        assertEquals(3, t.size());
        tl.get(0).setAssunto("-");
        assertTrue(tl.stream().anyMatch(e -> e.getAssunto().equals("-")));
        assertFalse(l.getEmails().stream().anyMatch(e -> e.getAssunto().equals("-")));
        
        //Teste a eliminaRecebidos
        l.eliminarRecebidos(new GregorianCalendar(y,m-1,d));
        assertEquals(8,l.totalEmails());
               
        l.eliminarRecebidosF(now);
        assertEquals(5,l.totalEmails());
        
        //Teste a emailsDoDia
        assertEquals(1,l.emailsDoDia().size());
        assertEquals(1,l.emailsDoDiaF().size());
     
        //Teste a antiSpam
        List<String> sl = new ArrayList<>();
        sl.add("spam");
        l.antiSpam(sl);
        assertEquals(4,l.totalEmails());
        sl.add("word");
        l.antiSpamF(sl);
        assertEquals(3,l.totalEmails());
        
        //Teste a eliminar
        l.eliminar(new GregorianCalendar(y,m+1,d));
        assertEquals(1,l.totalEmails());
        l.eliminarF(new GregorianCalendar(y,m+3,d));
        assertEquals(0,l.totalEmails());
        
        //Teste a clone e equals
        l.adicionarEmail(new Mail("r1",now,new GregorianCalendar(y,m-1,d), "assunto1 word test","texto"));
        l.adicionarEmail(new Mail("r2",now,new GregorianCalendar(y,m-2,d), "assunto3 spam","texto"));
        
        MailList o2 = l.clone();
        assertTrue(o2.equals(l));
        ArrayList<String> al = new ArrayList<>();
        al.add("spam");
        o2.antiSpam(al);
        assertFalse(o2.equals(l));
        assertFalse(o2 == l);
        assertFalse(o2.equals(null));
        assertFalse(o2.equals(new java.util.ArrayList()));
                
        //Teste a toString
        String r = testToString(o2.toString(),"assunto1","r1");
        if(!r.isEmpty()) {
            fail("ToString failed. Missing '"+r+"'");
        }   
    }
    /**/
    
    
    public String testToString(String s, Object...args) {
        for(Object o: args) {
            String ts = String.valueOf(o);
            if(!s.contains(ts)) {
                return ts;
            }
        }
        if(!s.contains(",") && !s.contains(" ") && !s.contains(";") && !s.contains(":") && !s.contains(">") && !s.contains("\n")) {
            return "Separator";
        }
        return "";
    }
    
    @SuppressWarnings("unchecked")
    public void testStructure(Class c) throws Exception {
        boolean cempty = false;
        boolean cparameter = false;
        boolean cclone = false;
        for(Constructor cn : c.getDeclaredConstructors()) {
            if(!cempty && cn.getParameterCount() == 0) {
                cempty = true;
            }
            if(!cparameter && cn.getParameterCount() == c.getDeclaredFields().length) {
                cparameter = true;
            }
            if(!cclone && cn.getParameterCount() == 1) {
                cclone = cn.getParameterTypes()[0] == c;
            }
        }
        if(!cempty || !cparameter ||!cclone) {
            throw new Exception("Um ou mais construtores nao implementados em "+c.getSimpleName());
        }        
        
        for(Field f : c.getDeclaredFields()) {
            if(!Modifier.isPrivate(f.getModifiers())) {
                throw new Exception("Existem atributos não privados em "+c.getSimpleName());
            }
            
            try {
                Method m = c.getMethod("get"+capitalize(f.getName()));    
                m = c.getMethod("set"+capitalize(f.getName()), f.getType());    
            } catch (Exception e) {
                throw new Exception("Existem getters/setters em falta (ou com tipos errados) em "+c.getSimpleName());
            }
        }
        
        boolean equals = false;
        boolean clone = false;
        boolean toString = false;
        
        for(Method m : c.getDeclaredMethods()) {
            if(!clone && m.getName().equals("clone")) {
                clone = m.getReturnType() == c && m.getParameterCount() == 0;
            }
            
            if(!equals && m.getName().equals("equals")) {
                
                equals = m.getReturnType() == boolean.class && 
                        m.getParameterCount() == 1;
            }
            
            if(!toString && m.getName().equals("toString")) {
                toString = m.getReturnType() == String.class && 
                        m.getParameterCount() == 0;
            }            
        }
        if(!equals) {
            throw new Exception("Equals em falta ou mal implemenatado em "+c.getSimpleName());
        } 
        if(!clone) {
            throw new Exception("Clone em falta ou mal implemenatado em "+c.getSimpleName());
        }
        if(!toString) {
            throw new Exception("ToString em falta ou mal implemenatado em "+c.getSimpleName());
        }
    }
    
    public static String capitalize(String s) {
        return s.toUpperCase().charAt(0)+s.substring(1);
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
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
