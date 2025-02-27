package org.example.pi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatematicasTest {


    @Test
    public void testGenerarNumeroPi() {
        double pi = Matematicas.generarNumeroPi(1_000_000);
        assertTrue(pi > 3.0 && pi < 3.2, "El valor aproximado de PI debe estar entre 3.0 y 3.2");
    }

    @Test
    public void testMenosPuntos() {
        double pi = Matematicas.generarNumeroPi(100);
        assertTrue(pi > 2.0 && pi < 4.0, "Incluso con pocos puntos, Pi debe estar en el rango esperado.");
    }
}
