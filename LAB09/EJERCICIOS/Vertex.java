package graph;

import linkedlist.ListLinked;

public class Vertex<E> {
    private E data;
    protected ListLinked<Edge<E>> listAdj;

    public Vertex(E data) {
        this.data = data;
        this.listAdj = new ListLinked<>();
    }

    public E getData() {
        return data;
    }

    public int getDegree() {
        return listAdj.toArrayList().size();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<?> v = (Vertex<?>) o;
            return this.data.equals(v.data);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.data + " --> " + listAdj.toString() + "\n";
    }
}
