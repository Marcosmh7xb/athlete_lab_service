<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar Equipe | Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gerenciar_equipe_style.css">
</head>
<body>

<div class="container">
    <a href="${pageContext.request.contextPath}/perfil-treinador" style="color:#888; text-decoration:none; font-size: 14px; transition: 0.3s;" onmouseover="this.style.color='red'" onmouseout="this.style.color='#888'">&larr; Voltar ao Perfil</a>

    <h2 style="margin-top:25px;">Detalhes da Equipe</h2>

    <form action="equipe" method="post">
        <input type="hidden" name="acao" value="editar">
        <input type="hidden" name="idEquipe" value="${equipe.idEquipe}">

        <div class="form-group">
            <label>Nome da Equipe</label>
            <input type="text" name="nome" value="${equipe.nome}" required>
        </div>

        <div class="form-group">
            <label>Esporte</label>
            <input type="text" name="esporte" value="${equipe.esporte}" required>
        </div>

        <div class="form-group">
            <label>Descrição</label>
            <textarea name="descricao" rows="3" placeholder="Foco ou notas sobre a equipe...">${equipe.descricao}</textarea>
        </div>

        <button type="submit" class="btn">Salvar Alterações</button>
    </form>

    <h2 style="margin-top:45px;">Membros da Equipe</h2>

    <form action="equipe" method="post" style="display:flex; gap:12px; margin-bottom:25px; align-items: flex-end;">
        <input type="hidden" name="acao" value="convidar">
        <input type="hidden" name="idEquipe" value="${equipe.idEquipe}">

        <div style="flex:1;">
            <label style="font-size: 11px; color: #888; text-transform: uppercase; margin-bottom: 5px; display: block; font-weight: 600;">Convidar Atleta por E-mail</label>
            <input type="email" name="email" placeholder="Digite o e-mail do atleta cadastrado..." required>
        </div>
        <button type="submit" class="btn" style="height: 45px;">Adicionar Atleta</button>
    </form>

    <c:if test="${not empty param.msg}">
        <p style="color:yellow; font-size:14px; background: rgba(255,255,0,0.1); padding: 10px; border-radius: 5px; border: 1px solid yellow; margin-bottom: 20px;">${param.msg}</p>
    </c:if>

    <div class="lista-atletas">
        <c:forEach var="atleta" items="${equipe.atletas}">
            <div class="atleta-item">
                <div class="atleta-info">
                    <strong>${atleta.nome}</strong>
                    <small>${atleta.email}</small>
                </div>

                <div class="actions-container">
                    <a href="${pageContext.request.contextPath}/equipe?acao=verAtleta&idAtleta=${atleta.idUsuario}" class="btn-link">
                       Ver Ficha
                    </a>

                    <form action="equipe" method="post" style="margin: 0;">
                        <input type="hidden" name="acao" value="remover">
                        <input type="hidden" name="idEquipe" value="${equipe.idEquipe}">
                        <input type="hidden" name="idAtleta" value="${atleta.idUsuario}">
                        <button type="submit" class="btn-outline">Remover</button>
                    </form>
                </div>
            </div>
        </c:forEach>

        <c:if test="${empty equipe.atletas}">
            <p style="color:#666; font-style: italic; text-align: center; padding: 20px 0;">Nenhum atleta vinculado a esta equipe ainda.</p>
        </c:if>
    </div>
</div>

</body>
</html>