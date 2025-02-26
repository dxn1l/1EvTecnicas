package org.example.grafos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class GraphTest {

    @Test
    public void testAddVertex() {
        Graph<Integer> g = new Graph<>();
        assertTrue(g.addVertex(1));
        assertFalse(g.addVertex(1), "No se deben agregar vértices duplicados.");
    }

    @Test
    public void testAddEdge() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        assertTrue(g.containsVertex(1));
        assertTrue(g.containsVertex(2));
    }

    @Test
    public void testOnePathFindsAPath() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);

        List<Integer> expectedPath = List.of(1, 5, 6, 4);
        assertEquals(expectedPath, g.onePath(1, 4), "El camino encontrado no es el esperado.");
    }

    @Test
    public void testOnePathNoPath() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(3, 4);

        assertNull(g.onePath(1, 4), "No debe encontrarse un camino entre nodos no conectados.");
    }
}
