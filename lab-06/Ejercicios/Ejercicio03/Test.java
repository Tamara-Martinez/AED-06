package ejercicio3;

import Actividad1.ExceptionIsEmpty;
import actividad3.PriorityQueue;
import ejercicio3.PriorityQueueLinked;

public class Test {
    public static void main(String[] args) {
        
        PriorityQueue<String, Integer> cola = new PriorityQueueLinked<>(3);

        cola.enqueue("Tarea urgente", 0);
        cola.enqueue("Tarea importante", 1);
        cola.enqueue("Tarea opcional", 2);
        cola.enqueue("Tarea crítica", 0);
        cola.enqueue("Tarea rutinaria", 2);

        System.out.println(cola.toString());

        try {
            System.out.println("Frente: " + cola.front());

            System.out.println("Final: " + cola.back());

            System.out.println("Eliminado: " + cola.dequeue());
            
            System.out.println(cola.toString());
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
