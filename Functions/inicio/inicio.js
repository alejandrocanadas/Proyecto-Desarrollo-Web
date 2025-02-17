document.addEventListener("DOMContentLoaded", function () {
    // Seleccionamos el modal de suscripción y sus botones
    const modalSuscribir = document.getElementById("modal-suscribir");
    const botonesSuscribir = document.querySelectorAll(".btn-suscribir, .btn-comunidad, .agendar");
    const botonCerrar = document.querySelector("#modal-suscribir .cerrar");

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

    // Formularios
    const formularioSuscripcion = document.getElementById("form-suscribir");
    const mensajeConfirmacionSuscripcion = document.getElementById("mensaje-registro");

    // Clase Cliente
    class Cliente {
        constructor(nombre, email, password, telefono) {
            this.nombre = nombre;
            this.email = email;
            this.password = password;
            this.telefono = telefono;
        }

        imprimirDatos() {
            console.log(`Nuevo Cliente Registrado`);
            console.log(`Nombre: ${this.nombre}`);
            console.log(`Email: ${this.email}`);
            console.log(`Contraseña: ${this.password}`);
            console.log(`Teléfono: ${this.telefono}`);
        }
    }

    // Asegurar que los modales estén ocultos al cargar la página
    modalSuscribir.style.display = "none";
    modalOpcion.style.display = "none";

    // Función para mostrar el modal de suscripción
    function mostrarModal() {
        modalSuscribir.style.display = "flex";
    }

    // Función para cerrar el modal de suscripción
    function cerrarModal() {
        modalSuscribir.style.display = "none";
    }

    // Event Listener para abrir el modal cuando se haga clic en los botones de suscripción
    botonesSuscribir.forEach(boton => {
        boton.addEventListener("click", function (event) {
            event.preventDefault();
            mostrarModal();
        });
    });

    // Event Listener para cerrar el modal de suscripción al hacer clic en la "X"
    if (botonCerrar) {
        botonCerrar.addEventListener("click", cerrarModal);
    }

    // Event Listener para cerrar el modal de suscripción al hacer clic fuera de él
    window.addEventListener("click", function (event) {
        if (event.target === modalSuscribir) {
            cerrarModal();
        }
    });

    // Manejo del formulario de suscripción
    if (formularioSuscripcion) {
        formularioSuscripcion.addEventListener("submit", function (event) {
            event.preventDefault();

            // Obtener los valores del formulario
            const nombre = document.getElementById("nombre-suscripcion").value;
            const email = document.getElementById("email-suscripcion").value;
            const password = document.getElementById("password-suscripcion").value;
            const telefono = document.getElementById("telefono-suscripcion").value;

            // Crear una nueva instancia de Cliente
            const nuevoCliente = new Cliente(nombre, email, password, telefono);

            // Imprimir datos del cliente en la consola
            nuevoCliente.imprimirDatos();

            // Mostrar mensaje de confirmación temporalmente
            mensajeConfirmacionSuscripcion.style.display = "block";

            // Limpiar formulario después de enviar
            formularioSuscripcion.reset();
        });
    }

    // Eventos de click en tarjetas de servicio (cambiar imagen y descripción)
    tarjetasServicio.forEach(tarjeta => {
        tarjeta.addEventListener("click", function () {
            const nuevaImagen = tarjeta.getAttribute("data-imagen");
            const nuevaDescripcion = tarjeta.getAttribute("data-descripcion");

            imagenPrincipal.src = nuevaImagen;
            descripcionImagen.textContent = nuevaDescripcion;
        });
    });

    // Lista de opciones personalizadas para cada botón
    const opcionesServicios = {
        "Juguetes": {
            imagen: "Resources/chris-jarvis-NOLrgL3hUJg-unsplash.jpg",
            descripcion: "Los juguetes ayudan a mantener activa y feliz a tu mascota."
        },
        "Accesorios": {
            imagen: "Resources/raoul-droog-yMSecCHsIBc-unsplash.jpg",
            descripcion: "Dale estilo y comodidad a tu mascota con nuestros accesorios."
        },
        "Uñas": {
            imagen: "Resources/karsten-winegeart-4OprnvM9UQs-unsplash.jpg",
            descripcion: "El corte de uñas es fundamental para la salud y comodidad de tu mascota."
        },
        "Cortes de pelo": {
            imagen: "Resources/charlesdeluvio-DziZIYOGAHc-unsplash.jpg",
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

    // Mostrar el popup con la descripción al hacer clic en un botón dentro de la tarjeta
    botonesPopup.forEach(boton => {
        boton.addEventListener("click", function (event) {
            event.stopPropagation(); // Evita que el clic afecte la tarjeta

            // Obtiene el nombre del botón
            const opcionSeleccionada = boton.textContent.trim();

            // Busca en el objeto de opciones
            if (opcionesServicios[opcionSeleccionada]) {
                tituloOpcion.textContent = opcionSeleccionada;
                imagenOpcion.src = opcionesServicios[opcionSeleccionada].imagen;
                descripcionOpcion.textContent = opcionesServicios[opcionSeleccionada].descripcion;

                // Muestra el modal
                modalOpcion.style.display = "flex";
            } else {
                console.error("Opción no encontrada:", opcionSeleccionada);
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
