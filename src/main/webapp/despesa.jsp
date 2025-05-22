<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri ="jakarta.tags.core"%>
<%@ page import="br.com.fiap.model.Despesa"%>
<%@ page import="br.com.fiap.dao.DespesaDao"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="br.com.fiap.exception.EntidadeNaoEncontrada"%>

    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Fintech - Controle de Despesas</title>
        <link rel="stylesheet" href="">
        <style>
            body {
                background-color: #f4f6f9;
            }
            .fintech-card {
                border-radius: 16px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                background: white;
            }
        </style>
    </head>
    <body>

    <div class="container mt-5">

        <h2 class="mb-3 text-center">Bem-vindo ao sistema de gerenciamento de despesas!</h2>

        <!-- resumo -->
        <div class="row mb-4">
            <div class="col-md-4">
                <div class="p-3 fintech-card text-center">
                    <h6>Total de Despesas</h6>
                    <h3 class="text-danger">R$ <c:out value="${totalDespesas}" /></h3>
                </div>
            </div>
        </div>
        <!-- formulário para add despesa -->
        <div class="fintech-card p-4 mb-4">

            <form action="inserirDespesa" method="post" class="row g-3">
                <div class="col-md-4">
                    <label>Nova Despesa</label>
                    <input type="text" name="nome" class="form-control" placeholder="Descrição da Despesa" required>
                </div>
                <div class="col-md-3">
                    <label>Valor da Despesa</label>
                    <input type="number" step="0.01" name="valor" class="form-control" placeholder="Valor (R$)" required>
                </div>
                <div class="col-md-3">
                    <label>Data do Pagamento</label>
                    <input type="date" name="data do pagamento" class="form-control" placeholder="Data do Pagamento" required>
                </div>
                <div class="col-md-2">
                    <label>Data do Vencimento</label>
                    <input type="date" name="data do vencimento" class="form-control" placeholder="Data do Vencimento">
                </div>

                <div class="col-md-4">
                    <select name="categoria" class="form-select" required>
                        <option value="">Selecione a Categoria</option>
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
                <div class="col-md-4">
                    <select name="formaPagamento" class="form-select" required>
                        <option value="">Forma de Pagamento</option>
                        <option value="Cartão">Cartão</option>
                        <option value="Dinheiro">Dinheiro</option>
                        <option value="PIX">PIX</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <select name="status" class="form-select" required>
                        <option value="Pendente">Pendente</option>
                        <option value="Pago">Pago</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <select name="recorrente" class="form-select" required>
                        <option value="">Recorrente?</option>
                        <option value="S">Sim</option>
                        <option value="N">Não</option>
                    </select>
                </div>

                <div class="col-md-12 d-flex justify-content-end">
                    <button type="submit" class="btn btn-success me-2">Cadastrar</button>
                    <button type="submit" formaction="atualizarDespesa" class="btn btn-warning me-2">Atualizar</button>
                    <button type="submit" formaction="removerDespesa" class="btn btn-danger me-2">Remover</button>
                    <button type="submit" formaction="pesquisarDespesa" class="btn btn-secondary">Pesquisar</button>
                </div>
            </form>
        </div>

        <!-- exibir tabela com despesas -->
        <div class="fintech-card p-4">
            <h5>Despesas Cadastradas</h5>
            <form action="pesquisarDespesa" method="get" class="input-group mb-3">
                <input type="text" name="termo" class="form-control" placeholder="Pesquisar por nome...">
                <button class="btn btn-outline-secondary" type="submit">Pesquisar</button>
            </form>
            <table class="table table-hover">
                <thead class="table-light">
                <tr>
                    <th>Nome</th>
                    <th>Valor</th>
                    <th>Data de Pagamento</th>
                    <th>Vencimento</th>
                    <th>Categoria</th>
                    <th>Forma</th>
                    <th>Status</th>
                    <th>Recorrente</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="despesa" items="${listaDespesas}">
                    <tr>
                        <td>${despesa.nome}</td>
                        <td>R$ ${despesa.valor}</td>
                        <td>${despesa.data}</td>
                        <td>${despesa.vencimento}</td>
                        <td>${despesa.categoria}</td>
                        <td>${despesa.formaPagamento}</td>
                        <td>${despesa.status}</td>
                        <td>${despesa.recorrente}</td>
                        <td>
                            <div class="btn-group" role="group">
                                <a href="editarDespesa?id=${despesa.id}" class="btn btn-warning btn-sm">Atualizar</a>
                                <a href="removerDespesa?id=${despesa.id}" class="btn btn-danger btn-sm">Remover</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </body>
    </html>