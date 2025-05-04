package ACT_LAB05;

public class Tarea implements Comparable<Tarea> {
    String titulo;
    int propiedad; // Prioridad de la tarea (más alto = más urgente)

    public Tarea(String titulo, int propiedad) {
        this.titulo = titulo;
        this.propiedad = propiedad;
    }

    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(otra.propiedad, this.propiedad);
    }

    @Override
    public String toString() {
        return "Tarea: " + titulo + " | Prioridad: " + propiedad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Tarea tarea) {
            return this.titulo.equals(tarea.titulo);
        }
        return false;
    }
}
