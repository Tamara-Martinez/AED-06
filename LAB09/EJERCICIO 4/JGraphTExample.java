package org.ejemplo;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

public class JGraphTExample {
    public static void main(String[] args) {
        Graph<String, DefaultEdge> graph = new SimpleDirectedGraph<>(DefaultEdge.class);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        System.out.println("Vértices: " + graph.vertexSet());
        System.out.println("Aristas: " + graph.edgeSet());

        DijkstraShortestPath<String, DefaultEdge> dijkstra = new DijkstraShortestPath<>(graph);
        System.out.println("Camino más corto de A a D: " + dijkstra.getPath("A", "D").getVertexList());
    }
}



