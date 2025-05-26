package avltree;

import bst.Node;

public class NodeAVL<E> extends Node<E> {
    protected int bf;

    public NodeAVL(E data) {
        super(data);
        this.bf = 0;
    }

    public void setLeft(NodeAVL<E> node) {
        this.left = node;
    }

    public void setRight(NodeAVL<E> node) {
        this.right = node;
    }

    public NodeAVL<E> getLeftAVL() {
        return (NodeAVL<E>) left;
    }

    public NodeAVL<E> getRightAVL() {
        return (NodeAVL<E>) right;
    }

    @Override
    public String toString() {
        return "[" + data + ", bf=" + bf + "]";
    }
}