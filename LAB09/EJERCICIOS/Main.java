package prueba;

import graph.GraphLinkModificado;
import graph.GraphListEdge;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("----- TEST GraphLinkModificado -----");

        GraphLinkModificado<String> graph = new GraphLinkModificado<>();

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        graph.insertEdgeWeight("A", "B", 4);
        graph.insertEdgeWeight("A", "C", 1);
        graph.insertEdgeWeight("C", "B", 2);
        graph.insertEdgeWeight("B", "E", 4);
        graph.insertEdgeWeight("C", "D", 4);
        graph.insertEdgeWeight("D", "E", 4);

        System.out.println("Grafo:\n" + graph);

        graph.bfs("A");
        graph.dfs("A");

        List<String> path = graph.bfsPath("A", "E");
        System.out.println("Camino BFS entre A y E: " + path);

        int dist = graph.shortPath("A", "E");
        System.out.println("Distancia mínima (sin peso) entre A y E: " + dist);

        System.out.println("¿Es conexo? " + graph.isConexo());

        List<String> dijkstraPath = graph.dijkstra("A", "E");
        System.out.println("Camino mínimo con Dijkstra entre A y E: " + dijkstraPath);

        System.out.println("\n----- TEST GraphListEdge -----");

        GraphListEdge<String, Integer> graphListEdge = new GraphListEdge<>();

        graphListEdge.insertVertex("A");
        graphListEdge.insertVertex("B");
        graphListEdge.insertVertex("C");
        graphListEdge.insertVertex("D");
        graphListEdge.insertVertex("E");

        graphListEdge.insertEdge("A", "B", 4);
        graphListEdge.insertEdge("A", "C", 1);
        graphListEdge.insertEdge("C", "B", 2);
        graphListEdge.insertEdge("B", "E", 4);
        graphListEdge.insertEdge("C", "D", 4);
        graphListEdge.insertEdge("D", "E", 4);

        System.out.println("Grafo GraphListEdge:\n" + graphListEdge);

        graphListEdge.bfs("A");
    }
}
