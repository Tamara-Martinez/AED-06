package graph;

import linkedlist.ListLinked;

import java.util.*;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.add(new Vertex<>(data));
        }
    }

    public void insertEdge(E verOri, E verDes) {
        insertEdge(verOri, verDes, -1); // sin peso
    }

    public void insertEdge(E verOri, E verDes, int weight) {
        Vertex<E> vOri = getVertex(verOri);
        Vertex<E> vDes = getVertex(verDes);

        if (vOri == null) {
            vOri = new Vertex<>(verOri);
            listVertex.add(vOri);
        }
        if (vDes == null) {
            vDes = new Vertex<>(verDes);
            listVertex.add(vDes);
        }

        vOri.listAdj.add(new Edge<>(vDes, weight));
        vDes.listAdj.add(new Edge<>(vOri, weight));
    }

    public boolean searchVertex(E data) {
        return getVertex(data) != null;
    }

    public boolean searchEdge(E v, E z) {
        Vertex<E> v1 = getVertex(v);
        Vertex<E> v2 = getVertex(z);

        if (v1 != null && v2 != null) {
            for (Edge<E> edge : v1.listAdj) {
                if (edge.refDest.equals(v2)) return true;
            }
        }
        return false;
    }

    public void removeVertex(E data) {
        Vertex<E> toRemove = getVertex(data);
        if (toRemove == null) return;

        listVertex.remove(toRemove);
        for (Vertex<E> v : listVertex) {
            v.listAdj.remove(new Edge<>(toRemove, -1));
        }
    }

    public void removeEdge(E v, E z) {
        Vertex<E> v1 = getVertex(v);
        Vertex<E> v2 = getVertex(z);

        if (v1 == null || v2 == null) return;

        v1.listAdj.remove(new Edge<>(v2, -1));
        v2.listAdj.remove(new Edge<>(v1, -1));
    }

    public void dfs(E start) {
        Vertex<E> startVertex = getVertex(start);
        if (startVertex == null) return;

        Set<Vertex<E>> visited = new HashSet<>();
        System.out.print("DFS: ");
        dfsRecursive(startVertex, visited);
        System.out.println();
    }

    private void dfsRecursive(Vertex<E> v, Set<Vertex<E>> visited) {
        System.out.print(v.getData() + " ");
        visited.add(v);

        for (Edge<E> edge : v.listAdj) {
            Vertex<E> neighbor = edge.refDest;
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public void bfs(E start) {
        Vertex<E> startVertex = getVertex(start);
        if (startVertex == null) return;

        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex);

        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            System.out.print(current.getData() + " ");

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    private Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) return v;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<E> v : listVertex) {
            sb.append(v.toString());
        }
        return sb.toString();
    }
}
