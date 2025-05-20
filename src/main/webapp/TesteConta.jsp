<%--
  Created by IntelliJ IDEA.
  User: Aline
  Date: 18/05/2025
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="br.com.fiap.dao.ContaDAO, br.com.fiap.model.Conta, java.sql.SQLException, java.util.List, java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Contas Cadastradas</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      line-height: 1.6;
    }
    h1 {
      color: #333;
      border-bottom: 2px solid #333;
      padding-bottom: 10px;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    th, td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }
    th {
      background-color: #f2f2f2;
    }
    .empty-message {
      color: #666;
      font-style: italic;
      margin-top: 20px;
    }
    .error {
      color: red;
      margin-top: 20px;
    }
  </style>
</head>
<body>
<h1>CONTAS CADASTRADAS</h1>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Nome</th>
    <th>Banco</th>
    <th>Saldo</th>
    <th>Tipo Conta</th>
  </tr>
  </thead>
  <tbody>
  <%
    try (ContaDAO contaDAO = new ContaDAO()) {
      List<Conta> contas = contaDAO.listar();

      // Ordena por ID decrescente e limita a 5 registros (mesma lógica do original)
      List<Conta> recentes = contas.stream()
              .sorted((c1, c2) -> c2.getIdConta().compareTo(c1.getIdConta()))
              .limit(5)
              .collect(Collectors.toList());

      if (recentes.isEmpty()) {
  %>
  <tr>
    <td colspan="5" class="empty-message">Nenhuma conta cadastrada recentemente.</td>
  </tr>
  <%
  } else {
    for (Conta c : recentes) {
  %>
  <tr>
    <td><%= c.getIdConta() %></td>
    <td><%= c.getNomeConta() %></td>
    <td><%= c.getBanco() %></td>
    <td>R$ <%= String.format("%.2f", c.getSaldo()) %></td>
    <td><%= c.getTipoConta().getDescricao() %></td>
  </tr>
  <%
      }
    }
  } catch (SQLException e) {
  %>
  <tr>
    <td colspan="5" class="error">Erro ao acessar o banco: <%= e.getMessage() %></td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>
</body>
</html>