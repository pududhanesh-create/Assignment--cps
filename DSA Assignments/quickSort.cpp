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

// Partition Function
int partition(int arr[], int low, int high)
{
    int pivot = arr[high];
    int i = low - 1;

    for(int j = low; j < high; j++)
    {
        if(arr[j] < pivot)
        {
            i++;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
}

// Quick Sort
void quickSort(int arr[], int low, int high)
{
    if(low < high)
    {
        int pi = partition(arr, low, high);

        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

// Measure Quick Sort Time
void testQuickSort(string caseName, int original[], int n)
{
    int *arr = new int[n];

    copyArray(original, arr, n);

    auto start = high_resolution_clock::now();

    quickSort(arr, 0, n - 1);

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

        testQuickSort("Random", random, n);
        testQuickSort("Ascending", ascending, n);
        testQuickSort("Descending", descending, n);
        testQuickSort("Partially Sorted", partial, n);
        testQuickSort("Mostly Sorted", mostly, n);
        testQuickSort("Duplicates", duplicate, n);

        delete[] random;
        delete[] ascending;
        delete[] descending;
        delete[] partial;
        delete[] mostly;
        delete[] duplicate;
    }

    return 0;
}