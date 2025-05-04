package ACT_LAB05;

public class ListaEnlazada<T> {
    private Node<T> cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    public boolean isEmptyList() {
        return cabeza == null;
    }

    public int length() {
        int count = 0;
        Node<T> actual = cabeza;
        while (actual != null) {
            count++;
            actual = actual.next;
        }
        return count;
    }

    public void destroyList() {
        cabeza = null;
    }

    public int search(T x) {
        int pos = 0;
        Node<T> actual = cabeza;
        while (actual != null) {
            if (actual.data.equals(x)) {
                return pos;
            }
            actual = actual.next;
            pos++;
        }
        return -1;
    }

    public void insertFirst(T x) {
        Node<T> nuevo = new Node<>(x);
        nuevo.next = cabeza;
        cabeza = nuevo;
    }

    public void insertLast(T x) {
        Node<T> nuevo = new Node<>(x);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Node<T> actual = cabeza;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
    }

    public void removeNode(T x) {
        if (cabeza == null) return;

        if (cabeza.data.equals(x)) {
            cabeza = cabeza.next;
            return;
        }

        Node<T> actual = cabeza;
        while (actual.next != null && !actual.next.data.equals(x)) {
            actual = actual.next;
        }

        if (actual.next != null) {
            actual.next = actual.next.next;
        }
    }

    public void imprimir() {
        Node<T> actual = cabeza;
        while (actual != null) {
            System.out.println(actual.data);
            actual = actual.next;
        }
    }

    public void invertir() {
        Node<T> prev = null;
        Node<T> actual = cabeza;
        Node<T> siguiente = null;

        while (actual != null) {
            siguiente = actual.next;
            actual.next = prev;
            prev = actual;
            actual = siguiente;
        }
        cabeza = prev;
    }

    public T obtenerElementoMasPrioritario() {
        if (cabeza == null) return null;
        Node<T> actual = cabeza;
        T max = cabeza.data;

        while (actual != null) {
            if (((Comparable<T>) actual.data).compareTo(max) > 0) {
                max = actual.data;
            }
            actual = actual.next;
        }

        return max;
    }
}

