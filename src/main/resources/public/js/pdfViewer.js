document.addEventListener("DOMContentLoaded", function () {
    const pdfListItems = document.querySelectorAll(".list-group-item");
    pdfListItems.forEach(item => {
        item.addEventListener("click", function () {
            const pdfId = this.getAttribute("data-id"); // Obtener el ID del PDF
            // Redirigir a la página para ver el PDF
            window.location.href = `/reportes/${pdfId}`; // Asegúrate de que la URL coincida con la ruta del controlador
        });
    });
});