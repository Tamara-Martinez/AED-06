package ejercicio2;

import Actividad1.ExceptionIsEmpty;
import actividad2.Queue;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el tamaño máximo de la cola: ");
            int capacidad = Integer.parseInt(scanner.nextLine()); // Leer como línea completa

            Queue<String> cola = new QueueArray<>(capacidad);

            for (int i = 0; i < capacidad; i++) {
                System.out.print("Ingrese el elemento a encolar: ");
                String dato = scanner.nextLine();
                cola.enqueue(dato);
                System.out.println("Elemento encolado: " + dato);
            }

            System.out.println("\nContenido de la cola:");
            System.out.println(cola.toString());

            System.out.println("Elemento en el frente: " + cola.front());
            System.out.println("Elemento en el final: " + cola.back());

            System.out.print("¿Desea agregar otro elemento? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.print("Ingrese un nuevo elemento a encolar: ");
                String nuevoElemento = scanner.nextLine();
                try {
                    cola.enqueue(nuevoElemento);
                    System.out.println("Elemento encolado: " + nuevoElemento);
                } catch (IllegalStateException e) {
                    System.out.println("Error: La cola está llena, no se puede agregar más elementos.");
                }
            }

            System.out.print("¿Desea eliminar el frente? (s/n): ");
            respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                String eliminado = cola.dequeue();
                System.out.println("Elemento eliminado: " + eliminado);
                System.out.println("Cola actualizada:");
                System.out.println(cola.toString());
            }

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}



