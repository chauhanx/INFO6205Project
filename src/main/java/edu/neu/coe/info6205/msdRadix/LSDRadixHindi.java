
package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LSDRadixHindi {

        static boolean isChinese = false;
        static int R=400;
        static int S=2000;
        public static int findLongestLength(String[] a) {
            int longest = 0;
            for (int i = 0; i < a.length; ++i) {
                if (a[i].length() > longest) {
                    longest = a[i].length();
                }
            }
            return longest;
        }

        public static int findCharAtInString(int i, int d, String[] a) {
            if (d < 0 || d >= a[i].length()) {
                return 0;
            }
            return a[i].charAt(d)-2300;
        }

        public static void sort(String[] arr) {
            int longestLength = findLongestLength(arr);
            sort(arr,longestLength);
        }

        private static void sort(String[] arr, int length) {
            int n = arr.length;
            String[] aux = new String[n];

            for (int i = length-1; i >= 0; i--) {

                // compute frequency counts
                int[] count = new int[R+1];
                for (int j = 0; j < n; j++) {
                    int ch = findCharAtInString(j, i, arr);
//                    System.out.println("ch " + ch +   "  " + arr[j].charAt(i));
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


        public static void main(String[] args) throws IOException {
            try{
                IOTextFile io = new IOTextFile();
                String[] arr = io.readFileStreamByLength(isChinese,200);
                // sort the strings
                sort(arr);
//                System.out.println(Arrays.toString(arr));
                io.writeStream(arr);
            }catch(FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }

        }
    }

