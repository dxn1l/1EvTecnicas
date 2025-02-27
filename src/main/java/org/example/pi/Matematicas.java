package org.example.pi;

import java.util.Random;

/**
 * Clase que proporciona métodos matemáticos, incluyendo la generación
 * de una aproximación del número Pi utilizando el método de Montecarlo.
 */

public class Matematicas {


    /**
     * Genera una aproximación al número Pi utilizando el método de Montecarlo.
     * Este método genera un número determinado de puntos aleatorios dentro
     * de un cuadrado de lado 2 y cuenta cuántos caen dentro del círculo inscrito.
     * Luego, usa la proporción entre ambos para estimar el valor de Pi.
     *
     * @param pasos Número total de puntos aleatorios a generar en el cuadrado.
     * @return Aproximación del número Pi basada en la proporción de puntos dentro del círculo.
     */

    public static double generarNumeroPi(long pasos) {
        Random random = new Random();
        long aciertos = 0;

        for (long i = 0; i < pasos; i++) {

            // Generar coordenadas aleatorias en el rango [-1,1]
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;

            // Verificar si el punto cae dentro del círculo unitario
            if (x * x + y * y <= 1) {
                aciertos++;
            }
        }

        // Calcular la aproximación de Pi
        return 4.0 * aciertos / pasos;
    }
}
