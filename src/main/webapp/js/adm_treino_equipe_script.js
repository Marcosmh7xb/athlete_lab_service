// =========================
// TROCAR ABAS
// =========================

function showSection(id, e){

    document.querySelectorAll(".tab-section")
        .forEach(s => s.classList.remove("active"));

    document.querySelectorAll(".tab-btn")
        .forEach(b => b.classList.remove("active"));

    document.getElementById(id)
        .classList.add("active");

    e.target.classList.add("active");
}

// =========================
// EQUIPE
// =========================

function openEquipeModal(){

    alert("Modal equipe depois.");
}

// =========================
// FECHAR MODAL
// =========================

function fecharTreino(){

    document.getElementById("modalTreino")
        .classList.remove("active");
}

// =========================
// ABRIR TREINO
// =========================

async function abrirTreino(idTreino){

    try {

        const response = await fetch(

            `${window.location.origin}/athletelab/api/treino?id=${idTreino}`
        );

        const treino = await response.json();

        document.getElementById("editIdTreino")
            .value = treino.idTreino;

        document.getElementById("editNome")
            .value = treino.nome;

        document.getElementById("editCategoria")
            .value = treino.categoria;

        document.getElementById("editStatus")
            .value = treino.status;

        // =========================
        // EXERCICIOS
        // =========================

        let html = "";

        treino.exercicios.forEach(ex => {

            html += `

                    <div class="exercicio-card">

                        <strong>${ex.nome}</strong>

                        <p>
                            Séries: ${ex.series}
                        </p>

                        <p>
                            Repetições: ${ex.repeticoes}
                        </p>

                    </div>

                `;
        });

        document.getElementById("listaExercicios")
            .innerHTML = html;

        document.getElementById("modalTreino")
            .classList.add("active");

    } catch (e){

        console.log(e);

        alert("Erro ao abrir treino.");
    }
}

// =========================
// SALVAR TREINO
// =========================

async function salvarTreino(){

    const formData = new FormData();

    formData.append(
        "idTreino",
        document.getElementById("editIdTreino").value
    );

    formData.append(
        "nome",
        document.getElementById("editNome").value
    );

    formData.append(
        "categoria",
        document.getElementById("editCategoria").value
    );

    formData.append(
        "status",
        document.getElementById("editStatus").value
    );

    try {

        await fetch(

            `${window.location.origin}/athletelab/api/treino`,

            {
                method: "POST",
                body: formData
            }
        );

        alert("Treino atualizado.");

        location.reload();

    } catch (e){

        console.log(e);

        alert("Erro ao salvar.");
    }
}