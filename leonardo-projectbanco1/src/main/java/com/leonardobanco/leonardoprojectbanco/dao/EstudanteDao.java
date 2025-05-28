package com.leonardobanco.leonardoprojectbanco.dao;

import com.leonardobanco.leonardoprojectbanco.modelo.Estudante;
import com.leonardobanco.leonardoprojectbanco.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDao {

    public void inserir(Estudante estudante) throws SQLException {
        String sql = "INSERT INTO estudante (nome, telefone, endereco, sexo, turno, idade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estudante.getNome());
            stmt.setString(2, estudante.getTelefone());
            stmt.setString(3, estudante.getEndereco());
            stmt.setString(4, estudante.getSexo());
            stmt.setString(5, estudante.getTurno());
            stmt.setInt(6, estudante.getIdade());

            stmt.executeUpdate();
        }
    }

    public List<Estudante> buscarTodos() throws SQLException {
        List<Estudante> estudantes = new ArrayList<>();
        String sql = "SELECT * FROM estudante";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estudante estudante = new Estudante();
                estudante.setId(rs.getInt("id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setTelefone(rs.getString("telefone"));
                estudante.setEndereco(rs.getString("endereco"));
                estudante.setSexo(rs.getString("sexo"));
                estudante.setTurno(rs.getString("turno"));
                estudante.setIdade(rs.getInt("idade"));

                estudantes.add(estudante);
            }
        }

        return estudantes;
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM estudante WHERE id = ?";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void atualizar(Estudante estudante) throws SQLException {
        String sql = "UPDATE estudante SET nome = ?, telefone = ?, endereco = ?, sexo = ?, turno = ?, idade = ? WHERE id = ?";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estudante.getNome());
            stmt.setString(2, estudante.getTelefone());
            stmt.setString(3, estudante.getEndereco());
            stmt.setString(4, estudante.getSexo());
            stmt.setString(5, estudante.getTurno());
            stmt.setInt(6, estudante.getIdade());
            stmt.setInt(7, estudante.getId());

            stmt.executeUpdate();
        }
    }

    public Estudante buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM estudante WHERE id = ?";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Estudante estudante = new Estudante();
                    estudante.setId(rs.getInt("id"));
                    estudante.setNome(rs.getString("nome"));
                    estudante.setTelefone(rs.getString("telefone"));
                    estudante.setEndereco(rs.getString("endereco"));
                    estudante.setSexo(rs.getString("sexo"));
                    estudante.setTurno(rs.getString("turno"));
                    estudante.setIdade(rs.getInt("idade"));
                    return estudante;
                }
            }
        }
        return null;
    }
}
