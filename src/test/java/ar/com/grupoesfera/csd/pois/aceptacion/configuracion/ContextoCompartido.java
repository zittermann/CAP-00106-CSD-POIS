package ar.com.grupoesfera.csd.pois.aceptacion.configuracion;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

@Component
public class ContextoCompartido {

    private ResultActions resultado;

    public void AgregarResultado(ResultActions resultado) {
        this.resultado = resultado;
    }

    public ResultActions obtenerResultado() {
        return resultado;
    }
}
