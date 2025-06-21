package btree;

import Exceptions.ItemDuplicated;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.util.function.Function;

import Exceptions.ItemNoFound;

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

            if (!nodosDesdeArchivo.contains(pnew)) {
                nodosDesdeArchivo.add(pnew);
            }
        }

        if (this.root != null && !nodosDesdeArchivo.contains(this.root)) {
            nodosDesdeArchivo.add(this.root);
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

        if (rd != null && !nodosDesdeArchivo.contains(rd)) {
            nodosDesdeArchivo.add(rd);
        }
        if (!nodosDesdeArchivo.contains(current)) {
            nodosDesdeArchivo.add(current);
        }
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

        // Añadir nodos a la lista si no están
        if (!nodosDesdeArchivo.contains(current)) {
            nodosDesdeArchivo.add(current);
        }
        if (!nodosDesdeArchivo.contains(nDes)) {
            nodosDesdeArchivo.add(nDes);
        }

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
            if (!nodosDesdeArchivo.isEmpty()) {
                for (BNode<E> node : nodosDesdeArchivo) {
                    s.append(formatoNodo(node));
                }
            } else {
                writeTreeFormatted(root, s);  // recorre el árbol desde la raíz
            }
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
    public boolean search(E cl) {
        return searchSupport(root, cl);
    }

    private boolean searchSupport(BNode<E> current, E cl) {
        if (current == null) return false;

        int pos = 0;
        while (pos < current.count && cl.compareTo(current.keys.get(pos)) > 0) {
            pos++;
        }

        if (pos < current.count && cl.compareTo(current.keys.get(pos)) == 0) {
            System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + pos);
            return true;
        } else {
            return searchSupport(current.childs.get(pos), cl);
        }
    }
    public void remove(E cl) {
        if (isEmpty()) return;
        removeRecursive(root, cl);

        if (root.count == 0) {
            if (root.childs.get(0) != null) {
                root = root.childs.get(0);
                root.parent = null;
            } else {
                root = null;
            }
        }
    }

    private void removeRecursive(BNode<E> node, E cl) {
        int pos = 0;
        while (pos < node.count && cl.compareTo(node.keys.get(pos)) > 0) pos++;

        if (pos < node.count && cl.compareTo(node.keys.get(pos)) == 0) {
            if (node.childs.get(pos) != null) {
                BNode<E> predNode = node.childs.get(pos);
                while (predNode.childs.get(predNode.count) != null)
                    predNode = predNode.childs.get(predNode.count);
                E predKey = predNode.keys.get(predNode.count - 1);
                node.keys.set(pos, predKey);
                removeRecursive(node.childs.get(pos), predKey);
            } else {
                for (int i = pos; i < node.count - 1; i++)
                    node.keys.set(i, node.keys.get(i + 1));
                node.keys.set(node.count - 1, null);
                node.count--;
            }
        } else {
            BNode<E> child = node.childs.get(pos);
            if (child == null) return;

            if (child.count == (orden - 1) / 2) {
                fixChild(node, pos);
            }

            removeRecursive(node.childs.get(pos), cl);
        }
    }

    private void fixChild(BNode<E> parent, int pos) {
        BNode<E> child = parent.childs.get(pos);

        if (pos > 0 && parent.childs.get(pos - 1).count > (orden - 1) / 2) {
            BNode<E> left = parent.childs.get(pos - 1);
            for (int i = child.count; i > 0; i--) {
                child.keys.set(i, child.keys.get(i - 1));
                child.childs.set(i + 1, child.childs.get(i));
            }
            child.childs.set(1, child.childs.get(0));
            child.keys.set(0, parent.keys.get(pos - 1));
            child.childs.set(0, left.childs.get(left.count));
            if (child.childs.get(0) != null) child.childs.get(0).parent = child;
            child.count++;

            parent.keys.set(pos - 1, left.keys.get(left.count - 1));
            left.count--;
        }

        else if (pos < parent.count && parent.childs.get(pos + 1).count > (orden - 1) / 2) {
            BNode<E> right = parent.childs.get(pos + 1);
            child.keys.set(child.count, parent.keys.get(pos));
            child.childs.set(child.count + 1, right.childs.get(0));
            if (child.childs.get(child.count + 1) != null) child.childs.get(child.count + 1).parent = child;
            child.count++;

            parent.keys.set(pos, right.keys.get(0));
            for (int i = 0; i < right.count - 1; i++) {
                right.keys.set(i, right.keys.get(i + 1));
                right.childs.set(i, right.childs.get(i + 1));
            }
            right.childs.set(right.count - 1, right.childs.get(right.count));
            right.count--;
        }

        else {
            if (pos < parent.count) {
                merge(parent, pos);
            } else {
                merge(parent, pos - 1);
            }
        }
    }

    private void merge(BNode<E> parent, int pos) {
        BNode<E> left = parent.childs.get(pos);
        BNode<E> right = parent.childs.get(pos + 1);

        left.keys.set(left.count, parent.keys.get(pos));
        left.count++;

        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count + i, right.keys.get(i));
            left.childs.set(left.count + i, right.childs.get(i));
            if (left.childs.get(left.count + i) != null)
                left.childs.get(left.count + i).parent = left;
        }
        left.childs.set(left.count + right.count, right.childs.get(right.count));
        if (left.childs.get(left.count + right.count) != null)
            left.childs.get(left.count + right.count).parent = left;

        left.count += right.count;

        for (int i = pos; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.count--;
    }
    public static BTree<Integer> building_Btree(String filename) throws ItemNoFound {
        BTree<Integer> arbol = null;
        List<BNode<Integer>> nodosEnOrden = new ArrayList<>();
        Map<Integer, BNode<Integer>> mapaNodos = new HashMap<>();
        Map<Integer, Integer> niveles = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int orden = Integer.parseInt(br.readLine().trim());
            arbol = new BTree<>(orden);

            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",", 3);
                int nivel = Integer.parseInt(partes[0].trim());
                int id = Integer.parseInt(partes[1].trim());

                BNode<Integer> nodo = new BNode<>(orden);
                BNode.setIdNodeManually(nodo, id); 
                niveles.put(id, nivel);

                String[] claves = partes[2].split(",");
                int count = 0;
                for (String claveStr : claves) {
                    Integer clave = Integer.parseInt(claveStr.trim());
                    nodo.keys.set(count, clave);
                    count++;
                }
                nodo.count = count;

                mapaNodos.put(id, nodo);
                nodosEnOrden.add(nodo);
            }

            for (BNode<Integer> padre : nodosEnOrden) {
                int nivelPadre = niveles.get(padre.idNode);
                int hijosEsperados = padre.count + 1;
                int encontrados = 0;

                for (BNode<Integer> posibleHijo : nodosEnOrden) {
                    if (niveles.get(posibleHijo.idNode) == nivelPadre + 1 && posibleHijo.parent == null) {
                        padre.childs.set(encontrados, posibleHijo);
                        posibleHijo.parent = padre;
                        encontrados++;
                        if (encontrados == hijosEsperados) break;
                    }
                }
            }
            for (BNode<Integer> n : nodosEnOrden) {
                if (niveles.get(n.idNode) == 0) {
                    arbol.root = n;
                    break;
                }
            }
            arbol.setNodosDesdeArchivo(nodosEnOrden);
            return arbol;

        } catch (IOException | NumberFormatException e) {
            throw new ItemNoFound("Error al construir árbol desde archivo: " + e.getMessage());
        }
    }

    private List<BNode<E>> nodosDesdeArchivo = new ArrayList<>();

    public void setNodosDesdeArchivo(List<BNode<E>> lista) {
        this.nodosDesdeArchivo = lista;
    }
    private String formatoNodo(BNode<E> current) {
        StringBuilder s = new StringBuilder();
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

        return s.toString();
    }
}

    