package cafeteria_manager_project;

import java.math.BigDecimal;
import cafeteria_manager_project.ProdutosManager;


public class Produto{
    private static int base = -1;
    private int id;
    private BigDecimal valorVenda;
    private BigDecimal valorCompra;
    private String nome;
    private String validade;
    private int quantidade;
    private static int vendidos;
    
    public Produto()
    {
      this.vendidos = 0;
      base += 1;
      this.id = base;  
    }
    public int getId()
    {
        return id;
    }
    public void setId(int newId)
    {
         this.id = newId;
    }
    
    public BigDecimal getValorVenda()
    {
        return valorVenda;
    }
    public void setValorVenda(BigDecimal newValue)
    {
        this.valorVenda = newValue;
    }
    public BigDecimal getValorCompra()
    {
        return valorCompra;
    }
    public void setValorCompra(BigDecimal newValue)
    {
        this.valorCompra = newValue;
    }
    public String getNome()
    {
        return nome;
    }
    public void setNome(String newName)
    {
        this.nome = newName;
    }
    public String getValidade()
    {
        return validade;
    }
    public void setValidade(String newDate)
    {
        this.validade = newDate;
    }
    public int getQuantidade()
    {
        return quantidade;
    }
    public void setQuantidade(int newQnt)
    {
        this.quantidade = newQnt;
    }
    public void DiminuirQuantidade(int qnt)
    {
      this.quantidade = quantidade - qnt;
    }
     public void AumentarQuantidade(int qnt)
    {
      this.quantidade = quantidade + qnt;
    }
    public void ProdutoVendido(){
        this.vendidos += 1;
    }
    public int GetVendidos(){
        return vendidos;
    }
    
}
