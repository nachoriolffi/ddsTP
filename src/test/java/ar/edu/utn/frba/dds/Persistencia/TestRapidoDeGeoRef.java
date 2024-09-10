package ar.edu.utn.frba.dds.Persistencia;


import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.Georef;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.GeorefService;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.ListadoProvincias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TestRapidoDeGeoRef {

    @Mock
    private GeorefService georefService;

    @InjectMocks
    private Georef georef;

    private Georef georef2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.datos.gob.ar/georef/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GeorefService georefService = retrofit.create(GeorefService.class);
        georef2 = new Georef(georefService);

    }

    @Test
    public void testListadoProvincias() throws IOException {
        // Mock the response
        ListadoProvincias listadoProvincias = new ListadoProvincias();
        Call<ListadoProvincias> call = mock(Call.class); // Mock Call
        Response<ListadoProvincias> response = Response.success(listadoProvincias);

        // Configura el comportamiento del mock
        when(call.execute()).thenReturn(response);
        when(georefService.provincias(anyString())).thenReturn(call);

        // Call the method
        ListadoProvincias result = georef.listadoProvincias();

        // Verify the result
        assertNotNull(result);
        verify(georefService, times(1)).provincias(anyString());
    }

    @Test
    public void verificoCargaDeProvincias() throws IOException {
        // Call the method
        ListadoProvincias result = georef2.listadoProvincias();

        System.out.println(result.provincias);
        System.out.println(result.cantidad);

        for(int i=0;i<24; i++){
            System.out.println("PROVINCIA: " + result.provincias.get(i).getNombre());

        }
        // Verify the result
        assertNotNull(result);
        System.out.println("Number of provinces: " + result.cantidad);

    }
}