$(document).ready(function () {
    const apiBase = "https://apis.datos.gob.ar/georef/api";
    let provinciaId;

    // Cargar provincias en el selector
    $.get(`${apiBase}/provincias`, function (data) {
        let provincias = data.provincias;

        provincias.sort(function (a, b) {
            return a.nombre.localeCompare(b.nombre);
        });

        let provinciaSelect = $('#provincia');
        provincias.forEach(function (provincia) {
            provinciaSelect.append(new Option(provincia.nombre, provincia.id));
        });
    });

    $('#provincia').change(function () {
        provinciaId = $(this).val();
    });
    $('#calle').on('input', function () {
        let direccion = $(this).val();
        let suggestionsContainer = $('#suggestions');

        suggestionsContainer.empty();

        if (direccion.length >= 3) { // Realizar búsqueda si hay al menos 3 caracteres
            $.get(`${apiBase}/direcciones?direccion=${direccion}&provincia=${provinciaId}&campos=completo&max=100`, function (data) {
                if (data.direcciones && data.direcciones.length > 0) {
                    suggestionsContainer.removeClass('d-none'); // Mostrar las sugerencias

                    let seenAddresses = new Set();
                    data.direcciones.forEach(function (direccionItem) {
                        if (!seenAddresses.has(direccionItem.nomenclatura)) {
                            seenAddresses.add(direccionItem.nomenclatura);

                            let suggestion = $('<button type="button" class="list-group-item list-group-item-action"></button>')
                                .text(direccionItem.nomenclatura)
                                .click(function () {
                                    $('#calle').val(direccionItem.nomenclatura); // Colocar el valor seleccionado en el input
                                    suggestionsContainer.addClass('d-none'); // Ocultar las sugerencias
                                });
                            suggestionsContainer.append(suggestion);
                        }
                    });
                } else {
                    suggestionsContainer.addClass('d-none'); // Ocultar el contenedor si no hay resultados
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                console.error("Error al cargar las direcciones:", textStatus, errorThrown);
            });
        } else {
            suggestionsContainer.addClass('d-none'); // Ocultar el contenedor si hay menos de 3 caracteres
        }
    });

    // Cerrar las sugerencias al hacer clic fuera
    $(document).on('click', function (event) {
        if (!$(event.target).closest('#calle, #suggestions').length) {
            $('#suggestions').addClass('d-none');
        }
    });
});