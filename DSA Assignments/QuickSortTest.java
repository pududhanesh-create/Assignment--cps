package dsa;

import java.util.Random;

public class QuickSortTest {

    static Random rand = new Random();

    // Random Array
    static void randomArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(arr.length * 10);
        }
    }

    // Ascending Array
    static void ascendingArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
    }

    // Descending Array
    static void descendingArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
    }

    // Partially Sorted Array
    static void partiallySortedArray(int[] arr) {
        ascendingArray(arr);

        int n = arr.length;

        for (int i = 0; i < n / 10; i++) {
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);

            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }

    // Mostly Sorted Array
    static void mostlySortedArray(int[] arr) {
        ascendingArray(arr);

        int n = arr.length;

        int a = rand.nextInt(n);
        int b = rand.nextInt(n);

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // Duplicate Array
    static void duplicateArray(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(n / 10 + 1);
        }
    }

    // Copy Array
    static void copyArray(int[] source, int[] destination) {
        System.arraycopy(source, 0, destination, 0, source.length);
    }

    // Partition with Random Pivot
    static int partition(int[] arr, int low, int high) {

        int randomPivot = low + rand.nextInt(high - low + 1);

        int temp = arr[randomPivot];
        arr[randomPivot] = arr[high];
        arr[high] = temp;

        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Quick Sort
    static void quickSort(int[] arr, int low, int high) {

        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Measure Time
    static void testQuickSort(String caseName, int[] original) {

        int[] arr = new int[original.length];

        copyArray(original, arr);

        long start = System.nanoTime();

        quickSort(arr, 0, arr.length - 1);

        long end = System.nanoTime();

        long time = (end - start) / 1000;

        System.out.println(caseName + " | Time = " + time + " microseconds");
    }

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 10000, 100000};

        for (int n : sizes) {

            int[] random = new int[n];
            int[] ascending = new int[n];
            int[] descending = new int[n];
            int[] partial = new int[n];
            int[] mostly = new int[n];
            int[] duplicate = new int[n];

            randomArray(random);
            ascendingArray(ascending);
            descendingArray(descending);
            partiallySortedArray(partial);
            mostlySortedArray(mostly);
            duplicateArray(duplicate);

            System.out.println("\n=================================");
            System.out.println("ARRAY SIZE = " + n);
            System.out.println("=================================");

            testQuickSort("Random", random);
            testQuickSort("Ascending", ascending);
            testQuickSort("Descending", descending);
            testQuickSort("Partially Sorted", partial);
            testQuickSort("Mostly Sorted", mostly);
            testQuickSort("Duplicates", duplicate);
        }
    }
}