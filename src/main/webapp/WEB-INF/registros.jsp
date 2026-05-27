<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.athletelab.usuario.UsuarioModel" %>

<%
    List<UsuarioModel> usuarios =
            (List<UsuarioModel>) request.getAttribute("usuarios");

    if (usuarios == null) {
        usuarios = new java.util.ArrayList<>();
    }
%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>

    <meta charset="UTF-8">

    <title>Painel Admin</title>

    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/registros_style.css">

</head>

<body>

<div class="container">

    <!-- HEADER -->

    <div class="header">

        <img src="${pageContext.request.contextPath}/banco_imagens/imagens/Athlete logo white.png"
             class="logo">

        <div class="header-right">

            <h2>Lista de registros</h2>

            <!-- BOTÃO PAINEL -->

           <a href="${pageContext.request.contextPath}/admin/painel"  class="painel-btn">
               Painel Admin
           </a>

            <!-- NOVO USUÁRIO -->

            <button class="novo-btn"
                    type="button">

                + Novo Usuário

            </button>

        </div>

    </div>

    <!-- BUSCA -->

    <form class="search-bar"
          id="searchForm">

        <input type="text"
               id="searchNome"
               placeholder="Nome">

        <input type="number"
               id="searchId"
               placeholder="ID">

        <button type="submit">

            Buscar

        </button>

    </form>

    <!-- ABAS -->

    <div class="tabs-container">

        <button class="tab-btn"
                data-tipo="ATLETA">

            Atletas

        </button>

        <button class="tab-btn"
                data-tipo="TREINADOR">

            Treinadores

        </button>

        <button class="tab-btn"
                data-tipo="ADMIN">

            Administradores

        </button>

    </div>

    <!-- LABELS -->

    <div class="labels">

        <span>Tipo</span>
        <span>Nome</span>
        <span>Data Nasc.</span>
        <span>ID</span>
        <span>Email</span>
        <span>Cidade</span>
        <span>Ações</span>

    </div>

    <!-- USUÁRIOS -->

    <%
        for(UsuarioModel usuario : usuarios){

            String nome =
                    usuario.getNome() != null
                            ? usuario.getNome()
                            : "";

            String email =
                    usuario.getEmail() != null
                            ? usuario.getEmail()
                            : "";

            String telefone =
                    usuario.getTelefone() != null
                            ? usuario.getTelefone()
                            : "";

            String cidade =
                    usuario.getCidadeUF() != null
                            ? usuario.getCidadeUF()
                            : "";

            String tipo =
                    usuario.getTipoUsuario() != null
                            ? usuario.getTipoUsuario()
                            : "";

            String nascimento = "";

            if (usuario.getDataNascimento() != null) {

                nascimento =
                        usuario.getDataNascimento().toString();

                if (nascimento.length() >= 10) {

                    nascimento =
                            nascimento.substring(0, 10);
                }
            }
    %>

    <div class="user-row"

         data-tipo="<%= tipo %>"
         data-nome="<%= nome %>"
         data-id="<%= usuario.getIdUsuario() %>">

        <div class="user-info">

            <span><%= tipo %></span>

            <span><%= nome %></span>

            <span><%= nascimento %></span>

            <span><%= usuario.getIdUsuario() %></span>

            <span><%= email %></span>

            <span><%= cidade %></span>

        </div>

        <div class="actions">

            <!-- EDITAR -->

            <button class="btn-editar"
                    type="button"

                    data-id="<%= usuario.getIdUsuario() %>"
                    data-nome="<%= nome %>"
                    data-email="<%= email %>"
                    data-telefone="<%= telefone %>"
                    data-cidade="<%= cidade %>"
                    data-nascimento="<%= nascimento %>"
                    data-tipo="<%= tipo %>">

                Editar

            </button>

            <!-- APAGAR -->

            <a class="btn-deletar"

               href="${pageContext.request.contextPath}/admin/usuario?acao=deletar&id=<%= usuario.getIdUsuario() %>">

                Apagar

            </a>

        </div>

    </div>

    <% } %>

</div>

<!-- MODAL ESCOLHA -->

<div class="modal-overlay"
     id="modalChoice">

    <div class="modal">

        <div class="modal-header">

            <h2>

                Selecionar Tipo de Usuário

            </h2>

            <button class="fechar-modal"
                    onclick="fecharChoice()">

                ×

            </button>

        </div>

        <div class="choice-container">

            <button type="button"
                    class="choice-btn"
                    onclick="abrirForm('ATLETA')">

                Atleta

            </button>

            <button type="button"
                    class="choice-btn"
                    onclick="abrirForm('TREINADOR')">

                Treinador

            </button>

            <button type="button"
                    class="choice-btn"
                    onclick="abrirForm('ADMIN')">

                Administrador

            </button>

        </div>

    </div>

</div>

<!-- MODAL FORM -->

