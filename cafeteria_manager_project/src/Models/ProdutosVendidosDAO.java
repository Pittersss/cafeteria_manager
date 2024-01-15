/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class ProdutosVendidosDAO 
{
 private static List<ProdutosVendidos> produtosVendidos = new ArrayList<>();
 private Connection con = null;
 public ProdutosVendidosDAO() throws SQLException
 {
    con = ConnectionFactory.getConnection();
 }
 public List<ProdutosVendidos> getProdutosVendidos()
 {
     return produtosVendidos;
 }
 public boolean AdicionarProdutoVendido(ProdutosVendidos produto)
    {
        produtosVendidos.add(produto);
        String sql = "INSERT INTO produtosvendidos (id, nome, valor, data) VALUES (?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setBigDecimal(3, produto.getValor());
            stmt.setString(4, produto.getData());
            stmt.executeUpdate();
            return true;
        } 
        catch (SQLException ex) 
        {
           System.err.println("Ocorreu um erro ao salvar um produto no banco de dados - Adicionar" + ex);
           return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
   
    }
    public List<ProdutosVendidos> CarregarProdutosVendidos()
    {
        String sql = "SELECT * FROM produtosvendidos";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        produtosVendidos.clear();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                ProdutosVendidos produtoVendido = new ProdutosVendidos();
                produtoVendido.setId(rs.getInt("id"));
                produtoVendido.setNome(rs.getString("nome"));
                produtoVendido.setValor(rs.getBigDecimal("valor"));
                produtoVendido.setData(rs.getString("data"));
                produtosVendidos.add(produtoVendido);           
            }
        
            
        } catch (SQLException ex) {
            System.err.println("Ocorreu um erro ao ler os produtos no banco de dados - Select - " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);  
        }
        return produtosVendidos;
    }
 
}
