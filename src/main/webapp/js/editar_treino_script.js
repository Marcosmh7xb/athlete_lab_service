

    document.addEventListener("DOMContentLoaded", () => {

    const modal = document.getElementById("modal");

    const openBtn = document.getElementById("openModal");

    const cancelBtn = document.getElementById("cancelModal");

    const editButtons = document.querySelectorAll(".edit-btn");

    // INPUTS
    const acaoInput = document.getElementById("acaoInput");

    const idExercicioInput = document.getElementById("idExercicio");

    const nomeInput = document.getElementById("nomeInput");

    const seriesInput = document.getElementById("seriesInput");

    const repeticoesInput = document.getElementById("repeticoesInput");

    const tempoInput = document.getElementById("tempoInput");

    const obsInput = document.getElementById("obsInput");

    const modalTitle = document.getElementById("modalTitle");

    const submitBtn = document.getElementById("submitBtn");

    // ABRIR MODAL NOVO
    openBtn.addEventListener("click", () => {

    modal.style.display = "flex";

    modalTitle.innerText = "Adicionar Exercício";

    submitBtn.innerText = "Salvar";

    acaoInput.value = "salvar";

    idExercicioInput.value = "";

    nomeInput.value = "";

    seriesInput.value = "1";

    repeticoesInput.value = "10";

    tempoInput.value = "0";

    obsInput.value = "";

});

    // FECHAR
    cancelBtn.addEventListener("click", () => {

    modal.style.display = "none";

});

    // CLIQUE FORA
    modal.addEventListener("click", (e) => {

    if (e.target.id === "modal") {

    modal.style.display = "none";

}

});

    // EDITAR
    editButtons.forEach(btn => {

    btn.addEventListener("click", () => {

    modal.style.display = "flex";

    modalTitle.innerText = "Editar Exercício";

    submitBtn.innerText = "Atualizar";

    acaoInput.value = "atualizar";

    idExercicioInput.value = btn.dataset.id;

    nomeInput.value = btn.dataset.nome;

    seriesInput.value = btn.dataset.series;

    repeticoesInput.value = btn.dataset.repeticoes;

    tempoInput.value = btn.dataset.tempo;

    obsInput.value = btn.dataset.obs;

});

});

});

