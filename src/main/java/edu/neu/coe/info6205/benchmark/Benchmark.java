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
    static int initial = 250000;


    private static void startBenchMark() throws IOException {
        try{
            System.out.println("Processing benchmarking ...");
            IOTextFile io = new IOTextFile();
//            int[] length = {initial};
            int[] length = {initial,2*initial,4*initial};
            int totalAlgos = 4;
            for(int i=0;i<totalAlgos;i++){
                yData.add(new ArrayList<>());
            }

            for(int l:length){
                xData.add((double)(l));
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
        getChart();
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


    }

    private static void createChart() throws IOException {
        XYChart chart = new XYChartBuilder().width(1000).height(600).theme(Styler.ChartTheme.XChart).title("Array Sorting").xAxisTitle("Array Size").yAxisTitle("Time(in seconds)").build();

        // Customize Chart
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setXAxisTickMarkSpacingHint(100);
        chart.getStyler().setXAxisLabelRotation(45);

        XYSeries series = chart.addSeries("MSD Radix", xData,yData.get(1));
        series.setMarker(SeriesMarkers.DIAMOND);
        series = chart.addSeries("LSD Radix", xData,yData.get(2));
        series.setMarker(SeriesMarkers.PLUS);
        series = chart.addSeries("Tim Sort", xData,yData.get(0));
        series.setMarker(SeriesMarkers.PLUS);
        series = chart.addSeries("QuickDual Pivot", xData,yData.get(3));
        series.setMarker(SeriesMarkers.PLUS);
        new SwingWrapper<XYChart>(chart).displayChart();
        BitmapEncoder.saveBitmapWithDPI(chart, "./SortAlgoChart", BitmapEncoder.BitmapFormat.PNG, 300);
    }

//    @Override
    public static void getChart() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Sorting algorithms").xAxisTitle("Array Size").yAxisTitle("Time").theme(Styler.ChartTheme.GGPlot2).build();

        // Customize Chart
        String size[] = {"25","50k","1M","2M"};
        // Series
        chart.addSeries("Tim", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(0)));
        chart.addSeries("MSD Radix", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(1)));
        chart.addSeries("LSD Radix", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(2)));
        chart.addSeries("Quick DualPivot", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(3)));

        new SwingWrapper<CategoryChart>(chart).displayChart();
    }


    public static void main(String[] args) throws IOException {
        startBenchMark();

    }
}
