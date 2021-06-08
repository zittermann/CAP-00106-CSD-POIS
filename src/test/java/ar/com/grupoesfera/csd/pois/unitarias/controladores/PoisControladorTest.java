package ar.com.grupoesfera.csd.pois.unitarias.controladores;

import ar.com.grupoesfera.csd.pois.controladores.PoisControlador;
import ar.com.grupoesfera.csd.pois.modelos.Poi;
import ar.com.grupoesfera.csd.pois.servicios.ServicioDePoi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PoisControladorTest {

    private static final Double LATIUD_VALIDA = -34.6048789;
    private static final Double LONGITUD_VALIDA = -58.3793919;

    @InjectMocks
    private PoisControlador controlador;

    @Mock
    private ServicioDePoi servicioDePoi;

    @Test
    public void respondeSinContenidoCuandoNoExisteUnPuntoDeInteresCercano() {

        when(this.servicioDePoi.obtenerPoiMasCercano(anyDouble(), anyDouble())).thenReturn(Optional.empty());

        ResponseEntity<Poi> respuesta = this.controlador.obtenerPoiMasCernano(LATIUD_VALIDA, LONGITUD_VALIDA);

        assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void respondeConElPuntoDeInteresCercanoEncontrado() {

        final double latitud = -34.6041537;
        final double longitud = -58.3807202;
        final String nombre = "Las Cuartetas";
        final String descripcion = "Pizzeria";
        final Optional<Poi> poiCercano = Optional.of(new Poi(latitud, longitud, nombre, descripcion));

        when(this.servicioDePoi.obtenerPoiMasCercano(anyDouble(), anyDouble()))
                .thenReturn(poiCercano);

        ResponseEntity<Poi> respuesta = this.controlador.obtenerPoiMasCernano(LATIUD_VALIDA, LONGITUD_VALIDA);
        Poi cuerpo = respuesta.getBody();

        assertThat(respuesta.getStatusCode().value()).isEqualTo(200);
        assertThat(cuerpo).isNotNull();
        assertThat(cuerpo.getLatitud()).isEqualTo(latitud);
        assertThat(cuerpo.getLongitud()).isEqualTo(longitud);
        assertThat(cuerpo.getNombre()).isEqualTo(nombre);
        assertThat(cuerpo.getDescripcion()).isEqualTo(descripcion);
    }
}
