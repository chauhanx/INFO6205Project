package edu.neu.coe.info6205.msdRadix;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class msd {

        static int R=256;
        static boolean isChinese = true;

        public static void main(String[] args) throws IOException {
            try{
                IOTextFile io = new IOTextFile();
                String[] a = io.readFileInRange("chinese.txt",1000);
                sort(a);
                System.out.println(Arrays.toString(a));
            }catch(FileNotFoundException ex){
                System.out.println(ex.getMessage());
            }
        }

        public static void sort(String[] arr) {
            Pair[] aux = new Pair[arr.length];
            Pair[] array = helper.getChinesePair(arr);
            sort(array, aux, 0, arr.length - 1, 0);
            for (int i = 0; i < array.length; i++) {
                arr[i] = array[i].getValue();
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

            for(int i=low;i<=high;i++){
                arr[i] = aux[i-low];
            }

            for(int r=0;r<R;r++){
                sort(arr,aux,low+count[r],low+count[r+1]-1,d+1);
            }
        }

        public static int char_at(Pair s, int d)
        {
//            to prevent current index out of bound
            if (d < s.getValue().length()) return s.getValue().charAt(d);
            else return -1;
        }


    }

