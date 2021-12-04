package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LSDRadixSort {

    static boolean isChinese = true;
    static int R=256;
    static int S=0;

    // do not instantiate
    public LSDRadixSort() { }


    public static int findLongestLength(Pair[] a) {
        int longest = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i].getArr().length > longest) {
                longest = a[i].getArr().length;
            }
        }
        return longest;
    }

    public static int getCharIndex(int d, int i, Pair[] a) {
        if (d < 0 || d >= a[i].getArr().length) {
            return 0;
        }
        // to prevent current index out of bound
        return a[i].getArr()[d]& 0xFF;
    }


    private static void sort(Pair[] arr, int length) {
        int n = arr.length;
        Pair[] aux = new Pair[arr.length];

        for (int i = length-1; i >= 0; i--) {
            int[] count = new int[R+1];

            for (int j = 0; j < n; j++) {
                count[getCharIndex(j, i, arr) + 1]++;
            }
            for (int r = 0; r < R; ++r) {
                count[r + 1] += count[r];
            }
            for (int j = 0; j < n; j++) {
                aux[count[getCharIndex(j, i, arr)]++] = arr[j];
            }
            for (int j = 0; j < n; j++) {
                arr[j] = aux[j];
            }
        }
    }

    public static void sort(String[] arr){
        Pair[] array = helper.getChinesePair(arr);
        int longestLength = findLongestLength(array);
        sort(array,longestLength);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[i].getKey();
        }
    }

    public static void main(String[] args) throws IOException {
        try{
            IOTextFile io = new IOTextFile();
            String[] arr = io.readFileInRange("chinese.txt",250000);
            sort(arr);
//            System.out.println(Arrays.toString(arr));
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }

    }
}
