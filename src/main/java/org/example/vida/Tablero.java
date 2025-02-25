package org.example.vida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Tablero {

    private static final int DIMENSION = 30;
    private int[][] estadoActual;
    private int[][] estadoSiguiente;

    public Tablero(){

        estadoActual = new int[DIMENSION][DIMENSION];
        estadoSiguiente = new int[DIMENSION][DIMENSION];

    }

    public void leerEstadoActual() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/matriz"))) {
            for (int i = 0; i < DIMENSION; i++) {
                String linea = br.readLine();
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = Character.getNumericValue(linea.charAt(j));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void generarEstadoActualPorMontecarlo() {
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = random.nextDouble() < 0.5 ? 1 : 0;
            }
        }
    }

    public void transitarAlEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);

                if (estadoActual[i][j] == 1) {
                    estadoSiguiente[i][j] = (vecinosVivos == 2 || vecinosVivos == 3) ? 1 : 0;
                } else {
                    estadoSiguiente[i][j] = (vecinosVivos == 3) ? 1 : 0;
                }
            }
        }

        int[][] temp = estadoActual;
        estadoActual = estadoSiguiente;
        estadoSiguiente = temp;
    }

    private int contarVecinosVivos(int fila, int columna) {
        int vivos = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int x = fila + i;
                int y = columna + j;
                if (x >= 0 && x < DIMENSION && y >= 0 && y < DIMENSION) {
                    vivos += estadoActual[x][y];
                }
            }
        }
        return vivos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] fila : estadoActual) {
            for (int celda : fila) {
                sb.append(celda == 1 ? "X" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
