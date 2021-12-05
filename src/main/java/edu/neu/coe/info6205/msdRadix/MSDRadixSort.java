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
            String[] a =  io.readFileStreamByLength(isChinese,2000);
            sort(a);
            System.out.println(Arrays.toString(a));
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }


    public static void sort(String[] arr)  {
         String[] aux = new String[arr.length];
         for(int i=0;i<arr.length;i++){
             arr[i] = helper.getPinyin(arr[i]);
         }
         sort(arr, aux, 0, arr.length - 1, 0);
    }

    private static void sort(String[] arr, String[] aux, int low, int high, int d) {
        if (high <= low )return;
        int[] count = new int[R+2];

        for(int i=low;i<=high;i++){
            int c =  char_at(arr[i],d);
            count[c+2]++;
        }
        for(int r=0;r<R;r++){
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

    //    to get character based on pinyin
    private static int char_at(String s, int d) {
        if (d < s.length()) return (int) s.charAt(d);
        return -1;
    }
}
