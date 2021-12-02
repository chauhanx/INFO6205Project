
package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

    public class LSDRadixHindi {

        static boolean isChinese = false;
        static int R=30000;
        static int S=0;

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
            return a[i].charAt(d);
        }

        public static void sort(String[] arr, int length) {
            int n = arr.length;
            int R = 65536;   // extend ASCII alphabet size
            String[] aux = new String[n];

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


        public static void main(String[] args) throws IOException {
            try{

                IOTextFile io = new IOTextFile();
                ArrayList<String> lines = io.readStream(isChinese);
                String[] arr = lines.toArray(new String[0]);
                // check that strings have fixed length
                int longestLength = findLongestLength(arr);
                // sort the strings
                sort(arr, longestLength);
                System.out.println("dalkal");
                io.writeStream(arr);
            }catch(FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }

        }
    }

