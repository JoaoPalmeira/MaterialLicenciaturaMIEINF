import java.util.*;
import java.io.*;

public class VOR implements Serializable{
    
    private HashMap<String,Equipa> equipas;
    
    public VOR(){
        this.equipas=new HashMap<String,Equipa>();
    }

    public VOR(HashMap<String,Equipa> equipas){
        this.equipas=equipas;
    }
    
    public VOR(VOR v){
        this.equipas=v.getEquipas();
    }
    
    public HashMap<String,Equipa> getEquipas(){
        HashMap<String,Equipa> aux=new HashMap<String,Equipa>();
        for(Equipa e:equipas.values())
            aux.put(e.getNome(),e.clone());
        return aux;
    }
    
    public Barco getBarco(String idEquipa, String idBarco) throws InvalidBoatException{
        if(equipas.containsKey(idEquipa)){
            for(Equipa e:equipas.values()){
                if(e.getNome().equals(idEquipa)){
                    if(e.getBarcos().containsKey(idBarco)){
                        for(Barco b:e.getBarcos().values()){
                            if(b.getID().equals(idBarco)){
                                return b;
                            }
                        }
                    }throw new InvalidBoatException(idBarco);
                }
            }
        }return null;
    }
    
    public ArrayList<Barco> getBarcos(String idEquipa,double milhas){
        ArrayList<Barco> barcos = new ArrayList<>();
        if(equipas.containsKey(idEquipa)){
            for(Equipa e:equipas.values()){
                if(e.getNome().equals(idEquipa)){
                    for(Barco b:e.getBarcos().values()){
                        if(b.getMilhas()>milhas){
                            barcos.add(b.clone());
                        }
                    }
                    return barcos;
                }
            }
        }return barcos;
    }
    
    public void removeBarco(String idEquipa,String idBarco) throws InvalidBoatException{
        if(equipas.containsKey(idEquipa)){
            for(Equipa e:equipas.values()){
                if(e.getNome().equals(idEquipa)){
                    if(e.getBarcos().containsKey(idBarco)){
                        for(Barco b:e.getBarcos().values()){
                            if(b.getID().equals(idBarco)){
                                equipas.remove(b);
                            }
                        }
                    }throw new InvalidBoatException(idBarco);
                }
            }
        }
    }
    
    public void gravaFicheiroTextoSeguraveis(String fich) throws IOException{
        PrintWriter x = new PrintWriter(fich);
        for(Equipa e:equipas.values()){
            for(Barco b:e.getBarcos().values()){
                if(b instanceof BarcoCatamaran || b instanceof BarcoHibrido){
                    x.println(b);
                }
            }
        }
    }
}