import java.util.*;

public class LinearSort {
    private static int ops = 0;
    public static int getOps(){return ops;}

    //Creat a int[] with n = size element and range from 0 to range -1
    public static int[] createList(int size, int range){
        Random r = new Random();
        int[] list = new int[size];
        for (int i=0; i<size; i++){
            list[i] = r.nextInt(range);
        }
        return list;
    }

    //print out the list
    public static void print(int[] a){
        for (int i=0; i<a.length;i++){
            System.out.print(a[i] + ", ");
        }
        System.out.println();
    }

    public static ArrayList<Integer> insertionSort (ArrayList<Integer> arr){
        for (int i=1; i<arr.size();i++){
                int key = arr.get(i);
                int j = i-1;
                ops+=2;
            while (j>=0 && arr.get(j)>key){
                arr.set(j+1,arr.get(j));
                arr.set(j, key);
                j--;
                ops+=3;
            }
            ops+=j*(j+1)/2;
        }
        ops += arr.size()+1;
        return arr;
    }

    public static int[] countingSort(int[] list){
        ops = 0;
        int max = list[0];
        for (int i=0;i<list.length;i++){
            if (list[i]>max) max = list[i];
            ops++;
        }
        ops += list.length + 1;
        int[] b = new int[list.length];
        return countingSort_help(list,b,max);
    }

    public static int[] countingSort_help(int[] a, int[] b, int k){
        int[] c = new int[k+1];
        for (int i=0;i<k+1;i++){
            c[i]=0;
        }
        ops += 2 * (k+1) + 1;
        for (int j=0; j<a.length;j++) {
            c[a[j]] = c[a[j]] + 1;
        }
        ops += 2 * a.length + 1;
        for (int i=1; i<k+1;i++){
            c[i] = c[i] + c[i-1];
        }
        ops += 2 * k + 1;
        for (int j=a.length-1;j>=0;j--){
            b[c[a[j]]-1] = a[j];
            c[a[j]]--;
        }
        ops += 3 * a.length + 1;
        return b;
    }

    public static int[] bucketSort(int[] list){
        ops = 0;
        int max = list[0];
        class Bucket{
            ArrayList<Integer> bucket = new ArrayList<>();
        }

        //find the max element of the list
        for (int i=0;i<list.length-1;i++){
            if (list[i]>max){
                max = list[i];
                ops++;
            }
        }
        ops += 2*list.length +1;

        //determined how many buckets is needed;
        int numOfBuckets = list.length/10;

        //create buckets for Bucket[];
        Bucket[] buckets = new Bucket[numOfBuckets];
        for (int i=0;i<numOfBuckets;i++){
            buckets[i] = new Bucket();
        }

        //fill in the bucket
        for (int i: list){
            int bucketIndex = i * numOfBuckets/(max+1);
            buckets[bucketIndex].bucket.add(i);
        }
        ops += 3*list.length;

        //sort each bucket which has more than 1 element and add the sorted element to the new int[]
        int index = 0;
        for (Bucket b: buckets){
            insertionSort(b.bucket);
            //put sorted element to the final sorted list
            for (int i : b.bucket) {
                list[index] = i;
                index++;
            }
        }
        ops += 2 * numOfBuckets+list.length;
        return list;
    }
}
