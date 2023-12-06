package cafeteria_manager_project;


public class Admin {
    public String[] meses = {"Janeiro","Fevereiro","Março","Abril","Maio", "Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private float gastos;
    private float ganhos;
    private float lucro;
    private int mes;
    private boolean fecharMes;
    
    public float getGastos()
    {
        return gastos;
    }
    public void setGastos(float newGasto)
    {
        this.gastos = newGasto;
    }
    public float getGanhos()
    {
        return ganhos;
    }
    public void setGanhos(float newGanho)
    {
        this.gastos = newGanho;
    }
    public int getMes()
    {
        return mes;
    }
    public void setMes(int newMes)
    {
        this.mes = newMes;
    }
    public void setFecharMes(boolean value)
    {
        this.fecharMes = value;
        if (value){
            //Salvar Dados e armazenas em uma lista para ser vizualizada
            //Alocação do Mês
            lucro = ganhos - gastos;
        }
    }
    
}
