package ar.com.grupoesfera.csd.pois.aceptacion;

import ar.com.grupoesfera.csd.pois.PoisApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
                 glue = "ar.com.grupoesfera.csd.pois.aceptacion")
                 //extraGlue = "ar.com.grupoesfera.csd.pois.aceptacion.pasos.configuraciones")
@SpringBootTest(classes = PoisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EjecutorPruebasAceptacion {
}
