<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String mensagem = null;
    Boolean sucesso = null;
    if (session.getAttribute("mensagem") != null) {
        mensagem = (String) session.getAttribute("mensagem");
        sucesso = (Boolean) session.getAttribute("sucesso");
        session.removeAttribute("mensagem");
        session.removeAttribute("sucesso");
    }
%>

<html>
<head><title>Cadastro</title></head>
<body>

<h2>Cadastro de Conta</h2>

<% if (mensagem != null) { %>
<p style="color:<%= (sucesso != null && sucesso) ? "green" : "red" %>;">
    <strong><%= mensagem %></strong>
</p>
<% } %>

<% if (sucesso == null || !sucesso) { %>
<form action="CadastroServlet" method="post">
    <label>Nome:</label>
    <input type="text" name="nome" required><br><br>

    <label>Sobrenome:</label>
    <input type="text" name="sobrenome" required><br><br>

    <label>Data de Nascimento:</label>
    <input type="date" name="dataNascimento" required><br><br>

    <label>Email:</label>
    <input type="email" name="email" required><br><br>

    <label>Senha:</label>
    <input type="password" name="senha" required><br><br>

    <label>Gênero:</label>
    <select name="genero" required>
        <option value="">Selecione</option>
        <option value="Masculino">Masculino</option>
        <option value="Feminino">Feminino</option>
        <option value="Outro">Outro</option>
    </select><br><br>

    <input type="submit" value="Cadastrar">
</form>
<% } %>

</body>
</html>
