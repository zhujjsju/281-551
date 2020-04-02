import java.util.Arrays;
import java.util.Random;

public class SimultaneousMinAndMax {
    public static int n = 50;

    private static int numberOfSwaps = 0;
    private static int numberOfComps = 0;

    public static int[] simultaneousMinAndMax(int A[]) {
        int B[] = new int[2];
        int small, large;
        small = Math.min(A[0], A[1]);
        large = Math.max(A[0], A[1]);

        for (int i = 2; i + 1 < A.length; i = i + 2) {
            numberOfComps++;
            int min = Math.min(A[i], A[i + 1]);
            numberOfSwaps++;
            numberOfComps++;
            int max = Math.max(A[i], A[i + 1]);
            numberOfSwaps++;
            numberOfComps++;

            small = Math.min(min, small);
            numberOfSwaps++;
            numberOfComps++;
            large = Math.max(max, large);
            numberOfSwaps++;
            numberOfComps++;
        }

        if (A.length % 2 != 0) {
            small = Math.min(small, A[A.length - 1]);
            large = Math.max(large, A[A.length - 1]);
            numberOfSwaps++;
            numberOfComps++;
        }
        B[0] = small;
        B[1] = large;
        return B;
    }
}
