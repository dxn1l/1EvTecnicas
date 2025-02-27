package org.example.grafos;

import java.util.*;

/**
 * Clase que representa un grafo dirigido utilizando listas de adyacencia.
 * Permite la manipulación de vértices y aristas, así como la búsqueda de caminos entre nodos.
 *
 * @param <V> Tipo de los vértices en el grafo.
 */

public class Graph<V>{

    /**
     * Lista de adyacencia del grafo, donde cada vértice está asociado a un conjunto de vértices adyacentes.
     */

    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade un vértice al grafo.
     *
     * @param v Vértice a añadir.
     * @return {@code true} si el vértice fue agregado, {@code false} si ya existía.
     */

    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    /**
     * Añade una arista entre dos vértices en el grafo.
     * Si los vértices no existen, los añade automáticamente antes de crear la arista.
     *
     * @param v1 Vértice de origen.
     * @param v2 Vértice de destino.
     * @return {@code true} si la arista fue agregada, {@code false} si ya existía.
     */

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2);
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a un vértice dado.
     *
     * @param v Vértice del cual se obtienen los adyacentes.
     * @return Conjunto de vértices adyacentes a {@code v}.
     * @throws Exception Si el vértice no existe en el grafo.
     */

    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice no existe en el grafo");
        }
        return adjacencyList.get(v);
    }

    /**
     * Verifica si un vértice existe en el grafo.
     *
     * @param v Vértice a comprobar.
     * @return {@code true} si el vértice está en el grafo, {@code false} en caso contrario.
     */


    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }


    /**
     * Representación en cadena de la lista de adyacencia del grafo.
     *
     * @return Cadena con la representación del grafo en formato lista de adyacencia.
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V key : adjacencyList.keySet()) {
            sb.append(key).append(" -> ").append(adjacencyList.get(key)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Busca un camino desde un vértice de origen {@code v1} hasta un vértice destino {@code v2}.
     * Utiliza una búsqueda en profundidad (DFS) con una pila y una tabla de traza para reconstruir el camino.
     *
     * @param v1 Vértice origen.
     * @param v2 Vértice destino.
     * @return Lista con la secuencia de vértices desde {@code v1} hasta {@code v2}, o {@code null} si no hay camino.
     */

    public List<V> onePath(V v1, V v2) {
        if (!containsVertex(v1) || !containsVertex(v2)) {
            return null;
        }

        Map<V, V> traza = new HashMap<>();
        Stack<V> abierta = new Stack<>();
        abierta.push(v1);
        traza.put(v1, null);

        while (!abierta.isEmpty()) {
            V v = abierta.pop();
            if (v.equals(v2)) {
                return reconstruirCamino(traza, v1, v2);
            }

            for (V vecino : adjacencyList.get(v)) {
                if (!traza.containsKey(vecino)) {
                    abierta.push(vecino);
                    traza.put(vecino, v);
                }
            }
        }
        return null;
    }

    /**
     * Reconstruye un camino desde el vértice de origen hasta el vértice destino utilizando la tabla de traza.
     *
     * @param traza Mapa que almacena cada vértice junto con su predecesor en la búsqueda.
     * @param v1 Vértice origen.
     * @param v2 Vértice destino.
     * @return Lista con la secuencia de vértices que forman el camino desde {@code v1} hasta {@code v2}.
     */

    private List<V> reconstruirCamino(Map<V, V> traza, V v1, V v2) {
        List<V> camino = new LinkedList<>();
        for (V v = v2; v != null; v = traza.get(v)) {
            camino.add(0, v);
        }
        return camino;
    }

}
