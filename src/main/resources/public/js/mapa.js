 document.addEventListener('DOMContentLoaded', function () {
            let map;

            const initMap = () => {
                // Inicializar el mapa con la vista centrada en Buenos Aires
                map = L.map('map', {
                    center: [-34.598630, -58.419962],
                    zoom: 12,
                    scrollWheelZoom: false, // Desactiva el zoom con la rueda del ratón
                    dragging: false, // Desactiva el arrastre del mapa
                    touchZoom: false, // Desactiva el zoom táctil
                    doubleClickZoom: false, // Desactiva el zoom por doble clic
                    boxZoom: false, // Desactiva el zoom por selección de área
                    keyboard: false, // Desactiva el control del mapa con teclado
                    zoomControl: false, // Desactiva los controles de zoom del mapa
                }).setView([-34.598630, -58.419962], 18); // Ubicación inicial (Buenos Aires)

                // Cargar los tiles de OpenStreetMap
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
                }).addTo(map);

                // Hacer una solicitud a la API de georreferenciación del Gobierno de Buenos Aires
                fetch('https://apis.datos.gob.ar/georef/api/direcciones?direccion=Avenida Medrano 951, CABA&max=1')
                    .then(response => response.json())
                    .then(data => {
                        // Obtener las coordenadas de latitud y longitud de la respuesta
                        var lat = data.direcciones[0].ubicacion.lat;
                        var lon = data.direcciones[0].ubicacion.lon;

                        // Agregar un marcador al mapa con las coordenadas obtenidas
                        L.marker([lat, lon]).addTo(map)
                            .bindPopup('Avenida Medrano 951, CABA');
                    })
                    .catch(error => {
                        console.error('Error al obtener la ubicación:', error);
                    });
            };

            // Detectar cuando el modal 'heladeraModal' está completamente visible
            const heladeraModal = document.getElementById('heladeraModal');
            heladeraModal.addEventListener('shown.bs.modal', function () {
                if (!map) {
                    initMap(); // Inicializar el mapa la primera vez que el modal se muestra
                } else {
                    setTimeout(() => {
                        map.invalidateSize(); // Ajustar el tamaño del mapa cuando el modal esté visible
                    }, 300); // Esperar 300 ms para asegurarse de que el modal esté completamente renderizado
                }
            });
        });