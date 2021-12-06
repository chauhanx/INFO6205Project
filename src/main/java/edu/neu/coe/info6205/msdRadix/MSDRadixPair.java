package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class MSDRadixPair {

    static int R=256;
    static boolean isChinese = true;
    public static void main(String[] args) throws IOException {
        try{
            IOTextFile io = new IOTextFile();
            String[] a =  io.readFileStreamByLength(isChinese,200);
            sort(a);
//            System.out.println(Arrays.toString(a));
            io.writeStream(a);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void sort(String[] arr)  {
        Pair[] aux = new Pair[arr.length];
        Pair[] a = new Pair[arr.length];
        for(int i=0;i<arr.length;i++){
            a[i] = new Pair(arr[i]);
        }
        sort(a, aux, 0, arr.length - 1, 0);

//        to print array back to chinese words
//        for(int i=0;i<arr.length;i++){
//            arr[i] = a[i].getValue();
//        }
    }

    private static void sort(Pair[] arr, Pair[] aux, int low, int high, int d) {
        if (high <= low )return;
        int[] count = new int[R+2];

        for(int i=low;i<=high;i++){
            int c =  char_at(arr[i].getValue(),d);
            count[c+2]++;
        }
        for(int r=0;r<R;r++){
            count[r+1] += count[r];
        }
        for(int i=low;i<=high;i++){
            aux[count[char_at(arr[i].getValue(), d) + 1]++] = arr[i];
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
