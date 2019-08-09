import java.time.LocalDate;
import java.util.*;
import java.time.temporal.ChronoUnit;
/**
 * Escreva a descrição da classe CodDatas aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class CodDatas
{
    private LocalDate[] datas;
    private int numElems; //contador
    
    public CodDatas(){
        this.datas = new LocalDate[10];
        this.numElems = 0;
    }
    public CodDatas(int n){
        this.datas=new LocalDate[n];
        //this numElems=0;
    }
    public LocalDate dataMaisProxima(LocalDate data){
        LocalDate res = datas[0];
        long dist = Integer.MAX_VALUE;
        for(int i=0; i<numElems; i++){
            long dias = data.until(datas[i], ChronoUnit.DAYS);
            dias = Math.abs(dias);
            if(dias<dist){
                res = datas[i];
                dist=dias;
            }
        }
        return res;
    }
    public void insereData(LocalDate data){
        if(this.numElems < this.datas.length)
            this.datas[this.numElems++] = data;
    }
}
