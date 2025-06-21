package btree;

import java.util.ArrayList;
import java.util.List;

public class BNode<E extends Comparable<E>> {
    public int count;                      // Cantidad de claves en el nodo
    public List<E> keys;                   // Claves almacenadas
    public List<BNode<E>> childs;          // Hijos del nodo
    public BNode<E> parent;                // Nodo padre
    public int idNode;                     // ID del nodo (para impresión y depuración)

    private static int globalId = 1;       // Contador global de IDs de nodo

    public BNode(int orden) {
        keys = new ArrayList<>(orden - 1);
        childs = new ArrayList<>(orden);

        // Inicializar con valores nulos
        for (int i = 0; i < orden - 1; i++) {
            keys.add(null);
        }
        for (int i = 0; i < orden; i++) {
            childs.add(null);
        }

        count = 0;
        idNode = globalId++;               // Asignar ID único
        parent = null;
    }

    /**
     * Verifica si el nodo está lleno (es decir, contiene el número máximo de claves permitidas).
     */
    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    /**
     * Busca una clave dentro del nodo y devuelve su posición.
     * @param key Clave a buscar.
     * @param pos Arreglo de un elemento donde se devolverá la posición encontrada o de inserción.
     * @return true si la clave existe, false si no existe (pero pos[0] contiene la posición donde insertarla).
     */
    public boolean searchNode(E key, int[] pos) {
        pos[0] = 0;
        while (pos[0] < count && key.compareTo(keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        return pos[0] < count && key.compareTo(keys.get(pos[0])) == 0;
    }

    /**
     * Permite asignar manualmente un ID al nodo (si cargas desde archivo o necesitas mantener IDs fijos).
     */
    public static void setIdNodeManually(BNode<?> node, int id) {
        node.idNode = id;
        if (id >= globalId) {
            globalId = id + 1;
        }
    }

    /**
     * Método útil para depurar o imprimir nodos.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodo ").append(idNode).append(" [ ");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append(" ]");
        return sb.toString();
    }
}
