package br.com.fiap.controller;

import br.com.fiap.dao.DespesaDao;
import br.com.fiap.exception.EntidadeNaoEncontrada;
import br.com.fiap.model.Despesa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/despesa"})
public class DespesaServlet extends HttpServlet {
    private DespesaDao despesaDao;

    @Override
    public void init() throws ServletException {
        try {
            System.out.println("Registrando driver Oracle...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            despesaDao = new DespesaDao();
            System.out.println("DAO inicializado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
            throw new ServletException("Driver JDBC não encontrado. Verifique o ojdbc11.jar no classpath", e);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
            throw new ServletException("Erro ao conectar ao banco: " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("acao");

        try {
            if (action != null) {
                switch (action) {
                    case "cadastrar":
                        cadastrar(request, response);
                        break;
                    case "remover":
                        removerPorId(request, response);
                        break;
                    case "pesquisar":
                        pesquisarPorDescricao(request, response);
                        break;
                    case "editar":
                        editar(request, response);
                        break;
                    case "atualizar":
                        atualizar(request, response);
                        break;
                    default:
                        listar(request, response);
                }
            } else {
                listar(request, response);
            }
        } catch (Exception e) {
            handleError(request, response, e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "listar";
        }

        try {
            switch (acao) {
                case "listar":
                    listar(request, response);
                    break;
                case "pesquisar":
                    pesquisarPorDescricao(request, response);
                    break;
                case "cadastrar":
                    request.getRequestDispatcher("form-cadastro-despesa.jsp").forward(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "remover":
                    removerPorId(request, response);
                    break;
                case "atualizar":
                    atualizar(request, response);
                    break;
                default:
                    listar(request, response);
                    break;
            }
        } catch (Exception e) {
            handleError(request, response, e);
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Despesa despesa = null;
        try {
            despesa = new Despesa();
            // validação dos campos obrigatórios
            String descricao = request.getParameter("descricao");
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new ServletException("Campo 'descrição' é obrigatório.");
            }
            despesa.setDescricao(descricao.trim());

            // validação do valor
            String valorStr = request.getParameter("valor");
            if (valorStr == null || valorStr.trim().isEmpty()) {
                throw new ServletException("Campo 'valor' é obrigatório.");
            }
            try {
                despesa.setValor(new BigDecimal(valorStr));
            } catch (NumberFormatException e) {
                throw new ServletException("Valor inválido. Use números com ponto decimal.", e);
            }

            // validação da data de pagamento
            String dataPagamentoStr = request.getParameter("dataPagamento");
            if (dataPagamentoStr == null || dataPagamentoStr.trim().isEmpty()) {
                throw new ServletException("Campo 'dataPagamento' é obrigatório.");
            }
            despesa.setDataPagamento(validarData(dataPagamentoStr, "dataPagamento"));

            // validação da data de vencimento (opcional)
            String vencimentoStr = request.getParameter("vencimento");
            if (vencimentoStr != null && !vencimentoStr.trim().isEmpty()) {
                despesa.setVencimento(validarData(vencimentoStr, "vencimento"));
            }

            // validação da categoria
            String categoria = request.getParameter("categoria_despesa");
            if (categoria == null || categoria.trim().isEmpty()) {
                throw new ServletException("Campo 'categoria_despesa' é obrigatório.");
            }
            despesa.setCategoriaDespesa(categoria.trim());

            // validação da forma de pagamento
            String formaPagamento = request.getParameter("forma_pagamento");
            if (formaPagamento == null || formaPagamento.trim().isEmpty()) {
                throw new ServletException("Campo 'forma_pagamento' é obrigatório.");
            }
            despesa.setFormaPagamento(formaPagamento.trim());

            // validação do status
            String status = request.getParameter("status_despesa");
            if (status == null || (!status.equals("PENDENTE") && !status.equals("PAGO") && !status.equals("ATRASADO"))) {
                throw new ServletException("Status inválido. Valores permitidos: PENDENTE, PAGO, ATRASADO.");
            }
            despesa.setStatusDespesa(status);

            // validação do campo recorrente
            String recorrenteParam = request.getParameter("recorrente");
            if (recorrenteParam == null || (!recorrenteParam.equalsIgnoreCase("S") && !recorrenteParam.equalsIgnoreCase("N"))) {
                throw new ServletException("Valor inválido para campo 'recorrente'. Use S ou N.");
            }
            despesa.setRecorrente(recorrenteParam.toUpperCase().charAt(0));

            System.out.println("Dados recebidos para cadastro:");
            request.getParameterMap().forEach((k, v) ->
                    System.out.println(k + "=" + Arrays.toString(v)));

            despesaDao.cadastrar(despesa);

            // feedback
            request.getSession().setAttribute("mensagem", "Despesa cadastrada com sucesso!");
            response.sendRedirect(request.getContextPath() + "/despesa?acao=listar");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar: " + e.getMessage());
            request.setAttribute("despesa", despesa);
            request.getRequestDispatcher("despesa.jsp").forward(request, response);
        }
    }

    private Date validarData(String dataStr, String campo) throws ServletException {
        try {
            return Date.valueOf(dataStr);
        } catch (IllegalArgumentException e) {
            throw new ServletException("Formato inválido para " + campo + ". Use yyyy-MM-dd", e);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Despesa> lista = despesaDao.listar();
            double total = despesaDao.calcularTotal();

            request.setAttribute("listaDespesas", lista);
            request.setAttribute("totalDespesas", total);

            RequestDispatcher dispatcher = request.getRequestDispatcher("despesa.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erro ao listar despesas", e);
        }
    }

    private void removerPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            despesaDao.removerPorId(id);

            request.getSession().setAttribute("mensagem", "Despesa removida com sucesso!");
            response.sendRedirect("despesa?acao=listar");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao remover: " + e.getMessage());
            request.getRequestDispatcher("despesa.jsp").forward(request, response);
        }
    }

    private void pesquisarPorDescricao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, EntidadeNaoEncontrada {
        String termo = request.getParameter("termo");
        List<Despesa> resultado = despesaDao.pesquisarPorDescricao(termo);
        double total = despesaDao.calcularTotal();

        request.setAttribute("listaDespesas", resultado);
        request.setAttribute("totalDespesas", total);
        request.getRequestDispatcher("despesa.jsp").forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                throw new IllegalArgumentException("ID da despesa não informado.");
            }

            Long id;
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ID da despesa inválido: " + idParam);
            }

            Despesa despesa = despesaDao.pesquisarPorId(Integer.parseInt(idParam));
            if (despesa == null) {
                throw new IllegalArgumentException("Despesa com ID " + id + " não encontrada.");
            }

            // logs de segurança para debug
            System.out.println("Dados da despesa para edição:");
            System.out.println("ID: " + despesa.getIdDespesa());
            System.out.println("Recorrente: '" + despesa.getRecorrente() + "'");
            System.out.println("Status: " + despesa.getStatusDespesa() + "'");

            request.setAttribute("despesaEdicao", despesa);
            request.getRequestDispatcher("despesa.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/despesa?acao=editar");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao carregar despesa para edição: " + e.getMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
    }

    protected void atualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Despesa despesa = new Despesa();
        try {

            String descricao = request.getParameter("descricao");
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new ServletException("Descrição é obrigatória");
            }

            String valorStr = request.getParameter("valor");
            if (valorStr == null || valorStr.trim().isEmpty()) {
                throw new ServletException("Valor é obrigatório");
            }

            String dataPagamentoStr = request.getParameter("dataPagamento");
            if (dataPagamentoStr == null || dataPagamentoStr.trim().isEmpty()) {
                throw new ServletException("Data de pagamento é obrigatória");
            }

            int id = Integer.parseInt(request.getParameter("id"));
            despesa.setIdDespesa(id);
            despesa.setDescricao(descricao);
            despesa.setValor(new BigDecimal(valorStr));
            despesa.setDataPagamento(validarData(dataPagamentoStr, "data_pagamento"));

            // campos opcionais
            String vencimentoStr = request.getParameter("vencimento");
            if (vencimentoStr != null && !vencimentoStr.isEmpty()) {
                despesa.setVencimento(validarData(vencimentoStr, "vencimento"));
            }

            despesa.setCategoriaDespesa(request.getParameter("categoria_despesa"));
            despesa.setFormaPagamento(request.getParameter("forma_pagamento"));

            // validação de status
            String status = request.getParameter("status_despesa");
            if (status == null || (!status.equals("PENDENTE") && !status.equals("PAGO") && !status.equals("ATRASADO"))) {
                throw new ServletException("Status inválido. Valores permitidos: PENDENTE, PAGO, ATRASADO.");
            }
            despesa.setStatusDespesa(status);

            // validação de recorrente
            String recorrenteParam = request.getParameter("recorrente");
            if (recorrenteParam == null || (!recorrenteParam.equalsIgnoreCase("S") && !recorrenteParam.equalsIgnoreCase("N"))) {
                throw new ServletException("Valor inválido para campo 'recorrente'. Use S ou N.");
            }
            despesa.setRecorrente(recorrenteParam.toUpperCase().charAt(0));

            // atualiza no banco
            despesaDao.editar(despesa);

            // redireciona com mensagem
            request.getSession().setAttribute("mensagem", "Despesa atualizada com sucesso!");
            response.sendRedirect("despesa?acao=listar");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar: " + e.getMessage());
            request.setAttribute("despesaEdicao", despesa);
            request.getRequestDispatcher("despesa.jsp").forward(request, response);
        }
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e)
            throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("erro", "Erro: " + e.getMessage());

        try {

            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        } catch (Exception ex) {

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Erro ao processar requisição: " + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        try {
            if (despesaDao != null) {
                despesaDao.fecharConexao();
                System.out.println("Conexão com o banco fechada");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}