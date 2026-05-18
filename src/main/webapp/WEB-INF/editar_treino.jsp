<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Editar Treino</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editar_treino_style.css">

    <!-- FONTES -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@100..900&display=swap" rel="stylesheet">
</head>

<body>

<div class="page">

    <div class="card">

        <!-- TOPO -->
        <div class="top-bar">
            <div class="back-button" onclick="history.back()">
                ← Voltar
            </div>
        </div>

        <!-- HEADER DO FORM -->
        <div class="form-header">

            <label>Nome do treino</label>
            <input type="text" placeholder="Ex: Treino de peito">

        </div>

        <div class="row">
            <div style="flex:1">
                <label>Categoria</label>
                <select>
                    <option>Musculação</option>
                    <option>Cardio</option>
                    <option>Funcional</option>
                    <option>Cross</option>
                </select>
            </div>

            <div style="flex:1">
                <label>Status</label>
                <select>
                    <option>Ativo</option>
                    <option>Inativo</option>
                </select>
            </div>
        </div>

        <!-- BOTÃO ADD EXERCÍCIO -->
        <div class="add-ex" id="openModal">
            <span class="icon-plus">+</span>
            <span>Adicionar exercício ao treino</span>
        </div>

        <div class="divider"></div>

        <!-- EXERCÍCIOS DINÂMICOS -->
        <div class="exercicios">

            <c:if test="${empty exercicios}">
                <p style="opacity:0.6;">Nenhum exercício adicionado ainda.</p>
            </c:if>

            <c:forEach var="ex" items="${exercicios}">

                <div class="ex-card">

                    <div class="ex-header">
                        <strong>${ex.nome}</strong>

                        <div class="actions">
                            <span class="edit">Editar</span>

                            <form action="${pageContext.request.contextPath}/exercicio" method="post" style="display:inline;">
                                <input type="hidden" name="acao" value="deletar">
                                <input type="hidden" name="idExercicio" value="${ex.idExercicio}">
                                <button type="submit" class="delete" style="border:none; background:none; cursor:pointer;">
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

        <div class="save">
            <button>Salvar Treino</button>
        </div>

    </div>
</div>

<!-- MODAL -->
<div class="modal" id="modal">

    <div class="modal-content">

        <h3>Adicionar Exercício</h3>

        <form action="${pageContext.request.contextPath}/exercicio" method="post">

            <input type="hidden" name="acao" value="salvar">
            <input type="hidden" name="idTreino" value="${treino.idTreino}">

            <label>Nome</label>
            <input type="text" name="nome" placeholder="Ex: Supino reto">

            <div class="selectors">

                <div class="select-box">
                    Séries<br>
                    <select name="series">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>

                <div class="select-box">
                    Repetições<br>
                    <select name="repeticoes">
                        <option>0</option><option>5</option><option>8</option>
                        <option>10</option><option>12</option><option>15</option>
                        <option>20</option>
                    </select>
                </div>

                <div class="select-box">
                    Tempo (min)<br>
                    <select name="tempoMin">
                        <option>0</option><option>10</option><option>15</option>
                        <option>20</option><option>30</option><option>40</option>
                    </select>
                </div>

            </div>

            <label>Observação</label>
            <textarea name="observacao" style="resize:none;"></textarea>

            <div class="modal-actions">
                <button type="button" class="btn-cancel" id="cancelModal">Cancelar</button>
                <button type="submit" class="btn-save">Salvar</button>
            </div>

        </form>

    </div>
</div>

<!-- JS -->
<script>
document.addEventListener("DOMContentLoaded", () => {

    const modal = document.getElementById("modal");
    const openBtn = document.getElementById("openModal");
    const cancelBtn = document.getElementById("cancelModal");

    openBtn.addEventListener("click", () => {
        modal.style.display = "flex";
    });

    cancelBtn.addEventListener("click", () => {
        modal.style.display = "none";
    });

    modal.addEventListener("click", (e) => {
        if (e.target.id === "modal") {
            modal.style.display = "none";
        }
    });

});
</script>

</body>
</html>