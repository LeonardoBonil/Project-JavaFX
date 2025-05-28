module com.leonardobanco.leonardoprojectbanco {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.leonardobanco.leonardoprojectbanco to javafx.fxml;
    opens com.leonardobanco.leonardoprojectbanco.controle to javafx.fxml;
    exports com.leonardobanco.leonardoprojectbanco to javafx.graphics;
}