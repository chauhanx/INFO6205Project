package edu.neu.coe.info6205.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.neu.coe.info6205.charts.Charts;
import edu.neu.coe.info6205.huskySort.PureHuskySort;
import edu.neu.coe.info6205.msdRadix.*;
import edu.neu.coe.info6205.util.Timer;


public class BenchmarkHindi {

    final static boolean isChinese = false;
    static List<Double> xData = new ArrayList<>();
    static List<List<Double>> yData = new ArrayList<>();
    static int initial = 250000;

    private static void startBenchMark() throws IOException {
        try{
            System.out.println("Benchmarking for Hindi Words");
            System.out.println("Processing benchmarking ...");
            IOTextFile io = new IOTextFile();
            int[] length = {initial,2*initial};
//            int[] length = {initial,2*initial,4*initial,8*initial,16*initial};
            int totalAlgos = 5;
            for(int i=0;i<totalAlgos;i++){
                yData.add(new ArrayList<>());
            }

            for(int l:length){
                xData.add((double) l);
                String[] words;
                if(l <= 1000000){
                    words = io.readFileStreamByLength(isChinese,l);
                }else{
                    words = new String[l];
                    for (int j = 0; j < l / 1000000; j++) {
                        System.arraycopy(io.readFileStreamByLength(isChinese, 1000000), 0, words, j * 1000000, 1000000);
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

        type = "Tim";
        timer = new Timer();
        final String[] tmp1 = Arrays.copyOf(words,words.length);
        TimSortHindi timSorter = new TimSortHindi();
        mean = timer.repeat(runs, () -> tmp1, t -> {
            timSorter.timSort(tmp1,tmp1.length);
            return null;
        });
        yData.get(0).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


        type = "MSD Radix";
        timer = new Timer();
        final String[] tmp2 = Arrays.copyOf(words,words.length);
        MSDRadixHindi MSDRadixSortSorter1 = new MSDRadixHindi();
        mean = timer.repeat(runs, () -> tmp2, t -> {
            MSDRadixSortSorter1.sort(tmp2);
            return null;
        });
        yData.get(1).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);

//      LSD Benchmark
        type = "LSD Radix";
        timer = new Timer();
        final String[] tmplsd = Arrays.copyOf(words,words.length);
        LSDRadixHindi lsdSort = new LSDRadixHindi();
        mean = timer.repeat(runs, () -> tmplsd, t -> {
            lsdSort.sort(tmplsd);
            return null;
        });
        yData.get(2).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


//      Quick Benchmark
        type = "Quick Dual";
        timer = new Timer();
        final String[] tmp3 = Arrays.copyOf(words,words.length);
        QuickDualPivotHindi qs = new QuickDualPivotHindi();
        mean = timer.repeat(runs, () -> tmp3, t -> {
            qs.sort(tmp3);
            return null;
        });
        yData.get(3).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


//      Pure Husky Sort
        type = "Husky Sort";
        timer = new Timer();
        final String[] quickTemp = Arrays.copyOf(words,words.length);
        PureHuskySort hs = new PureHuskySort<>();
        mean = timer.repeat(runs, () -> quickTemp, t -> {
            hs.sort(quickTemp);
            return null;
        });
        yData.get(4).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);

    }

    public static void main(String[] args) throws IOException {
        startBenchMark();
    }
}
