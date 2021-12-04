package edu.neu.coe.info6205.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.neu.coe.info6205.msdRadix.*;
import edu.neu.coe.info6205.util.Timer;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;


public class Benchmark {

    final static String fileName = "chinese.txt";
    static List<Double> xData = new ArrayList<>();
    static List<List<Double>> yData = new ArrayList<>();


    private static void startBenchMark() throws IOException {
        try{
            System.out.println("Processing benchmarking ...");
            IOTextFile io = new IOTextFile();
//            int[] length = {250000};
            int[] length = {250000,500000,1000000,2000000};
            int totalAlgos = 3;
            for(int i=0;i<totalAlgos;i++){
                yData.add(new ArrayList<>());
            }

            for(int l:length){
                xData.add((double) l);
                String[] words;
                if(l <= 1000000){
                    words = io.readFileInRange(fileName,l);
                }else{
                    words = new String[l];
                    for (int j = 0; j < l / 1000000; j++) {
                        System.arraycopy(io.readFileInRange("chinese.txt", 1000000), 0, words, j * 1000000, 1000000);
                    }
                }

                benchmark(words);
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        createChart();

    }


    private  static void benchmark(String[] words){
        Timer timer;
        double mean;
        String type;
        int runs = 1;
//      MSD Benchmark
//        type = "MSD Radix";
//        timer = new Timer();
//        final String[] msdTemp = Arrays.copyOf(words,words.length);
//        MSDRadixSort msdSorter = new MSDRadixSort();
//        mean = timer.repeat(10, () -> msdTemp, t -> {
//            msdSorter.sort(msdTemp);
//            return null;
//        });
//        yData.get(0).add(mean);
//        System.out.println("Time taken for "+type+" for "+ runs +" runs to sort "+words.length + " array size: "+mean);

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


        type = "MSD Radix";
        timer = new Timer();
        final String[] tmp2 = Arrays.copyOf(words,words.length);
        MSDRadixSort MSDRadixSortSorter1 = new MSDRadixSort();
        mean = timer.repeat(runs, () -> tmp2, t -> {
            MSDRadixSortSorter1.sort(tmp2);
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
//        type = "QuickDual Pivot";
//        timer = new Timer();
//        final String[] quickTemp = Arrays.copyOf(words,words.length);
//        QuickDualPivotC quickSortter = new QuickDualPivotC();
//        mean = timer.repeat(runs, () -> quickTemp, t -> {
//            quickSortter.sort(quickTemp);
//            return null;
//        });
//        yData.get(3).add(mean);
//        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);


    }

    private static void createChart() throws IOException {
        XYChart chart = new XYChartBuilder().width(1000).height(600).theme(Styler.ChartTheme.XChart).title("Array Sorting").xAxisTitle("Array Size").yAxisTitle("Time(in seconds)").build();

        // Customize Chart
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setXAxisTickMarkSpacingHint(100);

        XYSeries series = chart.addSeries("MSD Radix", xData,yData.get(1));
        series.setMarker(SeriesMarkers.DIAMOND);
        series = chart.addSeries("LSD Radix", xData,yData.get(2));
        series.setMarker(SeriesMarkers.PLUS);
        series = chart.addSeries("Tim Sort", xData,yData.get(0));
        series.setMarker(SeriesMarkers.PLUS);
        new SwingWrapper<XYChart>(chart).displayChart();
        BitmapEncoder.saveBitmapWithDPI(chart, "./SortAlgoChart", BitmapEncoder.BitmapFormat.PNG, 300);
    }


    public static void main(String[] args) throws IOException {
        startBenchMark();
    }
}
