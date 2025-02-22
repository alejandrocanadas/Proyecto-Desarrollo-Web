document.addEventListener("DOMContentLoaded", function() {
    const tipoMascota = document.getElementById("tipo_mascota");
    const razaContainer = document.getElementById("raza-container");
    const listaRazas = document.getElementById("lista-razas");
    const inputRaza = document.getElementById("raza");
    const form = document.getElementById("reserva-form");

    // Razas de perros y gatos
    const razasPerros = [
        "Labrador Retriever", "Bulldog Francés", "Bulldog Inglés", "Golden Retriever", 
        "Pastor Alemán", "Beagle", "Poodle", "Rottweiler", "Yorkshire Terrier", 
        "Dálmata", "Boxer", "Border Collie", "Husky Siberiano", "Dóberman", 
        "San Bernardo", "Chihuahua", "Cocker Spaniel", "Akita Inu", "Shiba Inu", 
        "Basset Hound", "Mastín Napolitano", "Pomerania", "Shar Pei", "Pug", 
        "Gran Danés", "Terranova", "Whippet", "Samoyedo", "Alaskan Malamute", "Galgo"
    ];

    const razasGatos = [
        "Persa", "Siamés", "Maine Coon", "Bengalí", "Sphynx", "Ragdoll", 
        "British Shorthair", "Scottish Fold", "Abyssinian", "Birmano", 
        "Angora Turco", "Devon Rex", "Cornish Rex", "Bombay", "Savannah", 
        "Manx", "Tonkinés", "Somalí", "Ocicat", "Chartreux", 
        "Oriental de pelo corto", "Siberiano", "Bosque de Noruega", 
        "Korat", "Singapura", "Havana Brown", "Snowshoe"
    ];

    tipoMascota.addEventListener("change", function() {
        listaRazas.innerHTML = ""; // Limpiar opciones previas
        inputRaza.value = ""; // Resetear el campo de raza
        
        let razas = this.value === "perro" ? razasPerros : this.value === "gato" ? razasGatos : [];

        if (razas.length > 0) {
            razas.forEach(raza => {
                let option = document.createElement("option");
                option.value = raza;
                listaRazas.appendChild(option);
            });
            razaContainer.style.display = "block";
        } else {
            razaContainer.style.display = "none";
        }
    });

    // Manejo del envío del formulario
    form.addEventListener("submit", function(event) {
        event.preventDefault(); 

        // Obtener los valores del formulario
        const nombre = document.getElementById("nombre").value;
        const apellido = document.getElementById("apellido").value;
        const telefono = document.getElementById("telefono").value;
        const email = document.getElementById("email").value;
        const tipoMascotaSeleccionado = tipoMascota.value;
        const razaSeleccionada = inputRaza.value;
        const fechaCita = document.getElementById("fecha").value;
        const horaCita = document.getElementById("hora").value;

        // Validación adicional (por ejemplo, evitar fechas pasadas)
        const fechaHoy = new Date().toISOString().split("T")[0];
        if (fechaCita < fechaHoy) {
            alert("No puedes seleccionar una fecha pasada.");
            return;
        }

        const reserva = {
            nombre,
            apellido,
            telefono,
            email,
            tipoMascota: tipoMascotaSeleccionado,
            raza: razaSeleccionada,
            fecha: fechaCita,
            hora: horaCita
        };

        // Guardar en localStorage (simulación de base de datos)
        let reservas = JSON.parse(localStorage.getItem("reservas")) || [];
        reservas.push(reserva);
        localStorage.setItem("reservas", JSON.stringify(reservas));

        alert("Reserva realizada con éxito.");

        form.reset();
        razaContainer.style.display = "none";
    });
});
