package org.example.grafos;

import java.util.*;

public class Graph<V>{

    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2);
    }

    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice no existe en el grafo");
        }
        return adjacencyList.get(v);
    }


    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V key : adjacencyList.keySet()) {
            sb.append(key).append(" -> ").append(adjacencyList.get(key)).append("\n");
        }
        return sb.toString();
    }

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

    private List<V> reconstruirCamino(Map<V, V> traza, V v1, V v2) {
        List<V> camino = new LinkedList<>();
        for (V v = v2; v != null; v = traza.get(v)) {
            camino.add(0, v);
        }
        return camino;
    }

}
