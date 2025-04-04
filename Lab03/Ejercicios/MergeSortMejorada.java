package LAB03_AED;

public class MergeSortMejorada {
    
    private static final int INSERTION_SORT_THRESHOLD = 10;

    public static void mergeSort(int[] arr) {
        int n = arr.length;
        int[] aux = new int[n]; // Arreglo auxiliar para fusionar sin copiar subarreglos
        mergeSortIterativo(arr, aux);
    }

    private static void mergeSortIterativo(int[] arr, int[] aux) {
        int n = arr.length;
        
        for (int size = 1; size < n; size *= 2) {
            for (int left = 0; left < n - size; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(arr, aux, left, mid, right);
            }
        }
    }
    
    private static void merge(int[] arr, int[] aux, int left, int mid, int right) {
        System.arraycopy(arr, left, aux, left, right - left + 1);
        
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            arr[k++] = (aux[i] <= aux[j]) ? aux[i++] : aux[j++];
        }
        
        while (i <= mid) {
            arr[k++] = aux[i++];
        }
    }
    
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Arreglo original:");
        printArray(arr);
        
        mergeSort(arr);
        
        System.out.println("Arreglo ordenado:");
        printArray(arr);
    }
}
