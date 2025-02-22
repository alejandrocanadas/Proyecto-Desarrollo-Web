let carrito = [];

document.addEventListener("DOMContentLoaded", () => {
    const botonesAgregar = document.querySelectorAll("button.agregar");
    botonesAgregar.forEach((boton) => {
        boton.addEventListener("click", (event) => {
            agregarAlCarrito(event.target);
        });
    });
});

function agregarAlCarrito(boton) {
    // Buscar el contenedor del producto o servicio de Daycare
    const producto = boton.closest(".producto") || boton.closest(".serviciodaycare");

    // Identificar si es un producto de la tienda o un servicio de Daycare
    const nombre = producto.querySelector(".nombre, .name").textContent;

    // Determinar la fuente correcta del precio
    let precioTexto = "";
    if (producto.classList.contains("producto")) {
        // Si es un producto de la tienda, buscar el último <li> dentro del <ul>
        precioTexto = producto.querySelector("ul li:last-child").textContent;
    } else if (producto.classList.contains("serviciodaycare")) {
        // Si es un servicio de Daycare, buscar en el <p>
        precioTexto = producto.querySelector("p").textContent;
    }

    // Extraer el valor numérico del precio correctamente
    const precio = parseInt(precioTexto.replace(/[^0-9]/g, ""), 10);

    // Manejo de imagen: Si es un producto, obtiene la imagen, si es Daycare, usa un ícono por defecto
    const imgProducto = producto.querySelector("img") ? producto.querySelector("img").src : "Resources/servicios/daycare.jpg";

    // Crear objeto para el carrito
    const productoSeleccionado = {
        nombre: nombre, 
        precio: precio,
        imagen: imgProducto
    };

    carrito.push(productoSeleccionado);
    actualizarCarrito();
}

function actualizarCarrito() {
    const listaCarrito = document.getElementById("listaCarrito");
    const totalCarrito = document.getElementById("totalCarrito");
    listaCarrito.innerHTML = "";
    let total = 0;

    if (carrito.length === 0) {
        listaCarrito.innerHTML = "<li>El carrito está vacío</li>";
    } else {
        carrito.forEach((producto, index) => {
            total += producto.precio;

            // Crear elementos del carrito
            const item = document.createElement("li");
            item.style.display = "flex";
            item.style.alignItems = "center";
            item.style.maxWidth = "400px";
            item.style.fontSize = "20px";
            item.style.fontWeight = "bold";
            item.style.backgroundColor = "#122732";
            item.style.borderRadius = "10px";
            item.style.padding = "10px";
            item.style.marginBottom = "5px";

            // Imagen
            const img = document.createElement("img");
            img.src = producto.imagen;
            img.alt = producto.nombre;
            img.style.width = "80px";
            img.style.height = "80px";
            img.style.objectFit = "cover"; 
            img.style.borderRadius = "10px";
            img.style.marginRight = "10px";

            // Texto con nombre y precio
            const texto = document.createElement("span");
            texto.textContent = `${producto.nombre} - ${producto.precio.toLocaleString()} COP`;
            texto.style.flexGrow = "1";

            // Botón para eliminar del carrito
            const botonEliminar = document.createElement("button");
            botonEliminar.innerHTML = "❌";
            botonEliminar.onclick = () => eliminarDelCarrito(index);
            botonEliminar.style.background = "none";
            botonEliminar.style.border = "none";
            botonEliminar.style.cursor = "pointer";
            botonEliminar.style.fontSize = "18px";
            botonEliminar.style.color = "red";
            botonEliminar.style.marginLeft = "10px";

            // Agregar elementos al carrito
            item.appendChild(img);
            item.appendChild(texto);
            item.appendChild(botonEliminar);
            listaCarrito.appendChild(item);
        });
    }

    // Mostrar total
    totalCarrito.innerText = `Total: ${total.toLocaleString()} COP`;
}

function eliminarDelCarrito(index) {
    carrito.splice(index, 1);
    actualizarCarrito();
}
