package com.leonardobanco.leonardoprojectbanco.controle;

import com.leonardobanco.leonardoprojectbanco.dao.EstudanteDao;
import com.leonardobanco.leonardoprojectbanco.modelo.Estudante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EstudanteControle implements Initializable {

    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_idade;
    @FXML
    private TextField tf_endereco;
    @FXML
    private TextField tf_telefone;
    @FXML
    private RadioButton rb_m;
    @FXML
    private RadioButton rb_f;
    @FXML
    private RadioButton rb_d;
    @FXML
    private RadioButton rb_n;

    @FXML
    private TableView<Estudante> tv_view;
    @FXML
    private TableColumn<Estudante, Integer> tc_id;
    @FXML
    private TableColumn<Estudante, String> tc_nome;
    @FXML
    private TableColumn<Estudante, Integer> tc_idade;
    @FXML
    private TableColumn<Estudante, String> tc_sexo;
    @FXML
    private TableColumn<Estudante, String> tc_turno;
    @FXML
    private TableColumn<Estudante, String> tc_endereco;
    @FXML
    private TableColumn<Estudante, String> tc_telefone;

    private EstudanteDao estudanteDao;
    private ObservableList<Estudante> listaEstudantes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Inicializar o DAO e a lista observável
            estudanteDao = new EstudanteDao();
            listaEstudantes = FXCollections.observableArrayList();

            // Configurar grupos de RadioButton
            ToggleGroup sexoGroup = new ToggleGroup();
            rb_m.setToggleGroup(sexoGroup);
            rb_f.setToggleGroup(sexoGroup);

            ToggleGroup turnoGroup = new ToggleGroup();
            rb_d.setToggleGroup(turnoGroup);
            rb_n.setToggleGroup(turnoGroup);

            // Configurar colunas da tabela
            configurarColunas();

            // Associar a lista observável à tabela
            tv_view.setItems(listaEstudantes);

            // Carregar dados do banco
            carregarEstudantes();

            // Debug - imprimir quantidade de estudantes carregados
            System.out.println("Número de estudantes carregados: " + listaEstudantes.size());

        } catch (Exception e) {
            e.printStackTrace();
            mostrarErro("Erro na inicialização", "Não foi possível carregar os dados: " + e.getMessage());
        }
    }

    private void configurarColunas() {
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tc_idade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        tc_sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tc_turno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        tc_endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tc_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    }

    private void carregarEstudantes() {
        try {
            // Limpar a lista atual
            listaEstudantes.clear();

            // Buscar todos os estudantes do banco
            List<Estudante> estudantes = estudanteDao.buscarTodos();

            // Adicionar à lista observável
            listaEstudantes.addAll(estudantes);

            // Debug - imprimir dados dos estudantes
            for (Estudante e : estudantes) {
                System.out.println("Estudante carregado - ID: " + e.getId() + ", Nome: " + e.getNome());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarErro("Erro ao carregar dados", "Não foi possível carregar os estudantes: " + e.getMessage());
        }
    }

    @FXML
    private void enviarTrigger() {
        if (validarCampos()) {
            try {
                // Criar novo estudante
                Estudante estudante = new Estudante();
                estudante.setNome(tf_nome.getText());
                estudante.setIdade(Integer.parseInt(tf_idade.getText()));
                estudante.setEndereco(tf_endereco.getText());
                estudante.setTelefone(tf_telefone.getText());
                estudante.setSexo(rb_m.isSelected() ? "Masculino" : "Feminino");
                estudante.setTurno(rb_d.isSelected() ? "Diurno" : "Noturno");

                // Salvar no banco e recarregar a tabela
                estudanteDao.inserir(estudante);
                carregarEstudantes();

                // Limpar campos
                limparCampos();

                mostrarSucesso("Estudante cadastrado com sucesso!");

            } catch (SQLException e) {
                e.printStackTrace();
                mostrarErro("Erro ao salvar", "Não foi possível salvar o estudante: " + e.getMessage());
            }
        }
    }

    @FXML
    private void limparCampos() {
        tf_nome.clear();
        tf_idade.clear();
        tf_endereco.clear();
        tf_telefone.clear();
        rb_m.setSelected(false);
        rb_f.setSelected(false);
        rb_d.setSelected(false);
        rb_n.setSelected(false);
    }

    private boolean validarCampos() {
        StringBuilder erro = new StringBuilder();

        if (tf_nome.getText().trim().isEmpty()) {
            erro.append("Nome é obrigatório\n");
        }
        if (tf_idade.getText().trim().isEmpty()) {
            erro.append("Idade é obrigatória\n");
        } else {
            try {
                Integer.parseInt(tf_idade.getText());
            } catch (NumberFormatException e) {
                erro.append("Idade deve ser um número válido\n");
            }
        }
        if (!rb_m.isSelected() && !rb_f.isSelected()) {
            erro.append("Selecione o sexo\n");
        }
        if (!rb_d.isSelected() && !rb_n.isSelected()) {
            erro.append("Selecione o turno\n");
        }

        if (erro.length() > 0) {
            mostrarErro("Erro de Validação", erro.toString());
            return false;
        }
        return true;
    }

    private void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
