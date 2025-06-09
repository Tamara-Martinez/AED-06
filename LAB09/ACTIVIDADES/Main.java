package prueba;

import graph.GraphLink;

public class Main {
    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>();

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");
        graph.insertEdge("C", "E");

        System.out.println("Grafo original:");
        System.out.println(graph);

        System.out.println("¿Existe vértice 'C'? " + graph.searchVertex("C"));
        System.out.println("¿Existe arista entre A y C? " + graph.searchEdge("A", "C"));

        graph.dfs("A"); 
        graph.bfs("A"); 

        graph.removeEdge("A", "C");
        System.out.println("\nDespués de eliminar la arista A-C:");
        System.out.println(graph);

        graph.removeVertex("D");
        System.out.println("Después de eliminar el vértice D:");
        System.out.println(graph);
    }
}
