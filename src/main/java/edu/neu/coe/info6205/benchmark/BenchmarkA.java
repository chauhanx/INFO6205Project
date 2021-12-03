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


public class BenchmarkA {

    final static String fileName = "chinese.txt";
    static List<Double> xData = new ArrayList<>();
    static List<List<Double>> yData = new ArrayList<>();


    private static void startBenchMark() throws IOException {
        try{
            IOTextFile io = new IOTextFile();
            int[] length = {250000};
//            int[] length = {250000,500000,999900};
            int totalAlgos = 2;
            for(int i=0;i<totalAlgos;i++){
                yData.add(new ArrayList<>());
            }

            for(int l:length){
                xData.add((double) l);
                String[] words = io.readFileInRange(fileName,l);
                benchmark(words);
            }
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        createChart();

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
        yData.get(0).add(mean);
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
        yData.get(1).add(mean);
        System.out.println("Time taken for "+type+" to sort "+words.length + " array size: "+mean);
    }


    private static void createChart() throws IOException {
        XYChart chart = new XYChartBuilder().width(1000).height(600).theme(Styler.ChartTheme.XChart).title("Array Sorting").xAxisTitle("Array Size").yAxisTitle("Time(in seconds)").build();

        // Customize Chart
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setXAxisTickMarkSpacingHint(100);

        XYSeries series = chart.addSeries("MSD Radix", xData,yData.get(0));
        series.setMarker(SeriesMarkers.DIAMOND);
        series = chart.addSeries("LSD Radix", xData,yData.get(1));
        series.setMarker(SeriesMarkers.PLUS);
        new SwingWrapper<XYChart>(chart).displayChart();
        BitmapEncoder.saveBitmapWithDPI(chart, "./SortAlgoChart", BitmapEncoder.BitmapFormat.PNG, 300);
    }


    public static void main(String[] args) throws IOException {
        startBenchMark();
    }
}
