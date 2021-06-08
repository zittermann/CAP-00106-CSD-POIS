package ar.com.grupoesfera.csd.pois.integracion.servicios;

import ar.com.grupoesfera.csd.pois.modelos.Poi;
import ar.com.grupoesfera.csd.pois.repositorio.RepositorioDePoi;
import ar.com.grupoesfera.csd.pois.servicios.ServicioDePoi;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:integration-test.properties")
public class ServicioDePoiIntegrationTest {

    @Autowired
    private ServicioDePoi servicio;

    @Autowired
    private RepositorioDePoi repositorio;

    @Test
    public void noEncuentraNingunPuntoDeInteresCercano() {

        this.repositorio.deleteAll();

        Optional<Poi> poiMasCercano = this.servicio.obtenerPoiMasCercano(-34.603765, -58.381570);

        assertThat(poiMasCercano).isNotPresent();
    }

    @Test
    public void deberiaEncontrarLasCuartetasEntreTodosLosPuntosDeInteres() {

        Poi elCuartito = new Poi(-34.59762, -58.385527, "El cuartito", "Pizza con Faina increible");
        Poi guerrin = new Poi(-34.60393, -58.38605, "Guerrin", "Pizzeria al paso");
        Poi lasCuartetas = new Poi(-34.60370, -58.37905, "Las cuartetas", "La mejor pizza a la piedra");

        this.repositorio.saveAll(List.of(elCuartito, guerrin, lasCuartetas));

        Optional<Poi> poiMasCercano = this.servicio.obtenerPoiMasCercano(-34.603765, -58.381570);

        Condition<Poi> esLasCuartetas = new Condition<>(poi -> "Las cuartetas".equals(poi.getNombre()), "Las cuartetas");
        assertThat(poiMasCercano).hasValueSatisfying(esLasCuartetas);
    }
}
