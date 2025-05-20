package br.com.fiap.fintech2.controller;

public class RemoverConta package br.com.fiap.servlet;

import br.com.fiap.dao.ContaDAO;
import br.com.fiap.exception.EntidadeNaoEncontrada;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/removerContas")
public class RemoverContasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String input = request.getParameter("ids");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Remoção de Múltiplas Contas</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; line-height: 1.6; }");
            out.println(".success { color: green; }");
            out.println(".error { color: red; }");
            out.println(".summary { margin-top: 20px; padding: 15px; background-color: #f5f5f5; border-radius: 5px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Remoção de Múltiplas Contas</h1>");

            if (input == null || input.trim().isEmpty() || !input.matches("\\d+(\\s*,\\s*\\d+)*")) {
                out.println("<p class='error'>Formato inválido! Use números separados por vírgula (ex: 1, 2, 3)</p>");
                out.println("</body></html>");
                return;
            }

            String[] ids = input.split(",");
            int sucessos = 0;
            int falhas = 0;

            try (ContaDAO dao = new ContaDAO()) {
                out.println("<h2>Processo de Remoção</h2>");
                out.println("<ul>");

                for (String idStr : ids) {
                    try {
                        long id = Long.parseLong(idStr.trim());

                        try {
                            dao.remover(id);
                            out.println("<li class='success'>✓ Conta ID " + id + " removida com sucesso</li>");
                            sucessos++;
                        } catch (EntidadeNaoEncontrada e) {
                            out.println("<li class='error'>✗ Conta ID " + id + " não encontrada</li>");
                            falhas++;
                        }
                    } catch (NumberFormatException e) {
                        out.println("<li class='error'>✗ ID inválido: " + idStr + "</li>");
                        falhas++;
                    }
                }

                out.println("</ul>");

                out.println("<div class='summary'>");
                out.println("<h3>Resumo da Operação</h3>");
                out.println("<p>Contas removidas com sucesso: " + sucessos + "</p>");
                out.println("<p>Falhas na remoção: " + falhas + "</p>");
                out.println("<p>Total processado: " + (sucessos + falhas) + "</p>");
                out.println("</div>");

            } catch (SQLException e) {
                out.println("<p class='error'>Erro grave ao acessar o banco de dados: " + e.getMessage() + "</p>");
            }

            out.println("<a href='index.html'>Voltar</a>");
            out.println("</body></html>");

        } finally {
            out.close();
        }
    }
}{
}
