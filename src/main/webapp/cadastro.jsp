<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.css" >
<!-- Bootstrap Bundle JS -->
<script src="resources/js/bootstrap.bundle.js"></script>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <title>Fintech - Cadastro</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<%@include file="header.jsp" %>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-sm p-4" style="max-width: 400px; width: 100%;">
        <h3 class="text-center mb-4">Criar Conta</h3>

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

        <form action="cadastrar" method="post">
            <div class="mb-3">
                <label for="nome" class="form-label">Nome Completo</label>
                <input
                        type="text"
                        class="form-control"
                        id="nome"
                        name="nome"
                        placeholder="Digite seu nome completo"
                        required
                        autofocus>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input
                        type="email"
                        class="form-control"
                        id="email"
                        name="email"
                        placeholder="Digite seu email"
                        required>
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
            <div class="mb-3">
                <label for="confirmarSenha" class="form-label">Confirmar Senha</label>
                <input
                        type="password"
                        class="form-control"
                        id="confirmarSenha"
                        name="confirmarSenha"
                        placeholder="Confirme sua senha"
                        required>
            </div>
            <div class="d-grid mb-3">
                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </div>
        </form>
        <div class="text-center">
            <small>Já possui conta? <a href="login.jsp">Fazer login</a></small>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>