<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <title>${equipe.nome} | Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <style>
        body { background: #000; font-family: "Inter", sans-serif; color: white; padding: 40px; }
        .container { max-width: 900px; margin: 0 auto; }

        /* Link de Voltar */
        .btn-voltar { color: #666; text-decoration: none; font-size: 14px; transition: 0.3s; display: inline-block; margin-bottom: 20px; }
        .btn-voltar:hover { color: red; }

        /* Cabeçalho da Equipe */
        .header-equipe { margin-bottom: 40px; }
        .header-equipe .tag { color: red; font-weight: bold; font-size: 13px; text-transform: uppercase; letter-spacing: 1px; }
        .header-equipe h1 { font-size: 38px; margin: 10px 0; }
        .header-equipe p { color: #888; font-size: 16px; max-width: 600px; line-height: 1.6; }

        /* Cards */
        .card { background: #0f0f0f; border: 1px solid #222; border-radius: 15px; padding: 30px; margin-bottom: 25px; }
        .section-title { color: red; font-size: 11px; text-transform: uppercase; margin-bottom: 20px; border-bottom: 1px solid #222; padding-bottom: 8px; font-weight: 800; }

        /* Criador / Treinador */
        .perfil-treinador { display: flex; align-items: center; gap: 20px; background: #161616; padding: 20px; border-radius: 12px; border: 1px solid #222; }
        .perfil-treinador img { width: 65px; height: 65px; border-radius: 50%; border: 2px solid red; object-fit: cover; }
        .perfil-treinador h3 { font-size: 18px; margin-bottom: 4px; }
        .perfil-treinador p { color: #666; font-size: 14px; }

        /* Grade de Membros */
        .membros-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(180px, 1fr)); gap: 15px; }
        .membro-card { background: #161616; padding: 20px; border-radius: 12px; text-align: center; border: 1px solid #222; transition: 0.3s; }
        .membro-card:hover { border-color: red; transform: translateY(-5px); }
        .membro-card img { width: 55px; height: 55px; border-radius: 50%; margin-bottom: 12px; border: 1px solid #333; object-fit: cover; }
        .membro-card p { font-size: 14px; font-weight: 600; }
        .membro-card span { font-size: 11px; color: #555; text-transform: uppercase; display: block; margin-top: 4px; }
    </style>
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