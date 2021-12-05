package edu.neu.coe.info6205.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.neu.coe.info6205.charts.Charts;
import edu.neu.coe.info6205.huskySort.PureHuskySort;
import edu.neu.coe.info6205.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.info6205.msdRadix.*;
import edu.neu.coe.info6205.util.Timer;


public class Benchmark {

    static List<Double> xData = new ArrayList<>();
    static List<List<Double>> yData = new ArrayList<>();
    static int initial = 250000;


    private static void startBenchMark() throws IOException {
        try{
            System.out.println("Processing benchmarking ...");
            IOTextFile io = new IOTextFile();
            int[] length = {initial};
//            int[] length = {initial,2*initial,4*initial};
            int totalAlgos = 4;
            for(int i=0;i<totalAlgos;i++){
                yData.add(new ArrayList<>());
            }

            for(int l:length){
                xData.add((double)(l));
                String[] words;
                if(l <= 4*initial){
                    words = io.readFileStreamByLength(true,l);
                }else{
                    words = new String[l];
                    for (int j = 0; j < l / 4*initial; j++) {
                        System.arraycopy(io.readFileStreamByLength(true, 4*initial), 0, words, j * 4*initial, 4*initial);
                    }
                }

                benchmark(words);
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Charts c = new Charts();
        c.createChart(xData,yData);
        c.getChart(xData,yData);
    }


    private  static void benchmark(String[] words){
        Timer timer;
        double mean;
        String type;
        int runs = 10;

//      Tim Sort Benchmark
        type = "Tim";
        timer = new Timer();
        final String[] tmp1 = Arrays.copyOf(words,words.length);
        TimSortChinese timSorter = new TimSortChinese();
        mean = timer.repeat(runs, () -> tmp1, t -> {
            timSorter.sort(tmp1);
            return null;
        });
        yData.get(0).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


//        MSD Benchmark
        type = "MSD Radix new ";
        timer = new Timer();
        final String[] tmp4 = Arrays.copyOf(words,words.length);
        MSDRadixSort msdSo = new MSDRadixSort();
        mean = timer.repeat(runs, () -> tmp4, t -> {
            msdSo.sort(tmp4);
            return null;
        });
        yData.get(1).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


//      LSD Benchmark
        type = "LSD Radix";
        timer = new Timer();
        final String[] tmp3 = Arrays.copyOf(words,words.length);
        LSDRadixSort sorter = new LSDRadixSort();
        mean = timer.repeat(runs, () -> tmp3, t -> {
            sorter.sort(tmp3);
            return null;
        });
        yData.get(2).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


//      QuickSOrt Pivot Benchmark
        type = "QuickDual Pivot";
        timer = new Timer();
        final String[] tmpQuick = Arrays.copyOf(words,words.length);
        QuickDualPivotC qs = new QuickDualPivotC();
        mean = timer.repeat(1, () -> tmpQuick, t -> {
            qs.sort(tmpQuick);
            return null;
        });
        yData.get(3).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


//      TimSort Benchmark
        type = "Husky Sort";
        timer = new Timer();
        final String[] tmphusky = Arrays.copyOf(words,words.length);
        PureHuskySort hs = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        mean = timer.repeat(1, () -> tmphusky, t -> {
            hs.sort(tmphusky);
            return null;
        });
        yData.get(3).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


    }




    public static void main(String[] args) throws IOException {
        startBenchMark();

    }
}
