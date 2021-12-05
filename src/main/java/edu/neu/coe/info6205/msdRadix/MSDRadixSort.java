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
            String[] a =  io.readFileStreamByLength(isChinese,20);
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

//lsd//msd//quick//tim
////[曹玉德, 樊辉辉, 高民政, 顾芳芳, 郭健华, 洪文胜, 黄锡鸿, 刘持平, 罗庆富, 沙洪涛, 舒冬梅, 宋雪光, 苏会敏, 王广风, 王诗卉, 徐志森, 许凤山, 杨腊香, 袁继鹏, 苑彬]
////[曹玉德, 樊辉辉, 高民政, 顾芳芳, 郭健华, 洪文胜, 黄锡鸿, 刘持平, 罗庆富, 沙洪涛, 舒冬梅, 宋雪光, 苏会敏, 王广风, 王诗卉, 徐志森, 许凤山, 杨腊香, 袁继鹏, 苑彬]
////[曹玉德, 樊辉辉, 高民政, 顾芳芳, 郭健华, 洪文胜, 黄锡鸿, 刘持平, 罗庆富, 沙洪涛, 舒冬梅, 宋雪光, 苏会敏, 王广风, 王诗卉, 徐志森, 许凤山, 杨腊香, 袁继鹏, 苑彬]
//[袁继鹏, 樊辉辉, 王诗卉, 宋雪光, 王广风, 徐志森, 舒冬梅, 刘持平, 沙洪涛, 洪文胜, 许凤山, 黄锡鸿, 苏会敏, 苑彬, 罗庆富, 高民政, 杨腊香, 郭健华, 曹玉德, 顾芳芳]
