package br.com.fiap.controller;

import br.com.fiap.dao.ContaDAO;
import br.com.fiap.exception.EntidadeNaoEncontrada;
import br.com.fiap.model.Conta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/conta")
public class ContaServlet extends HttpServlet {

    private ContaDAO contaDAO;

    @Override
    public void init() throws ServletException {
        try {
            // Criar uma implementação concreta do ContaDAO
            contaDAO = new ContaDAO() {
                @Override
                public void close() throws SQLException {
                    super.close();
                }
            };
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Erro ao inicializar ContaDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("remover".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                try {
                    contaDAO.remover(id);
                    request.setAttribute("mensagem", "Conta removida com sucesso!");
                } catch (EntidadeNaoEncontrada e) {
                    request.setAttribute("erro", "Conta não encontrada.");
                } catch (SQLException e) {
                    request.setAttribute("erro", "Erro ao remover conta: " + e.getMessage());
                }
            }

            List<Conta> contas = contaDAO.listar();
            request.setAttribute("contas", contas);
            request.getRequestDispatcher("conta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao processar solicitação: " + e.getMessage());
            request.getRequestDispatcher("conta.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nomeConta = request.getParameter("nomeConta");
            String banco = request.getParameter("banco");
            BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));
            String tipoContaStr = request.getParameter("tipoConta");

            Conta.TipoConta tipoConta = Conta.TipoConta.valueOf(tipoContaStr);

            Conta novaConta = new Conta(null, nomeConta, banco, saldo, tipoConta);
            contaDAO.cadastrar(novaConta);

            response.sendRedirect("conta");

        } catch (Exception e) {
            request.setAttribute("erro", "Erro ao cadastrar conta: " + e.getMessage());
            doGet(request, response);
        }
    }
}