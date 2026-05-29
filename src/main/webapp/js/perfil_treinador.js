document.addEventListener("DOMContentLoaded", function () {

    // TROCA DE ABAS
    const menuItems = document.querySelectorAll(".menu-item");
    const tabs = document.querySelectorAll(".tab");

    menuItems.forEach(item => {
        item.addEventListener("click", () => {

            menuItems.forEach(i => i.classList.remove("active"));
            tabs.forEach(t => t.classList.remove("active"));

            item.classList.add("active");

            const tab = document.getElementById(item.dataset.tab);

            if (tab) {
                tab.classList.add("active");
            }
        });
    });

    // ABRIR / FECHAR TREINO
    document.querySelectorAll(".treino-card").forEach(card => {
        const header = card.querySelector(".treino-header");

        if (header) {
            header.addEventListener("click", () => {
                card.classList.toggle("active");
            });
        }
    });

    // FILTROS
    const filterButtons = document.querySelectorAll(".filter-btn");
    const cards = document.querySelectorAll(".treino-card");

    filterButtons.forEach(btn => {
        btn.addEventListener("click", () => {

            btn.classList.toggle("active");

            const activeFilters = Array.from(
                document.querySelectorAll(".filter-btn.active")
            ).map(btn => btn.dataset.filter);

            cards.forEach(card => {
                const categoria = card.dataset.categoria;

                if (activeFilters.length === 0) {
                    card.style.display = "block";
                } else {
                    card.style.display =
                        activeFilters.includes(categoria)
                            ? "block"
                            : "none";
                }
            });
        });
    });

});