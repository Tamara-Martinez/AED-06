package ejercicio2;

import Actividad1.ExceptionIsEmpty;
import actividad2.Queue;

public class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int first;
    private int last;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
        this.first = 0;
        this.last = 0;
        this.size = 0;
    }

    @Override
    public void enqueue(E x) {
        if (size == capacity) {
            System.out.println("Error: La cola está llena, no se puede agregar más elementos.");
            return;
        }
        array[last] = x; 
        last = (last + 1) % capacity; 
        size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía, no se puede eliminar.");
        }
        E removed = array[first]; 
        first = (first + 1) % capacity; 
        size--;
        return removed;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        return array[first]; 
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        
        int backIndex = (last - 1 + capacity) % capacity;
        return array[backIndex];
    }

    @Override
    public boolean isEmpty() {
        return size == 0; 
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "La cola está vacía.";
        }
        StringBuilder sb = new StringBuilder("Frente -> ");
        int current = first;
        for (int i = 0; i < size; i++) {
            sb.append(array[current]).append(" -> ");
            current = (current + 1) % capacity;
        }
        sb.append("Final");
        return sb.toString();
    }
}

