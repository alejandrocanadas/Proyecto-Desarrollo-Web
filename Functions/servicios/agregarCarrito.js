let carrito = [];
document.addEventListener("DOMContentLoaded", () => {
    const botonesAgregar = document.querySelectorAll("button.agregar");
    botonesAgregar.forEach((boton, index) => {
        boton.addEventListener("click", () => {
            agregarAlCarrito(index);
        });
    });
});
function agregarAlCarrito(index) {
    const productos = document.querySelectorAll(".producto");
    const producto = productos[index];
    const nombre = producto.querySelector(".nombre").textContent;
    
    const precioTexto = producto.querySelector("ul").lastElementChild.innerText;
    const precio = parseInt(precioTexto.replace("Precio: ", "").replace(" COP", "").replace(".", ""));
    const imgProducto = producto.querySelector("img").src;

    const productoSeleccionado = {
        nombre: nombre, 
        precio: precio,
        imagen: imgProducto
    };
    carrito.push(productoSeleccionado);
    console.log(carrito);
    actualizarCarrito();
}

function actualizarCarrito() {
    console.log("HOLA");
    const listaCarrito = document.getElementById("listaCarrito");
    const totalCarrito = document.getElementById("totalCarrito");
    listaCarrito.innerHTML = "";
    let total = 0;
    if(carrito.length === 0){
        listaCarrito.innerHTML = "<li>El carrito está vacío</li>";
    } else{
        carrito.forEach((producto, index) => {
            console.log("HOLA");
            total += producto.precio;
            const item = document.createElement("li");
            const img = document.createElement("img");
            item.style.display = "flex";
            item.style.alignItems = "center";
            item.style.maxWidth = "400px";
            item.style.fontSize = "20px";
            item.style.fontWeight = "bold";
            img.src = producto.imagen;
            img.alt = producto.nombre;
            img.style.width = "150px";
            img.style.height = "160px";
            img.style.objectFit = "co"; 
            img.style.borderRadius = "10px";
            const botonEliminar = document.createElement("button");
            botonEliminar.innerHTML = "❌";
            botonEliminar.onclick = () => eliminarDelCarrito(index);
            const texto = document.createElement("span");
            texto.textContent = `${producto.nombre} - ${producto.precio.toLocaleString()} COP`;

            item.appendChild(img); // Agregar imagen
            item.appendChild(texto); // Agregar nombre y precio
            item.appendChild(botonEliminar); // Agregar botón elimina
            item.style.backgroundColor = "#122732";
            item.style.borderRadius = "10px";
            listaCarrito.appendChild(item); 
        });
    }
    totalCarrito.innerText = `Total: ${total.toLocaleString()} COP`;
}
function eliminarDelCarrito(index) {
    carrito.splice(index, 1);
    actualizarCarrito();
}