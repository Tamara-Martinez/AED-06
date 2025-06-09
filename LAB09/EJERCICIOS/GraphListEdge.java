package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GraphListEdge<V, E> {

    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            VertexObj<V, E> vert = new VertexObj<>(v, secVertex.size());
            secVertex.add(vert);
        }
    }

    public void insertEdge(V v1, V v2) {
        insertEdge(v1, v2, null);
    }

    public void insertEdge(V v1, V v2, E info) {
        VertexObj<V, E> vert1 = getVertex(v1);
        VertexObj<V, E> vert2 = getVertex(v2);

        if (vert1 == null) {
            insertVertex(v1);
            vert1 = getVertex(v1);
        }
        if (vert2 == null) {
            insertVertex(v2);
            vert2 = getVertex(v2);
        }

        if (!searchEdge(v1, v2)) {
            EdgeObj<V, E> edge = new EdgeObj<>(vert1, vert2, info, secEdge.size());
            secEdge.add(edge);
        }
    }

    public boolean searchVertex(V v) {
        return getVertex(v) != null;
    }

    public boolean searchEdge(V v1, V v2) {
        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.connects(getVertex(v1), getVertex(v2))) {
                return true;
            }
        }
        return false;
    }

    private VertexObj<V, E> getVertex(V v) {
        for (VertexObj<V, E> vert : secVertex) {
            if (vert.info.equals(v)) {
                return vert;
            }
        }
        return null;
    }

    public void bfs(V start) {
        VertexObj<V, E> startVertex = getVertex(start);
        if (startVertex == null) return;

        HashSet<VertexObj<V, E>> visited = new HashSet<>();
        Queue<VertexObj<V, E>> queue = new LinkedList<>();

        queue.add(startVertex);
        visited.add(startVertex);

        System.out.print("BFS (GraphListEdge): ");

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.print(current.info + " ");

            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;
                if (edge.endVertex1.equals(current)) neighbor = edge.endVertex2;
                else if (edge.endVertex2.equals(current)) neighbor = edge.endVertex1;

                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices:\n");
        for (VertexObj<V, E> v : secVertex) {
            sb.append(v.info).append("\n");
        }
        sb.append("Edges:\n");
        for (EdgeObj<V, E> e : secEdge) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
