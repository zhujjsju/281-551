
import java.util.*;

public class QuickSort {
    private static int comps = 0;
    private static int swaps = 0;

    public static int[] quickSort(int[] a, int size){
        quickSort_help( a, 0, size-1);
        return a;
    }

    private static void quickSort_help (int[] a, int first, int last){
        if (first < last){
        int splitpoint = partition(a, first, last);
        quickSort_help(a, first, splitpoint-1);
        quickSort_help(a,splitpoint+1,last);
        }
    }

    public static int[] quickSortWithInsertion(int[] a, int size,int min){
        quickSortWithInsertion_help(a,0,size-1, min);
        return a;
    }

    private static void quickSortWithInsertion_help (int [] a, int first, int last, int min){
        if ((last-first+1)<=min)
            insertSort(a,last-first+1);
        else if (first < last){
            int splitpoint = partition(a, first, last);
            quickSortWithInsertion_help(a, first, splitpoint-1,min);
            quickSortWithInsertion_help(a,splitpoint+1,last,min);
        }
    }

    public static int[] randomizedQuickSort (int[] a, int size){
        randomizedQuickSort_help( a, 0, size-1);
        return a;
    }

    private static void randomizedQuickSort_help (int[] a, int first, int last){
        if (first < last){
            int splitpoint = RandomizedPartition(a, first, last);
            //System.out.print( "Index-"  + splitpoint + " is " + a[splitpoint] + "; ");
            randomizedQuickSort_help(a, first, splitpoint-1);
            randomizedQuickSort_help(a, splitpoint+1, last);
        }
    }

    private static int RandomizedPartition(int[] a, int first, int last){
        Random rd = new Random();
        int i = rd.nextInt(last-first+1) + first;
        int temp1 = a[i];
        a[i] = a[last];
        a[last] = temp1;
        swaps++;
        return partition(a,first,last);
    }

    public static int[] medianOf3QuickSort (int [] a, int size){
        medianOf3QuickSort_help(a,0,size-1);
        return a;
    }

    private static void medianOf3QuickSort_help (int [] a, int first, int last){
        if (first < last){
            int splitpoint = medianOf3Partition(a, first, last);
            //System.out.print( "Index-"  + splitpoint + " is " + a[splitpoint] + "; ");
            medianOf3QuickSort_help(a, first, splitpoint-1);
            medianOf3QuickSort_help(a, splitpoint+1, last);
        }
    }

    private static int medianOf3Partition (int[] a, int first, int last){
        int medianIndex = (first + last)/2;
        if ((a[medianIndex]>=a[first]&&a[medianIndex]<=a[last])||(a[medianIndex]<=a[first]&&a[medianIndex]>=a[last])){
            int temp = a[last];
            a[last] = a[medianIndex];
            a[medianIndex] = temp;
            comps += 4;
            swaps++;
        }
        else if ((a[first]>=a[last]&&a[first]<=a[medianIndex])||(a[first]<=a[last]&&a[first]>=a[medianIndex])){
            comps +=4 ;
            int temp = a[last];
            a[last] = a[first];
            a[first] =temp;
            swaps++;
        }
        else
            comps += 4;
        return partition(a,first,last);
    }

    private static int partition(int[] a, int first, int last){
        int pivotValue = a[last];
        int i = first-1;
        for (int j = first; j<last;j++){
            if (a[j]<=pivotValue){
                comps++;
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                swaps++;
            }
            else
                comps++;
        }
        int temp0 = a[i+1];
        a[i+1] = a[last];
        a[last] = temp0;
        swaps++;
        return i+1;
    }

    public static void print(int[] a){
        for (int i=0;i<a.length;i++){
            System.out.print(a[i] + " ");
        }
        System.out.print("\n");
    }

    public static int[] insertSort (int[] list,int size){
        for (int i=1; i<size;i++){
            int key = list[i];
            for (int j = i-1; j>=0; j--){
                if (list[j]>key){
                    list[j+1]=list[j];
                    list[j]=key;
                    swaps++;
                    comps++;
                }
                else
                    comps++;
            }
        }
        //System.out.println("Operation number is " + opCounts);
        return list;
    }

    public static int[] createList(int n){
        Random rand = new Random();
        int[] newList = new int[n];
        for (int i=0;i<n;i++){
            newList[i] = rand.nextInt(1000);
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

    public static int[] createAlmostSortedArray(int num){
        int[] List = createSortedList(num);
        for (int i = num/50;i>0;i--){
            Random r = new Random();
            int j = r.nextInt(num-1);
            int temp = List[i];
            List[i] = List[j];
            List[j] = temp;
        }
        return List;
    }


    public static void main(String[] arg){
        int[] test = {15,6,48,2,-12,36,5,11,-96,17,26,-6,3,140,62,-32};
        /**
        print(quickSort(test,test.length));
        print(randomizedQuickSort(test,test.length));
        print(medianOf3QuickSort(test,test.length));
        print(quickSortWithInsertion(List50,List50.length,5));
        print(quickSortWithInsertion(List50,List50.length,10));
        print(quickSortWithInsertion(List50,List50.length,20));
        */
        int[] arraySize = {50,100,200,400};
        for (int i = 0;i<4; i++){
            for (int j = 0;j<10;j++){
                //int[] sub = createList(arraySize[i]);
                int[] sub = createAlmostSortedArray(arraySize[i]);
                int min = 10;
                //quickSort(sub,arraySize[i]);
                //quickSortWithInsertion(sub,arraySize[i],min);
                //randomizedQuickSort(sub,arraySize[i]);
                medianOf3QuickSort(sub,arraySize[i]);
                System.out.println("Array Size: " + arraySize[i]+ "\nVersion 4 - Trail " + (j+1) + ": Comparisons = " + comps + "; Swaps = " + swaps + ".");
                comps = 0;
                swaps = 0;
            }
        }
    }//end of main
}//end of class
