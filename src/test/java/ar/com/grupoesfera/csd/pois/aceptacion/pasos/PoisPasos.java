package ar.com.grupoesfera.csd.pois.aceptacion.pasos;

import ar.com.grupoesfera.csd.pois.aceptacion.configuracion.ContextoCompartido;
import ar.com.grupoesfera.csd.pois.modelos.Poi;
import ar.com.grupoesfera.csd.pois.repositorio.RepositorioDePoi;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

public class PoisPasos {

    @Autowired
    private RepositorioDePoi repositorioDePoi;

    @Autowired
    private ContextoCompartido contextoCompartido;

    @Autowired
    private MockMvc mockMvc;

    private String longitud;
    private String latitud;

    @DataTableType
    public Poi construir(Map<String, String> registro) {

        Double latitud = Double.parseDouble(registro.get("latitud"));
        Double longitud = Double.parseDouble(registro.get("longitud"));
        String nombre = registro.get("nombre");
        String descripcion = registro.get("descripcion");

        return new Poi(latitud, longitud, nombre, descripcion);
    }

    @Given("que existen los puntos de interes")
    public void queExistenLosPuntosDeInteres(List<Poi> puntosDeInteres) {
        repositorioDePoi.saveAll(puntosDeInteres);
    }

    @Given("^que estoy en '(.*)' y '(.*)'$")
    public void queEstoyEnLongitudYLatitud(String longitud, String latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    @When("pido el POI mas cercano")
    public void pidoElPOIMasCercano() throws Exception {
        ResultActions resultado = this.mockMvc.perform(
                get("/poiMasCercano")
                .queryParam("latitud", this.latitud)
                .queryParam("longitud",this.longitud));

        this.contextoCompartido.AgregarResultado(resultado);
    }
}
