<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <title>Fintech - Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<%@include file="header.jsp" %>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
  <div class="card shadow-sm p-4" style="max-width: 400px; width: 100%;">
    <h3 class="text-center mb-4">Seja bem-vindo</h3>

    <!-- MENSAGEM DE ERRO -->
    <c:if test="${not empty erro}">
      <div class="alert alert-danger text-center">
          ${erro}
      </div>
    </c:if>

    <!-- MENSAGEM DE SUCESSO -->
    <c:if test="${not empty sucesso}">
      <div class="alert alert-success text-center">
          ${sucesso}
      </div>
    </c:if>

    <form action="login" method="post">
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input
                type="email"
                class="form-control"
                id="email"
                name="email"
                placeholder="Digite seu email"
                required
                autofocus>
      </div>
      <div class="mb-3">
        <label for="senha" class="form-label">Senha</label>
        <input
                type="password"
                class="form-control"
                id="senha"
                name="senha"
                placeholder="Digite sua senha"
                required>
      </div>
      <div class="d-grid mb-3">
        <button type="submit" class="btn btn-primary">Entrar</button>
      </div>
    </form>
    <div class="text-center">
      <small>Não possui cadastro? <a href="cadastro.jsp">Cadastre-se aqui</a></small>
    </div>
  </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
