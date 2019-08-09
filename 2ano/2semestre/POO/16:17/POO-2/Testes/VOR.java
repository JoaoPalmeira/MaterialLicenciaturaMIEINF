import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
/*
 * Parte 1
 * 1)
 *  a)
 *  linha 4, retorna falso se o objeto "o" que é dado como argumento é nulo ou se o objetco "o" e o objeto que esta a ser comparado são de classes diferentes
 *  linha 7, esta a criar um novo objeto da classe Aluno, é que lhe é atribuido todas as caracteristicas, ou seja, as variaveis de instancia do objecto "o", atraves de um cast.
 *  linha 8, retorna verdadeiro se o objeto "umAluno" que é um cast do objeto "o", tem todas as variaveis de instancia iguais ao objeto que esta a ser comparado, neste caso
 *  as variaveis são o nome, numero de nota, como o nome é uma string, tem de ser fazer this.nome.equals(umAluno.getNome(), para comparar todos os caracteres do nome.
 *  b)
 *  a linha 4 continua a dar o resultado esperado, porque, a classe AlunoTE é uma subclasse da classe Aluno, ou seja, devido a hireditariedade, um objeto da classe AlunoTE 
 *  também é um objeto da classe Aluno, logo se o objeto o é nulo ou é de uma classe diferente de Aluno ou AlunoTE, o resultado é falso 
*/
public class VOR
{
    private String nome;
    private Map<String,Equipa> equipas;
    
    public Barco getBarco(String idEquipa, String idBarco) throws InvalidBoatException
    {
        if(!this.equipas.containsKey(idEquipa)) throw new InvalidBoatException("Equipa não existe!");
        else if(!this.equipas.get(idEquipa).getBarcos().containsKey(idBarco)) throw new InvalidBoatException("Barco não existe");
        else
        {
            return this.equipas.get(idEquipa).getBarcos().get(idBarco);
        }
    }
    
    public List<Barco> getBarcos(String idEquipa, double milhas)
    {
        ArrayList lista = new ArrayList();
        for(Map.Entry<String,Barco> b : this.equipas.get(idEquipa).getBarcos().entrySet())
        {
            if(b.getValue().getMilhas()>milhas) lista.add(b.getValue().clone());
        }
        return lista;
    }
    
    public void removeBarco(String idEquipa, String idBarco) throws InvalidBoatException
    {
        if(!this.equipas.containsKey(idEquipa)) throw new InvalidBoatException("Equipa não existe!");
        else if(!this.equipas.get(idEquipa).getBarcos().containsKey(idBarco)) throw new InvalidBoatException("Barco não existe");
        else
        {
            this.equipas.get(idEquipa).getBarcos().remove(idBarco);
        }
    }
    
    public void gravaFicheiroTextoSeguraveis(String fich) throws IOException
    {
        PrintWriter s = new PrintWriter(fich);
        ArrayList res = new ArrayList();
        for(Map.Entry<String,Equipa> e : this.equipas.entrySet())
        {
            for(Map.Entry<String,Barco> b : e.getValue().getBarcos().entrySet())
            {
                if(b instanceof Seguro) res.add(b.getValue().clone());
            }
        }
        s.print(res);
        s.flush();
        s.close();
    }
}
