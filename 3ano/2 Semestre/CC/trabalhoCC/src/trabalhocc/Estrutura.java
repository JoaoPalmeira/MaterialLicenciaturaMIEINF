
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeter15
 */
public class Estrutura {
    
    // String --- IP
    
    HashMap<String,TabelaEstado> servidores;

    public Estrutura() {
        this.servidores = new HashMap<>();
    }
        
    
    // Fazer o refresh para atualizar a tabela
    public void refresh_estrutura(String ip,int port,int ram,int cpu){
        
        TabelaEstado te = servidores.get(ip);
        
        te.setCpu(cpu);
        te.setRam(ram);
        te.setServerPort(port);
       
        
    }
    
    
    
    public void imprimirTabela(){
        
        System.out.println("| IP             Port            Ram            Cpu |");
        System.out.println("|                                                   |");
        System.out.println("|                                                   |");
        System.out.println("|                                                   |");
        
        for(Map.Entry<String,TabelaEstado> entry : servidores.entrySet()){
            
            System.out.println(entry.getKey() + entry.getValue().getServerPort() + entry.getValue().getRam() + entry.getValue().getCpu());
            
        }
        
        
        
        
        /*
        Iterator it = servidores.entrySet().iterator();
        while(it.hasNext()){
            
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + pair.getValue() );
            
            
        }
        */
        
    }
        
    
    
    
    
    
    
    
    
    
    
}
