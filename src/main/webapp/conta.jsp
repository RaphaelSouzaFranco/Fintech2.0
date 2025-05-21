<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.com.fiap.model.Conta" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Conta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/conta.css">
</head>
<body>
<%@include file="header.jsp"%>

<div class="container mt-4">
    <% if (request.getAttribute("mensagem") != null) { %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= request.getAttribute("mensagem") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <% } %>
    <% if (request.getAttribute("erro") != null) { %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <%= request.getAttribute("erro") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <% } %>

    <h1>Gerenciamento de Contas</h1>

    <!-- Formulário de Cadastro -->
    <div class="card mb-4">
        <div class="card-body">
            <button class="btn btn-primary mb-3" id="btnNovaConta">Adicionar Nova Conta</button>
            <form action="conta" method="post" id="formContainer" style="display: none;">
                <div class="row g-3">
                    <div class="col-md-3">
                        <input type="text" name="nomeConta" class="form-control" placeholder="Nome da Conta" required>
                    </div>
                    <div class="col-md-3">
                        <input type="text" name="banco" class="form-control" placeholder="Banco">
                    </div>
                    <div class="col-md-3">
                        <select name="tipoConta" class="form-select" required>
                            <option value="CORRENTE">Corrente</option>
                            <option value="POUPANCA">Poupança</option>
                            <option value="FISICA">Física</option>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <input type="number" name="saldo" class="form-control" step="0.01" placeholder="Saldo" required>
                    </div>
                    <div class="col-md-1">
                        <button type="submit" class="btn btn-success">Salvar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Tabela de Contas -->
    <div class="card">
        <div class="card-body">
            <table class="table" id="tabela-contas">
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Banco</th>
                    <th>Tipo</th>
                    <th>Saldo</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Conta> contas = (List<Conta>) request.getAttribute("contas");
                    if (contas != null) {
                        for (Conta conta : contas) {
                %>
                <tr>
                    <td><%= conta.getNomeConta() %></td>
                    <td><%= conta.getBanco() %></td>
                    <td><%= conta.getTipoConta() %></td>
                    <td>R$ <%= String.format("%.2f", conta.getSaldo()) %></td>
                    <td>
                        <a href="conta?action=remover&id=<%= conta.getIdConta() %>"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Tem certeza que deseja remover esta conta?')">
                            Remover
                        </a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Gráfico -->
    <div class="card mt-4">
        <div class="card-body">
            <h3>Distribuição dos saldos por conta</h3>
            <canvas id="graficoPizza"></canvas>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="resources/js/grafico.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>