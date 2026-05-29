
    // =========================
    // TROCA DE ABAS
    // =========================

    const menuItems =
    document.querySelectorAll(".menu-item");

    const tabs =
    document.querySelectorAll(".tab");

    menuItems.forEach(item => {

    item.addEventListener("click", () => {

        const target =
            item.dataset.tab;

        menuItems.forEach(i =>
            i.classList.remove("active")
        );

        tabs.forEach(t =>
            t.classList.remove("active")
        );

        item.classList.add("active");

        const section =
            document.getElementById(target);

        if(section){

            section.classList.add("active");

        }

    });

});

    // =========================
    // ABRIR / FECHAR TREINO
    // =========================

    document.querySelectorAll(".treino-card")
    .forEach(card => {

    const header =
    card.querySelector(".treino-header");

    header.addEventListener("click", () => {

    card.classList.toggle("active");

});

});

    // =========================
    // FILTRO DE TREINOS
    // =========================

    const filterButtons =
    document.querySelectorAll(".filter-btn");

    const cards =
    document.querySelectorAll(".treino-card");

    filterButtons.forEach(btn => {

    btn.addEventListener("click", () => {

        filterButtons.forEach(b =>
            b.classList.remove("active")
        );

        btn.classList.add("active");

        const filtro =
            btn.dataset.filter;

        cards.forEach(card => {

            const categoria =
                card.dataset.categoria;

            const meuTreino =
                card.dataset.meuTreino;

            if(filtro === "meus"){

                card.style.display =
                    meuTreino === "true"
                        ? "block"
                        : "none";

                return;

            }

            card.style.display =
                categoria === filtro
                    ? "block"
                    : "none";

        });

    });

});

