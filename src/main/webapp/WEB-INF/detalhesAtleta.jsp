<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <title>Ficha do Atleta | Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #000; font-family: "Inter", sans-serif; color: white; padding: 40px; }
        .container { max-width: 800px; margin: 0 auto; }
        .card { background: #0f0f0f; border-radius: 12px; padding: 35px; border: 1px solid #222; }
        .header-perfil { display: flex; align-items: center; gap: 20px; margin-bottom: 30px; }
        .header-perfil img { width: 75px; height: 75px; border-radius: 50%; border: 2px solid red; object-fit: cover; }
        .info-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
        .section-title { color: red; font-size: 11px; text-transform: uppercase; border-bottom: 1px solid #222; padding-bottom: 5px; margin-bottom: 15px; font-weight: bold; }
        .info-box { background: #1a1a1a; border: 1px solid #333; padding: 15px; border-radius: 8px; }
        .info-box label { font-size: 10px; color: #666; text-transform: uppercase; display: block; margin-bottom: 5px; }
        .info-box p { font-size: 14px; font-weight: 500; }
        .full-width { grid-column: span 2; }
        .btn-voltar { color: #888; text-decoration: none; font-size: 14px; display: inline-block; margin-bottom: 20px; transition: 0.3s; }
        .btn-voltar:hover { color: red; }
    </style>
</head>
<body>

<div class="container">
    <a href="javascript:history.back()" class="btn-voltar">&larr; Voltar para a Equipe</a>

    <div class="card">
        <div class="header-perfil">
            <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty atleta.foto ? atleta.foto : 'Customer.png'}" alt="Foto do Atleta">
            <div>
                <h2>${atleta.nome}</h2>
                <span style="color: red; font-size: 12px; font-weight: bold; text-transform: uppercase;">Ficha Técnica do Atleta</span>
            </div>
        </div>

        <div class="info-grid">
            <div>
                <h4 class="section-title">Dados de Contato</h4>
                <div class="info-box"><label>E-mail</label><p>${atleta.email}</p></div>
                <div class="info-box" style="margin-top: 10px;"><label>Telefone</label><p>${not empty atleta.telefone ? atleta.telefone : 'Não informado'}</p></div>
                <div class="info-box" style="margin-top: 10px;"><label>Cidade/UF</label><p>${not empty atleta.cidadeUF ? atleta.cidadeUF : 'Não informado'}</p></div>
            </div>

            <div>
                <h4 class="section-title">Informações de Treino</h4>
                <div class="info-box"><label>Modalidade</label><p>${atleta.perfilAtleta.modalidade}</p></div>
                <div class="info-box" style="margin-top: 10px;"><label>Nível de Experiência</label><p>${atleta.perfilAtleta.nivelExperiencia}</p></div>
                <div class="info-box" style="margin-top: 10px;"><label>Dias Disponíveis</label><p>${atleta.perfilAtleta.diasSemana}</p></div>
            </div>

            <div class="info-box"><label>Peso Atual</label><p>${atleta.perfilAtleta.peso} kg</p></div>
            <div class="info-box"><label>Altura</label><p>${atleta.perfilAtleta.altura} m</p></div>

            <div class="full-width">
                <div class="info-box"><label>Objetivo Principal do Atleta</label><p>${atleta.perfilAtleta.objetivo}</p></div>
            </div>

            <div class="full-width">
                <div class="info-box"><label>Restrições Médicas / Observações Clínicas</label><p>${not empty atleta.perfilAtleta.restricaoFisica ? atleta.perfilAtleta.restricaoFisica : 'Nenhuma restrição informada pelo atleta.'}</p></div>
            </div>
        </div>
    </div>
</div>

</body>
</html>