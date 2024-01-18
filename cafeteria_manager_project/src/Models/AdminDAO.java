/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Connection.ConnectionFactory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TextField;

/**
 *
 * @author Pittersss
 */
public class AdminDAO {
    private Connection con = null;
    
    public AdminDAO() throws SQLException
    {
        con = ConnectionFactory.getConnection();
    }
    public void CarregarValores(TextField investimentoTotal, TextField valorCaixa, TextField valorEstoque, TextField lucros)
    {
        String sql = "SELECT * FROM admin";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                investimentoTotal.setText(rs.getBigDecimal("invest_total").toString());
                valorCaixa.setText(rs.getBigDecimal("valor_caixa").toString());
                valorEstoque.setText(rs.getBigDecimal("custosEstoque").toString());
                lucros.setText(rs.getBigDecimal("lucrosMensais").toString());           
            }
   
        } catch (SQLException ex) {
            System.err.println("Ocorreu um erro ao ler os produtos no banco de dados - Select - " + ex);
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);  
        }
    }
    
    public boolean SalvarValores(BigDecimal valorInvestido, BigDecimal valorCaixa, BigDecimal custosMensais, BigDecimal lucrosMensais)
    {
        String sql = "UPDATE admin SET invest_total = ?, valor_caixa = ?, custosEstoque = ?, lucrosMensais = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setBigDecimal(1, valorInvestido);
            stmt.setBigDecimal(2, valorCaixa);
            stmt.setBigDecimal(3, custosMensais);
            stmt.setBigDecimal(4, lucrosMensais);
            stmt.setInt(5, 0);
            stmt.executeUpdate();
            return true;
            
        } 
        catch (SQLException ex) 
        {
           System.err.println("Ocorreu um erro ao salvar um produto no banco de dados - Editar" + ex);
           return false;
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    
    }
    

