package dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BucketSortTest {

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

    // Bucket Sort
    static void bucketSort(int[] arr) {

        int n = arr.length;

        int maxValue = arr[0];

        for (int i = 1; i < n; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        int bucketCount = 10;

        ArrayList<Integer>[] buckets = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {

            int index = (arr[i] * bucketCount) / (maxValue + 1);

            if (index >= bucketCount) {
                index = bucketCount - 1;
            }

            buckets[index].add(arr[i]);
        }

        int k = 0;

        for (int i = 0; i < bucketCount; i++) {

            Collections.sort(buckets[i]);

            for (int value : buckets[i]) {
                arr[k++] = value;
            }
        }
    }

    // Measure Bucket Sort Time
    static void testBucketSort(String caseName, int[] original) {

        int[] arr = new int[original.length];

        copyArray(original, arr);

        long start = System.nanoTime();

        bucketSort(arr);

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

            testBucketSort("Random", random);
            testBucketSort("Ascending", ascending);
            testBucketSort("Descending", descending);
            testBucketSort("Partially Sorted", partial);
            testBucketSort("Mostly Sorted", mostly);
            testBucketSort("Duplicates", duplicate);
        }
    }
}