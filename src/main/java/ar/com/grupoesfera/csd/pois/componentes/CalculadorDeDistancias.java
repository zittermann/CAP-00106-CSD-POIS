package ar.com.grupoesfera.csd.pois.componentes;

import ar.com.grupoesfera.csd.pois.modelos.Punto;
import org.springframework.stereotype.Component;

@Component
public class CalculadorDeDistancias {

    public double calcularDistanciaEntre(Punto origen, Punto objetivo) {

        double primerPunto  = Math.pow((objetivo.latitud - origen.latitud), 2);
        double segundoPunto  = Math.pow((objetivo.longitud - origen.longitud), 2);

        return Math.sqrt(primerPunto + segundoPunto);
    }
}
