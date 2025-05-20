package Prueba;

import bstreelinklistinterfgeneric.LinkedBST;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import Exceptions.ExceptionIsEmpty;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear una instancia del árbol binario de búsqueda
            LinkedBST<Integer> bst = new LinkedBST<>();

            // Insertar elementos en el árbol
            bst.insert(50);
            bst.insert(30);
            bst.insert(20);
            bst.insert(40);
            bst.insert(70);
            bst.insert(60);
            bst.insert(80);

            // Imprimir el árbol en orden
            System.out.println("Árbol In-Orden:");
            System.out.println(bst.inOrder());

            // Imprimir el árbol en preorden
            System.out.println("Árbol Pre-Orden:");
            System.out.println(bst.preOrder());

            // Imprimir el árbol en postorden
            System.out.println("Árbol Post-Orden:");
            System.out.println(bst.postOrder());

            // Buscar un elemento existente
            System.out.println("Buscar 40: " + bst.search(40));

            // Intentar buscar un elemento inexistente
            System.out.println("Buscar 100: " + bst.search(100)); // Esto lanzará una excepción

            // Eliminar un elemento
            bst.delete(20);
            System.out.println("Árbol después de eliminar 20:");
            System.out.println(bst.toString());

            // Encontrar el nodo mínimo
            System.out.println("Mínimo: " + bst.findMinNode());

            // Encontrar el nodo máximo
            System.out.println("Máximo: " + bst.findMaxNode());

        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ItemNoFound e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
