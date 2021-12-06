package edu.neu.coe.info6205.charts;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Charts {

    static List<Double> xData;
    static List<List<Double>> yData;

    public void createChart(List<Double> xData,List<List<Double>> yData) throws IOException {
        this.xData = xData;
        this.yData = yData;
        XYChart chart = new XYChartBuilder().width(1000).height(600).theme(Styler.ChartTheme.XChart).title("Array Sorting").xAxisTitle("Array Size").yAxisTitle("Time(in ms)").build();

        // Customize Chart
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setXAxisTickMarkSpacingHint(100);
        chart.getStyler().setXAxisLabelRotation(45);

        XYSeries series0 = chart.addSeries("Tim Sort", xData,yData.get(0));
        series0.setMarker(SeriesMarkers.PLUS);
        XYSeries series1 = chart.addSeries("MSD Radix", xData,yData.get(1));
        series1.setMarker(SeriesMarkers.DIAMOND);
        XYSeries series2 = chart.addSeries("LSD Radix", xData,yData.get(2));
        series2.setMarker(SeriesMarkers.PLUS);
        XYSeries series3 = chart.addSeries("QuickDual Pivot", xData,yData.get(3));
        series3.setMarker(SeriesMarkers.DIAMOND);
        XYSeries series4 = chart.addSeries("Husky Sort", xData,yData.get(4));
        series4.setMarker(SeriesMarkers.PLUS);

        new SwingWrapper<XYChart>(chart).displayChart();
        BitmapEncoder.saveBitmapWithDPI(chart, "./SortAlgoChart", BitmapEncoder.BitmapFormat.PNG, 300);
    }

    public  void getChart(List<Double> xData,List<List<Double>> yData) {
        this.yData = yData;
        this.xData = xData;
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Sorting algorithms").xAxisTitle("Array Size").yAxisTitle("Time (in ms)").theme(Styler.ChartTheme.GGPlot2).build();

        // Customize Chart
        String size[] = {"25","50k","1M","2M"};
        // Series
        chart.addSeries("Tim", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(0)));
        chart.addSeries("MSD Radix", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(1)));
        chart.addSeries("LSD Radix", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(2)));
        chart.addSeries("Quick DualPivot", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(3)));
        chart.addSeries("Husky", new ArrayList<Double>(xData), new ArrayList<Double>(yData.get(4)));

        new SwingWrapper<CategoryChart>(chart).displayChart();
    }


}
