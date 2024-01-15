/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria_manager_project;

import Models.ProdutoDAO;
import Models.Produto;
import Models.ProdutosVendidos;
import Models.ProdutosVendidosDAO;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;



public class FXMLDocumentController implements Initializable {
    @FXML
    private Tab tab_caixa;
    @FXML
    private ListView<String> listaProdutos;
    @FXML
    private ListView<String> listaCompras;
    @FXML
    private TextField txtValorTotal;
    @FXML
    private Label lbProdutosCaixa;
    @FXML
    private Button btn_c_add;
    @FXML
    private Label lbId;
    @FXML
    private Label lbProdutoComprado;
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
    private ListView<String> listaProdutosVendidos;
    @FXML
    private TableColumn<Produto, String> colId;
    @FXML
    private TableColumn<Produto, String> colNome;
    @FXML
    private TableColumn<Produto, String> colValorVenda;
    @FXML
    private TableColumn<Produto, String> colQuantidade;
    @FXML
    private TableColumn<Produto, String> colValidade;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtValorVenda;
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
    @FXML
    private TextField txtRecebido;
    @FXML
    private TextField txtTroco;
    @FXML
    private Button btn_c_remover;
    @FXML
    private TextField txtLucrosMensais;
    @FXML
    private TextField txtValorNoCaixa;
    @FXML
    private DatePicker dataValidade;
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            // TODO
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colValorVenda.setCellValueFactory(new PropertyValueFactory<>("valor"));
            colValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
            colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            try { 
                ProdutoDAO dao = new ProdutoDAO();
                dao.CarregarProdutos();
                ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
                PreencherTabela();
                PreencherOpcoesCaixa();
                ProdutosVendidosDAO vendidosDao = new ProdutosVendidosDAO();
                vendidosDao.CarregarProdutosVendidos();
                PreencherProdutosVendidos();
            } catch (ParseException | SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
        }
        catch(Exception ex){
            
        }

    }
    private void PreencherTabela() throws ParseException, SQLException
    {
        
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
        String status = "";
        for(int i = 0;i < produtos.size();i++)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date hoje = new Date();
            String atual = sdf.format(hoje);
            Date today = sdf.parse(atual);
            Date validade = sdf.parse(produtos.get(i).getValidade());
            long diff = validade.getTime() - today.getTime();
            TimeUnit time = TimeUnit.DAYS;
            long diferenca = time.convert(diff, TimeUnit.MILLISECONDS);
            if (diferenca >= 30)
            {
               status = " ✅";
            }
            else if (diferenca < 30 && diferenca > 15)
            {
               status = " OK"; 
            }
            else{
               status = " ❗";
            }
            String newValidade = produtos.get(i).getValidade();
            if (newValidade.length() <= 10)
            {
               produtos.get(i).setValidade(newValidade + status); 
            }
            else
            {
                String newDate = newValidade.substring(0, 10);
                produtos.get(i).setValidade(newDate + status);    
            }
               
        }
        tabelaProduto.setItems(produtos);
        }
       
    
    //Métodos Auxiliares
    
    private void PreencherOpcoesCaixa() throws SQLException
    {
      ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
      ObservableList<String> nomesProdutos = FXCollections.observableArrayList();
      for(int i = 0; i < produtos.size(); i++)
      {
        if (produtos.get(i).getQuantidade() > 0)
        {
            nomesProdutos.add(produtos.get(i).getId() + " - " + produtos.get(i).getNome() + " - R$ " + produtos.get(i).getValor());
        }
        else
        {
            lbProdutoComprado.setText("");
        }
      }
      listaProdutos.setItems(nomesProdutos);
    }
    private void PreencherCompras() throws SQLException{
        ObservableList<String> nomesProdutos = FXCollections.observableArrayList(new ProdutoDAO().getProdutosCompras());
        listaCompras.setItems(nomesProdutos); 
    }
    private void PreencherProdutosVendidos() throws SQLException
    {
        try{
            ObservableList<ProdutosVendidos> produtosVendidos = FXCollections.observableArrayList(new ProdutosVendidosDAO().getProdutosVendidos());
            ObservableList<String> listaAuxiliar = FXCollections.observableArrayList();
            for (ProdutosVendidos p: produtosVendidos)
            {
              String valores = "";
              valores += p.getId() + " - ";
              valores += p.getNome() + " - ";
              valores += "R$" + p.getValor() + " - ";
              valores += p.getData();
              listaAuxiliar.add(valores);
              System.out.println(valores);
              
            }
            listaProdutosVendidos.setItems(listaAuxiliar);
        }
        catch(NullPointerException e)
        {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Nenhum Produto Foi Vendido Ainda");
            a.show();
        }
    }
    private void CalcularSubTotal(ObservableList<String> produtosComprados){
        float valor = 0;
        for (int i = 0; i < produtosComprados.size(); i++)
        {
           int startProdutoIndex = produtosComprados.get(i).indexOf("$") + 1;
           valor += Float.parseFloat(produtosComprados.get(i).substring(startProdutoIndex));  
        }
        txtValorTotal.setText("R$ " +String.valueOf(valor));
        
    }
    @FXML
    private void AdicionarEClicked(ActionEvent event) throws ParseException, SQLException {
        if (SomenteNumeros() && ValidadorData())
        {
            Produto p = new Produto();
            p.setId();
            BigDecimal valorVenda = new BigDecimal(txtValorVenda.getText());
            p.setValor(valorVenda);
            p.setNome(txtNome.getText().toUpperCase());
            p.setValidade(dataValidade.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            System.out.println(p.getId());
            new ProdutoDAO().AdicionarProduto(p);
            System.out.println(p.getId());
            PreencherOpcoesCaixa();
            PreencherTabela();
            LimparCampos();
        }
   
    }

    @FXML
    private void EditarEClicked(ActionEvent event) throws ParseException, SQLException {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
        if (!lbId.getText().isEmpty())
        {
        for(int i = 0; i < produtos.size(); i++)
        {
          if (produtos.get(i).getId() == Integer.parseInt(lbId.getText()))
          {
              produtos.get(i).setNome(txtNome.getText());
              BigDecimal valorVenda = new BigDecimal(txtValorVenda.getText());
              produtos.get(i).setValor(valorVenda);
              produtos.get(i).setQuantidade(Integer.parseInt(txtQuantidade.getText()));
              produtos.get(i).setValidade(dataValidade.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
              
              tabelaProduto.refresh();
              lbId.setText("");
              break;
          }
        }  
        LimparCampos();
        PreencherTabela();
        PreencherOpcoesCaixa();
        }
        
    }

    @FXML
    private void RemoverEClicked(ActionEvent event) throws ParseException, SQLException {
        ProdutoDAO produtos = new ProdutoDAO();
        System.out.println(produtos.getProdutos().size());
        for(int i = produtos.getProdutos().size()-1; i >= 0; i--)
        {
          if (produtos.getProdutos().get(i).getId() ==  Integer.parseInt(lbId.getText()))
          {
              produtos.ApagarProduto(i);
              produtos.getProdutos().remove(i);
              ProdutoDAO dao = new ProdutoDAO();
              dao.AjustarIds();
              PreencherTabela();
              PreencherOpcoesCaixa();
              LimparCampos();
              break;
          }
        }
        
    }

    @FXML
    private void MaisUmEClicked(ActionEvent event) {
        txtQuantidade.setText(String.valueOf(Integer.parseInt(txtQuantidade.getText()) + 1));
    }

    @FXML
    private void MenosUmEClicked(ActionEvent event) {
        if(Integer.parseInt(txtQuantidade.getText()) > 0)
        {
          txtQuantidade.setText(String.valueOf(Integer.parseInt(txtQuantidade.getText()) - 1));
        }        
    }

    @FXML
    private void AdicionarCClicked(Event event) throws SQLException {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
        String produtoEscolhido = lbProdutoComprado.getText();
        if(!produtoEscolhido.isEmpty())
        { 
        int indexProdutoAdicionado = Integer.parseInt(produtoEscolhido.substring(0, 1));
        produtos.get(indexProdutoAdicionado).DiminuirQuantidade(1);
        tabelaProduto.refresh();
        PreencherOpcoesCaixa();
        
        new ProdutoDAO().AdicionarCompras(produtoEscolhido);
        PreencherCompras();
        ObservableList<String> produtosComprados = FXCollections.observableArrayList(new ProdutoDAO().getProdutosCompras());
        CalcularSubTotal(produtosComprados);
        }
        else
        {
          Alert a = new Alert(AlertType.ERROR);
          a.setContentText("Selecione Um Produto");
          a.show();
        }
        
        
    }

    @FXML
    private void RegistrarCClicked(ActionEvent event) {
       try
       {
       Float.parseFloat(txtRecebido.getText());
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
       Date today = new Date();
       String dataHoje = sdf.format(today);
       ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
       ObservableList<String> comprados = FXCollections.observableArrayList(new ProdutoDAO().getProdutosCompras());
       List<String> contados = new ArrayList<String>();
       for(int i = 0; i < comprados.size();i++)
       {
           int fimIndex = comprados.get(i).indexOf("-") - 1;
           String idProduto = comprados.get(i).substring(0, fimIndex);
           int counter = 0;
           if(!JaContei(idProduto, contados))
           {
               for(int j = 0; j < comprados.size(); j++)
               {
                  if (comprados.get(j).substring(0, fimIndex).equals(idProduto))
                  { 
                     String valor = comprados.get(i) + " - " + dataHoje;
                     new ProdutoDAO().AdicionarProdutoVendido(valor);
                     String[] valores = new ProdutosVendidos().SepararValores(valor);
                     
                     ProdutosVendidos produtoVendido = new ProdutosVendidos();
                     produtoVendido.setId(Integer.parseInt(valores[0]));
                     produtoVendido.setNome(valores[1]);
                     BigDecimal b = new BigDecimal(valores[2]);
                     produtoVendido.setValor(b);
                     produtoVendido.setData(valores[3]);
                     
                     new ProdutosVendidosDAO().AdicionarProdutoVendido(produtoVendido);
                  }
               }
               contados.add(idProduto);
               tabelaProduto.refresh();               
           }   
           else
           {
               continue;
           }
           
       }
       PreencherProdutosVendidos();
       listaCompras.getItems().clear();
       txtValorNoCaixa.setText(String.valueOf((Float.parseFloat((txtValorNoCaixa.getText())) + Float.parseFloat(txtRecebido.getText())) - Float.parseFloat(txtTroco.getText().substring(2))));
       txtLucrosMensais.setText(String.valueOf(Float.parseFloat(txtLucrosMensais.getText()) + Float.parseFloat(txtValorNoCaixa.getText())));
       txtValorTotal.setText("R$ 0");
       txtRecebido.setText("");
       txtTroco.setText("R$ 0");
       new ProdutoDAO().limparProdutos();
       PreencherCompras();
       PreencherOpcoesCaixa();
       }
       catch (Exception ex)
       {
        Alert a = new Alert(AlertType.ERROR);
        a.setContentText("DIGITE OS DADOS CORRETAMENTE");
        a.show();
       }    
    }
    @FXML
    private void GetValorRecebidoC(ActionEvent event) 
    {
        int index = txtValorTotal.getText().indexOf("$") + 1;
        float diferenca = Float.parseFloat(txtRecebido.getText()) - Float.parseFloat(txtValorTotal.getText().substring(index));
        if (diferenca >= 0)
        {
            txtTroco.setText("R$ " + String.valueOf(diferenca));
        }
    }

    @FXML
    private void PesquisarEClicked(ActionEvent event) throws ParseException, SQLException {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
        if (txtPesquisar.getText() != "")
        {
            for(int i = 0; i < produtos.size(); i++)
            {       
                if (produtos.get(i).getNome().equals(txtPesquisar.getText().toUpperCase()))
                {
                    lbId.setText(String.valueOf(produtos.get(i).getId()));
                    txtNome.setText(produtos.get(i).getNome());
                    txtValorVenda.setText(produtos.get(i).getValor().toString());
                    txtQuantidade.setText(String.valueOf(produtos.get(i).getQuantidade()));
                    //txtValidade.setText(produtos.get(i).getValidade());
                    dataValidade.setValue(LocalDate.parse(produtos.get(i).getValidade()));
                    break;
                }
            }
         
        }      
    }
    @FXML
    private void FecharMesAClicked(ActionEvent event)
    {
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setContentText("Deseja Realmente Fechar O Mês?");
        a.show();
        a.setOnCloseRequest(new EventHandler<DialogEvent>() 
        {
        @Override
        public void handle(DialogEvent event) 
        {
        ButtonType result=a.getResult();
        String resultText=result.getText();
        
        if (resultText == "OK")
            {
              //Código do Banco de Dados      
            }
        }
        });
    }
    @FXML
    private void FecharCaixaAClicked(ActionEvent event)
    {
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setContentText("Deseja Realmente Fechar O Caixa?");
        a.show();
        a.setOnCloseRequest(new EventHandler<DialogEvent>() 
        {
        @Override
        public void handle(DialogEvent event) 
        {
        ButtonType result=a.getResult();
        String resultText=result.getText();
        
        if (resultText == "OK")
            {
             //Código do Banco de Dados 
            }
        }
        });
    }
    @FXML
    private void RemoverCCompras(ActionEvent event) throws SQLException
    {
       if (!lbProdutosCaixa.getText().isEmpty())
       {
           String indexProduto  = listaCompras.getSelectionModel().getSelectedItem().substring(0,1);
           ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
           new ProdutoDAO().removerProdutosCompras(Integer.parseInt(lbProdutosCaixa.getText()));
           produtos.get(Integer.parseInt(indexProduto)).AumentarQuantidade(1);
           PreencherCompras();
           PreencherOpcoesCaixa();
           
           tabelaProduto.refresh();
           listaCompras.refresh();
           
           ObservableList<String> produtosComprados = FXCollections.observableArrayList(new ProdutoDAO().getProdutosCompras());
           CalcularSubTotal(produtosComprados);  
       }
       else
       {
           Alert a = new Alert(AlertType.ERROR);
           a.setContentText("Nenhum Produto Foi Selecionado");
           a.show();
       }
       
    }

    @FXML
    private void tbProdutosClicked(MouseEvent event) 
    {
        int i  = tabelaProduto.getSelectionModel().getSelectedIndex();
        Produto produto = (Produto)tabelaProduto.getItems().get(i);
        lbId.setText(String.valueOf(produto.getId()));
        txtNome.setText(produto.getNome());
        txtValorVenda.setText(produto.getValor().toString());
        dataValidade.setValue(LocalDate.parse(produto.getValidade().substring(0,10), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
    }
    private void LimparCampos()
    {
        txtValorVenda.clear();
        txtNome.clear();
        txtQuantidade.setText("0");    
    }
    public int getProduto(int id) throws SQLException
    {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(new ProdutoDAO().getProdutos());
        int fim = produtos.size()-1;
        for (int inicio = 0; inicio != fim;)
        {
            inicio = (fim + inicio)/2;
            if (id < produtos.get(inicio).getId())
            {
            fim = inicio;
            inicio = 0;
            }
            else if(id > produtos.get(inicio).getId()){
                inicio = (fim + inicio + 1)/2;
                if (inicio == fim){
                    return inicio;
                }    
            }
            else
            {
                return inicio;  
            }
        }
        return 0; 
    }
    private Boolean JaContei(String id, List<String> lista){
        for(int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).equals(id))
            {
                return true;
            }
        }
        return false;
    }
    private Boolean ValidadorData() throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date hoje = new Date();
        String atual = sdf.format(hoje);
        Date today = sdf.parse(atual);
        Date validade = sdf.parse(dataValidade.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        long diff = validade.getTime() - today.getTime();
        TimeUnit time = TimeUnit.DAYS;
        long diferenca = time.convert(diff, TimeUnit.MILLISECONDS);
        if(diferenca < 0)
        {
          Alert a = new Alert(AlertType.ERROR);
          a.setContentText("PRODUTO FORA DA VALIDADE!!!!");
          a.show();
          return false;
        }
        return true;
        
    }
    
    @FXML
    private void listaProdutosClicked(MouseEvent event) 
    {
        int i  = listaProdutos.getSelectionModel().getSelectedIndex();
        String produto = listaProdutos.getItems().get(i);
        lbProdutoComprado.setText(produto);
    }
    @FXML
    private void listaProdutosCaixaClicked(MouseEvent event) 
    {
        int i  = listaCompras.getSelectionModel().getSelectedIndex();   
        //String produto = listaCompras.getItems().get(i);
        lbProdutosCaixa.setText(String.valueOf(i));
    }
    
    private boolean SomenteNumeros()
    {
      try
      {
       Float.parseFloat(txtValorVenda.getText());
       Float.parseFloat(txtQuantidade.getText());
       return true;
      }
      catch(Exception ex)
      {
        Alert a = new Alert(AlertType.ERROR);
        a.setContentText("PREENCHA OS DADOS NUMÉRICOS APENAS COM NÚMEROS!!!!");
        a.show();
        return false;
      }
    }
    
}
