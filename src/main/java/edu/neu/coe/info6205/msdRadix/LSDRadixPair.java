package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

    public class LSDRadixPair {

        static boolean isChinese = true;
        static int R=256;
        static int S=0;


        public static int findLongestLength(Pair[] a) {
            int longest = Integer.MIN_VALUE;
            for (Pair i:a) {
                longest = Math.max(longest,i.getValue().length());
            }
            return longest;
        }

        public static int getCharIndex(int i, int d, Pair[] a) {
            if (d < 0 || d >= a[i].getValue().length()) {
                return 0;
            }
            return a[i].getValue().charAt(d);
        }


        private static void sort(Pair[] arr, int length) {
            int n = arr.length;
            Pair[] aux = new Pair[arr.length];

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
            Pair[] a = new Pair[arr.length];
            for(int i=0;i<arr.length;i++){
                a[i] = new Pair(arr[i]);
            }

            int longestLength = findLongestLength(a);
            sort(a, longestLength);


//            to print array back to chinese words
//            for(int i=0;i<arr.length;i++){
//                arr[i] = a[i].getKey();
//            }

        }

        public static void main(String[] args) throws IOException {
            try{
                IOTextFile io = new IOTextFile();
                String[] arr = io.readFileStreamByLength(isChinese,200);
                sort(arr);
//                System.out.println(Arrays.toString(arr));
                io.writeStream(arr);
            }catch(FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

