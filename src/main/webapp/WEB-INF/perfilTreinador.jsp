<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <title>Perfil Treinador - Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #000; font-family: "Inter", sans-serif; color: white; overflow-x: hidden; }
        .layout { display: flex; height: 100vh; }
        .sidebar { width: 80px; background: #0a0a0a; border-right: 1px solid #222; display: flex; flex-direction: column; align-items: center; justify-content: center; position: fixed; height: 100vh; z-index: 100; }
        .menu { display: flex; flex-direction: column; gap: 20px; }
        .menu-item { width: 65px; height: 65px; display: flex; align-items: center; justify-content: center; border-radius: 14px; cursor: pointer; transition: 0.3s; }
        .menu-item img { width: 28px; filter: brightness(0) invert(1); }
        .menu-item.active { background: #111; box-shadow: 0 0 10px rgba(255, 0, 0, 0.3); }
        .menu-item.active img { filter: brightness(0) saturate(100%) invert(21%) sepia(96%) saturate(7470%) hue-rotate(358deg); }
        .content { flex: 1; padding: 40px; margin-left: 80px; }
        .tab { display: none; }
        .tab.active { display: block; }
        .card { background: #0f0f0f; border-radius: 12px; padding: 30px; border: 1px solid #222; margin-bottom: 20px; }
        .info-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
        .info-box { background: #1a1a1a; border: 1px solid #333; padding: 15px; border-radius: 8px; }
        .info-box label { font-size: 11px; opacity: 0.5; text-transform: uppercase; }
        .btn { background: red; padding: 10px 20px; border-radius: 20px; color: white; cursor: pointer; text-decoration: none; font-weight: bold; border: none; }
        .bloqueio-total { position: fixed; inset: 0; background: rgba(0,0,0,0.95); z-index: 9999; display: flex; align-items: center; justify-content: center; backdrop-filter: blur(5px); }
    </style>
</head>
<body>

    <c:if test="${empty perfil.perfilTreinador}">
        <div class="bloqueio-total">
            <div class="card" style="width: 420px; border: 1px solid red; text-align: center;">
                <h2 style="margin-bottom: 15px;">Perfil Incompleto! ⚠️</h2>
                <p style="color: #888; margin-bottom: 25px;">Complete seu cadastro de treinador para liberar o painel.</p>
                <a href="${pageContext.request.contextPath}/editar-perfil" class="btn">Completar Agora</a>
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
                        <div style="display:flex; align-items:center; gap:15px;">
                            <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty perfil.foto ? perfil.foto : 'Customer.png'}" style="width:65px; height:65px; border-radius:50%; border:2px solid red; object-fit:cover;">
                            <div><h2>${perfil.nome}</h2><span style="color:red; font-weight:bold;">TREINADOR</span></div>
                        </div>
                        <a href="${pageContext.request.contextPath}/editar-perfil" class="btn">Editar Perfil</a>
                    </div>
                    <div class="info-grid">
                        <div class="info-box"><label>E-mail</label><p>${perfil.email}</p></div>
                        <div class="info-box"><label>Cidade/UF</label><p>${not empty perfil.cidadeUF ? perfil.cidadeUF : 'Não informado'}</p></div>
                        <div class="info-box"><label>Data de Nascimento</label><p>${perfil.dataNascimento}</p></div>
                        <div class="info-box"><label>Telefone</label><p>${perfil.telefone}</p></div>
                        <div class="info-box"><label>Modalidade</label><p>${perfil.perfilTreinador.modalidade}</p></div>
                        <div class="info-box"><label>Nível de Experiência</label><p>${perfil.perfilTreinador.nivelExperiencia}</p></div>
                        <div class="info-box"><label>Ambiente</label><p>${perfil.perfilTreinador.ambiente}</p></div>
                        <div class="info-box"><label>Sexo Atendido</label><p>${perfil.perfilTreinador.sexo}</p></div>
                        <div class="info-box" style="grid-column: span 2;"><label>Metodologia / Bio</label><p>${perfil.perfilTreinador.objetivo}</p></div>
                        <div class="info-box" style="grid-column: span 2;"><label>Restrições / Notas</label><p>${not empty perfil.perfilTreinador.restricaoFisica ? perfil.perfilTreinador.restricaoFisica : 'Nenhuma'}</p></div>
                    </div>
                </div>
            </section>

            <section class="tab" id="aba-treinos">
                <div class="card"><h2>Gestão de Treinos</h2><p>Controle de planilhas de atletas.</p></div>
            </section>

            <section class="tab" id="aba-equipes">
                <div class="card">
                    <h2>Minhas Equipes</h2>
                    <form action="${pageContext.request.contextPath}/equipe" method="post" style="background:#1a1a1a; padding:25px; border-radius:12px; border:1px solid #333; margin: 20px 0;">
                        <input type="hidden" name="acao" value="criar">
                        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 15px; margin-bottom: 15px;">
                            <input type="text" name="nome" placeholder="Nome da Equipe" required style="width:100%; padding:12px; background:#000; border:1px solid #333; color:white; border-radius:8px;">
                            <input type="text" name="esporte" placeholder="Esporte" required style="width:100%; padding:12px; background:#000; border:1px solid #333; color:white; border-radius:8px;">
                        </div>
                        <input type="text" name="descricao" placeholder="Descrição da equipe" style="width:100%; padding:12px; background:#000; border:1px solid #333; color:white; border-radius:8px; margin-bottom:15px;">
                        <button type="submit" class="btn" style="width:100%;">CRIAR EQUIPE</button>
                    </form>
                    <div class="info-grid">
                        <c:forEach var="equipe" items="${minhasEquipes}">
                            <div class="info-box">
                                <label style="color:red;">${equipe.esporte}</label>
                                <p><strong>${equipe.nome}</strong></p>
                                <p style="font-size:12px; color:#888;">${equipe.descricao}</p>
                                <a href="${pageContext.request.contextPath}/equipe?acao=gerenciar&id=${equipe.idEquipe}" style="color:red; font-size:12px; display:block; margin-top:10px;">Gerenciar &rarr;</a>
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