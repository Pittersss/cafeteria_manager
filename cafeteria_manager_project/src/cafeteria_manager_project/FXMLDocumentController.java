/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria_manager_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;



public class FXMLDocumentController implements Initializable {
    @FXML
    private Tab tab_caixa;
    @FXML
    private ListView<String> listaProdutos;
    @FXML
    private ChoiceBox<String> chBoxProdutos;
    @FXML
    private TextField txtValorTotal;
    @FXML
    private Button btn_c_add;
    @FXML
    private Button btn_c_registrar;
    @FXML
    private Tab tab_estoque;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Produto> tabelaProduto;
    @FXML
    private TableColumn<Produto, String> colId;
    @FXML
    private TableColumn<Produto, String> colNome;
    @FXML
    private TableColumn<Produto, String> colValor;
    @FXML
    private TableColumn<Produto, String> colQuantidade;
    @FXML
    private TableColumn<Produto, String> colValidade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextField txtValidade;
    @FXML
    private Button btn_e_Add;
    @FXML
    private Button btn_e_Edit;
    @FXML
    private Button btn_e_Rmv;
    @FXML
    private Button btnMaisUm;
    @FXML
    private Button btnMenosUm;
    @FXML
    private Tab tab_admin;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colId.setCellValueFactory(new PropertyValueFactory<Produto, String>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        colValor.setCellValueFactory(new PropertyValueFactory<Produto, String>("valor"));
        colValidade.setCellValueFactory(new PropertyValueFactory<Produto, String>("validade"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<Produto, String>("quantidade"));
        PreencherTabela();
    }
    private void PreencherTabela()
    {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutosManager().getProdutos());
        tabelaProduto.setItems(produtos);
    }

    @FXML
    private void AdicionarCClicked(Event event) {
        System.out.println("Funcionou");
    }

    @FXML
    private void RegistrarCClicked(ActionEvent event) {
        System.out.println("Funcionou");

    }

    @FXML
    private void PesquisarEClicked(ActionEvent event) {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutosManager().getProdutos());
        if (txtPesquisar.getText() != "")
        {
            for(int i = 0; i < produtos.size(); i++)
            {
                
                if (produtos.get(i).getNome().equals(txtPesquisar.getText().toUpperCase()))
                {
                    txtNome.setText(produtos.get(i).getNome());
                    txtValor.setText(produtos.get(i).getValor());
                    txtQuantidade.setText(produtos.get(i).getQuantidade());
                    txtValidade.setText(produtos.get(i).getValidade());
                    break;
                }
            }
         
        }
        else
        {
            PreencherTabela();
        }
            
        
    }

    @FXML
    private void AdicionarEClicked(ActionEvent event) {
        Produto p = new Produto();
        p.setId();
        p.setValor(txtValor.getText());
        p.setNome(txtNome.getText().toUpperCase());
        p.setValidade(txtValidade.getText());
        p.setQuantidade(txtQuantidade.getText());
        new ProdutosManager().Adicionar(p);
        
        PreencherTabela();
        txtValor.clear();
        txtNome.clear();
        txtQuantidade.clear();
        txtValidade.clear();
    }

    @FXML
    private void EditarEClicked(ActionEvent event) {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutosManager().getProdutos());
       
        
         
    }

    @FXML
    private void RemoverEClicked(ActionEvent event) {
        System.out.println("Funcionou");
    }

    @FXML
    private void MaisUmEClicked(ActionEvent event) {
        System.out.println("Funcionou");
    }

    @FXML
    private void MenosUmEClicked(ActionEvent event) {
        System.out.println("Funcionou");
    }

    @FXML
    private void tbProdutosClicked(MouseEvent event) {
        int i  = tabelaProduto.getSelectionModel().getSelectedIndex();
        Produto produto = (Produto)tabelaProduto.getItems().get(i);
        txtNome.setText(produto.getNome());
        txtValor.setText(produto.getValor());
        txtValidade.setText(produto.getValidade());
        txtQuantidade.setText(produto.getQuantidade());
    }
    
}
