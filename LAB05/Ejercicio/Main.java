package EJ_LAB05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa el número de elementos:");
        int numElementos = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        List<Integer> datos = new ArrayList<>();
        for (int i = 0; i < numElementos; i++) {
            System.out.print("Ingresa el valor del elemento " + (i + 1) + ": ");
            int valor = sc.nextInt();
            datos.add(valor);
        }

        Nodo<Integer> cabeza = ListaUtils.convertirAListaEnlazada(datos);

        while (true) {
            System.out.println("\n¿Qué operación deseas realizar?");
            System.out.println("1. Operaciones en ArrayList");
            System.out.println("2. Operaciones en Lista Enlazada");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    operacionesArrayList(datos);
                    break;
                case 2:
                    operacionesListaEnlazada(cabeza, sc);
                    break;
                case 3:
                    System.out.println("Programa terminado");
                    sc.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private static void operacionesArrayList(List<Integer> lista) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nOperaciones en ArrayList:");
        System.out.println("1. Buscar un elemento");
        System.out.println("2. Invertir la lista");
        System.out.print("Opción: ");
        int operacion = sc.nextInt();

        switch (operacion) {
            case 1:
                System.out.print("Ingresa el valor a buscar: ");
                int valorBuscar = sc.nextInt();
                System.out.println("¿Valor encontrado?: " + ListaUtils.buscarElemento(lista, valorBuscar));
                break;
            case 2:
                List<Integer> invertida = ListaUtils.invertirLista(lista);
                System.out.println("Lista invertida: " + invertida);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private static void operacionesListaEnlazada(Nodo<Integer> cabeza, Scanner sc) {
        System.out.println("\nOperaciones en Lista Enlazada:");
        System.out.println("1. Insertar un nodo al final");
        System.out.println("2. Contar nodos");
        System.out.println("3. Concatenar con otra lista");
        System.out.println("4. Comparar con otra lista");
        System.out.print("Opción: ");
        int operacion = sc.nextInt();

        switch (operacion) {
            case 1:
                System.out.print("Ingresa el valor del nuevo nodo: ");
                int valor = sc.nextInt();
                cabeza = ListaUtils.insertarAlFinal(cabeza, valor);
                ListaUtils.imprimirLista(cabeza);
                break;
            case 2:
                System.out.println("Número de nodos: " + ListaUtils.contarNodos(cabeza));
                break;
            case 3:
                System.out.print("¿Cuántos nodos tendrá la segunda lista? ");
                int n = sc.nextInt();
                Nodo<Integer> otra = null;
                for (int i = 0; i < n; i++) {
                    System.out.print("Valor " + (i + 1) + ": ");
                    otra = ListaUtils.insertarAlFinal(otra, sc.nextInt());
                }
                cabeza = ListaUtils.concatenarListas(cabeza, otra);
                ListaUtils.imprimirLista(cabeza);
                break;
            case 4:
                System.out.print("¿Cuántos nodos tendrá la lista para comparar? ");
                int m = sc.nextInt();
                Nodo<Integer> comparacion = null;
                for (int i = 0; i < m; i++) {
                    System.out.print("Valor " + (i + 1) + ": ");
                    comparacion = ListaUtils.insertarAlFinal(comparacion, sc.nextInt());
                }
                System.out.println("¿Son iguales?: " + ListaUtils.sonIguales(cabeza, comparacion));
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}

