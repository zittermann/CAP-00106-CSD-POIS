package ar.com.grupoesfera.csd.pois;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
                 plugin = { "json:target/json/cucumber.json"},
                 tags = "not @WIP")
public class EjecutorPruebasAceptacion {
}
