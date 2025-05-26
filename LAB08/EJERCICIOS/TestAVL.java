package avltree;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        try {
            System.out.println("=== Inserciones ===");
            int[] insertions = {30, 20, 10, 40, 50, 25};
            for (int val : insertions) {
                tree.insert(val);
                System.out.println("Insertado: " + val);
                tree.printTree();
                System.out.println();
            }

            System.out.print("Recorrido en preorden: ");
            tree.preorderTraversal();

            System.out.print("Recorrido por amplitud: ");
            tree.breadthFirstTraversal();

            System.out.println("\n=== Eliminaciones ===");
            int[] deletions = {10, 50};
            for (int val : deletions) {
                tree.delete(val);
                System.out.println("Eliminado: " + val);
                tree.printTree();
                System.out.println();
            }

            System.out.print("Recorrido en preorden después de eliminar: ");
            tree.preorderTraversal();

            System.out.print("Recorrido por amplitud después de eliminar: ");
            tree.breadthFirstTraversal();

        } catch (ItemDuplicated | ItemNoFound e) {
            System.err.println(e.getMessage());
        }
    }
}
