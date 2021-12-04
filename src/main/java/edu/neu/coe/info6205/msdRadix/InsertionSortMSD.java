package edu.neu.coe.info6205.msdRadix;

public class InsertionSortMSD {

    public static void sort(Pair[] a, int lo, int hi, int d) {
        for (int i = lo; i < hi; i++)
            for (int j = i; j > lo && less(a[j].getValue(), a[j - 1].getValue(), d); j--)
                swap(a, j, j - 1);
    }
    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void swap(Object[] a, int j, int i) {
        Object temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}
