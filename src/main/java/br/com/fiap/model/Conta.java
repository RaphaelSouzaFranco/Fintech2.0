package br.com.fiap.model;

import java.math.BigDecimal;

public class Conta {
    private Long idConta;
    private String nomeConta;
    private String banco;
    private BigDecimal saldo;
    private TipoConta tipoConta;

    public enum TipoConta {
        CORRENTE("Conta Corrente"),
        POUPANCA("Conta Poupança"),
        FISICA("Conta Física");

        private final String descricao;

        TipoConta(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    // Remova os outros construtores e mantenha apenas estes dois
    public Conta(Long id, String nomeConta, String banco, BigDecimal saldo, TipoConta tipoConta) {
        this.idConta = id;
        this.nomeConta = nomeConta;
        this.banco = banco != null ? banco : "";
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }

    public Conta(Long id, String nomeConta, String banco, BigDecimal saldo, String tipoConta) {
        this.idConta = id;
        this.nomeConta = nomeConta;
        this.banco = banco != null ? banco : "";
        this.saldo = saldo;
        this.tipoConta = TipoConta.valueOf(tipoConta);
    }

    // Getters e Setters
    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }

    public String getNomeConta() { return nomeConta; }
    public void setNomeConta(String nomeConta) { this.nomeConta = nomeConta; }

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public TipoConta getTipoConta() { return tipoConta; }
    public void setTipoConta(TipoConta tipoConta) { this.tipoConta = tipoConta; }

    @Override
    public String toString() {
        return "Conta{" +
                "idConta=" + idConta +
                ", nomeConta='" + nomeConta + '\'' +
                ", banco='" + banco + '\'' +
                ", saldo=" + saldo +
                ", tipoConta=" + tipoConta +
                '}';
    }
}