
/**
 * Escreva a descrição da interface CartãoPontos aqui.
 * 
 * @author (seu nome) 
 * @version (número da versão ou data)
 */

public interface CartaoPontos
{
    public int obterValorPontos();
    
    public void definirValorPontos(int pontos);
    
    /**
     * Devolve o número de pontos por noite
     */
    public double calculaPontos();
}
