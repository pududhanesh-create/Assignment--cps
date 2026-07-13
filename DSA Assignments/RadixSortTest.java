package dsa;

import java.util.Random;

public class RadixSortTest {

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

    // Get Maximum Element
    static int getMax(int[] arr) {

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    // Counting Sort (for Radix Sort)
    static void countingSort(int[] arr, int exp) {

        int n = arr.length;

        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {

            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Radix Sort
    static void radixSort(int[] arr) {

        int max = getMax(arr);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    // Measure Radix Sort Time
    static void testRadixSort(String caseName, int[] original) {

        int[] arr = new int[original.length];

        copyArray(original, arr);

        long start = System.nanoTime();

        radixSort(arr);

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

            testRadixSort("Random", random);
            testRadixSort("Ascending", ascending);
            testRadixSort("Descending", descending);
            testRadixSort("Partially Sorted", partial);
            testRadixSort("Mostly Sorted", mostly);
            testRadixSort("Duplicates", duplicate);
        }
    }
}