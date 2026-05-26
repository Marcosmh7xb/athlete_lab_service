<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <title>Meu Perfil Atleta | Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <style>
        * { margin:0; padding:0; box-sizing:border-box; }
        body { background:#000; font-family:"Inter", sans-serif; color:white; overflow-x:hidden; }
        .layout { display:flex; min-height:100vh; }
        .sidebar { width:80px; background:#0a0a0a; border-right:1px solid #222; display:flex; flex-direction:column; align-items:center; justify-content:center; position:fixed; height:100vh; z-index:100; }
        .menu { display:flex; flex-direction:column; gap:20px; }
        .menu-item { width:60px; height:60px; display:flex; align-items:center; justify-content:center; border-radius:12px; cursor:pointer; transition:0.3s; }
        .menu-item:hover { background:#111; }
        .menu-item img { width:26px; filter:brightness(0) invert(1); }
        .menu-item.active { background:#111; box-shadow:0 0 10px rgba(255,0,0,0.3); }
        .menu-item.active img { filter: invert(21%) sepia(96%) saturate(7470%) hue-rotate(358deg); }
        .content { flex:1; padding:40px; margin-left:80px; }
        .tab { display:none; }
        .tab.active { display:block; }
        .card { background:#0f0f0f; border-radius:15px; padding:35px; border:1px solid #222; margin-bottom: 20px; }
        .info-grid { display:grid; grid-template-columns: 1fr 1fr; gap:20px; }
        .section-title { color:red; font-size:11px; text-transform:uppercase; border-bottom:1px solid #222; padding-bottom:5px; margin-bottom:15px; font-weight:bold; }
        .info-box { background:#161616; border:1px solid #222; padding:15px; border-radius:10px; }
        .info-box label { font-size:10px; color:#666; text-transform:uppercase; display:block; margin-bottom:5px; }
        .info-box p { font-size:14px; font-weight:500; }
        .full-width { grid-column: span 2; }
        .btn-edit { background:red; color:white; padding:10px 20px; border-radius:20px; text-decoration:none; font-weight:bold; font-size:13px; transition: 0.3s; display: inline-block; }
        .bloqueio-total { position:fixed; inset:0; background:rgba(0,0,0,0.95); z-index:9999; display:flex; align-items:center; justify-content:center; text-align:center; backdrop-filter: blur(5px); }
    </style>
</head>
<body>

    <c:if test="${empty perfil.perfilAtleta}">
        <div class="bloqueio-total">
            <div class="card" style="width:420px; border: 1px solid red;">
                <h2 style="margin-bottom:15px;">Perfil Incompleto! ⚠️</h2>
                <p style="color:#888; margin-bottom:25px;">Complete seus dados técnicos para liberar o painel.</p>
                <a href="${pageContext.request.contextPath}/editar-perfil-atleta" class="btn-edit">Completar Agora</a>
            </div>
        </div>
    </c:if>

    <div class="layout">
        <aside class="sidebar">
            <div class="menu">
                <div class="menu-item active" data-tab="aba-perfil"><img src="${pageContext.request.contextPath}/banco_imagens/icones/Customer.png"></div>
                <div class="menu-item" data-tab="aba-equipes"><img src="${pageContext.request.contextPath}/banco_imagens/icones/Dashboard Layout.png"></div>
                <div class="menu-item" data-tab="aba-treinos"><img src="${pageContext.request.contextPath}/banco_imagens/icones/Trainers.png"></div>
                <div class="menu-item" data-tab="aba-config"><img src="${pageContext.request.contextPath}/banco_imagens/icones/Settings.png"></div>
            </div>
        </aside>

        <main class="content">
            <section class="tab active" id="aba-perfil">
                <div class="card">
                    <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:30px;">
                        <div style="display:flex; align-items:center; gap:20px;">
                            <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty perfil.foto ? perfil.foto : 'Customer.png'}" style="width:70px; height:70px; border-radius:50%; border:2px solid red; object-fit:cover;">
                            <div><h2>${perfil.nome}</h2><p style="color:red; font-size:12px; font-weight:bold;">ATLETA</p></div>
                        </div>
                        <a href="${pageContext.request.contextPath}/editar-perfil-atleta" class="btn-edit">Editar Perfil</a>
                    </div>

                    <div class="info-grid">
                        <div>
                            <h4 class="section-title">Dados de Cadastro</h4>
                            <div class="info-box"><label>E-mail</label><p>${perfil.email}</p></div>
                            <div class="info-box" style="margin-top:10px;"><label>Telefone</label><p>${not empty perfil.telefone ? perfil.telefone : 'Não informado'}</p></div>
                            <div class="info-box" style="margin-top:10px;"><label>Cidade/UF</label><p>${not empty perfil.cidadeUF ? perfil.cidadeUF : 'Não informado'}</p></div>
                        </div>
                        <div>
                            <h4 class="section-title">Informações Técnicas</h4>
                            <div class="info-box"><label>Modalidade</label><p>${perfil.perfilAtleta.modalidade}</p></div>
                            <div class="info-box" style="margin-top:10px;"><label>Nível</label><p>${perfil.perfilAtleta.nivelExperiencia}</p></div>
                            <div class="info-box" style="margin-top:10px;"><label>Dias de Treino</label><p>${perfil.perfilAtleta.diasSemana}</p></div>
                        </div>
                        <div class="info-box"><label>Peso</label><p>${perfil.perfilAtleta.peso} kg</p></div>
                        <div class="info-box"><label>Altura</label><p>${perfil.perfilAtleta.altura} m</p></div>
                        <div class="full-width"><div class="info-box"><label>Objetivo Principal</label><p>${perfil.perfilAtleta.objetivo}</p></div></div>
                        <div class="full-width"><div class="info-box"><label>Restrições / Observações</label><p>${not empty perfil.perfilAtleta.restricaoFisica ? perfil.perfilAtleta.restricaoFisica : 'Nenhuma'}</p></div></div>
                    </div>
                </div>
            </section>

            <section class="tab" id="aba-treinos">
                <div class="card"><h2>Meus Treinos</h2><p>Agenda e planilhas de treino personalizados.</p></div>
            </section>

            <section class="tab" id="aba-equipes">
                <div class="card">
                    <h2 style="margin-bottom:25px;">Minhas Equipes</h2>
                    <div class="info-grid">
                        <c:forEach var="equipe" items="${minhasEquipes}">
                            <div class="info-box">
                                <span style="color:red; font-size:10px; font-weight:bold;">${equipe.esporte}</span>
                                <p style="font-size:18px; font-weight:bold;">${equipe.nome}</p>
                                <p style="font-size:12px; color:#888; margin-top:5px;">${equipe.descricao}</p>
                                <a href="${pageContext.request.contextPath}/equipe?acao=ver&id=${equipe.idEquipe}" style="color:red; text-decoration:none; font-weight:bold; display:block; margin-top:15px;">Ver Detalhes &rarr;</a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </main>
    </div>
    <script>
        const menuItems = document.querySelectorAll(".menu-item");
        const tabs = document.querySelectorAll(".tab");
        menuItems.forEach(item => {
            item.addEventListener("click", () => {
                const target = item.getAttribute("data-tab");
                menuItems.forEach(i => i.classList.remove("active"));
                tabs.forEach(t => t.classList.remove("active"));
                item.classList.add("active");
                document.getElementById(target).classList.add("active");
            });
        });
    </script>
</body>
</html>