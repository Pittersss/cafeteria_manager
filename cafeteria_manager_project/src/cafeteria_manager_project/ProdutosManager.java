package cafeteria_manager_project;
import cafeteria_manager_project.Produto;
import java.util.List;
import java.util.ArrayList;

public class ProdutosManager {
    private static List<Produto> produtos = new ArrayList<Produto>();
    
    public void Adicionar(Produto produto)
    {
        produtos.add(produto);
    }
    public List<Produto> getProdutos()
    {
        return produtos;
    }
}
