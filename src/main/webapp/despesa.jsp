<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.com.fiap.model.Despesa" %>
<%@ page import="br.com.fiap.dao.DespesaDao" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.fiap.exception.EntidadeNaoEncontrada" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Fintech - Controle de Despesas</title>

    <!-- bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .fintech-card {
            border-radius: 16px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            background: white;
            padding: 20px;
            margin-bottom: 20px;
        }
        .btn {
            border-radius: 12px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        h2, h5 {
            color: #2c3e50;
            font-weight: 600;
        }
    </style>
</head>
<body>

<%@include file="header.jsp"%>

<div class="container mt-5">

    <h2 class="mb-4 text-center">💳 Fintech - Gerenciamento de Despesas</h2>

    <!-- resumo -->
    <div class="row mb-4">
        <div class="col-md-4 mx-auto">
            <div class="fintech-card text-center">
                <h6 class="text-muted">Total de Despesas</h6>
                <h3 class="text-danger">R$ <c:out value="${totalDespesas}" /></h3>
            </div>
        </div>
    </div>

    <!-- formulário para adicionar despesa -->
    <div class="fintech-card">
        <h5 class="mb-3">Adicionar Nova Despesa</h5>
        <form action="despesa" method="post" class="row g-3">
            <input type="hidden" name="acao" value="cadastrar">

            <div class="col-md-6">
                <label for="descricao" class="form-label">Descrição</label>
                <input type="text" id="descricao" name="descricao" class="form-control" placeholder="Descrição da Despesa" required>
            </div>

            <div class="col-md-3">
                <label for="valor" class="form-label">Valor (R$)</label>
                <input type="number" step="0.01" id="valor" name="valor" class="form-control" required>
            </div>

            <div class="col-md-3">
                <label for="dataPagamento" class="form-label">Data do Pagamento</label>
                <input type="date" id="dataPagamento" name="dataPagamento" class="form-control" required>
            </div>

            <div class="col-md-3">
                <label for="vencimento" class="form-label">Data do Vencimento</label>
                <input type="date" id="vencimento" name="vencimento" class="form-control">
            </div>

            <div class="col-md-3">
                <label class="form-label">Categoria</label>
                <select name="categoria_despesa" class="form-select" required>
                    <option value="">Selecione</option>
                    <option value="Alimentação">Alimentação</option>
                    <option value="Transporte">Transporte</option>
                    <option value="Lazer">Lazer</option>
                    <option value="Educação">Educação</option>
                    <option value="Investimento">Investimento</option>
                    <option value="Saúde">Saúde</option>
                    <option value="Moradia">Moradia</option>
                    <option value="Outros">Outros</option>
                </select>
            </div>

            <div class="col-md-3">
                <label class="form-label">Forma de Pagamento</label>
                <select name="forma_pagamento" class="form-select" required>
                    <option value="">Selecione</option>
                    <option value="Cartão">Cartão</option>
                    <option value="Dinheiro">Dinheiro</option>
                    <option value="PIX">PIX</option>
                </select>
            </div>

            <div class="col-md-3">
                <label class="form-label">Status</label>
                <select name="status_despesa" class="form-select" required>
                    <option value="PENDENTE">Pendente</option>
                    <option value="PAGO">Pago</option>
                    <option value="ATRASADO">Atrasado</option>
                </select>
            </div>

            <div class="col-md-3">
                <label class="form-label">Recorrente?</label>
                <select name="recorrente" class="form-select" required>
                    <option value="">Selecione</option>
                    <option value="S">Sim</option>
                    <option value="N">Não</option>
                </select>
            </div>

            <div class="col-md-12 d-flex justify-content-end">
                <button type="submit" class="btn btn-success me-2">Cadastrar</button>
            </div>
        </form>
    </div>

    <!-- formulário para edição -->
    <div class="container mt-5">
        <c:if test="${not empty despesaEdicao}">
            <div class="card p-4">
                <h3 class="mb-4">Editar Despesa</h3>
                <form action="despesa" method="post" class="row g-3">
                    <input type="hidden" name="acao" value="editar">
                    <input type="hidden" name="idDespesa" value="${despesa.idDespesa}" />
                    <input type="text" name="descricao" value="${despesa.descricao}" required /><br>
                    <input type="number" step="0.01" name="valor" value="${despesa.valor}" required /><br>
                    <input type="date" name="dataPagamento" value="${despesa.dataPagamento}" required /><br>
                    <input type="date" name="vencimento" value="${despesa.vencimento}" /><br>

                    <input type="text" name="categoriaDespesa" value="${despesa.categoriaDespesa}" required /><br>
                    <input type="text" name="formaPagamento" value="${despesa.formaPagamento}" required /><br>
                    <input type="text" name="statusDespesa" value="${despesa.statusDespesa}" required /><br>

                    <select name="recorrente">
                        <option value="S" ${despesa.recorrente == 'S' ? 'selected' : ''}>Sim</option>
                        <option value="N" ${despesa.recorrente == 'N' ? 'selected' : ''}>Não</option>
                    </select><br>

                    <button type="submit">Salvar</button>
                    <button type="button" onclick="window.location.href='despesa'">Cancelar</button>
                </form>
            </div>
        </c:if>
    </div>

    <!-- tabela de despesas -->
    <div class="fintech-card">
        <h5 class="mb-3">Despesas Cadastradas</h5>

        <form action="despesa" method="get">
            <input type="hidden" name="acao" value="pesquisar">
            <input type="text" name="termo" class="form-control" placeholder="Pesquisar por nome...">
            <button class="btn btn-outline-secondary" type="submit">Pesquisar</button>
        </form>

        <table class="table table-hover align-middle">
            <thead class="table-light">
            <tr>
                <th>Descrição</th>
                <th>Valor</th>
                <th>Data de Pagamento</th>
                <th>Vencimento</th>
                <th>Categoria</th>
                <th>Forma Pagamento</th>
                <th>Status</th>
                <th>Recorrente</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaDespesas}" var="despesa">
                <tr>
                    <td><c:out value="${despesa.descricao}"/></td>
                    <td>R$ <c:out value="${despesa.valor}"/></td>
                    <td><fmt:formatDate value="${despesa.dataPagamento}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${despesa.vencimento}" pattern="dd/MM/yyyy"/></td>
                    <td><c:out value="${despesa.categoriaDespesa}"/></td>
                    <td><c:out value="${despesa.formaPagamento}"/></td>
                    <td><c:out value="${despesa.statusDespesa}"/></td>
                    <td><c:out value="${despesa.recorrente}"/></td>
                    <td>
                        <a href="despesa?acao=editar&id=${despesa.idDespesa}" class="btn btn-sm btn-warning" title="Editar"><i class="bi bi-pencil"></i>Editar</a>
                        <a href="despesa?acao=remover&id=${despesa.idDespesa}" onclick="return confirm('Deseja realmente excluir esta despesa?')" class="btn btn-sm btn-danger" title="Excluir"><i class="bi bi-trash"></i>Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<%@include file="footer.jsp"%>
</body>
</html>