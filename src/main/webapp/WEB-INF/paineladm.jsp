<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel Administrativo</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/paineladm_style.css">
</head>

<body>

<div class="container">

    <!-- LOGO CENTRAL -->

    <header class="header">

        <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png"
             alt="Athlete Lab Logo"
             class="logo">

    </header>

    <!-- QUADRO PRINCIPAL -->

    <main class="dashboard-wrapper">

        <div class="dashboard">

            <!-- CARD 1 -->

            <a href="${pageContext.request.contextPath}/admin" class="admin-card">

                <div class="icon-area">

                    <img src="${pageContext.request.contextPath}/banco_imagens/icones/Dashboard Layout_r.png"
                         class="card-icon"
                         alt="Ícone registros">

                </div>

                <h2>Tabela de Registros</h2>

                <p>
                    Gerencie usuários, administradores,
                    atletas e treinadores.
                </p>

            </a>

            <!-- CARD 2 -->

            <a href="${pageContext.request.contextPath}/admin/treinos" class="admin-card">

                <div class="icon-area">

                    <img src="../banco_imagens/icones/Trainers_r.png"
                         class="card-icon"
                         alt="Ícone treinos">

                </div>

                <h2>Treinos</h2>

                <p>
                    Crie treinos, organize
                    e gerencie atividades esportivas.
                </p>

            </a>

            <!-- CARD 3 -->

            <a href="${pageContext.request.contextPath}/index.jsp" class="admin-card">

                <div class="icon-area">

                    <img src="../banco_imagens/icones/Globe.png"
                         class="card-icon"
                         alt="Ícone home">

                </div>

                <h2>Voltar ao Site</h2>

                <p>
                    Retorne para a página convencional
                    do Athlete Lab.
                </p>

            </a>

        </div>

    </main>

</div>

</body>

</html>