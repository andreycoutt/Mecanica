/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mecanica.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mecanica.model.dao.ServicoDAO;
import mecanica.model.database.PostgreSQL;
import mecanica.model.domain.ModeloVeiculo;
import mecanica.model.domain.Servicos;

/**
 * FXML Controller class
 *
 * @author Andre
 */
public class FXMLRelatorioVeiculosMaisAtendidosController implements Initializable {
    
    @FXML
    private TableView<ModeloVeiculo> tabeleviewRelatorio;
    @FXML
    private TableColumn tableColumnVeiculo;
    @FXML
    private TableColumn tableColumnNome;
    @FXML
    private TableColumn tableColumnPlaca;
    @FXML
    private TableColumn tableColumnDescricao;
    @FXML
    private TableColumn tableColumnQtdServicos;
    @FXML
    private Button buttonImprimir;
    
    @FXML
    private PieChart pieChart;
    private ObservableList<PieChart.Data> observableListPie;
    
    // Atributos para a manupulação do banco de dados
    private final PostgreSQL postgresql = new PostgreSQL();
    private final Connection connection = postgresql.conectar();
    private ServicoDAO servicoDao = new ServicoDAO();
    private List<PieChart.Data> servicos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void carregarServicos(){
        
              
        //servicos.buscar(servico);
         servicoDao.setConnection(connection);
        // Obtem os dados do servico
         servicos = servicoDao.quantidadeServicos();
        
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnQtdServicos.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        
        // Adiciona os dados no observableList e o adiciona no PieChart
        observableListPie = FXCollections.observableArrayList(servicos);
        pieChart.setData(observableListPie);
       
    }
    
}
