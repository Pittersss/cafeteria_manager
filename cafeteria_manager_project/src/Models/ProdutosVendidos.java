package Models;

import java.math.BigDecimal;

/**
 *
 * @author pedro
 */

public class ProdutosVendidos
{
    private int id;
    private String nomeProduto;
    private BigDecimal valor;
    private String data;
    
    public int getId()
    {
        return id;
    }
    public void setId(int newId)
    {
         this.id = newId;
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
        return nomeProduto;
    }
    public void setNome(String newName)
    {
        this.nomeProduto = newName;
    }
    public String getData()
    {
        return data;
    }
    public void setData(String newDate)
    {
        this.data = newDate;
    }
    public static String[] SepararValores(String sequencia)
    {
    //Tirar Cifrao e Espaços "ID - NOME - R$ 00.00 - DATA"
    String novaSequencia = "";
    int indexCifrao = sequencia.indexOf("$");
   
    for (int i = 0; i < sequencia.length(); i++)
    {
       if (i == indexCifrao || i == indexCifrao - 1 || sequencia.charAt(i) == ' ')
       {
           continue;
       }
       novaSequencia += sequencia.charAt(i);
    }
    String[] elementos = novaSequencia.split("-");
    
    return elementos;
    }
    
}
