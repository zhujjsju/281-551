import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

public class Sortings {
    private static int opNum = 0;
    public static int[] insertSort (int[] chaos,int size){
        //int opCounts = 0;
        for (int j=1; j<size;j++){
            Integer key = chaos[j];
            int i = j-1;
            while (i>=0 && chaos[i]>key){
                chaos[i+1]=chaos[i];
                //opCounts++;
                i--;
                chaos[i+1]=key;
            }
        }
        //System.out.println("Operation number is " + opCounts);
        return chaos;
    }

    public static int[] mergeSort (int[] list, int size) {
        if (size == 1) {
            return list;
        }
        int mid = size / 2;
        int[] firstHalf = new int[mid];
        int[] secHalf = new int[size - mid];
        for (int i = 0; i < mid; i++) {
            firstHalf[i] = list[i];
            //opNum++;
        }
        //print(firstHalf);
        for (int j = 0; j < size - mid; j++) {
            secHalf[j] = list[mid + j];
            //opNum++;
        }
        //print(secHalf);
        mergeSort(firstHalf, mid);
        mergeSort(secHalf, size - mid);

        int a = 0;
        int b = 0;
        int k = 0;
        while (a < firstHalf.length && b < secHalf.length) {
            if (firstHalf[a] < secHalf[b]) {
                list[k] = firstHalf[a];
                //opNum++;
                a++;
            }
            else {
                list[k] = secHalf[b];
                //opNum++;
                b++;
            }
            k++;
        }
        while (a < firstHalf.length) {
            list[k] = firstHalf[a];
            //opNum++;
            a++;
            k++;
        }
        while (b < secHalf.length) {
            list[k] = secHalf[b];
            //opNum++;
            b++;
            k++;
        }
        //System.out.println("merge: ");
        //print(list);
        //System.out.println("Operation number is " + opNum);
        return list;
    }

    public static int[] selectionSort(int[] a, int length) {
        //int opCounts = 0;
        for (int i=length-1; i>=0;i--){
            int index = 0;
            for (int j = 1; j < i+1; j++){
                if (a[j] > a[index]){
                    index = j;
                }
            }
            int temp = a[i];
            a[i] = a[index];
            a[index] = temp;
            //opCounts++;
        }
        //System.out.println("Opeartion Number is " + opCounts);
        return a;
    }

    public static void print(int[] a){
        for (int i=0;i<a.length;i++){
            System.out.print(a[i] + " ");
        }
        System.out.print("\n");
    }

    public static int[] createList(int n){
        Random rand = new Random();
        int[] newList = new int[n];
        for (int i=0;i<n;i++){
            newList[i] = rand.nextInt(1000000);
        }
        return newList;
    }

    public static int[] createReversedList (int n){
        int[] newList = new int[n];
        for (int i = 0; i<newList.length;i++){
            newList[i] = newList.length - i;
        }
        return newList;
    }

    public static int[] createSortedList (int n){
        int[] newList = new int[n];
        for (int i = 0; i<newList.length;i++){
            newList[i] = i;
        }
        return newList;
    }

    public static void main(String s[]) {
        //double sum = 0;
        int num = 500000;
        //int[] List = Sorting.createList(num); // Create a random list with 'num' elements.
        //int[] List = Sorting.createReversedList(num);
        int[] List = Sortings.createSortedList(num);
        //Sorting.print(List);
        ThreadMXBean tmb = ManagementFactory.getThreadMXBean();
        long startTime = System.nanoTime(); //get starting times
        long startCPUTime = tmb.getCurrentThreadCpuTime(); //also in nanoseconds
        Sortings.insertSort(List,num);
        //Sorting.mergeSort(List,num);
        //Sorting.selectionSort(List,num);
        //Sorting.quickSort(List,num);
        //QuickSort.quickSort(List,num);
        QuickSort.randomizedQuickSort(List,num);
        //for (double d = 0; d < 1000; d += 0.0001) sum += Math.sin(d) * Math.sin(d); //time consuming
        long endCPUTime = tmb.getCurrentThreadCpuTime(); //get ending times
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime; //calculate delta times
        long elapsedCPUTime = endCPUTime - startCPUTime;

        System.out.println("elapsed time = " + elapsedTime + " ns"); //report results
        System.out.println("cpu time = " + elapsedCPUTime + " ns");
        //System.out.println("\nsum=" + sum);
        //Sorting.print(List);
    }//end of main
}
