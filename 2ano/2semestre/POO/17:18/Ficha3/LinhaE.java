import java.util.*;
import java.lang.*;
/**
 * Escreva a descrição da classe LinhaE aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class LinhaE
{
    private String produto;
    private String descricao;
    private int quantidade;
    private float precoSemI;
    private float imposto;
    private float desconto;
    
    //Construtores
    
    public LinhaE(){
        this.produto="";
        this.descricao="";
        this.quantidade=0;
        this.precoSemI=0;
        this.imposto=0;
        this.desconto=0;
    }
    
    public LinhaE(String p, String d, int q, float psi, float i, float desc){
        this.produto=p;
        this.descricao=d;
        this.quantidade=q;
        this.precoSemI=psi;
        this.imposto=i;
        this.desconto=desc;
    }
    
    public LinhaE(LinhaE l){
        this.produto=l.getProduto();
        this.descricao=l.getDescricao();
        this.quantidade=l.getQuantidade();
        this.precoSemI=l.getPrecoSemI();
        this.imposto=l.getImposto();
        this.desconto=l.getDesconto();
    }
    
    
    //Getters and Setters
    
    public String getProduto(){
        return this.produto;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public float getPrecoSemI(){
        return this.precoSemI;
    }
    
    public float getImposto(){
        return this.imposto;
    }
    
    public float getDesconto(){
        return this.desconto;
    }
    
    public void setProduto (String p){
        this.produto=p;
    }
    
    public void setDescricao (String d){
        this.descricao=d;
    }
    
    public void setQuantidade (int q){
        this.quantidade=q;
    }
    
    public void setPrecoSemI (float psi){
        this.precoSemI=psi;
    }
    
    public void setImposto (float i){
        this.imposto=i;
    }
    
    public void setDesconto (float d){
        this.desconto=d;
    }
    
    //Funcionalidades
    
    public double calculaValorLinhaEnc(){
        double valor;
        valor = (this.precoSemI *(this.imposto/100))*(this.desconto/100);
        return valor;
    }
    
    public double calculaValorDesconto(){
        double valor;
        valor = (this.precoSemI *(this.desconto/100));
        return valor;
    }
    
    public boolean equals(Object o){
        if(o==this){
            return true;
        }
        if (o ==null || o.getClass() != this.getClass()){
            return false;
        }
        LinhaE l = (LinhaE) o;
        return (l.getProduto().equals(this.produto) && 
                l.getDescricao().equals(this.descricao) && 
                l.getQuantidade() == this.quantidade && 
                l.getPrecoSemI() == this.precoSemI &&
                l.getImposto() == this.imposto &&
                l.getDesconto() == this.desconto);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(this.produto).append("\n");
        sb.append("Descricao: ").append(this.descricao).append("\n");
        sb.append("Quantidade: ").append(this.quantidade).append("\n");
        sb.append("Preço Sem Imposto: ").append(this.precoSemI).append("\n");
        sb.append("Imposto: ").append(this.imposto).append("\n");
        sb.append("Desconto: ").append(this.desconto).append("\n");
        return sb.toString();
    }
    
    public LinhaE clone(){
        return new LinhaE(this);
    }
    
}
