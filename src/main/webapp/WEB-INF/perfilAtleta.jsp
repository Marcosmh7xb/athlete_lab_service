<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt_BR">

<head>
    <meta charset="UTF-8">
    <title>Perfil Atleta | Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/treinos_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfil_atleta_style.css">
</head>

<body>

<c:if test="${empty perfil.perfilAtleta}">
    <div class="bloqueio-total">
        <div class="card-alerta">
            <h2 style="margin-bottom:15px;">Perfil Incompleto! ⚠️</h2>
            <p style="color:#888; margin-bottom:25px;">Complete seu cadastro para liberar o painel.</p>
            <a href="${pageContext.request.contextPath}/editar-perfil-atleta" class="btn">Completar Agora</a>
        </div>
    </div>
</c:if>

<div class="layout">

    <aside class="sidebar">
        <div class="menu">
            <div class="menu-item active" data-tab="perfil">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Customer.png">
            </div>
            <div class="menu-item" data-tab="equipes">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Dashboard Layout.png">
            </div>
            <div class="menu-item" data-tab="treinos">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Trainers.png">
            </div>
            <div class="menu-item" data-tab="config">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Settings.png">
            </div>
        </div>
    </aside>

    <main class="content">

        <div class="top-logo">
            <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png">
        </div>

        <section class="tab active" id="perfil">
            <div class="card">
                <div class="card-header">
                    <div class="user-info">
                        <div class="avatar-container">
                            <form action="${pageContext.request.contextPath}/editar-perfil-atleta"
                                  method="post" enctype="multipart/form-data">
                                <input type="file" name="foto" id="input-foto-atleta" style="display:none;" onchange="this.form.submit()">
                                <label for="input-foto-atleta" class="avatar-overlay">Trocar Foto</label>
                            </form>
                            <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty perfil.foto ? perfil.foto : 'Customer.png'}">
                        </div>
                        <div>
                            <h2>${perfil.nome}</h2>
                            <span style="color:red; font-size:12px; font-weight:bold;">ATLETA</span>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/editar-perfil-atleta" class="btn-edit">Editar Perfil</a>
                </div>

                <div class="info-grid">
                    <div>
                        <h4 class="section-title">Dados de Cadastro</h4>
                        <div class="info-box"><label>E-mail</label><p>${perfil.email}</p></div>
                        <div class="info-box" style="margin-top:10px;"><label>Telefone</label><p>${not empty perfil.telefone ? perfil.telefone : 'Não informado'}</p></div>
                        <div class="info-box" style="margin-top:10px;"><label>Cidade/UF</label><p>${not empty perfil.cidadeUF ? perfil.cidadeUF : 'Não informado'}</p></div>
                    </div>

                    <div>
                        <h4 class="section-title">Informações Técnicas</h4>
                        <div class="info-box"><label>Modalidade</label><p>${perfil.perfilAtleta.modalidade}</p></div>
                        <div class="info-box" style="margin-top:10px;"><label>Nível</label><p>${perfil.perfilAtleta.nivelExperiencia}</p></div>
                        <div class="info-box" style="margin-top:10px;"><label>Dias Disponíveis</label><p>${perfil.perfilAtleta.diasSemana}</p></div>
                    </div>

                    <div class="info-box"><label>Peso</label><p>${perfil.perfilAtleta.peso} kg</p></div>
                    <div class="info-box"><label>Altura</label><p>${perfil.perfilAtleta.altura} m</p></div>

                    <div class="full-width">
                        <div class="info-box"><label>Objetivo Principal</label><p>${perfil.perfilAtleta.objetivo}</p></div>
                    </div>
                    <div class="full-width">
                        <div class="info-box"><label>Restrições Médicas / Observações</label><p>${not empty perfil.perfilAtleta.restricaoFisica ? perfil.perfilAtleta.restricaoFisica : 'Nenhuma'}</p></div>
                    </div>
                </div>
            </div>
        </section>

        <section class="tab" id="equipes">
            <div class="card">
                <div class="card-header">
                    <h2>Minhas Equipes</h2>
                </div>
                <div class="info-grid">
                    <c:forEach var="equipe" items="${minhasEquipes}">
                        <div class="info-box">
                            <span style="color:red;">${equipe.esporte}</span>
                            <h3>${equipe.nome}</h3>
                            <p style="font-size:12px; color:#888; margin-bottom:15px;">${equipe.descricao}</p>
                            <a href="${pageContext.request.contextPath}/equipe?acao=ver&id=${equipe.idEquipe}">
                                VER INTEGRANTES &rarr;
                            </a>
                        </div>
                    </c:forEach>
                    <c:if test="${empty minhasEquipes}">
                        <p style="color:#666; grid-column: span 2;">Você ainda não faz parte de nenhuma equipe.</p>
                    </c:if>
                </div>
            </div>
        </section>

        <section class="tab" id="treinos">
            <header class="top-bar">
                <div class="filters">
                    <button class="filter-btn active" data-filter="meus">Meus Treinos</button>
                    <button class="filter-btn" data-filter="Boxe">Boxe</button>
                    <button class="filter-btn" data-filter="Calistenia">Calistenia</button>
                    <button class="filter-btn" data-filter="Corrida">Corrida</button>
                    <button class="filter-btn" data-filter="Musculação">Musculação</button>
                </div>
            </header>

            <main class="container">
                <c:if test="${empty treinos}">
                    <div class="card">
                        <h2>Nenhum treino encontrado</h2>
                        <p style="margin-top:10px; color:#888;">Seu treinador ainda não cadastrou treinos para você.</p>
                    </div>
                </c:if>

                <c:forEach var="treino" items="${treinos}">
                    <div class="treino-card" data-categoria="${treino.categoria}" data-meu-treino="true">
                        <div class="treino-header">
                            <div class="left-info">
                                <h3>${treino.nome}</h3>
                                <span class="categoria">${treino.categoria}</span>
                            </div>
                        </div>

                        <div class="treino-body">
                            <c:forEach var="ex" items="${treino.exercicios}">
                                <div class="exercicio">
                                    <strong>${ex.nome}</strong>
                                    <p>Séries: ${ex.series}</p>
                                    <p>Repetições: ${ex.repeticoes}</p>
                                    <p>Tempo: ${ex.tempoMin} min</p>
                                    <div class="obs">${ex.observacao}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </main>
        </section>

        <section class="tab" id="config">
            <div class="card">
                <h2>Configurações</h2>
                <p style="margin-bottom: 20px;">Ajustes da conta.</p>
                <a href="${pageContext.request.contextPath}/logout" style="color:red; font-weight:bold; text-decoration: none; border: 1px solid red; padding: 10px 20px; border-radius: 20px; display:inline-block;">Sair do Sistema</a>
            </div>
        </section>

    </main>
