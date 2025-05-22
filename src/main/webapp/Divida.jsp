<%@ page import="br.com.fiap.model.Divida" %>
<%@ page import="br.com.fiap.dao.DividaDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Gerenciar Dívidas</title>
  <link rel="stylesheet" href="resources/css/bootstrap.css">
</head>
<body>

<%@include file="header.jsp"%>

<div class="container mt-4">
  <h2 class="mb-4">Cadastrar Dívida</h2>
  <form action="Divida" method="post">
    <div class="row">
      <div class="col-md-4 mb-3">
        <label class="form-label">Valor</label>
        <input type="number" step="0.01" class="form-control" name="valor" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Data Início</label>
        <input type="date" class="form-control" name="data_inicio" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Data Vencimento</label>
        <input type="date" class="form-control" name="data_vencimento" required>
      </div>
    </div>

    <div class="row">
      <div class="col-md-4 mb-3">
        <label class="form-label">Descrição</label>
        <input type="text" class="form-control" name="descricao" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Parcelas</label>
        <input type="number" class="form-control" name="parcelas" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Valor Parcela</label>
        <input type="number" step="0.01" class="form-control" name="valor_parcelas" required>
      </div>
    </div>

    <div class="row">
      <div class="col-md-4 mb-3">
        <label class="form-label">Status (ex: Paga ou Em Andamento)</label>
        <input type="text" class="form-control" name="status_divida" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Credor</label>
        <input type="text" class="form-control" name="credor" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Taxa Juros ( ex: 2 para 2% )</label>
        <input type="number" step="0.01" class="form-control" name="taxa_juros" required>
      </div>
    </div>

    <div class="row">
      <div class="col-md-4 mb-3">
        <label class="form-label">Forma de Pagamento</label>
        <input type="text" class="form-control" name="forma_pagamento" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">Origem da Dívida</label>
        <input type="text" class="form-control" name="origem_divida" required>
      </div>
      <div class="col-md-2 mb-3">
        <label class="form-label">ID Usuário</label>
        <input type="number" class="form-control" name="usuario_id_usuario" required>
      </div>
      <div class="col-md-4 mb-3">
        <label class="form-label">ID da Conta</label>
        <input type="number" class="form-control" name="conta_id_conta" required>
      </div>
    </div>

    <button type="submit" class="btn btn-success">Cadastrar Dívida</button>
  </form>

  <hr class="my-4"/>

  <!-- Busca por ID -->
  <h3>Pesquisar Dívida por ID</h3>
  <form method="get" action="Divida" class="mb-4">
    <div class="input-group w-50">
      <input type="number" class="form-control" name="buscarId" placeholder="Digite o ID da dívida" required>
      <button class="btn btn-primary" type="submit">Buscar</button>
    </div>
  </form>

  <!-- Mensagens -->
  <c:if test="${not empty mensagemErro}">
    <div class="alert alert-danger">${mensagemErro}</div>
  </c:if>

  <c:if test="${not empty mensagemSucesso}">
    <div class="alert alert-success">${mensagemSucesso}</div>
  </c:if>

  <!-- Resultado da busca -->
  <c:if test="${not empty dividaBuscada}">
    <h4>Resultado da busca:</h4>
    <ul class="list-group w-75 mb-4">
      <li class="list-group-item"><strong>ID:</strong> ${dividaBuscada.idDivida}</li>
      <li class="list-group-item"><strong>Valor:</strong> R$ ${dividaBuscada.valor}</li>
      <li class="list-group-item"><strong>Data Início:</strong> ${dividaBuscada.dataInicio}</li>
      <li class="list-group-item"><strong>Data Vencimento:</strong> ${dividaBuscada.dataVencimento}</li>
      <li class="list-group-item"><strong>Descrição:</strong> ${dividaBuscada.descricao}</li>
      <li class="list-group-item"><strong>Parcelas:</strong> ${dividaBuscada.parcelas}</li>
      <li class="list-group-item"><strong>Valor Parcela:</strong> R$ ${dividaBuscada.valorParcelas}</li>
      <li class="list-group-item"><strong>Status:</strong> ${dividaBuscada.statusDivida}</li>
      <li class="list-group-item"><strong>Credor:</strong> ${dividaBuscada.credor}</li>
      <li class="list-group-item"><strong>Taxa Juros:</strong> ${dividaBuscada.taxaJuros}%</li>
      <li class="list-group-item"><strong>Forma Pagamento:</strong> ${dividaBuscada.formaPagamento}</li>
      <li class="list-group-item"><strong>Origem Dívida:</strong> ${dividaBuscada.origemDivida}</li>
      <li class="list-group-item"><strong>ID Usuário:</strong> ${dividaBuscada.usuarioIdUsuario}</li>
      <li class="list-group-item"><strong>ID Conta:</strong> ${dividaBuscada.contaIdConta}</li>
    </ul>
  </c:if>

    <c:forEach var="d" items="${Divida}">
      <tr>
        <td>${d.id_divida}</td>
        <td>R$ ${d.valor}</td>
        <td>${d.data_inicio}</td>
        <td>${d.data_vencimento}</td>
        <td>${d.descricao}</td>
        <td>${d.parcelas}</td>
        <td>R$ ${d.valor_parcelas}</td>
        <td>${d.status_divida}</td>
        <td>${d.credor}</td>
        <td>${d.taxa_juros}</td>
        <td>${d.forma_pagamento}</td>
        <td>${d.origem_divida}</td>
        <td>${d.usuario_id_usuario}</td>
        <td>${d.conta_id_conta}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
