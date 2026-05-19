<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Criar Treino</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/criar_treino.css">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
</head>

<body>

<div class="page">

    <div class="card">

        <h2>Criar Novo Treino</h2>

        <form action="${pageContext.request.contextPath}/criartreino" method="post">

            <input type="hidden" name="acao" value="salvar">

            <label>Nome do treino</label>
            <input type="text" name="nome" placeholder="Ex: Treino de peito" required>

            <label>Categoria</label>
            <select name="categoria">
                <option>Musculação</option>
                <option>Cardio</option>
                <option>Funcional</option>
                <option>Cross</option>
            </select>

            <label>Status</label>
            <select name="status">
                <option>Ativo</option>
                <option>Inativo</option>
            </select>

            <div class="actions">
                <button type="submit">Criar Treino</button>
            </div>

        </form>

    </div>

</div>

</body>
</html>