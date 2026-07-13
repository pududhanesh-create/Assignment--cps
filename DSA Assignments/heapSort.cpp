#include <iostream>
#include <cstdlib>
#include <ctime>
#include <chrono>

using namespace std;
using namespace std::chrono;

// Random Array
void randomArray(int arr[], int n)
{
    for(int i = 0; i < n; i++)
        arr[i] = rand() % (n * 10);
}

// Ascending Array
void ascendingArray(int arr[], int n)
{
    for(int i = 0; i < n; i++)
        arr[i] = i + 1;
}

// Descending Array
void descendingArray(int arr[], int n)
{
    for(int i = 0; i < n; i++)
        arr[i] = n - i;
}

// Partially Sorted Array
void partiallySortedArray(int arr[], int n)
{
    ascendingArray(arr, n);

    for(int i = 0; i < n / 10; i++)
    {
        int a = rand() % n;
        int b = rand() % n;

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

// Mostly Sorted Array
void mostlySortedArray(int arr[], int n)
{
    ascendingArray(arr, n);

    int a = rand() % n;
    int b = rand() % n;

    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}

// Duplicate Array
void duplicateArray(int arr[], int n)
{
    for(int i = 0; i < n; i++)
        arr[i] = rand() % (n / 10 + 1);
}

// Copy Array
void copyArray(int source[], int destination[], int n)
{
    for(int i = 0; i < n; i++)
        destination[i] = source[i];
}

// Heapify
void heapify(int arr[], int n, int i)
{
    int largest = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if(left < n && arr[left] > arr[largest])
    {
        largest = left;
    }

    if(right < n && arr[right] > arr[largest])
    {
        largest = right;
    }

    if(largest != i)
    {
        int temp = arr[i];
        arr[i] = arr[largest];
        arr[largest] = temp;

        heapify(arr, n, largest);
    }
}

// Heap Sort
void heapSort(int arr[], int n)
{
    // Build Max Heap
    for(int i = n / 2 - 1; i >= 0; i--)
    {
        heapify(arr, n, i);
    }

    // Extract elements one by one
    for(int i = n - 1; i > 0; i--)
    {
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;

        heapify(arr, i, 0);
    }
}

// Measure Heap Sort Time
void testHeapSort(string caseName, int original[], int n)
{
    int *arr = new int[n];

    copyArray(original, arr, n);

    auto start = high_resolution_clock::now();

    heapSort(arr, n);

    auto stop = high_resolution_clock::now();

    auto duration = duration_cast<microseconds>(stop - start);

    cout << caseName
         << " | Time = "
         << duration.count()
         << " microseconds"
         << endl;

    delete[] arr;
}

int main()
{
    srand(time(0));

    int sizes[] = {100, 1000, 10000, 100000};

    for(int s = 0; s < 4; s++)
    {
        int n = sizes[s];

        int *random = new int[n];
        int *ascending = new int[n];
        int *descending = new int[n];
        int *partial = new int[n];
        int *mostly = new int[n];
        int *duplicate = new int[n];

        randomArray(random, n);
        ascendingArray(ascending, n);
        descendingArray(descending, n);
        partiallySortedArray(partial, n);
        mostlySortedArray(mostly, n);
        duplicateArray(duplicate, n);

        cout << "\n=====================================\n";
        cout << "ARRAY SIZE = " << n << endl;
        cout << "=====================================\n";

        testHeapSort("Random", random, n);
        testHeapSort("Ascending", ascending, n);
        testHeapSort("Descending", descending, n);
        testHeapSort("Partially Sorted", partial, n);
        testHeapSort("Mostly Sorted", mostly, n);
        testHeapSort("Duplicates", duplicate, n);

        delete[] random;
        delete[] ascending;
        delete[] descending;
        delete[] partial;
        delete[] mostly;
        delete[] duplicate;
    }

    return 0;
}