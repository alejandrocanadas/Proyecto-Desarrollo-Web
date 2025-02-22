const botonesServicios = document.getElementsByClassName("texto-servicios");

document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("modal");
    const petSupplies = document.querySelector(".texto-servicios:nth-child(1)"); // Pet Supplies
    const closeModal = document.querySelector(".close");

    // Mostrar modal al hacer clic en "Pet Supplies"
    petSupplies.addEventListener("click", function() {
        modal.style.display = "flex";
        modal.style.alignContent = "center";
        modal.style.justifyContent = "center";

    });

    // Cerrar modal al hacer clic en la "X"
    closeModal.addEventListener("click", function() {
        modal.style.display = "none";
    });

    // Cerrar modal si el usuario hace clic fuera del contenido
    window.addEventListener("click", function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("servgroom");
    const petSupplies = document.querySelector(".texto-servicios:nth-child(2)"); // Pet Supplies
    const closeModal = document.getElementById("close");

    // Mostrar modal al hacer clic en "Pet Supplies"
    petSupplies.addEventListener("click", function() {
        modal.style.display = "flex";
        modal.style.alignContent = "center";
        modal.style.justifyContent = "center";

    });

    // Cerrar modal al hacer clic en la "X"
    closeModal.addEventListener("click", function() {
        modal.style.display = "none";
    });

    // Cerrar modal si el usuario hace clic fuera del contenido
    window.addEventListener("click", function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});
document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("vet");
    const petSupplies = document.querySelector(".texto-servicios:nth-child(3)"); // Pet Supplies
    const closeModal = document.getElementById("close");

    // Mostrar modal al hacer clic en "Pet Supplies"
    petSupplies.addEventListener("click", function() {
        modal.style.display = "flex";
        modal.style.alignContent = "center";
        modal.style.justifyContent = "center";

    });

    // Cerrar modal al hacer clic en la "X"
    closeModal.addEventListener("click", function() {
        modal.style.display = "none";
    });

    // Cerrar modal si el usuario hace clic fuera del contenido
    window.addEventListener("click", function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});
document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("asistencia");
    const petSupplies = document.querySelector(".texto-servicios:nth-child(4)"); // Pet Supplies
    const closeModal = document.getElementById("close");

    // Mostrar modal al hacer clic en "Pet Supplies"
    petSupplies.addEventListener("click", function() {
        modal.style.display = "flex";
        modal.style.alignContent = "center";
        modal.style.justifyContent = "center";

    });

    // Cerrar modal al hacer clic en la "X"
    closeModal.addEventListener("click", function() {
        modal.style.display = "none";
    });

    // Cerrar modal si el usuario hace clic fuera del contenido
    window.addEventListener("click", function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});
document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("broading");
    const petSupplies = document.querySelector(".texto-servicios:nth-child(5)"); // Pet Supplies
    const closeModal = document.getElementById("close");

    // Mostrar modal al hacer clic en "Pet Supplies"
    petSupplies.addEventListener("click", function() {
        modal.style.display = "flex";
        modal.style.alignContent = "center";
        modal.style.justifyContent = "center";

    });

    // Cerrar modal al hacer clic en la "X"
    closeModal.addEventListener("click", function() {
        modal.style.display = "none";
    });

    // Cerrar modal si el usuario hace clic fuera del contenido
    window.addEventListener("click", function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});