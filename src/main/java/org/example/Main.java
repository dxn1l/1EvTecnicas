package org.example;

import org.example.grafos.Graph;
import org.example.pi.Matematicas;
import org.example.vida.Tablero;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * Clase principal que proporciona un menú interactivo para ejecutar diferentes simulaciones:
 * - Aproximación del número Pi usando el método de Montecarlo.
 * - Simulación del Juego de la Vida de Conway.
 * - Búsqueda de un camino en un grafo dirigido.
 */
public class Main {

    /**
     * Método principal que ejecuta el menú interactivo y permite al usuario seleccionar
     * qué simulación desea ejecutar.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelecciona un programa para ejecutar:");
            System.out.println("1 - Aproximación de Pi");
            System.out.println("2 - Juego de la Vida");
            System.out.println("3 - Búsqueda en Grafos");
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
                    ejecutarBusquedaEnGrafos();
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


    /**
     * Ejecuta la simulación del Juego de la Vida.
     * Se realizan dos simulaciones:
     * 1. Utilizando un tablero leído desde un archivo llamado "matriz".
     * 2. Generando un tablero aleatorio utilizando Montecarlo.
     * Se muestra la evolución del tablero en varias iteraciones.
     */


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

    /**
     * Ejecuta la simulación de búsqueda de un camino en un grafo dirigido.
     * Se crea un grafo con vértices y aristas predefinidos y se busca un camino entre los nodos 1 y 4.
     * Se muestra el grafo y el resultado de la búsqueda.
     */

    private static void ejecutarBusquedaEnGrafos() {
        Graph<Integer> grafo = new Graph<>();
        grafo.addEdge(1, 2);
        grafo.addEdge(3, 4);
        grafo.addEdge(1, 5);
        grafo.addEdge(5, 6);
        grafo.addEdge(6, 4);

        System.out.println("Grafo creado:");
        System.out.println(grafo);

        System.out.println("Buscando camino entre 1 y 4...");
        List<Integer> camino = grafo.onePath(1, 4);

        if (camino != null) {
            System.out.println("Camino encontrado: " + camino);
        } else {
            System.out.println("No se encontró un camino.");
        }
    }
}
