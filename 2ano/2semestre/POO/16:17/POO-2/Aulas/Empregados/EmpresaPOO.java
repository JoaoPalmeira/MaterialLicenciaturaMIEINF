
/**
 * Classe EmpresaPOO, com uma estrutura de dados interna que é um TreeMap.
 * Esta classe guarda todas as instâncias de Empregado e responde a todos 
 * os métodos do enunciado.
 * 
 * @author anr
 * @version 2014.05.12
 * @version 2015.05.11
 * @version 2016.05.04 (jfc)
 */
import java.util.*;
import java.io.*;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.joining;

public class EmpresaPOO implements Serializable {
    private String nome;
    private Map<String,Empregado> empregados;

    public EmpresaPOO() {
        this("");
    }

    public EmpresaPOO(String nome) {
        this.nome = nome;
        this.empregados = new TreeMap<String,Empregado>();
    }

    public EmpresaPOO(String nome, Map<String,Empregado> emps) {
        this.nome = nome;
        // this.empregados = emps; Não!!Encapsulamento
        // this.empregados = new TreeMap<String,Empregado>();
        // for (Empregado e: emps.values())
        //     this.empregados.put(e.getCodigo(), e.clone());
        this.empregados = emps.values()
                              .stream()
                              .collect(toMap(Empregado::getCodigo,
                                             Empregado::clone));

    }    

    public EmpresaPOO(EmpresaPOO ez) {
        this.nome = ez.getNome();
        this.empregados = ez.getEmpregados();
    }

    public String getNome() {
        return this.nome;
    }

    private  TreeMap<String,Empregado> getEmpregados() {
        TreeMap<String,Empregado> aux = new TreeMap<String,Empregado>();

        for(Empregado e: this.empregados.values())
            aux.put(e.getCodigo(), e.clone());

        return aux;
    }

    /**
     * Verificar se um empregado de código dado existe.
     */
    public boolean existeEmpregado(String cod) {
        return this.empregados.containsKey(cod);          
    }    

    /**
     * Devolver a ficha de um empregado dado o seu código.
     */
    public Empregado getEmpregado(String cod) throws EmpregadoException {
        try {
            return this.empregados.get(cod).clone();
        } 
        catch (NullPointerException e) {
            throw new EmpregadoException("Empregado "+cod+" não existe!", 1);
        }
    }    

    /**
     * Inserir um novo empregado.
     */
    public void addEmpregado(Empregado e) {
        this.empregados.put(e.getCodigo(), e.clone());
    }

    /**
     * Devolver uma cópia da lista de empregados.
     */
    public void addEmpregados(Set<Empregado> emps) {
        for(Empregado e: emps)
            addEmpregado(e.clone());

    }    

    /**
     * Inserir todos os empregados de um conjunto dado.
     */
    public void remEmpregado(String cod) {
        this.empregados.remove(cod);  

    }    

    /**
     * Calcular o total de salários a pagar.
     */
    public double totalSalarios() {
        double total = 0.0;
        for(Empregado e: this.empregados.values())
            total += e.salario();  //polimorfismo!!

        return total;
    }

    /**
     * Calcular o número total de gestores da empresa.
     */
    public long totalGestores() {
        // long total = 0;
        // for(Empregado e: this.empregados.values())
        //    if (e instanceof Gestor)
        //         total++;
        // return total;    

        return this.empregados.values().stream()
                                       .filter(e->e instanceof Gestor)
                                       .count();
    }    

    /**
     * Calcular o total de empregados do tipo dado 
     * (fornecido como parâmetro sob a forma de String).
     */
    public long qtsDeTipo(String nomeClasse) {
        // long total = 0;
        // for(Empregado e: this.empregados.values())
        //     if (e.getClass().getSimpleName().equals(nomeClasse))
        //         total++;
        // return total;
        return this.empregados.values()
                              .stream()
                              .filter(e->e.getClass().getSimpleName().equals(nomeClasse))
                              .count();
    }  

