package edu.neu.coe.info6205.msdRadix;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class MSDRadixSort {

    static int R=256;
    static int M = 15;
//    65536
    static boolean isChinese = true;

    public static void main(String[] args) throws IOException {
        try{
            List<String> list = new IOTextFile().readStream(isChinese);
            String[] a = list.toArray(new String[0]);
            Date start = new Date();
            if(isChinese){
                sortChinese(a);
            }else{
                R = 30000;
                sortHindi(a);
            }
            Date end = new Date();
            System.out.println(end.getTime()-start.getTime());
            new IOTextFile().writeStream(a);
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
            return a;
        }
        else return -1;
    }

    private static int char_at(String s, int d){
        if (d < s.length()){
            int a = (int)s.charAt(d);
            return a;
        }
        else return -1;
    }

    public static void sortChinese(String[] arr){

        Node[] aux = new Node[arr.length];
        Node[] array = new Node[arr.length];
        try {
            for (int i = 0; i < arr.length; i++) {
                array[i] = new Node(arr[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        sort(array, aux, 0, arr.length - 1, 0);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[i].getKey();
        }
    }

    public static void sortHindi(String[] arr){
        String[] aux = new String[arr.length];
        sort(arr,aux, 0,arr.length-1,0);
    }

    public static void sort(Node[] arr,Node[] aux,int low,int high,int d){
        if (high <= low){
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


    public static void sort(String[] arr,String[] aux,int low,int high,int d){
        if (high <= low){
            return;
        }

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




}
