<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <title>Perfil Atleta | Athlete Lab</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #000; font-family: "Inter", sans-serif; color: white; overflow-x: hidden; }
        .layout { display: flex; height: 100vh; }

        /* SIDEBAR */
        .sidebar { width: 80px; background: #0a0a0a; border-right: 1px solid #222; display: flex; flex-direction: column; align-items: center; justify-content: center; position: fixed; height: 100vh; z-index: 100; }
        .menu { display: flex; flex-direction: column; gap: 20px; }
        .menu-item { width: 60px; height: 60px; display: flex; align-items: center; justify-content: center; border-radius: 12px; cursor: pointer; transition: 0.3s; }
        .menu-item img { width: 26px; filter: brightness(0) invert(1); }
        .menu-item.active { background: #111; box-shadow: 0 0 10px rgba(255, 0, 0, 0.3); }
        .menu-item.active img { filter: invert(21%) sepia(96%) saturate(7470%) hue-rotate(358deg); }

        /* CONTEÚDO */
        .content { flex: 1; padding: 40px; margin-left: 80px; }
        .top-logo { margin-bottom: 30px; }
        .top-logo img { width: 150px; }

        /* CONTROLE DE ABAS */
        .tab { display: none; }
        .tab.active { display: block; }

        /* CARD */
        .card { background: #0f0f0f; border-radius: 15px; padding: 35px; border: 1px solid #222; }
        .card-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 40px; }
        .user-info { display: flex; gap: 20px; align-items: center; }

        /* AVATAR */
        .avatar-container { position: relative; width: 75px; height: 75px; }
        .avatar-container img { width: 100%; height: 100%; border-radius: 50%; border: 2px solid red; object-fit: cover; }
        .avatar-overlay { position: absolute; inset: 0; background: rgba(0,0,0,0.7); color: white; font-size: 9px; display: flex; align-items: center; justify-content: center; border-radius: 50%; opacity: 0; cursor: pointer; transition: 0.3s; text-align: center; text-transform: uppercase; font-weight: bold; }
        .avatar-container:hover .avatar-overlay { opacity: 1; }

        .btn-edit { background: red; color: white; padding: 10px 25px; border-radius: 25px; text-decoration: none; font-weight: bold; font-size: 14px; }
        .info-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 25px; }
        .section-title { color: red; font-size: 11px; text-transform: uppercase; border-bottom: 1px solid #222; padding-bottom: 5px; margin-bottom: 15px; }
        .info-box { background: #161616; border: 1px solid #222; padding: 15px; border-radius: 10px; margin-bottom: 10px; }
        .info-box label { font-size: 10px; color: #666; text-transform: uppercase; display: block; margin-bottom: 5px; }
        .info-box p { font-size: 14px; font-weight: 500; }
        .full-width { grid-column: span 2; }
    </style>
</head>
<body>

    <div class="layout">
        <aside class="sidebar">
            <div class="menu">
                <div class="menu-item active" data-tab="aba-perfil">
                    <img src="${pageContext.request.contextPath}/banco_imagens/icones/Customer.png">
                </div>
                <div class="menu-item" data-tab="aba-dashboard">
                    <img src="${pageContext.request.contextPath}/banco_imagens/icones/Dashboard Layout.png">
                </div>
                <div class="menu-item" data-tab="aba-treinos">
                    <img src="${pageContext.request.contextPath}/banco_imagens/icones/Trainers.png">
                </div>
                <div class="menu-item" data-tab="aba-config">
                    <img src="${pageContext.request.contextPath}/banco_imagens/icones/Settings.png">
                </div>
            </div>
        </aside>

        <main class="content">
            <div class="top-logo">
                <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png">
            </div>

            <section class="tab active" id="aba-perfil">
                <div class="card">
                    <div class="card-header">
                        <div class="user-info">
                            <div class="avatar-container">
                                <form action="${pageContext.request.contextPath}/editar-perfil-atleta" method="post" enctype="multipart/form-data">
                                    <input type="file" name="foto" id="input-foto-atleta" style="display:none;" onchange="this.form.submit()">
                                    <label for="input-foto-atleta" class="avatar-overlay">Trocar Foto</label>
                                </form>
                                <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty perfil.foto ? perfil.foto : 'Customer.png'}">
                            </div>
                            <div>
                                <h2>${perfil.nome}</h2>
                                <span style="color:red; font-size:12px; font-weight:bold;">ATLETA</span>
                            </div>
                        </div>
                        <a href="${pageContext.request.contextPath}/editar-perfil-atleta" class="btn-edit">Editar Perfil</a>
                    </div>

                    <div class="info-grid">
                        <div>
                            <h4 class="section-title">Dados de Cadastro</h4>
                            <div class="info-box"><label>E-mail</label><p>${perfil.email}</p></div>
                            <div class="info-box"><label>Cidade/UF</label><p>${not empty perfil.cidadeUF ? perfil.cidadeUF : 'Não informado'}</p></div>
                            <div class="info-box"><label>Telefone</label><p>${not empty perfil.telefone ? perfil.telefone : 'Não informado'}</p></div>
                        </div>
                        <div>
                            <h4 class="section-title">Dados de Treino</h4>
                            <div class="info-box"><label>Modalidade</label><p>${perfil.perfilAtleta.modalidade}</p></div>
                            <div class="info-box"><label>Peso / Altura</label><p>${perfil.perfilAtleta.peso} kg / ${perfil.perfilAtleta.altura} m</p></div>
                            <div class="info-box"><label>Objetivo</label><p>${perfil.perfilAtleta.objetivo}</p></div>
                        </div>
                        <div class="full-width">
                            <h4 class="section-title">Restrições e Notas</h4>
                            <div class="info-box"><p>${not empty perfil.perfilAtleta.restricaoFisica ? perfil.perfilAtleta.restricaoFisica : 'Nenhuma.'}</p></div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="tab" id="aba-dashboard">
                <div class="card"><h2>Dashboard</h2><p>Estatísticas em breve...</p></div>
            </section>
            <section class="tab" id="aba-treinos">
                <div class="card"><h2>Meus Treinos</h2><p>Treinos agendados aqui.</p></div>
            </section>
            <section class="tab" id="aba-config">
                <div class="card"><h2>Configurações</h2><p>Ajustes da conta.</p></div>
            </section>

        </main>
    </div>

    <script>
        const menuItems = document.querySelectorAll(".menu-item");
        const tabs = document.querySelectorAll(".tab");

        menuItems.forEach(item => {
            item.addEventListener("click", () => {
                const target = item.getAttribute("data-tab");

                // Remove active de todos
                menuItems.forEach(i => i.classList.remove("active"));
                tabs.forEach(t => t.classList.remove("active"));

                // Adiciona active no clicado
                item.classList.add("active");
                const targetSection = document.getElementById(target);
                if (targetSection) {
                    targetSection.classList.add("active");
                }
            });
        });
    </script>
</body>
</html>