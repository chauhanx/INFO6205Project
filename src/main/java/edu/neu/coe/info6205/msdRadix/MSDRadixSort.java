package edu.neu.coe.info6205.msdRadix;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MSDRadixSort {

    static int R=65536;
    static int R2 = 0;
//    65536

    public static void main(String[] args) throws IOException {
        try{
            List<String> list = new ReadTextFile().readStream();
            String[] a = list.toArray(new String[0]);
            sort(a);
            print(a);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void print(String[] str){
        System.out.println(Arrays.toString(str));
    }

    private static int char_at(String s, int d){
        if (d < s.length()){
            int a = (int)s.charAt(d);
            return a;
        }
        else return -1;
    }

    public static void sort(String[] arr){
        String[] aux = new String[arr.length];
        sort(arr,aux, R2,arr.length-1,0);
    }

    public static void sort(String[] arr,String[] aux,int low,int high,int d){
        if(high<=low) return;
        int[] count = new int[R+2];

        for(int i=low;i<=high;i++){
            int c =  char_at(arr[i],d);
            count[c+2]++;
        }
        for(int r=R2;r<R;r++){
            count[r+1] += count[r];
        }

        for(int i=low;i<=high;i++){
            aux[count[char_at(arr[i], d) + 1]++] = arr[i];
        }

        for(int i=low;i<=high;i++){
            arr[i] = aux[i-low];
        }
        for(int r=R2;r<R;r++){
            sort(arr,aux,low+count[r],low+count[r+1]-1,d+1);
        }

    }


}
