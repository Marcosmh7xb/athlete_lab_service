document.addEventListener("DOMContentLoaded", () => {

    // =========================
    // EXPANDIR / COLAPSAR TREINOS
    // =========================
    document.querySelectorAll(".treino-card").forEach(card => {
        const header = card.querySelector(".treino-header");
        header.addEventListener("click", () => {
            card.classList.toggle("active");
        });
    });

    // =========================
    // FILTRO DE TREINOS
    // =========================
    const filterButtons = document.querySelectorAll(".filter-btn");
    const cards = document.querySelectorAll(".treino-card");

    filterButtons.forEach(btn => {
        btn.addEventListener("click", () => {
            btn.classList.toggle("active");

            const activeFilters = Array.from(document.querySelectorAll(".filter-btn.active"))
                .map(btn => btn.dataset.filter);

            cards.forEach(card => {
                const categoria = card.dataset.categoria;

                if (activeFilters.length === 0) {
                    card.style.display = "block";
                    return;
                }

                card.style.display = activeFilters.includes(categoria) ? "block" : "none";
            });
        });
    });

});