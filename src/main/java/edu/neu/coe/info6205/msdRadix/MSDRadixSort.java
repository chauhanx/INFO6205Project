package edu.neu.coe.info6205.msdRadix;

import com.google.common.io.Resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MSDRadixSort {

//    private static char[] aux;
    int R=65536;
//    65536


    public static void main(String[] args) throws IOException {
        MSDRadixSort msdSort = new MSDRadixSort();
        try{
            ArrayList<String> lines = new ReadTextFile().readStream();
            String[] a = lines.toArray(new String[0]);
            String[] aux = new String[a.length];
            msdSort.sort(a,aux,0,a.length-1,0);
            msdSort.print(a);
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void print(String[] str){
        for (String s:str) {
            System.out.println(s);
        }
    }

    private static int charAt(String s, int d){
        if (d < s.length()) return (int)s.charAt(d);
        else return -1;
    }

    public  void sort(String[] a,String[] aux,int low,int high,int d){

        if(high<=low) return;
        int[] count = new int[R+2];
        for(int i=low;i<=high;i++){
            int c =  charAt(a[i],d);
            count[c+2]++;
        }
        for(int r=0;r<R;r++){
            count[r+1] += count[r];
        }

        for(int i=low;i<=high;i++){
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        for(int i=low;i<=high;i++){
            a[i] = aux[i-low];
        }

        for(int r=0;r<R;r++){
            sort(a,aux,low+count[r],low+count[r+1]-1,d+1);
        }

    }


}
