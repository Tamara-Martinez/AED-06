package ejercicio1;
import Actividad1.ExceptionIsEmpty;
import Actividad1.Stack;
import ejercicio1.StackLink;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Stack<String> pila = new StackLink<>();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("¿Cuántos elementos desea apilar?: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); 

            for (int i = 0; i < cantidad; i++) {
                System.out.print("Elemento " + (i + 1) + ": ");
                String elemento = scanner.nextLine();
                pila.push(elemento);
            }

            System.out.println("\nContenido actual de la pila:");
            System.out.println(pila.toString());

            System.out.println("Elemento en el tope: " + pila.top());

            System.out.print("¿Desea eliminar el tope? (s/n): ");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("s")) {
                System.out.println("Elemento eliminado: " + pila.pop());
                System.out.println("Pila después del pop:");
                System.out.println(pila.toString());
            }

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
