$(document).ready(function () {
    const apiBase = "https://apis.datos.gob.ar/georef/api";
    let provinciaId;
    let localidadId;

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
        const localidadSelect = $('#localidad');

        // Vaciar el selector de localidades antes de cargar las nuevas opciones
        localidadSelect.empty();
        localidadSelect.append(new Option("Seleccione una opción", "", true, true));

        // Cargar localidades de la provincia seleccionada
        $.get(`${apiBase}/localidades?provincia=${provinciaId}`, function (data) {
            const localidades = data.localidades;

            localidades.sort((a, b) => a.nombre.localeCompare(b.nombre));

            localidades.forEach(function (localidad) {
                localidadSelect.append(new Option(localidad.nombre, localidad.id));
            });

        }).fail(function (jqXHR, textStatus, errorThrown) {
            console.error("Error al cargar las localidades:", textStatus, errorThrown);
        });

    });

    $('#localidad').change(function () {
        localidadId = $(this).val();
    });


    $('#calle').on('input', function () {
        let direccion = $(this).val();
        let suggestionsContainer = $('#suggestions');

        suggestionsContainer.empty();

        if (direccion.length >= 3) {
            $.get(`${apiBase}/direcciones?direccion=${direccion}&provincia=${provinciaId}&campos=completo&max=100`, function (data) {
                if (data.direcciones && data.direcciones.length > 0) {
                    suggestionsContainer.removeClass('d-none');

                    let seenAddresses = new Set();
                    data.direcciones.forEach(function (direccionItem) {
                        if (!seenAddresses.has(direccionItem.nomenclatura)) {
                            seenAddresses.add(direccionItem.nomenclatura);

                            let suggestion = $('<button type="button" class="list-group-item list-group-item-action"></button>')
                                .text(direccionItem.nomenclatura)
                                .click(function () {
                                    $('#calle').val(direccionItem.nomenclatura);
                                    $('#altura').val(direccionItem.altura.valor);
                                    // Asignar coordenadas
                                    $('#latitud').val(direccionItem.ubicacion.lat);
                                    $('#longitud').val(direccionItem.ubicacion.lon);

                                    // Verificar en consola
                                    console.log("Altura:", direccionItem.altura.valor);
                                    console.log("Latitud:", direccionItem.ubicacion.lat);
                                    console.log("Longitud:", direccionItem.ubicacion.lon);

                                    suggestionsContainer.addClass('d-none');
                                });
                            suggestionsContainer.append(suggestion);
                        }
                    });
                } else {
                    suggestionsContainer.addClass('d-none');
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                console.error("Error al cargar las direcciones:", textStatus, errorThrown);
            });
        } else {
            suggestionsContainer.addClass('d-none');
        }
    });



    // Cerrar las sugerencias al hacer clic fuera
    $(document).on('click', function (event) {
        if (!$(event.target).closest('#calle, #suggestions').length) {
            $('#suggestions').addClass('d-none');
        }
    });
});