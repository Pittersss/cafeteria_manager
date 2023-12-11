package cafeteria_manager_project;


public class Produto {
    private static int base = -1;
    private String id;
    private String valor;
    private String nome;
    private String validade;
    private String quantidade;
    
    public Produto()
    {
      base += 1;
      this.id = String.valueOf(base);  
    }
    public String getId()
    {
        return id;
    }
    
    public String getValor()
    {
        return valor;
    }
    public void setValor(String newValue)
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
    public String getValidade()
    {
        return validade;
    }
    public void setValidade(String newDate)
    {
        this.validade = newDate;
    }
    public String getQuantidade()
    {
        return quantidade;
    }
    public void setQuantidade(String newQnt)
    {
        this.quantidade = newQnt;
    }
    
}
