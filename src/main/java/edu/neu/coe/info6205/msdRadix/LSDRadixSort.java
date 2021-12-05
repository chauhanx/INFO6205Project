package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class LSDRadixSort {

    static boolean isChinese = true;
    static int R=256;
    static int S=0;

    // do not instantiate
    public LSDRadixSort() { }


    public static int findLongestLength(String[] a) {
        int longest = Integer.MIN_VALUE;
        for (String i:a) {
            longest = Math.max(longest,i.length());
        }
        return longest;
    }

    public static int getCharIndex(int i, int d, String[] a) {
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
                int c = getCharIndex(j, i, arr);
                count[c + 1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            for (int j = 0; j < n; j++) {
                int c = getCharIndex(j, i, arr);
                aux[count[c]++] = arr[j];
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
            String[] arr = io.readFileStreamByLength(isChinese,20);
            sort(arr);
            System.out.println(Arrays.toString(arr));
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
}
