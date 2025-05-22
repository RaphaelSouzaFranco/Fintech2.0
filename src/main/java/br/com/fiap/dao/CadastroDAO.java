package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {

    public void cadastrar(String nome, String sobrenome, String dataNascimento, String email, String senha, String genero) {
        String sql = "INSERT INTO conta (nome, sobrenome, data_nascimento, email, senha, genero) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            stmt.setString(3, dataNascimento);
            stmt.setString(4, email);
            stmt.setString(5, senha);
            stmt.setString(6, genero);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar no banco", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
