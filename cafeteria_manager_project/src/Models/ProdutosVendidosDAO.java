/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;
import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 
}
