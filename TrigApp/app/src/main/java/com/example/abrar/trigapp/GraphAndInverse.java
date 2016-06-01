package com.example.abrar.trigapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.graphics.*;
import org.achartengine.chart.*;

/**
 * Created by abrar on 3/28/16.
 */
public class GraphAndInverse extends Activity {
    private GraphicalView myChart;
    private XYSeriesRenderer sineRenderer,cosineRenderer,tangentRenderer, xAxisRenderer;
    private XYSeriesRenderer inverseSineRenderer, inverseCosineRenderer, inverseTangentRenderer;
    private XYMultipleSeriesRenderer myRenderer;
    private XYSeries sineSeries,cosineSeries,tangentSeries, xAxisSeries;
    private XYSeries inverseSineSeries, inverseCosineSeries, inverseTangentSeries;
    private XYMultipleSeriesDataset mySeries;
    private boolean sinClicked = false;
    private boolean cosClicked = false;
    private boolean tanClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        setContentView(R.layout.graph_and_inverse_layout);
        Button sinButton = (Button) findViewById(R.id.sineButton2);
        Button cosButton =(Button) findViewById(R.id.cosineButton2);
        Button tanButton = (Button) findViewById(R.id.tangentButton2);

        sinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                sinClicked =  true;
                doSin();
            }
        });

        cosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cosClicked =  true;
                doCos();
            }
        });

        tanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanClicked = true;
                doTan();
            }
        });
    }

    private void doSin() {
        myChart.invalidate();
        clearData();
        initializeSinChart();
        addSineData();
        //addXAxis();
        myChart = ChartFactory.getLineChartView(this, mySeries, myRenderer);
        LinearLayout layout=(LinearLayout)findViewById(R.id.chart2);
        layout.removeAllViews();
        layout.addView(myChart);
    }

    private void doCos() {
        myChart.invalidate();
        clearData();
        initializeCosChart();
        addCosineData();
        //addXAxis();
        myChart = ChartFactory.getLineChartView(this, mySeries, myRenderer);
        LinearLayout layout=(LinearLayout)findViewById(R.id.chart2);
        layout.removeAllViews();
        layout.addView(myChart);
    }

    private void doTan() {
        myChart.invalidate();
        clearData();
        initializeTanChart();
        addTanData();
        //addXAxis();
        myChart = ChartFactory.getLineChartView(this, mySeries, myRenderer);
        LinearLayout layout=(LinearLayout)findViewById(R.id.chart2);
        layout.removeAllViews();
        layout.addView(myChart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout layout=(LinearLayout)findViewById(R.id.chart2);
        if(myChart==null) {
            clearData();
            initializeChart();
            if(sinClicked) {
                addSineData();
            }
            else if(cosClicked) {
                addCosineData();
            }
            else if(tanClicked) {
                addTanData();
            }
            else
                addData();
            myChart = ChartFactory.getLineChartView(this, mySeries, myRenderer);
            layout.addView(myChart);
        } else {
            clearData();
            initializeChart();
            if(sinClicked) {
                addSineData();
            }
            if(cosClicked) {
                addCosineData();
            }
            if(tanClicked) {
                addTanData();
            }
            myChart.repaint();
        }
    }

    private void initializeChart()
    {
        //xAxisRenderer=new XYSeriesRenderer();
        sineRenderer=new XYSeriesRenderer();
        cosineRenderer=new XYSeriesRenderer();
        tangentRenderer=new XYSeriesRenderer();
        inverseSineRenderer=new XYSeriesRenderer();
        inverseCosineRenderer=new XYSeriesRenderer();
        inverseTangentRenderer=new XYSeriesRenderer();

        //xAxisRenderer.setColor(Color.BLACK);
        sineRenderer.setColor(Color.GREEN);
        cosineRenderer.setColor(Color.BLUE);
        tangentRenderer.setColor(Color.YELLOW);
        inverseSineRenderer.setColor(Color.RED);
        inverseCosineRenderer.setColor(Color.CYAN);
        inverseTangentRenderer.setColor(Color.MAGENTA);
        //xAxisRenderer.setLineWidth(5);
        sineRenderer.setLineWidth(3);
        cosineRenderer.setLineWidth(3);
        tangentRenderer.setLineWidth(3);
        inverseSineRenderer.setLineWidth(3);
        inverseCosineRenderer.setLineWidth(3);
        inverseTangentRenderer.setLineWidth(3);

        myRenderer=new XYMultipleSeriesRenderer();
        //myRenderer.setXAxisColor(Color.BLACK);
        //myRenderer.addSeriesRenderer(xAxisRenderer);
        myRenderer.addSeriesRenderer(sineRenderer);
        myRenderer.addSeriesRenderer(cosineRenderer);
        myRenderer.addSeriesRenderer(tangentRenderer);
        myRenderer.addSeriesRenderer(inverseSineRenderer);
        myRenderer.addSeriesRenderer(inverseCosineRenderer);
        myRenderer.addSeriesRenderer(inverseTangentRenderer);
        myRenderer.setPanEnabled(false);
        myRenderer.setYAxisMax(1);
        myRenderer.setYAxisMin(-1);
        myRenderer.setLegendTextSize(20);
        myRenderer.setAxesColor(Color.BLACK);
        myRenderer.setBackgroundColor(Color.WHITE);
        myRenderer.setMarginsColor(Color.WHITE);
        myRenderer.setYLabelsColor(0, Color.BLACK);
        myRenderer.setXLabelsColor(Color.BLACK);
        //xAxisSeries=new XYSeries("");
        sineSeries=new XYSeries("Sine");
        cosineSeries=new XYSeries("Cosine");
        tangentSeries=new XYSeries("Tangent");
        inverseSineSeries=new XYSeries("Inverse Sine");
        inverseCosineSeries=new XYSeries("Inverse Cosine");
        inverseTangentSeries=new XYSeries("Inverse Tangent");
        mySeries=new XYMultipleSeriesDataset();
        //mySeries.addSeries(xAxisSeries);

        mySeries.addSeries(sineSeries);
        mySeries.addSeries(cosineSeries);
        mySeries.addSeries(tangentSeries);
        mySeries.addSeries(inverseSineSeries);
        mySeries.addSeries(inverseCosineSeries);
        mySeries.addSeries(inverseTangentSeries);
    }

    private void initializeSinChart()
    {
        sineRenderer=new XYSeriesRenderer();
        inverseSineRenderer=new XYSeriesRenderer();
        sineRenderer.setColor(Color.GREEN);
        inverseSineRenderer.setColor(Color.RED);
        sineRenderer.setLineWidth(3);
        inverseSineRenderer.setLineWidth(3);
        myRenderer=new XYMultipleSeriesRenderer();
        myRenderer.addSeriesRenderer(sineRenderer);
        myRenderer.addSeriesRenderer(inverseSineRenderer);
        myRenderer.setPanEnabled(false);
        myRenderer.setYAxisMax(1);
        myRenderer.setYAxisMin(-1);
        myRenderer.setLegendTextSize(20);
        myRenderer.setAxesColor(Color.BLACK);
        myRenderer.setBackgroundColor(Color.WHITE);
        myRenderer.setMarginsColor(Color.WHITE);
        myRenderer.setYLabelsColor(0, Color.BLACK);
        myRenderer.setXLabelsColor(Color.BLACK);
        sineSeries=new XYSeries("Sine");
        inverseSineSeries=new XYSeries("Inverse Sine");
        mySeries=new XYMultipleSeriesDataset();
        //mySeries.addSeries(xAxisSeries);
        mySeries.addSeries(sineSeries);
        mySeries.addSeries(inverseSineSeries);
    }

    private void initializeCosChart()
    {
        cosineRenderer=new XYSeriesRenderer();
        inverseCosineRenderer=new XYSeriesRenderer();
        cosineRenderer.setColor(Color.GREEN);
        inverseCosineRenderer.setColor(Color.RED);
        cosineRenderer.setLineWidth(3);
        inverseCosineRenderer.setLineWidth(3);
        myRenderer=new XYMultipleSeriesRenderer();
        myRenderer.addSeriesRenderer(cosineRenderer);
        myRenderer.addSeriesRenderer(inverseCosineRenderer);
        myRenderer.setPanEnabled(false);
        myRenderer.setYAxisMax(1);
        myRenderer.setYAxisMin(-1);
        myRenderer.setLegendTextSize(20);
        myRenderer.setAxesColor(Color.BLACK);
        myRenderer.setBackgroundColor(Color.WHITE);
        myRenderer.setMarginsColor(Color.WHITE);
        myRenderer.setYLabelsColor(0, Color.BLACK);
        myRenderer.setXLabelsColor(Color.BLACK);
        sineSeries=new XYSeries("Cosine");
        inverseSineSeries=new XYSeries("Inverse Cosine");
        mySeries=new XYMultipleSeriesDataset();
        mySeries.addSeries(cosineSeries);
        mySeries.addSeries(inverseCosineSeries);
    }

    private void initializeTanChart()
    {
        tangentRenderer=new XYSeriesRenderer();
        inverseTangentRenderer=new XYSeriesRenderer();
        tangentRenderer.setColor(Color.GREEN);
        inverseTangentRenderer.setColor(Color.RED);
        tangentRenderer.setLineWidth(3);
        inverseTangentRenderer.setLineWidth(3);
        myRenderer =new XYMultipleSeriesRenderer();
        myRenderer.addSeriesRenderer(tangentRenderer);
        myRenderer.addSeriesRenderer(inverseCosineRenderer);
        myRenderer.setPanEnabled(false);
        myRenderer.setYAxisMax(1);
        myRenderer.setYAxisMin(-1);
        myRenderer.setLegendTextSize(20);
        myRenderer.setAxesColor(Color.BLACK);
        myRenderer.setBackgroundColor(Color.WHITE);
        myRenderer.setMarginsColor(Color.WHITE);
        myRenderer.setYLabelsColor(0, Color.BLACK);
        myRenderer.setXLabelsColor(Color.BLACK);
        sineSeries=new XYSeries("Tangent");
        inverseSineSeries=new XYSeries("Inverse Tangent");
        mySeries=new XYMultipleSeriesDataset();
        mySeries.addSeries(tangentSeries);
        mySeries.addSeries(inverseTangentSeries);
    }


    private void addData()
    {
        for(int angle=0;angle<=360;angle+=1)
        {
            sineSeries.add(angle, Math.sin(angle*(Math.PI/180)));
            inverseSineSeries.add(angle, -1 * Math.sin(angle*(Math.PI/180)));
            cosineSeries.add(angle, Math.cos(angle*(Math.PI/180)));
            inverseCosineSeries.add(angle, -1 * Math.cos(angle*(Math.PI/180)));
            tangentSeries.add(angle, Math.tan(angle*(Math.PI/180)));
            inverseTangentSeries.add(angle,-1 *  Math.tan(angle*(Math.PI/180)));
        }
    }

    private void addSineData()
    {
        for(int angle=0;angle<=360;angle+=1)
        {
            sineSeries.add(angle, Math.sin(angle * (Math.PI / 180)));
            inverseSineSeries.add(angle, -1 * Math.sin(angle * (Math.PI / 180)));

        }
    }

    private void addCosineData()
    {
        for(int angle=0;angle<=360;angle+=1)
        {
            cosineSeries.add(angle, Math.cos(angle * (Math.PI / 180)));
            inverseCosineSeries.add(angle, -1 * Math.cos(angle * (Math.PI / 180)));

        }
    }

    private void addTanData()
    {
        for(int angle=0;angle<=360;angle+=1)
        {
            tangentSeries.add(angle, Math.tan(angle * (Math.PI / 180)));
            inverseTangentSeries.add(angle, -1 * Math.tan(angle * (Math.PI / 180)));

        }
    }

    /*private void addXAxis(){
        for(int angle=0;angle<=360;angle+=1)
        {
            xAxisSeries.add(angle, 1);

        }
    }*/

    private void clearData() {
        if(myRenderer != null) {
            myRenderer.clearXTextLabels();
            myRenderer.clearYTextLabels();
            myRenderer.removeAllRenderers();
            if(sineSeries != null && inverseSineSeries != null){
                sineSeries.clear();
                inverseSineSeries.clear();
            }
            if(cosineSeries != null && inverseCosineSeries != null) {
                cosineSeries.clear();
                inverseCosineSeries.clear();
            }
            if(tangentSeries != null && inverseTangentSeries != null) {
                tangentSeries.clear();
                inverseTangentSeries.clear();
            }
        }

    }
}
