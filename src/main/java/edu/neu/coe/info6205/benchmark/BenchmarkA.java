package edu.neu.coe.info6205.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.io.Resources;

import edu.neu.coe.info6205.msdRadix.*;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.util.Config;
//import INFO6205.src.test.java.edu.neu.coe.info6205.util.ConfigTest;
//import edu.neu.coe.info6205.util.PrivateMethodTester;
import edu.neu.coe.info6205.util.Timer;


public class BenchmarkA {

    static boolean isChinese = false;
    final static String fileName = "chinese.txt";


    private static void startBenchMark() {
        try{
            IOTextFile io = new IOTextFile();
            int[] length = {250000};
//            int[] length = {250000,250000,500000,999900};
            for(int l:length){
                String[] words = io.readFileInRange(fileName,l);
                benchmark(words);
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private  static void benchmark(String[] words){
        Timer timer = new Timer();
        double mean;

//      MSD Benchmark
        final String[] msdTemp = Arrays.copyOf(words,words.length);
        String type = "MSD Radix";
        MSDRadixSort msdSorter = new MSDRadixSort();
        mean = timer.repeat(10, () -> msdTemp, t -> {
            msdSorter.sort(msdTemp);
            return null;
        });
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);

//      LSD Benchmark
        timer = new Timer();
        final String[] lsdTemp = Arrays.copyOf(words,words.length);
        type = "LSD Radix";
        LSDRadixSort sorter = new LSDRadixSort();
        mean = timer.repeat(10, () -> lsdTemp, t -> {
            sorter.sort(lsdTemp);
            return null;
        });
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);
    }


    public static void main(String[] args) throws IOException {
        startBenchMark();
    }
}
