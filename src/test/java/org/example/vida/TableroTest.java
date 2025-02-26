package org.example.vida;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {

    @Test
    public void testGenerarEstadoAleatorio() {
        Tablero tablero = new Tablero();
        tablero.generarEstadoActualPorMontecarlo();

        int suma = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                suma += tablero.toString().charAt(i * 31 + j) == 'X' ? 1 : 0;
            }
        }
        assertTrue(suma > 0, "El tablero generado no puede estar completamente vacío.");
    }

    @Test
    public void testTransitarEstado() {
        Tablero tablero = new Tablero();
        tablero.generarEstadoActualPorMontecarlo();
        String estadoInicial = tablero.toString();
        tablero.transitarAlEstadoSiguiente();
        assertNotEquals(estadoInicial, tablero.toString(), "El estado debe cambiar después de una iteración.");
    }


}
