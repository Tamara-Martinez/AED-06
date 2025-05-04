package ACT_LAB05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorDeTareas<T extends Comparable<T>> {
    private ListaEnlazada<T> tareas;
    private List<T> tareasCompletadas;

    public GestorDeTareas() {
        tareas = new ListaEnlazada<>();
        tareasCompletadas = new ArrayList<>();
    }

    public void agregarTarea(T tarea) {
        tareas.insertLast(tarea);
    }

    public boolean eliminarTarea(T tarea) {
        int pos = tareas.search(tarea);
        if (pos != -1) {
            tareas.removeNode(tarea);
            return true;
        }
        return false;
    }

    public boolean contieneTarea(T tarea) {
        return tareas.search(tarea) != -1;
    }

    public void imprimirTareas() {
        tareas.imprimir();
    }

    public int contarTareas() {
        return tareas.length();
    }

    public T obtenerTareaMasPrioritaria() {
        return tareas.obtenerElementoMasPrioritario();
    }

    public void invertirTareas() {
        tareas.invertir();
    }

    public boolean completarTarea(T tarea) {
        if (eliminarTarea(tarea)) {
            tareasCompletadas.add(tarea);
            return true;
        }
        return false;
    }

    public void mostrarTareasCompletadas() {
        System.out.println("Tareas completadas:");
        for (T t : tareasCompletadas) {
            System.out.println(t);
        }
    }

    public void destruirTareas() {
        tareas.destroyList();
    }

    public T ingresarTarea(Scanner sc) {
        System.out.print("Ingresa el título de la tarea: ");
        String titulo = sc.nextLine();
        System.out.print("Ingresa la prioridad de la tarea (un número entero del 1 al 3, donde 1 es la más proritaria): ");
        int prioridad = sc.nextInt();
        sc.nextLine();
        return (T) new Tarea(titulo, prioridad);
    }
}

