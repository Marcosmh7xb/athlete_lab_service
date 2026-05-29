<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin - Treinos e Equipes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adm_treinos_equipes_style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/adm_treino_equipe_script.js"></script>
</head>
<body>

<div class="container">

    <! -- HEADER -->

    <div class="header">
        <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png" class="logo">
        <div class="header-right">
            <h2> Lista de Equipes e Treinos </h2>
            <a href="${pageContext.request.contextPath}/admin/painel" class="painel-btn"> Painel Admin </a>
        </div>
    </div>

    <div class="tabs-container">
        <button class="tab-btn active" onclick="showSection('equipes', event)"> Equipes </button>
        <button class="tab-btn" onclick="showSection('treinos', event)"> Treinos </button>
     </div>

    <!-- EQUIPES -->

    <section id="equipes" class="tab-section active">
        <button class="novo-btn" onclick="openEquipeModal()"> + Nova Equipe </button>
        <div class="table-header">
            <span>ID</span>
            <span>Nome</span>
            <span>Descrição</span>
            <span>Treinador</span>
            <span>Membros</span>
            <span>Ações</span>
        </div>
        <div id="equipesContainer"></div>
    </section>

    <!-- TREINOS -->

    <section id="treinos" class="tab-section">

        <!-- FORM -->

        <form action="${pageContext.request.contextPath}/criartreino" method="post">
            <input type="hidden" name="acao" value="salvar">

            <div class="form-grid">

                <div class="campo-wrapper">
                    <label>Nome</label>
                    <input type="text" name="nome"required>
                </div>

                <div class="campo-wrapper">
                    <label>Categoria</label>
                    <select name="categoria" required>
                        <option>Musculação</option>
                        <option>Cardio</option>
                        <option>Funcional</option>
                        <option>Cross</option>
                    </select>
                </div>

                <div class="campo-wrapper">
                    <label>Status</label>
                    <select name="status"required>
                        <option>Ativo</option>
                        <option>Inativo</option>
                    </select>
                </div>
            </div>

            <button class="criar-btn" type="submit"> + Criar Treino </button>
        </form>
    <hr>

        <!-- TREINOS -->

        <c:forEach var="treino" items="${treinos}">

            <div class="treino-card" data-id="${treino.idTreino}">
                <div class="treino-header">
                    <div>
                        <strong> ${treino.nome} </strong>
                        <div class="categoria">
                            ${treino.categoria}
                        </div>
                    </div>
                    <div class="actions">

                        <!-- EDITAR -->

                        <button type="button" class="btn-editar" onclick="abrirTreino(${treino.idTreino})"> Editar </button>

                        <!-- EXCLUIR -->

                        <form action="${pageContext.request.contextPath}/exercicio" method="post" style="display:inline;">

                            <input type="hidden" name="acao" value="deletar">
                            <input type="hidden" name="idExercicio" value="${treino.idTreino}">
                            <input type="hidden" name="idTreino" value="${treino.idTreino}">
                            <button type="submit" class="btn-deletar"> Excluir </button>

                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </section>
</div>

<!-- MODAL TREINO -->

<div id="modalTreino" class="modal">
    <div class="modal-content">

        <!-- HEADER --
        <div class="modal-header">
            <h2> Editar Treino </h2>
            <button onclick="fecharTreino()"> X </button>
        </div>

        <!-- FORM -->
        <form id="formEditarTreino">

            <input type="hidden" id="editIdTreino">
            <div class="campo-wrapper"> <label>Nome</label>
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
                    <option>Ativo</option>
                    <option>Inativo</option>
                </select>
            </div>

            <button type="button" class="criar-btn" onclick="salvarTreino()"> Salvar Alterações </button>

    <!-- EXERCICIOS -->

        </form>
        <div id="listaExercicios"> </div>
    </div>
</div>

</body>
</html>