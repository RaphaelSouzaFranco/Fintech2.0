<%@ page import="br.com.fiap.model.Receitas" %>
<%@ page import="br.com.fiap.dao.ReceitasDAO" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fiap.exception.EntidadeNaoEncontrada" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Gerenciar Receitas</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<%@include file="header.jsp"%>

<div class="container mt-4">

  <h2 class="mb-4">Cadastrar Receita</h2>
  <form method="post" action="receitas">
    <div class="row">
      <div class="col-md-4 mb-3">
        <label class="form-label">Valor</label>
        <input type="number" step="0.01" class="form-control" name="valor" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Data de Recebimento</label>
        <input type="date" class="form-control" name="data_Recebimento" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Descrição</label>
        <input type="text" class="form-control" name="descricao" required>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4 mb-3">
        <label class="form-label">Categoria</label>
        <input type="text" class="form-control" name="categoria_Receita" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Forma de Pagamento</label>
        <input type="text" class="form-control" name="forma_Pagamento" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Status</label>
        <input type="text" class="form-control" name="status" value="Recebido" required>
      </div>
    </div>
    <div class="row">
      <div class="col-md-6 mb-3">
        <label class="form-label">ID da Conta</label>
        <input type="number" class="form-control" name="conta_id_conta" value="1" required>
      </div>
      <div class="col-md-6 mb-3">
        <label class="form-label">ID do Usuário</label>
        <input type="number" class="form-control" name="usuario_id_usuario" value="1" required>
      </div>
    </div>
    <button type="submit" class="btn btn-success">Cadastrar Receita</button>
  </form>

  <hr class="my-4"/>

  <!-- PESQUISAR RECEITA POR ID -->
  <h3>Pesquisar Receita por ID</h3>
  <form method="get" action="receitas" class="mb-4">
    <div class="input-group w-50">
      <input type="number" class="form-control" name="buscarId" placeholder="Digite o ID da receita" required>
      <button class="btn btn-primary" type="submit">Buscar</button>
    </div>
  </form>

  <!-- Mensagens -->
  <c:if test="${not empty mensagemSucesso}">
    <div class="alert alert-success">${mensagemSucesso}</div>
  </c:if>
  <c:if test="${not empty mensagemAviso}">
    <div class="alert alert-warning">${mensagemAviso}</div>
  </c:if>
  <c:if test="${not empty mensagemErro}">
    <div class="alert alert-danger">${mensagemErro}</div>
  </c:if>

  <!-- Resultado da busca por ID -->
  <c:if test="${not empty receitaBuscada}">
    <h4>Resultado da busca:</h4>
    <ul class="list-group w-75 mb-4">
      <li class="list-group-item"><strong>ID:</strong> ${receitaBuscada.id_Receitas}</li>
      <li class="list-group-item"><strong>Valor:</strong> R$ ${receitaBuscada.valor}</li>
      <li class="list-group-item"><strong>Data:</strong> ${receitaBuscada.data_Recebimento}</li>
      <li class="list-group-item"><strong>Categoria:</strong> ${receitaBuscada.categoria_Receita}</li>
      <li class="list-group-item"><strong>Descrição:</strong> ${receitaBuscada.descricao}</li>
      <li class="list-group-item"><strong>Status:</strong> ${receitaBuscada.status}</li>
      <li class="list-group-item"><strong>Forma Pagamento:</strong> ${receitaBuscada.forma_Pagamento}</li>
      <li class="list-group-item"><strong>Conta ID:</strong> ${receitaBuscada.conta_id_conta}</li>
      <li class="list-group-item"><strong>Usuário ID:</strong> ${receitaBuscada.usuario_id_usuario}</li>
    </ul>
  </c:if>

  <!-- Listagem geral -->
  <h3>Todas as Receitas</h3>
  <table class="table table-striped table-bordered mt-3">
    <thead class="table-dark">
    <tr>
      <th>ID</th>
      <th>Valor</th>
      <th>Data</th>
      <th>Descrição</th>
      <th>Categoria</th>
      <th>Forma Pagamento</th>
      <th>Status</th>
      <th>Conta ID</th>
      <th>Usuário ID</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="r" items="${receitas}">
      <tr>
        <td>${r.id_Receitas}</td>
        <td>R$ ${r.valor}</td>
        <td>${r.data_Recebimento}</td>
        <td>${r.descricao}</td>
        <td>${r.categoria_Receita}</td>
        <td>${r.forma_Pagamento}</td>
        <td>${r.status}</td>
        <td>${r.conta_id_conta}</td>
        <td>${r.usuario_id_usuario}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
