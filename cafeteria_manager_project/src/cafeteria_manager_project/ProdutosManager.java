package cafeteria_manager_project;
import cafeteria_manager_project.Produto;
import java.util.List;
import java.util.ArrayList;

public class ProdutosManager {
    private static List<Produto> produtos = new ArrayList<Produto>();
    private static List<String> produtosCaixa = new ArrayList<String>();
    private static List<String> produtosComprados = new ArrayList<String>();
    private static List<String> produtosVendidos = new ArrayList<String>();
    
    public void AdicionarProduto(Produto produto)
    {
        produtos.add(produto);
    }
    public List<Produto> getProdutos()
    {
        return produtos;
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
        for(int i = 0; i < produtos.size(); i++)
        {
           produtos.get(i).setId(i);
        }
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
