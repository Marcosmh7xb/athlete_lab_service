<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalhes da Equipe | Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/detalhes_equipe_atletas_style.css">
</head>
<body>

<div class="container">
    <a href="${pageContext.request.contextPath}/perfil-atleta" class="btn-voltar">&larr; Voltar ao Perfil</a>

    <div class="equipe-header" style="margin-top: 25px;">
        <span>${equipe.esporte}</span>
        <h1>${equipe.nome}</h1>
        <p>${not empty equipe.descricao ? equipe.descricao : 'Nenhuma descrição fornecida para esta equipe.'}</p>
    </div>

    <h2 class="section-title">Treinador Responsável</h2>
    <div class="membro-item" style="border-color: red; margin-bottom: 25px;">
        <div class="membro-info">
            <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty equipe.treinador.foto ? equipe.treinador.foto : (not empty treinador.foto ? treinador.foto : 'Customer.png')}" alt="Foto do Treinador">
            <div class="membro-detalhes">
                <strong>${not empty equipe.treinador.nome ? equipe.treinador.nome : treinador.nome}</strong>
                <small>${not empty equipe.treinador.email ? equipe.treinador.email : treinador.email}</small>
            </div>
        </div>
        <span class="badge-role" style="background: rgba(255, 0, 0, 0.1); color: red; border-color: red;">Treinador</span>
    </div>

    <h2 class="section-title">Integrantes do Time</h2>

    <div class="lista-membros">
        <c:forEach var="atleta" items="${equipe.atletas}">
            <div class="membro-item">
                <div class="membro-info">
                    <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty atleta.foto ? atleta.foto : 'Customer.png'}" alt="Foto do Integrante">
                    <div class="membro-detalhes">
                        <strong>${atleta.nome}</strong>
                        <small>${atleta.email}</small>
                    </div>
                </div>
                <span class="badge-role">Atleta</span>
            </div>
        </c:forEach>

        <c:if test="${empty equipe.atletas}">
            <p style="color:#666; font-style: italic; text-align: center; padding: 20px 0;">Nenhum outro atleta vinculado a este time ainda.</p>
        </c:if>
    </div>
</div>

</body>
</html>