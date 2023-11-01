package ar.com.grupoesfera.csd.pois.service;

import ar.com.grupoesfera.csd.pois.exceptions.NoNearPointFoundException;
import ar.com.grupoesfera.csd.pois.modelos.Location;
import ar.com.grupoesfera.csd.pois.repositorio.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static ar.com.grupoesfera.csd.pois.helpers.LocationHelper.calculateDistance;

@Service
public class LocationService {

    private LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location findNearestLocation(Double lat, Double lon, String label) {

        List<Location> locations = repository.findAllByLabel(label); // Encuentra todas las localizaciones con ese label

        Location nearestInterestPoint = null;
        double closestLocation = 5; // in km, cannot bet longer than 5km

        for(Location location: locations) {
            double distance = calculateDistance(lat, lon,
                    location.getLatitude(), location.getLongitude());

            if (distance < closestLocation) {
                closestLocation = distance;
                nearestInterestPoint = location;
            }

        }

        if (closestLocation == 5) // Significa que ningún punto está más cerca de 5km
            throw new NoNearPointFoundException("No se ha encontrado un punto cercano");

        return nearestInterestPoint;

    }

    public Location create(Location location) {
        return repository.save(location);
    }

}
