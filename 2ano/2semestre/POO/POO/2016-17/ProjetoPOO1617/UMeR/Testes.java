
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A classe de teste Testes.
 *
 * @author  (seu nome)
 * @version (um número de versão ou data)
 */
public class Testes
{
    
    private UMeR umer;
    private Utilizador ut,ut1;
    private UMeRApp umerapp;
   //Cliente(String email, String nome, String password, String morada, LocalDate dataNascimento, Map<Integer,Viagem> viagens, Espaco2D localizacao)
    /**
     * Teste principal
     */
    @Test
    public void mainTest() {
        Map<Integer,Viagem> viagens1=new HashMap<>();
        Espaco2D espaco=new Espaco2D(0.0,0.0);
        LocalDate dataNascimento = LocalDate.of(1999,4,1);
        LocalDate dataNascimento1= LocalDate.of(1998,5,2);
        LocalDate dataNascimento2 = LocalDate.of(1965,6,28);
        LocalDate dataNascimento3 = LocalDate.of(1954,8,28);
        LocalDate dataNascimento4 = LocalDate.of(1995,4,25);
        LocalDate dataNascimento5 = LocalDate.of(1970,8,20);
        LocalDate dataNascimento7 = LocalDate.of(1976,3,15);
        LocalDate dataNascimento8 = LocalDate.of(1980,2,12);
        LocalDate dataNascimento9 = LocalDate.of(1990,12,24);
        LocalDate dataNascimento10 = LocalDate.of(1979,11,21);
        LocalDate dataNascimento6 = LocalDate.of(1969,9,9);
        umer = new UMeR();
        List viagensEmEspera = new ArrayList<>();
        
        try {
            ut = new Utilizador("andre@gmail.com","André","12345","Braga",dataNascimento,viagens1);  // Preencher parâmetros do construtor
            umer.adiciona(ut);
        }  
        catch(Exception e) {
            fail();
        }
        
        String email = ut.getEmail();
        String password = ut.getPassword();
        
        try {
            umer.iniciaSessao(email, password);
        } catch(Exception e) {
            fail();
        }
        
        try {
            ut1 = new Utilizador("sofia@gmail.com","Sofia","123456","Braga",dataNascimento,viagens1);  // Preencher parâmetros do construtor
            umer.adiciona(ut1);
        }  
        catch(Exception e) {
            fail();
        }
        
        String email1 = ut1.getEmail();
        String password1 = ut1.getPassword();
        Motorista ut1= new Motorista();
        
        try {
            umer.iniciaSessao(email1, password1);
        } catch(Exception e) {
            fail();
        }
               
        int grauC=0, grauC2=0, grauC3=0, grauC4=0, grauC5=0, grauC6=0, grauC7=0, grauC8=0, grauC9=0, grauC10=0;
        int classif=0, classif2=0, classif3=0, classif4=0, classif5=0, classif6=0, classif7=0, classif8=0, classif9=0, classif10=0;
        double kms=0.0, kms2=0.0, kms3=0.0, kms4=0.0, kms5=0.0, kms6=0.0, kms7=0.0, kms8=0.0, kms9=0.0, kms10=0.0;
        boolean dispon=true, dispon2=true, dispon3=true, dispon4=true, dispon5=true, dispon6=true, dispon7=true, dispon8=true, dispon9=true, dispon10=true;
        String matrix = "123456";String matrix2= "11111";String matrix3= "22222";
        String matrix6= "6666";String matrix5= "98765";String matrix4= "33333";
        String matrix7= "123ana";String matrix8= "43523";String matrix9= "44444";String matrix10= "4431234";
        
        String email2=new String();
        String password2=new String();
        
        try {
            Motorista m = new Motorista("daniel@gmail.com","Daniel","123456daniel","Braga",dataNascimento,viagens1,grauC,classif,kms,dispon,matrix);  // Preencher parâmetros do construtor
            umer.adiciona(m);
            email2 = m.getEmail();
            password2 = m.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email2, password2);
        } catch(Exception e) {
            fail();
        }
        
        String email3=new String();
        String password3=new String();
        
