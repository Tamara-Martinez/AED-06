package ejercicio1;

import Actividad1.ExceptionIsEmpty;
import Actividad1.Stack; 
import actividad3.Node;  

public class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }

    @Override
    public void push(E x) {
        Node<E> nuevo = new Node<>(x);
        nuevo.setNext(top);
        top = nuevo;
    }

    @Override
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía.");
        }
        E dato = top.getData();
        top = top.getNext();
        return dato;
    }

    @Override
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía.");
        }
        return top.getData();
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tope -> ");
        Node<E> current = top;
        while (current != null) {
            sb.append(current.getData()).append(" | ");
            current = current.getNext();
        }
        sb.append("Base");
        return sb.toString();
    }
}
