package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import Exceptions.ExceptionIsEmpty;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    private Node<E> root;

    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node<E> insertRec(Node<E> node, E data) throws ItemDuplicated {
        if (node == null) {
            return new Node<>(data);
        }

        int compare = data.compareTo(node.data);
        if (compare < 0) {
            node.left = insertRec(node.left, data);
        } else if (compare > 0) {
            node.right = insertRec(node.right, data);
        } else {
            throw new ItemDuplicated("El elemento " + data + " ya existe.");
        }

        return node;
    }

    @Override
    public boolean search(E data) throws ItemNoFound {
        return searchRec(root, data);
    }

    private boolean searchRec(Node<E> node, E data) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound("El elemento " + data + " no se encuentra.");
        }

        int compare = data.compareTo(node.data);
        if (compare < 0) {
            return searchRec(node.left, data);
        } else if (compare > 0) {
            return searchRec(node.right, data);
        }
        return true;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        root = deleteRec(root, data);
    }

    private Node<E> deleteRec(Node<E> node, E data) {
        if (node == null) {
            return node;
        }

        int compare = data.compareTo(node.data);
        if (compare < 0) {
            node.left = deleteRec(node.left, data);
        } else if (compare > 0) {
            node.right = deleteRec(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.data = findMinNodeRec(node.right).data;
            node.right = deleteRec(node.right, node.data);
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb, "", "");
        return sb.toString();
    }

    private void toStringRec(Node<E> node, StringBuilder sb, String padding, String pointer) {
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.data);
            sb.append("\n");
            toStringRec(node.left, sb, padding + "│   ", "├── ");
            toStringRec(node.right, sb, padding + "    ", "└── ");
        }
    }

    @Override
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderRec(root, sb);
        return sb.toString();
    }

    private void inOrderRec(Node<E> node, StringBuilder sb) {
        if (node != null) {
            inOrderRec(node.left, sb);
            sb.append(node.data).append(" ");
            inOrderRec(node.right, sb);
        }
    }

    @Override
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderRec(root, sb);
        return sb.toString();
    }

    private void preOrderRec(Node<E> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data).append(" ");
            preOrderRec(node.left, sb);
            preOrderRec(node.right, sb);
        }
    }

    @Override
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderRec(root, sb);
        return sb.toString();
    }

    private void postOrderRec(Node<E> node, StringBuilder sb) {
        if (node != null) {
            postOrderRec(node.left, sb);
            postOrderRec(node.right, sb);
            sb.append(node.data).append(" ");
        }
    }

    @Override
    public E findMinNode() throws ItemNoFound {
        if (root == null) {
            throw new ItemNoFound("El árbol está vacío.");
        }
        return findMinNodeRec(root).data;
    }

    private Node<E> findMinNodeRec(Node<E> node) throws ItemNoFound {
        if (node.left == null) {
            return node;
        }
        return findMinNodeRec(node.left);
    }

    @Override
    public E findMaxNode() throws ItemNoFound {
        if (root == null) {
            throw new ItemNoFound("El árbol está vacío.");
        }
        return findMaxNodeRec(root).data;
    }

    private Node<E> findMaxNodeRec(Node<E> node) throws ItemNoFound {
        if (node.right == null) {
            return node;
        }
        return findMaxNodeRec(node.right);
    }
    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío.");
        }
        root = null;
    }
    public int countAllNodes() {
        return countAllNodesRec(root);
    }

    private int countAllNodesRec(Node<E> node) {
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        return 1 + countAllNodesRec(node.left) + countAllNodesRec(node.right);
    }
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }
    public int height(E x) {
        Node<E> node = findNode(root, x);
        if (node == null) {
            return -1;
        }
        return heightIterative(node);
    }

    private Node<E> findNode(Node<E> node, E x) {
        while (node != null) {
            int compare = x.compareTo(node.data);
            if (compare < 0) {
                node = node.left;
            } else if (compare > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private int heightIterative(Node<E> node) {
        int height = 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                Node<E> current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
                levelSize--;
            }
            height++;
        }
        return height - 1;
    }
    public int amplitude(int nivel) {
        return amplitudeIterative(root, nivel);
    }

    private int amplitudeIterative(Node<E> node, int nivel) {
        if (node == null) {
            return 0;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(node);
        int currentLevel = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            if (currentLevel == nivel) {
                return levelSize;
            }
            while (levelSize > 0) {
                Node<E> current = queue.poll();
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
                levelSize--;
            }
            currentLevel++;
        }
        return 0;
    }
    public int areaBST() {
        int leafCount = countLeaves(root);
        int treeHeight = heightIterative(root);
        return leafCount * treeHeight;
    }

    private int countLeaves(Node<E> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeaves(node.left) + countLeaves(node.right);
    }
    public void drawBST() {
        System.out.println(toString());
    }
    public boolean sameArea(LinkedBST<E> otro) {
        return this.areaBST() == otro.areaBST();
    }
    public void parenthesize() {
        parenthesizeRec(root, 0);
    }

    private void parenthesizeRec(Node<E> node, int level) {
        if (node == null) {
            return;
        }
        String indent = " ".repeat(level * 4);
        System.out.println(indent + "(" + node.data);
        if (node.left != null || node.right != null) {
            if (node.left != null) {
                parenthesizeRec(node.left, level + 1);
            } else {
                System.out.println(indent + "    null");
            }
            if (node.right != null) {
                parenthesizeRec(node.right, level + 1);
            } else {
                System.out.println(indent + "    null");
            }
        }
        System.out.println(indent + ")");
    }

}


