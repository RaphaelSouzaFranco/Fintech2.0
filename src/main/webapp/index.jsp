<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tela Inicial - Finanças</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">

    <!-- CSS personalizado -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Home.css">

    <!-- Font Awesome para ícones -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
<header class="py-5 text-center bg-primary text-white">
    <div class="container">
        <h1 class="display-4">Bem-vindo(a)!</h1>
        <p class="lead">Escolha uma seção para gerenciar suas finanças</p>
    </div>
</header>

<div class="container my-5">
    <div class="row g-4">

        <!-- Card Conta -->
        <div class="col-12">
            <div class="card card-conta h-100">
                <div class="card-body text-center">
                    <i class="fas fa-wallet fa-3x mb-3 text-primary"></i>
                    <h3 class="card-title">Conta</h3>
                    <p class="card-text">Gerencie contas, saldos e tipos bancários.</p>
                    <a href="${pageContext.request.contextPath}/conta" class="btn btn-primary">Acessar</a>
                </div>
            </div>
        </div>

        <!-- Card Receitas -->
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-body text-center">
                    <i class="fas fa-circle-plus fa-3x mb-3 text-success"></i>
                    <h3 class="card-title">Receitas</h3>
                    <p class="card-text">Registre e acompanhe todos os seus ganhos e rendimentos.</p>
                    <a href="${pageContext.request.contextPath}/receitas" class="btn btn-success">
                        <i class="fas fa-plus-circle me-2"></i>Gerenciar Receitas
                    </a>
                </div>
            </div>
        </div>

        <!-- Card Despesas -->
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-body text-center">
                    <i class="fas fa-circle-minus fa-3x mb-3 text-danger"></i>
                    <h3 class="card-title">Despesas</h3>
                    <p class="card-text">Controle seus gastos e mantenha suas despesas organizadas.</p>
                    <a href="${pageContext.request.contextPath}/despesas" class="btn btn-danger">
                        <i class="fas fa-minus-circle me-2"></i>Gerenciar Despesas
                    </a>
                </div>
            </div>
        </div>


        <!-- Cards Dívidas -->
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-body text-center">
                    <i class="fas fa-credit-card fa-3x mb-3 text-warning"></i>
                    <h3 class="card-title">Dívidas</h3>
                    <p class="card-text">Acompanhe suas dívidas e prazos de pagamento.</p>
                    <a href="${pageContext.request.contextPath}/Divida.jsp" class="btn btn-warning">Acessar</a>
                </div>
            </div>
        </div>

        <!-- Cards Categorias -->
        <div class="col-md-6">
            <div class="card h-100">
                <div class="card-body text-center">
                    <i class="fas fa-tags fa-3x mb-3 text-info"></i>
                    <h3 class="card-title">Categorias</h3>
                    <p class="card-text">Organize despesas e receitas por categoria.</p>
                    <a href="${pageContext.request.contextPath}/categorias" class="btn btn-info">Acessar</a>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="py-3 text-center bg-light">
    <div class="container">
        <p class="text-muted mb-0">&copy; <%= java.time.Year.now().getValue() %> Finanças. Todos os direitos reservados.</p>
    </div>
</footer>

<!-- Bootstrap JS e Popper.js -->
<link rel="stylesheet" href="resources/js/bootstrap.bundle.js">
</body>
</html>