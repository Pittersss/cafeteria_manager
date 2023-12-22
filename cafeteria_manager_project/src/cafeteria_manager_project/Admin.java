package cafeteria_manager_project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class Admin {
    public String[] meses = {"Janeiro","Fevereiro","Março","Abril","Maio", "Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private static List<String> produtosVendidosMes = new ArrayList<String>();
    private float investimentoTotal;
    private float valorVendidoDia;
    private float gastosMensais;
    private float ganhosMensais;
    private float lucro;
    private boolean fecharMes;
    
    public float getGastosMensais()
    {
        return gastosMensais;
    }
    public void setGastosMensais(float newGasto)
    {
        this.gastosMensais = newGasto;
    }
    public float getGanhosMensais()
    {
        return ganhosMensais;
    }
    public void setGanhos(float newGanho)
    {
        this.gastosMensais = newGanho;
    }
    
    public void setFecharMes(boolean value)
    {
        this.fecharMes = value;
        if (value){
            //Salvar Dados e armazenas em uma lista para ser vizualizada
            //Alocação do Mês
            lucro = ganhosMensais - gastosMensais;
        }
    }
    public List<String> getVendidos(){
        return produtosVendidosMes;
    }
    public void AddProdutoVendidos(String produto){
        produtosVendidosMes.add(produto);
    }
    
    
}
