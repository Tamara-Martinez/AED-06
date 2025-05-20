package bstreeInterface;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import Exceptions.ExceptionIsEmpty;

public interface BinarySearchTree<E extends Comparable<E>> {
    void insert(E data) throws ItemDuplicated;
    boolean search(E data) throws ItemNoFound;
    void delete(E data) throws ItemNoFound, ExceptionIsEmpty; // Agrega las excepciones
    String toString();
    String inOrder();
    String preOrder();
    String postOrder();
    E findMinNode() throws ItemNoFound;
    E findMaxNode() throws ItemNoFound;
}
