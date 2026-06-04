<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Athlete Lab — Sobre o Projeto</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&family=Bebas+Neue&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sobre_style.css">
</head>

<body>

<!-- ===== HEADER FIXO ===== -->

<header class="site-header" id="siteHeader">

    <div class="header-logo" id="headerLogo">

        <img src="banco_imagens/imagens/Athlete logo white.png"
             class="logo" alt="Logo"
             onerror="this.style.display='none'; this.nextElementSibling.style.display='block'">
        <div class="logo-text-fallback" style="display:none">
            ATHLETE <span>LAB</span>
        </div>
    </div>

    <nav class="header-nav">
        <a href="${pageContext.request.contextPath}/home">Home</a>
        <a href="#sistema">O Sistema</a>
        <a href="#funcionalidades">Funcionalidades</a>
        <a href="#equipe">Equipe</a>
    </nav>

</header>


<!-- ===== HERO — LOGO GRANDE CENTRALIZADA ===== -->

<section class="hero" id="hero">

    <div class="hero-logo-wrap" id="heroLogoWrap">

        <img class="hero-logo-img"
             src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png"
             alt="Athlete Lab"
             onerror="this.style.display='none'; this.nextElementSibling.style.display='block'">

        <div class="hero-logo-text" style="display:none">
            ATHLETE <span>LAB</span>
        </div>

        <p class="hero-tagline">Plataforma de Gestão Esportiva</p>

    </div>

    <div class="hero-scroll-hint">
        <div class="scroll-line"></div>
        Scroll
    </div>

</section>


<div class="divider"></div>


<!-- ===== O SISTEMA ===== -->

<section class="sistema" id="sistema">
    <div class="section-inner">

        <div class="reveal">
            <span class="section-tag">O Sistema</span>
            <h2 class="section-title">O que é o <span>Athlete Lab</span>?</h2>
            <p class="section-subtitle">
                Uma plataforma web que conecta treinadores e atletas num único ambiente,
                centralizando equipes, treinos e acompanhamento de desempenho.
            </p>
        </div>

        <div class="sistema-layout">

            <div class="sistema-img-box reveal reveal-delay-1">
                <img src="banco_imagens/imagens/print_tela_home.png"
                    alt="Tela Home do Sistema"
                    style="width:100%; border-radius:18px; border:1px solid #1c1c1c;">
                <div class="img-deco"></div>
            </div>

            <div class="sistema-text reveal reveal-delay-2">
                <p style="font-size:15px; line-height:1.9; color:rgba(255,255,255,0.75); margin-bottom:24px;">
                    O <strong>Athlete Lab</strong> nasceu da necessidade de modernizar a gestão esportiva.
                    Treinadores podem criar equipes, montar treinos personalizados e acompanhar
                    cada atleta — tudo em um painel intuitivo.
                </p>
                <p style="font-size:15px; line-height:1.9; color:rgba(255,255,255,0.75);">
                    Atletas têm acesso aos seus treinos, histórico de atividades e comunicação
                    direta com o treinador, tornando a evolução mais rápida e estruturada.
                </p>

                <div class="big-stat">
                    <div class="stat">
                        <div class="stat-num">2</div>
                        <div class="stat-label">Perfis de Usuário</div>
                    </div>
                    <div class="stat">
                        <div class="stat-num">6</div>
                        <div class="stat-label">Funcionalidades</div>
                    </div>
                    <div class="stat">
                        <div class="stat-num">7</div>
                        <div class="stat-label">Tecnologias</div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</section>


<div class="divider"></div>


<!-- ===== O PROBLEMA ===== -->

<section class="problema" id="problema">
    <div class="section-inner">

        <div class="reveal">
            <span class="section-tag">O Problema</span>
            <h2 class="section-title">O que buscamos <span>resolver</span>?</h2>
        </div>

        <div class="problema-card reveal reveal-delay-1">
            <p class="problema-text">
                Treinadores e atletas ainda dependem de <strong>planilhas, cadernos e grupos de mensagens</strong>
                para organizar treinos e acompanhar resultados — ferramentas fragmentadas que dificultam
                o trabalho em equipe e a análise de desempenho.<br><br>
                O <strong>Athlete Lab</strong> centraliza tudo isso em uma plataforma única, moderna e
                acessível, para que o foco volte ao que realmente importa:
                <strong>a evolução do atleta</strong>.
            </p>
        </div>

    </div>
</section>


<div class="divider"></div>


<!-- ===== FUNCIONALIDADES ===== -->

