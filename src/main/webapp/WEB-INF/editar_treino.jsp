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
    <script src="${pageContext.request.contextPath}/js/editar_treino_script.js"></script>

</head>

<body>

<div class="page">
    <div class="card">
        <!-- TOPO -->
        <div class="top-bar">
            <div class="back-button" onclick="history.back()"> ← Voltar </div>
        </div>

        <!-- BOTÃO ADD -->
        <div class="add-ex"
             id="openModal">
            <span class="icon-plus">+</span>
            <span>Adicionar exercício ao treino</span>
        </div>

        <div class="divider"></div>

        <!-- EXERCICIOS -->

        <div class="exercicios">

            <c:if test="${empty exercicios}">
                <p style="opacity:0.6;">
                    Nenhum exercício adicionado ainda.
                </p>
            </c:if>

            <c:forEach var="ex" items="${exercicios}">

                <div class="ex-card">
                    <div class="ex-header">
                        <strong>${ex.nome}</strong>
                        <div class="actions">

                            <!-- BOTAO EDITAR -->
                            <button class="edit-btn" data-id="${ex.idExercicio}" data-nome="${ex.nome}" data-series="${ex.series}" data-repeticoes="${ex.repeticoes}" data-tempo="${ex.tempoMin}" data-obs="${ex.observacao}"> Editar </button>

                            <!-- EXCLUIR -->
                            <form action="${pageContext.request.contextPath}/exercicio" method="post" style="display:inline;">

                                <input type="hidden" name="acao" value="deletar">
                                <input type="hidden" name="idExercicio" value="${ex.idExercicio}">
                                <input type="hidden" name="idTreino" value="${treino.idTreino}">

                                <button type="submit" class="delete"> Excluir </button>

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

        <!-- SALVAR -->
        <div class="save">
            <a href="${pageContext.request.contextPath}/perfil-treinador?sucesso=1&idTreino=${treino.idTreino}" class="btn-cancel"> Salvar Treino  </a>
        </div>
    </div>
</div>

<!-- MODAL -->
<div class="modal"
     id="modal">

    <div class="modal-content">
        <h3 id="modalTitle"> Adicionar Exercício </h3>

        <form action="${pageContext.request.contextPath}/exercicio" method="post">

            <!-- ACAO -->
            <input type="hidden" name="acao" id="acaoInput" value="salvar">

            <!-- ID TREINO -->
            <input type="hidden" name="idTreino" value="${treino.idTreino}">

            <!-- ID EXERCICIO -->
            <input type="hidden" name="idExercicio" id="idExercicio">

            <!-- NOME -->
            <label>Nome</label>

            <input type="text" name="nome" id="nomeInput" placeholder="Ex: Supino reto">

            <!-- SELECTS -->
            <div class="selectors">

                <!-- SERIES -->
                <div class="select-box">

                    Séries<br>
                    <select name="series" id="seriesInput">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>

                </div>
                <!-- REPETICOES -->
                <div class="select-box"> Repetições<br>
                    <select name="repeticoes" id="repeticoesInput">
                        <option value="5">5</option>
                        <option value="8">8</option>
                        <option value="10">10</option>
                        <option value="12">12</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                    </select>
                </div>

                <!-- TEMPO -->
                <div class="select-box"> Tempo (min)<br>
                   <select name="tempoMin" id="tempoInput">
                        <option value="0">0</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        <option value="30">30</option>
                        <option value="40">40</option>
                    </select>
                </div>
            </div>

            <!-- OBS -->
            <label>Observação</label>

            <textarea name="observacao" id="obsInput"></textarea>

            <!-- ACOES -->
            <div class="modal-actions">
                <button type="button" class="btn-cancel" id="cancelModal"> Cancelar </button>
                <button type="submit" class="btn-save" id="submitBtn"> Salvar </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>