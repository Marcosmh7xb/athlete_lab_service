<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Treinos</title>

    <link rel="stylesheet" href="css/treinos_style.css">

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
</head>

<body>

<header class="top-bar">

    <div class="filters">
        <button class="filter-btn" data-filter="Musculação">Musculação</button>
        <button class="filter-btn" data-filter="Cardio">Cardio</button>
        <button class="filter-btn" data-filter="Funcional">Funcional</button>
        <button class="filter-btn" data-filter="Cross">Cross</button>
    </div>

    <a href="${pageContext.request.contextPath}/criartreino" class="add-btn">
        Adicionar
    </a>
</header>

<main class="container">

    <!-- 🔥 LOOP DE TREINOS -->
    <c:forEach var="treino" items="${treinos}">

        <div class="treino-card" data-categoria="${treino.categoria}">

            <div class="treino-header">

                <div class="left-info">
                    <h3>${treino.nome}</h3>
                    <span class="categoria">${treino.categoria}</span>
                </div>

                <div class="right-info">
                    <span class="data">${treino.dataCriacao}</span>

                    <button class="edit-btn">Editar</button>

                    <button class="toggle-btn">
                        <img class="arrow-icon" src="banco_imagens/icones/Forward.png">
                    </button>
                </div>

            </div>

            <div class="treino-body">

                <!-- 🔥 LOOP DE EXERCÍCIOS -->
                <c:forEach var="ex" items="${treino.exercicios}">

                    <div class="exercicio">

                        <div class="ex-header">
                            <strong>${ex.nome}</strong>

                            <div class="actions">

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

<script>

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
        btn.classList.toggle("active");

        const activeFilters = Array.from(document.querySelectorAll(".filter-btn.active"))
            .map(btn => btn.dataset.filter);

        cards.forEach(card => {
            const categoria = card.dataset.categoria;

            if (activeFilters.length === 0) {
                card.style.display = "block";
                return;
            }

            card.style.display = activeFilters.includes(categoria)
                ? "block"
                : "none";
        });
    });
});

</script>

</body>
</html>