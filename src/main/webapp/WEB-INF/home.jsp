<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Athlete Lab</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home_style.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@100..900&display=swap" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/js/home_script.js"></script>

</head>

<body>

<!-- ================= HEADER ================= -->

<header class="header">

    <!-- MENU ESQUERDO -->
    <div class="header-left">

        <button class="menu-btn" id="menuBtn">

            <!-- VOCÊ VAI COLOCAR SEU ÍCONE -->
            <img src="banco_imagens/icones/Menu.png" alt="Menu">

        </button>

        <!-- POPUP MENU -->
        <div class="menu-popup" id="menuPopup">

            <a href="#">A Plataforma</a>
            <a href="#">Benefícios</a>
            <a href="#">Estatísticas</a>

        </div>

    </div>

    <!-- LOGO -->
    <div class="header-center">

        <img src="banco_imagens/imagens/Athlete logo white.png"
             class="logo"
             alt="Logo">

    </div>

    <!-- PERFIL -->
    <div class="header-right">

        <button class="profile-btn" id="profileBtn">

          <div class="profile-avatar">

              <img src="${pageContext.request.contextPath}/banco_imagens/icones/User Male.png"
                   class="fotoPerfil">

          </div>

        </button>

        <!-- DROPDOWN PERFIL -->
        <div class="profile-popup" id="profilePopup">

            <!-- FUTURAMENTE:
                 if(logado) mostrar perfil
                 else mostrar login/cadastro
            -->

            <a href="${pageContext.request.contextPath}/perfil">
                Acessar Perfil
            </a>

            <button class="logout-btn">
                Sair
            </button>

        </div>

    </div>

</header>

<!-- ================= CARROSSEL ================= -->

<section class="carousel">

    <div class="slides">

        <div class="slide active">

            <img src="banco_imagens/imagens/treinador.png">

            <div class="overlay">

                <h2>
                    <span>Eleve o Nível</span><br>
                    A Qualquer Hora<br>
                    De Qualquer Lugar
                </h2>

                <p>
                    Conecte-se com treinadores e atletas em uma plataforma completa.
                    Acompanhe desempenho, crie planos e evolua com dados reais.
                </p>

            </div>

        </div>

        <div class="slide">

            <img src="banco_imagens/imagens/corredor_home.jpg">

            <div class="overlay">

                <h2>
                    <span>Treinamento Inteligente</span><br>
                    Evolua com Dados Reais
                </h2>

                <p>
                    Analise métricas e maximize seus resultados com estratégia.
                </p>

            </div>

        </div>

        <div class="slide">

            <img src="banco_imagens/imagens/altere_home.jpg">

            <div class="overlay">

                <h2>
                    <span>Performance Máxima</span><br>
                    Alcance Seu Pico
                </h2>

                <p>
                    Supere limites com tecnologia e acompanhamento profissional.
                </p>

            </div>

        </div>

    </div>

    <div class="navigation">

        <span class="dot active" data-index="0"></span>
        <span class="dot" data-index="1"></span>
        <span class="dot" data-index="2"></span>

    </div>

</section>

<!-- ================= MONITORAMENTO ================= -->

<section class="monitoramento">

    <div class="monitoramento-container">

        <div class="monitoramento-left">

            <img src="banco_imagens/imagens/correr_monitoramento.png" alt="">

            <div class="progress-bar">

                <div class="progress"></div>

                <span>45%</span>

            </div>

        </div>

        <div class="monitoramento-right">

            <h2>Monitoramento Constante</h2>

            <p class="descricao">
                Nossa missão é democratizar o acesso à tecnologia esportiva de elite.
                Oferecemos ferramentas profissionais para que treinadores possam
                extrair o máximo potencial de seus atletas.
            </p>

            <div class="itens">

                <div class="item">

                    <div class="icon">
                        <img src="banco_imagens/icones/Graph.png">
                    </div>

                    <div>
                        <h3>Análise Tática e Física</h3>

                        <p>
                            Métricas detalhadas de performance, velocidade,
                            resistência e posicionamento tático em um só lugar.
                        </p>
                    </div>

                </div>

                <div class="item">

                    <div class="icon">
                        <img src="banco_imagens/icones/Today.png">
                    </div>

                    <div>
                        <h3>Planejamento de Treinos</h3>

                        <p>
                            Crie cronogramas personalizados e acompanhe a carga de treino.
                        </p>
                    </div>

                </div>

                <div class="item">

                    <div class="icon">
                        <img src="banco_imagens/icones/Thumbs Up Down.png">
                    </div>

                    <div>
                        <h3>Feedback Direto</h3>

                        <p>
                            Comunicação integrada para ajustes rápidos e suporte contínuo.
                        </p>
                    </div>

                </div>

            </div>

        </div>

    </div>

</section>

<!-- ================= COMO FUNCIONA ================= -->

<section class="como-funciona">

    <h2 class="titulo">Como Funciona</h2>

    <p class="subtitulo">
        Um processo simples e direto para integrar tecnologia de ponta à sua rotina esportiva.
        <br>
        Da criação da conta até a alta performance.
    </p>

    <div class="linha-container">

        <div class="linha"></div>

        <div class="step">

            <div class="circle">
                <img src="banco_imagens/icones/Customer.png">
            </div>

            <span class="numero">1</span>

            <h3>Crie sua conta</h3>

            <p>
                Cadastre-se gratuitamente como Atleta para registrar dados
                ou como Treinador para gerenciar equipes.
            </p>

        </div>

        <div class="step">

            <div class="circle">
                <img src="banco_imagens/icones/Link.png">
            </div>

            <span class="numero">2</span>

            <h3>Conecte-se</h3>

            <p>
                Vincule seu perfil ao seu treinador ou convide seus atletas
                para formar sua equipe centralizada no sistema.
            </p>

        </div>

        <div class="step">

            <div class="circle">
                <img src="banco_imagens/icones/Graph_w.png">
            </div>

            <span class="numero">3</span>

            <h3>Evolua</h3>

            <p>
                Monitore treinos diários e analise métricas em tempo real
                para alcançar o próximo nível de performance.
            </p>

        </div>

    </div>

    <div class="cta">

        <a href="index.jsp">
            <button>Iniciar Agora →</button>
        </a>

    </div>

</section>

<!-- ================= FOOTER ================= -->

<footer class="footer">

    <div class="footer-container"></div>

</footer>

</body>
</html>