package Models;
import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {
    private static List<Produto> produtos = new ArrayList<Produto>();
    private static List<String> produtosCaixa = new ArrayList<String>();
    private static List<String> produtosComprados = new ArrayList<String>();
    private static List<String> produtosVendidos = new ArrayList<String>();
    private Connection con = null;
    
    public ProdutoDAO() throws SQLException
    {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean AdicionarProduto(Produto produto)
    {
        produtos.add(produto);
        String sql = "INSERT INTO produto (id, nome, valor, validade, quantidade) VALUES (?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setBigDecimal(3, produto.getValor());
            stmt.setString(4, produto.getValidade());
            stmt.setInt(5, produto.getQuantidade());
            stmt.executeUpdate();
            return true;
        } 
        catch (SQLException ex) 
        {
           System.err.println("Ocorreu um erro ao salvar um produto no banco de dados - Adicionar");
           return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
   
    }
    
    public boolean EditarProduto(Produto produto)
    {
        String sql = "UPDATE produto SET nome = ?, valor = ?, validade = ?, quantidade = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setBigDecimal(2, produto.getValor());
            stmt.setString(3, produto.getValidade());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getId());
            stmt.executeUpdate();
            return true;
        } 
        catch (SQLException ex) 
        {
           System.err.println("Ocorreu um erro ao salvar um produto no banco de dados - Editar");
           return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Produto> ListarProdutos()
    {
        String sql = "SELECT * FROM produto";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        produtos.clear();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValor(rs.getBigDecimal("valor"));
                produto.setValidade(rs.getString("validade"));
                produtos.add(produto);           
            }
        
            
        } catch (SQLException ex) {
            System.err.println("Ocorreu um erro ao ler os produtos no banco de dados - Select - " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);  
        }
        return produtos;
    }
    
    public List<Produto> getProdutos()
    {   
        return produtos;
    }
    public boolean ApagarProduto(int id)
    {
        String sql = "DELETE from produto WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } 
        catch (SQLException ex) 
        {
           System.err.println("Ocorreu um erro ao apagar um produto no banco de dados - Apagar");
           return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
            con = null;
        }
    }
    public void AdicionarProdutoVendido(String produto)
    {
        produtosVendidos.add(produto);
    }
    public List<String> getProdutosVendidos()
    {
        return produtosVendidos;
    }
    public void AjustarIds()
    { 
        String sql = "UPDATE produto SET id = ? WHERE nome = ?";
        PreparedStatement stmt = null;
        for(int i = 0; i < produtos.size(); i++)
        {
        produtos.get(i).setId(i);
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, i);
            stmt.setString(2, produtos.get(i).getNome());          
            stmt.executeUpdate();
           
        } 
        catch (SQLException ex) 
        {
            System.err.println("Ocorreu um erro ao ajustar os Ids - " + ex);
        }
        
        }
        ConnectionFactory.closeConnection(con, stmt);
    }
    public void AdicionarCaixa(String produto)
    {
        produtosCaixa.add(produto);
    }
    public List<String> getProdutosCaixa()
    {
        return produtosCaixa;
    }
    public void AdicionarCompras(String produto)
    {
        produtosComprados.add(produto);
    }
    public List<String> getProdutosCompras()
    {
        return produtosComprados;
    }
    public void removerProdutosCompras(int index)
    {
        produtosComprados.remove(index);
    }
    public void limparProdutos()
    {
        this.produtosComprados.clear();
    }
}
