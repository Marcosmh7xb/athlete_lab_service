<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Treinos</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/treinos_style.css">
    <script src="${pageContext.request.contextPath}/js/treinos_script.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
</head>

<body>

<header class="top-bar">

    <div class="filters">
        <button class="filter-btn" data-filter="Alongamento">Alongamento</button>
        <button class="filter-btn" data-filter="Boxe">Boxe</button>
        <button class="filter-btn" data-filter="Calistenia">Calistenia</button>
        <button class="filter-btn" data-filter="Cardio">Cardio</button>
        <button class="filter-btn" data-filter="Corrida">Corrida</button>
        <button class="filter-btn" data-filter="Cross">Cross</button>
        <button class="filter-btn" data-filter="Crossfit">Crossfit</button>
        <button class="filter-btn" data-filter="Ciclismo">Ciclismo</button>
        <button class="filter-btn" data-filter="Dança">Dança</button>
        <button class="filter-btn" data-filter="Funcional">Funcional</button>
        <button class="filter-btn" data-filter="Futebol">Futebol</button>
        <button class="filter-btn" data-filter="Handebol">Handebol</button>
        <button class="filter-btn" data-filter="HIIT">HIIT</button>
        <button class="filter-btn" data-filter="Jiu-Jitsu">Jiu-Jitsu</button>
        <button class="filter-btn" data-filter="Karatê">Karatê</button>
        <button class="filter-btn" data-filter="Kickboxing">Kickboxing</button>
        <button class="filter-btn" data-filter="Muay Thai">Muay Thai</button>
        <button class="filter-btn" data-filter="Musculação">Musculação</button>
        <button class="filter-btn" data-filter="Natação">Natação</button>
        <button class="filter-btn" data-filter="Pilates">Pilates</button>
        <button class="filter-btn" data-filter="Powerlifting">Powerlifting</button>
        <button class="filter-btn" data-filter="Skate">Skate</button>
        <button class="filter-btn" data-filter="Surf">Surf</button>
        <button class="filter-btn" data-filter="Tênis">Tênis</button>
        <button class="filter-btn" data-filter="Vôlei">Vôlei</button>
        <button class="filter-btn" data-filter="Yoga">Yoga</button>
    </div>

    <!-- Botão de adicionar só aparece se não for atleta -->
    <c:if test="${usuario.tipoUsuario != 'ATLETA'}">
        <a href="${pageContext.request.contextPath}/criartreino" class="add-btn">
            Adicionar
        </a>
    </c:if>
</header>

<main class="container">

    <!-- LOOP DE TREINOS -->
    <c:forEach var="treino" items="${treinos}">
        <div class="treino-card" data-categoria="${treino.categoria}">

            <div class="treino-header">
                <div class="left-info">
                    <h3>${treino.nome}</h3>
                    <span class="categoria">${treino.categoria}</span>
                </div>

                <div class="right-info">
                    <span class="data">${treino.dataCriacao}</span>

                    <!-- Botões só aparecem para não atletas -->
                    <c:if test="${usuario.tipoUsuario != 'ATLETA'}">
                        <button class="edit-btn">Editar</button>
                    </c:if>

                    <button class="toggle-btn">
                        <img class="arrow-icon" src="banco_imagens/icones/Forward.png">
                    </button>
                </div>
            </div>

            <div class="treino-body">

                <!-- LOOP DE EXERCÍCIOS -->
                <c:forEach var="ex" items="${treino.exercicios}">
                    <div class="exercicio">
                        <div class="ex-header">
                            <strong>${ex.nome}</strong>

                            <div class="actions">
                                <!-- Somente não atletas podem editar/excluir exercícios -->
                                <c:if test="${usuario.tipoUsuario != 'ATLETA'}">
                                    <span class="edit">Editar</span>

                                    <form action="${pageContext.request.contextPath}/exercicio"
                                          method="post"
                                          style="display:inline;">
                                        <input type="hidden" name="acao" value="deletar">
                                        <input type="hidden" name="idExercicio" value="${ex.idExercicio}">
                                        <input type="hidden" name="idTreino" value="${treino.idTreino}">
                                        <button type="submit"
                                                style="background:none;border:none;color:red;cursor:pointer;">
                                            Excluir
                                        </button>
                                    </form>
                                </c:if>
                            </div>
                        </div>

                        <p>Séries: ${ex.series}</p>
                        <p>Repetições: ${ex.repeticoes}</p>
                        <p>Tempo: ${ex.tempoMin} min</p>

                        <div class="obs">
                            ${ex.observacao}
                        </div>
                    </div>
                </c:forEach>

            </div>

        </div>
    </c:forEach>

</main>

</body>
</html>