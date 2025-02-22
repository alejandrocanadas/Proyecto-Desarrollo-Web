document.addEventListener("DOMContentLoaded", function () {
    const menuBtn = document.querySelector(".menu-hamburguesa");
    const navMenu = document.querySelector(".items-navegacion");

    menuBtn.addEventListener("click", function () {
        navMenu.classList.toggle("active");
    });
});
