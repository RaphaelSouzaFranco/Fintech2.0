package br.com.fiap.controller;

import br.com.fiap.model.Conta;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/conta")
public class ContaServlet extends HttpServlet {

    //Lista de contas na mémoria (igual "banco de dados")
    private static List<Conta> contas = new ArrayList<>();

    //Método para lidar com POST(Cadastro)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Pegando dados do formulário HTML
        Long id = Long.parseLong(request.getParameter("id"));
        String nomeConta = request.getParameter("nomeConta");
        String banco = request.getParameter("banco");
        BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));
        String tipoConta = request.getParameter("tipoConta");

        //Criando a conta
        Conta novaConta = new Conta(Math.toIntExact(id), nomeConta, banco, saldo, tipoConta);

        //Adicionanando na lista
        contas.add(novaConta);

        //Redirecionando para uma pagina que lista as contas
        request.setAttribute("conta", novaConta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("conta.jsp");
        dispatcher.forward(request, response);

    }

    //Método para remover uma conta via GET (passando ID na URL)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");

        if (idString != null) {
            Long id = Long.parseLong(idString); //Convertendo para Long
            contas.removeIf(conta ->conta.getId().equals(id)); //Usando equals() para comparação de Long

        }

        request.setAttribute("contas", contas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("conta.jsp");
        dispatcher.forward(request, response);
    }
}
