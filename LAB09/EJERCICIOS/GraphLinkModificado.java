package graph;

import linkedlist.ListLinked;

import java.util.*;

public class GraphLinkModificado<E> {

    protected ListLinked<Vertex<E>> listVertex;

    public GraphLinkModificado() {
        listVertex = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            listVertex.add(new Vertex<>(data));
        }
    }

    public void insertEdge(E verOri, E verDes) {
        insertEdge(verOri, verDes, -1);
    }

    public void insertEdgeWeight(E verOri, E verDes, int weight) {
        insertEdge(verOri, verDes, weight);
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

        // Evitar agregar arista duplicada
        if (!vOri.listAdj.contains(new Edge<>(vDes, weight))) {
            vOri.listAdj.add(new Edge<>(vDes, weight));
        }
        if (!vDes.listAdj.contains(new Edge<>(vOri, weight))) {
            vDes.listAdj.add(new Edge<>(vOri, weight));
        }
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

    // 01.a) BFS clásico que muestra los vértices visitados
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

    public ArrayList<E> bfsPath(E v, E z) {
        Vertex<E> start = getVertex(v);
        Vertex<E> end = getVertex(z);
        if (start == null || end == null) return null;

        Map<Vertex<E>, Vertex<E>> parentMap = new HashMap<>();
        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            if (current.equals(end)) break;

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!parentMap.containsKey(end)) return null; 

        ArrayList<E> path = new ArrayList<>();
        Vertex<E> current = end;
        while (current != null) {
            path.add(current.getData());
            current = parentMap.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    public int shortPath(E v, E z) {
        ArrayList<E> path = bfsPath(v, z);
        if (path == null) return -1;
        return path.size() - 1; 
    }

    public boolean isConexo() {
        if (listVertex.toArrayList().isEmpty()) return true;

        Vertex<E> start = listVertex.toArrayList().get(0);

        Set<Vertex<E>> visited = new HashSet<>();
        Queue<Vertex<E>> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<E> current = queue.poll();
            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited.size() == listVertex.toArrayList().size();
    }

    public List<E> dijkstra(E startData, E endData) {
        Vertex<E> start = getVertex(startData);
        Vertex<E> end = getVertex(endData);
        if (start == null || end == null) return null;

        Map<Vertex<E>, Integer> dist = new HashMap<>();
        Map<Vertex<E>, Vertex<E>> prev = new HashMap<>();
        PriorityQueue<Vertex<E>> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (Vertex<E> v : listVertex) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
        }

        dist.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Vertex<E> current = pq.poll();

            if (current.equals(end)) break;

            for (Edge<E> edge : current.listAdj) {
                Vertex<E> neighbor = edge.refDest;
                int weight = edge.weight < 0 ? 1 : edge.weight; 

                int alt = dist.get(current) + weight;
                if (alt < dist.get(neighbor)) {
                    dist.put(neighbor, alt);
                    prev.put(neighbor, current);
                    pq.remove(neighbor);
                    pq.add(neighbor);
                }
            }
        }

        if (dist.get(end) == Integer.MAX_VALUE) return null;

        List<E> path = new ArrayList<>();
        Vertex<E> current = end;
        while (current != null) {
            path.add(current.getData());
            current = prev.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    private Vertex<E> getVertex(E data) {
        for (Vertex<E> v : listVertex) {
            if (v.getData().equals(data)) {
                return v;
            }
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
