
/**
 * Classe Consulta: Aquando da consulta de um imóvel, é gerada uma
 * String automática com o ID do Imóvel e Mail do Utilizador. 
 * É posteriormente armazenada na classe Imoobiliaria.
 */
import java.lang.*;
import java.util.*;
import java.text.*;
public class Consulta
{
    // Variáveis de Instância
    private String genAuto;
    private Date hora;
    
    // Construtores
    public Consulta(String m, String i) {
        genAuto = "CONSULTA:  " + m + " - ID: " + i;
        hora =  new Date();
    }
    public Consulta (Consulta c) {
        this.genAuto = c.getComentario();
        this.hora = c.getData();
    }
    // Métodos de Instância
    public Consulta clone() {
        return new Consulta(this);
    }
    public String getComentario() {
        return this.genAuto;
    }
    public Date getData() {
        return hora;
    }
    /**
     * Hora atual da visita de um Imóvel, já com formatação.
     */
    public String getHoraVisita() {
        SimpleDateFormat formato = new SimpleDateFormat ("hh:mm");
        return formato.format(this.getData());
    }
    
    
   
}
