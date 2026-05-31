// =========================
// TOAST
// =========================

function mostrarToast(msg, tipo = "sucesso") {

    const toast = document.getElementById("toast");
    toast.textContent = msg;
    toast.className = `toast ${tipo} show`;

    setTimeout(() => {
        toast.classList.remove("show");
    }, 3000);
}

// =========================
// MODAL CRIAR
// =========================

function abrirModalCriar() {
    document.getElementById("modalCriar").classList.add("active");
}

function fecharModalCriar() {
    document.getElementById("modalCriar").classList.remove("active");
}

// =========================
// MODAL CONFIRMAR EXCLUSÃO
// =========================

function confirmarExcluir(idTreino) {
    document.getElementById("idTreinoExcluir").value = idTreino;
    document.getElementById("modalConfirmar").classList.add("active");
}

function fecharConfirmar() {
    document.getElementById("modalConfirmar").classList.remove("active");
}

function executarExcluir() {
    document.getElementById("formExcluir").submit();
}

// =========================
// FECHAR MODAL EDITAR
// =========================

function fecharTreino() {
    document.getElementById("modalTreino").classList.remove("active");
}

// =========================
// ABRIR TREINO (EDITAR)
// =========================

async function abrirTreino(idTreino) {

    try {

        const response = await fetch(
            `${window.location.origin}/athletelab/api/treino?id=${idTreino}`
        );

        if (!response.ok) {
            throw new Error("Erro ao carregar treino");
        }

        const treino = await response.json();

        document.getElementById("editIdTreino").value = treino.idTreino;
        document.getElementById("editNome").value = treino.nome;
        document.getElementById("editCategoria").value = treino.categoria;
        document.getElementById("editStatus").value = treino.status;

        let html = "";

        if (treino.exercicios && treino.exercicios.length > 0) {

            treino.exercicios.forEach(ex => {

                html += `
                    <div class="exercicio-card">
                        <strong>${ex.nome}</strong>
                        <p>Séries: ${ex.series}</p>
                        <p>Repetições: ${ex.repeticoes}</p>
                    </div>
                `;
            });

        } else {

            html = `
                <div class="exercicio-card">
                    <p>Nenhum exercício cadastrado.</p>
                </div>
            `;
        }

        document.getElementById("listaExercicios").innerHTML = html;

        document.getElementById("modalTreino")
            .classList.add("active");

    } catch (e) {

        console.error(e);

        mostrarToast(
            "Erro ao abrir treino.",
            "erro"
        );
    }
}

// =========================
// SALVAR TREINO
// =========================

async function salvarTreino() {

    try {

        const params = new URLSearchParams();

        params.append(
            "idTreino",
            document.getElementById("editIdTreino").value
        );

        params.append(
            "nome",
            document.getElementById("editNome").value
        );

        params.append(
            "categoria",
            document.getElementById("editCategoria").value
        );

        params.append(
            "status",
            document.getElementById("editStatus").value
        );

        const resp = await fetch(
            `${window.location.origin}/athletelab/api/treino`,
            {
                method: "POST",
                headers: {
                    "Content-Type":
                        "application/x-www-form-urlencoded"
                },
                body: params.toString(),
                credentials: "same-origin"
            }
        );

        if (!resp.ok) {

            const erro = await resp.text();

            console.error("ERRO SERVIDOR:", erro);

            mostrarToast(
                "Erro ao salvar treino.",
                "erro"
            );

            return;
        }

        fecharTreino();

        mostrarToast(
            "Treino atualizado com sucesso!"
        );

        setTimeout(() => {

            window.location.href =
                `${window.location.origin}/athletelab/admin/treinos`;

        }, 1500);

    } catch (e) {

        console.error(e);

        mostrarToast(
            "Erro ao salvar treino.",
            "erro"
        );
    }
}
// =========================
// EXPANDIR TREINO
// =========================

function toggleTreino(header) {

    const card = header.closest(".treino-card");

    card.classList.toggle("active");
}