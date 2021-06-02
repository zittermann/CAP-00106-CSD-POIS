package ar.com.grupoesfera.csd.pois.aceptacion.pasos;

import ar.com.grupoesfera.csd.pois.PoisApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = PoisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfiguracionContexto {
}
