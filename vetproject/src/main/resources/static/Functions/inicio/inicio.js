document.addEventListener("DOMContentLoaded", function () {
    // Imagen principal y tarjetas de servicio
    const imagenPrincipal = document.getElementById("imagen-principal");
    const descripcionImagen = document.getElementById("descripcion-imagen");
    const tarjetasServicio = document.querySelectorAll(".card-servicio");
    const botonesPopup = document.querySelectorAll(".boton-popup");

    // Modal para opciones de servicio
    const modalOpcion = document.getElementById("modal-opcion");
    const tituloOpcion = document.getElementById("titulo-opcion");
    const imagenOpcion = document.getElementById("imagen-opcion");
    const descripcionOpcion = document.getElementById("descripcion-opcion");
    const botonCerrarModal = document.querySelector("#modal-opcion .cerrar");

    // Ocultar el modal al cargar la página
    modalOpcion.style.display = "none";

    // Lista de opciones personalizadas para cada botón
    const opcionesServicios = {
        "Juguetes": {
            imagen: "Resources/chris-jarvis-NOLrgL3hUJg-unsplash.jpg",
            descripcion: "Los juguetes ayudan a mantener activa y feliz a tu mascota."
        },
        "Accesorios": {
            imagen: "Resources/reed-shepherd-r1q76Rut5t8-unsplash.jpg",
            descripcion: "Dale estilo y comodidad a tu mascota con nuestros accesorios."
        },
        "Uñas": {
            imagen: "Resources/servicios/daycare.jpg",
            descripcion: "El corte de uñas es fundamental para la salud y comodidad de tu mascota."
        },
        "Cortes de pelo": {
            imagen: "Resources/servicios/serviciosgrooming.jpg",
            descripcion: "Un corte de pelo adecuado mantiene a tu mascota fresca y cómoda."
        },
        "Chequeo de salud": {
            imagen: "Resources/werzk-luuuuuuu-tDlo2ZPlQlU-unsplash.jpg",
            descripcion: "Revisiones periódicas para garantizar la salud de tu mascota."
        },
        "Vacunas": {
            imagen: "Resources/flouffy-VW2HGWgtIfw-unsplash.jpg",
            descripcion: "Protege a tu mascota contra enfermedades con vacunas esenciales."
        },
        "Refugios": {
            imagen: "Resources/darinka-kievskaya-ff221Bu56mI-unsplash.jpg",
            descripcion: "Conoce refugios donde puedes adoptar una mascota o brindar apoyo."
        },
        "Entretenimiento": {
            imagen: "Resources/andrew-s-ouo1hbizWwo-unsplash.jpg",
            descripcion: "Actividades diseñadas para estimular y divertir a tu mascota."
        },
        "Comida": {
            imagen: "Resources/charlesdeluvio-D44HIk-qsvI-unsplash.jpg",
            descripcion: "Nutrición balanceada para el bienestar de tu mascota."
        }
    };

    // Eventos de click en tarjetas de servicio (cambiar imagen y descripción)
    tarjetasServicio.forEach(tarjeta => {
        tarjeta.addEventListener("click", function () {
            const nuevaImagen = tarjeta.getAttribute("data-imagen");
            const nuevaDescripcion = tarjeta.getAttribute("data-descripcion");

            if (nuevaImagen) {
                imagenPrincipal.src = nuevaImagen;
                descripcionImagen.textContent = nuevaDescripcion;
            }
        });
    });

    // Mostrar el popup con la descripción al hacer clic en un botón dentro de la tarjeta
    botonesPopup.forEach(boton => {
        boton.addEventListener("click", function (event) {
            event.stopPropagation(); // Evita que el clic afecte la tarjeta

            // Obtiene el nombre del botón
            const opcionSeleccionada = boton.textContent.trim();

            // Verifica que la opción existe y la imagen no es nula
            if (opcionesServicios[opcionSeleccionada] && opcionesServicios[opcionSeleccionada].imagen) {
                tituloOpcion.textContent = opcionSeleccionada;
                imagenOpcion.src = opcionesServicios[opcionSeleccionada].imagen;
                descripcionOpcion.textContent = opcionesServicios[opcionSeleccionada].descripcion;

                // Solo muestra el modal si la imagen es válida
                imagenOpcion.onload = function () {
                    modalOpcion.style.display = "flex";
                };

                // Manejo de error si la imagen no se puede cargar
                imagenOpcion.onerror = function () {
                    console.error("No se pudo cargar la imagen para:", opcionSeleccionada);
                    modalOpcion.style.display = "none";
                };
            } else {
                console.error("Opción no encontrada o sin imagen válida:", opcionSeleccionada);
                modalOpcion.style.display = "none"; // Evitar que el modal aparezca vacío
            }
        });
    });

    // Cerrar el modal de opciones cuando se haga clic en la "X"
    botonCerrarModal.addEventListener("click", function () {
        modalOpcion.style.display = "none";
    });

    // Cerrar el modal de opciones si se hace clic fuera del contenido
    window.addEventListener("click", function (event) {
        if (event.target === modalOpcion) {
            modalOpcion.style.display = "none";
        }
    });
});
