import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        int[] arr;
        Integer[] arr1 = new Integer[10000];
        Integer[] arr2 = new Integer[10000];
        Integer[] arr3 = new Integer[10000];
        Integer[] arr4 = new Integer[10000];
        Integer[] arr5 = new Integer[10000];
        Integer[] arr6 = new Integer[10000];
        Integer[] arr7 = new Integer[10000];

        long[] time_selection = new long[20];
        long[] time_bubble = new long[20];
        long[] time_insertion = new long[20];
        long[] time_shell = new long[20];
        long[] time_merge = new long[20];
        long[] time_heap = new long[20];
        long[] time_quick = new long[20];
        long start;
        long end;

        try {
            for(int i = 0 ; i < 1 ; ++i)
            {
                arr = generateSortedArr(10000);

                for(int t = 0 ; t < 10000 ; ++t)
                {
                    arr1[t] = arr[t];
                    arr2[t] = arr[t];
                    arr3[t] = arr[t];
                    arr4[t] = arr[t];
                    arr5[t] = arr[t];
                    arr6[t] = arr[t];
                    arr7[t] = arr[t];
                }

                System.out.println("shell " + i);
                start = System.nanoTime();
                ShellSort.sort(arr4);
                end = System.nanoTime();
                time_shell[i] = end - start;

                System.out.println("merge " + i);
                start = System.nanoTime();
                MergeSort.sort(arr5);
                end = System.nanoTime();
                time_merge[i] = end - start;

                System.out.println("heap " + i);
                start = System.nanoTime();
                HeapSort.sort(arr6);
                end = System.nanoTime();
                time_heap[i] = end - start;

                System.out.println("quick " + i);
                start = System.nanoTime();
                QuickSort.sort(arr7);
                end = System.nanoTime();
                time_quick[i] = end - start;

                System.out.println("selection " + i);
                start = System.nanoTime();
                SelectionSort.sort(arr1);
                end = System.nanoTime();
                time_selection[i] = end - start;

                System.out.println("bubble " + i);
                start = System.nanoTime();
                BubbleSort.sort(arr2);
                end = System.nanoTime();
                time_bubble[i] = end - start;

                System.out.println("insertion " + i);
                start = System.nanoTime();
                InsertionSort.sort(arr3);
                end = System.nanoTime();
                time_insertion[i] = end - start;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nTIME SELECTION");
        for(long l : time_selection)
            System.out.println(l);
        System.out.println("\nTIME BUBBLE");
        for(long l : time_bubble)
            System.out.println(l);
        System.out.println("\nTIME INSERTION");
        for(long l : time_insertion)
            System.out.println(l);
        System.out.println("\nTIME SHELL");
        for(long l : time_shell)
            System.out.println(l);
        System.out.println("\nTIME MERGE");
        for(long l : time_merge)
            System.out.println(l);
        System.out.println("\nTIME HEAP");
        for(long l : time_heap)
            System.out.println(l);
        System.out.println("\nTIME QUICK");
        for(long l : time_quick)
            System.out.println(l);
    }

    public static int[] generateRandArr(int size)
    {
        Random rand = new Random();
        int[] arr = new int[size];

        do{
            for(int i = 0 ; i < arr.length ; ++i)
                arr[i] = rand.nextInt();
        }while(isSorted(arr));

        return arr;
    }

    public static int[] generateSortedArr(int size)
    {
        Random rand = new Random();
        int[] arr = new int[size];

        for(int i = 0 ; i < arr.length ; ++i)
            arr[i] = i;

        return arr;
    }

    public static boolean isSorted(int[] arr)
    {
        for(int i  = 1 ; i < arr.length ; ++i)
            if(arr[i-1] > arr[i])
                return false;

        return true;
    }
}
