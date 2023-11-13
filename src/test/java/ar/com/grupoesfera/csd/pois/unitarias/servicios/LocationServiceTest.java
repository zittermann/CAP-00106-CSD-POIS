package ar.com.grupoesfera.csd.pois.unitarias.servicios;

import ar.com.grupoesfera.csd.pois.controladores.LocationController;
import ar.com.grupoesfera.csd.pois.exceptions.NoNearPointFoundException;
import ar.com.grupoesfera.csd.pois.modelos.Location;
import ar.com.grupoesfera.csd.pois.repositorio.LocationRepository;
import ar.com.grupoesfera.csd.pois.service.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LocationController.class)
public class LocationServiceTest {

    @Mock
    private final LocationRepository repository = mock(LocationRepository.class);

    @InjectMocks
    private final LocationService service = new LocationService(repository);

    @Test
    void willFindNearest() {

        Location santander = new Location();
        santander.setLatitude(1d);
        santander.setLongitude(1d);
        santander.setLabel("banco");
        santander.setName("Santander");

        Location bbva = new Location();
        bbva.setLatitude(2d);
        bbva.setLongitude(2d);
        bbva.setLabel("banco");
        bbva.setName("BBVA");

        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(santander);
        locations.add(bbva);

        when(repository.findAllByLabel("banco")).thenReturn(locations);
        Location response = service.findNearestLocation(1d, 1d, "banco");

        Assertions.assertEquals(santander, response);

    }

    @Test
    void findAllNearest() {

        Location santander = new Location();
        santander. setLatitude(1d);
        santander.setLongitude(1d);
        santander.setLabel("banco");
        santander.setName("Santander");

        Location bbva = new Location();
        bbva.setLatitude(1d);
        bbva.setLongitude(1d);
        bbva.setLabel("banco");
        bbva.setName("BBVA");

        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(santander);
        locations.add(bbva);

        when(repository.findAllByLabel("banco")).thenReturn(locations);
        List<Location> response = service.findAllNearestLocations(0.99999d, 0.99999d, "banco");

        Assertions.assertEquals(locations, response);

    }

    @Test
    void willThrowWhenNoNearest() {

        Location santander = new Location();
        santander.setLatitude(1d);
        santander.setLongitude(1d);
        santander.setLabel("banco");
        santander.setName("Santander");

        Location bbva = new Location();
        bbva.setLatitude(2d);
        bbva.setLongitude(2d);
        bbva.setLabel("banco");
        bbva.setName("BBVA");

        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(santander);
        locations.add(bbva);

        when(repository.findAllByLabel("banco")).thenReturn(locations);
        assertThatThrownBy(() -> service.findNearestLocation(3d, 3d, "banco"))
                .isInstanceOf(NoNearPointFoundException.class)
                .hasMessage("No se ha encontrado un punto cercano");

    }

    @Test
    void willThrowWhenNoNearestAll() {

        Location santander = new Location();
        santander.setLatitude(1d);
        santander.setLongitude(1d);
        santander.setLabel("banco");
        santander.setName("Santander");

        Location bbva = new Location();
        bbva.setLatitude(2d);
        bbva.setLongitude(2d);
        bbva.setLabel("banco");
        bbva.setName("BBVA");

        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(santander);
        locations.add(bbva);

        when(repository.findAllByLabel("banco")).thenReturn(locations);
        assertThatThrownBy(() -> service.findAllNearestLocations(3d, 3d, "banco"))
                .isInstanceOf(NoNearPointFoundException.class)
                .hasMessage("No se han encontrado puntos cercanos");

    }

}
