package org.example;

import org.example.pi.Matematicas;
import org.example.vida.Tablero;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelecciona un programa para ejecutar:");
            System.out.println("1 - Aproximación de Pi");
            System.out.println("2 - Juego de la Vida (pendiente)");
            System.out.println("3 - Búsqueda en Grafos (pendiente)");
            System.out.println("0 - Salir");
            System.out.print("Opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el número de puntos para la aproximación de Pi: ");
                    long pasos = scanner.nextLong();
                    double piAproximado = Matematicas.generarNumeroPi(pasos);
                    System.out.println("El número PI aproximado es: " + piAproximado);
                    break;
                case 2:
                    ejecutarJuegoDeLaVida();
                    break;
                case 3:
                    System.out.println("Búsqueda en Grafos aún no implementada.");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }



    private static void ejecutarJuegoDeLaVida() {
        try {
            Tablero tablero = new Tablero();
            System.out.println("===============SIMULACIÓN CON TABLERO LEÍDO DEL ARCHIVO===============\n");
            tablero.leerEstadoActual();
            System.out.println("Estado Inicial:");
            System.out.println(tablero);

            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                tablero.transitarAlEstadoSiguiente();
                System.out.println("Estado en Iteración " + (i + 1) + " (Archivo):");
                System.out.println(tablero);
            }

            System.out.println("================SIMULACIÓN CON TABLERO GENERADO ALEATORIAMENTE==========================\n");
            tablero.generarEstadoActualPorMontecarlo();
            System.out.println("Estado Inicial (Montecarlo):");
            System.out.println(tablero);

            for (int i = 0; i < 15; i++) {
                TimeUnit.SECONDS.sleep(1);
                tablero.transitarAlEstadoSiguiente();
                System.out.println("Estado en Iteración " + (i + 1) + " (Montecarlo):");
                System.out.println(tablero);
            }

        } catch (InterruptedException e) {
            System.out.println("Error en la simulación: " + e.getMessage());
        }
    }
}
