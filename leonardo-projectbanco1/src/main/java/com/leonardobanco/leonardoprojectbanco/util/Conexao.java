package com.leonardobanco.leonardoprojectbanco.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL =  "jdbc:mysql://localhost:3306/bancoEstudante";
    private static final String USUARIO = "root";
    private static final String SENHA = "Leonardo@1";
    public static Connection obterConexao() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException("erro ao obter conexao:" + e.getMessage());

        }
    }
    public static void fecharConexao(Connection connection) throws SQLException {
        if (connection != null && connection.isClosed()){
            connection.close();
        }
    }
}
