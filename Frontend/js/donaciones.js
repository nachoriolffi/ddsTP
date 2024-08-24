
// permite que se muestre al usuario la pantalla de carga de datos
// cuando presiona el boton de carga de datos

function mostrarPantallaCarga() {
    var cargaDatos = document.getElementById("cargaDatos");
    if (cargaDatos.style.display === "none") {
        cargaDatos.style.display = "block";
    } else {
        cargaDatos.style.display = "none";
    }
}