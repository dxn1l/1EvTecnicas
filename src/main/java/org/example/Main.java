package org.example;

import org.example.pi.Aproximacion;
import java.util.Scanner;

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
                    double piAproximado = Aproximacion.generarNumeroPi(pasos);
                    System.out.println("El número PI aproximado es: " + piAproximado);
                    break;
                case 2:
                    System.out.println("Juego de la Vida aún no implementado.");
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
}
