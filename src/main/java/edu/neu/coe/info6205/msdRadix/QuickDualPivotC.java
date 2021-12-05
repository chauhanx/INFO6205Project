package edu.neu.coe.info6205.msdRadix;

import java.io.IOException;
import java.util.Arrays;

public class QuickDualPivotC {

    // quicksort the array a[] using dual-pivot quicksort
    public static void sort(String[] a) {
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray a[lo .. hi] using dual-pivot quicksort
    private static void sort(String[] a, int lo, int hi) {
        if (hi <= lo) return;
        // make sure a[lo] <= a[hi]
        if (less(a[hi], a[lo])) exch(a, lo, hi);

        int lt = lo + 1, gt = hi - 1;
        int i = lo + 1;
        while (i <= gt) {
            if(less(a[i], a[lo])) exch(a, lt++, i++);
            else if(less(a[hi], a[i])) exch(a, i, gt--);
            else i++;
        }
        exch(a, lo, --lt);
        exch(a, hi, ++gt);
        // recursively sort three subarrays
        sort(a, lo, lt-1);
        if (less(a[lt], a[gt])) sort(a, lt+1, gt-1);
        sort(a, gt+1, hi);
    }


    private static boolean less(String v, String w) {
        return helper.compare(v,w) <= 0;
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Read strings from standard input, sort them, and print.
    public static void main(String[] args) throws IOException {
        String[] a = new IOTextFile().readFileInRange("chinese.txt",1000000);
        QuickDualPivotC q = new QuickDualPivotC();
        q.sort(a);
//        System.out.println(Arrays.toString(a));
    }

}