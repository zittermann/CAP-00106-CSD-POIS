package ar.com.grupoesfera.csd.pois.modelos;

public class Punto {

    public final double latitud;
    public final double longitud;

    private Punto(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public static Punto en(double latitud, double longitud) {
        return new Punto(latitud, longitud);
    }
}
