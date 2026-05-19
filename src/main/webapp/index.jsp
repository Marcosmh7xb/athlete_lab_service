<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro</title>
    <link rel="stylesheet" href="css/selecao_style.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
</head>

<body>

<header>
    <!-- LOGO AGORA CLICÁVEL -->
    <a href="WEB-INF/home.jsp">
        <img src="banco_imagens/imagens/Athlete logo white.png" alt="logo Athlete" class="logo">
    </a>

    <h2>Bem-vindo ao Athlete Lab</h2>
    <p>
        Selecione seu perfil abaixo para acessar o painel de controle e começar a gerenciar o desenvolvimento esportivo.
    </p>
</header>

<div class="container">

    <!-- ATLETA -->
    <div class="card">
        <img src="banco_imagens/icones/Group 53.png" class="icon">

        <h2 class="text">Atleta</h2>

        <p class="text">
            Acompanhe suas métricas, visualize seus treinos, analise seu progresso e comunique-se com sua equipe técnica.
        </p>

        <ul class="lista">
            <li>Dashboard de Performance</li>
            <li>Histórico de Tarefas</li>
            <li>Metas e Objetivos</li>
        </ul>

        <form action="login" method="get">
            <button type="submit" name="tipoUsuario" value="ATLETA">
                Entrar como Atleta →
            </button>
        </form>

        <div class="criar_conta">
            <p class="topo">Esqueceu a sua senha?</p>

            <p class="linha-texto">
                Ainda não tem acesso?
                <a href="cadastro?tipoUsuario=ATLETA" class="link-acao">
                    Solicite acesso
                </a>
            </p>
        </div>
    </div>

    <!-- TREINADOR -->
    <div class="card">
        <img src="banco_imagens/icones/Group 60.png" class="icon">

        <h2 class="text">Treinador</h2>

        <p class="text">
            Gerencie sua equipe, avalie condições físicas, planeje treinos e tome decisões baseadas em dados reais.
        </p>

        <ul class="lista">
            <li>Gestão de Elenco</li>
            <li>Relatórios Avançados</li>
            <li>Controle de Carga</li>
        </ul>

        <form action="login" method="get">
            <button type="submit" name="tipoUsuario" value="TREINADOR">
                Entrar como Treinador →
            </button>
        </form>

        <div class="criar_conta">
            <p class="topo">Esqueceu a sua senha?</p>

            <p class="linha-texto">
                Ainda não tem acesso?
                <a href="cadastro?tipoUsuario=TREINADOR" class="link-acao">
                    Solicite acesso
                </a>
            </p>
        </div>

    </div>

</div>

<footer class="rodape">
    <p>
        © 2026 AthleteLab. Todos os direitos reservados
        <a href="#">Termo de Uso</a>
        <a href="#">Privacidade</a>
    </p>
</footer>

</body>
</html>