package Prueba;

import bstreelinklistinterfgeneric.LinkedBST;
import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class Main {

    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            // Insertar elementos en el BST
            bst.insert(50);
            bst.insert(30);
            bst.insert(20);
            bst.insert(40);
            bst.insert(70);
            bst.insert(60);
            bst.insert(80);

            System.out.println("Árbol en InOrder:");
            System.out.println(bst.inOrder());

            System.out.println("Árbol en PreOrder:");
            System.out.println(bst.preOrder());

            System.out.println("Árbol en PostOrder:");
            System.out.println(bst.postOrder());

            System.out.println("Elemento mínimo: " + bst.findMinNode());
            System.out.println("Elemento máximo: " + bst.findMaxNode());

            // Probar el método de búsqueda
            System.out.println("¿Elemento 40 encontrado? " + bst.search(40));
            System.out.println("¿Elemento 25 encontrado? " + bst.search(25));

            // Probar el método de eliminación
            bst.delete(20);
            System.out.println("Después de eliminar el 20:");
            System.out.println(bst.inOrder());

            // Métodos adicionales
            System.out.println("Contando todos los nodos con hijos: " + bst.countAllNodes());
            System.out.println("Contando todos los nodos: " + bst.countNodes());

            // Altura del árbol
            System.out.println("Altura del nodo con valor 30: " + bst.height(30));

            // Área del BST
            System.out.println("Área del BST: " + bst.areaBST());

            // Dibujar el BST
            System.out.println("Dibujo del BST:");
            bst.drawBST();

            // Comparación de área
            LinkedBST<Integer> bst2 = new LinkedBST<>();
            bst2.insert(50);
            bst2.insert(30);
            bst2.insert(20);
            bst2.insert(40);
            bst2.insert(70);
            bst2.insert(60);
            bst2.insert(80);
            System.out.println("¿Los árboles tienen el mismo área? " + bst.sameArea(bst2));

            // Parenthesize el BST
            System.out.println("Árbol con paréntesis:");
            bst.parenthesize();

        } catch (ItemDuplicated | ItemNoFound | ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

