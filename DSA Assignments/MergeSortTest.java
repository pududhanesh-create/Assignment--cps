package dsa;

import java.util.Random;

public class MergeSortTest {

    static Random rand = new Random();

    // Random Array
    static void randomArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(n * 10);
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
        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
    }

    // Merge Function
    static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }

        for (int i = 0; i < n2; i++) {
            R[i] = arr[mid + 1 + i];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }

            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Merge Sort
    static void mergeSort(int[] arr, int left, int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    // Measure Merge Sort Time
    static void testMergeSort(String caseName, int[] original) {

        int[] arr = new int[original.length];

        copyArray(original, arr);

        long start = System.nanoTime();

        mergeSort(arr, 0, arr.length - 1);

        long end = System.nanoTime();

        long time = (end - start) / 1000; // microseconds

        System.out.println(caseName +
                " | Time = " +
                time + " microseconds");
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

            System.out.println("\n=====================================");
            System.out.println("ARRAY SIZE = " + n);
            System.out.println("=====================================");

            testMergeSort("Random", random);
            testMergeSort("Ascending", ascending);
            testMergeSort("Descending", descending);
            testMergeSort("Partially Sorted", partial);
            testMergeSort("Mostly Sorted", mostly);
            testMergeSort("Duplicates", duplicate);
        }
    }
}