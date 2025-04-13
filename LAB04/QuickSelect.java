package LAB04_EJ;

public class QuickSelect {

    public static int quickSelect(int[] arr, int k) {
        return quickSelectHelper(arr, 0, arr.length - 1, k - 1); 
    }

    private static int quickSelectHelper(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left]; 

        int pivotIndex = partition(arr, left, right); 

        if (k == pivotIndex) {
            return arr[k]; 
        } else if (k < pivotIndex) {
            return quickSelectHelper(arr, left, pivotIndex - 1, k); 
        } else {
            return quickSelectHelper(arr, pivotIndex + 1, right, k);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {9,2,7,1,7};
        int k = 4;

        int resultado = quickSelect(arr, k);
        System.out.println("El " + k + " elemento más pequeño en el arreglo es: " + resultado);
    }
}
