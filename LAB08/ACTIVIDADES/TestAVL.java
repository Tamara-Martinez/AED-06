package avltree;

import Exceptions.ItemDuplicated;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        int[][] testCases = {
            {30, 20, 10},        // RSR
            {10, 20, 30},        // RSL
            {30, 10, 20},        // RDR
            {10, 30, 20},        // RDL
            {50, 40, 30},        // RSR
            {50, 60, 70},        // RSL
            {70, 50, 60},        // RDR
            {30, 50, 40}         // RDL
        };

        int count = 1;
        for (int[] sequence : testCases) {
            System.out.println("\nCaso de prueba " + count + ":");
            tree = new AVLTree<>();
            for (int val : sequence) {
                try {
                    tree.insert(val);
                    System.out.println("Insertado: " + val);
                } catch (ItemDuplicated e) {
                    System.err.println(e.getMessage());
                }
            }
            System.out.println("Árbol resultante:");
            tree.printTree();
            count++;
        }
    }
}
