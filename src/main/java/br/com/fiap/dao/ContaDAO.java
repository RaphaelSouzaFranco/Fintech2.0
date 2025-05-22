package br.com.fiap.dao;

import br.com.fiap.exception.EntidadeNaoEncontrada;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Conta;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO implements AutoCloseable {
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

    public void remover(Long codigo) throws SQLException, EntidadeNaoEncontrada {
        String sql = "DELETE FROM CONTA WHERE id_conta = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, codigo);
            int linha = stm.executeUpdate();
            if (linha == 0) {
                throw new EntidadeNaoEncontrada("Conta não encontrada para ser removida");
            }
        }
    }

    public Conta pesquisar(Long codigo) throws SQLException, EntidadeNaoEncontrada {
        String sql = "SELECT * FROM conta WHERE id_conta = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setLong(1, codigo);

            try (ResultSet result = stm.executeQuery()) {
                if (!result.next()) {
                    throw new EntidadeNaoEncontrada("Conta não encontrada");
                }
                Long id = result.getLong("id_conta");
                String nome = result.getString("nome_conta");
                String banco = result.getString("banco");
                BigDecimal saldo = result.getBigDecimal("saldo");
                Conta.TipoConta tipoConta = Conta.TipoConta.valueOf(result.getString("tipo_conta"));

                return new Conta(id, nome, banco, saldo, tipoConta);
            }
        }
    }

    public List<Conta> listar() throws SQLException {
        List<Conta> lista = new ArrayList<>();
        String sql = "SELECT * FROM conta";

        try (PreparedStatement stm = conexao.prepareStatement(sql);
             ResultSet result = stm.executeQuery()) {

            while (result.next()) {
                Long id = result.getLong("id_conta");
                String nome = result.getString("nome_conta");
                String banco = result.getString("banco");
                BigDecimal saldo = result.getBigDecimal("saldo");
                Conta.TipoConta tipoConta = Conta.TipoConta.valueOf(result.getString("tipo_conta"));

                lista.add(new Conta(id, nome, banco, saldo, tipoConta));
            }
        }
        return lista;
    }

    public void atualizar(Conta conta) throws SQLException {
        String sql = "UPDATE CONTA SET nome_conta = ?, banco = ?, saldo = ?, tipo_conta = ? WHERE id_conta = ?";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, conta.getNomeConta());
            stm.setString(2, conta.getBanco());
            stm.setBigDecimal(3, conta.getSaldo());
            stm.setString(4, conta.getTipoConta().name());
            stm.setLong(5, conta.getIdConta());
            stm.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}