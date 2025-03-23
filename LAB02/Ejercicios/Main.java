package Ejercicio;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); 

        int opcion;
        boolean salir = false;

        System.out.println("Seleccione el tipo de operación:");
        System.out.println("1. Operaciones con Enteros");
        System.out.println("2. Operaciones con Decimales");

        int tipoOperacion = scanner.nextInt();

        if (tipoOperacion == 1) {
            System.out.println("Ingrese el valor entero:");
            int valor = scanner.nextInt();
            OperacionesMatInteger operador = new OperacionesMatInteger(valor);

            while (!salir) {
                mostrarMenu();
                opcion = scanner.nextInt();
                ejecutarOperacionEnteros(opcion, operador, scanner);
                if (opcion == 8) salir = true;
            }

        } else if (tipoOperacion == 2) {
            System.out.println("Ingrese el valor decimal:");
            double valor = scanner.nextDouble();
            OperacionesMatDouble operador = new OperacionesMatDouble(valor);

            while (!salir) {
                mostrarMenu();
                opcion = scanner.nextInt();
                ejecutarOperacionDobles(opcion, operador, scanner);
                if (opcion == 8) salir = true;
            }

        } else {
            System.out.println("Opción no válida");
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú de Operaciones:");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Producto");
        System.out.println("4. División");
        System.out.println("5. Potencia");
        System.out.println("6. Raíz Cuadrada");
        System.out.println("7. Raíz Cúbica");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void ejecutarOperacionEnteros(int opcion, OperacionesMatInteger operador, Scanner scanner) {
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el otro operando: ");
                System.out.println("Resultado: " + operador.sumar(scanner.nextInt()));
                break;
            case 2:
                System.out.print("Ingrese el otro operando: ");
                System.out.println("Resultado: " + operador.restar(scanner.nextInt()));
                break;
            case 3:
                System.out.print("Ingrese el otro operando: ");
                System.out.println("Resultado: " + operador.multiplicar(scanner.nextInt()));
                break;
            case 4:
                System.out.print("Ingrese el otro operando: ");
                int divisor = scanner.nextInt();
                if (divisor != 0) {
                    System.out.println("Resultado: " + operador.dividir(divisor));
                } else {
                    System.out.println("Error: División entre 0");
                }
                break;
            case 5:
                System.out.print("Ingrese el exponente: ");
                System.out.println("Resultado: " + operador.potencia(scanner.nextInt()));
                break;
            case 6:
                System.out.println("Resultado: " + operador.raizCuadrada());
                break;
            case 7:
                System.out.println("Resultado: " + operador.raizCubica());
                break;
            case 8:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private static void ejecutarOperacionDobles(int opcion, OperacionesMatDouble operador, Scanner scanner) {
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el otro operando: ");
                System.out.println("Resultado: " + operador.sumar(scanner.nextDouble()));
                break;
            case 2:
                System.out.print("Ingrese el otro operando: ");
                System.out.println("Resultado: " + operador.restar(scanner.nextDouble()));
                break;
            case 3:
                System.out.print("Ingrese el otro operando: ");
                System.out.println("Resultado: " + operador.multiplicar(scanner.nextDouble()));
                break;
            case 4:
                System.out.print("Ingrese el otro operando: ");
                double divisor = scanner.nextDouble();
                if (divisor != 0) {
                    System.out.println("Resultado: " + operador.dividir(divisor));
                } else {
                    System.out.println("Error: División entre 0");
                }
                break;
            case 5:
                System.out.print("Ingrese el exponente: ");
                System.out.println("Resultado: " + operador.potencia(scanner.nextDouble()));
                break;
            case 6:
                System.out.println("Resultado: " + operador.raizCuadrada());
                break;
            case 7:
                System.out.println("Resultado: " + operador.raizCubica());
                break;
            case 8:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}
