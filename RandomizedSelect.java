import java.util.Random;
import java.util.Arrays;

public class RandomizedSelect {
    public static int n = 50;
    private static int numberOfSwaps = 0;
    private static int numberOfComps = 0;

    private static int ithsmallest(int arr[], int low, int high, int i) {
        if (low == high) {
            numberOfComps++;
            return arr[low];
        }
        int q = randomizedPartition(arr, low, high);
        int k = q - low + 1;
        if (i == k) {
            numberOfComps++;
            return arr[q];
        }
        if (i < k) {
            numberOfComps++;
            return ithsmallest(arr, low, q - 1, i);
        }
        return ithsmallest(arr, q + 1, high, i - k);
    }

    public static int randomizedPartition(int a[], int low, int high) {
        Random rand = new Random();
        int pivot = rand.nextInt(high - low) + low;

        int temp = a[pivot];
        a[pivot] = a[high];
        a[high] = temp;
        numberOfSwaps++;

        return partition(a, low, high);
    }

    // Standard partition process of QuickSort().  It considers
    // the last element as pivot and moves all smaller element
    // to left of it and greater elements to right. This function
    // is used by randomPartition()
    public static int partition(int a[], int low, int high) {
        int pivot = a[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            int temp;
            if (a[j] <= pivot) {
                i++;
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                numberOfSwaps++;
                numberOfComps++;
            }
            numberOfComps++;
        }
        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        numberOfSwaps++;
        return i + 1;
    }
}
