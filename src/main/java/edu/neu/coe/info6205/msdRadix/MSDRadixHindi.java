package edu.neu.coe.info6205.msdRadix;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MSDRadixHindi {

    static int R=400;
    static int S=0;
    static boolean isChinese = false;

    public static void main(String[] args) throws IOException {
        try{
            IOTextFile io = new IOTextFile();
            String[] a = io.readFileStreamByLength(isChinese,200);
            sort(a);
            io.writeStream(a);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    // abc ,abx,zzz
    public static void print(String[] str){
        System.out.println(Arrays.toString(str));
    }


    public static int char_at(String s, int d){
        if (d < s.length()){
            return (int)s.charAt(d)-2300;
        }
        else return -1;
    }


    public static void sort(String[] arr){
        String[] aux = new String[arr.length];
        sort(arr,aux, 0,arr.length-1,0);
    }

    public static void sort(String[] arr,String[] aux,int low,int high,int d){
        if (high <= low){
            return;
        }
        int[] count = new int[R+2];

        for(int i=low;i<=high;i++){
            int c =  char_at(arr[i],d);
            count[c+2]++;
        }
        for(int r=S;r<R;r++){
            count[r+1] += count[r];
        }

        for(int i=low;i<=high;i++){
            aux[count[char_at(arr[i], d) + 1]++] = arr[i];
        }

        for(int i=low;i<=high;i++){
            arr[i] = aux[i-low];
        }
        for(int r=S;r<R;r++){
            sort(arr,aux,low+count[r],low+count[r+1]-1,d+1);
        }

    }

}
