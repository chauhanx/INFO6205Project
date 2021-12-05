package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LSDRadixSort {

    static boolean isChinese = true;
    static int R=256;
    static int S=0;

    // do not instantiate
    public LSDRadixSort() { }


    public static int findLongestLength(String[] a) {
        int longest = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i].length() > longest) {
                longest = a[i].length();
            }
        }
        return longest;
    }

    public static int getCharIndex(int d, int i, String[] a) {
        if (d < 0 || d >= a[i].length()) {
            return 0;
        }
        return a[i].charAt(d);
    }


    private static void sort(String[] arr, int length) {
        int n = arr.length;
        String[] aux = new String[arr.length];

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
        for(int i=0;i<arr.length;i++){
            arr[i] = helper.getPinyin(arr[i]);
        }
        int longestLength = findLongestLength(arr);
        sort(arr,longestLength);
    }

    public static void main(String[] args) throws IOException {
        try{
            IOTextFile io = new IOTextFile();
            String[] arr = io.readFileStreamByLength(isChinese,250000);
            sort(arr);
//            System.out.println(Arrays.toString(arr));
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }

    }
}