    /**
     * Total de Km percorridos po todos os Motoristas.
     */
    public double qtsKmsTotal() {
        double total = 0.0;
        for(Map.Entry<String,Empregado> e : this.empregados.entrySet())
            if (e instanceof Motorista)
                total += ((Motorista)e).getNKms();
        return total;
    }    

    /**
     * Ordenar os Empregados por ordem decrescente de salário, 
     * de acordo com a comparação do Comparator ComparatorSalario.
     * 
     */
    public Set<Empregado> ordenaSalario() {
        TreeSet<Empregado> res = new TreeSet<Empregado>(new ComparatorSalario());  

        for(Empregado e: this.empregados.values())
            res.add(e.clone());
        return res;
    }  

    /**
     * Ordenar os Empregados por nome.
     */
    public Set<Empregado> ordenaPorNome() {
        TreeSet<Empregado> res = new TreeSet<Empregado>(new ComparatorNome());  

        for(Empregado e: this.empregados.values())
            res.add(e.clone());
        return res; 
    }

    /**
     * Método geral que, dado um comparador, produz um conjunto ordenado
     * de acordo com o critério pretendido.
     * 
     * Quem invoca o método tem de previamente criar um comparador de Empregado
     * passá-lo como parâmetro a este método.
     */
    public Set<Empregado> ordenaPorCriterio(Comparator<Empregado> criterio) {
        TreeSet<Empregado> res = new TreeSet<Empregado>(criterio);  

        for(Empregado e: this.empregados.values())
            res.add(e.clone());
        return res;
    }  

  
    /**
     * Três funcionários com maior salário (por ordem do salário).
     */
    public Set<String> top3Salarios() {
        //treeset que vai conter os empregados ordenados por ordem decrescente de salário
        TreeSet<Empregado> aux = new TreeSet<Empregado>(new ComparatorSalario());

        for(Empregado e: this.empregados.values())
            aux.add(e);

        /* 
        É necessário criar um conjunto de códigos para os três empregados com maior salário.
        Como o treeset é de String, se utilizarmos a ordem natural de comparação de strings
        teremos os códigos ordenados alfabeticamente, o que não é o objectivo.
        Para mantermos a ordem, vamos parametrizar o treeset por um comparator que não altere a 
        ordem dos elementos.
         */

        TreeSet<String> res = new TreeSet<String>(new ComparatorOrdemVisita());
        Iterator<Empregado> it = aux.iterator();
        int i=0;
        while(it.hasNext() && i<3) {
            i++;
            res.add(it.next().getCodigo());
        }

        return res;  
    }    

    public String toString() {
        StringBuilder sb = new StringBuilder("--- Empregados ---\n");
        // for(Empregado emp : this.empregados.values())
        //    sb.append(emp.toString() + "\n");
        sb.append(this.empregados.values()
                                 .stream()
                                 .map(Empregado::toString)
                                 .collect(joining("\n")));
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if(this == obj) return true; 
        if((obj == null) || (this.getClass() != obj.getClass())) 
            return false;
        EmpresaPOO umaEmp = (EmpresaPOO) obj;
        return this.nome.equals(umaEmp.getNome()) && this.empregados.equals(umaEmp.getEmpregados());
    }  

    public EmpresaPOO clone() {
        return new EmpresaPOO(this);
    }

    //Métodos para gravar em ficheiro de objecto e de texto
    // a fazer mais tarde.
    
     
    public void gravaObj(String fich) throws IOException { 
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich)); 
        oos.writeObject(this); 
        
        oos.flush(); 
        oos.close(); 
    } 

    public static EmpresaPOO leObj(String fich) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
      
        EmpresaPOO te= (EmpresaPOO) ois.readObject();
        
        ois.close();
        return te;
    }

    public void log(String f, boolean ap) throws IOException {
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n----------- LOG - LOG - LOG - LOG - LOG ----------------\n");
        fw.write(this.toString());
        fw.write("\n----------- LOG - LOG - LOG - LOG - LOG ----------------\n");
        fw.flush();
        fw.close();
    }
    
}
