package bst;

import Exceptions.ItemDuplicated;

public class BSTree<E extends Comparable<E>> {
    protected Node<E> root;

    public void insert(E x) throws ItemDuplicated {
        root = insert(root, x);
    }

    protected Node<E> insert(Node<E> node, E x) throws ItemDuplicated {
        if (node == null) return new Node<>(x);
        int cmp = x.compareTo(node.getData());
        if (cmp == 0) throw new ItemDuplicated("Elemento duplicado: " + x);
        else if (cmp < 0) node.left = insert(node.getLeft(), x);
        else node.right = insert(node.getRight(), x);
        return node;
    }

    public boolean search(E x) {
        return search(root, x);
    }

    private boolean search(Node<E> node, E x) {
        if (node == null) return false;
        int cmp = x.compareTo(node.getData());
        if (cmp == 0) return true;
        else if (cmp < 0) return search(node.getLeft(), x);
        else return search(node.getRight(), x);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.getLeft(), height(node.getRight())));
    }
}

