package ar.com.grupoesfera.csd.pois.controladores;

import ar.com.grupoesfera.csd.pois.modelos.Location;
import ar.com.grupoesfera.csd.pois.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Devuelve todos los Location")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", content = @Content(
                    schema = @Schema(implementation = Location.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<Location>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Devuelve el punto de interés más cercano.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operación exitosa.",
                    content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado un punto cercano"),
    })
    @GetMapping("/nearest")
    public ResponseEntity<Location> findNearestInterestPoint(
            @Parameter(description = "Latitud", required = true)
            @RequestParam("lat") Double latitude,

            @Parameter(description = "Longitud", required = true)
            @RequestParam("lon") Double longtitude,

            @Parameter(description = "Label del POI",
                    required = true, example = "banco")
            @RequestParam String label) {
        return ResponseEntity.ok(service.findNearestLocation(latitude, longtitude, label));
    }

    @Operation(summary = "Devuelve todos los puntos de interés cercanos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operación exitosa.",
                    content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "400",
                    description = "No se han encontrado puntos cercanos"),
    })
    @GetMapping("/all-nearest")
    public ResponseEntity<ArrayList<Location>> findAllNearestLocations(
            @Parameter(description = "Latitud", required = true)
            @RequestParam("lat") Double latitude,

            @Parameter(description = "Longitud", required = true)
            @RequestParam("lon") Double longtitude,

            @Parameter(description = "Label del POI",
                    required = true, example = "banco")
            @RequestParam String label) {
        return ResponseEntity.ok(service.findAllNearestLocations(latitude, longtitude, label));
    }

    @Operation(summary = "Agrega un registro de Universo Winback.")
    @ApiResponse(responseCode = "200",
            description = "Location creado correctamente",
            content = @Content(schema = @Schema(implementation = Location.class)))
    @PostMapping
    public ResponseEntity<Location> create(@RequestBody Location location) {
        return ResponseEntity.ok(service.create(location));
    }

}
