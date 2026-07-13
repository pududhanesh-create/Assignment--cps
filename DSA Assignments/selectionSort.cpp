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

// Selection Sort
void selectionSort(int arr[], int n)
{
    for(int i = 0; i < n - 1; i++)
    {
        int minIndex = i;

        for(int j = i + 1; j < n; j++)
        {
            if(arr[j] < arr[minIndex])
            {
                minIndex = j;
            }
        }

        if(minIndex != i)
        {
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}

// Measure Selection Sort Time
void testSelectionSort(string caseName, int original[], int n)
{
    int *arr = new int[n];

    copyArray(original, arr, n);

    auto start = high_resolution_clock::now();

    selectionSort(arr, n);

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

        testSelectionSort("Random", random, n);
        testSelectionSort("Ascending", ascending, n);
        testSelectionSort("Descending", descending, n);
        testSelectionSort("Partially Sorted", partial, n);
        testSelectionSort("Mostly Sorted", mostly, n);
        testSelectionSort("Duplicates", duplicate, n);

        delete[] random;
        delete[] ascending;
        delete[] descending;
        delete[] partial;
        delete[] mostly;
        delete[] duplicate;
    }

    return 0;
}