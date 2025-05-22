package br.com.fiap.controller;

import br.com.fiap.dao.DividaDAO;
import br.com.fiap.model.Divida;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

@WebServlet("/Divida")
public class DividaServlet extends HttpServlet {

    private DividaDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dao = new DividaDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Erro ao inicializar DividaDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buscarId = request.getParameter("buscarId");
        List<Divida> dividas = null;

        try {
            dividas = dao.listar();

            if (buscarId != null && !buscarId.isEmpty()) {
                try {
                    int id = Integer.parseInt(buscarId);
                    Divida d = dao.buscarPorId(id);
                    if (d != null) {
                        boolean existe = false;
                        for (Divida div : dividas) {
                            if (div.getIdDivida() == d.getIdDivida()) {
                                existe = true;
                                break;
                            }
                        }
                        if (existe) {
                            request.setAttribute("dividaBuscada", d);
                        } else {
                            request.setAttribute("mensagemAviso", "Nenhuma dívida encontrada com ID " + id);
                        }
                    } else {
                        request.setAttribute("mensagemAviso", "Nenhuma dívida encontrada com ID " + id);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("mensagemErro", "ID inválido.");
                } catch (Exception e) {
                    request.setAttribute("mensagemErro", "Erro ao buscar dívida: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            request.setAttribute("mensagemErro", "Erro ao listar dívidas: " + e.getMessage());
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/Divida.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            String valorStr = request.getParameter("valor");
            String dataInicioStr = request.getParameter("data_inicio");
            String dataVencimentoStr = request.getParameter("data_vencimento");

            if (valorStr == null || valorStr.isEmpty() ||
                    dataInicioStr == null || dataInicioStr.isEmpty() ||
                    dataVencimentoStr == null || dataVencimentoStr.isEmpty()) {
                request.setAttribute("mensagemErro", "Campos obrigatórios não preenchidos.");
                request.getRequestDispatcher("/Divida.jsp").forward(request, response);
                return;
            }

            LocalDate dataInicio = LocalDate.parse(dataInicioStr);
            LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);

            if (dataInicio.isAfter(dataVencimento)) {
                request.setAttribute("mensagemErro", "Data de início não pode ser após a data de vencimento.");
                request.getRequestDispatcher("/Divida.jsp").forward(request, response);
                return;
            }

            Divida d = new Divida();

            d.setValor(Double.parseDouble(valorStr));
            d.setDataInicio(dataInicio);
            d.setDataVencimento(dataVencimento);
            d.setDescricao(request.getParameter("descricao"));
            d.setParcelas(Integer.parseInt(request.getParameter("parcelas")));
            d.setValorParcelas(Double.parseDouble(request.getParameter("valor_parcelas")));
            d.setStatusDivida(request.getParameter("status_divida"));
            d.setCredor(request.getParameter("credor"));
            d.setTaxaJuros(Double.parseDouble(request.getParameter("taxa_juros")));
            d.setFormaPagamento(request.getParameter("forma_pagamento"));
            d.setOrigemDivida(request.getParameter("origem_divida"));
            d.setUsuarioIdUsuario(Integer.parseInt(request.getParameter("usuario_id_usuario")));
            d.setContaIdConta(Integer.parseInt(request.getParameter("conta_id_conta")));

            dao.inserir(d);
            System.out.println("Inserção realizada com sucesso");
            request.setAttribute("mensagemSucesso", "Dívida cadastrada com sucesso!");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Erro de conversão: verifique se todos os números estão no formato correto.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao cadastrar dívida. Verifique os dados e tente novamente.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Divida.jsp");
        dispatcher.forward(request, response);
    }
}

