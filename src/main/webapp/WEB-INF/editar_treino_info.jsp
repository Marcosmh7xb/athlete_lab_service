<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Editar Treino</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/criar_treino.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
          rel="stylesheet">
</head>

<body>

<div class="page">
    <div class="card">

        <h2>Editar Treino</h2>

        <form action="${pageContext.request.contextPath}/treino/editarInfo"
              method="post">

            <input type="hidden"
                   name="idTreino"
                   value="${treino.idTreino}">

            <label>Email</label>
            <input type="email"
                   name="email"
                   value="${perfil.email}"
                   required>

            <label>Nome do treino</label>
            <input type="text"
                   name="nome"
                   value="${treino.nome}"
                   required>

            <label>Categoria</label>
            <select name="categoria">

                <option ${treino.categoria == 'Alongamento' ? 'selected' : ''}>Alongamento</option>
                <option ${treino.categoria == 'Boxe' ? 'selected' : ''}>Boxe</option>
                <option ${treino.categoria == 'Calistenia' ? 'selected' : ''}>Calistenia</option>
                <option ${treino.categoria == 'Cardio' ? 'selected' : ''}>Cardio</option>
                <option ${treino.categoria == 'Corrida' ? 'selected' : ''}>Corrida</option>
                <option ${treino.categoria == 'Cross' ? 'selected' : ''}>Cross</option>
                <option ${treino.categoria == 'Ciclismo' ? 'selected' : ''}>Ciclismo</option>
                <option ${treino.categoria == 'Funcional' ? 'selected' : ''}>Funcional</option>
                <option ${treino.categoria == 'Musculação' ? 'selected' : ''}>Musculação</option>
                <option ${treino.categoria == 'Natação' ? 'selected' : ''}>Natação</option>
                <option ${treino.categoria == 'Yoga' ? 'selected' : ''}>Yoga</option>

            </select>

            <label>Status</label>
            <label>Status</label>
            <select name="status">

                <option value="ATIVO"
                    ${treino.status == 'ATIVO' ? 'selected' : ''}>
                    ATIVO
                </option>

                <option value="INATIVO"
                    ${treino.status == 'INATIVO' ? 'selected' : ''}>
                    INATIVO
                </option>

            </select>

            <div class="actions">
                <button type="submit">
                    Salvar Alterações
                </button>
            </div>

        </form>

    </div>
</div>

</body>
</html>