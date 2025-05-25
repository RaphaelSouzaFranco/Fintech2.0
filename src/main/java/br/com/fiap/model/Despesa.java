package br.com.fiap.model;

import java.math.BigDecimal;
import java.util.Date;

public class Despesa {
    private int idDespesa;
    private BigDecimal valor;
    private Date dataPagamento;
    private Date vencimento;
    private String descricao;
    private String categoriaDespesa;
    private String formaPagamento;
    private String statusDespesa;
    private char recorrente;
    private int usuarioId;
    private int contaId;

    public Despesa() {}

    // getters e setters

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

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
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

    // métodos auxiliares para o JSP
    public String getNome() {
        return this.descricao;
    }

    public String getCategoria() {
        return this.categoriaDespesa;
    }

    public String getStatus() {
        return this.statusDespesa;
    }

    public String getData() {
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(this.dataPagamento);
    }

    public String getVencimentoFormatado() {
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(this.vencimento);
    }
}