        try {
            Motorista m1 = new Motorista("joaquina@gmail.com","Joaquina","joaquina123","Aveiro",dataNascimento2,viagens1,grauC2,classif2,kms2,dispon2,matrix2);  // Preencher parâmetros do construtor
            umer.adiciona(m1);
            email3 = m1.getEmail();
            password3 = m1.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
       
        try {
            umer.iniciaSessao(email3, password3);
        } catch(Exception e) {
            fail();
        }
        
        String email4=new String();
        String password4=new String();
        
        try {
            Motorista m2 = new Motorista("joao@iol.pt","Joao","joao123","Lisboa",dataNascimento3,viagens1,grauC3,classif3,kms3,dispon3,matrix3);  // Preencher parâmetros do construtor
            umer.adiciona(m2);
            email4 = m2.getEmail();
            password4 = m2.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email4, password4);
        } catch(Exception e) {
            fail();
        }
        String email5=new String();
        String password5=new String();
        
        try {
            Motorista m3 = new Motorista("pedro@hotmail.pt","pedro","123456","Faro",dataNascimento4,viagens1,grauC4,classif4,kms4,dispon4,matrix4);  // Preencher parâmetros do construtor
            umer.adiciona(m3);
            email5 = m3.getEmail();
            password5 = m3.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email5, password5);
        } catch(Exception e) {
            fail();
        }
        String email6=new String();
        String password6=new String();
        
        try {
            Motorista m4 = new Motorista("palmeira@hotmail.pt","palmeira","palms","Coimbra",dataNascimento5,viagens1,grauC5,classif5,kms5,dispon5,matrix5);  // Preencher parâmetros do construtor
            umer.adiciona(m4);
            email6 = m4.getEmail();
            password6 = m4.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email6, password6);
        } catch(Exception e) {
            fail();
        }
        
        String email7=new String();
        String password7=new String();
        
        try {
            Motorista m5 = new Motorista("rafa@hotmail.pt","rafael","rafa123","Chaves",dataNascimento6,viagens1,grauC6,classif6,kms6,dispon6,matrix6);  // Preencher parâmetros do construtor
            umer.adiciona(m5);
            email7 = m5.getEmail();
            password7 = m5.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email7, password7);
        } catch(Exception e) {
            fail();
        }
        
        String email8=new String();
        String password8=new String();
        
        try {
            Motorista m6 = new Motorista("ana@hotmail.pt","Ana","ana123","Barcelos",dataNascimento7,viagens1,grauC7,classif7,kms7,dispon7,matrix7);  // Preencher parâmetros do construtor
            umer.adiciona(m6);
            email8 = m6.getEmail();
            password8 = m6.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email8, password8);
        } catch(Exception e) {
            fail();
        }
        String email9=new String();
        String password9=new String();
        
        try {
            Motorista m7 = new Motorista("margarida@hotmail.pt","Margarida","margs123","Evora",dataNascimento8,viagens1,grauC8,classif8,kms8,dispon8,matrix8);  // Preencher parâmetros do construtor
            umer.adiciona(m7);
            email9 = m7.getEmail();
            password9 = m7.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
     
        try {
            umer.iniciaSessao(email9, password9);
        } catch(Exception e) {
            fail();
        }
         String email10=new String();
        String password10=new String();
        
        try {
            Motorista m8 = new Motorista("anabraga@hotmail.pt","Ana","braga123","Madeira",dataNascimento9,viagens1,grauC9,classif9,kms9,dispon9,matrix9);  // Preencher parâmetros do construtor
            umer.adiciona(m8);
            email10 = m8.getEmail();
            password10 = m8.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
      
        try {
            umer.iniciaSessao(email10, password10);
        } catch(Exception e) {
            fail();
        }
        String email11=new String();
        String password11=new String();
        
        try {
            Motorista m9 = new Motorista("helena@iol.pt","Helena","helena123","Viana do Castelo",dataNascimento10,viagens1,grauC10,classif10,kms10,dispon10,matrix10);  // Preencher parâmetros do construtor
            umer.adiciona(m9);
            email11 = m9.getEmail();
            password11 = m9.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email11, password11);
        } catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email2, password2);
        } catch(Exception e) {
            fail();
        }
        
        Espaco2D espaco1=new Espaco2D(0.0,0.0);
        Espaco2D espaco2=new Espaco2D(0.0,0.0);
        Espaco2D espaco3=new Espaco2D(0.0,0.0);
        Espaco2D espaco4=new Espaco2D(0.0,0.0);
        Espaco2D espaco5=new Espaco2D(0.0,0.0);
        Espaco2D espaco6=new Espaco2D(0.0,0.0);
        Espaco2D espaco7=new Espaco2D(0.0,0.0);
        Espaco2D espaco8=new Espaco2D(0.0,0.0);
        Espaco2D espaco9=new Espaco2D(0.0,0.0);
        Espaco2D espaco10=new Espaco2D(0.0,0.0);
        LocalDate dataNascimento_a = LocalDate.of(1955,5,21);
        LocalDate dataNascimento_b = LocalDate.of(1976,8,10);
        LocalDate dataNascimento_c = LocalDate.of(1956,5,12);
        LocalDate dataNascimento_d = LocalDate.of(1981,2,13);
        LocalDate dataNascimento_e = LocalDate.of(1991,10,29);
        LocalDate dataNascimento_f = LocalDate.of(1990,3,9);
        LocalDate dataNascimento_g = LocalDate.of(1986,5,15);
        LocalDate dataNascimento_h = LocalDate.of(1961,4,23);
        LocalDate dataNascimento_i = LocalDate.of(1971,11,25);
        LocalDate dataNascimento_j = LocalDate.of(1959,6,11);
        
        
        String email12=new String();
        String password12=new String();
        
        try {
            Cliente c = new Cliente("daniel@gmail.com","Daniel","123456daniel","Braga",dataNascimento_a,viagens1,espaco1);  // Preencher parâmetros do construtor
            umer.adiciona(c);
            email12 = c.getEmail();
            password12 = c.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email12, password12);
        } catch(Exception e) {
            fail();
        }
        
        String email13=new String();
        String password13=new String();
        
        try {
            Cliente c1 = new Cliente("daniela@gmail.com","Daniela","123456daniela","Braganca",dataNascimento_b,viagens1,espaco2);  // Preencher parâmetros do construtor
            umer.adiciona(c1);
            email13 = c1.getEmail();
            password13 = c1.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email13, password13);
        } catch(Exception e) {
            fail();
        }
        String email14=new String();
        String password14=new String();
        
        try {
            Cliente c2 = new Cliente("germano@gmail.com","germano","germano12","VilaVerde",dataNascimento_c,viagens1,espaco3);  // Preencher parâmetros do construtor
            umer.adiciona(c2);
            email14 = c2.getEmail();
            password14 = c2.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email14, password14);
        } catch(Exception e) {
            fail();
        }
         String email15=new String();
        String password15=new String();
        
        try {
            Cliente c3 = new Cliente("neca@iol.pt","Neca","Neca123","Barcelinhos",dataNascimento_d,viagens1,espaco4);  // Preencher parâmetros do construtor
            umer.adiciona(c3);
            email15 = c3.getEmail();
            password15 = c3.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email15, password15);
        } catch(Exception e) {
            fail();
        }
        
         String email16=new String();
        String password16=new String();
        
        try {
            Cliente c4 = new Cliente("joaquim@hotmail.com","Joaquim","111111","Airó",dataNascimento_e,viagens1,espaco5);  // Preencher parâmetros do construtor
            umer.adiciona(c4);
            email16 = c4.getEmail();
            password16 = c4.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email16, password16);
        } catch(Exception e) {
            fail();
        }
        String email17=new String();
        String password17=new String();
        
        try {
            Cliente c5 = new Cliente("jonas@hotmail.com","Joao","jonas125","Vila Boa",dataNascimento_f,viagens1,espaco6);  // Preencher parâmetros do construtor
            umer.adiciona(c5);
            email17 = c5.getEmail();
            password17 = c5.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email17, password17);
        } catch(Exception e) {
            fail();
        }
        
        String email18=new String();
        String password18=new String();
        
        try {
            Cliente c6 = new Cliente("barbara@gmail.com","barbara","barbara123","Vila Boa",dataNascimento_g,viagens1,espaco7);  // Preencher parâmetros do construtor
            umer.adiciona(c6);
            email18 = c6.getEmail();
            password18 = c6.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email18, password18);
        } catch(Exception e) {
            fail();
        }
        
        String email19=new String();
        String password19=new String();
        
        try {
            Cliente c7 = new Cliente("jorge@iol.pt","jorge","jorge69","Braga",dataNascimento_h,viagens1,espaco8);  // Preencher parâmetros do construtor
            umer.adiciona(c7);
            email19 = c7.getEmail();
            password19 = c7.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email19, password19);
        } catch(Exception e) {
            fail();
        }
        
        String email20=new String();
        String password20=new String();
        
        try {
            Cliente c8 = new Cliente("oscar@iol.pt","Oscar","123456789","Fatima",dataNascimento_i,viagens1,espaco9);  // Preencher parâmetros do construtor
            umer.adiciona(c8);
            email20 = c8.getEmail();
            password20 = c8.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email20, password20);
        } catch(Exception e) {
            fail();
        }
        
        String email21=new String();
        String password21=new String();
        
        try {
            Cliente c9 = new Cliente("bruna@hotmail.pt","Bruna","12345bruna","Braga",dataNascimento_j,viagens1,espaco10);  // Preencher parâmetros do construtor
            umer.adiciona(c9);
            email21 = c9.getEmail();
            password21 = c9.getPassword();
        }  
        catch(Exception e) {
            fail();
        }
        
        try {
            umer.iniciaSessao(email21, password21);
        } catch(Exception e) {
            fail();
        }
        
        Moto moto1 = new Moto(viagens1, 50, 1, 1, espaco, "01-AA-29", 2, -1);
        Moto moto2 = new Moto(viagens1, 45, 1, 2, espaco, "09-BB-02", 3, -1);
        Moto moto3 = new Moto(viagens1, 50, 1, 1, espaco, "12-CC-12", 2, -1);
        Moto moto4 = new Moto(viagens1, 55, 1, 1, espaco, "05-DD-20", 2, -1);
        Moto moto5 = new Moto(viagens1, 40, 2, 3, espaco, "11-EE-09", 2, -1);
       
        Carrinha carrinha1 = new Carrinha(viagens1, 70, 1.2, 1, espaco, "BH-11-25", 9, -1, viagensEmEspera);
        Carrinha carrinha2 = new Carrinha(viagens1, 80, 1.5, 2, espaco, "GC-09-09", 9, -1, viagensEmEspera);
        Carrinha carrinha3 = new Carrinha(viagens1, 70, 1.2, 1, espaco, "PA-10-01", 9, -1, viagensEmEspera);
        Carrinha carrinha4 = new Carrinha(viagens1, 60, 1.0, 3, espaco, "OL-05-01", 8, -1, viagensEmEspera);
        Carrinha carrinha5 = new Carrinha(viagens1, 75, 1.0, 2, espaco, "CQ-01-17", 9, -1, viagensEmEspera);
        
        CarroLigeiro carro1 = new CarroLigeiro(viagens1, 80, 1.5, 1, espaco, "EC-05-06", -1, 5, viagensEmEspera);
        CarroLigeiro carro2 = new CarroLigeiro(viagens1, 60, 1.1, 2, espaco, "SD-02-14", -1, 5, viagensEmEspera);
        CarroLigeiro carro3 = new CarroLigeiro(viagens1, 70, 1.2, 1, espaco, "SS-10-10", -1, 5, viagensEmEspera);
        CarroLigeiro carro4 = new CarroLigeiro(viagens1, 70, 1.2, 1, espaco, "FL-11-09", -1, 5, viagensEmEspera);
        CarroLigeiro carro5 = new CarroLigeiro(viagens1, 70, 1.5, 1, espaco, "RT-05-01", -1, 5, viagensEmEspera);
        
        

        Espaco2D e1 = new Espaco2D(20.1,25.7);
        LocalDate d1 = LocalDate.of(2015, 01, 25);
        Viagem viagem1 = new Viagem(12, 5, 20, 1, e1, d1);
        
        Espaco2D e2 = new Espaco2D(23.7,20.7);
        LocalDate d2 = LocalDate.of(2014, 01, 02);
        Viagem viagem2 = new Viagem(20, 10, 30, 2, e2, d2);
        
        Espaco2D e3 = new Espaco2D(4.3,2.7);
        LocalDate d3 = LocalDate.of(2017, 11, 05);
        Viagem viagem3 = new Viagem(12, 6, 20, 3, e3, d3);
        
        Espaco2D e4 = new Espaco2D(0.1,5.7);
        LocalDate d4 = LocalDate.of(2013, 10, 25);
        Viagem viagem4 = new Viagem(14, 9, 9, 4, e4, d4);
        
        Espaco2D e5 = new Espaco2D(10.5,12.8);
        LocalDate d5 = LocalDate.of(2015, 8, 02);
        Viagem viagem5 = new Viagem(5, 5, 5, 5, e5, d5);
        
        Espaco2D e6 = new Espaco2D(5.7, 9.9);
        LocalDate d6 = LocalDate.of(2017, 03, 07);
        Viagem viagem6 = new Viagem(6, 4, 7, 6, e6, d6);
        
        Espaco2D e7 = new Espaco2D(10.0, 14.7);
        LocalDate d7 = LocalDate.of(2016, 8, 04);
        Viagem viagem7 = new Viagem(4, 7, 10, 7, e7, d7);
        
        Espaco2D e8 = new Espaco2D(5.1,25.7);
        LocalDate d8 = LocalDate.of(2016, 01, 14);
        Viagem viagem8 = new Viagem(7, 10, 13, 8, e8, d8);
        
        Espaco2D e9 = new Espaco2D(4.3, 3.0);
        LocalDate d9 = LocalDate.of(2011, 11, 11);
        Viagem viagem9 = new Viagem(8, 10, 15, 9, e9, d9);
        
        Espaco2D e10 = new Espaco2D(9.1, 2.7);
        LocalDate d10 = LocalDate.of(2014, 9, 8);
        Viagem viagem10 = new Viagem(4, 7, 10, 10, e10, d10);
        
        Espaco2D e11 = new Espaco2D(14.1,7.7);
        LocalDate d11 = LocalDate.of(2015, 10, 07);
        Viagem viagem11 = new Viagem(10, 12, 18, 11, e11, d11);
        
        Espaco2D e12 = new Espaco2D(4.1, 4.7);
        LocalDate d12 = LocalDate.of(2016, 8, 04);
        Viagem viagem12 = new Viagem(11, 8, 9, 12, e12, d12);
        
        Espaco2D e13 = new Espaco2D(5.1,7.7);
        LocalDate d13 = LocalDate.of(2014, 12, 18);
        Viagem viagem13 = new Viagem(9, 12, 15, 13, e13, d13);
        
        Espaco2D e14 = new Espaco2D(19.1, 19.7);
        LocalDate d14 = LocalDate.of(2015, 10, 31);
        Viagem viagem14 = new Viagem(4, 7, 10, 14, e14, d14);
        
        Espaco2D e15 = new Espaco2D(17.1, 18.3);
        LocalDate d15 = LocalDate.of(2014, 03, 30);
        Viagem viagem15 = new Viagem(4, 5, 6, 15, e15, d15);
        
        try {
            guardaEstado(umer,"Estado");
        } catch (FileNotFoundException e) {
            System.out.println("Erro1. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro2. " + e.getMessage());
        }
        
    }
    
     /**
     * Método que guarda em ficheiro de objectos o objecto que recebe a mensagem.
     */
    public void guardaEstado(UMeR umer, String nomeFicheiro) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(umer); //guarda-se todo o objecto de uma só vez
        oos.flush();
        oos.close();
    }
    
}

