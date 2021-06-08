package ar.com.grupoesfera.csd.pois.controladores;

import ar.com.grupoesfera.csd.pois.modelos.Poi;
import ar.com.grupoesfera.csd.pois.servicios.ServicioDePoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class PoisControlador {

    private final ServicioDePoi servicioDePoi;

    @Autowired
    public PoisControlador(ServicioDePoi servicioDePoi) {
        this.servicioDePoi = servicioDePoi;        
    }

    @GetMapping("/poiMasCercano")
    public ResponseEntity<Poi> obtenerPoiMasCernano(@RequestParam("latitud") Double latitud,
                                                    @RequestParam("longitud") Double longitud) {

        Optional<Poi> poiMasCercano = this.servicioDePoi.obtenerPoiMasCercano(latitud, longitud);
        return poiMasCercano.isPresent() ? ResponseEntity.ok(poiMasCercano.get()) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
