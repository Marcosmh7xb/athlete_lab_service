<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <title>Gerenciar Equipe</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <style>
        body { background: #000; font-family: "Inter", sans-serif; color: white; padding: 40px; }
        .container { max-width: 800px; margin: 0 auto; background: #0f0f0f; padding: 30px; border-radius: 15px; border: 1px solid #222; }
        h2 { color: red; margin-bottom: 20px; border-bottom: 1px solid #222; padding-bottom: 10px; }
        .form-group { margin-bottom: 15px; }
        input, textarea { width: 100%; padding: 10px; background: #1a1a1a; border: 1px solid #333; color: white; border-radius: 5px; }
        .btn { background: red; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; font-weight: bold; }
        .lista-atletas { margin-top: 30px; }
        .atleta-item { display: flex; justify-content: space-between; background: #161616; padding: 15px; margin-bottom: 10px; border-radius: 8px; align-items: center; }
    </style>
</head>
<body>

<div class="container">
    <a href="${pageContext.request.contextPath}/perfil-treinador" style="color:#888; text-decoration:none;">&larr; Voltar ao Perfil</a>

    <h2 style="margin-top:20px;">Detalhes da Equipe</h2>

    <form action="equipe" method="post">
        <input type="hidden" name="acao" value="editar">
        <input type="hidden" name="idEquipe" value="${equipe.idEquipe}">
        <div class="form-group"><label>Nome</label><input type="text" name="nome" value="${equipe.nome}" required></div>
        <div class="form-group"><label>Esporte</label><input type="text" name="esporte" value="${equipe.esporte}" required></div>
        <div class="form-group"><label>Descrição</label><textarea name="descricao" rows="3">${equipe.descricao}</textarea></div>
        <button type="submit" class="btn">Salvar Alterações</button>
    </form>

    <h2 style="margin-top:40px;">Membros da Equipe</h2>

    <form action="equipe" method="post" style="display:flex; gap:10px; margin-bottom:20px;">
        <input type="hidden" name="acao" value="convidar">
        <input type="hidden" name="idEquipe" value="${equipe.idEquipe}">
        <input type="email" name="email" placeholder="Digite o e-mail do atleta..." required style="flex:1;">
        <button type="submit" class="btn">Adicionar Atleta</button>
    </form>

    <c:if test="${not empty param.msg}"><p style="color:yellow; font-size:14px;">${param.msg}</p></c:if>

    <div class="lista-atletas">
        <c:forEach var="atleta" items="${equipe.atletas}">
            <div class="atleta-item">
                <div>
                    <strong>${atleta.nome}</strong><br>
                    <small style="color:#888;">${atleta.email}</small>
                </div>
                <form action="equipe" method="post">
                    <input type="hidden" name="acao" value="remover">
                    <input type="hidden" name="idEquipe" value="${equipe.idEquipe}">
                    <input type="hidden" name="idAtleta" value="${atleta.idUsuario}">
                    <button type="submit" style="background:transparent; border:1px solid red; color:red; padding:5px 10px; border-radius:5px; cursor:pointer;">Remover</button>
                </form>
            </div>
        </c:forEach>
        <c:if test="${empty equipe.atletas}"><p style="color:#666;">Nenhum atleta na equipe ainda.</p></c:if>
    </div>
</div>

</body>
</html>