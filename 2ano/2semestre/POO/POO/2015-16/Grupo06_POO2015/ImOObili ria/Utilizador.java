import java.lang.String;
import java.lang.Boolean;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Escreva a descrição da classe Utilizador aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Utilizador
{
     // variáveis de instância
     private String email;
     private String nome;
     private String password;
     private String morada;
     private String dataNascimento;
     private String tipoUtilizador;
     
     // construtores
     public Utilizador(){
         email = "";
         nome = "";
         password = "";
         morada = "";
         dataNascimento = "";
         tipoUtilizador = "";
     }
     
     public Utilizador (String em, String no, String passwd, String mora, String dataN, String tipoUtil){
         email = em;
         nome = no;
         password = passwd;
         morada = mora;
         dataNascimento = dataN;
         tipoUtilizador = tipoUtil;
     }
     
     public Utilizador (Utilizador u2){
         email = u2.getEmail();
         nome = u2.getNome();
         password = u2.getPassword();
         morada = u2.getMorada();
         dataNascimento = u2.getDataNascimento();
         tipoUtilizador = u2.getTipoUtilizador();
     }
     
     // métodos de instância
     public String getEmail(){
         return email;
     }
     
     public String getNome(){
         return nome;
     }
     
     public String getPassword(){
         return password;
     }
     
     public String getMorada(){
         return morada;
     }
     
     public String getDataNascimento(){
         return dataNascimento;
     }
     
     public String getTipoUtilizador(){
         return tipoUtilizador;
     }
     
     public void setEmail (String em){
         email = em;
     }
     
     public void setNome (String no){
         nome = no;
     }
     
     public void setPassword (String passwd){
         password = passwd;
     }
     
     public void setMorada (String mora){
         morada = mora;
     }
     
     public void setDataNascimento (String dataN){
         dataNascimento = dataN;
     }
     
     public void setTipoUtilizador (String tipoU){
         tipoUtilizador = tipoU;
     }
     
      /**
     * Retorna uma cópia da instância
     */
     public Utilizador clone(){
        return new Utilizador(this);
     }
     
     /**
     * Compara a igualdade com outro objeto
     */
     public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Utilizador o = (Utilizador) obj;
        return o.getEmail().equals(email) && o.getNome().equals(nome) &&
               o.getPassword().equals(password) && o.getMorada().equals(morada) && 
               o.getDataNascimento().equals(dataNascimento) && o.getTipoUtilizador().equals(tipoUtilizador);
     }
    
     /**
      * Devolve uma representação textual do utilizador
      */
     public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("------------ UTILIZADOR ------------\n");
        s.append("Email: " + email + "\n");
        s.append("Nome: " + nome + "\n");
        s.append("Password: " + password + "\n");
        s.append("Morada: " + morada + "\n");
        s.append("Data de Nascimento: " + dataNascimento + "\n");
        s.append("Tipo de Utilizador: " + tipoUtilizador + "\n");
        return s.toString();
     }
}
