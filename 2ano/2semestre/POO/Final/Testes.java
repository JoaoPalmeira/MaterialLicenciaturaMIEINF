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
public class Testes{
    
    private JavaFatura javaFatura;
    private Utilizador c1,c2,c3,c4,c5,e1,e2,e3;
    private JavaFaturaApp javaApp;
    private ActividadeEconomica ae1,ae2,ae3,ae4,ae5,ae6,ae7;

    /**
     * Teste principal
     */
    @Test
    public void mainTest() {
        
        javaFatura = new JavaFatura();
        
        // Utilizador
        
        int nifC1=111111111, nifC2=222222222, nifC3=333333333, nifC4=444444444, nifC5=555555555, nifE1=123456789, nifE2=123123123, nifE3=121212121, admin=1234;
        
        String emailC1="1111@gmail.com",emailC2="2222@gmail.com",emailC3="3333@gmail.com",emailC4="4444@gmail.com", emailC5="5555@gmail.com",
               emailE1="1234@gmail.com", emailE2="1231@gmail.com",  emailE3="1212@gmail.com";
        
        String nomeC1="Andre", nomeC2="Daniel", nomeC3="Joao", nomeC4="Rafael", nomeC5="Bruno", nomeE1="Continente", nomeE2="Uminho", nomeE3="EDP";
    
        String moradaC1="Braga", moradaC2="Barcelos", moradaC3="Braga", moradaC4="Chaves", moradaC5="Viana", moradaE1="Chaves", moradaE2="Braga", moradaE3="Viana";
        
        String passC1="11", passC2="22", passC3="33", passC4="44", passC5="55", passE1="12", passE2="12", passE3="12", passAd="admin";
        
        // Contribuinte
        
        int num1=2, num2=3;
        int coef1=4, coef2=6;
        List<Integer> agregado1 = new ArrayList<>();
        List<Integer> agregado2 = new ArrayList<>();
        
        agregado1.add(nifC1); 
        agregado1.add(nifC2);
        
        agregado2.add(nifC3); 
        agregado2.add(nifC4);
        agregado2.add(nifC5);
        
        Map<Integer,ActividadeEconomica> codC1 = new HashMap<>();
        Map<Integer,ActividadeEconomica> codC2 = new HashMap<>();
        
        // Empresa
        
        String desgE1="Distribuiçao", desgE2="Universidade do Minho", desgE3="Luz/Gas";
        String locE1="Interior", locE2="Interior", locE3="Litoral";
        int coe1=4, coe2=3, coe3=2;
        
        Map<Integer,ActividadeEconomica> cod1 = new HashMap<>();
        Map<Integer,ActividadeEconomica> cod2 = new HashMap<>();
        Map<Integer,ActividadeEconomica> cod3 = new HashMap<>();
        
        // ------------  Testes  ------------//
        
        //Regista Utilizador
        
        //C1
        try {
            c1 = new Contribuinte(nifC1,emailC1,nomeC1,moradaC1,passC1,num1,coef1,agregado1,codC1);  // Preencher parâmetros do construtor
            javaFatura.adicionaU(c1);
        }  
        catch(Exception e) {
            fail();
        }
        
        int nif1 = c1.getNif();
        String pass1 = c1.getPass();
        
        try {
            javaFatura.iniciaSessao(nif1,pass1);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
        
        //C2
        try {
            c2 = new Contribuinte(nifC2,emailC2,nomeC2,moradaC2,passC2,num1,coef1,agregado1,codC1);  // Preencher parâmetros do construtor
            javaFatura.adicionaU(c2);
        }  
        catch(Exception e) {
            fail();
        }
        
        int nif2 = c2.getNif();
        String pass2 = c2.getPass();
        
        try {
            javaFatura.iniciaSessao(nif2,pass2);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
        
        //C3
        try {
            c3 = new Contribuinte(nifC3,emailC3,nomeC3,moradaC3,passC3,num2,coef2,agregado2,codC2);  // Preencher parâmetros do construtor
            javaFatura.adicionaU(c3);
        }  
        catch(Exception e) {
            fail();
        }
        
        int nif3 = c3.getNif();
        String pass3= c3.getPass();
        
        try {
            javaFatura.iniciaSessao(nif3,pass3);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
        
        //C4
        try {
            c4 = new Contribuinte(nifC4,emailC4,nomeC4,moradaC4,passC4,num2,coef2,agregado2,codC2);  // Preencher parâmetros do construtor
            javaFatura.adicionaU(c4);
        }  
        catch(Exception e) {
            fail();
        }
        
        int nif4 = c4.getNif();
        String pass4 = c4.getPass();
        
        try {
            javaFatura.iniciaSessao(nif4,pass4);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
        
        //C5
        try {
            c5 = new Contribuinte(nifC5,emailC5,nomeC5,moradaC5,passC5,num2,coef2,agregado2,codC2);  // Preencher parâmetros do construtor
            javaFatura.adicionaU(c5);
        }  
        catch(Exception e) {
            fail();
        }
        
        int nif5 = c5.getNif();
        String pass5 = c5.getPass();
        
        try {
            javaFatura.iniciaSessao(nif5,pass5);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
        
        //E1
        try {
            e1 = new Empresa(nifE1,emailE1,nomeE1,moradaE1,passE1,desgE1,locE1,coe1,cod1);  // Preencher parâmetros do construtor
            javaFatura.adicionaU(e1);
        }  
        catch(Exception e) {
            fail();
        }
        
        int nif6 = e1.getNif();
        String pass6 = e1.getPass();
        
        try {
            javaFatura.iniciaSessao(nif6,pass6);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
        
        //E2
        try {
            e2 = new Empresa(nifE2,emailE2,nomeE2,moradaE2,passE2,desgE2,locE2,coe2,cod2);  // Preencher parâmetros do construtor
            javaFatura.adicionaU(e2);
        }  
        catch(Exception e) {
            fail();
        }
        
        int nif7 = e2.getNif();
        String pass7 = e2.getPass();
        
        try {
            javaFatura.iniciaSessao(nif7,pass7);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
        
        //E3
        try {
            e3 = new Empresa(nifE3,emailE3,nomeE3,moradaE3,passE3,desgE3,locE3,coe3,cod3);  // Preencher parâmetros do construtor
            javaFatura.adicionaU(e3);
        }  
        catch(Exception e) {
            fail();
        }
        
        int nif8 = e3.getNif();
        String pass8 = e3.getPass();
        
        try {
            javaFatura.iniciaSessao(nif8,pass8);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
        
        try {
            javaFatura.iniciaSessao(admin,passAd);
        } catch(Exception e) {
           System.out.println("Erro1. " + e.getMessage());
        }
            
        //Associa Activadades Economicas C1/C2
        try{
            ae1 = new ActividadeEconomica(true,1);
            javaFatura.associarActCont(ae1,nifC1);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae2 = new ActividadeEconomica(true,2);
            javaFatura.associarActCont(ae2,nifC1);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae3 = new ActividadeEconomica(true,3);
            javaFatura.associarActCont(ae3,nifC1);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae1 = new ActividadeEconomica(true,1);
            javaFatura.associarActCont(ae1,nifC2);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae2 = new ActividadeEconomica(true,2);
            javaFatura.associarActCont(ae2,nifC2);
        }
        catch(Exception e) {
            fail();
        }
        
        //Associa Activadades Economicas C3/C4/C5
        try{
            ae1 = new ActividadeEconomica(true,1);
            javaFatura.associarActCont(ae1,nifC3);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae2 = new ActividadeEconomica(true,2);
            javaFatura.associarActCont(ae2,nifC3);
        }
        catch(Exception e) {
            fail();
        }
        
          try{
            ae3 = new ActividadeEconomica(true,3);
            javaFatura.associarActCont(ae3,nifC3);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae4 = new ActividadeEconomica(true,4);
            javaFatura.associarActCont(ae4,nifC3);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae1 = new ActividadeEconomica(true,1);
            javaFatura.associarActCont(ae1,nifC4);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae2 = new ActividadeEconomica(true,2);
            javaFatura.associarActCont(ae2,nifC4);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae3 = new ActividadeEconomica(true,3);
            javaFatura.associarActCont(ae3,nifC4);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae1 = new ActividadeEconomica(true,1);
            javaFatura.associarActCont(ae1,nifC5);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae2 = new ActividadeEconomica(true,2);
            javaFatura.associarActCont(ae2,nifC5);
        }
        catch(Exception e) {
            fail();
        }
        
        //Associa Activadades Economicas E1
        try{
            ae1 = new Restauracao(true,1,5,"Cafe-Bar");
            javaFatura.associarActEmp(ae1,nifE1);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae2 = new Educacao(true,2,3,"Material Escolar");
            javaFatura.associarActEmp(ae2,nifE1);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae3 = new Servicos(true,3,5,"Servicos","Geral");
            javaFatura.associarActEmp(ae3,nifE1);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae4 = new Saude(true,4,2,"Farmacia");
            javaFatura.associarActEmp(ae4,nifE1);
        }
        catch(Exception e) {
            fail();
        }
        
        //Associa Activadades Economicas E2
        try{
            ae1 = new Restauracao(true,1,4,"Bar Cps");
            javaFatura.associarActEmp(ae1,nifE2);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae2 = new Educacao(true,2,6,"Ensino");
            javaFatura.associarActEmp(ae2,nifE2);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae4 = new Saude(true,4,2,"MiniFarmacia");
            javaFatura.associarActEmp(ae4,nifE2);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae6 = new Transportes(true,6,8,"Autocarros AAUM","Bus");
            javaFatura.associarActEmp(ae6,nifE2);
        }
        catch(Exception e) {
            fail();
        }
        
        try{
            ae7 = new Habitacao(true,7,4,"Residencia","Apartamento");
            javaFatura.associarActEmp(ae7,nifE2);
        }
        catch(Exception e) {
            fail();
        }
        
        //Associa Activadades Economicas E3
        try{
            ae3 = new Servicos(true,3,3,"Distribuidor","Luz/Gas");
            javaFatura.associarActEmp(ae3,nifE3);
        }
        catch(Exception e) {
            fail();
        }
        
        //Aadicionar Concelhos
        Map<String,Double> concelho = new HashMap<>();
        String co1="Chaves", co2="Vila Real", co3="Guarda";
        double perc1=65, perc2=50, perc3=60;
        
        try{      
            javaFatura.adicionaConcelho(co1,perc1);
        }
        catch(Exception e) {
            fail();
        }
        
        try{      
            javaFatura.adicionaConcelho(co2,perc2);
        }
        catch(Exception e) {
            fail();
        }
        
        try{      
            javaFatura.adicionaConcelho(co3,perc3);
        }
        catch(Exception e) {
            fail();
        }
        
       //Adicionar Despesas E1
       Despesa d1 = new Despesa();
       LocalDate data1 = LocalDate.of(2018, 03, 07);
       ae1 = new ActividadeEconomica(false,1);
        
       try{
            d1 = new Despesa(1,false,nifE1," ",data1,nifC1,"Cafe",ae1,1.20);
            javaFatura.criaFaturaAss(d1);
        }
        catch(Exception e) {
            fail();
        }
        
       Despesa d2 = new Despesa();
       LocalDate data2 = LocalDate.of(2018, 04, 07);
       ae2 = new ActividadeEconomica(false,2);
        
       try{
            d2 = new Despesa(1,false,nifE1," ",data2,nifC1,"Livros",ae2,12.50);
            javaFatura.criaFaturaAss(d2);
        }
        catch(Exception e) {
            fail();
        }
       
       Despesa d3 = new Despesa();
       LocalDate data3 = LocalDate.of(2018, 02, 01);
       ae1 = new ActividadeEconomica(false,1);
        
       try{
            d3 = new Despesa(1,false,nifE1," ",data3,nifC3,"Almoco",ae1,5.00);
            javaFatura.criaFaturaAss(d3);
        }
        catch(Exception e) {
            fail();
        }
        
       Despesa d4 = new Despesa();
       LocalDate data4 = LocalDate.of(2018, 04, 03);
       ae4 = new ActividadeEconomica(false,4);
        
       try{
            d4 = new Despesa(1,false,nifE1," ",data4,nifC2,"Comprimidos",ae4,22.00);
            javaFatura.criaFaturaAss(d4);
        }
        catch(Exception e) {
            fail();
        }
        
       Despesa d5 = new Despesa();
       LocalDate data5 = LocalDate.of(2018, 05, 02);
       ae2 = new ActividadeEconomica(false,2);
        
       try{
            d5 = new Despesa(1,false,nifE1," ",data5,nifC3,"Cadernos",ae2,4.20);
            javaFatura.criaFaturaAss(d5);
        }
        catch(Exception e) {
            fail();
        }
        
       //Adicionar Despesas E2
       Despesa d6 = new Despesa();
       LocalDate data6= LocalDate.of(2018, 01, 02);
       ae2 = new ActividadeEconomica(false,2);
        
        try{
            d6 = new Despesa(1,false,nifE2," ",data6,nifC1,"FotoCopia",ae2,2.25);
            javaFatura.criaFaturaAss(d6);
        }
        catch(Exception e) {
            fail();
        }
        
       Despesa d7 = new Despesa();
       LocalDate data7 = LocalDate.of(2018, 02, 02);
       ae1 = new ActividadeEconomica(false,1);
        
        try{
            d7 = new Despesa(1,false,nifE2," ",data7,nifC4,"Bar",ae1,1.00);
            javaFatura.criaFaturaAss(d7);
        }
        catch(Exception e) {
            fail();
        }
        
       Despesa d8 = new Despesa();
       LocalDate data8= LocalDate.of(2018, 05, 02);
       ae7 = new ActividadeEconomica(false,7);
        
        try{
            d8 = new Despesa(1,false,nifE2," ",data8,nifC5,"Renda",ae7,125);
            javaFatura.criaFaturaAss(d8);
        }
        catch(Exception e) {
            fail();
        }
                
       //Adicionar Despesas E3
       Despesa d9 = new Despesa();
       LocalDate data9= LocalDate.of(2018, 04, 12);
       ae3 = new ActividadeEconomica(false,3);
        
        try{
            d9 = new Despesa(1,false,nifE3," ",data9,nifC3,"Conta-Luz",ae3,20.23);
            javaFatura.criaFaturaAss(d9);
        }
        catch(Exception e) {
            fail();
        }
        
       Despesa d10 = new Despesa();
       LocalDate data10= LocalDate.of(2018, 02, 22);
       ae3 = new ActividadeEconomica(false,3);
        
        try{
            d10 = new Despesa(1,false,nifE3," ",data10,nifC2,"Conta-Gas",ae3,21.00);
            javaFatura.criaFaturaAss(d10);
        }
        catch(Exception e) {
            fail();
        }

        try {
            guardaEstado(javaFatura,"Estado");
        } catch (FileNotFoundException e) {
            System.out.println("Erro1. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro2. " + e.getMessage());
        }
    }
     
     /**
      * Método que guarda em ficheiro de objectos o objecto que recebe a mensagem.
      */
    
     public void guardaEstado(JavaFatura jf, String nomeFicheiro) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(jf); //guarda-se todo o objecto de uma só vez
        oos.flush();
        oos.close();
    }
}

