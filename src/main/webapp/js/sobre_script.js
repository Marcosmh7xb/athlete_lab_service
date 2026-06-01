document.addEventListener("DOMContentLoaded", () => {

    const header     = document.getElementById("siteHeader");
    const headerLogo = document.getElementById("headerLogo");
    const heroWrap   = document.getElementById("heroLogoWrap");
    const hero       = document.getElementById("hero");

    // ===== ANIMAÇÃO: LOGO DESCE DO HERO → SOBE E FICA FIXA NO HEADER =====

    function onScroll() {

        const scrollY = window.scrollY;
        const heroH   = hero.offsetHeight;

        // Header com blur ao rolar
        if (scrollY > 20) {
            header.classList.add("scrolled");
        } else {
            header.classList.remove("scrolled");
        }

        // A logo do hero começa a sumir quando scroll passa de 25% da hero
        const fadeStart = heroH * 0.25;
        const fadeEnd   = heroH * 0.75;

        if (scrollY <= fadeStart) {
            heroWrap.style.opacity   = "1";
            heroWrap.style.transform = "scale(1) translateY(0)";
            headerLogo.classList.remove("visible");

        } else if (scrollY >= fadeEnd) {
            heroWrap.style.opacity   = "0";
            heroWrap.style.transform = "scale(0.6) translateY(-60px)";
            headerLogo.classList.add("visible");

        } else {
            const progress = (scrollY - fadeStart) / (fadeEnd - fadeStart);
            heroWrap.style.opacity   = 1 - progress;
            heroWrap.style.transform = `scale(${1 - progress * 0.4}) translateY(${-progress * 60}px)`;

            if (progress > 0.6) {
                headerLogo.classList.add("visible");
            } else {
                headerLogo.classList.remove("visible");
            }
        }

        // Chama os outros efeitos
        revealSections();
        animateTechBars();
    }

    // ===== REVEAL DAS SEÇÕES AO SCROLL =====

    function revealSections() {
        document.querySelectorAll(".reveal").forEach(el => {
            const rect = el.getBoundingClientRect();
            if (rect.top < window.innerHeight * 0.88) {
                el.classList.add("visible");
            }
        });
    }

    // ===== BARRAS DE TECNOLOGIA =====

    let barsAnimated = false;

    function animateTechBars() {
        if (barsAnimated) return;

        const section = document.querySelector(".tecnologias");
        if (!section) return;

        const rect = section.getBoundingClientRect();
        if (rect.top < window.innerHeight * 0.8) {
            barsAnimated = true;
            document.querySelectorAll(".tech-item").forEach((item, i) => {
                const fill = item.dataset.fill || "50";
                const bar  = item.querySelector(".tech-bar-fill");
                setTimeout(() => {
                    bar.style.width = fill + "%";
                }, i * 120);
            });
        }
    }

    // ===== LISTENERS =====

    window.addEventListener("scroll", onScroll, { passive: true });

    // Dispara uma vez ao carregar para fazer o reveal inicial
    onScroll();

});