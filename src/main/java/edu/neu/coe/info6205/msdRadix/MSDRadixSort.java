package edu.neu.coe.info6205.msdRadix;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MSDRadixSort {

    static int R=256;
    static boolean isChinese = true;
     public static void main(String[] args) throws IOException {
        try{
            IOTextFile io = new IOTextFile();
            String[] a = io.readFileInRange("chinese.txt",999990);
//            List<String> list = io.readStream(isChinese);
//            String[] a = list.toArray(new String[0]);
            Date start = new Date();
            sort(a);
            Date end = new Date();
            System.out.println(end.getTime()-start.getTime());
            io.writeStream(a);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void sort(String[] arr) {
        Node[] aux = new Node[arr.length];
        Node[] array = helper.getChinesePair(arr);

        sort(array, aux, 0, arr.length - 1, 0);

        for (int i = 0; i < array.length; i++) {
            arr[i] = array[i].getKey();
        }
    }

    private static void sort(Node[] arr, Node[] aux, int i, int i1, int i2) {
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
    private static int char_at(String s, int d){
        if (d < s.length()) return (int)s.charAt(d);
        return -1;


//    private static void sort(Node[] arr,Node[] aux,int low,int high,int d){
//        if (high <= low )return;
//        int[] count = new int[R+2];
//
//        for(int i=low;i<=high;i++){
//            int c =  char_at(arr[i].getValue(),d);
//            count[c+2]++;
//        }
//
//        for(int r=0;r<R;r++){
//            count[r+1] += count[r];
//        }
//
//        for(int i=low;i<=high;i++){
//            aux[count[char_at(arr[i].getValue(), d) + 1]++] = arr[i];
//        }
//
//        for(int i=low;i<=high;i++){
//            arr[i] = aux[i-low];
//        }
//
//        for(int r=0;r<R;r++){
//            sort(arr,aux,low+count[r],low+count[r+1]-1,d+1);
//        }
//    }

}
