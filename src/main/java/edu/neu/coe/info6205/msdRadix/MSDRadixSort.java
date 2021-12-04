package edu.neu.coe.info6205.msdRadix;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class MSDRadixSort {

        static int R=256;
        static boolean isChinese = true;

        public static void main(String[] args) throws IOException {
            try{
                IOTextFile io = new IOTextFile();
                String[] a = io.readFileInRange("chinese.txt",250000);
                sort(a);
            }catch(FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
        }

        public static void sort(String[] arr) {
            Pair[] aux = new Pair[arr.length];
            Pair[] array = helper.getChinesePair(arr);
            sort(array, aux, 0, arr.length - 1, 0);
            for (int i = 0; i < array.length; i++) {
                arr[i] = array[i].getKey();
            }
        }


    private static void sort(Pair[] arr, Pair[] aux, int low, int high, int d) {
            if (high <= low )return;
            int[] count = new int[R+2];

            for(int i=low;i<=high;i++){
                count[char_at(arr[i],d)+2]++;
            }

            for(int r=0;r<R+1;r++){
                count[r+1] += count[r];
            }

            for(int i=low;i<=high;i++){
                aux[count[char_at(arr[i], d) + 1]++] = arr[i];
            }

//            for(int i=low;i<=high;i++){
//                arr[i] = aux[i-low];
//            }
            if (high + 1 - low >= 0) System.arraycopy(aux, 0, arr, low, high + 1 - low);

            for(int r=0;r<R;r++){
                sort(arr,aux,low+count[r],low+count[r+1]-1,d+1);
            }
        }

        public static int char_at(Pair s, int d)
        {
//            to prevent current index out of bound
            if (d < s.getArr().length) return s.getArr()[d]&0xFF;
            else return -1;
        }

    }

