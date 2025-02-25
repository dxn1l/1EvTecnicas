package org.example.pi;

import java.util.Random;

public class Matematicas {

    public static double generarNumeroPi(long pasos) {
        Random random = new Random();
        long aciertos = 0;

        for (long i = 0; i < pasos; i++) {
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;

            if (x * x + y * y <= 1) {
                aciertos++;
            }
        }

        return 4.0 * aciertos / pasos;
    }
}