</div>

<script>
    const menuItems = document.querySelectorAll(".menu-item");
    const tabs = document.querySelectorAll(".tab");

    menuItems.forEach(item => {
        item.addEventListener("click", () => {
            const target = item.dataset.tab;
            menuItems.forEach(i => i.classList.remove("active"));
            tabs.forEach(t => t.classList.remove("active"));

            item.classList.add("active");
            const section = document.getElementById(target);
            if (section) {
                section.classList.add("active");
            }
        });
    });

    document.querySelectorAll(".treino-card").forEach(card => {
        const header = card.querySelector(".treino-header");
        header.addEventListener("click", () => {
            card.classList.toggle("active");
        });
    });

    const filterButtons = document.querySelectorAll(".filter-btn");
    const cards = document.querySelectorAll(".treino-card");

    filterButtons.forEach(btn => {
        btn.addEventListener("click", () => {
            filterButtons.forEach(b => b.classList.remove("active"));
            btn.classList.add("active");

            const filtro = btn.dataset.filter;

            cards.forEach(card => {
                const categoria = card.dataset.categoria;
                const meuTreino = card.dataset.meuTreino;

                if (filtro === "meus") {
                    card.style.display = meuTreino === "true" ? "block" : "none";
                    return;
                }
                card.style.display = categoria === filtro ? "block" : "none";
            });
        });
    });
</script>

</body>
</html>