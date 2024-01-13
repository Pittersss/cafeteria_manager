package Models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Admin {
    private static List<String> produtosVendidosMes = new ArrayList<String>();
    private BigDecimal investimentoTotal;
    private BigDecimal valorCaixa;
    private BigDecimal gastosMensais;
    private BigDecimal lucrosMensais;
    
    public BigDecimal getGastosMensais()
    {
        return gastosMensais;
    }
    public void setGastosMensais(BigDecimal newGasto)
    {
        this.gastosMensais = newGasto;
    }
    public BigDecimal getValorCaixa()
    {
        return valorCaixa;
    }
    
    public void setValorCaixa(BigDecimal newValor)
    {
        this.valorCaixa = newValor;
    }

    public void setLucro(BigDecimal newLucro)
    {
        this.lucrosMensais = newLucro;
    }
    public BigDecimal getLucro()
    {
       return lucrosMensais; 
    }
      
    public List<String> getVendidos()
    {
        return produtosVendidosMes;
    }
    public void AddProdutoVendidos(String produto)
    {
        produtosVendidosMes.add(produto);
    }
    
    
}
