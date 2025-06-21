package btree;

import java.util.ArrayList;
import java.util.List;
import Exceptions.ItemDuplicated;
import Model.RegistroEstudiante;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E clave) throws ItemDuplicated {
        up = false;
        E mediana = push(root, clave);

        if (up) {
            BNode<E> nuevaRaiz = new BNode<>(orden);
            nuevaRaiz.count = 1;
            nuevaRaiz.keys.set(0, mediana);
            nuevaRaiz.childs.set(0, root);
            nuevaRaiz.childs.set(1, nDes);
            if (root != null) root.parent = nuevaRaiz;
            if (nDes != null) nDes.parent = nuevaRaiz;
            root = nuevaRaiz;
        }
    }

    private E push(BNode<E> node, E clave) throws ItemDuplicated {
        int[] pos = new int[1];
        E mediana;

        if (node == null) {
            up = true;
            nDes = null;
            return clave;
        }

        boolean found = node.searchNode(clave, pos);
        if (found) throw new ItemDuplicated("Clave duplicada: " + clave);

        mediana = push(node.childs.get(pos[0]), clave);

        if (up) {
            if (node.nodeFull(orden - 1)) {
                mediana = divideNode(node, mediana, pos[0]);
            } else {
                putNode(node, mediana, nDes, pos[0]);
                up = false;
            }
        }

        return mediana;
    }

    private void putNode(BNode<E> node, E clave, BNode<E> rd, int k) {
        for (int i = node.count - 1; i >= k; i--) {
            node.keys.set(i + 1, node.keys.get(i));
            node.childs.set(i + 2, node.childs.get(i + 1));
        }

        node.keys.set(k, clave);
        node.childs.set(k + 1, rd);
        if (rd != null) rd.parent = node;
        node.count++;
    }

    private E divideNode(BNode<E> node, E clave, int k) {
        BNode<E> rd = nDes;
        List<E> tempKeys = new ArrayList<>();
        List<BNode<E>> tempChilds = new ArrayList<>();

        for (int i = 0; i < node.count; i++) tempKeys.add(node.keys.get(i));
        tempKeys.add(k, clave);

        for (int i = 0; i <= node.count; i++) tempChilds.add(node.childs.get(i));
        tempChilds.add(k + 1, rd);

        int mid = tempKeys.size() / 2;
        E mediana = tempKeys.get(mid);

        node.count = 0;
        for (int i = 0; i < orden - 1; i++) node.keys.set(i, null);
        for (int i = 0; i < orden; i++) node.childs.set(i, null);

        for (int i = 0; i < mid; i++) {
            node.keys.set(i, tempKeys.get(i));
            node.childs.set(i, tempChilds.get(i));
            if (tempChilds.get(i) != null) tempChilds.get(i).parent = node;
            node.count++;
        }
        node.childs.set(mid, tempChilds.get(mid));
        if (tempChilds.get(mid) != null) tempChilds.get(mid).parent = node;

        nDes = new BNode<>(orden);
        for (int i = mid + 1, j = 0; i < tempKeys.size(); i++, j++) {
            nDes.keys.set(j, tempKeys.get(i));
            nDes.childs.set(j, tempChilds.get(i));
            if (tempChilds.get(i) != null) tempChilds.get(i).parent = nDes;
            nDes.count++;
        }
        nDes.childs.set(nDes.count, tempChilds.get(tempKeys.size()));
        if (nDes.childs.get(nDes.count) != null)
            nDes.childs.get(nDes.count).parent = nDes;

        return mediana;
    }

    public boolean search(E clave) {
        return searchSupport(root, clave);
    }

    private boolean searchSupport(BNode<E> node, E clave) {
        if (node == null) return false;

        int pos = 0;
        while (pos < node.count && clave.compareTo(node.keys.get(pos)) > 0) pos++;

        if (pos < node.count && clave.compareTo(node.keys.get(pos)) == 0) return true;
        return searchSupport(node.childs.get(pos), clave);
    }

    // Método personalizado para buscar nombre por código
 // En clase BTree
    public String buscarNombre(int codigo) {
        if (!(root != null && root.keys.get(0) instanceof RegistroEstudiante)) {
            throw new IllegalStateException("Este árbol no contiene objetos RegistroEstudiante.");
        }

        RegistroEstudiante claveTemp = new RegistroEstudiante(codigo, "");
        return buscarNombreSupportRegistro(root, claveTemp);
    }

    @SuppressWarnings("unchecked")
    private String buscarNombreSupportRegistro(BNode<E> node, RegistroEstudiante clave) {
        if (node == null) return "No encontrado";

        int pos = 0;
        while (pos < node.count && clave.compareTo((RegistroEstudiante) node.keys.get(pos)) > 0) {
            pos++;
        }

        if (pos < node.count && clave.compareTo((RegistroEstudiante) node.keys.get(pos)) == 0) {
            return ((RegistroEstudiante) node.keys.get(pos)).getNombre();
        } else {
            return buscarNombreSupportRegistro(node.childs.get(pos), clave);
        }
    }



    public void printTree() {
        printSupport(root, 0);
    }

    private void printSupport(BNode<E> node, int level) {
        if (node == null) return;

        System.out.print("Nivel " + level + " - Nodo " + node.idNode + " : ");
        for (int i = 0; i < node.count; i++) {
            System.out.print(node.keys.get(i) + " ");
        }
        System.out.println();

        for (int i = 0; i <= node.count; i++) {
            printSupport(node.childs.get(i), level + 1);
        }
    }
}
