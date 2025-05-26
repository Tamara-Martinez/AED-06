package avltree;

import bst.BSTree;
import Exceptions.ItemDuplicated;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {
    private boolean heightChanged = false;

    @Override
    public void insert(E x) throws ItemDuplicated {
        root = insertAVL((NodeAVL) root, x);
    }

    private NodeAVL insertAVL(NodeAVL node, E x) throws ItemDuplicated {
        if (node == null) {
            heightChanged = true;
            return new NodeAVL(x);
        }

        int cmp = x.compareTo((E) node.getData());

        if (cmp == 0) throw new ItemDuplicated("Elemento duplicado: " + x);

        if (cmp < 0) {
            node.setLeft(insertAVL(node.getLeftAVL(), x));
            if (heightChanged) {
                switch (node.bf) {
                    case 1:
                        node.bf = 0;
                        heightChanged = false;
                        break;
                    case 0:
                        node.bf = -1;
                        break;
                    case -1:
                        node = balanceToRight(node);
                        heightChanged = false;
                        break;
                }
            }
        } else {
            node.setRight(insertAVL(node.getRightAVL(), x));
            if (heightChanged) {
                switch (node.bf) {
                    case -1:
                        node.bf = 0;
                        heightChanged = false;
                        break;
                    case 0:
                        node.bf = 1;
                        break;
                    case 1:
                        node = balanceToLeft(node);
                        heightChanged = false;
                        break;
                }
            }
        }

        return node;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL child = node.getRightAVL();
        if (child.bf == 1) {
            node.bf = 0;
            child.bf = 0;
            node = rotateSL(node);
        } else {
            NodeAVL grandChild = child.getLeftAVL();
            switch (grandChild.bf) {
                case -1:
                    node.bf = 0;
                    child.bf = 1;
                    break;
                case 0:
                    node.bf = 0;
                    child.bf = 0;
                    break;
                case 1:
                    node.bf = 1;
                    child.bf = 0;
                    break;
            }
            grandChild.bf = 0;
            node.setRight(rotateSR(child));
            node = rotateSL(node);
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL child = node.getLeftAVL();
        if (child.bf == -1) {
            node.bf = 0;
            child.bf = 0;
            node = rotateSR(node);
        } else {
            NodeAVL grandChild = child.getRightAVL();
            switch (grandChild.bf) {
                case 1:
                    node.bf = 0;
                    child.bf = -1;
                    break;
                case 0:
                    node.bf = 0;
                    child.bf = 0;
                    break;
                case -1:
                    node.bf = -1;
                    child.bf = 0;
                    break;
            }
            grandChild.bf = 0;
            node.setLeft(rotateSL(child));
            node = rotateSR(node);
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL child = node.getRightAVL();
        node.setRight(child.getLeftAVL());
        child.setLeft(node);
        return child;
    }

    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL child = node.getLeftAVL();
        node.setLeft(child.getRightAVL());
        child.setRight(node);
        return child;
    }

    public void printTree() {
        printTree((NodeAVL) root, 0);
    }

    private void printTree(NodeAVL node, int level) {
        if (node != null) {
            printTree(node.getRightAVL(), level + 1);
            for (int i = 0; i < level; i++) System.out.print("    ");
            System.out.println(node);
            printTree(node.getLeftAVL(), level + 1);
        }
    }
}
