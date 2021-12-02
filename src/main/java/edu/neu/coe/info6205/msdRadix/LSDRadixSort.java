package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class LSDRadixSort {

    static boolean isChinese = true;
    static int R=256;
    static int S=0;

    // do not instantiate
    private LSDRadixSort() { }


    public static int findLongestLength(Node[] a) {
        int longest = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i].getValue().length() > longest) {
                longest = a[i].getValue().length();
            }
        }
        return longest;
    }


    public static int findCharAtInString(int i, int d, Node[] a) {
        if (d < 0 || d >= a[i].getValue().length()) {
            return 0;
        }
        return a[i].getValue().charAt(d);
    }



    public static void sort(Node[] arr, int length) {
        int n = arr.length;
        Node[] aux = new Node[arr.length];

        for (int i = length-1; i >= 0; i--) {
            // compute frequency counts
            int[] count = new int[R+1];

            for (int j = 0; j < n; j++) {
                int ch = findCharAtInString(j, i, arr);
                count[ch + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; ++r) {
                count[r + 1] += count[r];
            }

            // move data
            for (int j = 0; j < n; j++) {
                int ch = findCharAtInString(j, i, arr);
                aux[count[ch]++] = arr[j];
            }

            // copy back
            for (int j = 0; j < n; j++) {
                arr[j] = aux[j];
            }
        }
    }


    public static void sort(String[] arr){

        Node[] array = helper.getChinesePair(arr);
        int longestLength = findLongestLength(array);
        sort(array,longestLength);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[i].getKey();
        }
    }


    public static void main(String[] args) throws IOException {
        try{
            IOTextFile io = new IOTextFile();
            ArrayList<String> lines = io.readStream(isChinese);
            String[] arr = lines.toArray(new String[0]);
            int n = arr.length;
            Date start = new Date();
            sort(arr);

            Date end = new Date();
            System.out.println(end.getTime()-start.getTime());
            io.writeStream(arr);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }

    }
}
