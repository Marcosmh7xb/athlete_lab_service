<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt_BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/login_style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>

    <div class="container">
        <div class="left">

            <div class="top-bar">
                <a href="index.jsp" class="voltar">← Voltar</a>
            </div>

            <div class="form-area">
                <img src="banco_imagens/imagens/Athlete logo white.png" alt="logo" class="logo">

                <% if (request.getAttribute("erro") != null) { %>
                    <div class="er">
                        <h2>Erro no login</h2>
                        <p>Email ou senha inválidos, Por favor tente novamente.</p>
                    </div>
                <% } else { %>
                    <div class="texto-login">
                        <h2>Bem-vindo de volta</h2>
                        <p>Insira seu e-mail e senha para acessar seu painel.</p>
                    </div>
                <% } %>

                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-content">

                        <input type="email" name="email" placeholder="E-mail" required>
                        <input type="password" name="senha" placeholder="Senha" required>
                        <input type="hidden" name="tipoUsuario" value="${not empty tipoUsuario ? tipoUsuario : param.tipoUsuario}">

                        <div class="opcoes">
                            <label class="lembrar">
                                <input type="checkbox"> <span class="checkmark"></span> Lembrar-me </label>
                        </div>

                        <button type="submit">Entrar</button>
                    </div>
                </form>

                <div class="cadastro">
                </div>
            </div>
        </div>

        <div class="right">

            <h2> Evolua a cada dia e domine seu esporte </h2>

            <p>
               Acesse seu dashboard exclusivo para visualizar seus treinos e acompanhar sua evolução.
               Tenha tudo organizado em um só lugar para alcançar seus objetivos com mais eficiência.
            </p>

            <img src="banco_imagens/imagens/performance.png" alt="imagem destaque" class="imagem-right">
        </div>
    </div>
</body>
</html>