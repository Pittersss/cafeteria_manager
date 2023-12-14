package cafeteria_manager_project;
import cafeteria_manager_project.Produto;
import java.util.List;
import java.util.ArrayList;

public class ProdutosManager {
    private static List<Produto> produtos = new ArrayList<Produto>();
    private static List<String> produtosCaixa = new ArrayList<String>();
    private static List<String> produtosComprados = new ArrayList<String>();
    
    public void AdicionarProduto(Produto produto)
    {
        produtos.add(produto);
    }
    public List<Produto> getProdutos()
    {
        return produtos;
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
        produtosCaixa.add(produto);
    }
    public List<String> getProdutosCompras()
    {
        return produtosCaixa;
    }
}
