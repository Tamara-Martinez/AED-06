package EJ_LAB05;

import java.util.ArrayList;
import java.util.List;

public class ListaUtils {

    // 1. Buscar un elemento genérico en una lista
    public static <T> boolean buscarElemento(List<T> lista, T valor) {
        return lista.contains(valor);
    }

    // 2. Invertir una lista genérica
    public static <T> List<T> invertirLista(List<T> lista) {
        List<T> listaInvertida = new ArrayList<>();
        for (int i = lista.size() - 1; i >= 0; i--) {
            listaInvertida.add(lista.get(i));
        }
        return listaInvertida;
    }

    // 3. Insertar un nodo al final
    public static <T> Nodo<T> insertarAlFinal(Nodo<T> cabeza, T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor);
        if (cabeza == null) {
            return nuevoNodo;
        }
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevoNodo;
        return cabeza;
    }

    // 4. Contar los nodos
    public static <T> int contarNodos(Nodo<T> cabeza) {
        int contador = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    // 5. Comparar dos listas
    public static <T> boolean sonIguales(Nodo<T> cabeza1, Nodo<T> cabeza2) {
        Nodo<T> actual1 = cabeza1;
        Nodo<T> actual2 = cabeza2;
        while (actual1 != null && actual2 != null) {
            if (!actual1.valor.equals(actual2.valor)) {
                return false;
            }
            actual1 = actual1.siguiente;
            actual2 = actual2.siguiente;
        }
        return actual1 == null && actual2 == null;
    }

    // 6. Concatenar dos listas
    public static <T> Nodo<T> concatenarListas(Nodo<T> cabeza1, Nodo<T> cabeza2) {
        if (cabeza1 == null) return cabeza2;
        if (cabeza2 == null) return cabeza1;

        Nodo<T> actual = cabeza1;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = cabeza2;
        return cabeza1;
    }

    public static <T> Nodo<T> convertirAListaEnlazada(List<T> lista) {
        Nodo<T> cabeza = null;
        for (T valor : lista) {
            cabeza = insertarAlFinal(cabeza, valor);
        }
        return cabeza;
    }

    public static <T> void imprimirLista(Nodo<T> cabeza) {
        Nodo<T> actual = cabeza;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.valor + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
}
