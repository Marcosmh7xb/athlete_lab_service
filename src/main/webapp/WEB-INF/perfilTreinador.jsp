<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil Treinador - Athlete Lab</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/treinos_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/perfil_treinador_style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
</head>

<body>

<div class="layout">

    <c:if test="${empty perfil.perfilTreinador}">
        <div class="bloqueio-total">
            <div class="card-alerta">
                <h2 style="margin-bottom: 15px;">Perfil Incompleto! ⚠️</h2>
                <p style="color: #888; margin-bottom: 25px;">Detectamos que você ainda não configurou suas informações técnicas. Complete seu cadastro para liberar o painel.</p>
                <a href="${pageContext.request.contextPath}/editar-perfil" class="btn">Completar Agora</a>
            </div>
        </div>
    </c:if>

    <aside class="sidebar">
        <div class="menu">
            <div class="menu-item active" data-tab="perfil">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Customer.png" alt="Perfil">
            </div>
            <div class="menu-item" data-tab="equipes">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Dashboard Layout.png" alt="Equipes">
            </div>
            <div class="menu-item" data-tab="treinos">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Trainers.png" alt="Treinos">
            </div>
            <div class="menu-item" data-tab="config">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Settings.png" alt="Configurações">
            </div>
        </div>
    </aside>

    <main class="content">

        <div class="top-logo">
            <a href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png" alt="Logo" >
            </a>
        </div>

        <section class="tab active" id="perfil">
            <div class="card">
                <div class="card-header">
                    <div class="user-info">
                        <div class="avatar-container">
                          <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty perfil.foto ? perfil.foto : 'Customer.png'}" class="foto-circulo" alt="Foto">
                        </div>
                        <div>
                            <h2>${perfil.nome}</h2>
                            <span>Treinador</span>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/editar-perfil">
                        <button class="btn">Editar Perfil</button>
                    </a>
                </div>

                <div class="info-grid">
                    <div class="info-box"><label>E-mail</label><p>${perfil.email}</p></div>
                    <div class="info-box"><label>Cidade/UF</label><p>${not empty perfil.cidadeUF ? perfil.cidadeUF : 'Não informado'}</p></div>
                    <div class="info-box"><label>Data de Nascimento</label><p>${not empty perfil.dataNascimento ? perfil.dataNascimento : '00/00/0000'}</p></div>
                    <div class="info-box"><label>Telefone</label><p>${not empty perfil.telefone ? perfil.telefone : '(00) 00000-0000'}</p></div>
                    <div class="info-box"><label>Modalidade</label><p>${perfil.perfilTreinador.modalidade}</p></div>
                    <div class="info-box"><label>Nível de Experiência</label><p>${perfil.perfilTreinador.nivelExperiencia}</p></div>
                    <div class="info-box"><label>Objetivo</label><p>${not empty perfil.perfilTreinador.objetivo ? perfil.perfilTreinador.objetivo : 'Não informado'}</p></div>
                    <div class="info-box"><label>Ambiente</label><p>${not empty perfil.perfilTreinador.ambiente ? perfil.perfilTreinador.ambiente : 'Não informado'}</p></div>
                    <div class="info-box"><label>Sexo Atendido</label><p>${perfil.perfilTreinador.sexo}</p></div>
                    <div class="info-box"><label>Restrição Física / Notas</label><p>${not empty perfil.perfilTreinador.restricaoFisica ? perfil.perfilTreinador.restricaoFisica : 'Nenhuma'}</p></div>
                </div>

                <div class="danger">
                    <a href="${pageContext.request.contextPath}/logout">Sair da Conta</a>
                </div>
            </div>
        </section>

        <section class="tab" id="equipes">
            <div class="card">
                <div class="card-header" style="margin-bottom: 20px;">
                    <h2>Minhas Equipes</h2>
                </div>

                <form action="${pageContext.request.contextPath}/equipe" method="post" style="background: #1a1a1a; padding: 25px; border-radius: 12px; border: 1px solid #333; margin-bottom: 40px;">
                    <input type="hidden" name="acao" value="criar">
                    <h3 style="color: red; font-size: 14px; text-transform: uppercase; margin-bottom: 15px;">Criar Nova Equipe</h3>

                    <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 15px; margin-bottom: 15px;">
                        <div>
                            <label style="font-size: 11px; color: #888; text-transform: uppercase; margin-bottom: 5px; display: block;">Nome da Equipe</label>
                            <input type="text" name="nome" placeholder="Ex: Elite Runners" required style="width: 100%; padding: 12px; background: #000; border: 1px solid #333; color: white; border-radius: 8px;">
                        </div>
                        <div>
                            <label style="font-size: 11px; color: #888; text-transform: uppercase; margin-bottom: 5px; display: block;">Esporte</label>
                            <input type="text" name="esporte" placeholder="Ex: Atletismo" required style="width: 100%; padding: 12px; background: #000; border: 1px solid #333; color: white; border-radius: 8px;">
                        </div>
                    </div>

                    <div style="margin-bottom: 15px;">
                        <label style="font-size: 11px; color: #888; text-transform: uppercase; margin-bottom: 5px; display: block;">Descrição Breve</label>
                        <input type="text" name="descricao" placeholder="Qual o foco desta equipe?" style="width: 100%; padding: 12px; background: #000; border: 1px solid #333; color: white; border-radius: 8px;">
                    </div>

                    <button type="submit" class="btn" style="width: 100%;">CADASTRAR EQUIPE</button>
                </form>

                <h3 style="font-size: 14px; text-transform: uppercase; color: #fff; border-bottom: 1px solid #333; padding-bottom: 10px; margin-bottom: 20px;">Equipes Ativas</h3>

                <div class="info-grid">
                    <c:forEach var="equipe" items="${minhasEquipes}">
                        <div class="info-box" style="display: flex; flex-direction: column; justify-content: space-between;">
                            <div>
                                <label style="color: red;">${equipe.esporte}</label>
                                <p style="font-size: 18px; font-weight: bold; margin-top: 5px;">${equipe.nome}</p>
                                <p style="font-size: 12px; color: #888; margin-top: 5px;">${equipe.descricao}</p>
                            </div>
                            <div style="margin-top: 20px; text-align: right;">
                                <a href="${pageContext.request.contextPath}/equipe?acao=gerenciar&id=${equipe.idEquipe}" style="background: transparent; border: 1px solid red; color: red; padding: 6px 15px; border-radius: 15px; text-decoration: none; font-size: 12px; font-weight: bold; transition: 0.3s;">
                                    Gerenciar Membros &rarr;
                                </a>
                            </div>
                        </div>
                    </c:forEach>

                    <c:if test="${empty minhasEquipes}">
                        <p style="color: #666; font-size: 14px; grid-column: span 2;">Você ainda não cadastrou nenhuma equipe.</p>
                    </c:if>
                </div>
            </div>
        </section>

        <section class="tab" id="treinos">
            <header class="top-bar">
                <div class="filters">
                     <button class="filter-btn" data-filter="Boxe">Boxe</button>
                     <button class="filter-btn" data-filter="Calistenia">Calistenia</button>
                     <button class="filter-btn" data-filter="Corrida">Corrida</button>
                     <button class="filter-btn" data-filter="Ciclismo">Ciclismo</button>
                     <button class="filter-btn" data-filter="Futebol">Futebol</button>
                     <button class="filter-btn" data-filter="Handebol">Handebol</button>
                     <button class="filter-btn" data-filter="Jiu-Jitsu">Jiu-Jitsu</button>
                     <button class="filter-btn" data-filter="Karaté">Karaté</button>
                     <button class="filter-btn" data-filter="Kickboxing">Kickboxing</button>
                     <button class="filter-btn" data-filter="Muay Thai">Muay Thai</button>
                     <button class="filter-btn" data-filter="Musculação">Musculação</button>
                     <button class="filter-btn" data-filter="Natação">Natação</button>
                     <button class="filter-btn" data-filter="Surf">Surf</button>
                     <button class="filter-btn" data-filter="Tênis">Tênis</button>
                     <button class="filter-btn" data-filter="Vôlei">Vôlei</button>
                </div>
                <a href="${pageContext.request.contextPath}/criartreino" class="add-btn">Adicionar</a>
            </header>

            <main class="container">
                <c:forEach var="treino" items="${treinos}">
                    <div class="treino-card" data-categoria="${treino.categoria}">
                        <div class="treino-header">
                            <div class="left-info">
                                <h3>${treino.nome}</h3>
                                <span class="categoria">${treino.categoria}</span>
                            </div>
                            <div class="right-info">
                                <span class="data">${treino.dataCriacao}</span>
                                <a href="${pageContext.request.contextPath}/treino/editar?idTreino=${treino.idTreino}" class="edit-btn">Editar</a>
                            </div>
                        </div>

                        <div class="treino-body">
                            <c:forEach var="ex" items="${treino.exercicios}">
                                <div class="exercicio">
                                    <div class="ex-header">
                                        <strong>${ex.nome}</strong>
                                        <div class="actions">
                                            <form action="${pageContext.request.contextPath}/exercicio" method="post" style="display:inline;">
                                                <input type="hidden" name="acao" value="deletar">
                                                <input type="hidden" name="idExercicio" value="${ex.idExercicio}">
                                                <input type="hidden" name="idTreino" value="${treino.idTreino}">
                                                <button type="submit" style="background:none;border:none;color:red;cursor:pointer;">Excluir</button>
                                            </form>
                                        </div>
                                    </div>
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
                <p>Ajustes da conta profissional.</p>
            </div>
        </section>

    </main>
