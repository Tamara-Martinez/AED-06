package graph;

public class Edge<E> {
    Vertex<E> refDest;
    int weight;

    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge<?>) {
            Edge<?> e = (Edge<?>) o;
            return this.refDest.equals(e.refDest);
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.weight > -1) return refDest.getData() + " [" + this.weight + "]";
        else return refDest.getData().toString();
    }
}
