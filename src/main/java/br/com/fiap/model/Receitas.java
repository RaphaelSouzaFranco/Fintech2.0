package br.com.fiap.model;

import java.math.BigDecimal;
import java.sql.Date;


public class Receitas {
    private int id_Receitas ;
    private Date data_Recebimento;
    private double valor;
    private String descricao;
    private String categoria_Receita;
    private String forma_Pagamento;
    private String status;
    private int conta_id_conta;
    private int usuario_id_usuario;


    public Receitas(double valor, Date data_Recebimento, int id_Receitas, String categoria_Receita, String descricao, String status, String forma_Pagamento, int conta_id_conta, int usuario_id_usuario ) {
        this.id_Receitas = id_Receitas;
        this.data_Recebimento = data_Recebimento;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria_Receita = categoria_Receita;
        this.forma_Pagamento = forma_Pagamento;
        this.status = status;
        this.conta_id_conta = conta_id_conta;
        this.usuario_id_usuario = usuario_id_usuario;
    }

    public int getUsuario_id_usuario() {
        return usuario_id_usuario;
    }

    public void setUsuario_id_usuario(int usuario_id_usuario) {
        this.usuario_id_usuario = usuario_id_usuario;
    }

    public int getConta_id_conta() {
        return conta_id_conta;
    }

    public void setConta_id_conta(int conta_id_conta) {
        this.conta_id_conta = conta_id_conta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData_Recebimento() {
        return data_Recebimento;
    }

    public void setData_Recebimento(Date data_Recebimento) {
        this.data_Recebimento = data_Recebimento;
    }

    public String getCategoria_Receita() {
        return categoria_Receita;
    }

    public void setCategoria_Receita(String categoria_Receita) {
        this.categoria_Receita = categoria_Receita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getForma_Pagamento() {
        return forma_Pagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.forma_Pagamento = formaPagamento;
    }

    public int getId_Receitas() {
        return id_Receitas;
    }

    public void setId_Receitas(int id_Receitas) {
        this.id_Receitas = id_Receitas;
    }

    public Receitas() {
    }

    public void exibirDados() {
        System.out.println("id: "+ id_Receitas);
        System.out.println("Valor: R$" + valor);
        System.out.println("Data: " + data_Recebimento);
        System.out.println("Categoria: " + categoria_Receita);
        System.out.println("Descrição: " + descricao);
        System.out.println("Status: " + status);
        System.out.println("Forma de Pagamento: "+ forma_Pagamento);
        System.out.println("Conta: "+ conta_id_conta);
        System.out.println("UsuárioId: " + usuario_id_usuario);

    }
}