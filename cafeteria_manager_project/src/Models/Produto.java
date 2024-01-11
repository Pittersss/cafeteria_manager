package Models;

import java.math.BigDecimal;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Produto{
    //public static int base = -1;
    private int id;
    private BigDecimal valor;
    private String nome;
    private String validade;
    private int quantidade;
    private static int vendidos;
    
    public Produto()
    {
      this.vendidos = 0;
      //base += 1;
      //this.id = base;  
    }
    public int getId()
    {
        return id;
    }
    public void setId(int newId)
    {
         this.id = newId;
    }
    public void setId() throws SQLException
    {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
        if (!produtos.isEmpty())
        {
           this.id = produtos.size(); 
        }
        else
        {
            this.id = 0;
        }
        
    }
    
    public BigDecimal getValor()
    {
        return valor;
    }
    public void setValor (BigDecimal newValue)
    {
        this.valor = newValue;
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
