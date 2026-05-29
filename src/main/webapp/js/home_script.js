

    document.addEventListener("DOMContentLoaded", () => {

    // ================= CARROSSEL =================

    let current = 0;

    const slides = document.querySelectorAll(".slide");
    const dots = document.querySelectorAll(".dot");

    function showSlide(index) {

    slides.forEach(s => s.classList.remove("active"));
    dots.forEach(d => d.classList.remove("active"));

    slides[index].classList.add("active");
    dots[index].classList.add("active");

    current = index;
}

    dots.forEach(dot => {

    dot.addEventListener("click", () => {

    showSlide(parseInt(dot.dataset.index));

    resetInterval();

});

});

    function nextSlide() {

    current = (current + 1) % slides.length;

    showSlide(current);

}

    let interval = setInterval(nextSlide, 5000);

    function resetInterval() {

    clearInterval(interval);

    interval = setInterval(nextSlide, 5000);

}

    // ================= MENU =================

    const menuBtn = document.getElementById("menuBtn");
    const menuPopup = document.getElementById("menuPopup");

    menuBtn.addEventListener("click", (e) => {

    e.stopPropagation();

    menuPopup.classList.toggle("active");

    profilePopup.classList.remove("active");

});

    // ================= PERFIL =================

    const profileBtn = document.getElementById("profileBtn");
    const profilePopup = document.getElementById("profilePopup");

    profileBtn.addEventListener("click", (e) => {

    e.stopPropagation();

    profilePopup.classList.toggle("active");

    menuPopup.classList.remove("active");

});

    // ================= FECHAR POPUPS =================

    document.addEventListener("click", () => {

    menuPopup.classList.remove("active");
    profilePopup.classList.remove("active");

});

});