</div>

<script>
    // TROCA DE ABAS
    const menuItems = document.querySelectorAll(".menu-item");
    const tabs = document.querySelectorAll(".tab");

    menuItems.forEach(item => {
        item.addEventListener("click", () => {
            menuItems.forEach(i => i.classList.remove("active"));
            tabs.forEach(t => t.classList.remove("active"));

            item.classList.add("active");
            document.getElementById(item.dataset.tab).classList.add("active");
        });
    });

    // ACCORDION DOS TREINOS
    document.querySelectorAll(".treino-card").forEach(card => {
        const header = card.querySelector(".treino-header");
        header.addEventListener("click", () => {
            card.classList.toggle("active");
        });
    });

    // FILTRO DINÂMICO
    const filterButtons = document.querySelectorAll(".filter-btn");
    const cards = document.querySelectorAll(".treino-card");

    filterButtons.forEach(btn => {
        btn.addEventListener("click", () => {
            btn.classList.toggle("active");

            const activeFilters = Array.from(document.querySelectorAll(".filter-btn.active"))
                .map(btn => btn.dataset.filter);

            cards.forEach(card => {
                const categoria = card.dataset.categoria;

                if (activeFilters.length === 0) {
                    card.style.display = "block";
                    return;
                }
                card.style.display = activeFilters.includes(categoria) ? "block" : "none";
            });
        });
    });
</script>

</body>
</html>
