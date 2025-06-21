package prueba;

import btree.BTree;
import Model.RegistroEstudiante;
import Exceptions.ItemDuplicated;

public class Main {
    public static void main(String[] args) {
        BTree<RegistroEstudiante> arbol = new BTree<>(4);

        RegistroEstudiante[] estudiantes = {
            new RegistroEstudiante(103, "Ana"),
            new RegistroEstudiante(110, "Luis"),
            new RegistroEstudiante(101, "Carlos"),
            new RegistroEstudiante(120, "Lucía"),
            new RegistroEstudiante(115, "David"),
            new RegistroEstudiante(125, "Jorge"),
            new RegistroEstudiante(140, "Camila"),
            new RegistroEstudiante(108, "Rosa"),
            new RegistroEstudiante(132, "Ernesto"),
            new RegistroEstudiante(128, "Denis"),
            new RegistroEstudiante(145, "Enrique"),
            new RegistroEstudiante(122, "Karina"),
            new RegistroEstudiante(108, "Juan") // duplicado intencional
        };

        System.out.println("Insertando estudiantes:");
        for (RegistroEstudiante est : estudiantes) {
            try {
                arbol.insert(est);
                System.out.println("Insertado: " + est);
            } catch (ItemDuplicated e) {
                System.out.println("Duplicado ignorado: " + est);
            }
        }

        System.out.println("\nVisualización del árbol:");
        arbol.printTree();

        System.out.println("\nBúsquedas:");
        System.out.println("Código 115: " + arbol.buscarNombre(115)); // David
        System.out.println("Código 132: " + arbol.buscarNombre(132)); // Ernesto
        System.out.println("Código 999: " + arbol.buscarNombre(999)); // No encontrado

        System.out.println("\nEliminando estudiante con código 101 (Carlos)...");
        // Aquí podrías implementar un método remove(RegistroEstudiante), pero como aún no lo has hecho,
        // puedes comentarlo o implementarlo luego.

        System.out.println("Insertando nuevo estudiante: (106, Sara)");
        try {
            arbol.insert(new RegistroEstudiante(106, "Sara"));
        } catch (ItemDuplicated e) {
            System.out.println("Duplicado: 106");
        }

        System.out.println("Código 106: " + arbol.buscarNombre(106)); // Sara

        System.out.println("\nVisualización final del árbol:");
        arbol.printTree();
    }
}