<section class="funcionalidades" id="funcionalidades">
    <div class="section-inner">

        <div class="reveal">
            <span class="section-tag">Funcionalidades</span>
            <h2 class="section-title">O que o sistema <span>oferece</span>?</h2>
            <p class="section-subtitle">Módulos pensados para cobrir todo o ciclo de gestão esportiva.</p>
        </div>

        <div class="func-grid">

            <div class="func-card reveal reveal-delay-1">
                <div class="func-num">01</div>
                <div class="func-icon">👤</div>
                <h3>Gestão de Usuários</h3>
                <p>Cadastro e autenticação de atletas e treinadores com perfis distintos, fotos e dados completos.</p>
            </div>

            <div class="func-card reveal reveal-delay-2">
                <div class="func-num">02</div>
                <div class="func-icon">🏃</div>
                <h3>Equipes</h3>
                <p>Criação e gerenciamento de equipes esportivas com associação de atletas e controle pelo treinador.</p>
            </div>

            <div class="func-card reveal reveal-delay-3">
                <div class="func-num">03</div>
                <div class="func-icon">📋</div>
                <h3>Treinos</h3>
                <p>Planejamento de treinos com exercícios, categorias e acompanhamento do status de execução.</p>
            </div>

            <div class="func-card reveal reveal-delay-1">
                <div class="func-num">04</div>
                <div class="func-icon">🛡️</div>
                <h3>Painel Administrativo</h3>
                <p>Área exclusiva para administradores gerenciarem todo o sistema, usuários, equipes e treinos.</p>
            </div>

            <div class="func-card reveal reveal-delay-2">
                <div class="func-num">05</div>
                <div class="func-icon">✏️</div>
                <h3>Perfis Personalizáveis</h3>
                <p>Edição completa de perfil — foto, dados pessoais, modalidade esportiva e informações de contato.</p>
            </div>

            <div class="func-card reveal reveal-delay-3">
                <div class="func-num">06</div>
                <div class="func-icon">🔒</div>
                <h3>Autenticação & Sessões</h3>
                <p>Sistema seguro de login, controle de sessão e logout com redirecionamentos por tipo de usuário.</p>
            </div>

        </div>

    </div>
</section>


<div class="divider"></div>


<!-- ===== TECNOLOGIAS ===== -->

<section class="tecnologias" id="tecnologias">
    <div class="section-inner">

        <div class="tech-layout">

            <div class="reveal">
                <span class="section-tag">Stack</span>
                <h2 class="section-title">Tecnologias <span>utilizadas</span></h2>
                <p class="section-subtitle">
                    O Athlete Lab foi desenvolvido como um projeto acadêmico integrador, reunindo conceitos de análise,
                    modelagem, banco de dados e desenvolvimento web. A aplicação foi construída seguindo a arquitetura
                    MVC (Model-View-Controller), promovendo organização, manutenção e escalabilidade do sistema.
                    Durante o desenvolvimento foram aplicados conhecimentos adquiridos nas disciplinas de LPOO e APSI,
                    incluindo levantamento de requisitos, modelagem UML, documentação, implementação das regras de negócio,
                    persistência de dados e construção da interface. O resultado é uma plataforma voltada para a gestão
                    esportiva, permitindo o gerenciamento de atletas, equipes, treinos e acompanhamento de desempenho
                    em um ambiente moderno e intuitivo.
                </p>
            </div>

            <div class="tech-stack reveal reveal-delay-2">

                <div class="tech-item" data-fill="90">
                    <div class="tech-icon-box">☕</div>
                    <div class="tech-info">
                        <h4>Java</h4>
                        <p>Linguagem principal — back-end e lógica de negócio</p>
                    </div>
                    <div class="tech-bar-wrap">
                        <div class="tech-bar"><div class="tech-bar-fill"></div></div>
                    </div>
                </div>

                <div class="tech-item" data-fill="85">
                    <div class="tech-icon-box">🌐</div>
                    <div class="tech-info">
                        <h4>Jakarta Servlets</h4>
                        <p>Controle de requisições HTTP e roteamento</p>
                    </div>
                    <div class="tech-bar-wrap">
                        <div class="tech-bar"><div class="tech-bar-fill"></div></div>
                    </div>
                </div>

                <div class="tech-item" data-fill="80">
                    <div class="tech-icon-box">📄</div>
                    <div class="tech-info">
                        <h4>JSP / JSTL</h4>
                        <p>Renderização de views dinâmicas no servidor</p>
                    </div>
                    <div class="tech-bar-wrap">
                        <div class="tech-bar"><div class="tech-bar-fill"></div></div>
                    </div>
                </div>

                <div class="tech-item" data-fill="75">
                    <div class="tech-icon-box">🗄️</div>
                    <div class="tech-info">
                        <h4>MySQL</h4>
                        <p>Banco de dados relacional para persistência</p>
                    </div>
                    <div class="tech-bar-wrap">
                        <div class="tech-bar"><div class="tech-bar-fill"></div></div>
                    </div>
                </div>

                <div class="tech-item" data-fill="70">
                    <div class="tech-icon-box">🎨</div>
                    <div class="tech-info">
                        <h4>HTML5 / CSS3 / JS</h4>
                        <p>Interface e interatividade no front-end</p>
                    </div>
                    <div class="tech-bar-wrap">
                        <div class="tech-bar"><div class="tech-bar-fill"></div></div>
                    </div>
                </div>

                <div class="tech-item" data-fill="65">
                    <div class="tech-icon-box">📦</div>
                    <div class="tech-info">
                        <h4>Maven</h4>
                        <p>Gerenciamento de dependências e build</p>
                    </div>
                    <div class="tech-bar-wrap">
                        <div class="tech-bar"><div class="tech-bar-fill"></div></div>
                    </div>
                </div>

            </div>

        </div>

    </div>
