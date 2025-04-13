package LAB04_EJ;

public class CosteMinimoEmbarcaderos {

    public static int[][] calcularCostosMinimos(int[][] T) {
        int n = T.length;
        int INF = Integer.MAX_VALUE;
        int[][] C = new int[n][n];

        // Inicializar C con los costos directos
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                C[i][j] = T[i][j];
            }
        }

        // Aplicar programación dinámica (bottom-up)
        for (int l = 2; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                for (int k = i + 1; k < j; k++) {
                    if (T[i][k] != INF && C[k][j] != INF) {
                        C[i][j] = Math.min(C[i][j], T[i][k] + C[k][j]);
                    }
                }
            }
        }

        return C;
    }

    public static void imprimirTabla(int[][] C) {
        int n = C.length;

        System.out.println("Matriz de costos mínimos (C[i][j]):\n");

        // Imprimir encabezados de columnas
        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.printf("%5d", j);
        }
        System.out.println();

        // Imprimir cada fila
        for (int i = 0; i < n; i++) {
            System.out.printf("%3d ", i);
            for (int j = 0; j < n; j++) {
                if (i >= j) {
                    System.out.print("     ");
                } else if (C[i][j] == Integer.MAX_VALUE) {
                    System.out.print("  INF");
                } else {
                    System.out.printf("%5d", C[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int[][] T = {
            {0,   2,   9,  10},
            {INF, 0,   6,   4},
            {INF, INF, 0,   3},
            {INF, INF, INF, 0}
        };

        int[][] C = calcularCostosMinimos(T);
        imprimirTabla(C);
    }
}
