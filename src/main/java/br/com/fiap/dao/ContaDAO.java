package br.com.fiap.dao;

import br.com.fiap.exception.EntidadeNaoEncontrada;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Conta;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ContaDAO implements AutoCloseable {
    private Connection conexao;

    public ContaDAO() throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Conta conta) throws SQLException {
        String sql = "INSERT INTO CONTA (id_conta, nome_conta, banco, saldo, tipo_conta) " +
                "VALUES (seq_conta.nextval, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, conta.getNomeConta());
            ps.setString(2, conta.getBanco());
            ps.setBigDecimal(3, conta.getSaldo());
            ps.setString(4, conta.getTipoConta().name());
            ps.executeUpdate();
        }
    }

    public List<Conta> listar() throws SQLException {
        List<Conta> lista = new ArrayList<>();
        String sql = "SELECT id_conta, nome_conta, banco, saldo, tipo_conta FROM conta";

        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {

            while (result.next()) {
                Conta conta = new Conta(
                        result.getLong("id_conta"),
                        result.getString("nome_conta"),
                        result.getString("banco"),
                        result.getBigDecimal("saldo"),
                        result.getString("tipo_conta")
                );
                lista.add(conta);
            }
        }
        return lista;
    }

    public void remover(Long id) throws SQLException, EntidadeNaoEncontrada {
        String sql = "DELETE FROM CONTA WHERE id_conta = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new EntidadeNaoEncontrada("Conta com ID " + id + " não encontrada para remoção.");
            }
        }
    }

    @Override
    public void close() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
