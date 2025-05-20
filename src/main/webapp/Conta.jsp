<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.com.fiap.model.Conta" %>
<%@page import="java.util.List" %>

<html>
<head>
    <title>Cadastro de Contas</title>
</head>
<body>
<h1>Conta</h1>
<div class="card">
    <table id="tabela-contas">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Banco</th>
            <th>Tipo</th>
            <th>Saldo</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Carteira</td>
            <td>---</td>
            <td>Física</td>
            <td>R$ 200,00</td>
        </tr>
        <tr>
            <td>Nubank</td>
            <td>Nubank</td>
            <td>Corrente</td>
            <td>R$ 1.300,00</td>
        </tr>
        <tr>
            <td>Itaú</td>
            <td>Itaú</td>
            <td>Poupança</td>
            <td>R$ 500,00</td>
        </tr>
        </tbody>
    </table>
</div>
<div class="card">
    <button id="btnNovaConta" class="botao-adicionar">Adicionar Nova Conta</button>
    <div id="formContainer" style="display: none; margin-top: 15px;">
        <input type="text" id="novoNome" placeholder="Nome" class="input-form">
        <input type="text" id="novoBanco" placeholder="Banco" class="input-form">
        <select id="novoTipo" class="input-form">
            <option value="Física">Física</option>
            <option value="Corrente">Corrente</option>
            <option value="Poupança">Poupança</option>
        </select>
        <input type="text" id="novoSaldo" placeholder="Saldo (ex: 1000,00)" class="input-form">
        <button id="btnConfirmar" class="botao-confirmar">Adicionar</button>
    </div>
</div>
<div class="grafico-container">
    <div class="grafico">
        <h2>Distribuição dos saldos por conta</h2>
        <canvas id="graficoPizza"></canvas>
    </div>
</div>

<footer>
    &copy; 2025 Finanças. Todos os direitos reservados.
</footer>
<!-- Seu script deve vir após o Chart.js -->
<script src="./js/grafico.js"></script>
</body>
</html>

