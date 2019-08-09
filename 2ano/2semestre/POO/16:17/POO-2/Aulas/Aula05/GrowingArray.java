/**
 * Implementação parcial da classe que representa um array dinâmico.
 * NOTA: Esta classe utiliza uma noção estrita de composição.
 * A FAZER: comente os métodos.
 * 
 * @author José Creissac Campos
 * @version 20160321
 */

import java.util.Arrays;
public class GrowingArray {
    private Aluno[] garr;
    private int tam;
    
    private static final int CapInicial = 2;
    
    public GrowingArray() {
        this.garr = new Aluno[GrowingArray.CapInicial];
        this.tam = 0;
    }
    
    public GrowingArray(int cap) {
        this.garr = new Aluno[cap];
        this.tam = 0;
    }
    
    public Aluno get(int i) {
        if (i>0 && i<tam)
            return garr[i].clone();
        else
            return null;
    }
    
    public void add(Aluno a) {
        garanteCapacidade(tam+1);
        garr[tam++] = a.clone();
    }
    
    public void garanteCapacidade(int size) {
        if (size>garr.length) {
            Aluno[] aux = new Aluno[Math.max(garr.length*2, size)];
            System.arraycopy(garr, 0, aux, 0, garr.length);
            garr = aux;
        }
    }
    
}