<div class="modal-overlay"
     id="modalForm">

    <div class="modal">

        <div class="modal-header">

            <h2 id="modalTitulo">

                Novo Usuário

            </h2>

            <button class="fechar-modal"
                    onclick="fecharForm()">

                ×

            </button>

        </div>

        <form class="modal-form"

              action="${pageContext.request.contextPath}/admin/usuario"
              method="post">

            <input type="hidden"
                   name="id"
                   id="usuarioId">

            <input type="hidden"
                   name="tipo_usuario"
                   id="tipoUsuarioHidden">

            <div class="form-grid"
                 id="formCampos"></div>

            <button type="submit"
                    class="criar-btn">

                Salvar

            </button>

        </form>

    </div>

</div>

<script>

/* ===================== FILTRO ===================== */

const tabButtons = document.querySelectorAll(".tab-btn");

const userRows = document.querySelectorAll(".user-row");

const searchForm = document.getElementById("searchForm");

const searchNome = document.getElementById("searchNome");

const searchId = document.getElementById("searchId");

let filtroAtual = null;

function aplicarFiltros(){

    const nome = searchNome.value.toLowerCase().trim();

    const id = searchId.value.trim();

    userRows.forEach(row => {

        const rowNome = row.dataset.nome.toLowerCase();

        const rowId = row.dataset.id;

        const rowTipo = row.dataset.tipo;

        let mostrar = true;

        if(filtroAtual && rowTipo !== filtroAtual){

            mostrar = false;
        }

        if(nome && !rowNome.includes(nome)){

            mostrar = false;
        }

        if(id && rowId !== id){

            mostrar = false;
        }

        row.style.display =
                mostrar ? "grid" : "none";

    });

}

/* ===================== ABAS ===================== */

tabButtons.forEach(btn => {

    btn.addEventListener("click", () => {

        const tipo = btn.dataset.tipo;

        if(filtroAtual === tipo){

            filtroAtual = null;

            btn.classList.remove("active");

            aplicarFiltros();

            return;
        }

        tabButtons.forEach(b =>
                b.classList.remove("active"));

        btn.classList.add("active");

        filtroAtual = tipo;

        aplicarFiltros();

    });

});

/* ===================== BUSCA ===================== */

searchForm.addEventListener("submit", function(e){

    e.preventDefault();

    aplicarFiltros();

});

searchNome.addEventListener("input", aplicarFiltros);

searchId.addEventListener("input", aplicarFiltros);

/* ===================== MODAIS ===================== */

const modalChoice =
        document.getElementById("modalChoice");

const modalForm =
        document.getElementById("modalForm");

const formCampos =
        document.getElementById("formCampos");

const tipoHidden =
        document.getElementById("tipoUsuarioHidden");

const usuarioId =
        document.getElementById("usuarioId");

const modalTitulo =
        document.getElementById("modalTitulo");

/* ===================== NOVO USUÁRIO ===================== */

document.querySelector(".novo-btn")
    .addEventListener("click", () => {

        modalChoice.classList.add("active");

});

/* ===================== FECHAR ===================== */

function fecharChoice(){

    modalChoice.classList.remove("active");
}

function fecharForm(){

    modalForm.classList.remove("active");
}

/* ===================== CRIAR ===================== */

function abrirForm(tipo){

    usuarioId.value = "";

    modalTitulo.innerText =
            "Novo Usuário";

    tipoHidden.value = tipo;

    formCampos.innerHTML = `

        <div class="campo-wrapper">
            <label class="campo-label">Nome completo</label>
            <input type="text" name="nome" required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Email</label>
            <input type="email" name="email" required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Senha</label>
            <input type="password" name="senha" required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Telefone</label>
            <input type="text" name="telefone" required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Cidade / UF</label>
            <input type="text" name="cidade_uf" required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Data de nascimento</label>
            <input type="date" name="data_nascimento" required>
        </div>

    `;

    modalChoice.classList.remove("active");

    modalForm.classList.add("active");
}

/* ===================== EDITAR ===================== */

document.addEventListener("click", function (e) {

    const btn =
            e.target.closest(".btn-editar");

    if (!btn) return;

    usuarioId.value =
            btn.dataset.id || "";

    tipoHidden.value =
            btn.dataset.tipo || "";

    modalTitulo.innerText =
            "Editar Usuário";

    formCampos.innerHTML = `

        <div class="campo-wrapper">
            <label class="campo-label">Nome completo</label>
            <input type="text"
                   name="nome"
                   value="${btn.dataset.nome}"
                   required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Email</label>
            <input type="email"
                   name="email"
                   value="${btn.dataset.email}"
                   required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Nova senha</label>
            <input type="password"
                   name="senha">
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Telefone</label>
            <input type="text"
                   name="telefone"
                   value="${btn.dataset.telefone}"
                   required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Cidade / UF</label>
            <input type="text"
                   name="cidade_uf"
                   value="${btn.dataset.cidade}"
                   required>
        </div>

        <div class="campo-wrapper">
            <label class="campo-label">Data de nascimento</label>
            <input type="date"
                   name="data_nascimento"
                   value="${btn.dataset.nascimento}"
                   required>
        </div>

    `;

    modalForm.classList.add("active");

});

</script>

</body>

</html>