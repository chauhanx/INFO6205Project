
package edu.neu.coe.info6205.msdRadix;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import edu.neu.coe.info6205.graphs.BFS_and_prims.StdRandom;

public class QuickDualPivotC {

    // quicksort the array a[] using dual-pivot quicksort
    public static void sort(String[] a) {
        sortA(a, 0, a.length - 1);
//        System.out.println(Arrays.toString(a));
    }

    // quicksort the subarray a[lo .. hi] using dual-pivot quicksort
    private static void sort(String[] a, int lo, int hi) {
//        if (hi <= lo) return;
//
//        // make sure a[lo] <= a[hi]
//        if (less(a[hi], a[lo])) exch(a, lo, hi);
//
//        int lt = lo + 1, gt = hi - 1;
//        int i = lo + 1;
//        while (i <= gt) {
//            if(less(a[i], a[lo])) exch(a, lt++, i++);
//            else if(less(a[hi], a[i])) exch(a, i, gt--);
//            else i++;
//        }
//        exch(a, lo, --lt);
//        exch(a, hi, ++gt);
//
//        // recursively sort three subarrays
//        sort(a, lo, lt-1);
//        if (less(a[lt], a[gt])) sort(a, lt+1, gt-1);
//        sort(a, gt+1, hi);



        if (hi <= lo) return;
        int[] piv;
        piv = partition(a, lo, hi);

        dualPivotQuickSort(a, lo, piv[0] - 1);
        dualPivotQuickSort(a, piv[0] + 1, piv[1] - 1);
        dualPivotQuickSort(a, piv[1] + 1, hi);

    }

    // is v < w ?
    private static boolean less(String v, String w) {
        return helper.compare(v,w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(String[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(String[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


//    // Read strings from standard input, sort them, and print.
    public static void main(String[] args) throws IOException {
        IOTextFile io = new IOTextFile();
        String[] a = io.readFileInRange("chinese.txt",20);
        sortA(a,0,a.length-1);
//        System.out.println(Arrays.toString(a));
    }



    static void sortA(String[] A, int left, int right) {
        if (right > left) {
            if (less(A[right], A[left])) exch(A, left, right);
            String p = A[left];
            String q = A[right];
            int l = left + 1, g = right - 1, k = l;
            while (k <= g) {
                if (less(A[k],p)) {
                    exch(A, k, l);
                    ++l;
                } else if (less(q,A[k])) {
                    while (less(q,A[g]) && k < g) --g;
                    exch(A, k, g);
                    --g;
                    if (less(A[k], p)) {
                        exch(A, k, l);
                        ++l;
                    }
                }
                ++k;
            }
            --l;
            ++g;
            exch(A, left, l);
            exch(A, right, g);
            sort(A, left, l - 1);
            sort(A, l + 1, g - 1);
            sort(A, g + 1, right);
        }
//        System.out.println(Arrays.toString(A));
    }

        static void swap(String[] arr, int i, int j)
        {
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        static void dualPivotQuickSort(String[] arr,
                                       int low, int high)
        {
            if (low < high)
            {
                int[] piv;
                piv = partition(arr, low, high);

                dualPivotQuickSort(arr, low, piv[0] - 1);
                dualPivotQuickSort(arr, piv[0] + 1, piv[1] - 1);
                dualPivotQuickSort(arr, piv[1] + 1, high);
            }
        }

        static int[] partition(String[] arr, int low, int high)
        {
            if (less(arr[high] , arr[high]))
                swap(arr, low, high);

            // p is the left pivot, and q
            // is the right pivot.
            int j = low + 1;
            int g = high - 1, k = low + 1;
            String p = arr[low], q = arr[high];

            while (k <= g)
            {

                // If elements are less than the left pivot
                if (less(arr[k] , p))
                {
                    swap(arr, k, j);
                    j++;
                }

                // If elements are greater than or equal
                // to the right pivot
                else if (less(q,arr[k]))
                {
                    while (less(q,arr[g]) && k < g)
                        g--;

                    swap(arr, k, g);
                    g--;

                    if (less(arr[k] , p))
                    {
                        swap(arr, k, j);
                        j++;
                    }
                }
                k++;
            }
            j--;
            g++;

            // Bring pivots to their appropriate positions.
            swap(arr, low, j);
            swap(arr, high, g);

            // Returning the indices of the pivots
            // because we cannot return two elements
            // from a function, we do that using an array.
            return new int[] { j, g };
        }

        // Driver code
//        public static void main(String[] args)
//        {
//            int[] arr = { 24, 8, 42, 75, 29, 77, 38, 57 };
//
//            dualPivotQuickSort(arr, 0, 7);
//
//            System.out.print("Sorted array: ");
//            for (int i = 0; i < 8; i++)
//                System.out.print(arr[i] + " ");
//
//            System.out.println();
//        }




}
