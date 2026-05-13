<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt_BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil Treinador - Athlete Lab</title>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">

    <style>
        /* O SEU CSS FORNECIDO */
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { background: #000; font-family: "Inter", sans-serif; color: white; overflow-x: hidden; }
        .layout { display: flex; height: 100vh; }
        .sidebar { width: 80px; background: #0a0a0a; border-right: 1px solid #222; display: flex; flex-direction: column; align-items: center; justify-content: center; position: fixed; height: 100vh; }
        .menu { display: flex; flex-direction: column; gap: 20px; }
        .menu-item { width: 65px; height: 65px; display: flex; align-items: center; justify-content: center; border-radius: 14px; cursor: pointer; transition: 0.3s; }
        .menu-item img { width: 28px; filter: brightness(0) invert(1); transition: 0.3s; }
        .menu-item:hover { background: #111; transform: scale(1.05); }
        .menu-item.active { background: #111; box-shadow: 0 0 10px rgba(255, 0, 0, 0.3); }
        .menu-item.active img { filter: brightness(0) saturate(100%) invert(21%) sepia(96%) saturate(7470%) hue-rotate(358deg) brightness(100%) contrast(117%); }
        .content { flex: 1; padding: 40px; margin-left: 80px; }
        .top-logo { margin-bottom: 30px; }
        .top-logo img { width: 160px; }
        .tab { display: none; }
        .tab.active { display: block; }
        .card { background: #0f0f0f; border-radius: 12px; padding: 30px; border: 1px solid #222; }
        .card-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; }
        .user-info { display: flex; gap: 15px; align-items: center; }
        .avatar-container { position: relative; width: 60px; height: 60px; }
        .avatar-container img { width: 100%; height: 100%; border-radius: 50%; border: 2px solid red; object-fit: cover; }
        .user-info h2 { font-size: 18px; }
        .user-info span { color: red; font-size: 13px; }
        .btn { background: red; border: none; padding: 8px 20px; border-radius: 20px; color: white; cursor: pointer; transition: 0.3s; font-weight: bold; text-decoration: none; font-size: 14px; }
        .btn:hover { background: #cc0000; }
        .info-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
        .info-box { background: #1a1a1a; border: 1px solid #333; padding: 15px; border-radius: 8px; }
        .info-box label { font-size: 11px; opacity: 0.5; text-transform: uppercase; letter-spacing: 1px; }
        .info-box p { margin-top: 5px; font-size: 14px; font-weight: 500; }
        .danger { margin-top: 20px; text-align: right; }
        .danger a { color: red; font-size: 12px; text-decoration: none; opacity: 0.7; }
        .danger a:hover { opacity: 1; }

        /* ESTILO DO BLOQUEIO */
        .bloqueio-total { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.98); display: flex; justify-content: center; align-items: center; z-index: 9999; backdrop-filter: blur(10px); }
        .card-alerta { background: #0f0f0f; padding: 40px; border-radius: 20px; border: 1px solid red; text-align: center; max-width: 400px; }
    </style>
</head>

<body>

<div class="layout">

    <c:if test="${empty perfil.perfilTreinador}">
        <div class="bloqueio-total">
            <div class="card-alerta">
                <h2 style="margin-bottom: 15px;">Perfil Incompleto! ⚠️</h2>
                <p style="color: #888; margin-bottom: 25px;">Detectamos que você ainda não configurou suas informações técnicas. Complete seu cadastro para liberar o painel.</p>
                <a href="${pageContext.request.contextPath}/editar-perfil" class="btn">Completar Agora</a>
            </div>
        </div>
    </c:if>

    <aside class="sidebar">
        <div class="menu">
            <div class="menu-item active" data-tab="perfil">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Customer.png" alt="Perfil">
            </div>
            <div class="menu-item" data-tab="dashboard">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Dashboard Layout.png" alt="Dashboard">
            </div>
            <div class="menu-item" data-tab="treinos">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Trainers.png" alt="Treinos">
            </div>
            <div class="menu-item" data-tab="config">
                <img src="${pageContext.request.contextPath}/banco_imagens/icones/Settings.png" alt="Configurações">
            </div>
        </div>
    </aside>

    <main class="content">

        <div class="top-logo">
            <a href="${pageContext.request.contextPath}/home"> <!-- Pega automaticamente o caminho raiz do projeto, ja que não podemos acesaar arquivos que estejão em WEB-INF/ -->
                <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png" alt="Logo" >
            </a>
        </div>

        <section class="tab active" id="perfil">
            <div class="card">
                <div class="card-header">
                    <div class="user-info">
                        <div class="avatar-container">
                          <img src="${pageContext.request.contextPath}/banco_imagens/icones/${not empty perfil.foto ? perfil.foto : 'Customer.png'}" class="foto-circulo" alt="Foto">
                        </div>
                        <div>
                            <h2>${perfil.nome}</h2>
                            <span>Treinador</span>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/editar-perfil">
                        <button class="btn">Editar Perfil</button>
                    </a>
                </div>

                <div class="info-grid">
                    <div class="info-box">
                        <label>E-mail</label>
                        <p>${perfil.email}</p>
                    </div>
                    <div class="info-box">
                        <label>Cidade/UF</label>
                        <p>${not empty perfil.cidadeUF ? perfil.cidadeUF : 'Não informado'}</p>
                    </div>
                    <div class="info-box">
                        <label>Data de Nascimento</label>
                        <p>${not empty perfil.dataNascimento ? perfil.dataNascimento : '00/00/0000'}</p>
                    </div>
                    <div class="info-box">
                        <label>Telefone</label>
                        <p>${not empty perfil.telefone ? perfil.telefone : '(00) 00000-0000'}</p>
                    </div>

                    <div class="info-box">
                        <label>Modalidade</label>
                        <p>${perfil.perfilTreinador.modalidade}</p>
                    </div>
                    <div class="info-box">
                        <label>Nível de Experiência</label>
                        <p>${perfil.perfilTreinador.nivelExperiencia}</p>
                    </div>
                    <div class="info-box">
                        <label>Objetivo</label>
                        <p>${not empty perfil.perfilTreinador.objetivo ? perfil.perfilTreinador.objetivo : 'Não informado'}</p>
                    </div>
                    <div class="info-box">
                        <label>Ambiente</label>
                        <p>${not empty perfil.perfilTreinador.ambiente ? perfil.perfilTreinador.ambiente : 'Não informado'}</p>
                    </div>
                    <div class="info-box">
                        <label>Sexo Atendido</label>
                        <p>${perfil.perfilTreinador.sexo}</p>
                    </div>
                    <div class="info-box">
                        <label>Restrição Física / Notas</label>
                        <p>${not empty perfil.perfilTreinador.restricaoFisica ? perfil.perfilTreinador.restricaoFisica : 'Nenhuma'}</p>
                    </div>
                </div>

                <div class="danger">
                    <a href="${pageContext.request.contextPath}/logout">Sair da Conta</a>
                </div>
            </div>
        </section>

        <section class="tab" id="dashboard">
            <h2 style="opacity: 0.5;">Painel de Estatísticas em breve...</h2>
        </section>
        <section class="tab" id="treinos"></section>
        <section class="tab" id="config"></section>

    </main>

</div>

<script>
    // TROCA DE ABAS
    const menuItems = document.querySelectorAll(".menu-item");
    const tabs = document.querySelectorAll(".tab");

    menuItems.forEach(item => {
        item.addEventListener("click", () => {
            menuItems.forEach(i => i.classList.remove("active"));
            tabs.forEach(t => t.classList.remove("active"));

            item.classList.add("active");
            document.getElementById(item.dataset.tab).classList.add("active");
        });
    });
</script>

</body>
</html>