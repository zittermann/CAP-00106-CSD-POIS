package ar.com.grupoesfera.csd.pois.unitarias.componentes;

import ar.com.grupoesfera.csd.pois.componentes.CalculadorDeDistancias;
import ar.com.grupoesfera.csd.pois.modelos.Punto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CalculadorDeDistanciasTest {

    private CalculadorDeDistancias calculadorDeDistancias;

    @BeforeEach
    public void inicializar() {
        this.calculadorDeDistancias = new CalculadorDeDistancias();
    }

    @Test
    public void obtenerDistanciaEntreLasCuartetasYElObelisco() {

        Punto lasCuartetas = Punto.en(-34.603919647085235, -58.378510044979166);
        Punto elObelisco = Punto.en(-34.60357524212199, -58.38156776323911);

        double distancia = this.calculadorDeDistancias.calcularDistanciaEntre(lasCuartetas, elObelisco);

        assertThat(distancia).isCloseTo(0.003, within(0.2));
    }

    @Test
    public void paraUnMismoPuntoLaDistanciaEsCero() {

        Punto lasCuartetas = Punto.en(-34.603919647085235, -58.378510044979166);

        double distancia = this.calculadorDeDistancias.calcularDistanciaEntre(lasCuartetas, lasCuartetas);

        assertThat(distancia).isEqualTo(0);
    }
}
