package actividad3;

import Actividad1.ExceptionIsEmpty;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {

    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }

        public String toString() {
            return data + "(" + priority + ")";
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void enqueue(E x, N pr) {
        EntryNode newEntry = new EntryNode(x, pr);
        Node<EntryNode> newNode = new Node<>(newEntry);

        if (isEmpty()) {
            first = last = newNode;
        } else if (pr.compareTo(first.getData().priority) > 0) {
         
            newNode.setNext(first);
            first = newNode;
        } else {
           
            Node<EntryNode> current = first;
            Node<EntryNode> prev = null;

            while (current != null && pr.compareTo(current.getData().priority) <= 0) {
                prev = current;
                current = current.getNext();
            }

            newNode.setNext(current);
            if (prev != null) {
                prev.setNext(newNode);
            }

            if (current == null) {
                last = newNode;
            }
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
        }
        E result = first.getData().data;
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        return result;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
        }
        return first.getData().data;
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
        }
        return last.getData().data;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Frente -> ");
        Node<EntryNode> current = first;
        while (current != null) {
            sb.append(current.getData().toString()).append(" -> ");
            current = current.getNext();
        }
        sb.append("null");
        return sb.toString();
    }
}
