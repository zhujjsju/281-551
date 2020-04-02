import java.util.Arrays;
import java.util.Random;

class MedianOfMedians {

    public static int n = 50;
    private static int numberOfSwaps = 0;
    private static int numberOfComps = 0;

    public static void swap(int[] arr, int i, int index) {
        if (arr[i] == arr[index]) {
            numberOfComps++;
            return;
        }
        int temp = arr[i];
        arr[i] = arr[index];
        arr[index] = temp;
        numberOfSwaps++;
    }

    public static int partition(int[] arr, int low, int high, int pivot) {
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == pivot) {
                swap(arr, i, high);
                numberOfSwaps++;
                numberOfComps++;
                break;
            }
            numberOfComps++;
        }
        int index = low - 1;
        int i = low;
        while (i < high) {
            if (arr[i] < pivot) {
                index++;
                swap(arr, i, index);
                numberOfSwaps++;
                numberOfComps++;
            }
            numberOfComps++;
            i++;
        }
        index++;
        swap(arr, index, high);
        numberOfSwaps++;
        return index;
    }

    private static int getMedianOfMedian(int arr[], int low, int high, int k) {
        if (k > 0 && k <= high - low + 1) {
            numberOfComps++;
            int n = high - low + 1;
            int i, median[] = new int[(n + 4) / 5];
            for (i = 0; i < median.length - 1; i++) {
                numberOfComps++;
                median[i] = median(Arrays.copyOfRange(arr, 5 * i + low, 5 * i + low + 4), 5);
            }

            if (n % 5 == 0) {
                numberOfComps++;
                median[i] = median(Arrays.copyOfRange(arr, 5 * i + low, 5 * i + low + 4), 5);
                i++;
            } else {
                median[i] = median(Arrays.copyOfRange(arr, 5 * i + low, 5 * i + low + (n % 5)), n % 5);
                i++;
            }
            int medOfMed = i == 1 ? median[i - 1]
                    : getMedianOfMedian(median, 0, i - 1, i / 2);

            int partition = partition(arr, low, high, medOfMed);
            if (partition - low == k - 1) {
                numberOfComps++;
                return arr[partition];
            }
            if (partition - low > k - 1) {
                numberOfComps++;
                return getMedianOfMedian(arr, low, partition - 1, k);
            }
            return getMedianOfMedian(arr, partition + 1, high, k - (partition + 1) + low);
        }
        return -1;
    }

    private static int median(int arr[], int n) {
        Arrays.sort(arr);
        return arr[n / 2];
    }
}
