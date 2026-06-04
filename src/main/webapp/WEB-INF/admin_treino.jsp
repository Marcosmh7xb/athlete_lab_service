<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Treinos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adm_treinos_equipes_style.css">

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>

<div class="container">

    <!-- HEADER -->
    <div class="header">
        <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png" class="logo">
        <div class="header-right">
            <h2>Gerenciar Treinos</h2>
            <a href="${pageContext.request.contextPath}/admin/painel" class="painel-btn">Painel Admin</a>
        </div>
    </div>

    <!-- BOTÃO ABRIR MODAL CRIAR -->
    <button class="novo-btn" onclick="abrirModalCriar()">+ Novo Treino</button>

    <hr>

    <!-- LISTA DE TREINOS -->
    <c:choose>
        <c:when test="${empty treinos}">
            <p style="opacity:0.5; margin-top:20px;">Nenhum treino cadastrado.</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="treino" items="${treinos}">

                <div class="treino-card" data-id="${treino.idTreino}">

                    <div class="treino-header"
                        onclick="toggleTreino(this)">

                        <div>
                            <strong>${treino.nome}</strong>
                            <div class="categoria">${treino.categoria}</div>
                        </div>

                        <div class="actions">

                            <button
                                type="button"
                                class="btn-editar"
                                onclick="event.stopPropagation(); abrirTreino(${treino.idTreino})">

                                Editar
                            </button>

                            <button
                                type="button"
                                class="btn-deletar"
                                onclick="event.stopPropagation(); confirmarExcluir(${treino.idTreino})">

                                Excluir
                            </button>

                        </div>

                    </div>

                    <div class="treino-body">

                        <c:choose>

                            <c:when test="${empty treino.exercicios}">
                                <div class="exercicio-card">
                                    Nenhum exercício cadastrado.
                                </div>
                            </c:when>

                            <c:otherwise>

                                <c:forEach var="ex" items="${treino.exercicios}">

                                    <div class="exercicio-card">

                                        <strong>${ex.nome}</strong>

                                        <p>Séries: ${ex.series}</p>

                                        <p>Repetições: ${ex.repeticoes}</p>

                                        <p>Tempo: ${ex.tempoMin} min</p>

                                    </div>

                                </c:forEach>

                            </c:otherwise>

                        </c:choose>

                    </div>

                </div>

        </c:forEach>
        </c:otherwise>
    </c:choose>

</div>

<!-- MODAL CRIAR TREINO -->
<div id="modalCriar" class="modal-overlay">
    <div class="modal">
        <div class="modal-header">
            <h2>Novo Treino</h2>
            <button class="modal-close" onclick="fecharModalCriar()">X</button>
        </div>
        <div class="modal-form">
            <form action="${pageContext.request.contextPath}/admin/treinos" method="post">
                <input type="hidden" name="acao" value="criar">

                <div class="campo-wrapper">
                    <label>Nome</label>
                    <input type="text" name="nome" required>
                </div>

                <div class="campo-wrapper">
                    <label>Categoria</label>
                    <select name="categoria" required>
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
                    </select>
                </div>

                <div class="campo-wrapper">
                    <label>Status</label>
                    <select name="status" required>
                        <option value="ATIVO">Ativo</option>
                        <option value="INATIVO">Inativo</option>
                    </select>
                </div>

                <button class="criar-btn" type="submit" style="margin-top:15px;">Criar Treino</button>
            </form>
        </div>
    </div>
</div>

<!-- MODAL EDITAR TREINO -->
<div id="modalTreino" class="modal-overlay">
    <div class="modal">
        <div class="modal-header">
            <h2>Editar Treino</h2>
            <button class="modal-close" onclick="fecharTreino()">X</button>
        </div>
        <div class="modal-form">
            <form id="formEditarTreino">
                <input type="hidden" id="editIdTreino">

                <div class="campo-wrapper">
                    <label>Nome</label>
                    <input type="text" id="editNome">
                </div>

                <div class="campo-wrapper">
                    <label>Categoria</label>
                    <select id="editCategoria">
                        <option>Musculação</option>
                        <option>Cardio</option>
                        <option>Funcional</option>
                        <option>Cross</option>
                    </select>
                </div>

                <div class="campo-wrapper">
                    <label>Status</label>
                    <select id="editStatus">
                        <option value="ATIVO">Ativo</option>
                        <option value="INATIVO">Inativo</option>
                    </select>
                </div>

                <button type="button" class="criar-btn" onclick="salvarTreino()" style="margin-top:15px;">Salvar Alterações</button>
            </form>

            <div id="listaExercicios"></div>
        </div>
    </div>
</div>

<!-- MODAL CONFIRMAR EXCLUSÃO -->
<div id="modalConfirmar" class="confirmar-overlay">
    <div class="confirmar-box">
        <p>Tem certeza que deseja excluir este treino?</p>
        <div class="confirmar-acoes">
            <button class="btn-confirmar-sim" onclick="executarExcluir()">Excluir</button>
            <button class="btn-confirmar-nao" onclick="fecharConfirmar()">Cancelar</button>
        </div>
    </div>
</div>

<!-- FORM OCULTO PARA EXCLUIR -->
<form id="formExcluir" action="${pageContext.request.contextPath}/admin/criartreino" method="post" style="display:none;">
    <input type="hidden" name="acao" value="deletar">
    <input type="hidden" name="idTreino" id="idTreinoExcluir">
</form>

<!-- TOAST -->
<div id="toast" class="toast"></div>

<script src="${pageContext.request.contextPath}/js/adm_treino_equipe_script.js"></script>
</body>
</html>