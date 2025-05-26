package avltree;

import bst.BSTree;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {
    private boolean heightChanged = false;

    @Override
    public void insert(E x) throws ItemDuplicated {
        root = insertAVL((NodeAVL<E>) root, x);
    }

    private NodeAVL<E> insertAVL(NodeAVL<E> node, E x) throws ItemDuplicated {
        if (node == null) {
            heightChanged = true;
            return new NodeAVL<>(x);
        }

        int cmp = x.compareTo(node.getData());

        if (cmp == 0) {
            throw new ItemDuplicated("Elemento duplicado: " + x);
        } else if (cmp < 0) {
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

    // Método para eliminar
    public void delete(E x) throws ItemNoFound {
        root = deleteAVL((NodeAVL<E>) root, x);
    }

    private NodeAVL<E> deleteAVL(NodeAVL<E> node, E x) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound("Elemento no encontrado: " + x);
        }

        int cmp = x.compareTo(node.getData());

        if (cmp < 0) {
            node.setLeft(deleteAVL(node.getLeftAVL(), x));
            if (heightChanged) {
                node = balanceAfterDeleteRight(node);
            }
        } else if (cmp > 0) {
            node.setRight(deleteAVL(node.getRightAVL(), x));
            if (heightChanged) {
                node = balanceAfterDeleteLeft(node);
            }
        } else {
            // Nodo encontrado
            if (node.getLeftAVL() == null) {
                heightChanged = true;
                return node.getRightAVL();
            } else if (node.getRightAVL() == null) {
                heightChanged = true;
                return node.getLeftAVL();
            } else {
                NodeAVL<E> min = findMin(node.getRightAVL());
                node.setData(min.getData());
                node.setRight(deleteAVL(node.getRightAVL(), min.getData()));
                if (heightChanged) {
                    node = balanceAfterDeleteLeft(node);
                }
            }
        }
        return node;
    }

    private NodeAVL<E> findMin(NodeAVL<E> node) {
        while (node.getLeftAVL() != null) {
            node = node.getLeftAVL();
        }
        return node;
    }

    private NodeAVL<E> balanceAfterDeleteLeft(NodeAVL<E> node) {
        switch (node.bf) {
            case 1:
                node.bf = 0;
                break;
            case 0:
                node.bf = -1;
                heightChanged = false;
                break;
            case -1:
                node = balanceToRight(node);
                if (node.bf == 0) heightChanged = false;
                break;
        }
        return node;
    }

    private NodeAVL<E> balanceAfterDeleteRight(NodeAVL<E> node) {
        switch (node.bf) {
            case -1:
                node.bf = 0;
                break;
            case 0:
                node.bf = 1;
                heightChanged = false;
                break;
            case 1:
                node = balanceToLeft(node);
                if (node.bf == 0) heightChanged = false;
                break;
        }
        return node;
    }

    private NodeAVL<E> balanceToLeft(NodeAVL<E> node) {
        NodeAVL<E> child = node.getRightAVL();
        if (child.bf == 1) { // Rotación Simple Izquierda (RSL)
            node.bf = 0;
            child.bf = 0;
            node = rotateSL(node);
        } else { // Rotación Doble Izquierda (RDL)
            NodeAVL<E> grandChild = child.getLeftAVL();
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

    private NodeAVL<E> balanceToRight(NodeAVL<E> node) {
        NodeAVL<E> child = node.getLeftAVL();
        if (child.bf == -1) { // Rotación Simple Derecha (RSR)
            node.bf = 0;
            child.bf = 0;
            node = rotateSR(node);
        } else { // Rotación Doble Derecha (RDR)
            NodeAVL<E> grandChild = child.getRightAVL();
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

    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        NodeAVL<E> child = node.getRightAVL();
        node.setRight(child.getLeftAVL());
        child.setLeft(node);
        return child;
    }

    private NodeAVL<E> rotateSR(NodeAVL<E> node) {
        NodeAVL<E> child = node.getLeftAVL();
        node.setLeft(child.getRightAVL());
        child.setRight(node);
        return child;
    }

    public void printTree() {
        printTree((NodeAVL<E>) root, 0);
    }

    private void printTree(NodeAVL<E> node, int level) {
        if (node != null) {
            printTree(node.getRightAVL(), level + 1);
            for (int i = 0; i < level; i++) System.out.print("    ");
            System.out.println(node);
            printTree(node.getLeftAVL(), level + 1);
        }
    }

    // Recorrido por amplitud (niveles)
    public void breadthFirstTraversal() {
        int h = height();
        for (int i = 0; i <= h; i++) {
            printLevel((NodeAVL<E>) root, i);
        }
        System.out.println();
    }

    private void printLevel(NodeAVL<E> node, int level) {
        if (node == null) return;
        if (level == 0) {
            System.out.print(node.getData() + " ");
        } else {
            printLevel(node.getLeftAVL(), level - 1);
            printLevel(node.getRightAVL(), level - 1);
        }
    }

    // Recorrido en preorden
    public void preorderTraversal() {
        preorder((NodeAVL<E>) root);
        System.out.println();
    }

    private void preorder(NodeAVL<E> node) {
        if (node == null) return;
        System.out.print(node.getData() + " ");
        preorder(node.getLeftAVL());
        preorder(node.getRightAVL());
    }
}
