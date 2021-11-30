package edu.neu.coe.info6205.msdRadix;

import com.google.common.io.Resources;
import com.ibm.icu.text.Collator;
import com.ibm.icu.text.RuleBasedCollator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MSDRadixSort {

//    private static char[] aux;
    int R=40959;
    int R2 = 19968;
//    65536
    static Collator collator = Collator.getInstance(Locale.CHINA);


    public static void main(String[] args) throws IOException {
        MSDRadixSort msdSort = new MSDRadixSort();
        try{
            List<String> list = new ReadTextFile().readStream();

//            System.out.println("Before sorting...");
//            System.out.println(list.toString());

//            Locale locale = new Locale("zh", "", "PINYIN");
//            Collator collator = (RuleBasedCollator) Collator.getInstance(locale);
            collator.setStrength(Collator.PRIMARY);
//            Collections.sort(list, collator);
//
//            System.out.println(list.toString());

            String[] a = list.toArray(new String[0]);
//            String a[] = {  "阿彬","阿安" ,"蓝桂", "蓝桂兰"};
            String[] aux = new String[a.length];
            Date d1 = new Date();
            msdSort.sort(a,aux, msdSort.R2,a.length-1,0);
            msdSort.print(a);
            Date d2 = new Date();
            System.out.println(d2.getTime()-d1.getTime());
//            msdSort.getC();


        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

//    public int getC(){
//        String input = "阿彬 阿安";
//
//        Map<Character, int[]> countMap = new HashMap<>();
//        for (Character ch : input.toCharArray()) {
//            int[] counter = countMap.get(ch);
//            if (counter == null)
//                countMap.put(ch, new int[] { 1 });
//            else
//                counter[0]++;
//        }
//        @SuppressWarnings("unchecked")
//        Map.Entry<Character, int[]>[] counts = countMap.entrySet().toArray(new Map.Entry[countMap.size()]);
////            Collator collator = Collator.getInstance(Locale.GERMAN);
//        Arrays.sort(counts, (e1, e2) -> collator.compare(e1.getKey().toString(), e2.getKey().toString()));
//        for (Map.Entry<Character, int[]> entry : counts)
//            System.out.printf("%c - %d%n", entry.getKey(), entry.getValue()[0]);
//
//        return 1;
//    }


    public void print(String[] str){
        for (String s:str) {
            System.out.println(s);
        }
    }

    private static int char_at(String s, int d){
        if (d < s.length()){
            int a = (int)s.charAt(d);
//            System.out.println(s +"  "+ a + " " + d);
            return a;
        }
        else return -1;
    }

    public  void sort(String[] a,String[] aux,int low,int high,int d){


        if(high<=low) return;
        int[] count = new int[R+2];
        for(int i=low;i<=high;i++){
            int c =  char_at(a[i],d);
            count[c+2]++;
        }
        for(int r=R2;r<R;r++){
            count[r+1] += count[r];
        }

        for(int i=low;i<=high;i++){
            aux[count[char_at(a[i], d) + 1]++] = a[i];
        }

        for(int i=low;i<=high;i++){
            a[i] = aux[i-low];
        }

        for(int r=R2;r<R;r++){
            sort(a,aux,low+count[r],low+count[r+1]-1,d+1);
        }

    }


}
