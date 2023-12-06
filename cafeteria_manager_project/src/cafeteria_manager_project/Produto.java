package cafeteria_manager_project;


public class Produto {
    private float valor;
    private String nome;
    private String validade;
    private int quantidade;
    
    public float getValor()
    {
        return valor;
    }
    public void setValor(float newValue)
    {
        this.valor = newValue;
    }
    public String getNome()
    {
        return nome;
    }
    public void setNome(String newName)
    {
        this.nome = newName;
    }
    public String getDate()
    {
        return validade;
    }
    public void setDate(String newDate)
    {
        this.validade = newDate;
    }
    public int getQuantidade()
    {
        return quantidade;
    }
    public void setQuantidade(int newQnt)
    {
        this.quantidade = newQnt;
    }
    
}
