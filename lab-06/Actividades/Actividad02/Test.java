package actividad2;
import Actividad1.ExceptionIsEmpty;
import actividad2.Queue;
import actividad2.QueueLink;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<String> cola = new QueueLink<>();

        try {
            System.out.println("Ingrese cuántos elementos desea agregar a la cola:");
            int n = scanner.nextInt();
            scanner.nextLine(); 

            for (int i = 0; i < n; i++) {
                System.out.print("Ingrese el elemento " + (i + 1) + ": ");
                String dato = scanner.nextLine();
                cola.enqueue(dato);
            }

            System.out.println("\nContenido actual de la cola:");
            System.out.println(cola.toString());

            System.out.println("Elemento en el frente: " + cola.front());
            System.out.println("Elemento en el final: " + cola.back());

            System.out.print("\n¿Desea eliminar el frente? (s/n): ");
            String op = scanner.nextLine();

            if (op.equalsIgnoreCase("s")) {
                String eliminado = cola.dequeue();
                System.out.println("Elemento eliminado: " + eliminado);
                System.out.println("Cola actualizada: " + cola.toString());
            }

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
