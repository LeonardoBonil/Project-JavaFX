package com.leonardobanco.leonardoprojectbanco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    private Stage janela;

    @Override
    public void start(Stage palco) throws Exception {
        try {
            this.janela = palco;

            // Carrega o FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telas/estudante.fxml"));
            Parent tela = loader.load();

            // Configura a cena
            Scene cena = new Scene(tela);

            // Configura a janela
            janela.setTitle("Sistema de Cadastro de Estudantes");
            janela.setScene(cena);
            janela.setResizable(true);
            janela.centerOnScreen();

            // Mostra a janela
            janela.show();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao iniciar aplicação: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
