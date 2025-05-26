package br.com.fiap.dao;

import br.com.fiap.exception.EntidadeNaoEncontrada;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Despesa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDao {
    private final Connection conexao;

    public DespesaDao() throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
        conexao.setAutoCommit(false); // Desabilita autocommit
    }

    public void cadastrar(Despesa despesa) throws SQLException {
        String sql = "INSERT INTO DESPESA (ID_DESPESA, VALOR, DATA_PAGAMENTO, VENCIMENTO, DESCRICAO, " +
                "CATEGORIA_DESPESA, FORMA_PAGAMENTO, STATUS_DESPESA, RECORRENTE, USUARIO_ID_USUARIO, CONTA_ID_CONTA) " +
                "VALUES (seq_despesa.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setBigDecimal(1, despesa.getValor());
            stm.setDate(2, new Date(despesa.getDataPagamento().getTime()));
            stm.setDate(3, despesa.getVencimento() != null ?
                    new Date(despesa.getVencimento().getTime()) : null);
            stm.setString(4, despesa.getDescricao());
            stm.setString(5, despesa.getCategoriaDespesa());
            stm.setString(6, despesa.getFormaPagamento());
            stm.setString(7, despesa.getStatusDespesa());
            stm.setString(8, String.valueOf(despesa.getRecorrente()));
            stm.setInt(9, despesa.getUsuarioId());
            stm.setInt(10, despesa.getContaId());

            System.out.println("Executando: " + stm.toString()); // Log da query

            int linhasAfetadas = stm.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);

            if (linhasAfetadas > 0) {
                conexao.commit();
            }

        } catch (SQLException e) {
            conexao.rollback(); // Adicione rollback em caso de erro
            System.err.println("Erro ao cadastrar: " + e.getMessage());
            throw e;
        }

    }

    public List<br.com.fiap.model.Despesa> pesquisarPorDescricao(String descricao) throws SQLException, EntidadeNaoEncontrada {
        String sql = "SELECT * FROM DESPESA WHERE DESCRICAO LIKE ? ORDER BY DATA_PAGAMENTO DESC";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, "%" + descricao + "%");
            ResultSet result = stm.executeQuery();

            List<Despesa> lista = new ArrayList<>();
            while (result.next()) {
                lista.add(parseDespesa(result));
            }

            if (lista.isEmpty()) {
                throw new EntidadeNaoEncontrada("Nenhuma despesa encontrada");
            }
            return lista;
        }
    }

    public List<Despesa> listar() throws SQLException {
        String sql = "SELECT * FROM DESPESA ORDER BY DATA_PAGAMENTO DESC";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();
            List<Despesa> lista = new ArrayList<>();
            while (result.next()) {
                lista.add(parseDespesa(result));
            }
            return lista;
        }
    }

    public void removerPorId(int id) throws SQLException {
        String sql = "DELETE FROM DESPESA WHERE ID_DESPESA = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, id);
            int linhasAfetadas = stm.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhuma despesa encontrada com o ID " + id);
            }

            System.out.println("Despesa com ID " + id + " removida com sucesso");
        }
    }

    public double calcularTotal() throws SQLException {
        String sql = "SELECT SUM(VALOR) AS TOTAL FROM DESPESA";
        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();
            return result.next() ? result.getDouble("TOTAL") : 0;
        }
    }

    private Despesa parseDespesa(ResultSet result) throws SQLException {
        Despesa despesa = new Despesa();
        despesa.setIdDespesa(result.getInt("ID_DESPESA"));
        despesa.setValor(result.getBigDecimal("VALOR"));
        despesa.setDataPagamento(result.getDate("DATA_PAGAMENTO"));
        despesa.setVencimento(result.getDate("VENCIMENTO"));
        despesa.setDescricao(result.getString("DESCRICAO"));
        despesa.setCategoriaDespesa(result.getString("CATEGORIA_DESPESA"));
        despesa.setFormaPagamento(result.getString("FORMA_PAGAMENTO"));
        despesa.setStatusDespesa(result.getString("STATUS_DESPESA"));
        despesa.setRecorrente(result.getString("RECORRENTE").charAt(0));
        despesa.setUsuarioId(result.getInt("USUARIO_ID_USUARIO"));
        despesa.setContaId(result.getInt("CONTA_ID_CONTA"));
        return despesa;
    }

    public Despesa pesquisarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM DESPESA WHERE ID_DESPESA = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return parseDespesa(rs);
            }
            throw new SQLException("Despesa não encontrada com ID: " + id);
        }
    }

    public void editar(Despesa despesa) throws SQLException {
        String sql = "UPDATE DESPESA SET DESCRICAO=?, VALOR=?, DATA_PAGAMENTO=?, " +
                "VENCIMENTO=?, CATEGORIA_DESPESA=?, FORMA_PAGAMENTO=?, " +
                "STATUS_DESPESA=?, RECORRENTE=? WHERE ID_DESPESA=?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, despesa.getDescricao());
            stm.setBigDecimal(2, despesa.getValor());
            stm.setDate(3, new java.sql.Date(despesa.getDataPagamento().getTime()));
            if (despesa.getVencimento() != null) {
                stm.setDate(4, new java.sql.Date(despesa.getVencimento().getTime()));
            } else {
                stm.setNull(4, java.sql.Types.DATE);
            }
            stm.setString(5, despesa.getCategoriaDespesa());
            stm.setString(6, despesa.getFormaPagamento());
            stm.setString(7, despesa.getStatusDespesa());
            stm.setString(8, String.valueOf(despesa.getRecorrente()));
            stm.setInt(9, despesa.getIdDespesa());

            stm.executeUpdate();
        }
    }



    public void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}