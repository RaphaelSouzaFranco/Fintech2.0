package br.com.fiap.controller;

import br.com.fiap.dao.CadastroDAO;
import br.com.fiap.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cadastrar")
public class CadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Recupera os parâmetros do formulário
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String confirmarSenha = request.getParameter("confirmarSenha");

            // Validações
            if (nome == null || email == null || senha == null || confirmarSenha == null ||
                    nome.trim().isEmpty() || email.trim().isEmpty() || senha.trim().isEmpty()) {
                request.setAttribute("erro", "Todos os campos são obrigatórios!");
                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                return;
            }

            if (!senha.equals(confirmarSenha)) {
                request.setAttribute("erro", "As senhas não correspondem!");
                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                return;
            }

            CadastroDAO dao = new CadastroDAO();

            if (dao.emailExiste(email)) {
                request.setAttribute("erro", "Este email já está cadastrado!");
                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
                return;
            }

            // Criação do usuário
            Usuario usuario = new Usuario(nome, email, senha);
            dao.cadastrar(usuario);

            request.setAttribute("sucesso", "Usuário cadastrado com sucesso!");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Não foi possível cadastrar o usuário: " + e.getMessage());
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        }
    }
}