/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.math.BigDecimal;
import java.sql.SQLException;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author pedro
 */
public class ProdutoDAONGTest {
    
    public ProdutoDAONGTest() {
    }

    @Test(enabled=false)
    public void InserirTest() throws SQLException 
    {
        Produto p = new Produto();
        p.setId(0);
        p.setNome("teste");
        p.setQuantidade(1);
        p.setValidade("20/12/2023");
        p.setValor(BigDecimal.ZERO);
        ProdutoDAO dao = new ProdutoDAO();
        if (dao.AdicionarProduto(p))
        {
            System.out.println("Inseriu produto com sucesso!");
        }
        else
        {
            System.out.println("Ocorreu um erro!");
        }
        
    }
    @Test(enabled=false)
    public void InserirVendidosTest() throws SQLException 
    {
        ProdutosVendidos p = new ProdutosVendidos();
        p.setId(0);
        p.setNome("teste");
        p.setData("20/12/2023");
        p.setValor(BigDecimal.ZERO);
        ProdutosVendidosDAO dao = new ProdutosVendidosDAO();
        if (dao.AdicionarProdutoVendido(p))
        {
            System.out.println("Inseriu produto com sucesso!");
        }
        else
        {
            System.out.println("Ocorreu um erro!");
        }
        
    }
    @Test(enabled=false)
    public void AtualizarTest() throws SQLException 
    {
        Produto p = new Produto();
        p.setId(1);
        p.setNome("testes");
        p.setQuantidade(1);
        p.setValidade("20/12/2023");
        p.setValor(BigDecimal.ZERO);
        ProdutoDAO dao = new ProdutoDAO();
        if (dao.EditarProduto(p))
        {
            System.out.println("Atualizou o produto com sucesso!");
        }
        else
        {
            System.out.println("Ocorreu um erro!");
        }
        
    }
    
    @Test(enabled=false)
    public void ListarTest() throws SQLException
    {
      ProdutoDAO dao = new ProdutoDAO();
      
      for (Produto c: dao.getProdutos())
      {
          System.out.println(c.getNome());
      }
    }
    @Test (enabled=false)
    public void ListarVendidosTest() throws SQLException
    {
      ProdutosVendidosDAO dao = new ProdutosVendidosDAO();
      
      for(ProdutosVendidos p: dao.getProdutosVendidos())
      {
          System.out.println(p.getNome());
      }
    }
    @Test(enabled=false)
    public void ApagarTest() throws SQLException
    {
      ProdutoDAO dao = new ProdutoDAO();
      int id = 0;
      System.out.println(dao.ApagarProduto(id));
    }
    
}
