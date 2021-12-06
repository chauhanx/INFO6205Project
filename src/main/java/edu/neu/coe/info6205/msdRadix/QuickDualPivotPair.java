

package edu.neu.coe.info6205.msdRadix;

import java.io.IOException;
import java.util.Arrays;

public class QuickDualPivotPair {
    final static boolean isChinese = true;

    // quicksort the array a[] using dual-pivot quicksort
    public static void sort(String[] arr) {

        Pair[] a = new Pair[arr.length];
        for(int i=0;i<arr.length;i++){
            a[i] = new Pair(arr[i]);
        }

        sort(a, 0, a.length - 1);

//        to print array back to chinese words
        for(int i=0;i<arr.length;i++){
            arr[i] = a[i].getKey();
        }
    }

    // quicksort the subarray a[lo .. hi] using dual-pivot quicksort
    private static void sort(Pair[] a, int lo, int hi) {
        if (hi <= lo) return;
        // make sure a[lo] <= a[hi]
        if (less(a[hi].getValue(), a[lo].getValue())) exch(a, lo, hi);

        int lt = lo + 1, gt = hi - 1;
        int i = lo + 1;
        while (i <= gt) {
            if(less(a[i].getValue(), a[lo].getValue())) exch(a, lt++, i++);
            else if(less(a[hi].getValue(), a[i].getValue())) exch(a, i, gt--);
            else i++;
        }
        exch(a, lo, --lt);
        exch(a, hi, ++gt);
        // recursively sort three subarrays
        sort(a, lo, lt-1);
        if (less(a[lt].getValue(), a[gt].getValue())) sort(a, lt+1, gt-1);
        sort(a, gt+1, hi);
    }


    private static boolean less(String v, String w) {
        return helper.compare(v,w) <= 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Pair[] a, int i, int j) {
        Pair swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Read strings from standard input, sort them, and print.
    public static void main(String[] args) throws IOException {
        IOTextFile io = new IOTextFile();
        String[] a = io.readFileStreamByLength(isChinese,200);
        sort(a);
//        System.out.println(Arrays.toString(a));
        io.writeStream(a);
    }

}