</section>


<div class="divider"></div>


<!-- ===== EQUIPE ===== -->

<section class="equipe" id="equipe">
    <div class="section-inner">

        <div class="reveal" style="text-align:center">
            <span class="section-tag">Equipe</span>
            <h2 class="section-title" style="text-align:center">Quem <span>desenvolveu</span>?</h2>
            <p class="section-subtitle" style="margin:0 auto">
                Time de desenvolvimento do curso de Análise e Desenvolvimento de Sistemas.
            </p>
        </div>

<div class="equipe-grid">

    <div class="membro-card reveal reveal-delay-1">
        <img class="membro-foto"
             src="${pageContext.request.contextPath}/banco_imagens/imagens/Cauã_foto.jpeg"
             alt="Cleriston Cauã">
        <div class="membro-nome">Cleriston Cauã</div>
        <div class="membro-role">Desenvolvedor</div>
    </div>

    <div class="membro-card reveal reveal-delay-2">
        <img class="membro-foto"
             src="${pageContext.request.contextPath}/banco_imagens/imagens/Milena_foto.jpeg"
             alt="Milena">
        <div class="membro-nome">Milena</div>
        <div class="membro-role">Desenvolvedora</div>
    </div>

    <div class="membro-card reveal reveal-delay-3">
        <img class="membro-foto"
             src="${pageContext.request.contextPath}/banco_imagens/imagens/Marcos_foto.jpeg"
             alt="Marcos">
        <div class="membro-nome">Marcos</div>
        <div class="membro-role">Desenvolvedor / Líder</div>
    </div>

    <div class="membro-card reveal reveal-delay-4">
        <img class="membro-foto"
             src="${pageContext.request.contextPath}/banco_imagens/imagens/Taylon_foto.jpeg"
             alt="Thaylon">
        <div class="membro-nome">Thaylon</div>
        <div class="membro-role">Desenvolvedor</div>
    </div>

</div>
</section>


<div class="divider"></div>


<!-- ===== INFORMAÇÃO ACADÊMICA ===== -->

<section class="academico" id="academico">
    <div class="section-inner">
        <div class="academico-inner">

            <div class="reveal">
                <span class="section-tag">Contexto Acadêmico</span>
                <h2 class="section-title">Sobre o <span>Projeto</span></h2>
            </div>

            <div class="academico-body reveal reveal-delay-1">

                <div class="professores-grid">

                    <div class="membro-card">
                        <img class="membro-foto"
                             src="${pageContext.request.contextPath}/banco_imagens/imagens/professor_woquiton.jpeg"
                             alt="Woquiton Fernandes">

                        <div class="membro-nome">Woquiton Fernandes</div>
                        <div class="membro-role">Professor • LPOO</div>
                    </div>

                    <div class="membro-card">
                        <img class="membro-foto"
                             src="${pageContext.request.contextPath}/banco_imagens/imagens/professora_monique.jpg"
                             alt="Monique">

                        <div class="membro-nome">Monique</div>
                        <div class="membro-role">Professora • APSI</div>
                    </div>

                </div>

                <p>
                    Este sistema foi desenvolvido como atividade prática da disciplina
                    <strong>Linguagem de Programação Orientada a Objetos (LPOO)</strong>,
                    no curso de <strong>Análise e Desenvolvimento de Sistemas</strong>,
                    sob orientação do professor <strong>Woquiton Fernandes</strong>.
                </p>

                <p>
                    O projeto também foi fundamentado nos conhecimentos adquiridos na disciplina
                    <strong>Análise e Projeto de Sistemas de Informação (APSI)</strong>,
                    ministrada pela professora <strong>Monique</strong>. Nela foram elaborados
                    os principais artefatos do sistema, como levantamento de requisitos,
                    casos de uso, diagramas UML, modelagem do banco de dados e documentação
                    necessária para o desenvolvimento do Athlete Lab.
                </p>

            </div>

            </div>
        </div>
    </div>
</section>


<!-- ===== FOOTER ===== -->

<footer class="site-footer">
    <div class="footer-logo">ATHLETE <span>LAB</span></div>
    <p>Projeto Acadêmico &mdash; LPOO &bull; Análise e Desenvolvimento de Sistemas</p>
</footer>


<script src="${pageContext.request.contextPath}/js/sobre_script.js"></script>

</body>
</html>