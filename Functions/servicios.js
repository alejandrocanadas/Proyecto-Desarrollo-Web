const botonesServicios = document.getElementsByClassName("texto-servicios");

document.addEventListener("DOMContentLoaded", function() {
    const modal = document.getElementById("modal");
    const petSupplies = document.querySelector(".texto-servicios:nth-child(1)"); // Pet Supplies
    const closeModal = document.querySelector(".close");

    // Mostrar modal al hacer clic en "Pet Supplies"
    petSupplies.addEventListener("click", function() {
        modal.style.display = "block";
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

botonesServicios[1].addEventListener("click", function () {
    window.location.href = "servicios.html";
}); 
botonesServicios[2].addEventListener("click", function () {
    window.location.href = "servicios.html";
}); 
botonesServicios[3].addEventListener("click", function () {
    window.location.href = "servicios.html";
}); 
botonesServicios[4].addEventListener("click", function () {
    window.location.href = "servicios.html";
}); 