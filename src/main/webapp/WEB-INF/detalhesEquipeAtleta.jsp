<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <title>${equipe.nome} | Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">

</head>
<body>

<div class="container">
    <a href="${pageContext.request.contextPath}/perfil-atleta" class="btn-voltar">&larr; Voltar ao meu Perfil</a>

    <header class="header-equipe">
        <span class="tag">${equipe.esporte}</span>
        <h1>${equipe.nome}</h1>
        <p>${equipe.descricao}</p>
    </header>

    <div class="card">
        <h4 class="section-title">Criador & Treinador Responsável</h4>
        <div class="perfil-treinador">
            <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty treinador.foto ? treinador.foto : 'Customer.png'}" alt="Foto do Treinador">
            <div>
                <h3>${treinador.nome}</h3>
                <p>${treinador.email}</p>
            </div>
        </div>
    </div>

    <div class="card">
        <h4 class="section-title">Colegas de Equipe (${equipe.atletas.size()})</h4>
        <div class="membros-grid">
            <c:forEach var="membro" items="${equipe.atletas}">
                <div class="membro-card">
                    <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty membro.foto ? membro.foto : 'Customer.png'}" alt="Foto Atleta">
                    <p>${membro.nome}</p>
                    <span>Atleta</span>
                </div>
            </c:forEach>

            <c:if test="${empty equipe.atletas}">
                <p style="color: #444; font-size: 14px; grid-column: span 2;">Nenhum colega de equipe encontrado.</p>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>