package edu.neu.coe.info6205.msdRadix;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MSDRadixSort {

    static int R=256;
    static int M = 15;
    static boolean isChinese = true;

    public static void main(String[] args) throws IOException {
        try{
            IOTextFile io = new IOTextFile();
            List<String> list = io.readStream(isChinese);
            String[] a = list.toArray(new String[0]);
           // System.out.println(a[0]);
            Date start = new Date();
            sort(a);
            Date end = new Date();
            System.out.println(end.getTime()-start.getTime());
            io.writeStream(a);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    // abc ,abx,zzz
    public static void print(String[] str){
        System.out.println(Arrays.toString(str));
    }

//    to get character based on pinyin
    private static int pinyinCharAt(Node s, int d){
        if (d < s.getValue().length()){
            int a = (int)s.getValue().charAt(d);
          // System.out.println(a);
            return a;
        }
        else return -1;
    }

    public static void sort(String[] arr){

        Node[] aux = new Node[arr.length];
        Node[] array = helper.getChinesePair(arr);

        sort(array, aux, 0, arr.length - 1, 0);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[i].getKey();
        }
    }

    public static void sort(Node[] arr,Node[] aux,int low,int high,int d){
        if (high <= low ){
//            InsertionSortMSD.sort(arr, low, high, d);
            return;
        }

        int[] count = new int[R+2];

        for(int i=low;i<=high;i++){
            int c =  pinyinCharAt(arr[i],d);
            count[c+2]++;
        }
        for(int r=0;r<R;r++){
            count[r+1] += count[r];
        }

        for(int i=low;i<=high;i++){
            aux[count[pinyinCharAt(arr[i], d) + 1]++] = arr[i];
        }

        for(int i=low;i<=high;i++){
            arr[i] = aux[i-low];
        }
        for(int r=0;r<R;r++){
            sort(arr,aux,low+count[r],low+count[r+1]-1,d+1);
        }

    }

}
