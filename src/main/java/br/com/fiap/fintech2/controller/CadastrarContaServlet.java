package br.com.fiap.controller;

import br.com.fiap.dao.ContaDAO;
import br.com.fiap.model.Conta;
import br.com.fiap.model.Conta.TipoConta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/CadastrarContaServlet")
public class CadastrarContaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Configura o tipo de conteúdo da resposta
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Simula as entradas do scanner usando parâmetros da requisição
        String titular = request.getParameter("titular");
        String banco = request.getParameter("banco");
        BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));
        int tipo = Integer.parseInt(request.getParameter("tipo"));

        // Mantém exatamente a mesma lógica de conversão de tipo
        Conta.TipoConta tipoConta = switch (tipo) {
            case 1 -> Conta.TipoConta.CORRENTE;
            case 2 -> Conta.TipoConta.POUPANCA;
            case 3 -> Conta.TipoConta.SALARIO;
            default -> Conta.TipoConta.CORRENTE;
        };

        try (ContaDAO dao = new ContaDAO()) {
            Conta conta = new Conta(null, titular, banco, saldo, tipoConta);
            dao.cadastrar(conta);

            // Saída HTML equivalente ao System.out.println original
            out.println("<html><body>");
            out.println("<h1>=== CADASTRO DE CONTA ===</h1>");
            out.println("<p>Conta cadastrada com sucesso!</p>");
            out.println("</body></html>");

        } catch (SQLException e) {
            // Equivalente ao System.err.println original
            out.println("<html><body>");
            out.println("<h1>Erro ao cadastrar conta</h1>");
            out.println("<p style='color:red;'>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}