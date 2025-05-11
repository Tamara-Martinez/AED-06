package Actividad1;

import Actividad1.ExceptionIsEmpty;
import Actividad1.Stack;
import Actividad1.StackArray;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la capacidad de la pila: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine(); 

        Stack<String> pila = new StackArray<>(capacidad);

        try {
            for (int i = 0; i < capacidad; i++) {
            	System.out.print("Ingrese el elemento " + (i + 1) + ": ");
                String dato = scanner.nextLine();
                pila.push(dato);
            }

            System.out.println("\nContenido actual de la pila:");
            System.out.println(pila.toString());

            System.out.println("\nElemento en el tope: " + pila.top());

            System.out.println("\nDesea eliminar el elemento del tope? (s/n): ");
            String opcion = scanner.nextLine();

            if (opcion.equalsIgnoreCase("s")) {
                String eliminado = pila.pop();
                System.out.println("Elemento eliminado: " + eliminado);
                System.out.println("Pila actual: " + pila.toString());
            }

        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage()); 
        }

        scanner.close();
    }
}