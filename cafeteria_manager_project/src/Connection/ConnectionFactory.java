
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pedro
 */
public class ConnectionFactory 
{
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/bdlanchonete";
    public static final String USER = "root";
    public static final String PASS = "";
    
    public static Connection getConnection() throws SQLException
    {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro na conexão", ex);
        }
    }
    
    public static void closeConnection(Connection con)
    {
      if (con != null)
      {
          try {
              con.close();
          } catch (SQLException ex) 
          {
             System.err.println("Erro ao fechar conexão"); 
          }
      }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt)
    {
      if (stmt != null)
      {
          try {
              stmt.close();
          } catch (SQLException ex) 
          {
              System.err.println("Erro ao fechar conexão e Stetmant");
          }
      }
      closeConnection(con);
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs)
    {
      if (rs != null)
      {
          try {
              rs.close();
          } catch (SQLException ex) 
          {
              System.err.println("Erro ao fechar conexão, statemant e resultSet");
          }
      }
      closeConnection(con, stmt);
    }
}
