package ejercicio3;

import Actividad1.ExceptionIsEmpty;
import actividad2.QueueLink;
import actividad3.PriorityQueue;

public class PriorityQueueLinked<E> implements PriorityQueue<E, Integer> {

    private QueueLink<E>[] colaPrioridades;
    private int niveles;

    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int niveles) {
        this.niveles = niveles;
        this.colaPrioridades = new QueueLink[niveles];

        for (int i = 0; i < niveles; i++) {
            colaPrioridades[i] = new QueueLink<>();
        }
    }

    @Override
    public void enqueue(E x, Integer pr) {
        if (pr < 0 || pr >= niveles) {
            throw new IllegalArgumentException("Prioridad fuera de rango.");
        }
        colaPrioridades[pr].enqueue(x);
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = 0; i < niveles; i++) {
            if (!colaPrioridades[i].isEmpty()) {
                return colaPrioridades[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        for (int i = 0; i < niveles; i++) {
            if (!colaPrioridades[i].isEmpty()) {
                return colaPrioridades[i].front();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        for (int i = niveles - 1; i >= 0; i--) {
            if (!colaPrioridades[i].isEmpty()) {
                return colaPrioridades[i].back();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < niveles; i++) {
            if (!colaPrioridades[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Contenido de la cola de prioridad:\n");
        for (int i = 0; i < niveles; i++) {
            sb.append("Prioridad ").append(i).append(": ")
              .append(colaPrioridades[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
