<%@ page import="br.com.fiap.model.Receitas" %>
<%@ page import="br.com.fiap.dao.ReceitasDAO" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fiap.exception.EntidadeNaoEncontrada" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <title>Gerenciar Receitas</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">

  <h2 class="mb-4">Cadastrar Receita</h2>
  <form method="post" action="receitas.jsp">
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
  <form method="get" action="receitas.jsp" class="mb-4">
    <div class="input-group w-50">
      <input type="number" class="form-control" name="buscarId" placeholder="Digite o ID da receita" required>
      <button class="btn btn-primary" type="submit">Buscar</button>
    </div>
  </form>

  <%
    // Inserção
    String valorStr = request.getParameter("valor");
    if (valorStr != null) {
      try {
        ReceitasDAO dao = new ReceitasDAO();
        Receitas receita = new Receitas();
        receita.setValor(Double.parseDouble(valorStr));
        receita.setData_Recebimento(Date.valueOf(request.getParameter("data_Recebimento")));
        receita.setDescricao(request.getParameter("descricao"));
        receita.setCategoria_Receita(request.getParameter("categoria_Receita"));
        receita.setFormaPagamento(request.getParameter("forma_Pagamento"));
        receita.setStatus(request.getParameter("status"));
        receita.setConta_id_conta(Integer.parseInt(request.getParameter("conta_id_conta")));
        receita.setUsuario_id_usuario(Integer.parseInt(request.getParameter("usuario_id_usuario")));
        dao.insert(receita);
        out.println("<div class='alert alert-success'>Receita cadastrada com sucesso!</div>");
      } catch (Exception e) {
        out.println("<div class='alert alert-danger'>Erro ao cadastrar receita: " + e.getMessage() + "</div>");
      }
    }

    // Pesquisa por ID
    String buscarId = request.getParameter("buscarId");
    if (buscarId != null) {
      try {
        int idBusca = Integer.parseInt(buscarId);
        ReceitasDAO dao = new ReceitasDAO();
        Receitas receita = dao.pesquisar(idBusca);
  %>
  <h4>Resultado da busca:</h4>
  <ul class="list-group w-75 mb-4">
    <li class="list-group-item"><strong>ID:</strong> <%= receita.getId_Receitas() %></li>
    <li class="list-group-item"><strong>Valor:</strong> R$ <%= receita.getValor() %></li>
    <li class="list-group-item"><strong>Data:</strong> <%= receita.getData_Recebimento() %></li>
    <li class="list-group-item"><strong>Categoria:</strong> <%= receita.getCategoria_Receita() %></li>
    <li class="list-group-item"><strong>Descrição:</strong> <%= receita.getDescricao() %></li>
    <li class="list-group-item"><strong>Status:</strong> <%= receita.getStatus() %></li>
    <li class="list-group-item"><strong>Forma Pagamento:</strong> <%= receita.getForma_Pagamento() %></li>
    <li class="list-group-item"><strong>Conta ID:</strong> <%= receita.getConta_id_conta() %></li>
    <li class="list-group-item"><strong>Usuário ID:</strong> <%= receita.getUsuario_id_usuario() %></li>
  </ul>
  <%
      } catch (EntidadeNaoEncontrada e) {
        out.println("<div class='alert alert-warning'>Receita não encontrada.</div>");
      } catch (Exception e) {
        out.println("<div class='alert alert-danger'>Erro: " + e.getMessage() + "</div>");
      }
    }

    // Listagem geral
    try {
      ReceitasDAO dao = new ReceitasDAO();
      List<Receitas> receitas = dao.listar();
  %>
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
    <% for (Receitas r : receitas) { %>
    <tr>
      <td><%= r.getId_Receitas() %></td>
      <td>R$ <%= r.getValor() %></td>
      <td><%= r.getData_Recebimento() %></td>
      <td><%= r.getDescricao() %></td>
      <td><%= r.getCategoria_Receita() %></td>
      <td><%= r.getForma_Pagamento() %></td>
      <td><%= r.getStatus() %></td>
      <td><%= r.getConta_id_conta() %></td>
      <td><%= r.getUsuario_id_usuario() %></td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <%
    } catch (Exception e) {
      out.println("<div class='alert alert-danger'>Erro ao listar receitas: " + e.getMessage() + "</div>");
    }
  %>

</div>
</body>
</html>
