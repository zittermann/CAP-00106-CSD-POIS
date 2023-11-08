package ar.com.grupoesfera.csd.pois.controladores;

import ar.com.grupoesfera.csd.pois.modelos.Location;
import ar.com.grupoesfera.csd.pois.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/api/locations")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Location>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Location> create(@RequestBody Location location) {
        return ResponseEntity.ok(service.create(location));
    }

    @GetMapping("/nearest")
    public ResponseEntity<Location> findNearestInterestPoint(
            @RequestParam("lat") Double latitude,
            @RequestParam("lon") Double longtitude,
            @RequestParam String label) {
        return ResponseEntity.ok(service.findNearestLocation(latitude, longtitude, label));
    }

    @GetMapping("/all-nearest")
    public ResponseEntity<ArrayList<Location>> findAllNearestLocations(
            @RequestParam("lat") Double latitude,
            @RequestParam("lon") Double longtitude,
            @RequestParam String label) {
        return ResponseEntity.ok(service.findAllNearestLocations(latitude, longtitude, label));
    }

}
