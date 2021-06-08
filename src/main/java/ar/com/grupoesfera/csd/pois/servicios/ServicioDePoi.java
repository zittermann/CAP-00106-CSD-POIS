package ar.com.grupoesfera.csd.pois.servicios;

import ar.com.grupoesfera.csd.pois.componentes.CalculadorDeDistancias;
import ar.com.grupoesfera.csd.pois.modelos.Poi;
import ar.com.grupoesfera.csd.pois.modelos.Punto;
import ar.com.grupoesfera.csd.pois.repositorio.RepositorioDePoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioDePoi {

    private RepositorioDePoi repositorioDePoi;
    private CalculadorDeDistancias calculadorDeDistancias;

    @Autowired
    public ServicioDePoi(RepositorioDePoi repositorioDePoi, CalculadorDeDistancias calculadorDeDistancias) {
        this.repositorioDePoi = repositorioDePoi;
        this.calculadorDeDistancias = calculadorDeDistancias;
    }

    public Optional<Poi> obtenerPoiMasCercano(double latitud, double longitud) {

        double distanciaMinima = Double.MAX_VALUE;
        Punto origen = Punto.en(latitud, longitud);
        Poi poiMasCercano = null;

        List<Poi> pois = this.repositorioDePoi.findAll();
        for (Poi unPoi : pois) {
            double distancia = this.calculadorDeDistancias.calcularDistanciaEntre(origen, Punto.en(unPoi.getLatitud(), unPoi.getLongitud()));
            if (distancia <= distanciaMinima) {
                distanciaMinima = distancia;
                poiMasCercano = unPoi;
            }
        }
        return Optional.ofNullable(poiMasCercano);
    }
}
