package br.com.fiap.model;

import java.math.BigDecimal;

public class Conta {
    private Long idConta;
    private String nomeConta;
    private String banco;
    private BigDecimal saldo;
    private TipoConta tipoConta;

    public Conta(Long id, String nome, String banco, BigDecimal saldo, TipoConta tipoConta) {
    }

    public Object getId() {
        return null;
    }

    public enum TipoConta {
        CORRENTE("Conta Corrente"),
        POUPANCA("Conta Poupança"),
        SALARIO("Conta Salário");

        private final String descricao;

        TipoConta(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

        @Override
        public String toString() {
            return descricao;
        }
    }

    public Conta(int id, String nome, String banco, BigDecimal saldo, String tipoConta) {
        this.idConta = (long) id;
        this.nomeConta = nome;
        this.banco = banco;
        this.saldo = saldo;
        this.tipoConta = TipoConta.valueOf(tipoConta);
    }

    // Getters e Setters
    public Long getIdConta() { return idConta; }
    public void setIdConta(Long idConta) { this.idConta = idConta; }
    public String getNomeConta() { return nomeConta; }
    public void setNomeConta(String nomeConta) { this.nomeConta = nomeConta; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }
    public TipoConta getTipoConta() { return tipoConta; }
    public void setTipoConta(TipoConta tipoConta) { this.tipoConta = tipoConta; }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + idConta +
                ", nome='" + nomeConta + '\'' +
                ", banco='" + banco + '\'' +
                ", saldo=" + saldo +
                ", tipoConta=" + tipoConta +
                '}';
    }
}