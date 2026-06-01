

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

    window.fecharChoice = function(){

    modalChoice.classList.remove("active");
}

    window.fecharForm = function(){

    modalForm.classList.remove("active");
}

    /* ===================== CRIAR ===================== */

    window.abrirForm = function(tipo){

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

