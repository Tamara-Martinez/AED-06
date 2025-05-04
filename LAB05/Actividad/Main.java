package ACT_LAB05;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Agregar tarea");
            System.out.println("2. Eliminar tarea");
            System.out.println("3. Imprimir todas las tareas");
            System.out.println("4. Verificar si una tarea existe");
            System.out.println("5. Mostrar tarea más prioritaria");
            System.out.println("6. Invertir tareas");
            System.out.println("7. Completar tarea");
            System.out.println("8. Mostrar tareas completadas");
            System.out.println("9. Salir");

            System.out.print("\nElige una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Tarea tarea = gestor.ingresarTarea(sc);
                    gestor.agregarTarea(tarea);
                    System.out.println("Tarea agregada.");
                    break;

                case 2:
                    System.out.print("Ingresa el título de la tarea a eliminar: ");
                    String tituloEliminar = sc.nextLine();
                    gestor.eliminarTarea(new Tarea(tituloEliminar, 0));
                    System.out.println("Tarea eliminada.");
                    break;

                case 3:
                    System.out.println("\nTareas actuales:");
                    gestor.imprimirTareas();
                    break;

                case 4:
                    System.out.print("Ingresa el título de la tarea a buscar: ");
                    String tituloBuscar = sc.nextLine();
                    boolean existe = gestor.contieneTarea(new Tarea(tituloBuscar, 0));
                    System.out.println(existe ? "Tarea encontrada." : "Tarea no encontrada.");
                    break;

                case 5:
                    Tarea tareaPrioritaria = gestor.obtenerTareaMasPrioritaria();
                    System.out.println("\nTarea más prioritaria: " + tareaPrioritaria);
                    break;

                case 6:
                    gestor.invertirTareas();
                    System.out.println("Tareas invertidas.");
                    break;

                case 7:
                    System.out.print("Ingresa el título de la tarea a completar: ");
                    String tituloCompletar = sc.nextLine();
                    gestor.completarTarea(new Tarea(tituloCompletar, 0));
                    System.out.println("Tarea completada.");
                    break;

                case 8:
                    gestor.mostrarTareasCompletadas();
                    break;

                case 9:
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        sc.close();
        System.out.println("Programa terminado");
    }
}

