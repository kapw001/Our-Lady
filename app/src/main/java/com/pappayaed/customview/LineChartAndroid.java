package com.pappayaed.customview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.pappayaed.common.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yasar on 19/2/18.
 */

public class LineChartAndroid extends LineChart {


    private static final String TAG = "LineChartAndroid";

    private ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();

    public LineChartAndroid(Context context) {
        super(context);
    }

    public LineChartAndroid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChartAndroid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LineDataSet getLineDataSet(ArrayList<Entry> entries, String label, int color) {
        LineDataSet dataset = new LineDataSet(entries, label);
        dataset.setDrawFilled(true);
        dataset.setDrawCircles(true);
//        dataset.setLineWidth(1.8f);
        dataset.setLineWidth(.0f);
        dataset.setMode(LineDataSet.Mode.LINEAR);
        dataset.setCircleRadius(3f);
        dataset.setCircleHoleRadius(0f);
        dataset.setCircleColor(color);
        dataset.setCircleColorHole(color);
        dataset.setHighLightColor(Color.rgb(244, 117, 117));
        dataset.setColor(color);
        dataset.setFillColor(color);
        dataset.setFillAlpha(100);
        dataset.setDrawHorizontalHighlightIndicator(false);
        dataset.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return -10;
            }
        });

        return dataset;
    }

    private void initChart() {


        setDescription(null);
        setDrawGridBackground(false);
//        setMaxVisibleValueCount(4);
//        setVisibleXRangeMaximum(4);
        setAutoScaleMinMaxEnabled(true);
        setDragEnabled(true);
        setPinchZoom(false);
        setDoubleTapToZoomEnabled(false);
//        getXAxis().setEnabled(false);

        // TODO: 19/2/18 set Right Axis Data

        setYAxisRightData();


        // TODO: 19/2/18 set Left Axis Data


        setYAxisLeftData();

    }

    private void setYAxisRightData() {
        getAxisRight().setDrawZeroLine(false);
        getAxisRight().setEnabled(false);
    }

    private void setYAxisLeftData() {
        getAxisLeft().setDrawGridLines(true);
        getAxisLeft().setEnabled(true);
        getAxisLeft().setDrawLabels(true);
//        getAxisLeft().setDrawZeroLine(false);
//        getAxisLeft().setDrawAxisLine(false);
    }

    public void setLineData(ArrayList<ILineDataSet> dataSets, ArrayList<String> labels, boolean isLagendEnabled) {

        setXAxis(labels);

        setLegend(isLagendEnabled);
        initChart();

        LineData data = new LineData(dataSets);
        data.setValueFormatter(new PercentFormatter());
        data.setDrawValues(false);
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(10f);

        animateY(1000, Easing.EasingOption.EaseInOutQuad);
        setData(data);
        invalidate();
    }


    // TODO: 13/2/18 setXaxis labels
    private XAxis setXAxis(ArrayList<String> labels) {

        XAxis xAxis = getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
//        xAxis.setXOffset(10f);
//        xAxis.setAxisLineWidth(.5f);
//        xAxis.setAxisMaximum(3);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        return xAxis;

    }

    // TODO: 13/2/18  this used set legend
    private Legend setLegend(boolean isEnabled) {
        //add legend to chart
        Legend legend = getLegend();
        legend.setEnabled(isEnabled);
//        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(10f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(5f);

        return legend;
    }

    public ArrayList<Entry> getEntries(Float[] f) {

        ArrayList<Entry> entries = new ArrayList();
        for (int i = 0; i < f.length; i++) {
            entries.add(new Entry((float) i, f[i]));
        }

        return entries;

    }

    public void setEntry(Float[] values, String label, int color) {

        iLineDataSets.clear();
        ArrayList<Entry> entries = new ArrayList();
        for (int i = 0; i < values.length; i++) {
            entries.add(new Entry((float) i, values[i]));
        }

        iLineDataSets.add(getLineDataSet(entries, label, color));


    }

    public void setEntry(List<Float> values, String label, int color) {

        iLineDataSets.clear();
        ArrayList<Entry> entries = new ArrayList();
        for (int i = 0; i < values.size(); i++) {
            entries.add(new Entry((float) i, values.get(i)));
        }

        iLineDataSets.add(getLineDataSet(entries, label, color));


    }

    public void showLineChart(ArrayList<String> labels, boolean isLagendEnabled) {

        if (iLineDataSets.size() > 0) {

            setLineData(iLineDataSets, labels, isLagendEnabled);
        } else {

            Log.e(TAG, "showLine: There is no data available ");
        }

    }


}
