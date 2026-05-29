<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt_BR">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Perfil Treinador | Athlete Lab</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/treinos_style.css">

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
          rel="stylesheet">

              <link rel="stylesheet"
                    href="${pageContext.request.contextPath}/css/perfil_treinador_style.css">

                 <script src="${pageContext.request.contextPath}/js/perfil_treinador.js"></script>

</head>

<body>

<!-- ================= BLOQUEIO ================= -->

<c:if test="${empty perfil.perfilTreinador}">

    <div class="bloqueio-total">

        <div class="card-alerta">

            <h2 style="margin-bottom:15px;">
                Perfil Incompleto! ⚠️
            </h2>

            <p style="color:#888; margin-bottom:25px;">

                Complete seu cadastro de treinador
                para liberar o painel.

            </p>

            <a href="${pageContext.request.contextPath}/editar-perfil"
               class="btn">

                Completar Agora

            </a>

        </div>

    </div>

</c:if>

<div class="layout">

    <!-- ================= SIDEBAR ================= -->

    <aside class="sidebar">

        <div class="menu">

            <div class="menu-item active"
                 data-tab="perfil">

                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Customer.png">

            </div>

            <div class="menu-item"
                 data-tab="equipes">

                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Dashboard Layout.png">

            </div>

            <div class="menu-item"
                 data-tab="treinos">

                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Trainers.png">

            </div>

            <div class="menu-item"
                 data-tab="config">

                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Settings.png">

            </div>

        </div>

    </aside>

    <!-- ================= CONTEÚDO ================= -->

    <main class="content">

        <div class="top-logo">

            <a href="${pageContext.request.contextPath}/home">

                <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png">

            </a>

        </div>

        <!-- ================= PERFIL ================= -->

        <section class="tab active"
                 id="perfil">

            <div class="card">

                <div class="card-header">

                    <div class="user-info">

                        <div class="avatar-container">

                            <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty perfil.foto ? perfil.foto : 'Customer.png'}">

                        </div>

                        <div>

                            <h2>${perfil.nome}</h2>

                            <span>TREINADOR</span>

                        </div>

                    </div>

                    <a href="${pageContext.request.contextPath}/editar-perfil"
                       class="btn">

                        Editar Perfil

                    </a>

                </div>

                <div class="info-grid">

                    <div class="info-box">

                        <label>E-mail</label>

                        <p>${perfil.email}</p>

                    </div>

                    <div class="info-box">

                        <label>Cidade/UF</label>

                        <p>
                            ${not empty perfil.cidadeUF ? perfil.cidadeUF : 'Não informado'}
                        </p>

                    </div>

                    <div class="info-box">

                        <label>Data de Nascimento</label>

                        <p>
                            ${not empty perfil.dataNascimento ? perfil.dataNascimento : '00/00/0000'}
                        </p>

                    </div>

                    <div class="info-box">

                        <label>Telefone</label>

                        <p>
                            ${not empty perfil.telefone ? perfil.telefone : '(00) 00000-0000'}
                        </p>

                    </div>

                    <div class="info-box">

                        <label>Modalidade</label>

                        <p>${perfil.perfilTreinador.modalidade}</p>

                    </div>

                    <div class="info-box">

                        <label>Nível de Experiência</label>

                        <p>${perfil.perfilTreinador.nivelExperiencia}</p>

                    </div>

                    <div class="info-box">

                        <label>Ambiente</label>

                        <p>
                            ${not empty perfil.perfilTreinador.ambiente ? perfil.perfilTreinador.ambiente : 'Não informado'}
                        </p>

                    </div>

                    <div class="info-box">

                        <label>Sexo Atendido</label>

                        <p>${perfil.perfilTreinador.sexo}</p>

                    </div>

                    <div class="info-box full-width">

                        <label>Objetivo / Metodologia</label>

                        <p>
                            ${not empty perfil.perfilTreinador.objetivo ? perfil.perfilTreinador.objetivo : 'Não informado'}
                        </p>

                    </div>

                    <div class="info-box full-width">

                        <label>Restrições / Notas</label>

                        <p>
                            ${not empty perfil.perfilTreinador.restricaoFisica ? perfil.perfilTreinador.restricaoFisica : 'Nenhuma'}
                        </p>

                    </div>

                </div>

                <div class="danger">

                    <a href="${pageContext.request.contextPath}/logout">

                        Sair da Conta

                    </a>

                </div>

            </div>

        </section>

        <!-- ================= EQUIPES ================= -->

        <section class="tab"
                 id="equipes">

            <div class="card">

                <h2 style="margin-bottom:20px;">
                    Minhas Equipes
                </h2>

                <!-- FORM CRIAR EQUIPE -->

                <form action="${pageContext.request.contextPath}/equipe"
                      method="post"
                      style="background:#1a1a1a;
                             padding:25px;
                             border-radius:12px;
                             border:1px solid #333;
                             margin-bottom:25px;">

                    <input type="hidden"
                           name="acao"
                           value="criar">

                    <div style="display:grid;
                                grid-template-columns:1fr 1fr;
                                gap:15px;
                                margin-bottom:15px;">

                        <input type="text"
                               name="nome"
                               placeholder="Nome da Equipe"
                               required
                               style="width:100%;
                                      padding:12px;
                                      background:#000;
                                      border:1px solid #333;
                                      color:white;
                                      border-radius:8px;">

                        <input type="text"
                               name="esporte"
                               placeholder="Esporte"
                               required
                               style="width:100%;
                                      padding:12px;
                                      background:#000;
                                      border:1px solid #333;
                                      color:white;
                                      border-radius:8px;">

                    </div>

                    <input type="text"
                           name="descricao"
                           placeholder="Descrição da equipe"
                           style="width:100%;
                                  padding:12px;
                                  background:#000;
                                  border:1px solid #333;
                                  color:white;
                                  border-radius:8px;
                                  margin-bottom:15px;">

                    <button type="submit"
                            class="btn"
                            style="width:100%;">

                        CRIAR EQUIPE

                    </button>

                </form>

                <!-- LISTA EQUIPES -->

                <div class="info-grid">

                    <c:forEach var="equipe"
                               items="${minhasEquipes}">

                        <div class="info-box">

                            <label style="color:red;">
                                ${equipe.esporte}
                            </label>

                            <p style="margin-top:8px; font-size:18px;">

                                <strong>${equipe.nome}</strong>

                            </p>

                            <p style="font-size:12px;
                                      color:#888;
                                      margin-top:8px;">

                                ${equipe.descricao}

                            </p>

                            <a href="${pageContext.request.contextPath}/equipe?acao=gerenciar&id=${equipe.idEquipe}"
                               style="color:red;
                                      font-size:12px;
                                      display:block;
                                      margin-top:15px;
                                      text-decoration:none;">

                                Gerenciar →

                            </a>

                        </div>

                    </c:forEach>

                </div>

            </div>

        </section>

        <!-- ================= TREINOS ================= -->

        <section class="tab"
                 id="treinos">

            <header class="top-bar">

                <div class="filters">

                    <button class="filter-btn"
                            data-filter="Boxe">

                        Boxe

                    </button>

                    <button class="filter-btn"
                            data-filter="Calistenia">

                        Calistenia

                    </button>

                    <button class="filter-btn"
                            data-filter="Corrida">

                        Corrida

                    </button>

                    <button class="filter-btn"
                            data-filter="Ciclismo">

                        Ciclismo

                    </button>

                    <button class="filter-btn"
                            data-filter="Futebol">

                        Futebol

                    </button>

                    <button class="filter-btn"
                            data-filter="Handebol">

                        Handebol

                    </button>

                    <button class="filter-btn"
                            data-filter="Jiu-Jitsu">

                        Jiu-Jitsu

                    </button>

                    <button class="filter-btn"
                            data-filter="Karatê">

                        Karatê

                    </button>

                    <button class="filter-btn"
                            data-filter="Kickboxing">

                        Kickboxing

                    </button>

                    <button class="filter-btn"
                            data-filter="Muay Thai">

                        Muay Thai

                    </button>

                    <button class="filter-btn"
                            data-filter="Musculação">

                        Musculação

                    </button>

                    <button class="filter-btn"
                            data-filter="Natação">

                        Natação

                    </button>

                    <button class="filter-btn"
                            data-filter="Surf">

                        Surf

                    </button>

                    <button class="filter-btn"
                            data-filter="Tênis">

                        Tênis

                    </button>

                    <button class="filter-btn"
                            data-filter="Vôlei">

                        Vôlei

                    </button>

                </div>

                <a href="${pageContext.request.contextPath}/criartreino"
                   class="add-btn">

                    Adicionar

                </a>

            </header>

            <main class="container">

                <c:forEach var="treino"
                           items="${treinos}">

                    <div class="treino-card"
                         data-categoria="${treino.categoria}">

                        <div class="treino-header">

                            <div class="left-info">

                                <h3>${treino.nome}</h3>

                                <span class="categoria">

                                    ${treino.categoria}

                                </span>

                            </div>

                            <div class="right-info">

                                <span class="data">

                                    ${treino.dataCriacao}

                                </span>

                                <a href="${pageContext.request.contextPath}/treino/editar?idTreino=${treino.idTreino}"
                                   class="edit-btn">

                                    Editar

                                </a>

                            </div>

                        </div>

                        <div class="treino-body">

                            <c:forEach var="ex"
                                       items="${treino.exercicios}">

                                <div class="exercicio">

                                    <div class="ex-header">

                                        <strong>${ex.nome}</strong>

                                        <div class="actions">

                                            <form action="${pageContext.request.contextPath}/exercicio"
                                                  method="post"
                                                  style="display:inline;">

                                                <input type="hidden"
                                                       name="acao"
                                                       value="deletar">

                                                <input type="hidden"
                                                       name="idExercicio"
                                                       value="${ex.idExercicio}">

                                                <input type="hidden"
                                                       name="idTreino"
                                                       value="${treino.idTreino}">

                                                <button type="submit"
                                                        style="background:none;
                                                               border:none;
                                                               color:red;
                                                               cursor:pointer;">

                                                    Excluir

                                                </button>

                                            </form>

                                        </div>

                                    </div>

                                    <p>
                                        Séries:
                                        ${ex.series}
                                    </p>

                                    <p>
                                        Repetições:
                                        ${ex.repeticoes}
                                    </p>

                                    <p>
                                        Tempo:
                                        ${ex.tempoMin} min
                                    </p>

                                    <div class="obs">

                                        ${ex.observacao}

                                    </div>

                                </div>

                            </c:forEach>

                        </div>

                    </div>

                </c:forEach>

            </main>

        </section>

        <!-- ================= CONFIG ================= -->

        <section class="tab"
                 id="config">

            <div class="card">

                <h2>Configurações</h2>

                <p style="margin-top:10px; color:#888;">

                    Ajustes do sistema em breve.

                </p>

            </div>

        </section>

    </main>

</div>

<!-- ================= SCRIPT ================= -->
<script>

</script>

</body>
</html>