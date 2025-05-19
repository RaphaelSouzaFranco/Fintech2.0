package br.com.fiap.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Despesa {

    private int idDespesa;
    private BigDecimal valor;
    private Date dataPagamento;
    private Date vencimento;
    private String descricao;
    private String categoriaDespesa;
    private String statusDespesa;
    private char recorrente;
    private int usuarioId;
    private int contaId;

    // Construtor SEM ID (para CADASTRO)
    public Despesa(BigDecimal valor, Date dataPagamento, Date vencimento, String descricao,
                   String categoriaDespesa, String statusDespesa, char recorrente, int usuarioId, int contaId) {
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.vencimento = vencimento;
        this.descricao = descricao;
        this.categoriaDespesa = categoriaDespesa;
        this.statusDespesa = statusDespesa;
        this.recorrente = recorrente;
        this.usuarioId = usuarioId;
        this.contaId = contaId;
    }

    // Construtor COM ID (para leitura e atualização)
    public Despesa(int idDespesa, BigDecimal valor, Date dataPagamento, Date vencimento, String descricao,
                   String categoriaDespesa, String statusDespesa, char recorrente, int usuarioId, int contaId) {
        this.idDespesa = idDespesa;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.vencimento = vencimento;
        this.descricao = descricao;
        this.categoriaDespesa = categoriaDespesa;
        this.statusDespesa = statusDespesa;
        this.recorrente = recorrente;
        this.usuarioId = usuarioId;
        this.contaId = contaId;
    }

    // Getters e Setters
    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(String categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }

    public String getStatusDespesa() {
        return statusDespesa;
    }

    public void setStatusDespesa(String statusDespesa) {
        this.statusDespesa = statusDespesa;
    }

    public char getRecorrente() {
        return recorrente;
    }

    public void setRecorrente(char recorrente) {
        this.recorrente = recorrente;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getContaId() {
        return contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    // Para facilitar debug e logs
    @Override
    public String toString() {
        return "Despesa{" +
                "idDespesa=" + idDespesa +
                ", valor=" + valor +
                ", dataPagamento=" + dataPagamento +
                ", vencimento=" + vencimento +
                ", descricao='" + descricao + '\'' +
                ", categoriaDespesa='" + categoriaDespesa + '\'' +
                ", statusDespesa='" + statusDespesa + '\'' +
                ", recorrente=" + recorrente +
                ", usuarioId=" + usuarioId +
                ", contaId=" + contaId +
                '}';
    }
}
