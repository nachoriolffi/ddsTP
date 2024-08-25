$(document).ready(function() {
    const apiBase = "https://apis.datos.gob.ar/georef/api";

    // Cargar provincias al cargar la página
    $.get(`${apiBase}/provincias`, function(data) {
        let provincias = data.provincias;

        // Ordenar provincias alfabéticamente
        provincias.sort(function(a, b) {
            return a.nombre.localeCompare(b.nombre);
        });

        let provinciaSelect = $('#provincia');
        provincias.forEach(function(provincia) {
            provinciaSelect.append(new Option(provincia.nombre, provincia.id));
        });
    });

    // Manejar cambio en el select de provincias
    $('#provincia').change(function() {
        let provinciaId = $(this).val();
        let localidadSelect = $('#localidad');

        if (provinciaId) {
            localidadSelect.prop('disabled', false);
            localidadSelect.empty().append(new Option('Seleccione una localidad', ''));

            // Cargar localidades basadas en la provincia seleccionada
            $.get(`${apiBase}/localidades?provincia=${provinciaId}&campos=id,nombre&max=1000`, function(data) {
                let localidades = data.localidades;

                // Ordenar localidades alfabéticamente
                localidades.sort(function(a, b) {
                    return a.nombre.localeCompare(b.nombre);
                });

                localidades.forEach(function(localidad) {
                    localidadSelect.append(new Option(localidad.nombre, localidad.id));
                });
            });
        } else {
            localidadSelect.prop('disabled', true).empty().append(new Option('Seleccione una localidad', ''));
        }
    });
});