package LAB04_EJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubconjuntoSumaObjetivo {
    
    public static boolean resolver(int[] arreglo, int objetivo) {
        List<Integer> numerosSeleccionados = new ArrayList<>();
        for (int i = 0; i < arreglo.length; i++) {
            if (esPotenciaDe2(arreglo[i])) {
                System.out.println("Incluyendo potencia de 2: " + arreglo[i]);
                numerosSeleccionados.add(arreglo[i]);
            } else if (puedeIncluirse(arreglo, arreglo[i], i)) {
                System.out.println("Incluyendo número válido: " + arreglo[i]);
                numerosSeleccionados.add(arreglo[i]);
            } else {
                System.out.println("Excluyendo número: " + arreglo[i]);
            }
        }
        int[] seleccionados = new int[numerosSeleccionados.size()];
        for (int i = 0; i < seleccionados.length; i++) {
            seleccionados[i] = numerosSeleccionados.get(i);
        }
        boolean resultado = sumaSubconjunto(seleccionados, objetivo);
        System.out.println("Resultado de suma de subconjunto: " + resultado);
        return resultado;
    }

    public static boolean esPotenciaDe2(int num) {
        return (num > 0) && (num & (num - 1)) == 0;
    }

    public static boolean puedeIncluirse(int[] arreglo, int num, int indice) {
        if (num % 5 == 0) {
            if (indice + 1 < arreglo.length && arreglo[indice + 1] % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean sumaSubconjunto(int[] arreglo, int objetivo) {
        boolean[] dp = new boolean[objetivo + 1];
        dp[0] = true;
        for (int num : arreglo) {
            for (int i = objetivo; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[objetivo];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la entrada: ");
        String[] entrada = scanner.nextLine().split(" ");
        if (entrada.length < 2) {
            System.out.println("Entrada no válida. Se espera al menos un número y un objetivo.");
            scanner.close();
            return;
        }
        int N = Integer.parseInt(entrada[0]);
        if (entrada.length != N + 2) {
            System.out.println("Entrada incorrecta: Se esperaba " + (N + 1) + " números y un objetivo.");
            scanner.close();
            return;
        }
        int[] arreglo = new int[N];
        for (int i = 0; i < N; i++) {
            arreglo[i] = Integer.parseInt(entrada[i + 1]);
        }
        int objetivo = Integer.parseInt(entrada[N + 1]);
        boolean exito = resolver(arreglo, objetivo);
        System.out.println(exito ? "true" : "false");
        scanner.close();
    }
}