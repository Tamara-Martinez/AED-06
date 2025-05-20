package bstreeInterface;

public interface BinarySearchTree<E> {
    void insert(E data) throws Exceptions.ItemDuplicated;
    boolean search(E data) throws Exceptions.ItemNoFound;
    void delete(E data) throws Exceptions.ExceptionIsEmpty;
    String toString();
    String inOrder();
    String preOrder();
    String postOrder();
    E findMinNode() throws Exceptions.ItemNoFound;
    E findMaxNode() throws Exceptions.ItemNoFound;
}
