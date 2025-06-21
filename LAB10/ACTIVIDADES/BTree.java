package btree; 

 

import Exceptions.ItemDuplicated; 

import java.util.ArrayList; 

import java.util.List; 

 

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

 

    public void insert(E cl) throws ItemDuplicated { 

        up = false; 

        E mediana = push(this.root, cl); 

        if (up) { 

            BNode<E> pnew = new BNode<>(orden); 

            pnew.count = 1; 

            pnew.keys.set(0, mediana); 

            pnew.childs.set(0, this.root); 

            pnew.childs.set(1, nDes); 

            if (this.root != null) this.root.parent = pnew; 

            if (nDes != null) nDes.parent = pnew; 

            this.root = pnew; 

        } 

    } 

 

    private E push(BNode<E> current, E cl) throws ItemDuplicated { 

        int[] pos = new int[1]; 

        E mediana; 

        if (current == null) { 

            up = true; 

            nDes = null; 

            return cl; 

        } else { 

            boolean found = current.searchNode(cl, pos); 

            if (found) throw new ItemDuplicated("Clave duplicada: " + cl); 

            mediana = push(current.childs.get(pos[0]), cl); 

            if (up) { 

                if (current.nodeFull(orden - 1)) 

                    mediana = divideNode(current, mediana, pos[0]); 

                else { 

                    putNode(current, mediana, nDes, pos[0]); 

                    up = false; 

                } 

            } 

            return mediana; 

        } 

    } 

 

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) { 

        for (int i = current.count - 1; i >= k; i--) { 

            current.keys.set(i + 1, current.keys.get(i)); 

            current.childs.set(i + 2, current.childs.get(i + 1)); 

        } 

        current.keys.set(k, cl); 

        current.childs.set(k + 1, rd); 

        if (rd != null) rd.parent = current; 

        current.count++; 

    } 

 

    private E divideNode(BNode<E> current, E cl, int k) { 

        BNode<E> rd = nDes; 

        List<E> tempKeys = new ArrayList<>(); 

        List<BNode<E>> tempChilds = new ArrayList<>(); 

 

        for (int i = 0; i < current.count; i++) { 

            tempKeys.add(current.keys.get(i)); 

        } 

        tempKeys.add(k, cl); 

 

        for (int i = 0; i <= current.count; i++) { 

            tempChilds.add(current.childs.get(i)); 

        } 

        tempChilds.add(k + 1, rd); 

 

        int posMdna = tempKeys.size() / 2; 

        E mediana = tempKeys.get(posMdna); 

 

        // Limpiar nodo actual 

        for (int i = 0; i < orden - 1; i++) current.keys.set(i, null); 

        for (int i = 0; i < orden; i++) current.childs.set(i, null); 

        current.count = 0; 

 

        for (int i = 0; i < posMdna; i++) { 

            current.keys.set(i, tempKeys.get(i)); 

            current.childs.set(i, tempChilds.get(i)); 

            if (tempChilds.get(i) != null) tempChilds.get(i).parent = current; 

            current.count++; 

        } 

        current.childs.set(posMdna, tempChilds.get(posMdna)); 

        if (tempChilds.get(posMdna) != null) tempChilds.get(posMdna).parent = current; 

 

        nDes = new BNode<>(orden); 

        for (int i = posMdna + 1, j = 0; i < tempKeys.size(); i++, j++) { 

            nDes.keys.set(j, tempKeys.get(i)); 

            nDes.childs.set(j, tempChilds.get(i)); 

            if (tempChilds.get(i) != null) tempChilds.get(i).parent = nDes; 

            nDes.count++; 

        } 

        nDes.childs.set(nDes.count, tempChilds.get(tempKeys.size())); 

        if (nDes.childs.get(nDes.count) != null) nDes.childs.get(nDes.count).parent = nDes; 

 

        return mediana; 

    } 

 

    @Override 

    public String toString() { 

        StringBuilder s = new StringBuilder(); 

        if (isEmpty()) { 

            s.append("El árbol está vacío...\n"); 

        } else { 

            s.append("id nodo | claves nodo     | id padre | id hijos\n"); 

            s.append("--------+------------------+----------+----------------\n"); 

            writeTreeFormatted(this.root, s); 

        } 

        return s.toString(); 

    } 

 

    private void writeTreeFormatted(BNode<E> current, StringBuilder s) { 

        if (current == null) return; 

 

        s.append(String.format("%-8d| ", current.idNode)); 

 

        StringBuilder claves = new StringBuilder(); 

        for (int i = 0; i < current.count; i++) { 

            claves.append(current.keys.get(i)); 

            if (i < current.count - 1) claves.append(", "); 

        } 

        s.append(String.format("%-16s| ", claves.toString())); 

 

        String padre = (current.parent == null) ? "--" : String.valueOf(current.parent.idNode); 

        s.append(String.format("%-9s| ", padre)); 

 

        StringBuilder hijos = new StringBuilder(); 

        boolean tieneHijos = false; 

        for (int i = 0; i <= current.count; i++) { 

            BNode<E> hijo = current.childs.get(i); 

            if (hijo != null) { 

                if (tieneHijos) hijos.append(","); 

                hijos.append(hijo.idNode); 

                tieneHijos = true; 

            } 

        } 

        s.append(tieneHijos ? hijos.toString() : "--").append("\n"); 

 

        for (int i = 0; i <= current.count; i++) { 

            writeTreeFormatted(current.childs.get(i), s); 

        } 

    } 

} 