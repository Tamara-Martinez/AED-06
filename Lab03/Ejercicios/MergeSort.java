package LAB03_AED;

public class MergeSort {

    // Método principal que ordena el arreglo usando Merge Sort
    public static void mergeSort(int[] arr) {  // O(1) 
        if (arr.length < 2) {  // O(1) 
            return; // O(1) 
        }
        
        // Dividir el arreglo en dos mitades
        int mid = arr.length / 2;  // O(1) 
        
        int[] left = new int[mid];  // O(1) 
        int[] right = new int[arr.length - mid];  // O(1) 

        // Copiar los elementos en los subarreglos
        System.arraycopy(arr, 0, left, 0, mid);  // O(n) 
        System.arraycopy(arr, mid, right, 0, arr.length - mid);  // O(n) 

        // Llamadas recursivas para ordenar las dos mitades
        mergeSort(left);  // O(n log n)
        mergeSort(right);  // O(n log n)

        // Fusionar las dos mitades ordenadas
        merge(arr, left, right);  // O(n) 
    }

    // Método para fusionar dos subarreglos en un arreglo ordenado
    private static void merge(int[] arr, int[] left, int[] right) {  // O(1) 
        int i = 0, j = 0, k = 0;  // O(1) 

        // Fusionar los arreglos left y right en arr[]
        while (i < left.length && j < right.length) {  // O(n) 
            if (left[i] <= right[j]) {  // O(1) 
                arr[k++] = left[i++];  // O(1) 
            } else {
                arr[k++] = right[j++];  // O(1) 
            }
        }

        // Si quedan elementos en left, copiarlos en arr
        while (i < left.length) {  // O(n) 
            arr[k++] = left[i++];  // O(1) 
        }

        // Si quedan elementos en right, copiarlos en arr
        while (j < right.length) {  // O(n)
            arr[k++] = right[j++];  // O(1) 
        }
    }

    // Método para imprimir el arreglo
    public static void printArray(int[] arr) {  // O(1) 
        for (int i = 0; i < arr.length; i++) {  // O(n) 
            System.out.print(arr[i] + " ");  // O(1) 
        }
        System.out.println();  // O(1) - Salto de línea.
    }

    // Método principal para probar el algoritmo
    public static void main(String[] args) {  
        int[] arr = {38, 27, 43, 3, 9, 82, 10};  // O(1) 

        System.out.println("Arreglo original:");
        printArray(arr);  // O(n) 

        mergeSort(arr);  // O(n log n) 

        System.out.println("Arreglo ordenado:");
        printArray(arr);  // O(n)
    }
}
