package br.com.fiap.model;

import java.time.LocalDate;

public class Divida {

    private int ID_DIVIDA;
    private double VALOR;
    private LocalDate DATA_INICIO;
    private LocalDate DATA_VENCIMENTO;
    private String DESCRICAO;
    private int PARCELAS;
    private double VALOR_PARCELAS;
    private String STATUS_DIVIDA;
    private String CREDOR;
    private double TAXA_JUROS;
    private String FORMA_PAGAMENTO;
    private String ORIGEM_DIVIDA;
    private int USUARIO_ID_USUARIO;
    private int CONTA_ID_CONTA;

    public Divida() {
    }

    public Divida(int ID_DIVIDA, double VALOR, LocalDate DATA_INICIO, LocalDate DATA_VENCIMENTO, String DESCRICAO,
                  int PARCELAS, double VALOR_PARCELAS, String STATUS_DIVIDA, String CREDOR, double TAXA_JUROS,
                  String FORMA_PAGAMENTO, String ORIGEM_DIVIDA, int USUARIO_ID_USUARIO, int CONTA_ID_CONTA) {
        this.ID_DIVIDA = ID_DIVIDA;
        this.VALOR = VALOR;
        this.DATA_INICIO = DATA_INICIO;
        this.DATA_VENCIMENTO = DATA_VENCIMENTO;
        this.DESCRICAO = DESCRICAO;
        this.PARCELAS = PARCELAS;
        this.VALOR_PARCELAS = VALOR_PARCELAS;
        this.STATUS_DIVIDA = STATUS_DIVIDA;
        this.CREDOR = CREDOR;
        this.TAXA_JUROS = TAXA_JUROS;
        this.FORMA_PAGAMENTO = FORMA_PAGAMENTO;
        this.ORIGEM_DIVIDA = ORIGEM_DIVIDA;
        this.USUARIO_ID_USUARIO = USUARIO_ID_USUARIO;
        this.CONTA_ID_CONTA = CONTA_ID_CONTA;
    }

    public int getIdDivida() {
        return ID_DIVIDA;
    }

    public void setIdDivida(int ID_DIVIDA) {
        this.ID_DIVIDA = ID_DIVIDA;
    }

    public double getValor() {
        return VALOR;
    }

    public void setValor(double VALOR) {
        this.VALOR = VALOR;
    }

    public LocalDate getDataInicio() {
        return DATA_INICIO;
    }

    public void setDataInicio(LocalDate DATA_INICIO) {
        this.DATA_INICIO = DATA_INICIO;
    }

    public LocalDate getDataVencimento() {
        return DATA_VENCIMENTO;
    }

    public void setDataVencimento(LocalDate DATA_VENCIMENTO) {
        this.DATA_VENCIMENTO = DATA_VENCIMENTO;
    }

    public String getDescricao() {
        return DESCRICAO;
    }

    public void setDescricao(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }

    public int getParcelas() {
        return PARCELAS;
    }

    public void setParcelas(int PARCELAS) {
        this.PARCELAS = PARCELAS;
    }

    public double getValorParcelas() {
        return VALOR_PARCELAS;
    }

    public void setValorParcelas(double VALOR_PARCELAS) {
        this.VALOR_PARCELAS = VALOR_PARCELAS;
    }

    public String getStatusDivida() {
        return STATUS_DIVIDA;
    }

    public void setStatusDivida(String STATUS_DIVIDA) {
        this.STATUS_DIVIDA = STATUS_DIVIDA;
    }

    public String getCredor() {
        return CREDOR;
    }

    public void setCredor(String CREDOR) {
        this.CREDOR = CREDOR;
    }

    public double getTaxaJuros() {
        return TAXA_JUROS;
    }

    public void setTaxaJuros(double TAXA_JUROS) {
        this.TAXA_JUROS = TAXA_JUROS;
    }

    public String getFormaPagamento() {
        return FORMA_PAGAMENTO;
    }

    public void setFormaPagamento(String FORMA_PAGAMENTO) {
        this.FORMA_PAGAMENTO = FORMA_PAGAMENTO;
    }

    public String getOrigemDivida() {
        return ORIGEM_DIVIDA;
    }

    public void setOrigemDivida(String ORIGEM_DIVIDA) {
        this.ORIGEM_DIVIDA = ORIGEM_DIVIDA;
    }

    public int getUsuarioIdUsuario() {
        return USUARIO_ID_USUARIO;
    }

    public void setUsuarioIdUsuario(int USUARIO_ID_USUARIO) {
        this.USUARIO_ID_USUARIO = USUARIO_ID_USUARIO;
    }

    public int getContaIdConta() {
        return CONTA_ID_CONTA;
    }

    public void setContaIdConta(int CONTA_ID_CONTA) {
        this.CONTA_ID_CONTA = CONTA_ID_CONTA;
    }

    @Override
    public String toString() {
        return "Divida{" +
                "ID_DIVIDA=" + ID_DIVIDA +
                ", VALOR=" + VALOR +
                ", DATA_INICIO=" + DATA_INICIO +
                ", DATA_VENCIMENTO=" + DATA_VENCIMENTO +
                ", DESCRICAO='" + DESCRICAO + '\'' +
                ", PARCELAS=" + PARCELAS +
                ", VALOR_PARCELAS=" + VALOR_PARCELAS +
                ", STATUS_DIVIDA='" + STATUS_DIVIDA + '\'' +
                ", CREDOR='" + CREDOR + '\'' +
                ", TAXA_JUROS=" + TAXA_JUROS +
                ", FORMA_PAGAMENTO='" + FORMA_PAGAMENTO + '\'' +
                ", ORIGEM_DIVIDA='" + ORIGEM_DIVIDA + '\'' +
                ", USUARIO_ID_USUARIO=" + USUARIO_ID_USUARIO +
                ", CONTA_ID_CONTA=" + CONTA_ID_CONTA +
                '}';
    }
}
