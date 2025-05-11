package actividad3;
import Actividad1.ExceptionIsEmpty;
import actividad3.PriorityQueue;
import actividad3.PriorityQueueLinkSort;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<String, Integer> cola = new PriorityQueueLinkSort<>();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("¿Cuántos elementos desea ingresar?: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); 

            for (int i = 0; i < cantidad; i++) {
                System.out.print("Ingrese el elemento " + (i + 1) + ": ");
                String dato = scanner.nextLine();
                System.out.print("Ingrese su prioridad (entero): ");
                int prioridad = scanner.nextInt();
                scanner.nextLine(); 
                cola.enqueue(dato, prioridad);
            }

            System.out.println("\nContenido de la cola de prioridad:");
            System.out.println(cola.toString());

            System.out.println("Elemento en el frente: " + cola.front());
            System.out.println("Elemento en el final: " + cola.back());

            System.out.print("¿Desea eliminar el frente? (s/n): ");
            String respuesta = scanner.nextLine();
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
