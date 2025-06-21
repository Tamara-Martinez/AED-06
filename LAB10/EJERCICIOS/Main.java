package prueba;

import btree.BTree;
import Exceptions.ItemDuplicated;

public class Main {
    public static void main(String[] args) {
        try {
            BTree<Integer> arbol = new BTree<>(5);

            int[] valores = {
                31, 12, 19, 3, 10, 13, 16, 22, 25, 28,
                41, 57, 63, 33, 38, 40, 49, 52, 55, 60,
                62, 67, 70, 72
            };

            for (int v : valores) {
                arbol.insert(v);
            }

            System.out.println("Árbol B original:\n");
            System.out.println(arbol);

            System.out.println("¿Está 52 en el árbol?: " + arbol.search(52));
            System.out.println("¿Está 99 en el árbol?: " + arbol.search(99));

            System.out.println("\nEliminando 12...");
            arbol.remove(12);
            System.out.println(arbol);

            System.out.println("¿Está 12 en el árbol después de eliminar?: " + arbol.search(12));
        } catch (ItemDuplicated e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


